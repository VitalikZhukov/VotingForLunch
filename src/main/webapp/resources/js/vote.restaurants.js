const restaurantAjaxUrl = "profile/restaurants/";

const ctx = {
    ajaxUrl: restaurantAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: restaurantAjaxUrl
        }).done(updateTableByData);
    }
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                },
                {
                    "data": "vote"
                },
                {
                    "data": "name"
                },
                {
                    "data": "voteCounter"
/*                },
                {
                    "data": "dish"
                },
                {
                    "data": "price"*/
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