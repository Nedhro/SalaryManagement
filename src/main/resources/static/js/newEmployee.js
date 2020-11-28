function postData() {
    var employeeName = $("#employeeName").val();
    var grade = $("#grade").val();
    var address = $("#address").val();
    var mobile = $("#mobile").val();
    var accountName = $("#accountName").val();
    var accountNumber = $("#accountNumber").val();
    var accountType = $("#accountType").val();
    var currentBalance = $("#currentBalance").val();
    var bankName = $("#bankName").val();
    var branchName = $("#branchName").val();
    var employeeData = {
        "employeeName": employeeName,
        "grade": grade,
        "address": address,
        "mobile": mobile,
        "account": {
            "accountName": accountName,
            "accountNumber": accountNumber,
            "accountType": accountType,
            "currentBalance": currentBalance,
            "bankName": bankName,
            "branchName": branchName
        }
    };
    $.ajax({
        type: 'POST',
        url: '/employee',
        data: JSON.stringify(employeeData),
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