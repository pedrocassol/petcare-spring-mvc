<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Registrar Consultas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consultas.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Registrar Consultas</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="top-buttons d-flex justify-content-center gap-3">

            <a href="${pageContext.request.contextPath}/consultas" class="btn btn-primary">Cadastrar consulta</a>

            <a href="${pageContext.request.contextPath}/listarConsultas" class="btn btn-outline-primary">Visualizar consultas</a>

        </div>

        <div class="consultation-card p-4 shadow-sm">

            <div class="d-flex justify-content-end mb-2">
                <span class="required-note">* indica campo obrigatório</span>
            </div>

            <form action="${pageContext.request.contextPath}/consulta" method="post" accept-charset="UTF-8">

                <div class="mb-3">
                    <label class="form-label">Paciente (Pet): <span class="required">*</span></label>

                    <select name="idPet" class="form-select" required>
                        <option value="">Selecione o pet</option>

                        <c:forEach var="p" items="${pets}">
                            <option value="${p.id}">
                                    ${p.nome} - ${p.nomeProprietario}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Data e Hora: <span class="required">*</span></label>
                        <input type="datetime-local" name="dataHora" class="form-control" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Veterinário Responsável: <span class="required">*</span></label>
                        <select name="veterinario" class="form-select" required>
                            <option value="">Selecione o veterinário</option>
                            <option value="Dra. Mariana">Dra. Mariana</option>
                            <option value="Dr. Lucas">Dr. Lucas</option>
                            <option value="Dra. Camila">Dra. Camila</option>
                        </select>
                    </div>

                </div>

                <div class="mb-3">
                    <label class="form-label">Descrição da Consulta / Motivo: <span class="required">*</span></label>
                    <textarea name="descricao" rows="4" class="form-control" placeholder="Descreva os sintomas ou motivo da visita..." required></textarea>
                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Valor Estimado (R$): <span class="required">*</span></label>
                        <input type="number" name="valorEstimado" class="form-control" min="0" step="0.01" placeholder="0.00" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Status <span class="required">*</span></label>

                        <div class="d-flex align-items-center gap-4 pt-2">
                            <label>
                                <input type="radio" name="status" value="Agendada" checked>Agendada
                            </label>

                            <label>
                                <input type="radio" name="status" value="Realizada">Realizada
                            </label>
                        </div>
                    </div>

                </div>

                <div class="mb-4">
                    <label class="form-label">Observações Adicionais:</label>
                    <textarea name="observacoes" rows="3" class="form-control" placeholder="Notas..."></textarea>
                </div>

                <hr>

                <div class="d-flex justify-content-end gap-2 pt-2">

                    <button type="reset" class="btn btn-outline-secondary">Cancelar</button>

                    <button type="submit" class="btn btn-primary">Salvar Consulta</button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>

</html>
