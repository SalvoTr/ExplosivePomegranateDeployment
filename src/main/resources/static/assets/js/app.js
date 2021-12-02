serviceEndpointURL = window.location.protocol + "//" + window.location.host;

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