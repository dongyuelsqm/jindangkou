<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/head.jsp"%>
<c:set var="currentPath" value="welcome" />
<link rel="stylesheet" href="${contextPath}/res/css/module/welcome.css" />
<body>
<div id="J_body" class="J_body">
    <div id="J_main" class="J_main">
        <%@ include file="./common/navigation.jsp" %>
        <div class="main" id="main">
            <div class="banners flexslider" id="slider-wrapper">
                <ul class="slides">
                    <c:forEach var="index" begin="1" end="4" >
                        <li>
                            <a href="javascript:" class="banner banner-${index}" style="background-image: url(${contextPath}/res/css/module/img/banner-${index}.png);"></a>
                        </li>
                    </c:forEach>
                </ul>
                <form role="form" class="form" id="form-login">
                    <h1 class="text-center">登录后台</h1>
                    <div class="error text-center">
                        <i class="iconfont icon-info"></i>
                        <span class="error-msg"></span>
                    </div>
                    <div class="row">
                        <input type="text" class="form-control" name="userName" id="userName" value="" placeholder="请输入您的用户名"/>
                    </div>
                    <div class="row">
                        <input type="password" class="form-control" name="userPassword" id="userPassword" value="" placeholder="请输入您的密码"/>
                    </div>
                    <div class="row" style="margin-top: 30px;">
                        <button type="button" class="btn btn-submit" role="btn-login" data-do="login">登录</button>
                    </div>
                    <div class="row">
                        <a href="#" class="pull-left" >忘记密码</a>
                        <a href="#" class="pull-right" >游客进入</a>
                    </div>
                </form>
            </div>
            <div class="container" style="background-color: #fff; padding-bottom: 40px;">
                <div class="intro-info clearfix">
                    <div class="intro">
                        <img src="${contextPath}/res/css/module/img/icon-1.png" alt="微信商城" />
                        <h2>微信商城</h2>
                        <p>微商城可清晰展示爆款、特卖及分类商品，商品以售价、人气、销量等进行排序，并提供多图与小视频展示功能</p>
                    </div>
                    <div class="intro">
                        <img src="${contextPath}/res/css/module/img/icon-2.png" alt="后台服务" />
                        <h2>后台服务</h2>
                        <p>登录后台可进行店铺管理、商城管理、订单管理、微信管理、售后管理、查看统计报表等操作，轻松实现微商城功能</p>
                    </div>
                    <div class="intro">
                        <img src="${contextPath}/res/css/module/img/icon-3.png" alt="进销存系统" >
                        <h2>进销存系统</h2>
                        <p>完善的进销存系统，实时统计入库量、库存量、销售量，高效便捷，避免漏单情况</p>
                    </div>
                    <div class="intro">
                        <img src="${contextPath}/res/css/module/img/icon-4.png" alt="数据报表" >
                        <h2>数据报表</h2>
                        <p>多方面统计报表，数据一目了然，可查看订单统计、商品统计、客户统计，大数据引领工作导向</p>
                    </div>
                </div>
                <div class="clearfix video-intro">
                    <img src="${contextPath}/res/css/module/img/video-sig.png" class="pull-left" alt="" />
                    <div class="intro">
                        <h2>微T台 - 主图视频</h2>
                        <p>新一代微商城系统，添加微视频功能，全新视听，让服饰完美绽放，给客户全新购物体验，宝贝转化率完美提升！</p>
                    </div>
                    <img src="${contextPath}/res/css/module/img/video.png" class="" style="margin-top: 95px;" alt="" />
                </div>
                <div class="text-center">
                    <img src="" alt="" />
                </div>
            </div>
        </div>
    </div>
    <%@ include file="./common/footer.jsp"%>
    <script type="text/javascript">
        seajs.use('module/base/main');
        seajs.use('module/welcome', function(mod){
            mod.run();
        });
    </script>
</div>
</body>
</html>
