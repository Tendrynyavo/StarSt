<%@ page import="stock.Stock" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.ovoc01.dao.utilities.Utilities" %>
<%@ page import="element.Product" %>
<%@ page import="element.Composition" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Connection c = Utilities.createConnection();
    Vector<Product> compositionVector = new Product().select(c);
%>
<html>
<head>
    <link rel="stylesheet" href="./asset/css/ajout.css">
    <title>Title</title>
</head>
<body>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<div class="login-root">
    <div class="box-root flex-flex flex-direction--column" style="min-height: 100vh;flex-grow: 1;">
        <div class="loginbackground box-background--white padding-top--64">
            <div class="loginbackground-gridContainer">
                <div class="box-root flex-flex" style="grid-area: top / start / 8 / end;">
                    <div class="box-root"
                         style="background-image: linear-gradient(white 0%, rgb(247, 250, 252) 33%); flex-grow: 1;">
                    </div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 4 / 2 / auto / 5;">
                    <div class="box-root box-divider--light-all-2 animationLeftRight tans3s"
                         style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 6 / start / auto / 2;">
                    <div class="box-root box-background--blue800" style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 7 / start / auto / 4;">
                    <div class="box-root box-background--blue animationLeftRight" style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 8 / 4 / auto / 6;">
                    <div class="box-root box-background--gray100 animationLeftRight tans3s" style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 2 / 15 / auto / end;">
                    <div class="box-root box-background--cyan200 animationRightLeft tans4s" style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 3 / 14 / auto / end;">
                    <div class="box-root box-background--blue animationRightLeft" style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 4 / 17 / auto / 20;">
                    <div class="box-root box-background--gray100 animationRightLeft tans4s" style="flex-grow: 1;"></div>
                </div>
                <div class="box-root flex-flex" style="grid-area: 5 / 14 / auto / 17;">
                    <div class="box-root box-divider--light-all-2 animationRightLeft tans3s"
                         style="flex-grow: 1;"></div>
                </div>
            </div>
        </div>
        <div class="box-root padding-top--24 flex-flex flex-direction--column" style="flex-grow: 1; z-index: 9;">
            <div class="box-root padding-top--48 padding-bottom--24 flex-flex flex-justifyContent--center">
                <h1><a href="http://blog.stackfindover.com/" rel="dofollow">Insertion Produit</a></h1>
            </div>
            <div class="formbg-outer">
                <div class="formbg">
                    <div class="formbg-inner padding-horizontal--48">
                        <span class="padding-bottom--15">Veuillez insérer le produit</span>
                        <form action="create" method="post" id="stripe-login">
                            <div class="field padding-bottom--24">
                                <div class="grid--50-50">
                                    <label >Produits</label>
                                </div>
                                <select class="sel" name="comp">
                                    <% for (Product comp : compositionVector) {
                                        Composition temp = comp.fkToObject(c,0);%>
                                    <option value="<%=comp.getReference()%>"><%=temp.getName()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="field padding-bottom--24">
                                <div class="grid--50-50">
                                    <label for="quantity">Quantity</label>
                                </div>
                                <input type="number" name="quantity" required>
                            </div>
                            <% if(request.getParameter("error")!=null){ %>
                                <h2> Tsy ampy ny matière première</h2>
                            <% }%>
                            <div class="field padding-bottom--24">
                                <input type="submit" name="submit" value="Continue" class="sub">
                            </div>
                            <p class="error"><h1></h1></p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
</body>
</html>
