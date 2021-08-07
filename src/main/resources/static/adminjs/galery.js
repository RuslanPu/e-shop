//add galery
$('#addFinishGalerySubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addGalery')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/galery/add',
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

$('.btnDeleteGalery').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteGalery').click(function () {
        var id = elementId;
        $.ajax({
            url: 'galery/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btnDeleteGalery').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteGalery').modal('hide');
            }
        });
    });
})
