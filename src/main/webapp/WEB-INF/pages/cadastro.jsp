<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
            <input type="text" name="nome" class="form-control" placeholder="Nome" required>
        </div>

        <div class="mb-3">
            <input type="email" name="email" class="form-control" placeholder="E-mail" required>
        </div>

        <div class="mb-4">
            <input type="password" name="senha" class="form-control" placeholder="Senha" required>
        </div>

        <button type="submit" class="btn btn-petcare w-100">Cadastrar</button>
    </form>

    <div class="text-center mt-3">
        <a href="${pageContext.request.contextPath}/login" class="text-decoration-none petcare-link">Voltar</a>
    </div>

</div>

</body>
</html>
