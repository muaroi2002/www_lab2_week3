<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Employee</title>
</head>
<body>
<h1>INERT NEW EMPLOYEE</h1>
<form action="controls?action=insertEmp" method="post">
    Full Name: <input name="name"/><br/>
    Date of birth: <input name="dob"/><br/>
    Email: <input name="email"/><br/>
    Phone: <input name="phone"/><br/>
    Address: <textarea name="address"></textarea><br/>
    <input type="submit" value="Insert new Employee"/><br/>
    <input type="reset" value="Clear"/><br/>
</form>
</body>
</html>
