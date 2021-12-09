$(document).ready(function () {
    $(loadData());

    function loadData() {
        // call the function getAllBooks defined in app.js and get the json list of books back
        // take the result and form a div book element for each list item and then display those newly created html items on the page
        getAllBooks( function (result) {
            mapBookList("allBooks", result);
        })

        // TODO call function to load filter data into some mutliclickable dropdown
    }

})

