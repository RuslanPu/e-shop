//add offer
$('#addFinishOfferSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addOffer')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/timbildingO/add',
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
            window.location.href = "/timbildingForAdmin";
        }


    })

});
//action click button edit offer
var offerDataAfterEdit = new Object();
//click edit offer to open modal window
$('.btn_edit_offer').click(function() {

    var id = $(this).attr('data');
    getOfferById(id);

});

//update offer
$('#editFinishOfferSubmit').click(function() {

    var form = $('#uploadFormDataOffer')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/timbildingO/update',
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
            elementParentByID.children('#offerName').text('Название: '+ data.caption);
            elementParentByID.children('#offerCost').text('Стоимость: ' + data.articleText);


        }


    });

});

//get offer
function getOfferById(id) {
    $.ajax({
        url: '/timbildingO/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditOffer .form-group .id").val(data.timbildingOffer.id);
            $(".formEditOffer .form-group .name").val(data.timbildingOffer.caption);
            $(".formEditOffer .form-group .cost").val(data.timbildingOffer.articleText);

        }
    });
}
//delete offer
$('.btnDeleteOffer').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteOffer').click(function () {
        var id = elementId;
        $.ajax({
            url: 'timbildingO/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btn_edit_offer').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteOffer').modal('hide');
            }
        });
    });
})