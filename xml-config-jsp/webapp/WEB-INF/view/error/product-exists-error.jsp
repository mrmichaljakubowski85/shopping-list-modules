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
