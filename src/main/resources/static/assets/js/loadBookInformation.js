$(document).ready(function () {
    //getUrlParameter function defined in app.js
    const bookId = getUrlParameter();

    /**
     * @author Clelia
     * get all comment previously made on the book
     * */
    getAllComments(bookId, function (commentList) {
        $('.bookComment').empty();
        if (commentList.length > 0) {
            $('.bookComment').append(
                $('<h2>').text('Comments about book health'),
                $('<table class="table">').append(
                    $('<thead>').append(
                        $('<tr>').append(
                            $('<th>').text("Start Borrowing Date"),
                            $('<th>').text("Comment")
                        )
                    ),
                    $('<tbody class="commenList">')
                )
            );
            $.each(commentList, function (i, comment) {
                $('.commenList').append(
                    $('<tr>').append(
                        $('<td>').text(comment.startDate),
                        $('<td>').text(comment.bookComment)
                    )
                );
            })
        }

    })

    /**
     * @author Clelia
     * get all Authors listed in one line
     * */
    function listAuthors(authors) {
        let authorarray = [];
        $.each(authors, function (key, author) {
            let aauthor = author.authorFirstname + " " + author.authorLastname;
            authorarray.push(aauthor);
        });
        return authorarray.join(" , ");
    }

    /**
     * @author Clelia
     * get all Categories listed in one line
     * */
    function listCategories(categories) {
        let categoryarray = [];
        $.each(categories, function (key, categories) {
            let ccategory = categories.categoryName;
            categoryarray.push(ccategory);
        });
        return categoryarray.join(" , ");
    }

    $(document).on('click', '#borrowBookBtn', function () {
        reserveThisBook(bookId, function (result) {
            alert("This book was reserved successfully");
        })
    });

    $(document).on('click', '#addCommentButton', function () {
        $('#input-new-comment').append(
            $('<input type="text" class="form-control" name="comment" placeholder="Category name" required/>'),
            $('<button class="btn btn_save saveNewComment">').text("Save")
        )
    });

    $(document).on('click', '.saveNewComment', function () {
        let comment = $('input[name=comment]').val();
        addNewCommentToBook(bookId, comment, function () {
            alert("The comment was successfully added to the book, to view please reload the page");
        });
    });

    /**
     * @author Clelia
     * Check button option, you only can add a comment
     * when you are the one that has the book and you
     * cannot reserve a book that is already borrowed to someone
     * */
    function buttonOption(result) {
        let myBooking = false;
        bookedByMe(result.book_id, function (data) {
            myBooking = data;
            // if currentlybooked = false
            if (!result.currentlyBorrowed) {
                $('#borrowBookBtn').prop("disabled", false);
                $('#addCommentButton').hide();
            }// if booked by me, have the option to add a comment to a book
            else if (myBooking) {
                $('#addCommentButton').attr('name', result.book_id).show();
                $('#borrowBookBtn').prop("disabled", true);
            } // else show button to borrow disabled and have the message with it when it will be back available
            else {
                $('#borrowBookBtn').prop("disabled", true);
            }
        });
    }

    $(loadData());

    function loadData() {
        // call the function getBookInfo defined in app.js and get the json back
        getBookInfo(bookId, function (result) {
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
                        $('<p>').text("ISBN: " + result.isbn),
                        $('<p>').text("Published: " + result.year),
                        $('<div>').append(
                            $('<button id="borrowBookBtn" name="' + result.book_id + '" class="btn btn-primary col-sm-3" type="button">').text("Borrow this book"),
                            $('<button id="addCommentButton" name="' + result.book_id + '" class="btn btn-primary col-sm-3" type="button">').text("Add a comment about the book status"),
                            buttonOption(result)
                        )
                    )
                ),
                $('<div class="bookInfoDescription">').append(
                    $('<p>').text(result.description)
                ),

                $('<div class="input-group mb-3" id="input-new-comment">'),
                $('<div class="bookComment">')
            );
        })
    }
})