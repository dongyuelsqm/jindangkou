<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/head.jsp"%>
<c:set var="currentPath" value="agent" />
<style>
    h1.agent-title{background: url(../res/css/module/img/agent-title.png) no-repeat center; color: #f20b11; font-size: 16px; line-height: 120px; height: 120px; margin-top: 20px; margin-bottom: 20px;}

    .agent-detail{padding: 40px; overflow: hidden;}
        .agent-advantage{padding-bottom: 10px;}
        .agent-condition{padding-top: 10px;}
    .agent-detail > img{height: 360px; width: 540px;}
    .agent-detail > div{padding-top: 40px; padding-right: 10px; width: 500px;}
        .agent-detail h2{color: #b2b2b2; font-size: 18px; }
        .agent-detail p{color: #474747; font-size: 14px; line-height: 20px;}
            .agent-detail p.info-detail{color: #777;}
            .agent-detail p.info{margin-top: 20px; margin-bottom: 20px;}

</style>
<body>
<div id="J_body" class="J_body">
    <div id="J_main" class="J_main">
        <%@ include file="./common/navigation.jsp" %>
        <div class="main" id="main">
            <div class="container" style="background-color: #fff; padding-bottom: 40px;">
                <div class="agent-detail agent-advantage">
                    <img class="pull-left" src="${contextPath}/res/css/module/img/agent-1.png" alt="代理优势" />
                    <div class="pull-right">
                        <h2>代理优势</h2>
                        <p class="info">为了以最快的速度打开使用金档口软件平台的服装批发市场，现面向全国地区招募各式市、县、区级代理商。成为我们的代理商，可获得我们的全力支持，包括但不限于以下：</p>
                        <p class="info-detail">1）做的业务量越高，提成比例越高；</p>
                        <p class="info-detail">2）无需支付代理费用，基本无风险，公司给予保障； </p>
                        <p class="info-detail">3）提供全面的关于金档口第三方服务平台完整的培训资料； </p>
                    </div>
                </div>
                <h1 class="agent-title text-center">联系我们</h1>
                <div class="agent-detail agent-condition">
                    <img class="pull-right" src="${contextPath}/res/css/module/img/agent-2.png" alt="代理条件" />
                    <div class="pull-left">
                        <h2>代理条件</h2>
                        <p class="info">如果您有意向成为我们的代理商，您需要满足以下要求；</p>
                        <p class="info-detail">1）认同金档口的产品及管理理念和经营模式，具有真诚长期的合作意向；</p>
                        <p class="info-detail">2）形象姣好，口齿伶俐，有一定的地推销售经验的优先； </p>
                        <p class="info-detail">3）时间相对比较自由，经过培训对公司的产品了解并能对客户讲解清晰；</p>
                        <p class="info-detail">4）承诺不跨授权区域进行代理工作，切不得随意更改公司规定价格销售公司产品，不得扰乱市场。</p>
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
