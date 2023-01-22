package controller;

import com.ovoc01.dao.utilities.Utilities;
import element.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stock.Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

@WebServlet(name = "create", value = "/create")
public class Create extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        Connection c = Utilities.createConnection();
        String idProduit = request.getParameter("comp");
        Double quantity = Double.parseDouble(request.getParameter("quantity"));
        try{
            Product product = new Product();product.setReference(idProduit);
            Vector<Product> productVector = product.select(c);
            if(productVector.get(0).canBeCreated(c,quantity)){
                productVector.get(0).create(c,quantity);
                c.commit();
                response.sendRedirect("index.jsp");
            }
            response.sendRedirect("create.jsp?error=1");
        }catch (Exception e){
            out.println(e);
            try {
                c.rollback();
            } catch (SQLException ex) {
                out.println(ex);
            }
        }finally {
            try {
                c.close();
            } catch (SQLException e) {
                out.println(e);
            }
        }
    }
}
