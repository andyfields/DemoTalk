<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
  <title>${display.title}</title>
  
</head>
<body>
  
<header class="hidden-xs bgimage img-responsive">
</header>

<div class="container">
  <div class="row">
      <div class="col-sm-9 col-xs-12 panel panel-default margin-top-15">
        <div class="panel-body ctblue">
          <h1 class="margin-top-none">${display.banner}</h1>
        </div>
      </div>
  </div>

  <form method="get" action="${display.forwardURL}">
      <div class="row">
        <div class="form-group">
        	<c:forEach items="${display.messages}" var="message">
              <div class="col-sm-offset-1 col-sm-8 col-xs-9 margin-top-10" >
                ${message}
              </div>
              <div class="col-xs-3"></div>
            </c:forEach>            
        </div>
      </div>
      
      <div class="row margin-top-15">
         <div class="form-group">
            <div class="col-sm-1"></div>
            <div class="col-sm-11">
                <button id="loginSubmit" type="submit" class="btn btn-primary button-padding">OK</button>
            </div>
          </div>
      </div>

  </form>
    
</div>

</body>
</html>