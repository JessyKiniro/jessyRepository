<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/9
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>转账</title>
</head>
<body>
<form action="servlet1" method="get">
  转出账户:<input type="text" name="userFrom"> <br>
  转入账户:<input type="text" name="userTo"><br>
  转账金额:<input type="text" name="money"><br>
  <input type="submit" value="提交">
</form>
</body>
</html>
