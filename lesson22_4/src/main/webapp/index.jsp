
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>转账</title>
</head>
<body>
<form action="transfer" method="get">
    转出账户:<input type="text" name="userFrom"> <br>
    转入账户:<input type="text" name="userTo"><br>
    转账金额:<input type="text" name="money"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
