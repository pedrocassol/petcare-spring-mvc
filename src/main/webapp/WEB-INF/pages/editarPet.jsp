<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>

    <meta charset="UTF-8">

    <title>Editar Pet</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css?v=3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pets.css">

</head>

<body>

<jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />

<div class="content">

    <div class="page-header">
        <h1>Editar Pet</h1>
        <span class="user-greeting">Olá, ${sessionScope.usuario.nome}</span>
    </div>

    <div class="page-body">

        <div class="consultation-card p-4 shadow-sm">

            <div class="d-flex justify-content-end mb-2">
                <span class="required-note">* indica campo obrigatório</span>
            </div>

            <form action="${pageContext.request.contextPath}/editarPet" method="post" accept-charset="UTF-8">

                <input type="hidden" name="id" value="${pet.id}">

                <h5 class="section-title mb-3">Informações do Animal</h5>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Nome do animal <span class="required">*</span></label>
                        <input type="text" name="nome" class="form-control" value="${pet.nome}" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Espécie <span class="required">*</span></label>
                        <select name="especie" class="form-select">
                            <option value="Cachorro" ${pet.especie == 'Cachorro' ? 'selected' : ''}>
                                Cachorro
                            </option>
                            <option value="Gato" ${pet.especie == 'Gato' ? 'selected' : ''}>
                                Gato
                            </option>
                        </select>
                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Raça</label>
                        <input type="text" name="raca" class="form-control" value="${pet.raca}">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Idade</label>
                        <input type="number" name="idade" class="form-control" value="${pet.idade}">
                    </div>

                </div>

                <div class="mb-3">
                    <label class="form-label">Sexo</label>
                    <div class="d-flex align-items-center gap-4 pt-2">
                        <label>
                            <input type="radio" name="sexo" value="Macho"${pet.sexo == 'Macho' ? 'checked' : ''}>Macho
                        </label>
                        <label>
                            <input type="radio" name="sexo" value="Fêmea"${pet.sexo == 'Fêmea' ? 'checked' : ''}>Fêmea
                        </label>
                    </div>
                </div>

                <hr>

                <h5 class="section-title mb-3">Dados do Responsável</h5>

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Proprietário <span class="required">*</span></label>
                        <select name="idProprietario" class="form-select" required>
                            <c:forEach var="p" items="${proprietarios}">
                                <option value="${p.id}" ${p.id == pet.idProprietario ? 'selected' : ''}>
                                        ${p.nome}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                </div>

                <div class="mb-4">
                    <label class="form-label">Observações</label>
                    <textarea name="observacoes" rows="4" class="form-control">${pet.observacoes}</textarea>
                </div>

                <hr>

                <div class="d-flex justify-content-end gap-2 pt-2">

                    <a href="${pageContext.request.contextPath}/listarPets" class="btn btn-outline-secondary">Cancelar</a>

                    <button type="submit" class="btn btn-primary">Salvar Alterações</button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>

</html>
