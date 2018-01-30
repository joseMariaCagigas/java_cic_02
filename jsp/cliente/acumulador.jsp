<html>
<head></head>
<body>
    <%
        String acumulado = request.getParameter("acumulado");
        String num = request.getParameter("num");

        int iAcumulado = 0;

        if (acumulado != null) {
            iAcumulado = Integer.parseInt(acumulado);;
        }

        int iNum = 0;

        if (num != null) {
            iNum = Integer.parseInt(num);;
        }

        iAcumulado += iNum;
    %>
    Llevas acumulado: <%=iAcumulado%>
    <br/>
    <form method='post'>
        N&uacute;mero a sumar: <input name='num'/><br/>
        <input type='hidden' name='acumulado' value='<%=iAcumulado%>'/>
        <input type='submit' value='Enviar'/>
    </form>
</body>
</html>
