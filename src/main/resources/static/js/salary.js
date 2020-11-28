$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/salary',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (result) {
            var salaryTable = $('#salaryInfo');
            salaryTable.find("tbody tr").remove();
            for (var i = 0; i < result.content.length; i++) {
                if (result.content[i].grade != "COMPANY") {
                    var salaryInfo = result.content[i];
                    console.log(salaryInfo);
                    salaryTable.append("<tr><td>" + (i+1) + "</td><td>"
                        + salaryInfo.grade + "</td><td>"
                        + salaryInfo.basic + "</td><td>"
                        + salaryInfo.total + "</td><td>");
                }
            }

        },
        error: function (jqXHR) {
            console.log(jqXHR);
        }
    });
});

function renderData() {
    var balance = $("#basic").val();
    console.log(parseFloat(balance));
    accountData = {
        "grade": "SIX",
        "basic": parseFloat(balance)
    };
    $.ajax({
        type: 'POST',
        url: '/salary',
        data: JSON.stringify(accountData),
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (result) {
            console.log(result);
        },
        error: function (jqXHR) {
            console.log(jqXHR);
        }
    });
}