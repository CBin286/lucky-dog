<%--
  Created by IntelliJ IDEA.
  User: junlong
  Date: 2019-11-16
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>文件上传</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" action="" lay-filter="example">
    <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>新增文件</h2>
    </div>
    <div class="layui-inline" style="width:500px;">
        <hr>
    </div>
    <div class="layadmin-user-login-box layadmin-user-login-header">
        <%--绘本名字--%>
        <div class="layui-form-item">
            <label class="layui-form-label">新增书名：</label>
            <div class="layui-input-inline">
                <input type="text" name="documentScore" id="hideBookName" lay-verify="title" autocomplete="off"
                       class="layui-input"style="width: 300px" >
            </div>
        </div>

        <div style="padding-bottom: 10px;">

            <div class="layui-upload">

                <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">内容信息：</label>
            <div class="layui-input-inline">
                <input type="text" name="documentScore" id="pageContent" lay-verify="title" autocomplete="off"
                       placeholder="请输入绘本信息"
                       class="layui-input" style="width: 300px;height: 120px">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">新页数：</label>
            <div class="layui-input-inline">
                <input type="text" name="documentScore" id="pageNum" lay-verify="title" autocomplete="off" class="layui-input"style="width: 300px">
            </div>
        </div>

        <div class="demoTable">
            <div style="padding-bottom: 10px;">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="test9">保存</button>
                </div>
            </div>
        </div>
    </div>
</form>

<script>
    layui.use(['upload','jquery'], function(){
        var upload = layui.upload,
            $=layui.jquery;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test8' //绑定元素
            ,url: '${pageContext.request.contextPath}/admin/upload' //上传接口
            ,auto: false
            ,accept: 'file'
            ,bindAction: '#test9'
            ,before: function(obj){
                this.data = {
                    bookName: $("#hideBookName").val(),
                    pageContent: $("#pageContent").val(),
                    pageNum: $("#pageNum").val()
                }
            }
            ,done: function(res){
                //上传完毕回调
                alert("上传成功！");
            }
            ,error: function(){
                //请求异常回调
                alert("上传失败！");
            }
        });
    });


</script>
</body>

</html>
