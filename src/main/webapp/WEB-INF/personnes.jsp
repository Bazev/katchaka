<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personnes</title>
</head>
<body>
<jsp:include page="entete.jsp"></jsp:include>
<h2>Liste des personnes</h2>
<form action="personnes" method="get">
    <input type="text" name="FILTRE" value="${filtre}">
    <input type="submit" value="Filter">
    <a href="/personnes">Enlever le filtre</a>
</form>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Pseudo</th>
        <th scope="col">Date de naissance <a href="personnes?sort=dateDeNaissance">&#x2B06</a> <a href="personnes?sort=dateDeNaissance,DESC">&#x2B07</a></th>
        <th scope="col">Statut <a href="personnes?sort=statut.nom">&#x2B06</a>
            <a href="personnes?sort=statut.nom,DESC">&#x2B07</a></th>
        <th scope="col">Ville <a href="personnes?sort=ville.nom">&#x2B06</a><a href="personnes?sort=ville.nom,DESC">&#x2B07</a>
        </th>
        <th scope="col">Interets</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageDePersonnes.content}" var="personne">
        <tr>
            <td>${personne.pseudo}</td>
            <td>${personne.dateDeNaissance}</td>
            <td>${personne.statut.nom}</td>
            <td>${personne.ville.nom}</td>
            <td><c:forEach items="${personne.interets}" var="interet">${interet.nom}
            </c:forEach></td>
            <td><button type="button" onclick="window.location.href='personne?ID=${personne.id}'" class="btn btn-warning" >Modifier</button>
                <button type="button" onclick="window.location.href='delete?ID=${personne.id}'"class="btn btn-danger delete">X</button>
                <button type="button" onclick="window.location.href='inviterPersonne?ID=${personne.id}'"class="btn btn-success">Inviter</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- On fournit les liens de navigation pour passer aux autres pages de personnes -->
<!-- On teste si la page de personnes est la première page -->
<c:if test="${!pageDePersonnes.first}">
    <a href="personnes?page=0&sort=${pageDePersonnes.sort.iterator().next().property},${pageDePersonnes.sort.iterator().next().direction}">&#x23EE;</a>
    <a href="personnes?page=${pageDePersonnes.number-1}&sort=${pageDePersonnes.sort.iterator().next().property},${pageDePersonnes.sort.iterator().next().direction}">&#x23EA;</a>
</c:if>
Page ${pageDePersonnes.number+1}
<!-- On teste si la page de personnes est la dernière page -->
<c:if test="${!pageDePersonnes.last}">
    <a href="personnes?page=${pageDePersonnes.number+1}&sort=${pageDePersonnes.sort.iterator().next().property},${pageDePersonnes.sort.iterator().next().direction}">&#x23E9;</a>
    <a href="personnes?page=${pageDePersonnes.totalPages - 1}&sort=${pageDePersonnes.sort.iterator().next().property},${pageDePersonnes.sort.iterator().next().direction}">&#x23ED;</a>
</c:if>
<br><br>
Tri sur pseudo : ${pageDePersonnes.sort.by("pseudo")} <br>
Tri actuel : ${pageDePersonnes.sort.iterator().next()}<br>
Propriété de tri : ${pageDePersonnes.sort.iterator().next().property}<br>
Direction de tri : ${pageDePersonnes.sort.iterator().next().direction}<br>
<br>
<div>
<jsp:useBean id="dateFin" class="java.util.Date"></jsp:useBean>
<c:set var="msFin" value="${dateFin.getTime()}" scope="page"></c:set>
    Page généré en : ${msFin-msDepart} ms</div>
</body>
</html>
