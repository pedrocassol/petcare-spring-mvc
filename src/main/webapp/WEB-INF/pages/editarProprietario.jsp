<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Editar Proprietário</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/proprietarios.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Editar Proprietário</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="consultation-card p-4 shadow-sm">

            <div class="d-flex justify-content-end mb-2">
                <span class="required-note">* indica campo obrigatório</span>
            </div>

            <h5 class="section-title mb-3">Informações do Proprietário</h5>

            <form action="${pageContext.request.contextPath}/editarProprietario" method="post" accept-charset="UTF-8">

                <input type="hidden" name="id" value="${proprietario.id}">

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nome <span class="required">*</span></label>
                        <input type="text" name="nome" class="form-control" value="${proprietario.nome}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Telefone <span class="required">*</span></label>
                        <input type="text" name="telefone" class="form-control" value="${proprietario.telefone}" required>
                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">E-mail <span class="required">*</span></label>
                        <input type="email" name="email" class="form-control" value="${proprietario.email}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Endereço <span class="required">*</span></label>
                        <input type="text" name="endereco" class="form-control" value="${proprietario.endereco}" required>
                    </div>

                </div>

                <hr>

                <div class="d-flex justify-content-end gap-2 pt-2">

                    <a href="${pageContext.request.contextPath}/listarProprietarios" class="btn btn-outline-secondary">Cancelar</a>

                    <button type="submit" class="btn btn-primary">Salvar Alterações</button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>

</html>
