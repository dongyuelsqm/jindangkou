<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/head.jsp"%>
<c:set var="currentPath" value="products" />
<style>
    .products-wrapper{color: #666; padding: 40px 30px 0;}

    ul.information > li{float: left; padding-bottom: 40px; width: 25%;}
        ul.information h3{color: #666; font-size: 14px; margin-top: 10px;}
        ul.information div{border-radius: 50px; font-size: 32px; height: 100px; line-height: 100px; margin: auto; width: 100px;}
            ul.information div > span{font-size: 14px;}
            ul.information div.odd{background-color: #f20b11; color: #fff; }
            ul.information div.even{background-color: #fff; border: 2px solid #f20b11; color: #f20b11;}

    .products-wrapper > div{margin: auto; width: 960px;}
        .products-wrapper > div > img,
        .products-wrapper > div > div{float: left; height:280px; width: 320px;}
        .products-wrapper > div > div{background-color: #e6e6e6; line-height: 20px; text-indent: 2em; padding: 70px 20px;}

    .advantage-wrapper{margin-left: auto; margin-right: auto; width: 1000px;}
    .advantage-wrapper > h2{background: url(../res/css/module/img/product-line.png) no-repeat center; color: #999; font-size: 36px; font-weight: bolder;}
    .advantage > li{background: no-repeat 10px center; padding: 20px 20px 20px 88px; text-align: left; }
        .advantage > li.adv-1{background-image: url(../res/css/module/img/adv-order-1.png); }
        .advantage > li.adv-2{background-image: url(../res/css/module/img/adv-order-2.png); }
        .advantage > li.adv-3{background-image: url(../res/css/module/img/adv-order-3.png); }
        .advantage > li.adv-4{background-image: url(../res/css/module/img/adv-order-4.png); }
        .advantage > li.adv-5{background-image: url(../res/css/module/img/adv-order-5.png); }
    .advantage > li > h3{color: #333; font-size: 24px;}
    .advantage > li > div{line-height: 20px; margin-top: 10px;}

    .table{margin: 0 auto 20px; width: 800px;}
        .table > thead th{text-align: center; line-height: 2em;}
        .table > tbody td .iconfont{color: #b6b6b6; font-weight: 700;}

</style>
<body>
    <div id="J_body" class="J_body">
        <div id="J_main" class="J_main">
            <%@ include file="./common/navigation.jsp" %>
            <div class="main" id="main">
                <div class="container text-center" style="background-color: #fff; padding-bottom: 40px;">
                    <div class="products-wrapper information-wrapper">
                        <ul class="information clearfix">
                            <li>
                                <div class="odd">6.5<span>亿</span></div>
                                <h3>微信月活跃人数</h3>
                            </li>
                            <li>
                                <div class="even">76.9<span>%</span></div>
                                <h3>覆盖率</h3>
                            </li>
                            <li>
                                <div class="odd">64.6<span>%</span></div>
                                <h3>活跃率</h3>
                            </li>
                            <li>
                                <div class="even">55.2<span>%</span></div>
                                <h3>平均每天打开微信10次以上</h3>
                            </li>
                            <li>
                                <div class="odd">62.7<span>%</span></div>
                                <h3>微信用户好友超过50人</h3>
                            </li>
                            <li>
                                <div class="even">65<span>%</span></div>
                                <h3>微信用户每月使用微信>10分钟</h3>
                            </li>
                            <li>
                                <div class="odd">952<span>亿</span></div>
                                <h3>微信带动的信息消费规模</h3>
                            </li>
                            <li>
                                <div class="even">110<span>亿</span></div>
                                <h3>微信带动的生活消费</h3>
                            </li>
                        </ul>
                    </div>

                    <div class="products-wrapper">
                        <div class="clearfix">
                            <img src="${contextPath}/res/css/module/img/product-1.png" alt="" />
                            <div class="text-left">服装品牌公众号的运营侧重在于客户服务，体验经济时代，服务即营销。Marketing3.0时代需要更专注于人类的情感需求，要求企业同时关注消费者的内心需求，也就是进行品牌资产营销，体验营销，情感营销，建立全新粉丝经济新生态。</div>
                            <img src="${contextPath}/res/css/module/img/product-2.png" alt="" />
                            <div class="text-left">21世纪随着微信，微博等新的社交工具的新起，我们已经步入全新的社交媒体营销的浪潮，相对与以往以产品为中心的Market1.0时代与市场，目标定位为导向的Marketing2.0是时代。现代营销学之父菲利普科特勒的营销3.0理论认为，随着网络化的持续推进，消费者的信息高度互联，信息异常灵通，口碑传播的作用v极度突出。</div>
                            <img src="${contextPath}/res/css/module/img/product-3.png" alt="" />
                            <div class="text-left">它以客户为中心，以了解客户更深层的需求，提高客户的满意度，培养，维持忠诚度为目的，改善企业与客户之间的关系。分析，分类，根据客户的不同需求进行差别化服务，从而实现精确式营销。</div>
                        </div>
                    </div>

                    <div class="products-wrapper advantage-wrapper">
                        <h2>我们的优势</h2>
                        <ul class="advantage">
                            <li class="adv-1">
                                <h3>高度便利化</h3>
                                <div>微信在生活中，随着微信在日常生活的渗透加深，每日打开微信次数10次以上，使用微信时长达10分钟上，商家可通过微信服务号对客户，实现精准营销，所营销产品，客户可直接进入微信商城随时浏览，随时购买商品。</div>
                            </li>
                            <li class="adv-2">
                                <h3>信息即时性</h3>
                                <div>基于微信实时在线，服饰微信营销者可以通过微信公众平台定期按时向关注用户发送服饰品信息，客户在第一时间收到信息，确保宣传信息即时有效。</div>
                            </li>
                            <li class="adv-3">
                                <h3>沉淀客户，建立粉丝圈</h3>
                                <div>基于微信本身社交软件的特殊性，微信服务号是天然的ＣＲＭ平台，在微信服务号沉淀客户，方便快捷，而且没有上限，不必再为微信加满而烦恼了。同时，通过我们金档口的数据分析技术，可以更精准更多元的营销。</div>
                            </li>
                            <li class="adv-4">
                                <h3>熟人经济</h3>
                                <div>微信作为是日常分享的社交软件，ＡＩＳＡＳ（即：引起注意—激发兴趣—信息搜索—生产行动—信息分享）重新构建消费者行为模式。让客户购买商品，通过好友形成良性的口碑好友传播。进一步宣传产品，吸引更多消费者尽心消费。</div>
                            </li>
                            <li class="adv-5">
                                <h3>新社交媒体</h3>
                                <div>微信公众号在营销过程中，成为各大服饰品牌的标配。</div>
                            </li>
                        </ul>
                    </div>

                    <div class="products-wrapper">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>品牌</th>
                                    <th>公众号数</th>
                                    <th>公众号名称</th>
                                    <th>"R"商标</th>
                                    <th>微信认证</th>
                                    <th>类型</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>CHANEL</td>
                                    <td>1</td>
                                    <td>CHANEL</td>
                                    <td><i class="iconfont icon-checkmark"></i></td>
                                    <td><i class="iconfont icon-checkmark"></i></td>
                                    <td>服务号</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="./common/footer.jsp"%>
    </div>
    <script type="text/javascript">
        seajs.use('module/base/main');
    </script>
</body>
</html>