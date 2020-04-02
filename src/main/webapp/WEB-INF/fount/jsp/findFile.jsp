<%--
  Created by IntelliJ IDEA.
  User: Mr.Fan
  Date: 2020-3-10
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <%String path = request.getContextPath(); %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" >
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>

</head>
<body>
<input type="hidden" id="path" value="<%=path%>">
<%--<form class="layui-form" action="" lay-filter="example" accept-charset="UTF-8" enctype="multipart/form-data">--%>
    <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2 style="margin-left: 43%">上传文件</h2>
    </div>
    <div class="layui-inline" style="width:100%;">
        <hr>
    </div>

    <div class="layui-fluid" id="searchTable">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <div style="padding-bottom: 10px;">
                <div class="layui-upload">
                    搜索条件：
                    <div class="layui-inline">
                        <input class="layui-input"  id="demoReload" autocomplete="off" placeholder="请输入文档名称" style="width: 200px">
                    </div>
                    <button type="button" class="layui-btn layui-btn-normal" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索文档</button>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-anim layui-anim-scale">
        <table id="dataTable" lay-filter="test"></table>
    </div>

    <script type="text/html" id="butdiv">
        <button class="layui-btn layui-btn-sm layui-btn-normal updatestatus" lay-event="down"><i class="layui-icon">&#xe601;</i>下载文档</button>
    </script>

<%--</form>--%>

</body>
<script>
    layui.use(['upload', 'jquery', 'layer','table','laydate','form'], function () { //导入模块
        var upload = layui.upload,
            $ = layui.jquery;
        var form=layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var table = layui.table;
        var path = $("#path").val();

        //第一个实例
        table.render({
            elem: '#dataTable'
            , height: 280
            , url: path + '/user/getDocumentTbl' //数据接口
            , page: true //开启分页
            , id: 'searchTable'
            , limit: 5
            , limits: [5, 10, 15, 20]
            , cols: [[ //表头
                {field: 'document_id', title: '文档ID', width: 120, sort: true, fixed: 'left', align: 'center'}
                , {field: 'document_head', title: '文档名称', width: 120, align: 'center'}
                , {field: 'user_name', title: '上传人员', width: 120, align: 'center'}
                , {field: 'document_date', title: '上传时间', width: 120, sort: true, align: 'center'}
                , {field: 'document_integral', title: '下载积分', width: 120, sort: true, align: 'center'}
                , {field: 'document_type_name', title: '文档类型', width: 160, sort: true,align: 'center'}
                , {field: 'document_down_num', title: '下载次数', width: 180, sort: true, align: 'center'}
                , {field: '', title: '操作', toolbar: "#butdiv", width: 200, align: 'center'}
            ]]
        });

        $('#searchTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            if (type == 'reload') {
                //执行重载
                table.reload('searchTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        document_head: $("#demoReload").val(),
                    }
                });
            }
        });

        table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            // var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            var path1 = $("#path").val();

            if (layEvent === 'down') {
                $.ajax({
                    url:'${pageContext.request.contextPath}/user/judgeScore',
                    type:'post',
                    data: data,
                    success:function(msg){
                        alert(msg);
                        if ("success"==msg){
                            window.location.href ='${pageContext.request.contextPath}/user/downDocumentInf?filename=' + data.document_head+"."+data.document_type_name;
                        }else {
                            layer.alert("会员积分不够",{icon:2});
                        }
                    },error:function (err) {
                        layer.alert("网络繁忙",{icon:2});
                    }
                });

            }
        });

            //阻止表单提交
        form.on('submit(formDemo)', function(data){
            return false;//阻止表单跳转
        });


    });

</script>
</html>