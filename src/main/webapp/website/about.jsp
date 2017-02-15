<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/head.jsp"%>
<c:set var="currentPath" value="about" />
<style>
    .about-detail{background-color: #576981; overflow: hidden; }
        .about-detail > img{height: 460px; width: 800px;}
        .about-detail > div{color: #fff; font-size: 14px; padding: 100px 20px; width: 400px;}
            .about-detail p{text-indent: 2em; line-height: 20px;}
            .about-detail p.text-center{text-indent: 0; margin-top: 30px;}

    .about-detail.text-center{background-color: initial; margin-bottom: 30px; margin-top: 30px;}
        .about-detail.text-center > img{height: 200px; width: 360px;}
        .about-detail.text-center > img.center{margin-left: 30px; margin-right: 30px;}

    .btn{border-radius: 20px; font-size: 16px; height: 40px; width: 135px;}
        .btn > .iconfont{margin-right: 5px;}
</style>
<body>
    <div id="J_body" class="J_body">
        <div id="J_main" class="J_main">
            <%@ include file="./common/navigation.jsp" %>
            <div class="main" id="main">
                <div class="container" style="background-color: #fff; padding-bottom: 40px;">
                    <div class="about-detail">
                        <img class="pull-left" src="${contextPath}/res/css/module/img/about-1.png" alt="" />
                        <div class="pull-right">
                            <p class="info">金档口是重庆宅美达电子商务有限公司旗下打造的服装批发市场B2B平台，专门针对微信公众账号提供营销推广服务的第三方平台，域名为www.weidangkou.com。主要功能是针对批发商提供有针对性的、操作简单易用的营销推广服务，更加流畅和方便地服务批发商和批发客。此外，团队核心成员多数为浙大研究生，曾履职人人网、中移动、中兴通讯等知名企业。</p>
                            <p class="text-center"><a href="${contextPath}/website/contact.jsp" class="btn btn-white"><i class="iconfont icon-contact"></i>联系我们</a></p>
                        </div>
                    </div>
                    <div class="about-detail text-center">
                        <img src="${contextPath}/res/css/module/img/about-s1.png" alt="" />
                        <img src="${contextPath}/res/css/module/img/about-s2.png" alt="" class="center"/>
                        <img src="${contextPath}/res/css/module/img/about-s3.png" alt="" />
                    </div>
                    <div class="about-detail">
                        <img class="pull-left" src="${contextPath}/res/css/module/img/about-2.png" alt="" />
                        <div class="pull-right">
                            <p class="info">通过金档口平台，批发商可以轻松管理自己的微信各类信息，打造专属的微商城，对微信营销实现有效监控，极大扩展潜在客户群和实现企业的运营目标。金档口平台很好的弥补了微信公众平台本身功能不足、针对性不强、交互不便利的问题，用户无需下载app，即可关注商家微信服务号即可进行交易。</p>
                            <p class="text-center"><a href="${contextPath}/website/contact.jsp" class="btn btn-white"><i class="iconfont icon-contact"></i>联系我们</a></p>
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
