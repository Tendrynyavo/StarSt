package controller;

import com.ovoc01.dao.utilities.Utilities;
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

@WebServlet(name = "ajout", value = "/ajout")
public class Ajout extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        Connection c = Utilities.createConnection();
        String idProduit = request.getParameter("comp");
        Double quantity = Double.parseDouble(request.getParameter("quantity"));
        Double price = Double.parseDouble(request.getParameter("prix"));
        String date = (request.getParameter("date"));
        try{
            Transaction transaction = new Transaction(idProduit,true,date,quantity,price);
            transaction.manageStock(c);
            response.sendRedirect("add.jsp");
            c.commit();
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
