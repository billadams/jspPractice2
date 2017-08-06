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

<div class="container">
    <h1>Employee List</h1>
    
    <c:if test="${messages != null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <!--<span>Error:</span>-->
            <c:forEach var="message" items="${messages}">
                <ul>
                    <li>${message}</li>
                </ul>
            </c:forEach>
        </div>
    </c:if>  
    
    <div class="row results">
        <div class="col-md-12">
            <table class="table table-bordered table-striped">

                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Employee ID</th>
                        <th>Date of Birth</th>
                        <th>Hire Date</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="employee" items="${employeeList}" varStatus="status">
                        <tr>
                            <td><c:out value="${employee.firstName}" /></td>
                            <td><c:out value="${employee.middleName}" /></td>
                            <td><c:out value="${employee.lastName}" /></td>
                            <td><c:out value="${employee.employeeID}" /></td>
                            <td><c:out value="${employee.birthDate}" /></td>
                            <td><c:out value="${employee.hireDate}" /></td>
                            <td>
                                <form action="" method="post">
                                    <input type="hidden" name="action" value="editEmployee">
                                    <input type="hidden" name="arrayIndex" 
                                           value="<c:out value='${status.index}'/>">
                                    <input class="btn btn-primary btn-block" type="submit" value="Edit">
                                </form>
                            </td>
                            <td>
                                <form action="" method="post">
                                    <input type="hidden" name="action" value="removeEmployee">
                                    <input type="hidden" name="arrayIndex" 
                                           value="<c:out value='${status.index}'/>">
                                    <input class="btn btn-danger btn-block" type="submit" value="Delete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>     
                </tbody>
            </table>
            <%--<c:if test="${isNewSession == true}">--%>
                <form action="" method="post">
                    <input type="hidden" name="action" value="destroySession">
                    <input class="btn btn-warning" type="submit" value="Destroy Session">
                </form>     
            <%--</c:if>--%>

        </div> <!-- end col-md-12 -->
    </div> <!-- end row results -->

    <div class="col-md-6">
        <h2>Add new employee</h2>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="addEmployee">
            <input type="hidden" name="arrayIndex" value="<c:out value='${employeeIndex}' />">
            <div class="form-group row">
                <label for="first-name" class="col-md-3 col-form-label">First name:</label>
                <div class="col-md-9">
                    <input type="text" name="firstName" class="form-control" id="first-name" value="${firstName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="middle-name" class="col-md-3 col-form-label">Middle name:</label>
                <div class="col-md-9">
                    <input type="text" name="middleName" class="form-control" id="middle-name" value="${middleName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="last-name" class="col-md-3 col-form-label">Last name:</label>
                <div class="col-md-9">
                    <input type="text" name="lastName" class="form-control" id="last-name" value="${lastName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="employee-id" class="col-md-3 col-form-label">Employee ID:</label>
                <div class="col-md-9">
                    <input type="text" name="employeeID" class="form-control" id="employee-id" value="${employeeID}">
                </div>
            </div>
            <div class="form-group row">
                <label for="date-of-birth" class="col-md-3 col-form-label">Date of Birth:</label>
                <div class="col-md-9">
                    <input type="date" name="birthDate" class="form-control" id="date-of-birth" value="${birthDateInputString}">
                </div>
            </div>             
            <div class="form-group row">
                <label for="hire-date" class="col-md-3 col-form-label">Hire Date:</label>
                <div class="col-md-9">
                    <input type="date" name="hireDate" class="form-control" id="last-name" value="${hireDateInputString}">
                </div>
            </div>
            <div class="form-group row">
                <div>
                    <c:choose>
                        <c:when test="${isUpdate == false}">
                            <input class="btn btn-primary" type="submit" value="Add Employee">
                        </c:when>
                        <c:otherwise>
                            <input class="btn btn-primary" type="submit" value="Update Employee">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </div> <!-- end col-md-6 -->

</div> <!-- end container -->
    
</body>
</html>