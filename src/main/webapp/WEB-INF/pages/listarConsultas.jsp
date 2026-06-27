<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Consultas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consultas.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Consultas</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="top-buttons d-flex justify-content-center gap-3">

            <a href="${pageContext.request.contextPath}/consultas"
               class="btn btn-outline-primary">
                Cadastrar consulta
            </a>

            <a href="${pageContext.request.contextPath}/listarConsultas"
               class="btn btn-primary">
                Visualizar consultas
            </a>

        </div>

        <div class="consultation-card consultation-list-card p-4 shadow-sm">

            <div class="table-responsive">

            <table class="table table-hover align-middle mb-0 consultation-table">

                <thead>
                <tr>
                    <th>Paciente</th>
                    <th>Proprietário</th>
                    <th>Data e Hora</th>
                    <th>Veterinário</th>
                    <th>Status</th>
                    <th>Valor</th>
                    <th>Motivo</th>
                    <th class="text-end">Opções</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="c" items="${consultas}">
                    <tr>
                        <td>${c.nomePet}</td>
                        <td>${c.nomeProprietario}</td>
                        <td>${fn:replace(c.dataHora, 'T', ' ')}</td>
                        <td>${c.veterinario}</td>
                        <td>${c.status}</td>
                        <td>R$ ${c.valorEstimado}</td>
                        <td>${c.descricao}</td>
                        <td class="text-end">
                            <a href="${pageContext.request.contextPath}/editarConsulta?id=${c.id}"
                               class="btn btn-sm btn-outline-primary me-2">
                                Editar
                            </a>

                            <form action="${pageContext.request.contextPath}/excluirConsulta" method="post" class="d-inline" onsubmit="return confirm('Deseja excluir esta consulta?');">
                                <input type="hidden" name="id" value="${c.id}">
                                <button type="submit" class="btn btn-sm btn-outline-danger">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty consultas}">
                    <tr>
                        <td colspan="8" class="text-center text-muted py-4">
                            Nenhuma consulta cadastrada.
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
