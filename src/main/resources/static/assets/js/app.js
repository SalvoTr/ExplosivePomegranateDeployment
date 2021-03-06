// ############################# AJAX calls #####################################################

serviceEndpointURL = window.location.protocol + "//" + window.location.host;

// make a Get request to "/allBooks" and get the json with all books back
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

// get request to "/userRole" and get a boolean with true back if user is ROLE_ADMIN
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

//registers new user
function postNewUser(firstname, lastname, email, password, callbackSuccess, callbackError) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: serviceEndpointURL + "/myNewUser",
        data: JSON.stringify({
            "firstname": firstname,
            "lastname": lastname,
            "email": email,
            "password": password
        }),
        success: function(){
            callbackSuccess(true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            callbackError(jqXHR.responseJSON.message);
        }
    });
}

// save new book
function registerNewBook(newBookInfo, callback) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: serviceEndpointURL +"/newBook",
        data: newBookInfo,
        success: function () {
            callback(true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
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

// get book information with given category name
function getBookFromCategoryName(categoryName, callback){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/categoryBooks/name/"+categoryName,
        success: function (data) {
            callback(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
        }
    });
}

// get profile information
function getProfileInfo(callback){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/myUserProfile",
        success: function (data) {
            callback(data);
        }
    });
}
// update profile information
function updateUser(user, callback){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: serviceEndpointURL+"/updateMyUserProfile",
        data: user,
        success: function (data) {
            callback(data);
        }
    });
}

// get categories
function getCategories(callback){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/allCategories",
        success: function (data) {
            callback(data)
        }
    });
}

// get authors
function getAuthors(callback){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/allAuthors",
        success: function (data) {
            callback(data)
        }
    });
}

// reserve selected book
function reserveThisBook(bookId, callback) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: serviceEndpointURL+"/reserveBook/"+bookId,
        success: function (data) {
            callback(data);
        }
    });

}

function bookedByMe(bookId, callback) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/bookedByMe/"+bookId,
        success: function (data) {
            callback(data);
        }
    });
}
function addNewCommentToBook(bookId, comment, callback) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: serviceEndpointURL+"/addComment/"+bookId,
        data: comment,
        success: function (data) {
            callback(data);
        }
    });
}

function getAllComments(bookId, callback) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/allComments/"+bookId,
        success: function (data) {
            callback(data);
        }
    });
}
// borrowed books by this user
function getMyBorrows(callback) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/myBorrows",
        success: function (data) {
            callback(data);
        }
    });
}
// all borrowed books
function getAllBorrows(callback) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: serviceEndpointURL+"/allBorrowed/",
        success: function (data) {
            callback(data);
        }
    });
}

// return book to library
function returnBook(bookId, callback) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: serviceEndpointURL+"/returnBook/"+bookId,
        success: function (data) {
            callback(data);
        }
    });
}