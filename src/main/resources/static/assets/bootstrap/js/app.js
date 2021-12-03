function postNewUser(firstname, lastname, email, password, callbackSuccess, callbackError) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        headers: {
            "X-XSRF-TOKEN": getCookie("XSRF-TOKEN") // TODO I HAVE NO CLUE ABOUT THIS
        },
        url: serviceEndpointURL + "/register",
        data: JSON.stringify({
            "firstname": firstname,
            "lastname": lastname,
            "email": email,
            "password": password
        }),
        success: function () {
            callbackSuccess(true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            callbackError(jqXHR.responseJSON.message);
        }
    });
}