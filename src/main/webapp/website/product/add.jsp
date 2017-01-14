<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    .inventory > label{padding-right: 0;}
    .inventory > div input{margin-right: 20px; width: 40px;}
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
            <select name="department" >
                <option value="">羽绒服</option>
                <option value="">呢大衣</option>
                <option value="">毛衣</option>
            </select>
        </div>
    </div>
    <!--
    <div class="row">
        <label class="col-md-3 control-label">尺码</label>
        <div class="col-md-5">
            <span class="checkbox-inline">
                <input type="checkbox" name="size" value="xs"/> xs
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="size" value="s"/> s
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="size" value="m"/> m
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="size" value="x"/> x
            </span>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">颜色<span class="required">*</span></label>
        <div class="col-md-5">
            <span class="checkbox-inline">
                <input type="checkbox" name="color" value="red"/> 红色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" value="coffee"/> 咖啡色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" value="blue"/> 蓝色
            </span>
        </div>
    </div>
    -->
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
            <button type="button" id="btn-img" style="display: none;"></button>
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="" class="upload-default" role="upload-img" />
            <input type="hidden" value="" name="pictures" role="upload"/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">宝贝视频<span class="required">*</span></label>
        <div class="col-md-9 comment">请保证视频大小为20M以下</div>
        <div class="col-md-5 col-md-offset-3" >
            <button type="button" id="btn-video" style="display: none;"></button>
            <video loop="loop" src="" autoplay="autoplay" id="video" role="upload-video">
                <img src="${contextPath}/res/css/base/img/upload-default.png" alt="" class="upload-default"/>
            </video>
            <input type="hidden" value="" name="videos" role="upload"/>
        </div>
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
            <input type="text" name="postal" placeholder="" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">库存<span class="required">*</span></label>
        <div class="col-md-5 inventory">
            <label class="col-md-2">黄色：</label>
            <div class="col-md-10">
                SX: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
                M: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
                L: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
            </div>
            <label class="col-md-2">红色：</label>
            <div class="col-md-10">
                SX: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
                M: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
                L: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
            </div>
            <label class="col-md-2">蓝色：</label>
            <div class="col-md-10">
                SX: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
                M: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
                L: <input type="text" name="quantity" class="input-icon input-icon-count" placeholder="" value=""/>
            </div>
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
    seajs.use('module/product/add', function(mod){
        mod.run();
    });
</script>
