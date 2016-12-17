<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.userStatus = '${userStatus}';
	G.userType = '${userType}';
	try{
		G.identityData = eval('(${identityData})');
	}catch(e){
		console.log('${identityData}');
		G.identityData = {};
	}
</script>
<link rel="stylesheet" href="${contextPath}/res/css/plugins/jquery-uploadify/uploadify.css" />
<style>	
   	.form .row.nest {margin: 0 -15px 10px -15px; }
   	.form .img-block{margin-top: 10px; display: none;}
   	.form img{width: 100%;}
   	#identity-vertification-container{border-top: 1px solid #cdcdcd;}
</style>
<div class="container">
	<div class="breadcrumb">
	    <span><a href="#member/personal-center.action">个人中心</a></span> &gt;
	    <span><a href="#member/personal/personal-data.action">个人资料</a></span> &gt;
	    <span>实名认证</span>
	</div>
	<div class="outer-content">
		<div class="content-wrapper" id="content">
			<div class="content-offset-left content">
				<h1 class="content-title">实名认证</h1>
				<form class="block-body form">
					<div class="row">
						<div class="col-md-2 control-label">认证类型：</div>
						<div id="verify-types" class="col-md-10 ">
							<c:if test="${userStatus != 3}">
								<div class="radio-inline" >
									<input type="radio" class="radiobox" name="userType" data-type="personal" ${userType == 2 || userType == 1 ? "checked" : ""} ${userStatus == 2 ? "readonly" : ""} />个人开发者
								</div>
								<div class="radio-inline" >
									<input type="radio" class="radiobox" name="userType" data-type="company" ${userType == 3 ? "checked" : ""} ${userStatus == 2 ? "readonly" : ""} />企业用户
								</div>
							</c:if>
							<c:if test="${userStatus == 3}">
								<c:if test="${userType != 3}">
								<div class="radio-inline" >
									<input type="radio" class="radiobox" name="userType" data-type="personal" ${userType == 2 ? "checked" : ""} />个人开发者
								</div>
								</c:if>
								<c:if test="${userType != 2}">
								<div class="radio-inline" >
									<input type="radio" class="radiobox" name="userType" data-type="company" ${userType == 3 ? "checked" : ""} />企业用户
								</div>
								</c:if>
							</c:if>
						</div>
						<c:if test="${userStatus == 2}">
						<div class="col-md-10 col-md-offset-2 comment">
							您已经提交过信息，信息审核中，请耐心等待
						</div>
						</c:if>
					</div>
				</form>
				<div id="identity-vertification-container" class="block-body"></div>
			</div>
		</div>
		<%@ include file="../personal-menu.jsp"%>
	</div>
</div>

<script id="tmpl-verify-personal" type="text/html">
	<input type="hidden" name="userStautus" value="${userStatus}"/>
	<div class="row">
		<label class="col-md-3 control-label">真实姓名<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="realName" id="realName" placeholder="请输入真实姓名" value="{{realName}}"/>
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{realName}}</p>
		</div>
		</c:if>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-9 col-md-offset-3 comment">请输入与身份证或护照相符的真实姓名</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">证件类型<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<select name="certificateType" id="certificateType" value="{{certificateType}}">
				<c:forEach var="type" items="${typeList}" varStatus="status">
				<option value="${type.typeKey}" <c:if test="${status.first}">selected</c:if> >${type.typeName}</option>
				</c:forEach>
			</select>
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<# 	var certificateTypeStr = '';
				if(certificateType == 0){
					certificateTypeStr = '身份证';
				}else if(certificateType == 1){
					certificateTypeStr = '护照';
				}
			#>
			<p class="form-static">{{certificateTypeStr}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">证件号码<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="certificateNo" id="certificateNo" placeholder="请输入证件号码" value="{{certificateNo}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
				<p class="form-static">{{certificateNo}}</p>
		</div>
		</c:if>
	</div>
	<c:if test="${userStatus == 1 || userStatus == 4}">
	<div class="row">
		<label class="col-md-3 control-label">证件照片<span class="required">*</span></label>
		<div class="col-md-9 comment">确保证件内容清晰可见，控制图片的大小在2M以内，支持PNG，JPG格式</div>
	</div>
	</c:if>
	<div class="row">
		<label class="col-md-3 control-label">正面照片<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="certificatePathZm" id="certificatePathZm" readonly="readonly" value="{{certificatePathZm}}" />
		</div>
		<div class="col-md-2">
			<button class="btn btn-submit btn-upload" id="btn-upload-0" type="button" data-index="0" >上传照片</button>
		</div>
		</c:if>
		<div class="col-sm-4 <c:if test="${userStatus == 1 || userStatus == 4}">col-md-offset-3 img-block</c:if>">
			<img src="{{certificatePathZm}}" alt="" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">反面照片<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="certificatePathFm" id="certificatePathFm" readonly="readonly" value="{{certificatePathFm}}" />
		</div>
		<div class="col-md-2">
			<button class="btn btn-submit btn-upload" id="btn-upload-1" type="button" data-index="1" >上传照片</button>
		</div>
		</c:if>
		<div class="col-md-4 <c:if test="${userStatus == 1 || userStatus == 4}">col-md-offset-3 img-block</c:if>">
			<img src="{{certificatePathFm}}" alt="" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">个人网站或APP名称</label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="personalWebsite" id="personalWebsite" placeholder="请输入个人网站或APP名称" value="{{personalWebsite}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{personalWebsite}}</p>
		</div>
		</c:if>    
	</div>
	<div class="row">
		<label class="col-md-3 control-label">个人网站或APP简介</label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<textarea name="personalWebsiteIntro" id="personalWebsiteIntro" placeholder="请输入个人网站或APP简介" >{{personalWebsiteIntro}}</textarea>
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{personalWebsiteIntro}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">个人网站地址或APP下载地址</label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="personalWebsiteUrl" id="personalWebsiteUrl" placeholder="请输入个人网站或APP下载地址" value="{{personalWebsiteUrl}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{personalWebsiteUrl}}</p>
		</div>
		</c:if>
	</div>
	<c:if test="${userStatus == 1 || userStatus == 4}">
	<div class="row">
		<div class="col-md-9 col-md-offset-3">
			<span class="agreement checkbox-inline">
				<input type="checkbox" class="checkbox" id="isRead"/>我同意
			</span>
			<a href="${protocolUrl}" class="show-protocol" target="_blank">《认证协议》</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-md-offset-3">
			<button type="button" class="btn btn-submit" id="btn-submit">
				<c:if test="${userStatus == 1}">提交认证</c:if>
				<c:if test="${userStatus == 4}">修改</c:if>
			</button>
		</div>
	</div>
	</c:if>
</script>

<script id="tmpl-verify-company" type="text/html">
	<input type="hidden" name="userStautus" value="${userStatus}"/>
	<div class="row">
		<div class="col-md-12">
   			<b>公司基本信息</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">公司名称<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="companyName" id="companyName" placeholder="请输入公司名称" value="{{companyName}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{companyName}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">官网地址<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="websiteUrl" id="websiteUrl" placeholder="请输入官网地址" value="{{websiteUrl}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{websiteUrl}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">公司地址<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<div class="row nest">
				<div class="col-md-4">
					<select id="provinceId" name="provinceId" data-name="{{province}}" >
						<option selected value="">省份</option>
					</select>
					<input type="hidden" id="province" name="province" value="{{province}}" />
				</div>
				<div class="col-md-4">
					<select id="cityId" name="cityId" disabled data-name="{{city}}">
						<option selected value="">市区</option>
					</select>
					<input type="hidden" id="city" name="city" value="{{city}}" />
				</div>
				<div class="col-md-4">
					<select id="streetId" name="streetId" disabled data-name="{{street}}">
						<option selected value="">街道</option>
					</select>
					<input type="hidden" id="street" name="street" value="{{street}}" />
				</div>
			</div>
		</div>
		<div class="col-md-5 col-md-offset-3">
			<input type="text" placeholder="请输入详细地址" id="companyAddr-detail" disable name="companyAddrDetail" value="{{companyAddrDetail}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{newCompanyAddr}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">公司简介<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<textarea name="companyIntro" id="companyIntro" placeholder="请输入公司简介" >{{companyIntro}}</textarea>
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{companyIntro}}</p>
		</div>
		</c:if>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-12">
			<b>营业执照信息</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">营业执照编号<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="licenseNo" id="licenseNo" placeholder="请输入营业执照编号" value="{{licenseNo}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{licenseNo}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">营业执照照片<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="licensePath" id="licensePath" readonly="readonly" value="{{licensePath}}" />
		</div>
		<div class="col-md-2">
			<button class="btn btn-submit btn-upload" id="btn-upload-0" type="button" data-index="0" >上传照片</button>
		</div>
		</c:if>
		<div class="col-md-4 <c:if test="${userStatus == 1 || userStatus == 4}">col-md-offset-3 img-block</c:if>">
			<img src="{{licensePath}}" alt="" />
		</div>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-9 col-md-offset-3 comment">确保证件内容清晰可见，控制图片的大小在2M以内，支持PNG，JPG格式</div>
		</c:if>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-12">
			<b>组织机构代码和税务登记证信息(两者都需上传)</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">组织机构代码<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="orgCode" id="orgCode" placeholder="请输入组织机构代码" value="{{orgCode}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{orgCode}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">组织机构代码照片<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="orgPath" id="orgPath"  readonly="readonly" value="{{orgPath}}" />
		</div>
		<div class="col-md-2">
			<button class="btn btn-submit btn-upload" id="btn-upload-1" type="button" data-index="1">上传照片</button>
		</div>
		</c:if>
		<div class="col-md-4 <c:if test="${userStatus == 1 || userStatus == 4}">col-md-offset-3 img-block</c:if>">
			<img src="{{orgPath}}" alt="" />
		</div>
		<c:if test="${userStatus == 1 || userStatus == 4}">
			<div class="col-md-9 col-md-offset-3 comment">确保证件内容清晰可见，控制图片的大小在2M以内，支持PNG，JPG格式</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">税务登记编号<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="taxRegistrationNo" id="taxRegistrationNo" placeholder="请输入税务登记编号" value="{{taxRegistrationNo}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{taxRegistrationNo}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">税务登记编号照片<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="taxRegistrationPath" id="taxRegistrationPath" readonly="readonly" value="{{taxRegistrationPath}}" />
		</div>
		<div class="col-md-2">
			<button class="btn btn-submit btn-upload" id="btn-upload-2" type="button" data-index="2" >上传照片</button>
		</div>
		</c:if>
		<div class="col-md-4 <c:if test="${userStatus == 1 || userStatus == 4}">col-md-offset-3 img-block</c:if>">
			<img src="{{taxRegistrationPath}}" alt="" />
		</div>
		<c:if test="${userStatus == 1 || userStatus == 4}">
			<div class="col-md-9 col-md-offset-3 comment">确保证件内容清晰可见，控制图片的大小在2M以内，支持PNG，JPG格式</div>
		</c:if>
	</div>
	<hr>
 	<div class="row">
		<div class="col-md-12">
			<b>联系人信息</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">企业联系人<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="contactMan" id="contactMan" placeholder="请输入企业联系人" value="{{contactMan}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{contactMan}}</p>
		</div>
		</c:if>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">联系电话<span class="required">*</span></label>
		<c:if test="${userStatus == 1 || userStatus == 4}">
		<div class="col-md-5">
			<input type="text" name="contactWay" id="contactWay" placeholder="请输入联系电话" value="{{contactWay}}" />
		</div>
		</c:if>
		<c:if test="${userStatus == 2 || userStatus == 3}">
		<div class="col-md-9">
			<p class="form-static">{{contactWay}}</p>
		</div>
		</c:if>
	</div>
	<c:if test="${userStatus == 1 || userStatus == 4}">
	<div class="row">
		<div class="col-md-9 col-md-offset-3">
			<span class="agreement checkbox-inline">
				<input type="checkbox" class="checkbox" id="isRead"/>我同意
			</span>
			<a href="${protocolUrl}" class="show-protocol" target="_blank">《认证协议》</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-md-offset-3">
			<button type="button" class="btn btn-submit" id="btn-submit">
				<c:if test="${userStatus == 1}">提交认证</c:if>
				<c:if test="${userStatus == 4}">修改</c:if>
			</button>
		</div>
	</div>
	</c:if>
</script>

<script id="tmpl-identityVerificationModal" type="text/html">
	<div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-label">认证</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-submit" id="btn-submit">提交认证</button>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript">
	seajs.use('module/member/personal/identity-verification', function(mod) {
		mod.run();
	});	
</script>