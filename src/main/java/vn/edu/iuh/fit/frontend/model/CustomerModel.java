package vn.edu.iuh.fit.frontend.model;

import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.services.CustomerServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class CustomerModel {
    private final CustomerServices services = new CustomerServices();

    public void insertCustomers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Customer customer = new Customer(name, email, phone, address);
        services.insertCust(customer);
        System.out.println(customer);
        resp.sendRedirect("customerListing.jsp");
    }

    public List<Customer> getAllCustomer() {
        return services.getAll();
    }
}
