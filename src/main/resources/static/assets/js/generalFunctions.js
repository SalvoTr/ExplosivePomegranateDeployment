// ############################# methods used in multiple files #####################################################
$(document).ready(function() {
    isUserAdmin(function (isAdmin){
        // if user is no admin = ROLE_ADMIN = true, hide a few elements on the page
        if(!isAdmin) {
            //navigation of currently borrowed
            $("#currentlyBorrowed").hide();
            // button to add new book
            $("#addNewBook").hide();
            // button to edit book
            $("#editBook").hide();
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

// take the result and form a div book element for each list item and then display those newly created html items on the page
function mapBookList(placeholderID, booklist) {
    // delete content of the dom element with id
    $('#'+placeholderID).empty();
    // go through result list
    $.each(booklist, function (i, book) {
        //add new item into div with id allBooks
        $("#allBooks")
            .append(
                $('<div class="row rowList">').append(
                    $('<a href="/bookDetails/'+book.book_id+'">'),
                    $('<div class="col-sm-2">').append(
                        $('<img src="../assets/img/sample-book-cover.png">')
                    ),
                    $('<div class="col-sm-9" id="bookListItem">').append(
                        $(book.currentlyBorrowed ? $('<p class="BorrowedStyle">').text("Currently borrowed") : $('<p class="notBorrowedStyle">').text("Available")),
                        $("<h3>").text(book.title),
                        $('<p>').text("Author(s): " + listAuthors(book.authors)),
                        $('<p>').text("Category: " + listCategories(book.categories)),
                        // if text description lenght is over 200 replace the remaining text with '...'
                        $(book.description.length > 200 ? $("<p>").text(book.description.substring(0,200)+"...") : $("<p>").text(book.description) )
                    )
                ),
                $('<div class="placeholder-empty">')
            );

    })
}

function mapBorrowList(placeholderId, borrowList) {
    // delete content of the dom element with id
    $('#'+placeholderId).empty();
    // go through result list
    $.each(borrowList, function (i, borrow) {
        //add new item into div with id allBooks
        $('#'+placeholderId)
            .append(
                $('<div class="row rowList">').append(
                    $('<a href="/bookDetails/'+borrow.book.book_id+'">'),
                    $('<div class="col-sm-2">').append(
                        $('<img src="../assets/img/sample-book-cover.png">')
                    ),
                    $('<div class="col-sm-9" id="bookListItem">').append(
                        $(borrow.book.currentlyBorrowed ? $('<p class="BorrowedStyle">').text("Currently borrowed") : $('<p class="notBorrowedStyle">').text("Available")),
                        $(borrow.book.currentlyBorrowed ? $('<p>').text("This book needs to be returned at: "+ borrow.initEndDate):  $('<p>').text("Was returned at: "+borrow.initEndDate)),
                        $("<h3>").text(borrow.book.title),
                        $('<p>').text("Author(s): " + listAuthors(borrow.book.authors)),
                        $('<p>').text("Category: " + listCategories(borrow.book.categories)),
                        // if text description lenght is over 200 replace the remaining text with '...'
                        $(borrow.book.description.length > 200 ? $("<p>").text(borrow.book.description.substring(0,200)+"...") : $("<p>").text(borrow.book.description) )
                    )
                ),
                $('<div class="placeholder-empty">')
            );

    })
}

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

function myBorrows(borrowed){
    let borrowedarray = [];
    $.each(borrowed, function(key, borrowed){
        let bborrowed = borrowed;
        borrowedarray.push(bborrowed);
    });
    return borrowedarray.join(" , ");
}