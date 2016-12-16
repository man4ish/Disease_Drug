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

public class GetSNPRecords extends HttpServlet {
    public void doPost (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
         resp.setContentType("text/html;charset=UTF-8");
         PrintWriter out = resp.getWriter();
         String[] hash_value = req.getParameterValues("hash_values");
        
	 try {
	      Class.forName("com.mysql.jdbc.Driver");
              String connectionUrl = "jdbc:mysql://192.168.7.4/variant_db?" +"user=manish&password=";
              Connection con = DriverManager.getConnection(connectionUrl);
              Statement stmt = con.createStatement ();
              printResultSet1 ( resp );
              for (int i =0; i <hash_value.length; i++)
              {
                  //String diseasequery = "select disease.name from disease where name_hash = "+ hash_value[i] + ";"; 
                 String query = "select di_snp.risk_snp, di_snp.snp_id, di_snp.risk_allele , di_snp.allele_freq, di_snp.p_value, gene.entrez_symbol,gene.gene_type, gene.omim_id, pubmed.journal, pubmed.pm_id from di_snp join citation on citation.cite_id = di_snp.cite_id left outer JOIN pubmed on citation.pm_id = pubmed.pm_id left outer JOIN snp on snp.snp_id = di_snp.snp_id left outer join gene on  snp.entrez_id = gene.entrez_id WHERE di_snp.name_hash = " + hash_value[i] + " ORDER BY p_value"; 
                 //String query= "SELECT * FROM `di_snp` WHERE name_hash = " + hash_value[i] + " ORDER BY p_value";  // Need to implement for multiple values
                 ResultSet rs = stmt.executeQuery(query);
                                
                 //ResultSet grs = stmt.executeQuery (diseasequery);
                 String gname = getgenename ( hash_value[i] );
                 //grs.close();
                 printResultSet2 ( resp, rs, gname);
                 rs.close();
              }
              printResultSet3 ( resp);
             
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


    private void printResultSet1 ( HttpServletResponse resp)
        throws SQLException, IOException  {

		PrintWriter out = resp.getWriter();
	        out.println("<html>");
                out.println("<head>");
                out.println("<meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″ />");

                out.println("<title> Search by </title>");

                out.println("<link rel= stylesheet href= style.css />");
                out.println("<script type= text/javascript src= script.js ></script>");
                out.println("</head>");
                out.println("<body style=margin: 0px; padding: 0px; font-family: 'Trebuchet MS',verdana;>");
               //out.println("<form name=frm method=post action=GetRecords>");    
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
                out.println("<br>");out.println("<br>");out.println("<br>");out.println("<br>");
                
                
                
                
                //out.println("<font size=4 color=#FF9900>Search By " + searchId + "</font>");
                out.println("<HR NOSHADE>");
                out.println("<br>");
                //out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> Enter a " + searchId + "</b> ");
                //out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=text name=disease size =30 /> <input type=submit value=Search />"); 
                //out.println("<br><br><br><br><br>");
                out.println("<table cellpadding= 0 cellspacing= 0 border= 1><div style=height:1px; width:1px; overflow:auto;>");

                out.println("<thead><th bgcolor= #D3D3D3>Disease Name</th><th bgcolor= #D3D3D3>SNP Id</th><th bgcolor= #D3D3D3>Risk SNP</th><th bgcolor= #D3D3D3>Allele Freq</th><th bgcolor= #D3D3D3>p Value</th><th bgcolor= #D3D3D3>Entrez Symbol</th><th bgcolor= #D3D3D3>Gene Type</th><th bgcolor= #D3D3D3>OMIM Id</th><th bgcolor= #D3D3D3>Journal</th><th bgcolor= #D3D3D3>Pubmed Id</th></thead>");           
        }
        private void printResultSet2 ( HttpServletResponse resp, ResultSet rs, String gname )
        throws SQLException  {
        try  {
                PrintWriter out = resp.getWriter();
                int numCols = rs.getMetaData().getColumnCount ();
                while ( rs.next() ) 
                {
                        out.println("<tr onMouseover= this.bgColor='#EEEEEE' onMouseout= this.bgColor='#FFFFFF'>");
                        //out.print("<td>" + genename + "</td>" );
                         out.print("<td>"+gname+"</td>" );
                        out.print("<td>" + "<a href=" + "http://www.ncbi.nlm.nih.gov/projects/SNP/snp_ref.cgi?rs="+rs.getString(1)+">" + rs.getString(1) +"</td>" ); 
                        
                        if (rs.getString(3) != "")
                           out.print("<td>" + rs.getString(2)+"-"+rs.getString(3) + "</td>" );
                        else 
                           out.print("<td>"+rs.getString(2)+"-?</td>" );
                        
                        if(rs.getString(4) == null)
                            out.print("<td>-</td>" );
                        else
                            out.print("<td>" + rs.getString(4) + "</td>" );
                        
                        for (int i=5; i<=numCols-5; i++) 
                        {
                                out.print("<td>" + rs.getString(i) + "</td>" );                             
                        } 
                        // end for
                        //out.println("<FORM NAME = form2 ACTION = GetPublication METHOD=POST>");
                        //out.print("<td>" + "<INPUT TYPE= submit size = 20 name= cite_id Value= "+ rs.getString(7) +">" + "</td>" );
                        //out.println("</FORM>");
                        if(rs.getString(6) == null)
                            out.print("<td>-</td>" );
                        else
                            out.print("<td>" + rs.getString(6) + "</td>" );
                        if(rs.getString(7) == null)
                            out.print("<td>-</td>" );
                        else
                            out.print("<td>" + rs.getString(7) + "</td>" );
                        
                        if(rs.getString(8) == null)
                            out.print("<td>-</td>" );
                        else
                            out.print("<td>" + "<a href=" + "http://omim.org/entry/"+rs.getString(8)+">" + rs.getString(8) + "</a></td>" );
                        out.print("<td>" + rs.getString(9) + "</td>" );
                        out.print("<td>" + "<a href=" + "http://www.ncbi.nlm.nih.gov/pubmed?term="+rs.getString(10)+">" + rs.getString(10) + "</a></td>" );
                        out.println("</tr>");
                }  
	    }  // end try
        catch ( IOException except)  {
        }  // end catch
    }  // end returnHTML
        private void printResultSet3 ( HttpServletResponse resp )
        throws SQLException, IOException  {
            PrintWriter out = resp.getWriter();
            out.println("</table>"); 
            out.println("</td></tr>");
            out.println("</table>");  
            out.println("</form>");
            out.println("<script type= text/javascript >");
            out.println("var sorter=new table.sorter( sorter)");
            out.println("sorter.init(sorter,1");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
	    out.close();
        }

    private String getgenename(String hash_value) throws SQLException, ClassNotFoundException 
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
}  // end GetRecords


