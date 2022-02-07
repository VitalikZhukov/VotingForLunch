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

function voting() {
    var id = $('input[name="vote"]:checked').val();
    $.ajax({
        url: restaurantAjaxUrl + id,
        type: "POST",
        data: "restaurantId=" + id
    }).done();
}

$(function () {
    makeEditable( {
            "columns": [
                {
                    "data": "vote",
                    "render": function (data, type, row) {
                        return "<input type='radio' name='vote' value='" + row.id + "'/>";
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
        });

    $.datetimepicker.setLocale(localeCode);

});