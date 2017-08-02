<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Practice 2</title>
    <link rel="stylesheet" href="styles/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

    <div class="row results">
        <div class="col-md-8">
            <table class="table table-bordered table-striped">

                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Employee ID</th>
                        <th>Date of Birth</th>
                        <th>Hire Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${arrList}" varStatus="status">
                        <tr>
                            <th>${item.firstName}</th>
                            <th>${item.middleName}</th>
                            <th>${item.lastName}</th>
                            <th>${item.employeeID}</th>
                            <th>${item.birthDate}</th>
                            <th>${item.hireDate}</th>
                        </tr>
                    </c:forEach>     
                </tbody>
            </table>
        </div>
    </div>


</body>
</html>