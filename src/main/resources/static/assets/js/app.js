serviceEndpointURL = window.location.protocol + "//" + window.location.host;

$(document).ready(function() {
    isUserAdmin(function (isAdmin){
        // if user is no admin = ROLE_ADMIN = true, hide a few elements on the page
        if(!isAdmin) {
            //navigation of currently borrowed
            $("#currentlyBorrowed").hide();
            // button to add new book
            $("#addNewBook").hide();
        }
    })
});

//return the last part of the url to get an id
function getUrlParameter(){
    // first take the url
    // split it on each /
    // then use the last item that was left
    // for example "https://localhos:8080/bookDetails/15" returns 15
    return $(location).attr("href").split('/').pop();
}

// make a Get reguest to "/allBooks" and get the json with all books back
function getAllBooks(callback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: serviceEndpointURL + "/allBooks",
        success: function (data) {
            callback(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
        }
    });
}

// get reguest to "/userRole" and get a boolean with true back if user is ROLE_ADMIN
function isUserAdmin(callback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: serviceEndpointURL + "/userRole",
        success: function (data) {
            callback(data);
        }
    });
}

/**
 * @author Salvatore
 * for registering new user
 * */
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

// get bookInformation
function getBookInfo(bookId, callback){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/bookInfo/"+bookId,
        success: function (data) {
            callback(data);
        }
    });
}