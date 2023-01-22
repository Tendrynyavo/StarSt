<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./asset/css/create.css">
    <link rel="stylesheet" href="./asset/css/stock.css">
    <title>JSP - Hello World</title>
</head>
<body>
<div>
    <a href="create.jsp"><button>
  <span>
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
        <path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M11 11V5h2v6h6v2h-6v6h-2v-6H5v-2z"></path>
    </svg> Create product
  </span>
        <a href="add.jsp"><button>
  <span>
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
        <path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M11 11V5h2v6h6v2h-6v6h-2v-6H5v-2z"></path>
    </svg> Add Product
  </span>
    </button></a>
    <a href="stock.jsp">
        <button class="cssbuttons-io-button"> Voir stock
            <div class="icon">
                <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M0 0h24v24H0z" fill="none"></path><path d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z" fill="currentColor"></path></svg>
            </div>
        </button>
    </a>
</div>
    <style>
        div{
            margin-left: 200px;
            width: 700px;
            display: flex;
            justify-content: space-around;
        }
    </style>
</body>
</html>