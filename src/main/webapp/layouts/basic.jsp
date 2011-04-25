<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<title><tiles:getAsString name="title"/></title>
  
  	<link rel="stylesheet" href="<c:url value="/css/sunny/jquery-ui-1.8.11.custom.css" />" />
  	<link rel="stylesheet" href="<c:url value="/css/style.css" />" />
  	<script type="text/javascript" src="<c:url value="/js/jquery-1.5.1.min.js" />"></script>
  	<script type="text/javascript" src="<c:url value="/js/jquery.validate.min.js" />"></script>
  	<script type="text/javascript" src="<c:url value="/js/additional-methods.min.js" />"></script>
  	<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.11.custom.min.js" />"></script>
  	<script type="text/javascript" src="<c:url value="/js/money.js" />"></script>
  </head>
<body class="ui-widget" >
        <table width="100%">
      <tr>
        <td>
          <tiles:insertAttribute name="header" />
        </td>
      </tr>
      <tr>
        <td class="menu" align="right">
          <tiles:insertAttribute name="menu" />
        </td>
      </tr>
      <tr>
        <td>
          <div id="content" class="ui-widget-content">
          	<tiles:insertAttribute name="body" />
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table>
  </body>
</html>