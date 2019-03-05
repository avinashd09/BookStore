import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubjectServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String userid=(String) session.getAttribute("user");
        if(userid==null)
                
                {
                response.sendRedirect("index.jsp");
                }
        
        int items=0;
        HashSet<String> set=(HashSet<String>) session.getAttribute("cart");
        if(set!=null){
        items=set.size();
        }
        
        
        String subval="ALL";
        Cookie c[]=request.getCookies();
        for(Cookie ck:c)
        {
        String name=ck.getName();
        if(name.equals("choice"))
        {
        subval=ck.getValue();
        break;
        }
        }
       try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
        String sql="select distinct subject from books"; 
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        out.println("<html>");
        out.println("<body>");
        out.println("<marquee><h3>OFFERS ON "+subval+" BOOKS</h3></marquee>");
        out.println("<h3>WELCOME "+userid+"</h3>");
        
        out.println("<h3>SUBJECT-LIST</h3>");
        while(rs.next())
        {
        String s=rs.getString(1);
        out.println("<a href=TitleServlet?sub="+s+">");
        out.println(s);
        out.println("</a>");
        out.println("<br>");
        }
         out.println("<a href=buyerpage.jsp><h3>HOME</h3></a>");
        out.println("</body>");
        out.println("</html>");
        con.close();
       }
      catch(Exception e)
        {
      out.println(e);
        }
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
