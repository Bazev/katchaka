<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord</title>
</head>
<body>
<jsp:include page="entete.jsp"></jsp:include>

<h1>Bienvenue ${sessionScope.personne.pseudo}</h1>
<h2>Liste des invitations recus</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Pseudo</th>
        <th scope="col">Ville</th>
        <th scope="col">Envoyée le</th>
        <th scope="col">Opérations</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.personne.invitationsRecues}" var="invitation">
    <tr>
        <td>${invitation.expediteur.pseudo}</td>
        <td>${invitation.expediteur.ville.nom}</td>
        <td><fmt:formatDate pattern="dd/MM/yyyy hm" value="${invitation.dateEnvoi}"></fmt:formatDate> </td>
        <td><a href="reponseInvitation?ID=${invitation.id}&EST_ACCEPTE=true">Accepter</a>
            <a href="reponseInvitation?ID=${invitation.id}&EST_ACCEPTE=false">Décliner</a>
        </td>
    </tr>
    </c:forEach>

    </tbody>
</table>

<h2>Liste des invitation envoyés</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Pseudo</th>
        <th scope="col">Ville</th>
        <th scope="col">Envoyée le</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.personne.invitationsEnvoyees}" var="invitationEnvoye">
        <tr>
            <td>${invitationEnvoye.destinataire.pseudo}</td>
            <td>${invitationEnvoye.destinataire.ville.nom}</td>
            <td><fmt:formatDate pattern="dd/MM/yyyy hm" value="${invitation.dateEnvoi}"></fmt:formatDate> </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<br><br>
<button type="button" onclick="window.location.href='deconnexion'" class="btn btn-danger">Déconnexion</button>
</body>
</html>