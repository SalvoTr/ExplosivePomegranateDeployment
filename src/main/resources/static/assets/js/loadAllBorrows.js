$(document).ready(function () {
    $(loadData());

    function loadData() {

        getAllBorrows(function (result) {
            mapBookList("allBooks", result);
        })
    }
})
