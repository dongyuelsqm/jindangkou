<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<link rel="stylesheet" href="${contextPath}/res/css/module/password.css" />
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
   
</style>
<div class="container">
    <div class="breadcrumb">找回密码</div>
    <div class="content">
    	<!-- <h1 class="content-title">找回密码</h1> -->
        <div class="pwd-steps clearfix">
            <div class="pwd-step active">1. 确认账号</div>
            <div class="step-arrow step-arrow-3"></div>
            <div class="pwd-step">2. 验证身份</div>
            <div class="step-arrow step-arrow-4"></div>
            <div class="pwd-step">3. 重置密码</div>
            <div class="step-arrow step-arrow-4"></div>
        </div>
        <form role="form" class="form form-pwd" id="confirmAccount">
            <div class="row">
                <label class="col-sm-4 control-label" for="userName">用户名<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="userName" id="userName" value="" placeholder="请输入您的用户名/手机号码/邮箱"/>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 control-label" for="verifyCode">验证码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control verify-code left" name="verifyCode" id="verifyCode" placeholder="请输入验证码" />
                    <img src="./imgBuilder" alt="验证码" class="verify-img right" id="verifyImg" />
                </div>
                <div class="col-sm-4">
                	<a href="javascript:" class="verifyImg-change" >看不清？换一个</a>
                </div>
            </div>
            <div class="row">
	            <div class="col-sm-offset-4 col-sm-4">
	                <button type="button" class="btn btn-submit">下一步</button>
	            </div>
            </div>
        </form>
    </div>
</div>
<script>
    seajs.use('module/password', function(mod){
		new mod.confirmAccountView({
            el: '#confirmAccount'
        });
	});    
</script>