<%-- 
    Document   : index
    Created on : Dec 14, 2011, 11:01:34 AM
    Author     : manish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Search by rsid</title>
</head>
<body style="margin: 0px; padding: 0px; font-family: 'Trebuchet MS',verdana;">
    
<form name="frm" method="post" action="Display_Records/GetSNPs">    
<table width="100%" style="height: 100%;" cellpadding="10" cellspacing="0" border="1">
<tr>

<!-- ============ HEADER SECTION ============== -->
<td colspan="2" style="height: 100px;" bgcolor="#FFFFFF"><h1>
   <table>
    <tr> <td>  
   <img src="https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcR23tjTQgDZBhY1dxm7a2eSo7c37jrHWbBvqdWN6OlM3j8kaGnx" height="60" width="60" />
   </td> <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="4" face="arial"><b>Malaysian Genome Resouce Center</b></font> <br> <font size="4" color=#F88017>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Disease/Drug Search</font>  </td></tr></table>
</h1>
</td></tr>
<tr>
    
<!-- ============ LEFT COLUMN (MENU) ============== -->
<td width="12%" valign="top" bgcolor="#FFE4C4">
<br>
<b>Search by:
    <br>
    <A href="snp.jsp">-SNP RSID</A> 
    <br>
    <A href="disease.jsp">-Disease Name</A>
</b>
<br>
<table align ="center">
</table>  
</td>
<!-- ============ RIGHT COLUMN (CONTENT) ============== -->
<td width="88%" valign="top" bgcolor="#FFFFFF">
    <font size="4" color=#F88017>Search By SNP ID</font>
<HR NOSHADE>
<br>
<b> Enter a RSID </b> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="snpid" size ="30" /> <input type="submit" value="Search" /> 
</td></tr>
</table>  
</form>
</body>
<html>