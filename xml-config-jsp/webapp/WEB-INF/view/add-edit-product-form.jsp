<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:choose>
        <c:when test="${product.id == null}">
            <title>Add new Product</title>
        </c:when>
        <c:otherwise>
            <title>Edit Product</title>
        </c:otherwise>
    </c:choose>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
        <jsp:include page="common/logged-in-user-info.jsp" />
        <hr>
    <div id="header">
        <c:choose>
            <c:when test="${product.id == null}">
                <h3>Add new Product</h3>
            </c:when>
            <c:otherwise>
                <h3>Edit Product</h3>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div id="container">
    <div id="content">

                <form:form action="save" modelAttribute="product" method="post">
                    <form:hidden path="id"/>
                    <table>
                        <tbody>
                        <tr>
                            <td><label>Title:</label></td>
                            <td><form:input path="title"/></td>
                        </tr>
                        <tr>
                            <td><label>Quantity:</label></td>
                            <td><form:input path="quantity"/></td>
                        </tr>
                        <tr>
                            <td><label>Unit:</label></td>
                            <td><form:input path="unit"/></td>
                        </tr>
                        <tr>
                            <td><label>Description:</label></td>
                            <td><form:input path="description"/></td>
                        </tr>
                        <tr>
                            <td><label>Checked:</label></td>
                            <td><form:checkbox path="checked"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Save" class="save"/></td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
        <div style="clear: both;">
            <p>
                <a href="${pageContext.request.contextPath}/product/list">Back to Products</a>
            </p>
        </div>

    </div>
</div>
</body>
</html>
