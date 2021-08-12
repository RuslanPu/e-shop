var entryDataAfterEdit = new Object();
//click edit entry to open modal window
$('.btn_edit_entry').click(function() {

    var idEntry = $(this).attr('data');
    getEntryById(idEntry);

});
//update entry
$('#editEntrySubmit').click(function() {
    var id = $('.formEntryEdit .form-group .id').val();
    var title = $(".entryTitle").text();
    var text = $('.formEntryEdit .form-group .entryTextarea').val();
    $.ajax({
        url: '/entry/updateEntry',
        datatype: 'json',
        type: 'post',
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            title: title,
            text: text

        }),
        success: function (data) {
            console.log(data);
        }


    })

    entryDataAfterEdit['id'] = id;
    entryDataAfterEdit['title'] = title;
    entryDataAfterEdit['text'] = text;

    $('#editEntryModal').modal('hide');

    var elementParentByID = $('.btn_edit_entry').filter(function () {
        return $(this).attr('data') == entryDataAfterEdit.id;
    }).parent();

    elementParentByID.children('.card-title').text(entryDataAfterEdit.title);

    elementParentByID.children('.card-text').text(entryDataAfterEdit.text);


});
//get data entry via rest controller and ajax
function getEntryById(id) {
    $.ajax({
        url: '/entry/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $("#editEntryModalLabel").text(data.entry.title);
            $('.formEntryEdit .form-group .entryTextarea').val(data.entry.text);
            $(".formEntryEdit .form-group .id").attr("value", data.entry.id);
        }
    });

}