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
            <select name="department" >
                <option value="0">羽绒服</option>
                <option value="1">呢大衣</option>
                <option value="2">毛衣</option>
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
    -->
    <div class="row">
        <label class="col-md-3 control-label">颜色<span class="required">*</span></label>
        <div class="col-md-5">
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="red"/> 红色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="coffee"/> 咖啡色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="blue"/> 蓝色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="yellow"/> 黄色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="pink"/> 粉色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="oringe"/> 橙色
            </span>
            <span class="checkbox-inline">
                <input type="checkbox" name="color" role="color" value="purple"/> 紫色
            </span>
        </div>
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
            <button type="button" id="btn-img" style="display: none;"></button>
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="上传文件" class="upload-default" role="upload-img" />
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">宝贝视频<span class="required">*</span></label>
        <div class="col-md-9 comment">请保证视频大小为20M以下</div>
        <div class="col-md-5 col-md-offset-3" >
            <button type="button" id="btn-video" style="display: none;"></button>
            <video loop="loop" src="" autoplay="autoplay" id="video" role="upload-video" style="display: none;" ></video>
            <img src="${contextPath}/res/css/base/img/upload-default.png" alt="上传视频" style="display: none;" role="upload-video" id="video-default" class="upload-default" />
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
            <select name="postal" >
                <option value="300000">包邮</option>
                <option value="300001">不包邮</option>
            </select>
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
    <li data-color="blue" id="inventory-{{colorCode}}">
        <label class="col-md-2">{{colorName}}：</label>
        <div class="col-md-10">
            <div class="col-md-3">SX: <input type="text" role="quantity" data-size="sx" class="input-icon input-icon-count" placeholder="" value=""/></div>
            <div class="col-md-3">S: <input type="text" role="quantity" data-size="s" class="input-icon input-icon-count" placeholder="" value=""/></div>
            <div class="col-md-3">M: <input type="text" role="quantity"  data-size="m" class="input-icon input-icon-count" placeholder="" value=""/></div>
            <div class="col-md-3">L: <input type="text" role="quantity"  data-size="l" class="input-icon input-icon-count" placeholder="" value=""/></div>
            <div class="col-md-3">XL: <input type="text" role="quantity" data-size="xl" class="input-icon input-icon-count" placeholder="" value=""/></div>
            <div class="col-md-3">XXL: <input type="text" role="quantity" data-size="xxl" class="input-icon input-icon-count" placeholder="" value=""/></div>
            <div class="col-md-3">XXXL: <input type="text" role="quantity" data-size="xxxl" class="input-icon input-icon-count" placeholder="" value=""/></div>
        </div>
    </li>
</script>
<script type="text/javascript">
    seajs.use('module/product/add', function(mod){
        mod.run();
    });
</script>
