const restaurantAjaxUrl = "/profile/restaurants";

const ctx = {
    ajaxUrl: restaurantAjaxUrl,
    updateTable: function () {
        $.get(restaurantAjaxUrl, updateTableByData);
    }
}

(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "dish1"
                },
                {
                    "data": "price1"
                },
                {
                    "data": "dish2"
                },
                {
                    "data": "price2"
                },
                {
                    "data": "dish3"
                },
                {
                    "data": "price3"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});