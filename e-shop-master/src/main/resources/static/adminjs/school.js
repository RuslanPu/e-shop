//add kinder article
$('#addFinishKinderSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addKinder')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/school/add',
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
            window.location.href = "/schoolForAdmin";

        }


    })

});

//action click button edit article
var KinderDataAfterEdit = new Object();
//click edit article to open modal window
$('.btn_edit_kinder').click(function() {

    var id = $(this).attr('data');
    getKinderById(id);

});

//update article
$('#editFinishKinderSubmit').click(function() {

    var form = $('#uploadFormDataKinder')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/school/update',
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

            $("#editKinder").modal('hide');
            var elementParentByID = $('.btn_edit_kinder').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            elementParentByID.children('#kinderName').text('Название : '+ data.caption);
            elementParentByID.children('#kinderText').text('Tекст : ' + data.articleText);
            elementParentByID.children('#kinderAltImage').text('Alt image: ' + data.altImage);
            elementParentByID.parent().children('.card-img-top').attr('src','files/'+data.imageId);
        }


    });

});

//get article
function getKinderById(id) {
    $.ajax({
        url: '/school/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditKinder .form-group .id").val(data.school.id);
            $(".formEditKinder .form-group .name").val(data.school.caption);

            $(".formEditKinder .form-group .altImage").val(data.school.altImage);
            $('.formEditKinder .form-group .textKinderTextarea').val(data.school.articleText);
            $(".formEditKinder .form-group .imageKinder").attr("src","files/"+ data.school.imageId );

        }
    });
}

//delete article
$('.btnDeleteKinder').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteKinder').click(function () {
        var id = elementId;
        $.ajax({
            url: 'school/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btn_edit_kinder').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().parent().remove();


                $('#deleteKinder').modal('hide');
            }
        });
    });
})