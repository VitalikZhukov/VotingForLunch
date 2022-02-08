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
    let name = restaurant[1];
    document.getElementById("restaurantName").innerHTML = name;
    $.ajax({
        url: restaurantAjaxUrl + id,
        type: "POST"
    }).done(function () {
        successNoty("common.voting" + name);
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