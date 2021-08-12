//add banner
$('#addFinishAdvSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addAdv')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/adv/add',
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

//delete adv
$('.btnDeleteAdv').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteAdv').click(function () {
        var id = elementId;
        $.ajax({
            url: '/adv/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btnDeleteAdv').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteAdv').modal('hide');
            }
        });
    });
})

$('.btn_edit_adv').click(function() {

    var id = $(this).attr('data');
    getAdvById(id);

});


//get banner
function getAdvById(id) {
    $.ajax({
        url: '/adv/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditAdv .form-group .id").val(data.id);
            $(".formEditAdv .form-group .caption").val(data.caption);
            $(".formEditAdv .form-group .text").val(data.text);
        }
    });
}

//update adv
$('#editFinishAdvSubmit').click(function() {

    var form = $('#uploadFormDataAdv')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/adv/update',
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
            $("#editAdv").modal('hide');
            var elementParentByID = $('.btn_edit_adv').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            console.log(elementParentByID);
            elementParentByID.children('#advText').text('Текст преимущества: '+ data.text);
            elementParentByID.children('#advCaption').text('Заголовок преимущства: '+ data.caption);

        }


    });

});
