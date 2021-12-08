$(document).ready(function () {
    //getUrlParameter function defined in app.js
    const bookId = getUrlParameter();
    $(loadData());
    function loadData() {
        // call the function getBookInfo defined in app.js and get the json back
        getBookInfo( bookId, function (result) {
            // delete content of the dom element with id
            $("#bookInformation").empty();
            //build html
            $("#bookInformation").append(
                $('<div class="row bookInfoBanner">').append(
                    $(' <div class="col-sm-4">').append(
                        $('<img class="bookInfoImage" src="../assets/img/sample-book-cover.png">')
                    ),
                    $('<div class="col-sm-8">').append(
                        $('<h1 class="bookInfoTitle">').text(result.title),
                        $(result.currentlyBorrowed ? $('<p class="BorrowedStyle">').text("Currently borrowed") : $('<p class="notBorrowedStyle">').text("Available")),
                        $('<p>').text("ISBN:"+result.isbn),
                        $('<p>').text("Published:"+result.year)
                        // TODO author and Categories are missing
                    )
                ),
                $('<div class="bookInfoDescription">').append(
                    $('<p>').text(result.description)
                )
            );
        })
    }

})