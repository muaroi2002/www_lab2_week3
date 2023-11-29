package vn.edu.iuh.fit.frontend.controllers;

import vn.edu.iuh.fit.frontend.model.CustomerModel;
import vn.edu.iuh.fit.frontend.model.EmployeeModel;
import vn.edu.iuh.fit.frontend.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controls")
public class ServletController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null) {
                String action = actionObject.toString();
                if (action.equals("insertEmp")) {
                    EmployeeModel empModel = new EmployeeModel();
                    empModel.insertEmployees(req, resp);
                } else if (action.equals("insertCust")) {
                    CustomerModel customerModel = new CustomerModel();
                    customerModel.insertCustomers(req, resp);
                } else if (action.equals("insert_products")) {
                    ProductModel pm = new ProductModel();
                    pm.insertProducts(req, resp);
                }

            } else {
                resp.sendRedirect("customerListing.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null) {
                String action = actionObject.toString();
                if (action.equals("cust_list")) {
                    resp.sendRedirect("customerListing.jsp");
                } else if (action.equals("delete_product")) {
                    ProductModel pm = new ProductModel();
                    pm.deleteProducts(req, resp);
                }
            } else {
                resp.sendRedirect("Empoyee.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
