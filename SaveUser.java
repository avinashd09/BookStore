import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveUser extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
    PrintWriter out=resp.getWriter();
    String username=req.getParameter("username");
    String userid=req.getParameter("userid");
    String password=req.getParameter("password");
    String mobile=req.getParameter("mobile");
    String address=req.getParameter("address");
    String email=req.getParameter("email");
    
    String sql="insert into users value(?,?,?,?,?,?)";
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
    PreparedStatement ps=con.prepareStatement(sql);
    ps.setString(1, username);
    ps.setString(2, userid);
    ps.setString(3, password);
    ps.setString(4, mobile);
    ps.setString(5, address);
    ps.setString(6, email);    
    ps.executeUpdate();
    }
    catch(Exception e)
    {
        out.println(e);
    }
      out.println("Registration completed");
    }  
    
}