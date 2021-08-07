//add banner
$('#addFinishOfferSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addOffer')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/offer/add',
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
$('.btnDeleteOffer').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteOffer').click(function () {
        var id = elementId;
        $.ajax({
            url: 'offer/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btnDeleteOffer').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteOffer').modal('hide');
            }
        });
    });
})

$('.btn_edit_offer').click(function() {

    var id = $(this).attr('data');
    getOfferById(id);

});

//get banner
function getOfferById(id) {
    $.ajax({
        url: '/offer/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditOffer .form-group .id").val(data.id);
            $(".formEditOffer .form-group .caption").val(data.caption);
            $(".formEditOffer .form-group .text").val(data.text);
            $(".formEditOffer .form-group .alt").val(data.altImage);
            $(".formEditOffer .form-group .imageOffer").attr("src", "files/"+data.imageId);
        }
    });
}

//update banner
$('#editFinishOfferSubmit').click(function() {

    var form = $('#uploadFormDataOffer')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/offer/update',
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
            $("#editOffer").modal('hide');
            var elementParentByID = $('.btn_edit_offer').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            console.log(elementParentByID);
            elementParentByID.children('#offerAltImage').text('Alt изображения : '+ data.altImage);
            elementParentByID.children('#offerText').text('Заголовок услуги : '+ data.text);
            elementParentByID.children('#offerCaption').text('Текст услуги : '+ data.caption);


            elementParentByID.parent().children('.card-img-top').attr('src','files/'+data.imageId);
        }


    });

});
