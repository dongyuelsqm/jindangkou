<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.exponent = '${exponent}';
	G.modulus = '${modulus}';
</script>
<style>
	.content{height: 642px;}
    .form{height: 350px; margin-top: -175px; position: absolute; top: 50%; width: 100%;}
        
    .pro-content{text-indent: 2em;}
</style>
<div class="container">
    <div class="breadcrumb"></div>
    <div class="content">
    	<h1 class="content-title">
    		用户注册
    		<!-- <span class="cor-grey">User registration</span> -->
    	</h1>
        <form role="form" class="form clearfix" id="form-register">
<!--
            <div class="form-group">
                <label class="col-sm-3 control-label" for="userName">用户名<span class="required">*</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="userName" id="userName" value="" placeholder="请输入您的用户名"/>
                </div>
            </div>
-->
            <div class="row">
                <label class="col-sm-4 control-label" for="userMobile">手机号码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="userMobile" id="userMobile" value="" placeholder="请输入您的手机号码"/>
                </div>
                <div class="col-sm-12 col-sm-offset-4 comment">
                	<button class="btn btn-mobileCode btn-valideCode" type="button" >发送验证码</button>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 control-label" for="mobileCode">短信验证码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="mobileCode" id="mobileCode" value="" placeholder="请输入短信验证码"/>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 control-label" for="userPassword">密码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="userPassword" id="userPassword" value="" placeholder="请输入您的密码"/>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 control-label" for="userPasswordConfirm">确认密码<span class="required">*</span></label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="userPasswordConfirm" id="userPasswordConfirm" value="" placeholder="请输入确认密码"/>
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
	                <span class="isReaded checkbox-inline">
	                    <input type="checkbox" class="checkbox" name="agree" id="isRead" />已阅读 
	                </span>
	                <a href="${protocolUrl}" class="show-protocol" target="_blank">《注册协议》</a>
                </div>
            </div>
            <div class="row">
            	<div class="col-sm-offset-4 col-sm-4">
	                <button type="button" class="btn btn-submit btn-register pull-left">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script id="tmpl-registerModal" type="text/html">
	<div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-label">注册协议</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript">
	seajs.use('module/register', function(mod){
		mod.run();
	});	
</script>