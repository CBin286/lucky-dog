<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/18
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"   isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>树组件</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
    <input id="dataVal" hidden >
    <div id="test1"></div>

    <form class="layui-form" action=""  >
        <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即修改</button>
    </form>

</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    //console.log($("#dataVal").val());

    window.onload=function()//用window的onload事件，窗体加载完毕的时候
    {

        layui.use(['tree','jquery'], function(){
            var tree = layui.tree,
                form = layui.form,
                $=layui.jquery;

            var roleId =$("#dataVal").val();

            console.log(roleId);

            //获取菜单
            $.ajax({
                //相应路劲
                url:"${pageContext.request.contextPath}/admin/getAllMenu",
                //是否异步提交
                async:true,
                //请求类型
                type:"post",
                //数据名
                data:"roleId="+roleId,
                //数据类型:文本
                datatype:"text",
                //返回成功消息
                success:function (msg) {

                    var ms=JSON.parse(msg);
                    console.log(ms);

                    var ids=[];
                    var allMap=ms["allMap"];
                    var roleMenu=ms["roleMap"];

                    console.log(allMap);
                    console.log(roleMenu);


                    for (var i=0;i<roleMenu.length;i++){
                        ids.push(roleMenu[i].id);
                    }
                    console.log(ids);
                    //渲染
                    var inst1 = tree.render({
                        elem: '#test1'  //绑定元素
                        ,data: allMap
                        ,showCheckbox: true
                        ,showLine:true
                        ,id:"ids"
                    });
                    tree.setChecked("ids",ids);
                },
                //返回失败消息
                error:function () {
                }
            });

            //监听提交
            form.on('submit(formDemo)', function(data){

                var checkData = tree.getChecked('ids');

                var mg=JSON.stringify(checkData);
                console.log(checkData);

                $.ajax({
                    url:'${pageContext.request.contextPath}/admin/updateMenu',
                    async:true,
                    type:'post',
                    data: {"checkData":mg,"roleId":roleId},
                    success:function(msg){

                        layer.alert("修改成功！",{icon:6},function (index) {
                            //回调
                            layer.close(layer.index);
                            parent.location.reload(); // 父页面刷新
                        });

                    },error:function (err) {
                        console.log(err);
                    }
                });

                return false;//阻止表单跳转
            });

        });

    };


</script>
</html>
