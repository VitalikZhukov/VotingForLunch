const menuAjaxUrl = "profile/menus/";

const ctx = {
    ajaxUrl: menuAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: menuAjaxUrl
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
                    "data": "number"
                },
                {
                    "data": "dish"
                },
                {
                    "data": "price"
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

$(function(){
    $('table td:first-child').each(function (i) {
        $(this).html(i+1);
    });
});
