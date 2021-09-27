<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<link href="style.css" rel="stylesheet">
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Ajouter une personne</title>
</head>
<body>
<jsp:include page="entete.jsp"></jsp:include>
<h1>Inscription</h1>
<div class="container row justify-content-center inscription">
    <%--@elvariable id="personne" type=""--%>
    <form:form action="personne" method="post" modelAttribute="personne">

        <form:label path="pseudo">Pseudo</form:label>
        <form:input path="pseudo" type="text"></form:input>
        <form:errors path="pseudo" cssClass="erreur"></form:errors>
        <br>
        <form:label path="email">Email</form:label>
        <form:input path="email" type="email"></form:input>
        <form:errors path="email" cssClass="erreur"></form:errors>
        <br>
        <form:label path="motDePasse">Mot de passe</form:label>
        <form:password path="motDePasse"></form:password>
        <form:errors path="motDePasse" cssClass="erreur"></form:errors>
        <br>
        <form:label path="ville">Ville</form:label>
        <form:select path="ville">
            <form:option value="">Veuillez sélectionner</form:option>
            <form:options items="${villes}" itemLabel="nom" itemValue="id" />
        </form:select>
        <form:errors path="ville" cssClass="erreur"></form:errors>
        <br>
        <form:label path="genre">Genre</form:label>
        <form:select path="genre">
            <form:option value="">Veuillez sélectionner</form:option>
            <form:options items="${genres}" itemLabel="nom" itemValue="id"/>
        </form:select>
        <form:errors path="genre" cssClass="erreur"></form:errors>
        <br>
        <form:label path="genreRecherche">Genre recherché</form:label>
        <form:select path="genreRecherche">
            <form:option value="">Veuillez sélectionner</form:option>
            <form:options items="${genres}" itemLabel="nom" itemValue="id"></form:options>
        </form:select>
        <form:errors path="genre" cssClass="erreur"></form:errors>
        <br>
        <form:label path="statut">Statut</form:label>
        <form:select path="statut">
            <form:option value="">Veuillez sélectionner</form:option>
            <form:options items="${statuts}" itemLabel="nom" itemValue="id"></form:options>
        </form:select>
        <form:errors path="statut" cssClass="erreur"></form:errors>
        <br>
        <form:label path="interets">Interêts</form:label>
        <form:select path="interets" multiple="true">
            <form:options items="${interets}" itemLabel="nom" itemValue="id"></form:options>
        </form:select>
        <form:errors path="interets" cssClass="erreur"></form:errors>
        <br>
        <form:label path="bio">Bio</form:label>
        <form:textarea path="bio"></form:textarea>
        <form:errors path="interets" cssClass="erreur"></form:errors>
        <br>
        <form:label path="dateDeNaissance">Date de Naissance</form:label>
        <form:input path="dateDeNaissance" type="Date"></form:input>
        <form:errors path="dateDeNaissance" cssClass="erreur"></form:errors>
        <br>
        <form:label path="estFumeur">Fumeur</form:label>
        <form:checkbox path="estFumeur"></form:checkbox>
        <br>
        <form:hidden path="id"/>
        <form:button>Enregistrer</form:button>
    </form:form>
</div>

</body>
</html>
