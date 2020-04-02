<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/17
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>权限管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>

<form class="layui-form" action=""  >
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称：</label>
        <div class="layui-input-inline">
            <input type="text" id="roleName" name="roleName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" value="${userName}">
        </div>
        <button class="layui-btn" id="button" lay-submit="" lay-filter="formDemo" data-type="reload" >查询</button>

    </div>

</form>

<table id="demo" lay-filter="test"></table>


</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑权限菜单</a>
</script>

<script>
    layui.use(['table','laydate','form','jquery'], function(){
        var table = layui.table,
            laydate=layui.laydate,
            form=layui.form,
            $=layui.jquery;
        //表格实例
        table.render({
            elem: '#demo'
            ,height: 470
            ,id:'testReload'
            ,url: '${pageContext.request.contextPath}/admin/getRole' //数据接口
            ,page: true //开启分页
            ,limit:10
            ,cols: [[ //表头
                {field: 'role_id', title: 'ID', width:180}
                ,{field: 'role_name', title: '角色', width:180}
                ,{fixed: 'right', width:600, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
        });

        //阻止表单提交
        form.on('submit(formDemo)', function(data){
            return false;//阻止表单跳转
        });

        //表格数据重载
        $('#button').on('click',function () {

            var type=$(this).data('type');

            if(type == 'reload'){

                table.reload('testReload',{
                    page:{
                        curr:1
                    }
                    ,where:{
                        roleName:$("#role_name").val(),

                    }
                });
            }
        });

        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            console.log(data.role_id);

            var roleId=data.role_id;

            if(layEvent === 'edit'){ //编辑
                //do something
                layer.open({
                    type: 2,
                    title: '修改菜单权限',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['700px', '500px'],
                    content: "${pageContext.request.contextPath}/admin/path/jurTree",
                    success: function (layero, index) {
                        var body=layer.getChildFrame("body",index);
                        body.find("#dataVal").val(roleId);

                    }
                });

            }
        });

    });


</script>
</html>
