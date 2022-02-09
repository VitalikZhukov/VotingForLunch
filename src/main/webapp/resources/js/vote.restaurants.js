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
    const value = $('input[name="vote"]:checked').val();
    const restaurant = value.split(" !! ");
    let id = restaurant[0];
    document.getElementById("restaurantName").innerHTML = restaurant[1];

    $.ajax({
        url: restaurantAjaxUrl + id,
        type: "POST"
    }).done(function () {
        ctx.updateTable();
        successNoty("common.voting");
    });
}

$(function () {
    makeEditable( {
            "columns": [
                {
                    "data": "vote",
                    "render": function (data, type, row) {
                        return "<input type='radio' name='vote' value='" + row.id + " !! " + row.name + "'/>";
                    }
                },
                {
                    "data": "name",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return "<a href='menus?restaurantId=" + row.id + "'>" + data + "</a>";
                        }
                        return data;
                    }
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