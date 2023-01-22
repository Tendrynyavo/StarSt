<%@ page import="stock.Stock" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.ovoc01.dao.utilities.Utilities" %>
<%@ page import="element.Product" %>
<%@ page import="element.Composition" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="stock.Transaction" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Connection c = Utilities.createConnection();
    String id = request.getParameter("id");
    Transaction transaction = new Transaction();transaction.setIdProduit(id);
    Vector<Transaction> transactionVector = transaction.select(c);
    //DecimalFormat df = new DecimalFormat("#.##");
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./asset/css/style.css">
    <link rel="stylesheet" href="./asset/css/details.css">
    <title>JSP - Hello World</title>
</head>
<table class="styled-table">
    <thead>
    <tr>
        <th>Date</th>
        <th>Produit</th>
        <th>Quantité</th>
        <th>Prix Unitaire</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <% for (Transaction stock : transactionVector) {
        String type = "";
        Composition temp = stock.fkToObject(c, 0);
        if(stock.getIsEntering())type="Entrée";
        else type="Sortie";
    %>
    <tr>
        <td><%=stock.getDateTransaction()%>
        </td>
        <td><%=temp.getName()%>
        </td>
        <td><%=(stock.getQuantity())%>
        </td>
        <td><%=(stock.getPriceUnitaire()) %></td>
        <td><%=type%></td>
    </tr>

    <% } %>
    <!-- and so on... -->
    </tbody>
</table>
<style>
    a{
        text-decoration: none;
    }
</style>
</body>
</html>