$(document).ready(function () {
    $(loadData());

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

    function loadData() {
        // call the function getAllBooks defined in app.js and get the json list of books back
        // take the result and form a div book element for each list item and then display those newly created html items on the page
        getAllBooks( function (result) {
            mapBookList("allBooks", result);
        })

        getMyBorrows(function (result){
            listMyBorrows("myBorrows" , result)
        })
        // TODO call function to load filter data into some mutliclickable dropdown
    }

})

