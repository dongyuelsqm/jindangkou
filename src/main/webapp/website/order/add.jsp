<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    .search-input{width: auto;}

    ul.product-list{border: 1px solid #cdcdcd; box-shadow: 0 0 3px #aaa; width: 324px;}
    ul.product-list > li{background-color: #fff; line-height: 30px; overflow: hidden; padding: 10px;}
        ul.product-list > li > span{float: left; padding: 10px;}
        ul.product-list > li > img{float: left; height: 100px; width: 100px;}

    ul.inventory > li{line-height: 30px; margin-bottom: 5px; overflow: hidden; width: 20%;}
        ul.inventory > li > label,
        ul.inventory > li div{padding-left: 0; padding-right: 0;}
        ul.inventory > li > div > div{float: left; margin-bottom: 5px;}
            ul.inventory > li > div input{padding-left: 5px; width: 60px;}

    .table{border-bottom: 1px solid #ddd;}
        .table .row{line-height: 30px;}

    .nest{margin-bottom: 10px;}
    .dropdown{width: auto;}
        .dropdown ul{max-height: 400px; overflow-y: auto;}
        .dropdown ul > li > a{line-height: normal; height: auto; padding-left: 15px; padding-right: 15px; width: auto;}
</style>
<h1 class="content-title">添加订单</h1>
<form class="block-body form" id="order-form">
    <div class="row">
        <label class="col-md-3 control-label">添加商品</label>
        <div class="col-md-5">
            <div class="input-group search-input">
                <input type="text" class="form-control search-text" name="" placeholder="输入货号或商品名称进行搜索">
                <span class="input-group-addon btn-search" role="btn-search">搜索</span>
            </div>
            <ul class="product-list" id="product-list"></ul>
        </div>
    </div>
    <div class="row">
        <table class="table" id="order-table">
            <thead>
                <tr>
                    <th>已选商品</th>
                    <th>填写数量</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <!--
    <div class="row">
        <label class="col-md-3 control-label">运费<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="" placeholder="请输入买家用户名" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">交易状态<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="name" placeholder="请输入买家用户名" value=""/>
        </div>
    </div>
    -->
    <div class="row">
        <label class="col-md-3 control-label">买家用户名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="name" placeholder="请输入买家用户名" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">应收款<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="" class="input-icon input-icon-price" placeholder="请输入应收款" value=""/>
        </div>
    </div>
    <hr/>
    <div class="row">
        <label class="col-md-3 control-label">收货人<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="receiver" placeholder="请输入收货人" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">联系方式<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="phone" placeholder="请输入联系方式" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">收货地址<span class="required">*</span></label>
        <div class="col-md-5">
            <div class="row nest">
                <div class="col-md-4">
                    <div class="dropdown" id="province_dropdown">
                        <div class="input-group">
                            <input type="text" readonly class="form-control" value="省份" placeholder="" />
                            <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                        </div>
                        <input type="hidden" name="province" id="province" value="" />
                        <ul class="ul-dropdown" id="province_select"></ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="dropdown" id="city_dropdown">
                        <div class="input-group">
                            <input type="text" readonly class="form-control" value="城市" placeholder="" />
                            <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                        </div>
                        <input type="hidden" name="city" id="city" value="" />
                        <ul class="ul-dropdown" id="city_select"></ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="dropdown" id="district_dropdown">
                        <div class="input-group">
                            <input type="text" readonly class="form-control" value="区/县" placeholder="" />
                            <span class="input-group-addon" role="select" ><i class="iconfont icon-unfold"></i></span>
                        </div>
                        <input type="hidden" name="district" id="district" value="" />
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
        <div class="col-md-offset-3 col-md-5 text-center">
            <button type="button" class="btn btn-submit" data-do="submit" role="btn-submit">提交</button>
        </div>
    </div>
</form>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-orderItem">
    <li>
        <img src="{{pictures}}" alt="{{name}}" />
        <span>货号: {{code}}</br>{{name}}</span>
        <span class="">${{price}}</span>
        <span class="checkbox-inline pull-right">
            <input type="checkbox" data-productid="{{id}}" role="product" value=""/>
        </span>
    </li>
</script>

<script type="text/html" id="tmpl-productItem">
    <td>
        <div style="width: 220px;">
            <img src="{{pictures}}" alt="{{name}}" />{{name}}
        </div>
    </td>
    <td>
        <div style="width: 700px;">
            <ul class="inventory"></ul>
        </div>
    </td>
</script>

<script type="text/html" id="tmpl-inventoryItem">
    <li >
        <label class="col-md-5 text-right">{{model}}：</label>
        <div class="col-md-7 inventory-size">
            <input type="text" role="quantity" data-size="{{sizeCode}}" data-color="{{colorCode}}" data-productid="{{productId}}" class="input-icon input-icon-count" placeholder="{{number}}" value=""/>
        </div>
    </li>
</script>

<script type="text/javascript">
    seajs.use('module/order/add', function(mod){
        mod.run();
    });
</script>
</body>
</html>
