$(document).ready(
    function () {
        $.ajax({
            type: "GET",
            url: "/employee",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                var employees = response.content;
                console.log(employees);
                var employeeTable = $('#employee');
                employeeTable.find("tbody tr").remove();
                for (var j = 0; j < employees.length; j++) {
                    var noOfEmp = document.getElementById("noOfEmp");
                    noOfEmp.innerHTML = employees.length-1;
                    employeeTable.append("<tr><td>" + j + "</td><td>"
                        + employees[j].employeeName + "</td><td>"
                        + employees[j].grade + "</td><td>"
                        + employees[j].address + "</td><td>"
                        + employees[j].mobile + "</td><td>"
                        + employees[j].account.accountNumber + "</td>");
                } //console.log("success response");
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    });
function renderSalaryData() {
    $.ajax({
        type: "GET",
        url: "/salary",
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (response) {
            var salary = response;
            console.log(salary);
            //console.log("success response");
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}
renderSalaryData();