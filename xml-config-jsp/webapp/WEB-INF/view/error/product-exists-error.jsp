<%--
  Created by IntelliJ IDEA.
  User: Kliny
  Date: 19.02.2019
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h3>Error - Product with ID already exists: ${productId}</h3>
    <a href="${pageContext.request.contextPath}/product/list">Back to Products</a>
</body>
</html>
