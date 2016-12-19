<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.exponent = '${exponent}';
	G.modulus = '${modulus}';
</script>
<style>
	.content{height: 642px;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a> </span> &gt;
        <span><a href="#member/personal/personal-data.action">个人资料</a></span> &gt;
		<span>密码修改</span>
    </div>
	<div class="outer-content">
		<div class="content-wrapper">
	        <div id="content" class="content content-offset-left">
	        	<h1 class="content-title">基本信息</h1>
	            <div class="block-body">
	                <form id="password-form" class="password-container form">
	                    <div class="row">
	                        <div class="col-md-2 description"><span>用户名：</span></div>
	                        <div class="col-md-5">
	                            <span>${userName}</span>
	                            <input type="hidden" name="userName" value="${userName}" />
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-2 description"><span>原始密码：</span></div>
	                        <div class="col-md-5">
	                            <input type="password" name="pwd" id="pwd" />
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-2 description"><span>新密码：</span></div>
	                        <div class="col-md-5">
	                            <input type="password" name="pwdnew" id="pwdnew" />
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-2 description"><span>新密码确认：</span></div>
	                        <div class="col-md-5">
	                            <input type="password" name="pwdconfirm" id="pwdconfirm" />
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-5 col-md-offset-2">
	                            <button type="button" class="btn btn-submit">确认</button>
	                        </div>
	                    </div>
	                </form>
	            </div>
	        </div>
        </div>
        <%@ include file="../personal-menu.jsp"%>
    </div>
</div>

<script type="text/javascript">
	seajs.use('module/member/personal/password', function (mod) {
		mod.run();
	});	
</script>