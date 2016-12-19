<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<link rel="stylesheet" href="${contextPath}/res/css/module/password.css" />
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.userName = '${user.userName}';
</script>
<style>
   .form .btn.pull-left,
   .form .btn.pull-right{margin-right: 0; width: 46%;}
		#info-wrapper > .row{line-height: 30px;}
</style>
<div class="container">
    <div class="breadcrumb">找回密码</div>
    <div class="content">
    	<!-- <h1 class="content-title">找回密码</h1> -->
        <div class="pwd-steps clearfix">
            <div class="pwd-step actived">1. 确认账号</div>
            <div class="step-arrow step-arrow-2"></div>
            <div class="pwd-step active">2. 验证身份</div>
            <div class="step-arrow step-arrow-3"></div>
            <div class="pwd-step">3. 重置密码</div>
            <div class="step-arrow step-arrow-4"></div>
        </div>
        <form role="form" class="form form-pwd" id="safeVerify">
            <div class="row">
                <label class="col-sm-4 control-label" for="valideType">选则身份验证方式<span class="required">*</span></label>
                <div class="col-sm-4">
                    <span class="radio-inline">
                    	<input type="radio" class="radiobox" checked="checked" name="valideType" data-key="1"/>手机号码
                    </span>
                    <c:if test="${!empty user.userEmail}">
                        <span class="radio-inline"><input type="radio" class="radiobox" name="valideType" data-key="2"/>邮箱</span>
                    </c:if>
                </div>
            </div>
            <div class="row" id="userName-wrapper">
                <label class="col-sm-4 control-label" for="userName">账户名<span class="required">*</span></label>
                <div class="col-sm-4">
                    <p class="form-static">${user.userName}</p>
                </div>
            </div>
            <div id="info-wrapper"></div>
            <div class="row">
	            <div class="col-sm-offset-4 col-sm-4">
	            	<button type="button" class="btn btn-default pull-left btn-back">上一步</button>
	                <button type="button" class="btn btn-submit pull-right">下一步</button>
	            </div>
            </div>
        </form>
    </div>
</div>
<script type="text/html" id="tmpl-userMobileView">
    <div class="row">
		<label class="col-sm-4 control-label" for="userMobile">手机号码<span class="required">*</span></label>
        <div class="col-sm-4">
            <p class="form-static">${user.userMobile}</p>
			<input type="hidden" name="userMobile" id="userMobile" value="${user.userMobile}"/>
        </div>
       	<div class="col-sm-12 col-sm-offset-4 comment">
            <button class="btn btn-mobileCode btn-valideCode" type="button" >获取验证码</button>
        </div>
    </div>
    <div class="row">
        <label class="col-sm-4 control-label" for="mobileCode">短信验证码<span class="required">*</span></label>
        <div class="col-sm-4">
            <input type="text" class="form-control randomCode" name="randomCode" id="mobileCode" value="" placeholder="请输入短信验证码"/>
        </div>
    </div>
</script>
<script type="text/html" id="tmpl-userEmailView">
    <div class="row">
        <label class="col-sm-4 control-label" for="userEmail">邮箱<span class="required">*</span></label>
        <div class="col-sm-4">
            <p class="form-static">${user.userEmail}</p>
            <input type="hidden" name="userEmail" id="userEmail" value="${user.userEmail}"/>
        </div>
       	<div class="col-sm-12 col-sm-offset-4 comment">
            <button class="btn btn-emailCode btn-valideCode" type="button" >获取验证码</button>
        </div>
    </div>
    <div class="row">
        <label class="col-sm-4 control-label" for="emailCode">邮箱验证码<span class="required">*</span></label>
        <div class="col-sm-4">
            <input type="text" class="form-control randomCode" name="randomCode" id="emailCode" value="" placeholder="请输入邮箱验证码"/>
        </div>
    </div>
</script>
<script>
    seajs.use('module/password', function(mod){
		new mod.safeVerifyView({
            el: '#safeVerify'
        });
	});    
</script>
