//add banner
$('#addFinishFaqSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addFaq')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/faq/add',
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
$('.btnDeleteFaq').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteFaq').click(function () {
        var id = elementId;
        $.ajax({
            url: '/faq/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btnDeleteFaq').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteFaq').modal('hide');
            }
        });
    });
})

$('.btn_edit_faq').click(function() {

    var id = $(this).attr('data');
    getFaqById(id);

});


//get banner
function getFaqById(id) {
    $.ajax({
        url: '/faq/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditFaq .form-group .id").val(data.id);
            $(".formEditFaq .form-group .caption").val(data.caption);
            $(".formEditFaq .form-group .text").val(data.text);
        }
    });
}

//update adv
$('#editFinishFaqSubmit').click(function() {

    var form = $('#uploadFormDataFaq')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/faq/update',
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
            $("#editFaq").modal('hide');
            var elementParentByID = $('.btn_edit_faq').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            console.log(elementParentByID);
            elementParentByID.children('#faqText').text('Ответ : '+ data.text);
            elementParentByID.children('#faqCaption').text('Вопрос : '+ data.caption);

        }


    });

});
