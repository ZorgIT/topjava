<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<c:set var="colorRd" value="#FF0000"/>
<c:set var="colorGrn" value="#026440"/>

<table border="1">
    <tr align="center">
        <th width="250px">Date</th>
        <th width="250px">Description</th>
        <th width="250px">Calories</th>
<%--        <th width="250px" align="center">БН update</th>--%>
<%--        <th width="250px" align="center">БН delete</th>--%>
    </tr>
    <c:forEach items="${mealList}" var="mealq">
        <c:set var="isExceed" value="${colorGrn}"/>
        <c:if test="${mealq.isExcess()}">
            <c:set var="isExceed" value="${colorRd}"/>
        </c:if>
        <tr align="left">
            <td><font color=${isExceed}>${mealq.getDateTimeFormatted()}</font></td>
            <td><font color=${isExceed}>${mealq.getDescription()}</font></td>
            <td><font color=${isExceed}>${mealq.getCalories()}</font></td>
<%--            <td align="center"><a href="update">Update</a> </td>--%>
<%--            <td align="center"><a href="delete">Delete</a> </td>--%>
        </tr>
    </c:forEach>
</table>

</body>
</html>