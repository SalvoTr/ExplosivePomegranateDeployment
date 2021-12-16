$(document).ready(function () {
    //getUrlParameter function defined in app.js
    const bookId = getUrlParameter();

    function listAuthors(authors){
        let authorarray = [];
        $.each(authors, function(key, author){
            let aauthor = author.authorFirstname+" "+author.authorLastname;
            authorarray.push(aauthor);
        });
        return authorarray.join(" , ");
    }

    function listCategories(categories){
        let categoryarray = [];
        $.each(categories, function(key, categories){
            let ccategory = categories.categoryName;
            categoryarray.push(ccategory);
        });
        return categoryarray.join(" , ");
    }

    function reserveBook (book_id){
        console.log("test");
        reserveThisBook(book_id, function (result){
            alert("This book was reserved successfully");
        })
    }

    $(document).on('click', '#borrowBookBtn', function() {
        let bookId = $(this).attr("name");
        console.log(bookId);
        reserveThisBook(bookId, function (result){
            alert("This book was reserved successfully");
        })
    });

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
                        $('<p>').text("Author(s): " + listAuthors(result.authors)),
                        $('<p>').text("Category: " + listCategories(result.categories)),
                        $('<p>').text("ISBN: "+result.isbn),
                        $('<p>').text("Published: "+result.year),
                        // TODO author and Categories are missing
                        $('<div>').append(
                            // TODO create ajax funxtion that listens on reserveBook(book_id)
                            // TODO on successful reservation, show message that book was reserved successfully and update availability status on page
                            $('<button id="borrowBookBtn" name="'+result.book_id+'" class="btn btn-primary col-sm-3" type="button">' ).text("Borrow this book")
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