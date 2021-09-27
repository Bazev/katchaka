<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<link href="style.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
</head>
<body>
<jsp:include page="entete.jsp"/>
<h1>Bienvenue Sur Katchaka</h1>
<h2>Connexion</h2>
<form action="connexion" method="post">
    <div class="mb-3 row formConnexion" >
        <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10 form">
            <input name="email" type="text"  class="form-control form-control-lg" id="staticEmail" placeholder="email@example.com">
        </div>
    </div>
    <div class="mb-3 row">
        <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-10">
            <input name="motDePasse" type="password" class="form-control form-control-lg" id="inputPassword">
        </div>
        <div>
            <button type="submit" class="btn btn-primary mb-3">Connexion</button>
        </div>
    </div>
</form>



</body>
</html>