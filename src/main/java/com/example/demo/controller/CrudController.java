package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class CrudController {
    @Autowired
    private UserService service;


    @Autowired
    private MetaFileDbService offerService;

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private MainInfoRepository mainInfoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ListImageRepository listImageRepository;

    @Autowired
    private FileDBDao fileDbRepository;


    @GetMapping("admin/add")
    public ResponseEntity<JsonObject> getRoles() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.setAllRoles(service.getAllRole());

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("user/requestById")
    public ResponseEntity<JsonObject> getUserById(@RequestBody User user) {
        Long id = user.getId();
        User userById = service.getUserById(id);
        List<Role> allRoles = service.getAllRole();
        JsonObject jsonObject = new JsonObject();
        jsonObject.setUser(userById);
        jsonObject.setAllRoles(allRoles);

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestBody User user) {
        Long id = user.getId();
        System.out.println(id);
        User userDeleted = service.getUserById(id);
        service.delete(userDeleted);
        return  "delete";
    }

    @PostMapping("/admin/add")
    public String addUser(@RequestBody User user) {
        String[] roles = new String[user.getRoles().size()];
        for (int i = 0 ; i < user.getRoles().size(); i++) {
            roles[i] = user.getRoles().get(i).getName();
        }
        service.add(user, roles);
        return  "add";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser(@RequestBody User user) {
        System.out.println(user.getRoles());
        String[] roles = new String[user.getRoles().size()];
        for (int i = 0 ; i < user.getRoles().size(); i++) {
            roles[i] = user.getRoles().get(i).getName();
        }
        service.edit(user, roles);
        return "update";
    }

    @PostMapping("/user/checkEmail")
    public ResponseEntity<JsonObject> checkEmail(@RequestBody User user) {
//        Long id = user.getId();
        String email = user.getEmail();
//        User userById = service.getUserById(id);
        boolean unicEmail;
        if (!service.unicEmail(email)) {
            unicEmail = false;
        } else { unicEmail = true; }

        JsonObject jsonObject = new JsonObject();
        jsonObject.setUnicEmail(unicEmail);

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }



    @PostMapping("/product/requestById")
    public ResponseEntity<Product> getProductById(@RequestBody Product product) {
        product = productRepository.findById(product.getId()).get();
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/product/delete")
    public String deleteProduct(@RequestBody Product product1) {
        Product product = productRepository.findById(product1.getId()).get();

        List<ListImage> productImage = listImageRepository.getListImageByProductId(product.getId().toString());

        for (int i = 0; i < productImage.size(); i++) {
            String imageId = productImage.get(i).getImageId();
            FileDB file = storageService.getFile(imageId);
            fileDbRepository.delete(file);

            listImageRepository.delete(productImage.get(i));
        }

        productRepository.delete(product);
        return "delete";
    }

    @PostMapping("/product/add")
    public ResponseEntity addProduct(@ModelAttribute UploadForm form) throws Exception{
        try{

            Product product = new Product();
            product.setName(form.getName());
            Product forListImage =  productRepository.save(product);
            List<ListImage> listImagesId = new ArrayList<>();
            for (int i = 0; i < form.getFile().length; i++) {
                ListImage listImage = new ListImage();
                FileDB fileDB = storageService.store(form.getFile()[i]);
                String fileName1 = StringUtils.cleanPath(form.getFile()[i].getOriginalFilename());
                byte[] data1 = form.getFile()[i].getBytes();
                String idImage = fileDB.getId();
                listImage.setImageId(idImage);
                listImage.setProductId(forListImage.getId().toString());
                listImageRepository.save(listImage);
            }


            product.setName(form.getName());


            productRepository.save(product);
            System.out.println("ADD product");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("don't add");
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/faq/update")
    public ResponseEntity updateFaq(@ModelAttribute UploadForm form) throws Exception{

        try{
            GeneralEntry adv = new GeneralEntry();
            adv.setId(form.getId());
            adv.setCaption(form.getCaption());
            adv.setText(form.getText());
            adv.setCategory("faq");
            GeneralEntry saveAdv = new GeneralEntry();
            saveAdv =  entryRepository.save(adv);

            return new ResponseEntity(saveAdv, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/faq/requestById")
    public ResponseEntity<GeneralEntry> getFaqById(@RequestBody GeneralEntry generalEntry) {
        generalEntry = entryRepository.findById(generalEntry.getId()).get();
        return new ResponseEntity<GeneralEntry>(generalEntry, HttpStatus.OK);
    }

    @PostMapping("/faq/delete")
    public String deleteFaq(@RequestBody GeneralEntry adv) {
        GeneralEntry  adv1 = entryRepository.findById(adv.getId()).get();
        entryRepository.delete(adv1);
        return "delete";
    }

    @PostMapping("/faq/add")
    public ResponseEntity addFaq(@ModelAttribute UploadForm form) throws Exception{
        try{
            GeneralEntry adv = new GeneralEntry();
            adv.setCategory("faq");
            adv.setCaption(form.getCaption());
            adv.setText(form.getText());
            entryRepository.save(adv);
            System.out.println("ADD faq");
            return new ResponseEntity(adv, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("don't add");
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/offer/update")
    public ResponseEntity updateOffer(@ModelAttribute UploadForm form) throws Exception{
        String imageId;
        //if old image
        try{
            GeneralEntry generalEntry = new GeneralEntry();
            if (form.getFile()[0].getOriginalFilename().isEmpty()) {
                String bannerId = form.getId().toString();
                generalEntry = entryRepository.findById(Long.parseLong(bannerId)).get();
                imageId = generalEntry.getImageId();

            } else {
                String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
                byte[] data1 = form.getFile()[0].getBytes();
                FileDB fileDB = storageService.store(form.getFile()[0]);
                imageId = fileDB.getId();
            }
            generalEntry.setId(form.getId());
            generalEntry.setImageId(imageId);
            generalEntry.setCaption(form.getCaption());
            generalEntry.setAltImage(form.getAltImage());
            generalEntry.setText(form.getText());
            generalEntry.setCategory("offer_c");

            GeneralEntry saveGeneralEntry = new GeneralEntry();
            saveGeneralEntry =  entryRepository.save(generalEntry);

            return new ResponseEntity(saveGeneralEntry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/offer/requestById")
    public ResponseEntity<GeneralEntry> getOfferById(@RequestBody GeneralEntry generalEntry) {
        generalEntry = entryRepository.findById(generalEntry.getId()).get();
        return new ResponseEntity<GeneralEntry>(generalEntry, HttpStatus.OK);
    }

    @PostMapping("/offer/delete")
    public String deleteOffer(@RequestBody String id) {
        GeneralEntry offer = entryRepository.findById(Long.parseLong(id)).get();
        entryRepository.delete(offer);
        return "delete";
    }


    @PostMapping("/offer/add")
    public ResponseEntity addOffer(@ModelAttribute UploadForm form) throws Exception{
        try{
            FileDB fileDB = storageService.store(form.getFile()[0]);
            String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
            byte[] data1 = form.getFile()[0].getBytes();
            String idImage = fileDB.getId();

            GeneralEntry offer = new GeneralEntry();
            offer.setCategory("offer_c");
            offer.setCaption(form.getCaption());
            offer.setText(form.getText());
            offer.setAltImage(form.getAltImage());
            offer.setImageId(idImage);
            entryRepository.save(offer);
            System.out.println("ADD offer");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("don't add");
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/info/update")
    public ResponseEntity updateInfo(@ModelAttribute UploadForm form) throws Exception{

        try{
            MainInfo info = new MainInfo(form.getId(), form.getNameCompany(), form.getTelephone(), form.getEmail(), form.getTimeWork(), form.getAboutCompany());

            MainInfo saveInfo = new MainInfo();
            saveInfo =  mainInfoRepository.save(info);

            return new ResponseEntity(saveInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/info/requestById")
    public ResponseEntity<MainInfo> getInfoById(@RequestBody MainInfo info) {
        info = mainInfoRepository.findById(info.getId()).get();
        return new ResponseEntity<MainInfo>(info, HttpStatus.OK);
    }

    @PostMapping("/adv/requestById")
    public ResponseEntity<GeneralEntry> getAdvById(@RequestBody GeneralEntry generalEntry) {
        generalEntry = entryRepository.findById(generalEntry.getId()).get();
        return new ResponseEntity<GeneralEntry>(generalEntry, HttpStatus.OK);
    }

    @PostMapping("/adv/update")
    public ResponseEntity updateAdv(@ModelAttribute UploadForm form) throws Exception{

        try{
            GeneralEntry adv = new GeneralEntry();
            adv.setId(form.getId());
            adv.setCaption(form.getCaption());
            adv.setText(form.getText());
            adv.setCategory("advantage");
            GeneralEntry saveAdv = new GeneralEntry();
            saveAdv =  entryRepository.save(adv);

            return new ResponseEntity(saveAdv, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/adv/delete")
    public String deleteAdv(@RequestBody GeneralEntry adv) {
        GeneralEntry  adv1 = entryRepository.findById(adv.getId()).get();
        entryRepository.delete(adv1);
        return "delete";
    }

    @PostMapping("/adv/add")
    public ResponseEntity addAdv(@ModelAttribute UploadForm form) throws Exception{
        try{
            GeneralEntry adv = new GeneralEntry();
            adv.setCategory("advantage");
            adv.setCaption(form.getCaption());
            adv.setText(form.getText());
            entryRepository.save(adv);
            System.out.println("ADD advantage");
            return new ResponseEntity(adv, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("don't add");
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/banner/add")
    public ResponseEntity addBanner(@ModelAttribute UploadForm form) throws Exception{
        try{
            FileDB fileDB = storageService.store(form.getFile()[0]);
            String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
            byte[] data1 = form.getFile()[0].getBytes();
            String idImage = fileDB.getId();

            GeneralEntry generalEntry = new GeneralEntry();
            generalEntry.setAltImage(form.getAltImage());
            generalEntry.setImageId(idImage);
            generalEntry.setCaption(form.getCaption());
            generalEntry.setText(form.getText());
            generalEntry.setCategory("banner");
            entryRepository.save(generalEntry);
            System.out.println("ADD banner");
            return new ResponseEntity(generalEntry, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("don't add");
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/banner/delete")
    public String deleteBanner(@RequestBody GeneralEntry generalEntry) {
        entryRepository.delete(generalEntry);
        return  "delete";
    }

    @PostMapping("/banner/requestById")
    public ResponseEntity<GeneralEntry> getBannerById(@RequestBody GeneralEntry generalEntry) {
        generalEntry = entryRepository.findById(generalEntry.getId()).get();
        return new ResponseEntity<GeneralEntry>(generalEntry, HttpStatus.OK);
    }

    @PostMapping("/banner/update")
    public ResponseEntity updateBanner(@ModelAttribute UploadForm form) throws Exception{
        String imageId;
        //if old image
        try{
            GeneralEntry generalEntry = new GeneralEntry();
            if (form.getFile()[0].getOriginalFilename().isEmpty()) {
                String bannerId = form.getId().toString();
                generalEntry = entryRepository.findById(Long.parseLong(bannerId)).get();
                imageId = generalEntry.getImageId();

            } else {
                String fileName1 = StringUtils.cleanPath(form.getFile()[0].getOriginalFilename());
                byte[] data1 = form.getFile()[0].getBytes();
                FileDB fileDB = storageService.store(form.getFile()[0]);
                imageId = fileDB.getId();
            }
            generalEntry.setId(form.getId());
            generalEntry.setImageId(imageId);
            generalEntry.setCaption(form.getCaption());
            generalEntry.setAltImage(form.getAltImage());
            generalEntry.setText(form.getText());
            generalEntry.setCategory("banner");

            GeneralEntry saveGeneralEntry = new GeneralEntry();
            saveGeneralEntry =  entryRepository.save(generalEntry);

            return new ResponseEntity(saveGeneralEntry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}

