<html>
<head></head>
<body>
    <%
        Integer acumulado = (Integer) session.getAttribute("acumulado");
        String num = request.getParameter("num");


        int iAcumulado = 0;

        if (acumulado != null) {
            iAcumulado = acumulado.intValue();
        }

        int iNum = 0;

        if (num != null) {
            iNum = Integer.parseInt(num);;
        }

        iAcumulado += iNum;

        session.setAttribute("acumulado", new Integer(iAcumulado));
    %>
    Llevas acumulado: <%=iAcumulado%>
    <br/>
    <form method='post'>
        N&uacute;mero a sumar: <input name='num'/><br/>
        <input type='submit' value='Enviar'/>
    </form>
</body>
</html>
