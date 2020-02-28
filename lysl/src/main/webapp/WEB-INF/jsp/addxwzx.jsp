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
        <li><label>新闻来源</label><input id="origin" name="" type="text" class="dfinput"/><i>标题不能超过30个字符</i></li>
        <li><label>新闻标题</label><input id="tit" name="" type="text" class="dfinput"/><i>标题不能超过30个字符</i></li>
        <li><label>新闻发布日期</label>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </li>
        <li><label>是否审核</label><cite><input name="" type="radio" value=""
                                            checked="checked"/>是&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="radio"
                                                                                               value=""/>否</cite></li>
        <li><label>新闻链接</label><input id="ur" name="" type="text" class="dfinput"
                                      value="http://www..com/"/></li>
        <%--        <li><label>文章内容</label><textarea name="" cols="" rows="" class="textinput"></textarea></li>--%>
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
        var ori = document.getElementById("origin").value;
        var time = document.getElementById("test1").value;
        var ur = document.getElementById("ur").value;
        var tit = document.getElementById("tit").value;
        $.ajax({
            type: "POST",
            url: "http://47.113.115.120:8080/news/addNews",
            data: JSON.stringify({"news": {"publishDatetime": time, "origin": ori, "url": ur, "title": tit}}),
            dataType: 'json',
            contentType: "application/json", //必须有
            success: function (jsonResult) {
                var json = JSON.stringify(jsonResult, ["resultDesc"]);
                var obj = new Function("return" + json)();//转换后的JSON对象
                var res = obj.resultDesc;//json name
                alert(res);
                window.location.href = '/xwzxgl';
            }
        });
    }
</script>


</body>
</html>
