<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="./common/head.jsp"%>
<c:set var="currentPath" value="concat" />
<style>
    .main > .container{background: url(../res/css/module/img/contact-bg.png) repeat-y center; box-shadow: 0px 0px 8px 3px #888; -moz-box-shadow: 0px 0px 8px 3px #888; height: 750px; width: 980px;}
        .main > .container > div{padding: 30px; width: 50%;}

    h1{color: #ccc; font-size: 48px; text-align: left;}
    h2{background: url(../res/css/module/img/contact-title.png) no-repeat center; color: #666; height: 50px; line-height: 50px; font-size: 16px; }

    .contact-us{color: #f00; }
        .contact-us h2{margin-top: 60px; margin-bottom: 50px;}
        .contact-us p{color: #666; font-size: 16px; line-height: 25px;}
        .contact-us img{margin-top: 50px;}

    .form{padding: 20px;}
    .form > .row { line-height: 48px;}
        .form .form-control{border-radius: 8px; line-height: 48px;}
        input.form-control{height: 50px;}
    .form .btn-submit,
    .form .btn-submit:hover,
    .form .btn-submit:focus{background-color: #f20b11; border-radius: 20px; font-size: 16px; height: 40px; line-height: 40px; width: 50%;}
</style>
<body>
<div id="J_body" class="J_body">
    <div id="J_main" class="J_main">
        <%@ include file="./common/navigation.jsp" %>
        <div class="main" id="main">
            <div class="container" style="padding-bottom: 40px;">
                <div class="pull-left contact-us text-center">
                    <h1>CONTACT US</h1>
                    <h2>联系我们</h2>
                    <div>
                        <p>公司座机：086-023-63767640</p>
                        <p>服务邮箱：rsy@hundsun.cn</p>
                        <img src="${contextPath}/res/css/module/img/contact.png" alt="联系我们" />
                    </div>
                </div>
                <div class="pull-left" style="padding-top: 50px;">
                    <h2 class="text-center">留言</h2>
                    <form class="form">
                        <div class="row">
                            <input type="text" class="form-control"  placeholder="您的称呼" />
                        </div>
                        <div class="row">
                            <input type="text" class="form-control"  placeholder="联系方式" />
                        </div>
                        <div class="row">
                            <input type="text" class="form-control"  placeholder="常用QQ" />
                        </div>
                        <div class="row">
                            <input type="text" class="form-control"  placeholder="单位名称." />
                        </div>
                        <div class="row">
                            <textarea class="form-control" placeholder="留言内容"></textarea>
                        </div>
                        <div class="row text-center">
                            <button class="btn btn-submit" type="button">提交</button>
                        </div>
                    </form>
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
