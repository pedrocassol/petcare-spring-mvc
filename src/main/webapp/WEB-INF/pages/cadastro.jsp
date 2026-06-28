<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <title>Cadastro - PetCare</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cadastro.css?v=1">
</head>

<body class="d-flex align-items-center justify-content-center vh-100 bg-light-custom">

<div class="box-cadastro p-4 shadow-sm bg-white">

    <div class="text-center mb-4">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo img" class="img-fluid" style="max-width: 150px;">
    </div>

    <h2 class="text-center mb-4 petcare-title">Criar conta</h2>

    <form action="${pageContext.request.contextPath}/usuario" method="post" accept-charset="UTF-8">
        <div class="mb-3">
            <input type="text" name="nome" class="form-control" placeholder="Nome"
                   value="${fn:escapeXml(usuario.nome)}" maxlength="100" autocomplete="name" required>
        </div>

        <div class="mb-3">
            <input type="email" name="email" class="form-control" placeholder="E-mail"
                   value="${fn:escapeXml(usuario.email)}" maxlength="100" autocomplete="email" required>
        </div>

        <div class="mb-4">
            <input type="password" name="senha" class="form-control" placeholder="Senha"
                   minlength="6" maxlength="100" autocomplete="new-password" required>
        </div>

        <button type="submit" class="btn btn-petcare w-100">Cadastrar</button>
    </form>

    <div class="text-center mt-3">
        <a href="${pageContext.request.contextPath}/login" class="text-decoration-none petcare-link">Voltar</a>
    </div>

    <c:if test="${not empty erro}">
        <div class="alert alert-danger mt-3 mb-0 text-center p-2" role="alert" style="font-size: 14px;">
                ${erro}
        </div>
    </c:if>

    <c:if test="${not empty erros}">
        <div class="alert alert-danger mt-3 mb-0 p-2" role="alert" style="font-size: 14px;">
            <ul class="mb-0">
                <c:forEach var="erroValidacao" items="${erros}">
                    <li>${erroValidacao.defaultMessage}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

</div>

</body>
</html>
