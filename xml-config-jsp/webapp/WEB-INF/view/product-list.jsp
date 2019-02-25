<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Products</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h3>List of Products</h3>
    </div>
</div>
<div id="container">
    <div id="content">

        <input type="button" value="Add Product" onclick="window.location.href='addProduct'; return false;"
               class="add-button"/>

        <table>
            <tr>
                <th>Title</th>
                <th>Quantity</th>
                <th>Unit</th>
                <th>Checked</th>
                <th>Action</th>
            </tr>
            <c:forEach var="product" items="${products}">

                <c:url var="productDetailsLink" value="/product/details">
                    <c:param name="productId" value="${product.id}"/>
                </c:url>
                <c:url var="deleteProductLink" value="/product/delete">
                    <c:param name="productId" value="${product.id}"/>
                </c:url>
                <c:url var="checkProductLink" value="/product/check">
                    <c:param name="productId" value="${product.id}"/>
                    <c:param name="action" value="${product.checked ? true : false}"/>
                </c:url>
                <tr>
                    <th>${product.title}</th>
                    <th>${product.description}</th>
                    <th>${product.quantity}</th>
                    <th>${product.unit}</th>
                    <th>
                            ${product.checked}
                            |
                             <a href="${checkProductLink}">Check/Uncheck</a>
                    </th>

                    <td>
                        <a href="${productDetailsLink}">Details</a>
                        |
                        <a href="${deleteProductLink}">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
