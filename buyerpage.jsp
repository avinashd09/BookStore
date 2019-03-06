<%
String id=(String)session.getAttribute("user");
%>
<html>
    <body>
        <h1>WELCOME <%=id%></h1>
        <hr>
        <pre>
        <a href="SubjectServlet">VIEW BOOK</a>
        <a href="">SEARCH BY TITLE</a>
        <a href="">SEARCH BY AUTHOR</a>
        <a href="DisplayCart">VIEW CART</a>
        <a href="searchbyid.jsp">SEARCH-BOOK-BY-ID</a>
        <a href="KillSession">LOG-OUT</a>
        </pre>
        <hr>
    </body>
</html>
