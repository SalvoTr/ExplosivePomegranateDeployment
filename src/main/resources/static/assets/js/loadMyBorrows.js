$(document).ready(function () {
    $(loadData());

    function loadData() {
        getMyBorrows(function (result) {
            mapBorrowList("myBorrows", result);
        })
    }
})
