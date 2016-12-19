<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.userName = '${personalData.userName}';
	G.userStatus = '${personalData.userStatus}';
</script>
<style> 
	.content{height: 642px;}
	.block-body > .row{line-height: 30px;}
    .account-types > div{padding-right: 0;}
    
    .content-title > span{font-size: 12px;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a> </span> &gt;
		<span>个人资料</span>
    </div>
	<div class="outer-content">
		<div class="content-wrapper">
	        <div id="content" class="content content-offset-left">
	            <div class="content-title">
	            	基本信息 
	                <c:if test="${personalData.userStatus == 1}">
	                    <a href="#member/personal/identity-verification.action" class="btn btn-link" role="button">(未认证，点击实名认证)</a>
	                </c:if>
	                <c:if test="${personalData.userStatus == 2}">
	                   	<span>您已经提交过信息，信息审核中，请耐心等待</span>
	                </c:if>
	            </div>
	            <div class="block-body">
	                <div class="row">
	                    <div class="col-md-2 control-label text-right"><span>用户名：</span></div>
	                    <div class="col-md-3"><span>${personalData.userName}</span></div>
	                    <div class="col-md-7"><a href="javascript:" class="modify" role="button" data-type="username">编辑用户名</a></div>
	                </div>
	                <div class="row">
	                    <div class="col-md-2 control-label text-right"><span>手机号码：</span></div>
	                    <div class="col-md-3"><span>${personalData.userMobile}</span></div>
	                    <div class="col-md-7"><a href="javascript:" class="modify" role="button" data-type="mobile">更换手机号码</a></div>
	                </div>
	                <div class="row">
	                    <div class="col-md-2 control-label text-right"><span>绑定邮箱：</span></div>
	                    <div class="col-md-3"><span>${empty personalData.userEmail ? "未绑定" : personalData.userEmail}</span></div>
	                    <div class="col-md-7"><a href="javascript:" class="modify" role="button" data-type="email">${empty personalData.userEmail ? "绑定邮箱" : "更换绑定邮箱"}</a></div>
	                </div>
	
	                <div class="row">
	                    <div class="col-md-2 control-label text-right"><span>账户类型：</span></div>
	                    <div class="col-md-10">
	                        <div class="row account-types">
	                            <c:forEach items="${userTypeList}" var="item">
	                            	<c:if test="${personalData.userStatus != 3}">
	                            		<div class="col-md-2">
	                            			<span class="radio-inline">
			                                    <input type="radio" name="account-type" readonly="readonly" class="account-type account-type-${item.typeKey}" value="${item.typeKey}" ${item.typeKey == personalData.userType ? "checked" : ""} />
			                                    ${item.typeName}
		                                    </span>
		                                </div>
	                            	</c:if>
	                            	<c:if test="${personalData.userStatus == 3 && item.typeKey == personalData.userType}">
	                            		<div class="col-md-2">
	                            			<span class="radio-inline">
		                                    	<input type="radio" name="account-type" readonly="readonly" class="account-type account-type-${item.typeKey}" value="${item.typeKey}" checked />
		                                    	${item.typeName}
		                                    </span>
		                                </div>
	                            	</c:if>
	                            </c:forEach>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
        <%@ include file="../personal-menu.jsp"%>
    </div>
</div>
<script type="text/html" id="tmpl-verify-username">
    <div class="row">
        <label class="col-md-3 control-label">用户名<span class="required">*</span></label>
		<div class="col-md-5">
            <input type="text" name="userName" placeholder="请输入用户名"/>
		</div>
		<div class="col-md-9 col-md-offset-3 comment">
			<span>4~16个字符以内，只能包含英文符号、数字以及符号”@“、”_“、”-“</span>
		</div>
	</div>
</script>

<script type="text/html" id="tmpl-verify-mobile">
	<div class="row">
		<label class="col-md-3 control-label">原手机号码</label>
        <div class="col-md-5">
        	<span>${personalData.userMobile}</span>
            <input type="hidden" name="oldMobile" value="${personalData.userMobile}" />
        </div>
        <div class="col-md-3">
        	<button type="button" id="btn-oldSmsCode" class="btn btn-submit" role="button">发送验证码</button>
        </div>
        <div class="col-md-9 col-md-offset-3 comment">
        	如原手机号码已丢失或停用，请<a href="javascript:">联系客服人员</a>修改
        </div>
	</div>
    <div class="row">
    	<label class="col-md-3 control-label">原手机验证码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="oldMobileVerification" placeholder="请输入验证码"/>
        </div>
    </div>
    <div class="row">
    	<label class="col-md-3 control-label">新手机号码<span class="required">*</span></label>
        <div class="col-md-5">
        	<input type="text" name="newMobile" placeholder="请输入新手机号码"/>
        </div>
        <div class="col-md-3">
        	<button type="button" id="btn-newSmsCode" class="btn btn-submit" role="button">发送验证码</button>
        </div>
	</div>
    <div class="row">
    	<label class="col-md-3 control-label">新手机验证码<span class="required">*</span></label>
        <div class="col-md-5">
        	<input type="text" name="newMobileVerification" placeholder="请输入验证码"/>
        </div>
    </div>
</script>

<script type="text/html" id="tmpl-verify-email">
	<div class="row">
    	<label class="col-md-3 control-label">邮箱地址<span class="required">*</span></label>
        <div class="col-md-5">
        	<input type="text" name="userEmail" placeholder="请输入邮箱地址"/>
        </div>
        <div class="col-md-3">
        	<button class="btn btn-submit" id="btn-emailCode" type="button" >发送验证码</button>
        </div>
    </div>
	<div class="row">
    	<label class="col-md-3 control-label">验证码<span class="required">*</span></label>
        <div class="col-md-5">
        	<input type="text" name="emailRandomCode" placeholder="请输入验证码"/>
        </div>
    </div>
</script>

<script id="tmpl-personalDataModal" type="text/html">
	<div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-label">修改</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-submit" id="btn-submit">确定修改</button>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript">
	seajs.use('module/member/personal/personal-data', function(mod) {
		mod.run();
	});	
</script>