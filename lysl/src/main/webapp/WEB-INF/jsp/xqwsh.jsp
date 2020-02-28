<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
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
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <%--    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shtg">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shbtg">不通过</a>
</script>

<script type="text/html" id="photosdemo">
    <div id="layer-photos-demo" class="layer-photos-demo">
        <a class="layui-btn layui-btn-xs" lay-event="spic1">pic1</a>
        <a class="layui-btn layui-btn-xs" lay-event="spic2">pic2</a>
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
            elem: '#test',
            url: '/demand/getUnreviewedDemands',
            toolbar: '#toolbarDemo',  //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '用户数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'institutionName', title: '名称', width: 120, fixed: 'left', unresize: true, sort: true}
                , {field: 'institutionType', title: '类型', width: 120, edit: 'text'}
                // , {
                //     field: 'email', title: '邮箱', width: 150, edit: 'text', templet: function (res) {
                //         return '<em>' + res.email + '</em>'
                //     }
                // }
                , {field: 'province', title: '省', width: 80, edit: 'text', sort: true}
                , {field: 'city', title: '城市', width: 100}
                , {field: 'district', title: '区', width: 100}
                , {field: 'address', title: '地址', width: 200}
                , {title: '资质证明/需求详情', toolbar: '#photosdemo', width: 200}
                , {field: 'description', title: '描述'}
                , {field: 'doneeName', title: '联系人', width: 120}
                , {field: 'phone', title: '电话', width: 100, sort: true}
                , {field: 'wxNumber', title: '微信', width: 120}
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
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'shtg') {
                var json = JSON.stringify(data, ["demandId"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var id = obj.demandId;//json name
                $.ajax({
                    type: "GET",
                    url: "/demand/verifyDemand?demandId=" + id + "&verify=APPROVED",
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.parse(jsonResult);
                        alert(json.resultCode);
                        window.location.href = '/xqwsh';
                    }
                });

            } else if (obj.event === 'spic1') {

                var json = JSON.stringify(data, ["authList"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var list = obj.authList;//json name
                addr = list.toString().split(",")[0];
                imageflag = CheckImgExists(addr);
                if (imageflag) {

                    var img = "<img src='" + addr + "' onload='javascript:DrawImage(this,1080,2000)'/>";
                    layer.open({
                        type: 1,
                        shade: false,
                        title: false, //不显示标题
                        shadeClose: true,
                        area: ['auto', '90%'],
                        content: img, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                        offset: ['50px', '300px'],
                        cancel: function () {
                            //layer.msg('图片查看结束！', { time: 5000, icon: 6 });
                        }
                    });
                } else
                    alert("图片不存在");
            } else if (obj.event === 'spic2') {
                // var json = JSON.stringify(data, ["demandId"]);
                // var obj = new Function("return" + json)();//转换后的JSON对象
                // var id = obj.demandId;//json name
                // window.location.href = '/jzshz?id=' + id;

                var json = JSON.stringify(data, ["authList"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var list = obj.authList;//json name
                addr = list.toString().split(",")[1];
                imageflag = CheckImgExists(addr);

                if (imageflag) {

                    var img = "<img src='" + addr + "' onload='javascript:DrawImage(this,1080,2000)'/>";
                    layer.open({
                        type: 1,
                        shade: false,
                        title: false, //不显示标题
                        shadeClose: true,
                        area: ['auto', '90%'],
                        content: img, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                        offset: ['50px', '300px'],
                        cancel: function () {
                            //layer.msg('图片查看结束！', { time: 5000, icon: 6 });
                        }
                    });
                } else
                    alert("图片不存在");
            } else if (obj.event === 'sxqxq') {
                var json = JSON.stringify(data);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var list = obj.materialList;//json name
                var html_middle = '';
                for (var i = 0; i < list.length; i++) {
                    var jsonx = JSON.stringify(list[i]);
                    var objx = new Function("return" + jsonx)();//转换后的JSON对象
                    html_middle = html_middle + "<tr>\n" +
                        "<td>" + objx.materialName + "</td>\n" +
                        "<td>" + objx.materialAmount + "</td>\n" +
                        "</tr>";
                }

                var html_front = "<div class=\"layui-form\">\n" +
                    "  <table class=\"layui-table\">\n" +
                    "    <thead>\n" +
                    "      <tr>\n" +
                    "        <th>名称</th>\n" +
                    "        <th>数量</th>\n" +
                    "\n" +
                    "      </tr> \n" +
                    "    </thead>\n" +
                    "    <tbody>";

                var html_back = "    </tbody>\n" +
                    "  </table>\n" +
                    "</div>";

                var html = html_front + html_middle + html_back;

                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['40%', '40%'], //宽高
                    content: html
                });
            } else if (obj.event === 'shbtg') {
                var json = JSON.stringify(data, ["demandId"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var id = obj.demandId;//json name
                $.ajax({
                    type: "GET",
                    url: "/demand/verifyDemand?demandId=" + id + "&verify=DISAPPROVED",
                    contentType: "application/json", //必须有
                    success: function (jsonResult) {
                        var json = JSON.parse(jsonResult);
                        alert(json.resultCode);
                        window.location.href = '/xqwsh';
                    }
                });
            }
        });
    });

    /**
     * 图片按宽高比例进行自动缩放
     * @param ImgObj
     *     缩放图片源对象
     * @param maxWidth
     *     允许缩放的最大宽度
     * @param maxHeight
     *     允许缩放的最大高度
     * @usage
     *     调用：<img src="图片" onload="javascript:DrawImage(this,100,100)">
     */
    function DrawImage(ImgObj, maxWidth, maxHeight) {
        var image = new Image();
        //原图片原始地址（用于获取原图片的真实宽高，当<img>标签指定了宽、高时不受影响）
        image.src = ImgObj.src;
        // 用于设定图片的宽度和高度
        var tempWidth;
        var tempHeight;
        if (image.width > 2000) {

            if (image.width > 0 && image.height > 0) {
                //原图片宽高比例 大于 指定的宽高比例，这就说明了原图片的宽度必然 > 高度
                if (image.width / image.height >= maxWidth / maxHeight) {
                    if (image.width > maxWidth) {
                        tempWidth = maxWidth;
                        // 按原图片的比例进行缩放
                        tempHeight = (image.height * maxWidth) / image.width;
                    } else {
                        // 按原图片的大小进行缩放
                        tempWidth = image.width;
                        tempHeight = image.height;
                    }
                } else {// 原图片的高度必然 > 宽度
                    if (image.height > maxHeight) {
                        tempHeight = maxHeight;
                        // 按原图片的比例进行缩放
                        tempWidth = (image.width * maxHeight) / image.height;
                    } else {
                        // 按原图片的大小进行缩放
                        tempWidth = image.width;
                        tempHeight = image.height;
                    }
                }
                // 设置页面图片的宽和高
                ImgObj.height = tempHeight;
                ImgObj.width = tempWidth;
                // 提示图片的原来大小
                ImgObj.alt = image.width + "×" + image.height;
            }
        }
    }

    //判断图片是否存在
    function CheckImgExists(imgurl) {
        var ImgObj = new Image(); //判断图片是否存在
        ImgObj.src = imgurl;
        //存在图片
        if (ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0)) {
            return true;
        } else {
            return false;
        }
    }
</script>

</body>
</html>