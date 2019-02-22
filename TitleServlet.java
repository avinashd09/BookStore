import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TitleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        PrintWriter out=response.getWriter();
        String subject=request.getParameter("sub");
        String sql="select bcode,title from books where subject=?";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,subject);
        ResultSet rs=ps.executeQuery();
         out.println("<html>");
         out.println("<body>");
         out.println("<h3>BOOKS-LIST</h3>");
         while(rs.next())
         {
         String s1=rs.getString(1);         
         String s2=rs.getString(2);
         out.println("<a href=DetailServlet?code="+s1+">");
         out.println(s2);
         out.println("</a>");
         out.println("<br>");
         }
         out.println("<a href=buyerpage.jsp><h3>BUYERPAGE</h3></a>");
         out.println("<a href=SubjectServlet><h3>SUBJECTPAGE</h3></a>");
         out.println("</body>");
         out.println("</html>");
         con.close();
        }
        catch(Exception e){}
    
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}