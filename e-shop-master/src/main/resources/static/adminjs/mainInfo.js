

$('.btn_edit_info').click(function() {

    var id = $(this).attr('data');
    getInfoById(id);

});


//get banner
function getInfoById(id) {
    $.ajax({
        url: '/info/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditInfo .form-group .id").val(data.id);
            $(".formEditInfo .form-group .name").val(data.nameCompany);
            $(".formEditInfo .form-group .tel").val(data.telephone);
            $(".formEditInfo .form-group .email").val(data.email);
            $(".formEditInfo .form-group .time").val(data.timeWork);
            $(".formEditInfo .form-group .about").val(data.aboutCompany);
        }
    });
}

//update adv
$('#editFinishInfoSubmit').click(function() {

    var form = $('#uploadFormDataInfo')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/info/update',
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
            $("#editInfo").modal('hide');
            var elementParentByID = $('.btn_edit_info').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            console.log(elementParentByID);
            elementParentByID.children('#infoName').text('Название сайта : '+ data.nameCompany);
            elementParentByID.children('#infoTel').text('Телефон : '+ data.telephone);
            elementParentByID.children('#infoEmail').text('email : '+ data.email);
            elementParentByID.children('#infoTime').text('Режим работы : '+ data.timeWork);
            elementParentByID.children('#infoAbout').text('Режим работы : '+ data.aboutCompany);

        }


    });

});
