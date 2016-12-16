package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class disease_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Search by rsid </title>\n");
      out.write("</head>\n");
      out.write("<body style=\"margin: 0px; padding: 0px; font-family: 'Trebuchet MS',verdana;\">\n");
      out.write("    \n");
      out.write("<form name=\"frm\" method=\"post\" action=\"Display_Records/GetRecords\">    \n");
      out.write("<table width=\"100%\" style=\"height: 100%;\" cellpadding=\"10\" cellspacing=\"0\" border=\"1\">\n");
      out.write("<tr>\n");
      out.write("\n");
      out.write("<!-- ============ HEADER SECTION ============== -->\n");
      out.write("<td colspan=\"2\" style=\"height: 100px;\" bgcolor=\"#FFFFFF\"><h1>\n");
      out.write("   <table>\n");
      out.write("    <tr> <td>  \n");
      out.write("   <img src=\"https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcR23tjTQgDZBhY1dxm7a2eSo7c37jrHWbBvqdWN6OlM3j8kaGnx\" height=\"60\" width=\"60\" />\n");
      out.write("   </td> <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"4\" face=\"arial\"><b>Malaysian Genome Resouce Center</b></font> <br> <font size=\"4\" color=#F88017>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Disease/Drug Search</font></td></tr></table>\n");
      out.write("</h1>\n");
      out.write("</td></tr>\n");
      out.write("<tr>\n");
      out.write("    \n");
      out.write("<!-- ============ LEFT COLUMN (MENU) ============== -->\n");
      out.write("<td width=\"12%\" valign=\"top\" bgcolor=\"#FFE4C4\">\n");
      out.write("<br>\n");
      out.write("<b>Search by:\n");
      out.write("    <br>\n");
      out.write("    <A href=\"snp.jsp\">-SNP RSID</A> \n");
      out.write("    <br>\n");
      out.write("    <A href=\"disease.jsp\">-Disease Name</A>    \n");
      out.write("</b>\n");
      out.write("<br>\n");
      out.write("\n");
      out.write("<table align =\"center\">\n");
      out.write("</table>  \n");
      out.write("</td>\n");
      out.write("\n");
      out.write("<!-- ============ RIGHT COLUMN (CONTENT) ============== -->\n");
      out.write("<td width=\"88%\" valign=\"top\" bgcolor=\"#FFFFFF\">\n");
      out.write("    <font size=\"4\" color=#F88017>Search By Disease Name</font>\n");
      out.write("<HR NOSHADE>\n");
      out.write("<br>\n");
      out.write("<b> Enter a Disease Name </b> \n");
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"text\" name=\"disease\" size =\"30\" /> <input type=\"submit\" value=\"Search\" /> \n");
      out.write("</td></tr>\n");
      out.write("</table>  \n");
      out.write("</form>\n");
      out.write("</body>\n");
      out.write("<html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
