//add banner
$('#addFinishBannerSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addBanner')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/banner/add',
        // datatype: 'json',
        type: 'post',
        // contentType: "application/json",
        enctype: 'multipart/form-data',
        processData : false,  // <----required to upload
        contentType : false,  // <----required to upload
        cache : false,
        timeout: 600000,
        data: data,
        success: function (data) {
            console.log(data);
            window.location.href = "/admin";
        }


    })

});

//delete banner
$('.btnDeleteBanner').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteBanner').click(function () {
        var id = elementId;
        $.ajax({
            url: 'banner/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btnDeleteBanner').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteBanner').modal('hide');
            }
        });
    });
})

$('.btn_edit_banner').click(function() {

    var id = $(this).attr('data');
    getBannerById(id);

});

//get banner
function getBannerById(id) {
    $.ajax({
        url: '/banner/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditBanner .form-group .id").val(data.id);
            $(".formEditBanner .form-group .caption").val(data.caption);
            $(".formEditBanner .form-group .text").val(data.text);
            $(".formEditBanner .form-group .alt").val(data.altImage);
            $(".formEditBanner .form-group .imageBanner").attr("src", "files/"+data.imageId);
        }
    });
}

//update banner
$('#editFinishBannerSubmit').click(function() {

    var form = $('#uploadFormDataBanner')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/banner/update',
        // datatype: 'json',
        type: 'post',
        // contentType: "application/json",
        enctype: 'multipart/form-data',
        processData : false,  // <----required to upload
        contentType : false,  // <----required to upload
        cache : false,
        timeout: 600000,
        data: data,
        success: function (data) {
            $("#editBanner").modal('hide');
            var elementParentByID = $('.btn_edit_banner').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            console.log(elementParentByID);
            elementParentByID.children('#bannerAltImage').text('Описание изображения: '+ data.altImage);
            elementParentByID.children('#bannerText').text('Текст баннера: '+ data.text);
            elementParentByID.children('#bannerCaption').text('Заголовок баннера: '+ data.caption);


            elementParentByID.parent().children('.card-img-top').attr('src','files/'+data.imageId);
        }


    });

});
