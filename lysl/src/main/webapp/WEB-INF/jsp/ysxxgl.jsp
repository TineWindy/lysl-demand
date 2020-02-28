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
        <button class="layui-btn layui-btn-sm" lay-event="addNews">添加运输信息</button>
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
        <a class="layui-btn layui-btn-xs" lay-event="shtg">通过</a>
        <a class="layui-btn layui-btn-xs" lay-event="shbtg">不通过</a>
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
            , url: 'http://47.113.115.120:8080/backend/transportation/getTransportationList'
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
                , {field: 'name', title: '名称', width: 120, fixed: 'left', edit: 'text', unresize: true, sort: true}
                , {field: 'linkMan', title: '联系人', width: 120, edit: 'text', sort: true}
                , {field: 'linkMobile', title: '联系电话', edit: 'text', width: 150}
                , {field: 'deliveryScope', title: '配送区域', edit: 'text', width: 300}
                , {field: 'remark', title: '备注', width: 300, edit: 'text', sort: true}
                , {field: 'checkStatus', title: '审核状态'}
                , {title: '审核', toolbar: '#photosdemo', width: 200}
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
                    window.location.href = '/addysxx'
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'shtg') {
                var json = JSON.stringify(data, ["id"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var id = obj.id;//json name
                $.ajax({
                    type: "GET",
                    url: "http://47.113.115.120:8080//backend/transportation/checkTransportation?id=" + id + "&checkStatus=APPROVED",
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.parse(jsonResult);
                        alert(json.msg);
                        window.location.href = '/ysxxgl';
                    }
                });
            } else if (obj.event === 'shbtg') {
                var json = JSON.stringify(data, ["id"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var id = obj.id;//json name
                $.ajax({
                    type: "GET",
                    url: "http://47.113.115.120:8080/backend/transportation/checkTransportation?id=" + id + "&checkStatus=DISAPPROVED",
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.parse(jsonResult);
                        alert(json.msg);
                        window.location.href = '/ysxxgl';
                    }
                });
            } else if (obj.event === 'sc') {
                var json = JSON.stringify(data, ["id"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var id = obj.id;//json name
                $.ajax({
                    type: "GET",
                    url: "http://47.113.115.120:8080/transportation/deleteTransportation?id=" + id,
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.parse(jsonResult);
                        var jsonx = JSON.stringify(json);
                        var obj = new Function("return" + jsonx)();//转换后的JSON对象
                        // var res = objx[0].msg;//json name
                        alert(obj.msg);
                        window.location.href = '/ysxxgl';
                    }
                });
            } else if (obj.event === 'edit') {
                var json = JSON.stringify(data);
                var obj = new Function("return" + json)();//转换后的JSON对象
                $.ajax({
                    type: "POST",
                    url: "http://47.113.115.120:8080/backend/transportation/updateTransportation",
                    data: JSON.stringify({
                        "transportation": {
                            "id": obj.id,
                            "name": obj.name,
                            "linkMan": obj.linkMan,
                            "linkMobile": obj.linkMobile,
                            "deliveryScope": obj.deliveryScope,
                            "remark": obj.remark,
                        }
                    }),
                    dataType: 'json',
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.stringify(jsonResult, ["msg"]);
                        var obj = new Function("return" + json)();//转换后的JSON对象
                        var res = obj.msg;//json name
                        alert(res);
                        window.location.href = '/ysxxgl';
                    }
                });
            }
        });
    });
</script>

</body>
</html>