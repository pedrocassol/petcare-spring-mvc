<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Cadastro de Pets</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pets.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Cadastro de Pets</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="top-buttons d-flex justify-content-center gap-3">

            <a href="${pageContext.request.contextPath}/pets" class="btn btn-primary">Cadastrar Pet</a>

            <a href="${pageContext.request.contextPath}/listarPets" class="btn btn-outline-primary">Visualizar Pets</a>

        </div>

        <div class="consultation-card p-4 shadow-sm">

            <div class="d-flex justify-content-end mb-2">
                <span class="required-note">* indica campo obrigatório</span>
            </div>

            <form action="${pageContext.request.contextPath}/pet" method="post" accept-charset="UTF-8">

                <h5 class="section-title mb-3">Informações do Animal</h5>
                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nome do animal: <span class="required">*</span></label>
                        <input type="text" name="nome" class="form-control" placeholder="Ex: Rex" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Espécie: <span class="required">*</span></label>
                        <select name="especie" class="form-select" required>
                            <option value="">Selecione a espécie</option>
                            <option>Cachorro</option>
                            <option>Gato</option>
                        </select>
                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Raça:</label>
                        <input type="text" name="raca" class="form-control" placeholder="Ex: Golden Retriever">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Idade:</label>
                        <input type="number" name="idade" class="form-control" placeholder="Ex: 3">
                    </div>

                </div>

                <div class="mb-3">
                    <label class="form-label">Sexo:</label>
                    <div class="d-flex align-items-center gap-4 pt-2">
                        <label><input type="radio" name="sexo" value="Macho">Macho</label>
                        <label><input type="radio" name="sexo" value="Fêmea">Fêmea</label>
                    </div>
                </div>

                <hr>

                <h5 class="section-title mb-3">Dados do Responsável</h5>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Proprietário: <span class="required">*</span></label>
                        <select name="idProprietario" class="form-select" required>
                            <option value="">Selecione o proprietário</option>
                            <c:forEach var="p" items="${proprietarios}">
                                <option value="${p.id}">${p.nome}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Telefone:</label>
                        <input type="text" name="telefone" class="form-control" placeholder="(00) 00000-0000">
                    </div>

                </div>

                <div class="mb-4">
                    <label class="form-label">Observações:</label>
                    <textarea name="observacoes" rows="4" class="form-control" placeholder="Ex: Animal alérgico a medicamentos"></textarea>
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
