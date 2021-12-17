$(document).ready(function () {

    $("#searchFor").keyup(function() {

        // Retrieve the input field text and reset the count to zero
        var filter = $(this).val(),
            count = 0;

        // Loop through the comment list
        $('#allBooks #bookListItem').each(function() {
            // If the list item does not contain the text phrase fade it out
            if ($(this).text().search(new RegExp(filter, "i")) < 0) {
                // hide not only the text item but the parent div as well
                    $(this).parent().hide();  // MY CHANGE
                    // also hide placeholder after element
                    $(this).parent().next().hide();
                // Show the list item if the phrase matches and increase the count by 1
            } else {
                $(this).show(); // MY CHANGE
                count++;
            }
        });
    });

    // by clicking the search button, all books with the given category are returned
    $("#search-form").submit(function (event) {
        // https://stackoverflow.com/questions/27759380/how-to-stop-refreshing-page-after-ajax-call
        event.preventDefault();
        event.stopPropagation();
        let catName = '';
        $("#category-selection-search option:selected").map(function(){
            catName = this.value;
        })
        getBookFromCategoryName(catName, function (result) {
            mapBookList("allBooks", result);
        })

    });
/*
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();
        e.stopPropagation(); // only neccessary if something above is listening to the (default-)event too

        [YOUR CODE GOES HERE]
    });*/


    $(loadData());
    function loadData() {
        // call the function getAllBooks defined in app.js and get the json list of books back
        // take the result and form a div book element for each list item and then display those newly created html items on the page
        getAllBooks( function (result) {
            mapBookList("allBooks", result);
        })

        // load category dropdown
        getCategories( function (categories) {
            const dropdown = $(".categorySelectSearch");
            dropdown.empty();
            $.each(categories, function (key, category) {
                dropdown.append($('<option value="'+category.categoryName+'">').text(category.categoryName));
            })
            // from script choices.min.js
            let multipleCancelButton = new Choices('#category-selection-search', {maxItemCount: 1});
        });
    }
});

