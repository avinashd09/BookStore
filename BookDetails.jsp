<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%!
int highdis=20;
int lowdis=10;
int getDis(int pr)
{
int dis=0;
if(pr<500)
{
dis=(pr*lowdis)/100;
}else
{
dis=(pr*highdis)/100;
}
return dis;
}

%>




<%
int code=Integer.parseInt(request.getParameter("code"));
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
String sql="select * from books where bcode=?";
PreparedStatement ps=con.prepareStatement(sql);
ps.setInt(1, code);
ResultSet rs=ps.executeQuery();
rs.next();
String bcode=rs.getString(1);
String title=rs.getString(2);
String author=rs.getString(3);
String subject=rs.getString(4);
String price=rs.getString(5);
%>
<h3>BOOKS DETAILS</h3>
<html>
    <body>
        <h3>BOOKS DETAILS</h3>
        
        <table border="1" width="1">
            <tbody>
                <tr>
                    <td>BCode</td>
                    <td><%out.println(bcode);%></td>
                </tr>
                <tr>
                    <td>TITLE</td>
                    <td><%out.println(title);%></td>
                </tr>
                <tr>
                    <td>AUTHOR</td>
                    <td><%out.println(author);%></td>
                </tr>
                <tr>
                    <td>SUBJECT</td>
                    <td><%out.println(subject);%></td>
                </tr>
                <tr>
                    <td>PRICE</td>
                    <td><%out.println(price);%></td>
                </tr>
                 <tr>
                    <td>DISCOUNT</td>
                    <td><%=getDis(Integer.parseInt(price))%></td>
                </tr>
            </tbody>
        </table>
                <a href="buyerpage.jsp">BUYER-PAGE</a>
    </body>
</html>
<%
con.close();
%>