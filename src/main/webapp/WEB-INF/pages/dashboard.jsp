<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css?v=2">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Dashboard</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="dashboard-body">

        <div class="dashboard-intro">

            <div>
                <h2>Visão Geral</h2>
                <p>Bem-vindo de volta.</p>
            </div>

            <div class="dashboard-actions">
                <a href="${pageContext.request.contextPath}/pets" class="btn btn-outline-primary">Cadastrar Pet</a>

                <a href="${pageContext.request.contextPath}/consultas" class="btn btn-primary">Nova Consulta</a>
            </div>

        </div>

        <div class="stats-grid">

            <div class="stat-card">
                <div class="stat-header">
                    <span>Pets Cadastrados</span>
                    <span class="stat-icon">P</span>
                </div>
                <strong>${totalPets}</strong>
            </div>

            <div class="stat-card">
                <div class="stat-header">
                    <span>Proprietários</span>
                    <span class="stat-icon">P</span>
                </div>
                <strong>${totalProprietarios}</strong>
            </div>

            <div class="stat-card">
                <div class="stat-header">
                    <span>Consultas Cadastradas</span>
                    <span class="stat-icon">C</span>
                </div>
                <strong>${totalConsultas}</strong>
            </div>

        </div>

        <div class="recent-card">

            <div class="recent-title">
                Atendimentos Recentes
            </div>

            <table class="table table-hover align-middle mb-0">

                <thead>
                <tr>
                    <th>Pet</th>
                    <th>Proprietário</th>
                    <th>Vet</th>
                    <th>Status</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="c" items="${consultasRecentes}">
                    <tr>
                        <td>${c.nomePet}</td>
                        <td>${c.nomeProprietario}</td>
                        <td>${c.veterinario}</td>
                        <td>
                            <span class="status-pill ${c.status == 'Realizada' ? 'status-done' : 'status-scheduled'}">
                                    ${c.status}
                            </span>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty consultasRecentes}">
                    <tr>
                        <td colspan="4" class="text-center text-muted py-4">
                            Nenhum atendimento cadastrado.
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
