package vn.edu.iuh.fit.frontend.model;

import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.services.ProductServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ProductModel {
    private final ProductServices services =new ProductServices();
    public void insertProducts(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String unit = req.getParameter("unit");
        String manu = req.getParameter("menu");
        String status = req.getParameter("status");

        Product product =new Product(name,desc,unit,manu, ProductStatus.valueOf(status));
        services.insert(product);
        resp.sendRedirect("products.jsp");
    }
    public void deleteProducts(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        long id =Long.parseLong(req.getParameter("id"));
        services.updateStatus(id, ProductStatus.IN_ACTIVE);
        resp.sendRedirect("products.jsp");
    }
}
