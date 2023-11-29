<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.backend.services.CustomerServices" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<%
    CustomerServices services = new CustomerServices();
    List<Customer> lst = services.getAll();
%>
<table width="70%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Email</th>
        <th><a href="insertCustomer.jsp">Insert</a></th>
    </tr>
    <% for (Customer customer : lst) {%>
    <tr>
        <td><%=customer.getId()%>
        </td>
        <td><%=customer.getName()%>
        </td>
        <td><%=customer.getAddress()%>
        </td>
        <td><%=customer.getPhone()%>
        </td>
        <td><%=customer.getEmail()%>
        </td>
        <td>
            <a href="">Update</a>
            <a href="">Delete</a>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
