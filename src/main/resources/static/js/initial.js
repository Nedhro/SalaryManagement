$(document).ready(
    function () {
        var employeeData = {
            "id": "",
            "employeeName": "Company Main Account",
            "grade": "COMPANY",
            "address": "Dhaka",
            "mobile": 8801234567890,
            "account": {
                "id": "",
                "accountName": "Company Account",
                "accountNumber": 1234567890123,
                "accountType": "CURRENT",
                "currentBalance": 0,
                "bankName": "BanK Asia",
                "branchName": "Mohammadpur"
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
    });