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
                $.ajax({
                    type: "GET",
                    url: "/salary",
                    dataType: 'json',
                    success: function (response) {
                        var salary = response.content;
                        for (var j = 0; j < employees.length; j++) {
                            var noOfEmp = document.getElementById("noOfEmp");
                            noOfEmp.innerHTML = employees.length - 1;
                            var totalSalary;
                            for (let x in salary) {
                                if (salary[x].grade === employees[j].grade) {
                                    totalSalary = salary[x].total;
                                    var newArr = [];
                                    employeeTable.append("<tr><td>" + j + "</td><td>"
                                        + employees[j].employeeName + "</td><td>"
                                        + employees[j].grade + "</td><td>"
                                        + "<span id='salaryValue'>" + totalSalary + "</span>" + "</td>");
                                    salaryData = {
                                        "accountId": employees[j].account.id,
                                        "currentBalance": totalSalary
                                    }
                                    var accountId = employees[j].account.id;
                                    newArr.push(salaryData);
                                    console.log(newArr);
                                }
                            }
                        }
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                    }
                });
                //console.log("success response");
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    });

function disburseSalary() {
    $.ajax({
        type: 'PUT',
        url: '/employee/disburse/1',
        data: JSON.stringify(salaryData),
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