<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
    <form action="controls?action=insertCust" method="post">
        Name: <input name="name"><br/>
        Email: <input name="email"><br/>
        Phone: <input name="phone"><br/>
        Addess: <input name="address"><br/>
        <input type="submit" value="Insert"><br/>
        <input type="reset" value="Clear"><br/>
    </form>
</body>
</html>
