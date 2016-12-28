<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">商户信息</h1>
<form class="block-body form" id="customer-form">
    <div class="row">
        <label class="col-md-3 control-label">用户名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="userName" placeholder="请输入用户名" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">手机号码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="userMobile" placeholder="请输入手机号码" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">QQ号码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="qq" placeholder="请输入QQ号码" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">常用邮箱<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="userEmail" placeholder="请输入常用邮箱" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">店铺名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="shopName" placeholder="请输入店铺名称" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">店铺形象<span class="required">*</span></label>
        <div class="col-md-9 comment">尺寸750*750像素以上，大小2M以下</div>
        <div class="col-md-5 col-md-offset-3">
            <button type="button" id="btn-img" style="display: none;"></button>
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="" class="upload-default" role="upload-img" />
            <input type="hidden" value="" name="pictures" role="upload"/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">发货地<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="address" placeholder="请输入发货地址" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">服务电话<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="telephone" id="" placeholder="请输入服务电话" value=""/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-3 col-md-5">
            <button type="button" class="btn btn-submit" data-do="submit" role="btn-submit">提交</button>
        </div>
    </div>
</form>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/shopping-chart/customer', function(mod){
        mod.run();
    });
</script>
