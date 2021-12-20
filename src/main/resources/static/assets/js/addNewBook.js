$(document).ready(function () {
    // variable to keep track of created author and category input fields
    let inputCount = 0;

    // load datepicker
    $("#datepicker").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years",
        autoclose: true // close picker once year is selected
    });

    // remove the input row with the id given by the clicked element
    $(document).on('click', '.btn_remove', function () {
        let input_id = $(this).attr("id");
        $('#additional-input-' + input_id + '').remove();
    });

    // add author input fields
    $("#addAuthor").click(function () {
        // increase author input index by 1
        inputCount++;
        // add author input fields
        $('#author-group').append(
            $('<div class="input-group mb-3 author-group" id="additional-input-' + inputCount + '">').append(
                $('<input form="new-book-form" type="text" class="form-control" name="author-firstname" placeholder="Author firstname(s)" required/>'),
                $('<input form="new-book-form" type="text" class="form-control" style="margin-left: 10px" name="author-lastname" placeholder="Author lastname" required/>'),
                $('<button class="btn btn_remove" id="' + inputCount + '">').text("Remove")
            )
        );
    });

    // add author input fields
    $("#addCategory").click(function () {
        // increase category input index by 1
        inputCount++;
        // add category input fields
        $('#category-group').append(
            $('<div class="input-group mb-3 category-group" id="additional-input-' + inputCount + '">').append(
                $('<input form="new-book-form" type="text" class="form-control" name="category-name" placeholder="Category name" required/>'),
                $('<button class="btn btn_remove" id="' + inputCount + '">').text("Remove")
            )
        );
    });

    // load author dropdown
    getAuthors(function (authors) {
        const dropdown = $(".authorSelect");
        dropdown.empty();
        $.each(authors, function (key, author) {
            dropdown.append($('<option>').text(author.authorFirstname + " " + author.authorLastname));
        })
        // from script choices.min.js
        let multipleCancelButton = new Choices('#author-selection', {removeItemButton: true});

    });

    // load category dropdown
    getCategories(function (categories) {
        const dropdown = $(".categorySelect");
        dropdown.empty();
        $.each(categories, function (key, category) {
            dropdown.append($('<option value="' + category.categoryName + '">').text(category.categoryName));
        })
        // from script choices.min.js
        let multipleCancelButton = new Choices('#category-selection', {removeItemButton: true});

    });

    $("#new-book-form").submit(function (event) {
        let object = new Object();
        object.isbn = $('input[name=isbn]').val();
        object.title = $('input[name=title]').val();
        object.description = $('textarea[name=description]').val();
        object.year = $('input[name=year]').val();
        object.authors = [];
        object.categories = [];

        // get selected authors from the dropdown and add them to the authors list
        $("#author-selection option:selected").map(function () {
            let fullname = this.value;
            // split full name into first & middle and lastname
            let fname = fullname.substring(0, fullname.lastIndexOf(" ") + 1);
            let lname = fullname.substring(fullname.lastIndexOf(" ") + 1, fullname.length);
            // add first and lastname entry to authors list
            object.authors.push({"firstname": fname, "lastname": lname})
        });
        // get author information for new author from input fields
        // save part of json string in a tempAuthor variable. we assume that first name always is saved before the lastname that is why we only add it to the object.authors array when lastname is filled
        let tempAuthorF = "";
        let tempAuthorL = "";
        $(".author-group input").map(function () {
            if (this.name === "author-firstname") {
                tempAuthorF = this.value;
            } else if (this.name === "author-lastname") {
                tempAuthorL = this.value;
                object.authors.push({"firstname": tempAuthorF, "lastname": tempAuthorL});
            }
        });

        // get selected categories from the dropdown and add them to the categories list
        $("#category-selection option:selected").map(function () {
            // add category name to categories list
            object.categories.push({"name": this.value})
        });
        // get category information for new category
        $(".category-group input").map(function () {
            object.categories.push({"name": this.value});
        });


        // make the ajax call to registerNewBook and send object
        registerNewBook(JSON.stringify(object), function (result) {
            let validationFailed = false;

            // check if validation failed ( at least one author and category need to be selected if no input)
            if (object.authors.length === 0 || object.categories.length === 0) {
                validationFailed = true;
                alert("You are missing some selections, please make sure you have selected at least one author and one category if not added otherwise");
            }
            // if validation failed then prevent default and abort submission
            if (validationFailed) {
                result.preventDefault();
                return false;
            }
            window.location.replace("/home");
            alert("You successfully saved the book to your database")
        }, function (error) {
            alert("there was an error: " + error);
        });
        event.preventDefault();
    })

});