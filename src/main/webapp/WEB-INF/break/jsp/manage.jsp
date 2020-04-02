<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/9
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理界面</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">后台管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
<%--            <li class="layui-nav-item"><a href="">控制台</a></li>--%>
<%--            <li class="layui-nav-item"><a href="">商品管理</a></li>--%>
<%--            <li class="layui-nav-item"><a href="">用户</a></li>--%>
<%--            <li class="layui-nav-item">--%>
<%--                <a href="javascript:;">其它系统</a>--%>
<%--                <dl class="layui-nav-child">--%>
<%--                    <dd><a href="">邮件管理</a></dd>--%>
<%--                    <dd><a href="">消息管理</a></dd>--%>
<%--                    <dd><a href="">授权管理</a></dd>--%>
<%--                </dl>--%>
<%--            </li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${account}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
        <c:if test="${not empty menumap}">
            <c:forEach items="${menumap}" step="1" var="i">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">${i.key}</a>
                    <dl class="layui-nav-child">
                    <c:forEach items="${i.value}" step="1" var="j">
                        <dd><a href="javascript:void(0);"  title="${pageContext.request.contextPath}/admin/path/${j.menu_url}" onclick="changeUrl(this)">${j.menu_name}</a></dd>
                    </c:forEach>
                    </dl>
            </ul>
            </c:forEach>
        </c:if>
        </div>
    </div>

    <div class="layui-body">
        <iframe id="ifram_div_iframe" style="width: 100%;height: 100%;" name="ifram_div_ifram" src="${pageContext.request.contextPath}/admin/path/userTbl"></iframe>
<%--        <div style="padding: 15px;">内容主体区域</div>--%>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © cykjgroup.com
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element','jquery'], function(){
        var element = layui.element
            ,$=layui.jquery;
    });


    function changeUrl(node) {
        //获取跳转路径
        console.log(node.title);
        document.getElementById("ifram_div_iframe").src=node.title;
    }
</script>
</body>
</html>
