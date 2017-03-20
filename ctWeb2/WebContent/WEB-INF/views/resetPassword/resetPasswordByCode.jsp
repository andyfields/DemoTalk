<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="css/common/styles.css">
  <link rel="stylesheet" href="css/resetPassword/resetPasswordByCode.css">
  <title>Reset Password</title>

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
          <h2 class="margin-top-none">Reset Password</h2>
        </div>
      </div>

      <div class="row">
        <div class="col-xs-12">
          <form:form id="resetPasswordByCodeForm" action="resetPasswordByCode.do" method="post" modelAttribute="parmsResetPassword"
            enctype="application/x-www-form-urlencoded" class="form-horizontal">

            <form:hidden path="resetPasswordCode" />

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

            <div class="form-group">
              <c:forEach items="${parmsResetPassword.successMessages}" var="msg">
                <div class="col-sm-offset-3 col-sm-9">
                  <div class="alert alert-success alert-style">
                    <c:out value="${msg}" />
                  </div>
                </div>

              </c:forEach>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-group">
              <label class="col-sm-3 control-label" for="inputPassword">Password:</label>
              <div class="col-sm-9">
                <form:input id="inputPassword" path="inputPassword" type="password" placeholder="Password"
                  class="form-control text-box-width" />
                <span class="visible-lg-inline visible-xl-inline"> <span
                  class="pw-length-warning input-warning margin-left-15 hidden"> Passwords must be eight or more
                    characters.</span>
                </span>
              </div>
              <div class="col-sm-offset-3 col-sm-9 hidden-xl hidden-lg">
                <span class="pw-length-warning input-warning hidden"> Passwords must be eight or more characters.</span>
              </div>
            </div>
            
            <div class="form-group">
              <label class="col-sm-3 control-label" for="confirmationPassword">Confirm Password:</label>
              <div class="col-sm-9">
                <input id="confirmationPassword" type="password" placeholder="Confirm Password"
                  class="form-control text-box-width" /> <span class="visible-lg-inline visible-xl-inline"> <span
                  class="pw-nomatch-warning input-warning margin-left-15 hidden"> Passwords do not match.</span>
                </span>
              </div>
              <div class="col-sm-offset-3 col-sm-9 hidden-xl hidden-lg">
                <span class="pw-nomatch-warning input-warning hidden"> Passwords do not match. </span>
              </div>

            </div>
            <div class="form-group">
              <div class="col-sm-3"></div>
              <div class="col-sm-9">
                <button id="btnSubmit" type="submit" class="btn btn-primary button-padding">Submit</button>
              </div>
            </div>
          </form:form>
        </div>
        <!-- col -->
      </div>
      <!-- row -->

    </div>

    <script src="js/common/jquery-3.1.1.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/resetPassword/resetPasswordByCode.js"></script>  

  </body>
</html>
