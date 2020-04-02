layui.use(['table','jquery'], function(){
    var table = layui.table;
    $ = layui.jquery;
    var path =$("#path").val();

    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 450
        ,url:path+ '/user/myScore' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'name', title: '发生用户', width:110, sort: true, fixed: 'left'}//hide开启隐藏域
            ,{field: 'time', title: '发生时间', width:180}
            ,{field: 'event', title: '增加/扣除', width:100}
            ,{field: 'score', title: '积分数', width:100, sort: true}
        ]]

    });


});
