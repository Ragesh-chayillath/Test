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
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/home.css" />
        <script src="${cp}/resources/js/jquery.js"></script>
        <script src="${cp}/resources/js/bootstrap.js"></script>
        <script>
            var msg = '${msg}';
            if (msg.length > 0) {
                alert(msg);
            }
        </script>
    </head>
    <body>
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <header id="header">

                            <div class="slider">
                                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner" role="listbox">
                                        <div class="item active">
                                            <img src="${cp}/resources/css/images/theam.png">
                                        </div>
                                        <div class="item">
                                            <img src="">
                                        </div>
                                    </div>

                                </div>
                            </div><!--slider-->
                            <nav class="navbar navbar-default" style="background-color: #ff3120">                                
                                <a class="navbar-brand" href="#"><img class="img-responsive" style="width:160px;height: 170px;" src="${cp}/resources/css/images/download.png"></a>
                                <span class="site-name">Welcome ${user.name}</span>
                                <span class="site-description">${user.email}</span>                                
                            </nav>
                        </header>
                    </div>
                </div>
            </div>
            <!--            ----------------------------------------------->
            <div class="container">
                <h2 class="text-warning col-md-12" style="font-weight: bold;text-align: center;">Your Blogs</h2>
                <a href="#popup1" class="col-md-12">
                    <button type="button" class="btn btn-primary" style="float: right;">Add New Blog</button>
                </a>
                <c:if test="${not empty blogs}">
                    <div class="row">     
                        <c:forEach var="blogItem" items="${blogs}">
                            <div class="span3 col-md-4">
                                <div class="well">
                                    <h2 class="text-info"><c:out value="${blogItem.blog_title}"/></h2>              
                                    <p><c:out value="${blogItem.blog_description}"/> </p>
                                    <hr>                
                                </div>
                            </div>
                        </c:forEach>                        
                    </div>
                </c:if>
            </div>

            <!--            ---------------------------------------------->
            <div id="popup1" class="overlay">
                <div class="popup">
                    <h2>Add New Blog</h2>
                    <a class="close" href="#">×</a>
                    <div class="content">
                        <div class="login-form">
                            <div class="main-div">
                                <form:form method ="POST" modelAttribute="blog" action="addBlog">
                                    <div class="form-group">
                                        <form:input path="blog_title" class="form-control"  placeholder="Title"/>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="blog_description" class="form-control" placeholder="Description"/>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Create Blog</button><br/>                                   
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
