import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class VerifyUser1 extends HttpServlet {
    
   Connection con;PreparedStatement ps;
      
   @Override
   public void init()
   {
       String sql="select username,userid,password from users where userid=? and password=?";
       try{
   Class.forName("com.mysql.jdbc.Driver");
   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata?autoReconnect=true&useSSL=false", "root", "root");
   ps=con.prepareStatement(sql);
       }catch(Exception e){}
   }
   
   @Override
   public void destroy()
   {
       try 
       {
           con.close();
       } catch (SQLException ex) {     
       }
   }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
{
       PrintWriter out=resp.getWriter();   
       String userid=req.getParameter("userid");
       String pw=req.getParameter("password");
       String utype=req.getParameter("usertype");
       
       if(utype.equals("ADMIN"))
       {
       if(userid.equals("admin") && pw.equals("indore"))
       {
           resp.sendRedirect("adminpage.jsp");
       }else
       {
       out.println("INVALID ADMIN ACCOUNT");
       }
       }else
       {
       try
       {
       ps.setString(1,userid);
       ps.setString(2,pw);
       ResultSet rs= ps.executeQuery();
       boolean found=rs.next();
       if(found)
       {
           
       HttpSession Session=req.getSession();   
       Session.setAttribute("user", userid);
       resp.sendRedirect("buyerpage.jsp");
       }else
       {
       out.println("INVALID BUYER ACCOUNT");
       }
       }
         catch (SQLException ex) 
         {  
         out.println(ex);  
         }
       
       }
}
}