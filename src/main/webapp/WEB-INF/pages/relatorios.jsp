<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Relatórios</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/relatorios.css?v=2">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Relatórios</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="reports-body">

        <c:if test="${not empty erro}">
            <div class="alert alert-danger" role="alert">${erro}</div>
        </c:if>

        <div class="reports-intro">
            <div>
                <h2>Resumo Geral</h2>
                <p>Acompanhe os principais números do PetCare.</p>
            </div>
        </div>

        <div class="filter-card">

            <form action="${pageContext.request.contextPath}/relatorios" method="get" accept-charset="UTF-8" class="period-filter">

                <label class="period-label">Período:</label>

                <input type="date" name="dataInicio" class="form-control period-input"
                       value="${fn:escapeXml(dataInicio)}">

                <span class="period-separator">até</span>

                <input type="date" name="dataFim" class="form-control period-input"
                       value="${fn:escapeXml(dataFim)}">

                <button type="submit" class="btn btn-primary period-button">Aplicar Filtros</button>

            </form>

        </div>

        <div class="reports-grid">

            <div class="report-card">
                <span>Pets Cadastrados</span>
                <strong>${totalPets}</strong>
            </div>

            <div class="report-card">
                <span>Proprietários</span>
                <strong>${totalProprietarios}</strong>
            </div>

            <div class="report-card">
                <span>Consultas no Período</span>
                <strong>${totalConsultas}</strong>
            </div>

            <div class="report-card">
                <span>Valor Estimado Total</span>
                <strong>R$ ${valorTotal}</strong>
            </div>

        </div>

        <div class="reports-table-card">

            <div class="table-title">Consultas</div>

            <div class="table-responsive">

                <table class="table table-hover align-middle mb-0">

                    <thead>
                    <tr>
                        <th>Data e Hora</th>
                        <th>Pet</th>
                        <th>Proprietário</th>
                        <th>Veterinário</th>
                        <th>Status</th>
                        <th>Valor</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="c" items="${consultas}">
                        <tr>
                            <td>${fn:replace(c.dataHora, 'T', ' ')}</td>
                            <td>${c.nomePet}</td>
                            <td>${c.nomeProprietario}</td>
                            <td>${c.veterinario}</td>
                            <td>
                                <span class="status-pill ${c.status == 'Realizada' ? 'status-done' : 'status-scheduled'}">
                                        ${c.status}
                                </span>
                            </td>
                            <td>R$ ${c.valorEstimado}</td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty consultas}">
                        <tr>
                            <td colspan="6" class="text-center text-muted py-4">
                                Nenhuma consulta encontrada.
                            </td>
                        </tr>
                    </c:if>
                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>

</body>

</html>
