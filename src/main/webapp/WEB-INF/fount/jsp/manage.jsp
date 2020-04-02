<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/31
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>用户主页面</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">用户主页</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="javascript:void(0);" title="${pageContext.request.contextPath}/user/path/findFile" onclick="changeUrl(this)">搜索文档</a></li>
            <li class="layui-nav-item"><a href="javascript:void(0);" title="${pageContext.request.contextPath}/user/path/upload" onclick="changeUrl(this)">上传文档</a></li>
<%--            <li class="layui-nav-item"><a href="javascript:void(0);" title="${pageContext.request.contextPath}/user/path/download" onclick="changeUrl(this)">下载文档</a></li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${roleName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:void(0);" title="${pageContext.request.contextPath}/user/path/userMsg" onclick="changeUrl(this)">我的信息</a></dd>
                    <dd><a href="javascript:void(0);" title="${pageContext.request.contextPath}/user/path/userScore" onclick="changeUrl(this)">我的积分</a></dd>
                    <dd><a href="javascript:void(0);" title="${pageContext.request.contextPath}/user/path/userFile" onclick="changeUrl(this)">我的文档</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:void(0);" title="">退出登录</a></li>
        </ul>
    </div>


        <!-- 内容主体区域 -->
        <iframe id="ifram_div_iframe" style="width: 100%;height: 100%;" name="ifram_div_ifram" src=""></iframe>


    <div class="layui-footer" style="left: 0px;">
        <!-- 底部固定区域 -->
        © cykjgroup.com
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });

    function changeUrl(node) {
        //获取跳转路径
        console.log(node.title);
        document.getElementById("ifram_div_iframe").src=node.title;
    }
</script>
</body>
</html>
