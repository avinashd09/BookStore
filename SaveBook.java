import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveBook extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
             {
      
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String bcode=request.getParameter("bcode");
            String title=request.getParameter("title");
            String author=request.getParameter("author");
            String subject=request.getParameter("subject");
            String price=request.getParameter("price");
            String sql="insert into books value(?,?,?,?,?)";
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
            PreparedStatement ps=con.prepareStatement(sql);  
            ps.setInt(1,Integer.parseInt(bcode));
            ps.setString(2,title);
            ps.setString(3,author);
            ps.setString(4,subject);
            ps.setInt(5,Integer.parseInt(price));
            ps.executeUpdate();
            con.close();
            }catch(Exception e)
            {
                out.println(e);
            }
            out.println("BOOK ADDED SUCCESSFULLY");
            out.println("<pre>");
            out.println("<a href=bookentry.jsp><h3>ADD BOOKS</h3></a>");
            out.println("<a href=adminpage.jsp><h3>HOME</h3></a>");
            out.println("</pre>");
        
      
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
