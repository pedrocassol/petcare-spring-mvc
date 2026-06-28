<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Editar Consulta</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consultas.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Editar Consulta</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <c:if test="${not empty erro}">
            <div class="alert alert-danger" role="alert">${erro}</div>
        </c:if>

        <c:if test="${not empty erros}">
            <div class="alert alert-danger" role="alert">
                <ul class="mb-0">
                    <c:forEach var="item" items="${erros}">
                        <li>${item.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <div class="consultation-card p-4 shadow-sm">

            <form action="${pageContext.request.contextPath}/editarConsulta" method="post" accept-charset="UTF-8">

                <input type="hidden" name="id" value="${consulta.id}">

                <div class="mb-3">
                    <label class="form-label">Paciente (Pet) <span class="required">*</span></label>

                    <select name="idPet" class="form-select" required>
                        <c:forEach var="p" items="${pets}">
                            <option value="${p.id}" ${p.id == consulta.idPet ? 'selected' : ''}>
                                    ${fn:escapeXml(p.nome)} - ${fn:escapeXml(p.nomeProprietario)}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Data e Hora <span class="required">*</span></label>
                        <input type="datetime-local" name="dataHora" class="form-control" value="${consulta.dataHora}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Veterinário Responsável <span class="required">*</span></label>
                        <select name="veterinario" class="form-select" required>
                            <option value="Dra. Mariana" ${consulta.veterinario == 'Dra. Mariana' ? 'selected' : ''}>
                                Dra. Mariana
                            </option>
                            <option value="Dr. Lucas" ${consulta.veterinario == 'Dr. Lucas' ? 'selected' : ''}>
                                Dr. Lucas
                            </option>
                            <option value="Dra. Camila" ${consulta.veterinario == 'Dra. Camila' ? 'selected' : ''}>
                                Dra. Camila
                            </option>
                        </select>
                    </div>

                </div>

                <div class="mb-3">
                    <label class="form-label">Descrição da Consulta / Motivo <span class="required">*</span></label>
                    <textarea name="descricao" rows="4" class="form-control" maxlength="500"
                              required>${fn:escapeXml(consulta.descricao)}</textarea>
                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Valor Estimado (R$) <span class="required">*</span></label>
                        <input type="number" name="valorEstimado" class="form-control" min="0" step="0.01" value="${consulta.valorEstimado}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Status <span class="required">*</span></label>

                        <div class="d-flex align-items-center gap-4 pt-2">
                            <label>
                                <input type="radio" name="status" value="Agendada"
                                       ${consulta.status == 'Agendada' ? 'checked' : ''} required>Agendada
                            </label>

                            <label>
                                <input type="radio" name="status" value="Realizada" ${consulta.status == 'Realizada' ? 'checked' : ''}>Realizada
                            </label>
                        </div>
                    </div>

                </div>

                <div class="mb-4">
                    <label class="form-label">Observações Adicionais</label>
                    <textarea name="observacoes" rows="3" class="form-control"
                              maxlength="500">${fn:escapeXml(consulta.observacoes)}</textarea>
                </div>

                <hr>

                <div class="d-flex justify-content-end gap-2 pt-2">

                    <a href="${pageContext.request.contextPath}/listarConsultas" class="btn btn-outline-secondary">Cancelar</a>

                    <button type="submit" class="btn btn-primary">Salvar alterações</button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>

</html>
