<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/10
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>

<form class="layui-form" action=""  >
    <div class="layui-form-item">
        <label class="layui-form-label">账号：</label>
        <div class="layui-input-inline">
            <input type="text" id="userName" name="userName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" value="${userName}">
        </div>

        <label class="layui-form-label">性别：</label>
        <div class="layui-input-inline">
            <select name="userSex" id="userSex" lay-filter="aihao">
                <option value=""></option>
                <option value="1" <c:if test="${user_sex}==1">selected="selected"</c:if> >男</option>
                <option value="2" <c:if test="${user_sex}==2">selected="selected"</c:if> >女</option>
            </select>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">注册时间：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input text" id="test10" placeholder=" - " value="${user_date}">
            </div>
        </div>
        <button class="layui-btn" id="button" lay-submit="" lay-filter="formDemo" data-type="reload" >查询</button>

    </div>

</form>

<button class="layui-btn" id="add" lay-submit="" lay-filter="add">新增</button>


<table id="demo" lay-filter="test"></table>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
            ,url: '${pageContext.request.contextPath}/admin/getUserTbl' //数据接口
            ,page: true //开启分页
            ,limit:10
            ,cols: [[ //表头
                {field: 'user_id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'user_account', title: '账号', width:180}
                ,{field: 'user_pwd', title: '密码', width:80, sort: true}
                ,{field: 'user_sex', title: '性别', width:80
                    ,templet: function(d){
                        return d.user_sex == 1?'男':'女';
                    }}
                ,{field: 'user_age', title: '年龄', width: 177}
                ,{field: 'user_date', title: '注册日期', width: 177}
                ,{fixed: 'right', width:150, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
        });

        //日期时间范围
        laydate.render({
            elem: '#test10'
            ,type: 'datetime'
            ,range: true
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
                        user_account:$("#userName").val(),
                        user_sex:$("#userSex").val(),
                        user_date:$(".text").val(),
                    }
                });
            }

        });

        //添加用户
        $('#add').on('click',function () {
            layer.open({
                type: 2,
                title: '新增',
                shadeClose: true,
                shade: 0.8,
                area: ['700px', '500px'],
                content: "${pageContext.request.contextPath}/admin/path/adduser",
                yes: function (index, layero) {

                }
            });
        });


        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'detail'){ //查看
                //do somehing
            } else if(layEvent === 'del'){ //删除
                layer.confirm('确定删除数据?', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        //相应路劲
                        url:"${pageContext.request.contextPath}/admin/deleteUser",
                        //是否异步提交
                        async:true,
                        //请求类型
                        type:"post",
                        //数据名
                        data:data,
                        //数据类型:文本
                        datatype:"text",
                        //返回成功消息
                        success:function (msg) {
                            layer.alert("删除成功",{icon:6});
                        },
                        //返回失败消息
                        error:function () {
                        }
                    });

                });
            } else if(layEvent === 'edit'){ //编辑
                //do something
                $.ajax({
                    //相应路劲
                    url:"${pageContext.request.contextPath}/admin/getUserMsg",
                    //是否异步提交
                    async:true,
                    //请求类型
                    type:"post",
                    //数据名
                    data:data,
                    //数据类型:文本
                    datatype:"text",
                    //返回成功消息
                    success:function (msg) {
                        layer.open({
                            type: 2,
                            title: '修改',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['700px', '500px'],
                            content: "${pageContext.request.contextPath}/admin/path/modifyUser",
                            yes: function (index, layero) {

                            }
                        });
                    },
                    //返回失败消息
                    error:function () {
                    }
                });
            }
        });

    });
</script>
</body>
</html>
