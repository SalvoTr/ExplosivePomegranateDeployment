$(document).ready(function () {
    $(loadData());

    function loadData() {
        console.log("test")
        getMyBorrows(function (result) {
            mapBorrowList("myBorrows", result);
        })
    }
})
