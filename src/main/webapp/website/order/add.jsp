<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    ul.inventory > li{line-height: 30px; margin-bottom: 5px; overflow: hidden;}
        ul.inventory > li > label,
        ul.inventory > li div{padding-left: 0; padding-right: 0;}
        ul.inventory > li > div > div{float: left; margin-bottom: 5px;}
            ul.inventory > li > div input{padding-left: 5px; width: 40px;}

    .table{border-bottom: 1px solid #ddd;}
    .table .row{line-height: 30px;}
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
        </div>
    </div>
    <div class="row">
        <table class="table" id="order-table">
            <thead>
                <tr>
                    <th>已选商品</th>
                    <th>勾选商品属性</th>
                    <th>填写数量</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div style="width: 280px;">
                        <img src="" alt="" />
                        维多利亚同款保暖呢大衣保暖呢大衣批发
                    </div>
                </td>
                <td>
                    <div style="width: 280px;">
                        <div class="row">
                            <label class="col-md-3 control-label">尺码</label>
                            <div class="col-md-9">
                                <span class="checkbox-inline">
                                    <input type="checkbox" name="size" role="size" value="xs"/> xs
                                </span>
                                <span class="checkbox-inline">
                                    <input type="checkbox" name="size" role="size" value="s"/> s
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
                            <label class="col-md-3 control-label">颜色</label>
                            <div class="col-md-9">
                                <span class="checkbox-inline">
                                    <input type="checkbox" name="color" role="color" value="red"/> 红色
                                </span>
                                <span class="checkbox-inline">
                                    <input type="checkbox" name="color" role="color" value="coffee"/> 咖啡色
                                </span>
                                <span class="checkbox-inline">
                                    <input type="checkbox" name="color" role="color" value="blue"/> 蓝色
                                </span>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div style="width: 350px;">
                        <ul class="inventory" id="inventory"></ul>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">运费<span class="required">*</span></label>
        <div class="col-md-5">
            <select name="postal">
                <option value="300000">包邮</option>
                <option value="300001">不包邮</option>
            </select>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">交易状态<span class="required">*</span></label>
        <div class="col-md-5">
            <select name="status">
                <option value="0">待付款</option>
                <option value="1">交易成功</option>
                <option value="2">交易关闭</option>
            </select>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">买家用户名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="username" placeholder="请输入买家用户名" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">应收款<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="price" class="input-icon input-icon-price" placeholder="请输入应收款" />
        </div>
    </div>
    <br/>
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
            <input type="text" name="address" placeholder="请输入收货地址" value=""/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-3 col-md-5">
            <button type="button" class="btn btn-submit" data-do="submit" role="btn-submit">提交</button>
        </div>
    </div>
</form>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-order-item">
    <li>
        <img src="" alt="" />
        维多利亚同款保暖呢大衣保暖呢大衣批发
        <span>价格</span>
        <span class="checkbox-inline pull-right">
            <input type="checkbox" name="color" value="red"/> 红色
        </span>
    </li>
</script>

<script type="text/html" id="tmpl-">
    <td>
        <div style="width: 280px;">
            <img src="" alt="" />
            维多利亚同款保暖呢大衣保暖呢大衣批发
        </div>
    </td>
    <td>
        <div style="width: 280px;">
            <div class="row">
                <label class="col-md-3 control-label">颜色</label>
                <div class="col-md-9">
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
                <label class="col-md-3 control-label">尺码</label>
                <div class="col-md-5">
                    <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="xs"/> xs
                    </span>
                            <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="s"/> s
                    </span>
                            <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="m"/> m
                    </span>
                            <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="x"/> x
                    </span>
                            <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="xl"/> xl
                    </span>
                            <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="xxl"/> xxl
                    </span>
                            <span class="checkbox-inline">
                        <input type="checkbox" name="size" role="size" value="xxxl"/> xxxl
                    </span>
                </div>
            </div>
        </div>
    </td>
    <td>
        <div style="width: 350px;">
            <ul class="inventory"></ul>
        </div>
    </td>
</script>

<script type="text/html" id="tmpl-inventoryItem">
    <li data-color="blue" id="inventory-{{colorCode}}">
        <label class="col-md-2">{{colorName}}：</label>
        <div class="col-md-10 inventory-size"></div>
    </li>
</script>

<script type="text/html" id="tmpl-sizeItem">
    <div class="col-md-3 inventory-{{sizeCode}}">
        {{sizeCode}}: <input type="text" role="quantity" data-size="{{sizeCode}}" class="input-icon input-icon-count" placeholder="" value=""/>
    </div>
</script>

<script type="text/javascript">
    seajs.use('module/order/add', function(mod){
        mod.run();
    });
</script>
