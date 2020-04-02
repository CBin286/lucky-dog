<%--
  Created by IntelliJ IDEA.
  User: HJY
  Date: 2020-3-10
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的积分</title>
    <%String path = request.getContextPath();%>
    <link rel="stylesheet" href=<%=path+"/layui/css/layui.css" %> media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
</head>
<body >
<div class="layadmin-user-login-box layadmin-user-login-header">
    <h2 style="margin-left: 43%">我的积分</h2>
</div>
<input type="hidden" id="path" value="<%=path%>">
<table id="demo" lay-filter="test"></table>
</body>
<script>
    layui.use(['table','jquery'], function(){
        var table = layui.table;
        $ = layui.jquery;
        var path =$("#path").val();

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 450
            ,url:path+ '/user/userScore' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'score_date', title: '发生时间', width:180}
                ,{field: 'score_name', title: '收入/支出', width:100}
                ,{field: 'score_num', title: '积分数', width:100, sort: true}
            ]]
        });
    });

</script>
</html>
