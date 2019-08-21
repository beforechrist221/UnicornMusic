<%--
  Created by IntelliJ IDEA.
  User: clj
  Date: 2019/3/8
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>注册</h1>
<form action="reg" method="post">
    <input id="email" type="text" name="email" placeholder="EMAIL"><span></span><br>
    <input type="text" name="username" placeholder="USERNAME"><br>
    <input type="password" name="password" placeholder="PASSWORD"><br>
    <input type="submit" value="REG"><br>
    <%--<%=request.getAttribute("message") == null ? "":request.getAttribute("message")%>--%>
</form>
<script src="assets/scripts/jquery-3.3.1.min.js"></script>
<script>
    $(function () {
        $('#email').on('blur',function () {
            var email = $(this).val();
            console.log('test');
            $.ajax({
                url:'checkEmail',
                type:'post',
                data:{'email':email},
                dataType:'json',
                success:function (data) {
                    // console.log(data.isEmailExisted);
                    // console.log(data.isEmailExisted ==true);
                    if(data.isEmailExisted){
                        $('span').text('Email is already existed').css('color','#f00')

                    }
                }
            });
        });
    });
</script>
</body>
</html>
