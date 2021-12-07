$(document).ready(function () {
    $(loadData());

    function loadData() {
        // call the function getAllBooks defined in app.js and get the json list of books back
        // take the result and form a div book element for each list item and then display those newly created html items on the page
        getAllBooks( function (result) {
           // delete content of the dom element with id
           $("#allBooks").empty();
            // go through result list
            $.each(result, function (i, book) {
                //add new item into div with id allBooks
                $("#allBooks")
                    .append(
                        $('<a href="/bookDetails/'+book.book_id+'"><div class="row">').append(
                            $('<div class="col-sm-2">').append(
                                $('<img src="../assets/img/sample-book-cover.png">')
                            ),
                            $('<div class="col-sm-9">').append(
                                $(book.currentlyBorrowed ? $('<p class="BorrowedStyle">').text("Currently borrowed") : $('<p class="notBorrowedStyle">').text("Available")),
                                $("<h3>").text(book.title),
                                $("<p>").text(book.description)
                            ),
                            $('<div class="placeholder-empty">')
                        )
                    );

            })
        })
    }

})

