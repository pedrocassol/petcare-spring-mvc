<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Cadastro de Proprietários</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/proprietarios.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Cadastro de Proprietários</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="top-buttons d-flex justify-content-center gap-3">

            <a href="${pageContext.request.contextPath}/proprietarios" class="btn btn-primary">Cadastrar Proprietário</a>

            <a href="${pageContext.request.contextPath}/listarProprietarios" class="btn btn-outline-primary">Visualizar Proprietários</a>

        </div>

        <div class="consultation-card p-4 shadow-sm">

            <div class="d-flex justify-content-end mb-2">
                <span class="required-note">* indica campo obrigatório</span>
            </div>

            <h5 class="section-title mb-3">Informações do Proprietário</h5>

            <form action="${pageContext.request.contextPath}/proprietario" method="post" accept-charset="UTF-8">

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nome <span class="required">*</span></label>
                        <input type="text" name="nome" class="form-control" placeholder="Ex: Pedro Cassol" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Telefone <span class="required">*</span></label>
                        <input type="text" name="telefone" class="form-control" placeholder="Ex: (55) 99999-9999" required>
                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">E-mail <span class="required">*</span></label>
                        <input type="email" name="email" class="form-control" placeholder="Ex: pedro@email.com" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Endereço <span class="required">*</span></label>
                        <input type="text" name="endereco" class="form-control" placeholder="Ex: Rua X, Centro" required>
                    </div>

                </div>

                <hr>

                <div class="d-flex justify-content-end gap-2 pt-2">

                    <button type="reset" class="btn btn-outline-secondary">Cancelar</button>

                    <button type="submit" class="btn btn-primary">Salvar Cadastro</button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>

</html>
