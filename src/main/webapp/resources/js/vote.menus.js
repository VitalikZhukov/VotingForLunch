const menuAjaxUrl = "profile/menus/";

const ctx = {
    ajaxUrl: menuAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: menuAjaxUrl
        }).done(updateTableByData);
    }
};

$(function () {
    makeEditable({
            "columns": [
/*                {
                    "data": "number"
                },*/
                {
                    "data": "dish"
                },
                {
                    "data": "price"
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
    });

    $.datetimepicker.setLocale(localeCode);

});

/*$(function(){
    $('table td:first-child').each(function (i) {
        $(this).html(i+1);
    });
});*/
