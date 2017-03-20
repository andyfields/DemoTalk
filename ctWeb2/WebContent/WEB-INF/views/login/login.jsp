<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="css/common/styles.css">
  <link rel="stylesheet" href="css/login/login.css">
  <title>Login</title>
  
</head>
<body>
  
<header class="bgimage img-responsive">
</header>

<div class="container">
<div class="row">
    <div class="col-md-12 col-sm-12 panel panel-default margin-top-15">
      <div class="panel-body ctblue">
        <h1 class="margin-top-none">Cooltalk Forum</h1>
        <h5 class="padding-left-45">Take charge or your conversations</h5>
      </div>
    </div>
</div>
</div>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <form:form action="login.do" method="post" modelAttribute="loginParms" class="form-horizontal">
                <div class="form-group">
                  <spring:bind path="*">
                    <c:forEach items="${status.errorMessages}" var="error">
                      <div class="col-sm-offset-3 col-sm-9">
                        <div class="alert alert-danger alert-style">
                          <c:out value="${error}" />
                        </div>
                      </div>
                      
                    </c:forEach>
                  </spring:bind>
                </div>
                
                <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="inputEmail">Email:</label>
                    <div class="col-sm-9">
                        <form:input id="inputEmail" path="inputEmail" type="email" placeholder="Email" class="form-control text-box-width" required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="inputPassword">Password:</label>
                    <div class="col-sm-9">
                        <form:input id="inputPassword" path="inputPassword" type="password" placeholder="Password" class="form-control text-box-width" required="true" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-9">
                        <button id="loginSubmit" type="submit" class="btn btn-primary button-padding disabled" disabled>Submit</button>
                    </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-4"></div>
                  <div class="col-sm-8">
                    <a href="sendResetPassword.do" class="hidden-xs link-register-forgot register-left-forgot-padding">Forgot Password</a>
                    <a href="sendResetPassword.do" class="hidden-sm hidden-md hidden-lg link-register-forgot">Forgot Password</a>
                  </div>
                  <div class="col-sm-4 margin-top-5"></div>
                  <div class="col-sm-8 margin-top-5">
                    <a href="register.do" class="hidden-xs link-register-forgot register-left-forgot-padding">Register</a>
                    <a href="register.do" class="hidden-sm hidden-md hidden-lg link-register-forgot">Register</a>
                  </div>
                </div>
            </form:form>
        </div> <!-- col -->
    </div> <!-- row -->
    
</div>

<div class="container">
<div class="row">
    <div class="col-md-12 col-sm-12 panel panel-default">
      <div class="panel-body padding-top-none">
        <h4 class="margin-top-none">Welcome to the CoolTalk Forum, a new concept in open discussion sites!</h4>
        <h5>The CoolTalk forum reinvents the way open coversations are held online by putting the members in control
        of what they see and who they talk to.</h5>
      </div>
    </div>
</div>
</div>

<script src="js/common/jquery-3.1.1.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/login/login.js"></script>  

</body>
</html>
