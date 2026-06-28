<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>

<body class="d-flex align-items-center justify-content-center vh-100 bg-light-custom">

<div class="box-login p-4 shadow-sm bg-white">

    <div class="text-center mb-4">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo img" class="img-fluid" style="max-width: 150px;">
    </div>

    <h2 class="text-center mb-4 petcare-title">Login</h2>

    <form action="${pageContext.request.contextPath}/login" method="post" accept-charset="UTF-8">
        <div class="mb-3">
            <input type="email" name="email" class="form-control" placeholder="E-mail"
                   value="${fn:escapeXml(email)}" maxlength="100" autocomplete="email" required>
        </div>
        <div class="mb-4">
            <input type="password" name="senha" class="form-control" placeholder="Senha"
                   maxlength="100" autocomplete="current-password" required>
        </div>
        <button type="submit" class="btn btn-petcare w-100">Entrar</button>
    </form>

    <div class="text-center mt-3">
        <a href="${pageContext.request.contextPath}/cadastro" class="text-decoration-none petcare-link">Criar conta</a>
    </div>

    <c:if test="${not empty erro}">
        <div class="alert alert-danger mt-3 mb-0 text-center p-2" role="alert" style="font-size: 14px;">
                ${erro}
        </div>
    </c:if>

    <c:if test="${not empty sucesso}">
        <div class="alert alert-success mt-3 mb-0 text-center p-2" role="alert" style="font-size: 14px;">
                ${sucesso}
        </div>
    </c:if>
</div>

</body>
</html>
