
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring 4 Web MVC via Annotations</title>
                <link rel="stylesheet" type="text/css" href="${cp}/resources/css/login.css" />
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/bootstrap.css" />
        <script src="${cp}/resources/js/jquery.js"></script>
        <script src="${cp}/resources/js/bootstrap.js"></script>
        <script>
            var msg='${msg}';
            if(msg.length>0){
                alert(msg);
            }
        </script>
    </head>
    <body id="LoginForm">
        <div class="container">
            <h1 class="form-heading">User Login</h1>
            <div class="login-form">
                <div class="main-div">
                    <div class="panel">
<!--                        <h2>Admin Login</h2>-->
                        <p>Please enter your email and password</p>
                    </div>
                    <form:form method ="POST" modelAttribute="user" action="loginUser">
                        <div class="form-group">
                            <form:input path="email" class="form-control" id="inputEmail" placeholder="Email Address"/>
                        </div>
                        <div class="form-group">
                            <form:password path="password" class="form-control" id="inputPassword" placeholder="Password"/>
                        </div>
<!--                        <div class="forgot">
                            <a href="reset.html">Forgot password?</a>
                        </div>-->
                        <button type="submit" class="btn btn-primary">Login</button><br/>
                        <br/>
                        <a href="register"><button type="button" class="btn btn-primary">Register</button></a>
                    </form:form>
                </div>
                
            </div>
        </div>    
    </body>
</html>
