<%-- 
    Document   : GetSNPRecords
    Created on : Dec 27, 2011, 2:55:12 PM
    Author     : manish
--%>

<%@ page language="java" import="java.sql.*"%>
<%@ page import="java.util.*" %>
<html>
<head><title>SNP Data</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<STYLE TYPE="text/css">#dek {POSITION:absolute;VISIBILITY:hidden;Z-INDEX:200;}</STYLE>
<DIV ID="dek"></DIV>
<script type="text/javascript" src="ajaxjs.js"></script>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="table.css" />
<script type="text/javascript" src="script.js"></script>
</head>
<body>
    <%! String getdiseasename(String hash_value) throws SQLException, ClassNotFoundException 
    {
            String gname = null;
	    Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://192.168.7.4/variant_db?" +"user=manish&password=";
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement ();
            String query = "select disease.name from disease where name_hash = "+ hash_value + ";"; 
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                    gname=  rs.getString(1); 
            }
            rs.close();
            stmt.close();
            con.close();
            return gname;
    }
    %>
  <table width="100%" height = 100% cellpadding="10" cellspacing="0" border="1">
          <!-- ============ HEADER SECTION ============== -->
             <tr>
                <td colspan=2 height= 100px; bgcolor=#FFFFFF>
                  <h1>               
                  <table>
                     <tr>  
                        <td>
                           <img src=https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcR23tjTQgDZBhY1dxm7a2eSo7c37jrHWbBvqdWN6OlM3j8kaGnx height=60 width=60 /></td>
                        <td> 
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=4 face=arial><b>Malaysian Genome Resouce Center</b></font> <br> <font size=4 color=#F88017>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Disease/Drug Search</font></td>
                     </tr>
                  </table>                
                  </h1>
               </td>
              </tr>
           
             <!-- ============ LEFT COLUMN (MENU) ============== -->
             <tr>
                 <td width=12% valign=top bgcolor=#FFE4C4>
                     <br>
                     <b>Search by:<br><A href=snp.jsp>-SNP RSID</A> <br><A href=disease.jsp>-Disease Name</A></b>
                     <br>
                 </td>                   
               <!-- ============ RIGHT COLUMN (CONTENT) ============== -->         
                 <td width= 88% valign=right bgcolor=#FFFFFF>
             
                    <div id="wrapper">
                    <div style="width: 1050px; height: 720px; overflow-x: auto; overflow-y: auto; background-color: #ffffff"> 
                        
	                 <table cellpadding="0" cellspacing="0" border="1" class="sortable" id="sorter" align="center" >
                        
                            <tbody>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Disease Name</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>SNP Id</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Risk SNP</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Allele Freq</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>p Value</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Entrez Symbol</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Gene Type</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>OMIM Id</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Journal</b></font></td>
                            <td bgColor="#D3D3D3" width="270" height="19"><font color="#ffffff" face= "Times New Roman"><b>Pubmed Id</b></font></td>
                          

<%
String[] hash_value = request.getParameterValues("hash_values");
String DRIVER = "com.mysql.jdbc.Driver";
Class.forName(DRIVER).newInstance();


Connection con=null;
ResultSet rst=null;
Statement stmt=null;

try{
String url="jdbc:mysql://192.168.7.4/variant_db?" +"user=manish&password=";

int i=1;
con=DriverManager.getConnection(url);
stmt=con.createStatement();
for (int j =0; j <hash_value.length; j++)
{
    String dname = getdiseasename(hash_value[j]);
    String query = "select  di_snp.risk_snp, di_snp.snp_id, di_snp.risk_allele, di_snp.allele_freq, di_snp.p_value, gene.entrez_symbol,gene.gene_type, gene.omim_id, pubmed.journal, pubmed.pm_id from di_snp join citation on citation.cite_id = di_snp.cite_id left outer JOIN pubmed on citation.pm_id = pubmed.pm_id left outer JOIN snp on snp.snp_id = di_snp.snp_id left outer join gene on  snp.entrez_id = gene.entrez_id WHERE di_snp.name_hash =" + hash_value[j] + " ORDER BY p_value"; 

    //String query = "select disease.name, di_snp.risk_snp, di_snp.snp_id, di_snp.risk_allele , di_snp.allele_freq, di_snp.p_value, gene.entrez_symbol,gene.gene_type, gene.omim_id, pubmed.journal, pubmed.pm_id from di_snp join disease on disease.name_hash = di_snp.name_hash join citation on citation.cite_id = di_snp.cite_id left outer JOIN pubmed on citation.pm_id = pubmed.pm_id left outer JOIN snp on snp.snp_id = di_snp.snp_id left outer join gene on  snp.entrez_id = gene.entrez_id WHERE di_snp.name_hash =" + hash_value[i] + " ORDER BY p_value"; 
rst=stmt.executeQuery(query);
while(rst.next()){

if (i==(i/2)*2){
%>
<tr>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=dname%></font></td>

<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><a href="<%="http://www.ncbi.nlm.nih.gov/projects/SNP/snp_ref.cgi?rs="+rst.getString(2)%>"><%=rst.getString(2)%></font></a></td>
<% if (rst.getString(3) == ""){
%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(2)+"-?"%></font></td>
<%}else {%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(2)+"-"+rst.getString(3)%></font></td>
<%}%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(4)%></font></td>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(5)%></font></td>
<% if (rst.getString(6) == null){
%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19">-</td>
<% } else {    
%>
<script type="text/javascript" > var s = <%=rst.getString(6)%>; </script>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><!a href= "#" ONMOUSEOVER= "javascript:loadContent(s)" ONMOUSEOUT="kill()"><%=rst.getString(6)%></font></a>
<!div id="prtCnt">
                    </td>

<%}%>
<% if (rst.getString(7) == null){
%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19">-</td>
<% } else {    
%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(7)%></font></td>
<%}%>
<% if (rst.getString(8) == null){
%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19">-</td>
<% } else {    
%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><a href="<%="http://omim.org/entry/"+rst.getString(8)%>"><%=rst.getString(8)%></font></a></td>
<%}%>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(9)%></font></td>
<td bgColor="#f2f2f2" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><a href="<%="http://www.ncbi.nlm.nih.gov/pubmed?term="+rst.getString(10)%>"><%=rst.getString(10)%></a></td>
</tr>
<%
}else{
%>
<tr>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=dname%></font></td>

<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><a href="<%="http://www.ncbi.nlm.nih.gov/projects/SNP/snp_ref.cgi?rs="+rst.getString(2)%>"><%=rst.getString(2)%></a></td>
<% if (rst.getString(3) == ""){
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(2)+"-?"%></font></td>
<%}else {%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(2)+"-"+rst.getString(3)%></font></td>
<%}%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(4)%></font></td>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(5)%></font></td>
<% if (rst.getString(6) == null){
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19">-</td>
<% } else {    
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(6)%></font></td>
<%}%>

<% if (rst.getString(7) == null){
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19">-</td>
<% } else {    
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(7)%></font></td>
<%}%>

<% if (rst.getString(8) == null){
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19">-</td>
<% } else {    
%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><a href="<%="http://omim.org/entry/"+rst.getString(8)%>"><%=rst.getString(8)%></font></a></td>
<%}%>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><%=rst.getString(9)%></font></td>
<td bgColor="#FFE4C4" vAlign="top" width="270" height="19"><font size ="3" face= "Times New Roman"><a href="<%="http://www.ncbi.nlm.nih.gov/pubmed?term="+rst.getString(10)%>"><%=rst.getString(10)%></a></font></td></tr>
</div>
<% }

i++;
}
}
rst.close();
stmt.close();
con.close();
}catch(Exception e){
System.out.println(e.getMessage());
}
%>

                                        </tbody>
                                        </table>
                                        </div>
                                        </td>
                                </tr>
                                </table>
                                <script type="text/javascript">
                                var sorter=new table.sorter("sorter");
                                sorter.init("sorter",1);
               </script>
       </body>
</html>