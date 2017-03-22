<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/head.jsp"%>
<c:set var="currentPath" value="try-use" />
<style>
    .main > .container{background-color: #fff; padding: 20px 20px 40px;}
    h1{background: url(${contextPath}/res/css/module/img/use-title.png) no-repeat center; color: #666; font-size: 16px; line-height: 104px;}
    p.statics{color: #999; font-size: 14px; padding-top: 30px; padding-bottom: 30px; }

    .step{padding: 20px 0;}
    .step-1{height: 450px;}
    .step-2{height: 338px;}
    .step-3{height: 570px;}
        .step > div{background: url(${contextPath}/res/css/module/img/step.png) no-repeat center;display: inline-block; height: 174px; padding-top: 50px; width: 372px;}
        .step-1 > div{margin-top: 138px;}
        .step-2 > div{margin-top: 82px;}
        .step-3 > div{margin-top: 198px;}
            .step p{color: #f20b11; font-size: 18px;}
            .step p.num{color: #b2b2b2; font-size: 30px;}
    .step img{border: 10px solid #e6e6e6; margin-left: 70px;}
        .step-3 img{margin-left: 30px;}
</style>
<body>
<div id="J_body" class="J_body">
    <div id="J_main" class="J_main">
        <%@ include file="./common/navigation.jsp" %>
        <div class="main" id="main">
            <div class="container text-center">
                <h1>试用步骤</h1>
                <p class="statics">亲自体验金档口后台系统与微商城平台。在您决定购买之前，我们为您提供试用机会，让您马上发现我们的价值</p>
                <div class="">
                    <div class="step step-1">
                        <div class="">
                            <p class="num">1</p>
                            <p>申请微信服务号</p>
                        </div>
                        <img src="${contextPath}/res/css/module/img/step-1.png" class="" alt="申请微信服务号" />
                    </div>
                    <div class="step step-2">
                        <div class="">
                            <p class="num">2</p>
                            <p>登录金档口后台进行授权绑定</p>
                        </div>
                        <img src="${contextPath}/res/css/module/img/step-2.png" class="" alt="登录金档口后台进行授权绑定" />
                    </div>
                    <div class="step step-3">
                        <div class="">
                            <p class="num">3</p>
                            <p>上传商品开启商城模式</p>
                        </div>
                        <img src="${contextPath}/res/css/module/img/step-3.png" class="" alt="上传商品开启商城模式" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="./common/footer.jsp"%>
    <script type="text/javascript">
        seajs.use('module/base/main');
    </script>
</div>
</body>
</html>

