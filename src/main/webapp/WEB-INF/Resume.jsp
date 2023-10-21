<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Résumé de la commande</title>
</head>
<body>

    <table>
        <tr>
        	<th>id du produit</th>
            <th>Code du produit</th>
            <th>Libelle du produit</th>
            <th>Description du produit</th>
        </tr>

        <c:forEach items="${orderedProducts}" var="product">
            <tr>
            	<td>${product.id}</td>
                <td>${product.code}</td>
                <td>${product.libelle}</td>
                <td>${product.description}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

