<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring 4 Web MVC via Annotations</title>    
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
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Register</div>
                        <div class="card-body">
                            <form:form method = "POST" modelAttribute="user" action = "registerUser">
                                <div class="form-group">
                                    <label for="name" class="cols-sm-2 control-label">Your Name</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <form:input class="form-control" path="name" id="name" placeholder="Enter your Name" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="cols-sm-2 control-label">Your Email</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                                <form:input class="form-control" path="email" id="email" placeholder="Enter your Email" />
                                        </div>
                                    </div>
                                </div>                                
                                <div class="form-group">
                                    <label for="password" class="cols-sm-2 control-label">Password</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                            <form:password class="form-control" path="password" id="password" placeholder="Enter your Password" />
                                        </div>
                                    </div>
                                </div> 
                                <div class="form-group ">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button">Register</button>
                                </div>
                                <div class="login-register">
                                    <a href="login">Login</a>
                                </div>
                            </form:form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
