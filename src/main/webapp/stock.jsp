<%@ page import="stock.Stock" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.ovoc01.dao.utilities.Utilities" %>
<%@ page import="element.Product" %>
<%@ page import="element.Composition" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Connection c = Utilities.createConnection();
    Vector<Stock> stockVector = new Stock().select(c);
    double prixTotal = new Stock().stockTotal(c);
    DecimalFormat df = new DecimalFormat("#.##");
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
        <th>Produit</th>
        <th>Prix Unitaire(Ar)</th>
        <th>Quantité</th>
        <th>Montant(Ar)</th>
        <th>Voir Détails</th>
    </tr>
    </thead>
    <tbody>
    <% for (Stock stock : stockVector) {
        Composition temp = stock.fkToObject(c, 0);%>
    <tr>
        <td><%=temp.getName()%>
        </td>
        <td><%=df.format(stock.getPriceUnitaire())%>
        </td>
        <td><%=df.format(stock.getQuantity())%>
        </td>
        <td><%=df.format(stock.getQuantity() * stock.getPriceUnitaire()) %>
        </td>
        <td>
            <a href="details.jsp?id=<%=stock.getIdProduit()%>">
                <button>
                    Détails
                    <div class="arrow-wrapper">
                        <div class="arrow"></div>
                    </div>
                </button>
            </a>
        </td>
    </tr>

    <% } %>
    <!-- and so on... -->
    </tbody>
</table>
<h1>Total:<%= df.format(prixTotal)%>Ar</h1>
<style>
    a{
        text-decoration: none;
    }
</style>
</body>
</html>