<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    .form .comment{line-height: 40px; margin-bottom: 0;  margin-top: 0; }

    .nest{margin-bottom: 10px;}
    .dropdown{width: auto;}
        .dropdown ul{max-height: 400px; overflow-y: auto;}
            .dropdown ul > li > a{line-height: normal; height: auto; padding-left: 15px; padding-right: 15px; width: auto;}
</style>
<h1 class="content-title">商户信息</h1>
<form class="block-body form" id="customer-form">
    <div class="row">
        <label class="col-md-3 control-label">用户名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="username" placeholder="请输入用户名" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">手机号码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="phone" placeholder="请输入手机号码" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">QQ号码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="QQ" placeholder="请输入QQ号码" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">常用邮箱<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="email" placeholder="请输入常用邮箱" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">店铺名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="storeName" placeholder="请输入店铺名称" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">店铺形象<span class="required">*</span></label>
        <div class="col-md-9 comment">尺寸750*750像素以上，大小2M以下</div>
        <div class="col-md-5 col-md-offset-3">
            <button type="button" id="btn-img" style="display: none;"></button>
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="" class="upload-default" role="upload-img" />
            <input type="hidden" value="" name="picture" role="upload"/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">发货地<span class="required">*</span></label>
        <div class="col-md-5">
            <div class="row nest">
                <div class="col-md-4">
                    <div class="dropdown" id="province_dropdown">
                        <div class="input-group">
                            <input type="text" readonly class="form-control" name="province" value="省份" placeholder="" />
                            <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                        </div>
                        <input type="hidden" id="province" value="" />
                        <ul class="ul-dropdown" id="province_select"></ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="dropdown" id="city_dropdown">
                        <div class="input-group">
                            <input type="text" readonly class="form-control" name="city" value="城市" placeholder="" />
                            <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                        </div>
                        <input type="hidden" id="city" value="" />
                        <ul class="ul-dropdown" id="city_select"></ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="dropdown" id="district_dropdown">
                        <div class="input-group">
                            <input type="text" readonly class="form-control" name="district" value="区/县" placeholder="" />
                            <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                        </div>
                        <input type="hidden" id="district" value="" />
                        <ul class="ul-dropdown" id="district_select"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-5 col-md-offset-3">
            <input type="text" placeholder="请输入详细地址" id="detail" name="detail" value="" />
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">服务电话<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="tel" placeholder="请输入服务电话" value=""/>
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
</body>
</html>
