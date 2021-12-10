$(document).ready(function() {
    // variable to keep track of created author and category input fields
    let inputCount = 0;

    // load datepicker
    $("#datepicker").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years",
        autoclose:true // close picker once year is selected
    });

    // remove the input row with the id given by the clicked element
    $(document).on('click', '.btn_remove', function() {
        let input_id = $(this).attr("id");
        $('#additional-input-'+input_id+'').remove();
    });

    // add author input fields
    $( "#addAuthor" ).click(function() {
        // increase author input index by 1
        inputCount++;
        // add author input fields
        $('#author-group').append(
            $('<div class="input-group mb-3" id="additional-input-'+inputCount+'">').append(
                $('<input form="new-book-form" type="text" class="form-control" name="author-firstname" placeholder="Author firstname(s)"/>'),
                $('<input form="new-book-form" type="text" class="form-control" style="margin-left: 10px" name="author-lastname" placeholder="Author lastname"/>'),
                $('<button class="btn btn_remove" id="'+inputCount+'">').text("Remove")
            )
        );
    });

    // add author input fields
    $( "#addCategory" ).click(function() {
        // increase category input index by 1
        inputCount++;
        // add category input fields
        $('#category-group').append(
            $('<div class="input-group mb-3" id="additional-input-'+inputCount+'">').append(
                $('<input form="new-book-form" type="text" class="form-control" name="category-name" placeholder="Category name"/>'),
                $('<button class="btn btn_remove" id="'+inputCount+'">').text("Remove")
            )
        );
    });

    // load author dropdown
    getAuthors(function (authors){
        const dropdown = $(".authorSelect");
        dropdown.empty();
        $.each(authors, function (key, author) {
            dropdown.append($('<option>').text(author.authorFirstname+" "+ author.authorLastname));
        })
        // from script choices.min.js
        let multipleCancelButton = new Choices('#author-selection', {removeItemButton: true});

    });

    // load category dropdown
    getCategories( function (categories) {
        const dropdown = $(".categorySelect");
        dropdown.empty();
        $.each(categories, function (key, category) {
            dropdown.append($('<option value="'+category.categoryName+'">').text(category.categoryName));
        })
        // from script choices.min.js
        let multipleCancelButton = new Choices('#category-selection', {removeItemButton: true});

    });



    $("#new-book-form").submit(function (event) {
        let object = new Object();
        object.isbn =  $('input[name=isbn]').val();
        object.title =  $('input[name=title]').val();
        object.description = $('textarea[name=description]').val();
        object.year = $('input[name=year]').val();

        // get selected authors from the dropdown
        let fullnames = $("#author-selection option:selected").map(function(){
            return this.value.split(" ") }).get()


        //object.category = [$("option:selected").map(function(){ return this.value }).get().join(", ")];
        /*registerNewBook(JSON.stringify(object),  function (result) {
            // TODO add confirmation modal that new book was successfully saved before going back home
            window.location.replace("/home");
        }, function (error){
            $("#error-modal").modal('show');
            $("#error-text").text(error);
        });
        event.preventDefault();*/
    })

});