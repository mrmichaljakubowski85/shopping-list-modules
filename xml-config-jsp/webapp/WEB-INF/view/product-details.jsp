<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="productDetailsLink" value="/product/edit">
    <c:param name="productId" value="${product.id}"/>
</c:url>
<c:url var="deleteProductLink" value="/product/delete">
    <c:param name="productId" value="${product.id}"/>
</c:url>
<html>
<head>
    <title>Product details</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h3>Product details</h3>
    </div>
</div>
<div id="container">
    <div id="content">
        <h4>Title</h4>
        <p>${product.title}</p>
        <h4>Description</h4>
        <p>${product.description}</p>
        <h4>Quantity</h4>
        <p>${product.quantity}</p>
        <h4>Unit</h4>
        <p>${product.unit}</p>
        <h4>Checked</h4>
        <p>${product.checked}</p>
        <p>
            <a href="${productDetailsLink}">Edit</a>
            |
            <a href="${deleteProductLink}">Remove</a>
        </p>
        <a href="${pageContext.request.contextPath}/product/list">Back to Products</a>
    </div>
</div>
</body>
</html>
