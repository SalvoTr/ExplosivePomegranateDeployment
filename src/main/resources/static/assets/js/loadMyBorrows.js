$(document).ready(function () {
    $(loadData());

    function loadData() {
        console.log("test")
        getMyBorrows(function (result) {
            mapBookList("allBooks", result);
        })
    }
})
