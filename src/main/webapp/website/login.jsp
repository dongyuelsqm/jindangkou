<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<!DOCTYPE html>
<head>
    <title>金档口</title>
    <link rel="stylesheet" href="${contextPath}/res/css/base/iconfont/iconfont.css" />
    <link rel="stylesheet" href="${contextPath}/res/css/base/style.css" />
    <link rel="stylesheet" href="${contextPath}/res/css/module/login.css" />
    <!--[if lte IE 8]>
    <script type="text/javascript" src="${contextPath}/res/js/plugins/respond.js"></script>
    <![endif]-->
</head>
<body>
    <div id="J_body" class="J_body">
        <div class="content">
            <a href="${contextPath}/website/welcome.jsp" class="logo" id="logo"></a>
            <form role="form" class="form" id="form-login">
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
                <div class="row">
                    <input type="text" class="form-control verify-code pull-left" name="verifyCode" id="verifyCode" placeholder="请输入验证码" />
                    <img src="./imgBuilder" alt="验证码" class="verify-img pull-right" data-do="verifyImg-change" id="verifyImg" />
                </div>
                <!--
                <div class="row">
                    <div class="col-sm-12">
                        <span class="checkbox-inline">
                            <input type="checkbox" class="checkbox" id="isRemember"/> 记住密码
                        </span>
                        <a href="#password!confirmAccount.action" class="forget-pwd pull-right" >忘记密码</a>
                    </div>
                </div>
                -->
                <div class="row">
                    <button type="button" class="btn btn-submit" role="btn-login" data-do="login">提交</button>
                </div>
            </form>
        </div>
    </div>
    <!-- seajs -->
    <script src="${contextPath}/res/js/lib/seajs/sea.js" id="seajsnode"></script>
    <script type="text/javascript">
        seajs.config({
            debug: true,
            base: '${contextPath}/res/js',
            alias: {
                'jquery': 'lib/jquery/jquery-debug',
                'underscore': 'lib/underscore/1.8.3/underscore',
                'backbone': 'lib/backbone/1.2.1/backbone'
            }
        });
        seajs.use('module/login', function(mod){
            mod.run();
        });
    </script>
</body>
</html>