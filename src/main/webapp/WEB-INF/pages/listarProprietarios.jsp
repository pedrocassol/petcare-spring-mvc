<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Proprietários</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/proprietarios.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Proprietários Cadastrados</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="top-buttons d-flex justify-content-center gap-3">

            <a href="${pageContext.request.contextPath}/proprietarios" class="btn btn-outline-primary">Cadastrar Proprietário</a>

            <a href="${pageContext.request.contextPath}/listarProprietarios" class="btn btn-primary">Visualizar Proprietários</a>

        </div>

        <c:if test="${not empty erro}">
            <div class="alert alert-danger mt-3 mb-3" role="alert">
                ${erro}
            </div>
        </c:if>

        <c:if test="${not empty sucesso}">
            <div class="alert alert-success mt-3 mb-3" role="alert">
                ${sucesso}
            </div>
        </c:if>

        <div class="consultation-card list-card p-4 shadow-sm">

            <table class="table table-hover align-middle mb-0">

                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>E-mail</th>
                    <th>Endereço</th>
                    <th class="text-end">Opções</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="p" items="${proprietarios}">
                    <tr>
                        <td>${p.nome}</td>
                        <td>${p.telefone}</td>
                        <td>${p.email}</td>
                        <td>${p.endereco}</td>
                        <td class="text-end">
                            <a href="${pageContext.request.contextPath}/editarProprietario?id=${p.id}" class="btn btn-sm btn-outline-primary me-2">Editar</a>

                            <form action="${pageContext.request.contextPath}/excluirProprietario" method="post" class="d-inline" onsubmit="return confirm('Deseja excluir este proprietário?');">
                                <input type="hidden" name="id" value="${p.id}">
                                <button type="submit" class="btn btn-sm btn-outline-danger">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty proprietarios}">
                    <tr>
                        <td colspan="5" class="text-center text-muted py-4">
                            Nenhum proprietário cadastrado.
                        </td>
                    </tr>
                </c:if>
                </tbody>

            </table>

        </div>

    </div>

</div>

</body>

</html>
