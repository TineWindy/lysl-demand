<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>珞樱善联</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addNews">添加新闻</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sc">删除</a>
</script>

<script type="text/html" id="photosdemo">
    <div id="layer-photos-demo" class="layer-photos-demo">
        <a class="layui-btn layui-btn-xs" lay-event="sxqxq">详情</a>
    </div>
</script>


<script src="../layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/javascript" src="../js/jquery.js"></script>

<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#test'
            , url: 'http://47.113.115.120:8080/backend/news/getNewsList'
            , request: {
                pageName: 'pageNo',   // 页码的参数名称，默认：page
                limitName: 'pageSize'   // 每页数据量的参数名，默认：limit
            }
            , method: "get"
            , toolbar: '#toolbarDemo',  //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '用户数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'origin', title: '来源', width: 200, fixed: 'left', unresize: true, edit: 'text', sort: true}
                , {field: 'title', title: '新闻标题', width: 400, edit: 'text', sort: true}
                , {field: 'url', edit: 'text', title: '链接'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
            ]]
            , page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
                case 'addNews':
                    window.location.href = '/addxwzx'
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'sxqxq') {

                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['40%', '40%'], //宽高
                    content: html
                });
            } else if (obj.event === 'sc') {
                var json = JSON.stringify(data, ["id"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var id = obj.id;//json name
                $.ajax({
                    type: "GET",
                    url: "http://47.113.115.120:8080/backend/news/deleteNews?id=" + id,
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.parse(jsonResult);
                        var jsonx = JSON.stringify(json);
                        var obj = new Function("return" + jsonx)();//转换后的JSON对象
                        // var res = objx[0].msg;//json name
                        alert(obj.msg);
                        window.location.href = '/xwzxgl';
                    }
                });
            } else if (obj.event === 'edit') {
                var json = JSON.stringify(data);
                var obj = new Function("return" + json)();//转换后的JSON对象
                $.ajax({
                    type: "POST",
                    url: "http://47.113.115.120:8080/backend/news/updateNews",
                    data: JSON.stringify({
                        "news": {
                            "id": obj.id,
                            "publishDatetime": obj.publishDatetime,
                            "origin": obj.origin,
                            "url": obj.url,
                            "title": obj.title
                        }
                    }),
                    dataType: 'json',
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.stringify(jsonResult);
                        var obj = new Function("return" + json)();//转换后的JSON对象
                        var res = obj.msg;//json name
                        alert(res);
                        window.location.href = '/xwzxgl';
                    }
                });
            }
        });
    });
</script>

</body>
</html>