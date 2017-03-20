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
  <link rel="stylesheet" href="css/resetPassword/sendResetPassword.css">
  <title>Send Reset Password</title>
  
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
    <div class="col-xs-12 col-sm-offset-3 col-sm-9">
      <h2 class="margin-top-none">Send Reset Password</h2>
    </div>
  </div>

    <div class="row">
        <div class="col-xs-12 col-sm-offset-3 col-sm-9">
          <h5 class="margin-top-none">A reset code will be sent to your email.</h5>
        </div>
        
        <div class="col-xs-12">
          <form:form action="sendResetPassword.do" method="post" modelAttribute="sendResetParms" class="form-horizontal">
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
              <label class="col-sm-3 control-label" for="resetEMail">Login Email:</label>
              <div class="col-sm-9">
                <form:input id="resetEMail" path="resetEMail" type="email" placeholder="Email" class="form-control text-box-width" required="true" />
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-3"></div>
              <div class="col-sm-9">
                <button id="resetSubmit" type="submit" class="btn btn-primary button-padding disabled" disabled>Submit</button>
              </div>
            </div>
          </form:form>
        </div> <!-- col -->
    </div> <!-- row -->
    
</div>

<script src="js/common/jquery-3.1.1.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/resetPassword/sendResetPassword.js"></script>  

</body>
</html>
