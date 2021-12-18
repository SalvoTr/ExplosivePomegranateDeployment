$(document).ready(function () {
    $(loadData());

    function loadData() {

        getAllBorrows(function (result) {
            mapBorrowList("allBorrows", result);
        })
    }
})
