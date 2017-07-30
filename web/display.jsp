<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Practice 2</title>
</head>
<body>

<table>

    <h3>right here have table headers</h3>
    <c:forEach var="item" items="${arrList}" varStatus="status">
        
        ${status.index}
        
        ${item.lastName}

        ${item.birthDate}

    </c:forEach>  
</table>


</body>
</html>