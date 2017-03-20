<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="oldlg" uri="/WEB-INF/OutlandTags.tld"%>


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
  <link rel="stylesheet" href="css/sections/sections.css">
  <title>Sections</title>

</head>
<body>
  

<header>
  <nav class="navbar navbar-default navbar-fixed-top ">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapseMenu">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      <div id="collapseMenu" class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">Account</a></li>
          <li><a href="#">Log Out</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="bgimage img-responsive" />
</header>

<div class="container">
  <div class="row">
      <div class="col-md-12 col-sm-12 panel panel-default margin-top-15">
        <div class="panel-body ctblue">
          <h1 class="margin-top-none">Cooltalk Forum</h1>
          <h5 class="padding-left-45">Take charge of your conversations</h5>
        </div>
      </div>
  </div>
</div>
  
<div class="container">
    <div class="row">
      <div class="col-xs-12 col-sm-offset-1 col-sm-5">
        <div>
          <h2 class="ctalign-top margin-top-none">Sections</h2>
          <a class="admin-link-font margin-left-15" data-toggle="modal" data-target="#addNewSection" href="#">Add</a>
<!--           <button typ="button" class="btn admin-link-font margin-left-15" data-toggle="modal" data-target="#addNewSection">Add</a> -->
        </div>
      </div>
      <div class="col-sm-6">
      </div>
    </div>
    
    <div id="SectionList">
    
    </div> <!-- SectionList -->
    
    <div id="templates" class="hidden">
      <div id="section-row-template">
        <div class="row">
            <div class="col-xs-12">
              
              <div class="col-sm-offset-1 col-sm-11">
                <a class="admin-edit" href="#">Edit</a><a class="margin-left-15 section-name" href="#">Section1</a>
              </div>
            
            </div> <!-- col -->
        </div> <!-- row -->
      </div>
    </div> <!-- hidden -->
        
    <!-- Bootstrap addNewSection modal tag -->
    <oldlg:BootstrapModal modalId='addNewSection' title='Add New Section' inputLabel='Section Name:' inputId='newSectionName' 
        inputPlaceholder='Section Name' button1caption='Create Section' button1Id='btnCreateSection' >
    </oldlg:BootstrapModal>
    
    <!-- Bootstrap cancel addNewSection modal tag -->
    <oldlg:BootstrapModal modalId='confirmCloseNewSection' title='Confirm Close' size="small" 
           button1caption='OK' button1Id='btnConfirmCloseNewSection'  button1Dismiss='true'
           button2caption='Cancel' button2Id='btnCancelCloseNewSection' button2Dismiss='true'> 
        No section will not be created.
    </oldlg:BootstrapModal> 
        
    <!-- Bootstrap editSection modal tag -->
    <oldlg:BootstrapModal modalId='editSection' title='Edit Section Name' inputLabel='Section Name:' inputId='editSectionName' 
        inputPlaceholder='Section Name' button1caption='Edit Section Name' button1Id='btnEditSection' >
    </oldlg:BootstrapModal>
    
    <!-- Bootstrap cancel editSection modal tag -->
    <oldlg:BootstrapModal modalId='confirmCloseEditSection' title='Confirm Close' size="small" 
           button1caption='OK' button1Id='btnConfirmCloseEditSection'  button1Dismiss='true'
           button2caption='Cancel' button2Id='btnCancelEditSection' button2Dismiss='true'> 
        Name will not be changed.
    </oldlg:BootstrapModal> 
        
</div>

<script src="js/common/jquery-3.1.1.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sections/sections.js"></script>  
<script type="text/javascript" src="js/sections/sectionsList.js"></script>  

</body>
</html>
