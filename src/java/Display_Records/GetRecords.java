package Display_Records;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class GetRecords extends HttpServlet {
    public void doPost (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
         resp.setContentType("text/html;charset=UTF-8");
         PrintWriter out = resp.getWriter();
         String snpid = req.getParameter("snpid");
              String disease = req.getParameter("disease");
              String searchId= null;
              if(disease != null)
                  searchId = "Disease Name";
              else if(snpid != null)
                  searchId = "SNP ID";
	try {
	      Class.forName("com.mysql.jdbc.Driver");
              String connectionUrl = "jdbc:mysql://192.168.7.4/variant_db?" +"user=manish&password=";
              Connection con = DriverManager.getConnection(connectionUrl);
              Statement stmt = con.createStatement ();
              String query= "SELECT * FROM `disease` WHERE name REGEXP '" + disease+"'";
              ResultSet rs = stmt.executeQuery (query);
              printResultSet ( resp, rs, searchId );
              rs.close();
              stmt.close();
              con.close();
        }  // end try

        catch (SQLException ex) {
            

	        resp.setContentType("text/html");
			
		while (ex != null) {  
                	out.println ("SQL Exception:  " + ex.getMessage ());
                	ex = ex.getNextException ();  
              }  // end while
        }  // end catch SQLException

        catch (java.lang.Exception ex) {
		resp.setContentType("text/html");	
		out.println ("Exception:  " + ex.getMessage ());
	  }
    }  // end doGet


    private void printResultSet ( HttpServletResponse resp, ResultSet rs, String searchId )
        throws SQLException  {
        try  {
		PrintWriter out = resp.getWriter();
	        out.println("<html>");
                out.println("<head>");
                out.println("<title> Search by "+ searchId + " </title>");

                out.println("</head>");
                out.println("<body style=margin: 0px; padding: 0px; font-family: 'Trebuchet MS',verdana;>");
                out.println("<form name=frm method=post action=GetRecords>");    
                out.println("<table width=100% style=height: 100%; cellpadding=10 cellspacing=0 border=1>");
                out.println("<tr>");
                out.println("<td colspan=2 style=height: 100px; bgcolor=#FFFFFF><h1>");
                
                out.println("<table>");
                out.println("<tr>");  
                out.println("<td><img src=https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcR23tjTQgDZBhY1dxm7a2eSo7c37jrHWbBvqdWN6OlM3j8kaGnx height=60 width=60 /></td>");
                out.println("<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=4 face=arial><b>Malaysian Genome Resouce Center</b></font> <br> <font size=4 color=#F88017>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Disease/Drug Search</font></td>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("</h1>");
                out.println("</td></tr>");
                out.println("<tr>");
                out.println("<td width=12% valign=top bgcolor=#FFE4C4>");
                out.println("<br>");
                out.println("<b>Search by:<br><A href=../snp.jsp>-SNP RSID</A> <br><A href=../disease.jsp>-Disease Name</A></b>");
                out.println("<br>");
                out.println("<table align =center>");
                out.println("</table> "); 
                out.println("</td>");
                out.println("<td width=88% valign=top bgcolor=#FFFFFF>");
                out.println("<font size=4 color=#F88017>Search By " + searchId + "</font>");
                out.println("<HR NOSHADE>");
                out.println("<br>");
                out.println("<b> Enter a " + searchId + "</b> ");
                out.println("</form>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=text name=disease size =30 /> <input type=submit value=Search />"); 
                out.println("<br><br><br><br><br>");
                out.println("<table cellpadding= 0 cellspacing= 0 border= 1> <div style=height:1px; width:1px; overflow:auto;>");
                out.println("<FORM NAME = form2 ACTION = ../Drug_Disease_Application.jsp METHOD=POST>");
                out.println("<thead><th bgcolor= black> <font color= #ffffff> Disease Name </font></th><th bgcolor=black> <INPUT TYPE= submit bgcolor=black name= Show_SNP Value= Show_SNP> </th></thead>");  
                    
                int numCols = rs.getMetaData().getColumnCount ();
                while ( rs.next() ) 
                {
                        out.println("<tr onMouseover= this.bgColor='#EEEEEE' onMouseout= this.bgColor='#FFFFFF'>");
                        out.print("<td>" + rs.getString(2) + "</td>" );
                        out.println("<td>");
                        out.println("<input TYPE=checkbox name=hash_values VALUE= " + rs.getString(1)+ "></td>");//<td>+ rs.getString(1));
                        out.println("</td>");
                        out.println("</tr>");
                } 
                
                out.println("</FORM>");
                out.println("</table>"); 
                out.println("</td></tr>");
                out.println("</table>");                
                out.println("</body>");
                out.println("</html>");
	        out.close();
	    }  // end try
        catch ( IOException except)  {
        }  // end catch
    }  // end returnHTML
}  // end GetRecords
