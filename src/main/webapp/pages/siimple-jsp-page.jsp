<%--
  User: giophub
  Date: 3/23/2018
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script type="text/javascript" language="JavaScript">
    window.onload = function () {
        var sessionid = "<%= session.getId() %>";
        alert("sessionid = " + sessionid);
    }
</script>

<h1>The request session with request.getSession().getId(): </h1>
<%= request.getSession().getId() %>

<br><br>

<h1>The session with session.getId(): </h1>
<%= session.getId() %>

</body>
</html>
