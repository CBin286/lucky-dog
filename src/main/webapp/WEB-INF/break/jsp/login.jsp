<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/9
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录界面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <style>
        .login {
            margin-top: 15%;
            padding: 2% 0;
            width: 28%;
            background-color: #FFF;
        }

        .btn-center {
            text-center: center;
            margin: 0 auto;
        }

        .login-div{
            border: rgb(19, 39, 50) 2px solid !important;
            padding-left: 5%;
            border-radius: 50px;
        }

        /*input{
            background: none;
            border: none;
        }*/
    </style>

</head>
<body>

<div class="layui-main login">
    <h3 style="text-align: center">登录</h3>

    <form class="layui-form" action="${pageContext.request.contextPath}/admin/login"  method="post" enctype="application/x-www-form-urlencoded" >
        <div class="layui-form-item">
            <label class="layui-form-label">输入框</label>
            <div class="layui-input-inline">
                <input type="text" name="id" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码框</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">辅助文字</div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form','jquery'], function(){
        var form = layui.form
            ,$=layui.jquery;

        //监听提交
        // form.on('submit(formDemo)', function(data){
        //     console.log(data.field);
        //     $.ajax({
        //         url:'AdminLoginServlet?method=adminLogin',
        //         async:true,
        //         type:'post',
        //         data: data.field,
        //         success:function(msg){
        //
        //             console.log(msg);
        //
        //             window.location.href="jsp/manage.jsp"
        //
        //         },error:function (err) {
        //             console.log(err);
        //         }
        //     });
        //     return false;//阻止表单跳转
        // });
    });
</script>
</body>
</html>
