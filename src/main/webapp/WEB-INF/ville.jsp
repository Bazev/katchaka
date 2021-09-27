<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une ville</title>
</head>
<body>
<jsp:include page="entete.jsp"/>
<h1>Ajouter une ville</h1>
<form action="ajouterVille" method="post">
    <label>Nom</label>
    <input type="text" name="ville"><br>
    <input type="submit" value="Enregistrer">
</form>
<br>
<a href="villes">Liste des villes</a><br>
<a href="interets">Liste des interêts</a><br>
<a href="personnes">Liste des personnes</a><br>
</body>
</html>