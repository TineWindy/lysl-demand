<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>珞樱善联</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">添加新闻资讯</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>新闻信息</span></div>

    <ul class="forminfo">
        <li><label>名称</label><input id="ysname" name="" type="text" class="dfinput"/><i>运输公司或个人</i></li>
        <li><label>运输联系人</label><input id="linkname" name="" type="text" class="dfinput"/></li>
        <li><label>联系人电话</label><input id="linkphone" name="" type="text" class="dfinput"/></li>
        <li><label>是否审核</label><cite><input name="" type="radio" value=""
                                            checked="checked"/>是&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="radio"
                                                                                               value=""/>否</cite></li>
        <li><label>运输区域</label><input id="deliver" name="" type="text" class="dfinput"
                                      value=""/></li>
        <li><label>备注</label><input id="remark" name="" class="dfinput"></input></li>
        <li><label>&nbsp;</label><input name="" type="button" class="btn" onclick="submitnews()" value="确认保存"/>
        </li>
    </ul>

</div>
<script src="../layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //常规用法
        laydate.render({
            elem: '#test1'
        });
    });

    function submitnews() {
        var ysname = document.getElementById("ysname").value;
        var linkname = document.getElementById("linkname").value;
        var linkphone = document.getElementById("linkphone").value;
        var remark = document.getElementById("remark").value;

        $.ajax({
            type: "POST",
            url: "http://47.113.115.120:8080/backend/transportation/addTransportation",
            data: JSON.stringify({
                "transportation": {
                    "name": ysname,
                    "linkMan": linkname,
                    "linkMobile": linkphone,
                    "deliveryScope": deliver,
                    "remark": remark
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
</script>


</body>
</html>
