<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>珞樱善联</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="../js/jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson li").click(function () {
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('ul').slideUp();
                } else {
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>功能栏</div>

<dl class="leftmenu">

    <dd>
        <div class="title">
            <img src="../images/leftico01.png"/>管理信息
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="/index" target="rightFrame">首页模版</a><i></i></li>
            <%--            <li><cite></cite><a href="/right" target="rightFrame">数据列表</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/imgtable" target="rightFrame">图片数据表</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/form" target="rightFrame">添加编辑</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/imglist" target="rightFrame">图片列表</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/imglist1" target="rightFrame">自定义</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/tools" target="rightFrame">常用工具</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/filelist" target="rightFrame">信息管理</a><i></i></li>--%>
            <%--            <li><cite></cite><a href="/tab" target="rightFrame">Tab页</a><i></i></li>--%>
            <li><cite></cite><a href="/xqwsh" target="rightFrame">需求审核</a><i></i></li>
            <li><cite></cite><a href="/jzxx" target="rightFrame">捐赠信息</a><i></i></li>
            <li><cite></cite><a href="/axcgl" target="rightFrame">爱心池信息管理</a><i></i></li>
        </ul>
    </dd>


<%--    <dd>--%>
<%--        <div class="title">--%>
<%--            <img src="../images/leftico02.png"/>其他设置--%>
<%--        </div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="#">编辑内容</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">发布信息</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">档案列表显示</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>


<%--    <dd>--%>
<%--        <div class="title"><img src="../images/leftico03.png"/>编辑器</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="#">自定义</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">常用资料</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">信息列表</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">其他</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>


<%--    <dd>--%>
<%--        <div class="title"><img src="../images/leftico04.png"/>日期管理</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="#">自定义</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">常用资料</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">信息列表</a><i></i></li>--%>
<%--            <li><cite></cite><a href="#">其他</a><i></i></li>--%>
<%--        </ul>--%>

<%--    </dd>--%>

</dl>
</body>
</html>
