<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/2/16
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>

    <script>
        layui.use(['layer', 'form','jquery'], function(){
            var layer = layui.layer
                ,form = layui.form
                ,$=layui.jquery;


            //阻止表单提交。可在这里做操作
            form.on('submit(*)', function(data){
                console.log(data.elem);//被执行事件的元素DOM对象，一般为button对象
                console.log(data.form); //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}

                var userData=data.field;
                //var userMsg=JSON.stringify(userData);

                alert("userData");
                $.ajax({
                    //相应路劲
                    url:"${pageContext.request.contextPath}/user/toReg",
                    //是否异步提交
                    async:true,
                    //请求类型
                    type:"post",
                    //数据名
                    data:userData,
                    //数据类型:json
                    datatype:"json",
                    //返回成功消息
                    success:function (msg) {
                        // var msg =xhr.responseText;//读取返回的字符串
                        console.log(msg);
                        var a=msg.split("&")[0];
                        if("Error"===msg){
                            layer.msg('注册失败!!', {icon: 5});

                            //window.location.href="${pageContext.request.contextPath}/AdminLoginServlet?method=adminLogin";
                        }else if("Success"===msg) {
                            //页面跳转
                            layer.alert('注册成功，赶快前去登录吧', {icon: 6,yes:function () {
                                    window.location.href="login";
                                }});
                        }

                    },
                    //返回失败消息
                    error:function () {
                    }
                });

                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });


        //检查用户名
        function checkAccount(node) {
            var account=node.value;
            console.log(account);

            $.ajax({
                //相应路劲
                url:"${pageContext.request.contextPath}/user/testUserAccount",
                //是否异步提交
                async:true,
                //请求类型
                type:"post",
                //数据名
                data:{"user_account":account},
                //数据类型:文本
                datatype:"text",
                //返回成功消息
                success:function (msg) {
                    // var msg =xhr.responseText;//读取返回的字符串
                    console.log(msg);
                    var a=msg.split("&")[0];
                    if("Error"===msg){
                        layer.msg('用户名被占用啦!!', {icon: 5});
                        node.value="";
                        //window.location.href="${pageContext.request.contextPath}/AdminLoginServlet?method=adminLogin";
                    }else if("Success"===msg) {
                        //页面跳转

                    }

                },
                //返回失败消息
                error:function () {
                }
            })

        }
        //验证密码是否符合要求
        function checkPwd(node) {
            var p1 = node.value;
            var reg1=/^(?:\d+|[a-zA-Z0-9]+|[!@#$%^&*]+)$/;
            if(reg1.test(p1)){

            }else {
                layer.msg('这个密码不符合要求哦！', {icon: 5});
                node.value="";
            }
        }
        function checkPwd2(node){
            var p=$('#pwd').val();
            var p2 = node.value;
            if(p!=p2){
                layer.msg('密码都长的不一样啦！', {icon: 5});
                node.value="";
            }
        }
        //验证年龄
        function checkAge(node) {
            var ag = node.value;
            let reg=/^(?:[1-9][0-9]?|1[01][0-9]|120)$/;//年龄是1-120之间有效
            if(reg.test(ag)){

            }else {
                layer.msg('你的年龄真奇葩！', {icon: 5});
                node.value="";
            }

        }
        //验证手机
        function checkPhone(node) {
            var phone = node.value;
            var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (myreg.test(phone)) {

            } else {
                layer.msg('你的这手机号码不是地球的吧？', {icon: 5});
                node.value="";
            }
        }
    </script>


</head>
<body>
<form class="layui-form" >

    <div style="background: #ffffff;width: 500px;height: 635px;margin-left: 700px;margin-top: 200px;"  align="center">
        <div style="background: #ffffff;width: 500px;margin-top: 20px">
            <h1 style="padding-top: 50px;padding-bottom: 50px;">欢迎注册新用户</h1>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="text" name="user_account" lay-verify="required"   autocomplete="off" placeholder="请输入用户名" class="layui-input" onchange="checkAccount(this)">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="password" name="user_pwd" lay-verify="required"  id="pwd" placeholder="请输入密码" autocomplete="off" class="layui-input" onchange="checkPwd(this)">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="password" name="password1" lay-verify="required"  placeholder="请输确认密码" autocomplete="off" class="layui-input" onchange="checkPwd2(this)">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教育水平</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="text" name="user_education" lay-verify="required"  autocomplete="off" placeholder="请输入姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="text" name="user_age" lay-verify="required"  autocomplete="off" placeholder="请输入年龄" class="layui-input" onchange="checkAge(this)">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="text" name="user_phone" lay-verify="required"  autocomplete="off" placeholder="请输入手机号" class="layui-input" onchange="checkPhone(this)">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱地址</label>
            <div class="layui-input-block" style="margin-right: 30px;">
                <input type="text" name="user_emil" lay-verify="required"  autocomplete="off" placeholder="请输入通讯地址" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="user_sex" value="1" title="男" checked="">
                <input type="radio" name="user_sex" value="2" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 5px;">
                <button type="submit" class="layui-btn" lay-submit="*" lay-filter="*">立即注册</button>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/user/path/login">已有账号返回登陆>>></a>
    </div>

</form>
</body>
</html>
