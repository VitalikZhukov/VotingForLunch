const restaurantAjaxUrl = "profile/restaurants/";

const ctx = {
    ajaxUrl: restaurantAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: restaurantAjaxUrl
        }).done(updateTableByData);
    }
};

$(function () {
    makeEditable( {
            "columns": [
                {
                    "data": "vote",
                    "render": function () {
                        return "<input type='radio' name='vote'/>";
                    }
                },
                {
                    "data": "name"
                },
                {
                    "data": "voteCounter"
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
        "createdRow": function (row, data, dataIndex) {
            $(row).attr("data-meal-excess", data.excess);
        }
        });

    $.datetimepicker.setLocale(localeCode);

});