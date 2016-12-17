<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<link rel="stylesheet" href="${contextPath}/res/css/module/password.css" />
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.exponent = '${exponent}';
	G.modulus = '${modulus}';
</script>
<style>
   .form .btn{margin-right: 0; width: 46%;}
</style>
<div class="container">
    <div class="breadcrumb">找回密码</div>
    <div class="content">
    	<!-- <h1 class="content-title">找回密码</h1> -->
        <div class="pwd-steps clearfix">
            <div class="pwd-step actived">1. 确认账号</div>
            <div class="step-arrow step-arrow-1"></div>
            <div class="pwd-step actived">2. 验证身份</div>
            <div class="step-arrow step-arrow-2"></div>
            <div class="pwd-step active">3. 重置密码</div>
            <div class="step-arrow step-arrow-3"></div>
        </div>
        <form role="form" class="form form-pwd" id="resetPassword">
            <div class="row">
                <label class="col-sm-4 control-label" for="newPassword">新密码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="newPassword" id="newPassword" value="" placeholder="请输入您的新密码"/>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 control-label" for="confirmPassword">确认密码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" value="" placeholder="请输入确认密码"/>
                </div>
            </div>
            <div class="row">
	            <div class="col-sm-offset-4 col-sm-4">
	                <button type="button" class="btn btn-default pull-left btn-back">上一步</button>
	                <button type="button" class="btn btn-submit pull-right">下一步</button>
	            </div>
            </div>
        </form>
    </div>
</div>
<script>
    seajs.use('module/password', function(mod){
		new mod.resetPasswordView({
            el: '#resetPassword'
        });
	});    
</script>