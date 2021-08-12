$('#afterAdd').click(function() {
    $('.add .roles').find('option').remove();
    $.ajax({
        url: '/admin/add',
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        data: JSON,
        success: function (data) {
            console.log(data);
            var objSelect = $(".add .roles");

            $.each(data.allRoles, function (key, value) {
                $(objSelect).append($("<option></option>", {value: value.name, text: value.name}));
            });
        }
    });
})


$('.buttonAddUser').click(function () {
    var roles = new Array;
    $('.add .roles option').each(function () {
        if ($(this).prop('selected') == true) {
            roles.push($(this).val());
        }
    });


    var name = $(".add input[name='name']").val();
    var lastName = $(".add input[name='lastName']").val();
    var age = $(".add input[name='age']").val();
    var password = $(".add input[name='password']").val();
    var email = $(".add input[name='email']").val();

    $.ajax({
        url: '/admin/add',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            password: password,
            email: email,
            roles: roles
        }),
        success: function (data) {
            console.log(data);
            window.location.href = "/admin";
        }
    });

})
//delete user
$('.buttonDelete').click(function() {
    var elementStringTableByID = $(this).attr('data');
    $('.buttonAfterDelete').click(function () {
        var id = elementStringTableByID;
        $.ajax({
            url: '/admin/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementStrinTableByID = $('tbody th').filter(function () {
                    return $(this).text() == id;
                }).parent().remove();
                $('#exampleModal1').modal('hide');

            }
        });
    });
});

var     userAfterSubmit = new Object();


function addCheckRoleInUserRoles(arr) {
    var arr1 = new Array();
    $(arr).each(function (index, value) {
        if ($(this).prop('selected') == true) {
            arr1.push($(this).val());
        }
    });
    return arr1;

}

function checkOption(arr) {
    var i = 0;
    $(arr).each(function () {
        if ($(this).prop('selected') == true) {
            i++
        }
    });
    if (i == 0) {
        return false;
    } else {
        return true;
    }
}

function getUserById(id) {
    $.ajax({
        url: '/user/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".edit .name").attr("value", data.user.name);
            $(".edit .id").attr("value", data.user.id);
            $(".edit .lastName").attr("value", data.user.lastName);
            $(".edit .age").attr("value", data.user.age);
            $(".edit .password").attr("value", data.user.password);
            $(".edit .email").attr("value", data.user.email);
            var objSelect = $(".edit .roles");

            $.each(data.allRoles, function (key, value) {
                $(objSelect).append($("<option></option>", {value: value.name, text: value.name}));
            });


            $.each(data.user.roles, function (index, value) {
                selected($(".roles option"), value.name);
            });


        }
    });

    function selected(arr, value) {
        $(arr).each(function () {
            if ($(this).val() == value) { // EDITED THIS LINE
                $(this).attr("selected", "selected");
            }
        });
    }

}


function unicEmail(email) {
    //function which send ajax request to the server
    $.ajax({
        url: '/user/checkEmail',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            email: email
        }),
        success: function (data) {
            console.log(data);
            return data.unicEmail;
        }
    });
}

// valid data field
$('#editSubmit').click(function () {

    var arr1 = new Array;
    $('.edit .roles option').each(function () {
        if ($(this).prop('selected') == true) {
            arr1.push($(this).val());
        }
    });


    var name = $(".edit input[name='name']").val();
    var email = $(".edit input[name='email']").val();
    var lastName = $(".edit input[name='lastName']").val();
    var password = $(".edit input[name='password']").val();
    var id = $(".edit input[name='id']").val();
    var age = $(".edit input[name='age']").val();


    // if(!unicEmail(email)) {
    //     $(".error").text("Not unic");
    // }
    //
    // if(!checkOption(arr)) {
    //     $(".error").text("Choose role");
    // }

    $.ajax({
        url: '/admin/updateUser',
        datatype: 'json',
        type: 'post',
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            name: name,
            lastName: lastName,
            age: age,
            password: password,
            email: email,
            roles: arr1

        }),
        success: function (data) {
            console.log(data);
        }


    });
    userAfterSubmit['id'] = id;
    userAfterSubmit['name'] = name;
    userAfterSubmit['lastName'] = lastName;
    userAfterSubmit['age'] = age;
    userAfterSubmit['email'] = email;
    userAfterSubmit['roles'] = arr1;

    userAfterSubmit['password'] = password;

    console.log(userAfterSubmit);
    $('#exampleModalEdit').modal('hide');

    var elementStrinTableByID = $('tbody th').filter(function () {
        return $(this).text() == userAfterSubmit.id;
    }).parent();

    elementStrinTableByID.children(':eq(1)').text(userAfterSubmit.name);

    elementStrinTableByID.children(':eq(2)').text(userAfterSubmit.lastName);

    elementStrinTableByID.children(':eq(3)').text(userAfterSubmit.age);

    elementStrinTableByID.children(':eq(4)').text(userAfterSubmit.email);

    elementStrinTableByID.children(':eq(5)').find('span').remove();

    $.each(userAfterSubmit.roles, function (key, value) {
        elementStrinTableByID.children(':eq(5)').append($("<span></span>", {class: 'pr-1', text: value}));
    });


    // $('tbody th').filter(function() {
    //     return $(this).text() == userAfterSubmit.id;
    // }).parent().children(':eq(1)').text(userAfterSubmit.name);
    //
    // $('tbody th').filter(function() {
    //     return $(this).text() == userAfterSubmit.id;
    // }).parent().children(':eq(1)').text(userAfterSubmit.name);


});
// get id
$('.buttonEdit').click(function () {
    $('.roles').find('option').remove();
    var id = $(this).attr('data');
    getUserById(id);
});