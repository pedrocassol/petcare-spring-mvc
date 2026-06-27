<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Pets</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pets.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Pets Cadastrados</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="top-buttons d-flex justify-content-center gap-3">

            <a href="${pageContext.request.contextPath}/pets" class="btn btn-outline-primary">Cadastrar Pet</a>

            <a href="${pageContext.request.contextPath}/listarPets" class="btn btn-primary">Visualizar Pets</a>

        </div>

        <div class="consultation-card list-card p-4 shadow-sm">

            <table class="table table-hover align-middle mb-0">

                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Espécie</th>
                    <th>Raça</th>
                    <th>Idade</th>
                    <th>Sexo</th>
                    <th>Proprietário</th>
                    <th>Observações</th>
                    <th class="text-end">Opções</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="p" items="${pets}">
                    <tr>
                        <td>${p.nome}</td>
                        <td>${p.especie}</td>
                        <td>${p.raca}</td>
                        <td>${p.idade}</td>
                        <td>${p.sexo}</td>
                        <td>${p.nomeProprietario}</td>
                        <td>${p.observacoes}</td>
                        <td class="text-end">
                            <a href="${pageContext.request.contextPath}/editarPet?id=${p.id}"
                               class="btn btn-sm btn-outline-primary me-2">
                                Editar
                            </a>

                            <form action="${pageContext.request.contextPath}/excluirPet" method="post" class="d-inline" onsubmit="return confirm('Deseja excluir este pet?');">
                                <input type="hidden" name="id" value="${p.id}">
                                <button type="submit" class="btn btn-sm btn-outline-danger">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty pets}">
                    <tr>
                        <td colspan="8" class="text-center text-muted py-4">
                            Nenhum pet cadastrado.
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
