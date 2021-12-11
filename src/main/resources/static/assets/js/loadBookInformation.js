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
                        // if book is currently borrowed show text "currently borrowed" if not then show it is available
                        $(result.currentlyBorrowed ? $('<p class="BorrowedStyle">').text("Currently borrowed") : $('<p class="notBorrowedStyle">').text("Available")),
                        $('<p>').text("Author: " +
                            alert(JSON.stringify(authors),)
                            $.each(result.authors,
                            function (key, author) {
                                let authorname = author.authorFirstname+" "+author.authorLastname;
                                console.log( authorname);
                                return authorname;
                            }
                        ).join(" , ")
                        ),
                        $('<p>').text("ISBN: "+result.isbn),
                        $('<p>').text("Published: "+result.year),
                        // TODO author and Categories are missing
                        $('<div>').append(
                            // TODO create ajax funxtion that listens on reserveBook(book_id)
                            // TODO on successful reservation, show message that book was reserved successfully and update availability status on page
                            $('<button onclick="reserveBook('+result.book_id+')" id="borrowBookBtn" class="btn btn-primary col-sm-3" type="button">').text("Borrow this book")
                        )
                    )
                ),
                $('<div class="bookInfoDescription">').append(
                    $('<p>').text(result.description)
                ),
                // TODO get the previous comments of the book and print them in a list/table
                // TODO make a for each (see example in loadBooks.js and print each entry
                // TODO design the comments in a way they are fitting onto the page
                $('<div class="bookComment">')
                // TODO create method who checks if you are the one currently borrowing the book, if you are the one currently borrowing the book add a button with the option to add a new comment
            );
        })
    }

})