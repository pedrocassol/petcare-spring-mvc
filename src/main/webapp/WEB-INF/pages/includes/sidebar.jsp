<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%
    String currentPath = (String) request.getAttribute("jakarta.servlet.forward.servlet_path");

    if (currentPath == null) {
        currentPath = request.getServletPath();
    }
%>

<div class="sidebar">

    <div class="logo-area">

        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo PetCare" class="logo-img">

        <span class="logo-text">PetCare</span>

    </div>

    <ul class="menu list-unstyled">

        <li class="<%= "/dashboard".equals(currentPath) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        </li>

        <li class="<%= "/proprietarios".equals(currentPath) || "/proprietario".equals(currentPath) || "/listarProprietarios".equals(currentPath) || "/editarProprietario".equals(currentPath) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/proprietarios">Proprietários</a>
        </li>

        <li class="<%= "/pets".equals(currentPath) || "/pet".equals(currentPath) || "/listarPets".equals(currentPath) || "/editarPet".equals(currentPath) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/pets">Pets</a>
        </li>

        <li class="<%= "/consultas".equals(currentPath) || "/consulta".equals(currentPath) || "/listarConsultas".equals(currentPath) || "/editarConsulta".equals(currentPath) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/consultas">Consultas</a>
        </li>

        <li class="<%= "/relatorios".equals(currentPath) ? "active" : "" %>">
            <a href="${pageContext.request.contextPath}/relatorios">Relatórios</a>
        </li>

    </ul>

    <div class="bottom-menu">

        <a href="${pageContext.request.contextPath}/logout">Sair</a>

    </div>

</div>
