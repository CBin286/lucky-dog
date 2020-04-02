<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/10
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <meta charset="utf-8">
    <title>修改用户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">账号：</label>
        <div class="layui-input-inline">
            <input type="hidden" name="user_id" value="${user.user_id}" class="layui-input">
            <input type="text" name="user_account" value="${user.user_account}"  required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码：</label>
        <div class="layui-input-inline">
            <input type="password" name="user_pwd" value="${user.user_pwd}" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄：</label>
        <div class="layui-input-inline">
            <input type="text" name="user_age" value="${user.user_age}"  required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="user_sex" value="1" title="男" checked>
            <input type="radio" name="user_sex" value="2" title="女" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>


    //Demo
    layui.use(['form','jquery'], function(){
        var form = layui.form,
            $=layui.jquery;


        //监听提交
        form.on('submit(formDemo)', function(data){

            console.log(data.field);

            $.ajax({
                //相应路劲
                url:"${pageContext.request.contextPath}/admin/updateUser",
                //是否异步提交
                async:true,
                //请求类型
                type:"post",
                //数据名
                data:data.field,
                //数据类型:文本
                datatype:"text",
                //返回成功消息
                success:function (msg) {
                    layer.alert("修改成功",{icon:6},function (index) {
                        //回调
                        layer.close(layer.index);
                        parent.location.reload(); // 父页面刷新
                    });

                },
                //返回失败消息
                error:function () {
                }
            });

            return false;
        });
    });
</script>
</html>
