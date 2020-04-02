<%--
  Created by IntelliJ IDEA.
  User: HJY
  Date: 2020-3-10
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文档审核</title>
    <%String path = request.getContextPath();%>
    <link rel="stylesheet" href=<%=path+"/layui/css/layui.css" %> media="all">
    <script src="<%=request.getContextPath()%>/layui/layui.js"></script>
    <script src="<%=request.getContextPath()%>/back/js/jquery-3.4.1.js"></script>
    <script src="<%=request.getContextPath()%>/back/js/json2.js"></script>
    <script src="<%=request.getContextPath()%>/back/js/documentReview.js"></script>

</head>
<body >
<input type="hidden" id="path" value="<%=path%>">

<!-- 增加搜索条件 -->
<div class="demoTable">
    <div style="float: left;margin-left: 20%;">
        <label class="layui-form-label" >文档标题</label>
        <div class="layui-inline">
            <input class="layui-input"  id="title" autocomplete="off">
        </div>
    </div>
    <div style="float: left">
        <label class="layui-form-label" >上传人</label>
        <div class="layui-inline">
            <input class="layui-input"  id="who" autocomplete="off">
        </div>
    </div>
    <div style="float: left">
        <label class="layui-form-label" >文档类型</label>
        <div class="layui-input-block" style="width: 190px">
            <select  name="type" id="type" lay-verify="required">
                <option value=""></option>
                <c:if test="${not empty documentTypes}">
                    <c:forEach items="${documentTypes}" var="i" step="1">
                        <option value="${i.typeid}" <c:if test="${type==i.typeid}">selected="selected"</c:if>>${i.typename}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
    </div>
    <div style="clear: left;margin-left: 30%;padding-top: 1%;">
        <label class="layui-form-label" >上传时间</label>
        <div class="layui-inline">
            <input class="layui-input" type="date" name="time1" id="time1" autocomplete="off">
        </div>
        至:
        <div class="layui-inline">
            <input class="layui-input" type="date" name="time2"  id="time2"autocomplete="off">
        </div>
        <button class="layui-btn" id="btn1" data-type="reload">搜索</button>
        <a href="<%=request.getContextPath()%>/admin/downDocumentInf" >下载</a>
    </div>

</div>

<table id="demo" lay-filter="test"></table>



<script type="text/html" id="butdiv">
    {{#  if(d.document_state == 1){ }}
    <button class="layui-btn layui-btn-sm " lay-event="down" >下载</button>
    <button class="layui-btn layui-btn-sm layui-btn-normal ok">审核通过</button>
    <button class="layui-btn layui-btn-sm layui-btn-danger no">审核不通过</button>
    {{#  } else { }}
    <button class="layui-btn layui-btn-sm " lay-event="down">下载</button>
    {{#  } }}
</script>
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
            ,url:path+ '/admin/documentReview' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'document_id', title: '文档ID', width: 120, sort: true, fixed: 'left', align: 'center'}
                , {field: 'document_head', title: '文档名称', width: 120, align: 'center'}
                , {field: 'user_name', title: '上传人员', width: 120, align: 'center'}
                , {field: 'document_date', title: '上传时间', width: 120, sort: true, align: 'center'}
                , {field: 'document_integral', title: '下载积分', width: 120, sort: true, align: 'center'}
                , {field: 'document_type_name', title: '文档类型', width: 160, sort: true,align: 'center'}
                , {field: 'document_down_num', title: '下载次数', width: 180, sort: true, align: 'center'}
                ,{field: '', title: '操作',toolbar:'#butdiv', width:280, }
            ]]
            ,id: 'testReload'


        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            if(type == 'reload'){
                //执行重载
                table.reload('testReload', {//拿到全部数据的id
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        document_head: $("#title").val(),
                        user_name: $("#who").val(),
                        document_type_name:$("#type option:selected").text(),//拿到下拉框选中的值
                        time1: $("#time1").val(),
                        time2: $("#time2").val()
                    }

                });
            }

        });

        //修改状态
        $('body').on('click', '.ok', function() {
            var type = $(this).text();
            var $td = $(this).parents('tr').children('td');
            var did = $td.eq(0).text();//获取点击按钮相对应的id
            var userid = $td.eq(2).text();
            var typename = $td.eq(6).text();//获取文档类型
            layer.confirm('确定修改?',{icon:3,title:'提示'},function (index) {
                $.ajax({
                    async:true,
                    method : "POST",
                    url :path+'/admin/changeDocumentState',
                    data: "did="+did +"&butName="+type+"&typename="+typename+"&userid="+userid,
                    dataType : "text",
                    success:function(data){
                        if ("error"==data){
                            layer.alert("修改失败",{icon:2});
                        }else {
                            layer.alert("修改成功",{icon:6},function () {
                                window.location.reload();
                            });
                        }
                    }
                })
                layer.close(index);
            })
        })

        //下载
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            // var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            var path1 = $("#path").val();
            if(layEvent === 'down'){ //查看
                console.log(data.did)
                // var elink = document.createElement("a");
                // elink.setAttribute("href",path1+'/admin/downDocumentInf?did='+data.did)
                window.location.href=path1+'/admin/downDocumentInf?did='+data.did;
                // $.ajax({
                //     async:true,
                //     method : "POST",
                //     url :path1+'/admin/downDocumentInf',
                //     data: data,
                //     dataType : "text",
                // success:function(data){
                //     var msg = eval('(' + data + ')'); //转换字符串为json对象
                //     console.log("msg=="+msg)
                //     console.log("msg1=="+msg.url)
                //     var elink = document.createElement("a");
                //     elink.download = msg.title;
                //     elink.style.display = "none";
                //     var blob = new Blob([msg.url]);
                //     if ("success"==data){
                //         layer.alert("删除成功",{icon:6},function () {
                //             window.parent.location.reload();
                //         });
                //     }else {
                //         layer.alert("删除失败",{icon:2});
                //     }
                // }
                // })
            }
        });



    });

</script>
</html>
