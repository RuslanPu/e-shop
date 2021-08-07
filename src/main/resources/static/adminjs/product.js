//add banner
$('#addFinishProductSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addProduct')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/product/add',
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
            window.location.href = "/product";
        }


    })

});

//delete banner
$('.btnDeleteProduct').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteProduct').click(function () {
        var id = elementId;
        $.ajax({
            url: '/product/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btnDeleteProduct').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteProduct').modal('hide');
            }
        });
    });
})

$('.btn_edit_product').click(function() {

    var id = $(this).attr('data');
    getProductById(id);

});

function getProductById(id) {
    $.ajax({
        url: '/product/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditOffer .form-group .id").val(data.id);
            $(".formEditOffer .form-group .name").val(data.caption);

        }
    });
}

$('#editFinishProductSubmit').click(function() {

    var form = $('#uploadFormDataProduct')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/product/update',
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
            window.location.href = "/product";
        }


    });

});
