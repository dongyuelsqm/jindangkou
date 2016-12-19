<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<style>
    .content{height: 642px;}
    .form{height: 230px; margin-top: -115px; position: absolute; top: 50%; width: 100%;}
    a.register{color: #242424; text-decoration: underline;}
    .error{color: #4d4d4d; display: none; line-height: 25px; text-align: center; }
    .error .iconfont{color: #e11377;}
</style>
<%@ include file="./common/top.jsp"%>
<h1 class="content-title">
    账号登录
    <!-- <span class="cor-grey">Account login</span>  -->
</h1>
<form role="form" class="form" id="form-login">
    <div class="error text-center">
        <i class="iconfont">&#xe635;</i>
        <span class="error-msg"></span>
    </div>
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <div class="input-group">
                <span class="input-group-addon"><i class="iconfont">&#xe656;</i></span>
                <input type="text" class="form-control" name="userName" id="userName" value="" placeholder="请输入您的用户名/邮箱/手机号码"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <div class="input-group">
                <span class="input-group-addon"><i class="iconfont">&#xe660;</i></span>
                <input type="password" class="form-control" name="userPassword" id="userPassword" value="" placeholder="请输入您的密码"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4">
            <input type="text" class="form-control verify-code left" name="verifyCode" id="verifyCode" placeholder="请输入验证码" />
            <img src="./imgBuilder" alt="验证码" class="verify-img pull-right" id="verifyImg" />
        </div>
        <div class="col-sm-3">
            <a href="javascript:" class="verifyImg-change" >看不清？换一个</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
	                <span class="checkbox-inline">
	                    <input type="checkbox" class="checkbox" id="isRemember"/> 记住密码
	                </span>
            <a href="#password!confirmAccount.action" class="forget-pwd pull-right" >忘记密码</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <button type="button" class="btn btn-submit btn-login">提交</button>
        </div>
    </div>
    <div class="text-center">
        <a href="#register.action" class="register" >免费注册</a>
    </div>
</form>
<%@include file="./common/footer.jsp"%>

<script type="text/javascript">
    seajs.use('module/login', function(mod){
        mod.run();
    });
</script>