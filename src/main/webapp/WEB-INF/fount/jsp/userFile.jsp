<%--
  Created by IntelliJ IDEA.
  User: Mr.Fan
  Date: 2020-3-10
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的文档</title>
    <%String path = request.getContextPath(); %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<input type="hidden" id="path" value="<%=path%>">
<form class="layui-form" action="" onsubmit="return false;">
    <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2 style="margin-left: 43%">我的上传记录</h2>
    </div>
    <div class="layui-inline" style="width:100%;">
        <hr>
    </div>

    <div class="layui-fluid" id="searchTable">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <div style="padding-bottom: 10px;">
                <div class="layui-upload">
                    <div style="float: left;margin-left: 23%">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-block" style="width: 190px;">
                            <select  name="state" id="state" lay-verify="required">
                                <option value=""></option>
                                <option value="2">已通过</option>
                                <option value="3">未通过</option>
                                <option value="1">待审核</option>
                            </select>
                        </div>
                    </div>

                    <div style="float: left">
                        <label class="layui-form-label" >文档类型</label>
                        <div class="layui-input-block" style="width: 190px">
                            <select  name="type" id="type" lay-verify="required">
                                <option value="1">pdf</option>
                                <option value="2">doc</option>
                                <option value="3">txt</option>
                                <option value="4">ppt</option>
                                <option value="5">xls</option>

                            </select>
                        </div>
                    </div>
                    <div style="clear: left;padding-top: 10px;margin-left: 25%">
                        <label class="layui-form-label" >上传时间</label>
                        <div class="layui-inline">
                            <input class="layui-input" type="date" name="time1" id="time1" autocomplete="off">
                        </div>
                        至:
                        <div class="layui-inline" style="clear: left">
                            <input class="layui-input" type="date" name="time2"  id="time2"autocomplete="off">
                        </div>
                        <button type="button"  class="layui-btn layui-btn-normal" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-anim layui-anim-scale">
        <table id="dataTable" lay-filter="test"></table>
    </div>

    <script type="text/html" id="butdiv">
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="delete" ><i class="layui-icon">&#xe640;</i>删除</button>
    </script>

</form>

</body>

<script>

    layui.use(['upload', 'jquery', 'layer','table','laydate'], function () { //导入模块
        $ = layui.jquery;
        var form = layui.form;
        var table = layui.table;
        var path = $("#path").val();


        //第一个实例
        table.render({
            elem: '#dataTable'
            , height: 280
            , url: path + '/user/getUserFile' //数据接口
            , page: true //开启分页
            , id: 'searchTable'
            , limit: 5
            , limits: [5, 10, 15, 20]
            , cols: [[ //表头
                {field: 'document_id', title: '文档ID', width: 120, sort: true, fixed: 'left', align: 'center'}
                , {field: 'document_head', title: '文档名称', width: 150, align: 'center'}
                , {field: 'document_date', title: '上传时间', width: 180, sort: true, align: 'center'}
                , {field: 'document_integral', title: '下载积分', width: 120, sort: true, align: 'center'}
                , {field: 'document_type_name', title: '文档类型', width: 160, sort: true,align: 'center'}
                , {field: 'document_state', title: '审核状态', width: 180, sort: true, align: 'center',
                    templet: function(d){
                    if (d.document_state== 1){
                        return '待审核';
                    }else if(d.document_state== 2){
                        return '已通过';
                    }else {
                        return '未通过';
                    }

                    }}
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
                        time1: $("#time1").val(),
                        time2: $("#time2").val(),
                        state :$("#state option:selected").text(),//状态
                        type : $("#type option:selected").text()//获取类型
                    }
                });
            }
        });


        //删除
        //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(test)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            var path1 = $("#path").val();
            if(layEvent === 'delete'){ //查看
                $.ajax({
                    async:true,
                    method : "POST",
                    url :path1+'/user/deleteDocumentInf',
                    data: data,
                    dataType : "text",
                    success:function(data){
                        if ("success"==data){
                            layer.alert("删除成功",{icon:6},function () {
                                window.parent.location.reload();
                            });
                        }else {
                            layer.alert("删除失败",{icon:2});
                        }
                    }
                })
            }
        });
    });




</script>
</html>