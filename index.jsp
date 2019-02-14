<html>
    <body>
        <h2>BOOK STORE</h2>
        <h4>LOGIN</h4>
        
        <form action="VerifyUser" method="post">
            <pre>   
        UserId    <input type="text" name="UserId"/>
       
        Password  <input type="password" name="password"/>
        
        UserType  <select name="usertype">
                  
                  <option>ADMIN</option>
                  <option>BUYER</option>
                  </select>
            </pre>
                  <input type="submit" value="Login"/>
                  
                  <input type="reset" value="Reset"/>       
                          
                   
        </form>
       <a href="registration.jsp">NEW USER REGISTRATION</a>
    </body>
</html>
