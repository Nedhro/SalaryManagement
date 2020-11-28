$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/employee',
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (result) {
            for (var i = 0; i < result.content.length; i++) {
                if (result.content[i].grade == "COMPANY") {
                    var companyInfo = result.content[i];
                    renderDetails(companyInfo);
                }
            }

        },
        error: function (jqXHR) {
            console.log(jqXHR);
        }
    });
});

function renderDetails(info) {
    console.log(info);
    var id = document.getElementById("accountId");
    id.value = info.account.id;
    var field1 = document.getElementById("accountName");
    field1.innerHTML = info.account.accountName;
    var field2 = document.getElementById("rank");
    field2.innerHTML = info.grade;
    var field3 = document.getElementById("accNum");
    field3.innerHTML = info.account.accountNumber;
    var field4 = document.getElementById("accType");
    field4.innerHTML = info.account.accountType;
    var field5 = document.getElementById("bank");
    field5.innerHTML = info.account.bankName;
    var field6 = document.getElementById("branch");
    field6.innerHTML = info.account.branchName;
    var field6 = document.getElementById("currentBalance");
    field6.value = info.account.currentBalance;
}

function renderData() {
    var balance = $("#currentBalance").val();
    var accountId = $("#accountId").val();
    console.log(accountId);
    console.log(parseFloat(balance));
    accountData = {
        "currentBalance": parseFloat(balance)
    };
    $.ajax({
        type: 'PUT',
        url: '/employee/account/' + accountId,
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