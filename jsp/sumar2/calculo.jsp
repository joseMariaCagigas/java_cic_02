html>
<head></head>
<body>
    <%
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        if (num1 == null && num2 == null) {

    %>
    <form method='get'>
        N&uacute;mero 1: <input name='num1'/><br/>
        N&uacute;mero 2: <input name='num2'/><br/>
        <input type='submit' value='Enviar'/>
    </form>

    <%
        } else {


            out.write("Valores recibidos: " + num1 + " - " + num2);
        
            out.write("<br/>Resultado: " + (Integer.valueOf(num1) + Integer.valueOf(num2)));
        }
    %>
</body>
</html>
