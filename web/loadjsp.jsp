<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ page language="java" import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%
 String q =request.getParameter("q");
 Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://192.168.7.4/variant_db?" +"user=manish&password=";
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement ();
            String query = "select * from gene where entrez_symbol = '"+ q + "';"; 
            ResultSet rs = stmt.executeQuery(query);
            int numCols = rs.getMetaData().getColumnCount();
            while(rs.next())
            {
                for (int i =1; i < numCols; i++)
                    out.println(rs.getString(i)); 
            }
            rs.close();
            stmt.close();
            con.close();
 java.util.Date dt=new java.util.Date();
 //out.print(q+dt);
%>