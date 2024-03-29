<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    ul.inventory > li{margin-bottom: 5px; overflow: hidden;}
    ul.inventory > li > label,
    ul.inventory > li div{padding-left: 0; padding-right: 0;}
    ul.inventory > li > div > div{float: left; margin-bottom: 5px;}
        ul.inventory > li > div input{padding-left: 5px; width: 40px;}
</style>
<h1 class="content-title">添加商品</h1>
<form class="block-body form" id="product-form">
    <div class="row">
        <label class="col-md-3 control-label">货号</label>
        <div class="col-md-5">
            <input type="text" name="code" id="code" placeholder="请输入货号" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">商品名称<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="name" id="name" placeholder="请输入商品名称" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">销售价<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="price" id="price" class="input-icon input-icon-price" placeholder="请输入售价" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">商品分类<span class="required">*</span></label>
        <div class="col-md-5">
            <div class="dropdown" id="department-dropdown">
                <div class="input-group">
                    <input type="text" readonly class="form-control"  placeholder="" />
                    <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                </div>
                <input type="hidden" name="department" />
                <ul class="ul-dropdown"></ul>
            </div>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">颜色<span class="required">*</span></label>
        <div class="col-md-5" id="color-wrapper"></div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">尺码</label>
        <div class="col-md-5" id="size-wrapper"></div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">起批件数<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="minimum" id="minimum" class="input-icon input-icon-count" placeholder="请输入起批件数" value=""/>
        </div>
        <div class="col-md-9 col-md-offset-3 comment">若该商品为零售，则不标明起批件数</div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">宝贝图片<span class="required">*</span></label>
        <div class="col-md-9 comment">尺寸750*750像素以上，大小2M以下，默认显示第1张，最多5张(可拖拽图片调整显示顺序)</div>
        <div class="col-md-5 col-md-offset-3">
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="上传文件" class="upload-default upload-img" role="upload-img" />
        </div>
        <button type="button" id="btn-img" style="display: none;"></button>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">宝贝视频<span class="required">*</span></label>
        <div class="col-md-9 comment">请保证视频大小为20M以下</div>
        <div class="col-md-5 col-md-offset-3" >
            <video loop="loop" src="" autoplay="autoplay" id="video" role="upload-video" style="display: none;" class="upload-video"></video>
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="上传视频" role="upload-video" class="upload-img upload-default" />
        </div>
        <button type="button" id="btn-video" style="display: none;"></button>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">商品描述<span class="required">*</span></label>
        <div class="col-md-5">
            <textarea name="descriptive" id="descriptive" placeholder="请输入商品描述"></textarea>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">运费模板<span class="required">*</span></label>
        <div class="col-md-5">
            <div class="dropdown" id="postal-dropdown">
                <div class="input-group">
                    <input type="text" readonly class="form-control"  placeholder="" />
                    <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                </div>
                <input type="hidden" name="postal" />
                <ul class="ul-dropdown">
                    <li class="selected"><a href="javascript:" data-val="300000">包邮</a></li>
                    <li><a href="javascript:" data-val="300001">不包邮</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">库存<span class="required">*</span></label>
        <div class="col-md-5">
            <ul class="inventory" id="inventory"></ul>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-3 col-md-5">
            <button type="button" class="btn btn-submit" data-do="submit" role="btn-submit">提交</button>
        </div>
    </div>
</form>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-inventoryItem">
    <li data-color="{{colorCode}}" id="inventory-{{colorCode}}">
        <label class="col-md-2">{{colorName}}：</label>
        <div class="col-md-10 inventory-size"></div>
    </li>
</script>

<script type="text/html" id="tmpl-sizeItem">
    <div class="col-md-3 inventory-{{sizeCode}}">
        {{sizeName}}: <input type="text" role="quantity" data-size="{{sizeCode}}" class="input-icon input-icon-count" placeholder="" value=""/>
    </div>
</script>

<script type="text/html" id="tmpl-color">
    <span class="checkbox-inline">
        <input type="checkbox" role="color" value="1" data-name="{{name}}" /> {{name}}
    </span>
</script>

<script type="text/html" id="tmpl-size">
    <span class="checkbox-inline">
        <input type="checkbox" role="size" value="1" data-name="{{name}}" /> {{name}}
    </span>
</script>

<script type="text/javascript">
    seajs.use('module/product/add', function(mod){
        mod.run();
    });
</script>
