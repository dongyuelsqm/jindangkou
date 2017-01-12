<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">添加订单</h1>
<form class="block-body form" id="product-form">
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
        <table class="table" id="product-table">
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
                    <div style="width: 100px;">
                        <img src="" alt="" />
                        维多利亚同款保暖呢大衣保暖呢大衣批发
                    </div>
                </td>
                <td>
                    <div class="row">
                        <label class="col-md-3 control-label">尺码</label>
                        <div class="col-md-9">
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
                        <label class="col-md-3 control-label">颜色</label>
                        <div class="col-md-9">
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
                </td>
                <td>
                    <div class="row">
                        <label class="col-md-3">黄色：</label>
                        <div class="col-md-9">
                            SX: <input type="text" name="" class="input-icon input-icon-count" placeholder="" value=""/>
                            M: <input type="text" name="" class="input-icon input-icon-count" placeholder="" value=""/>
                            L: <input type="text" name="" class="input-icon input-icon-count" placeholder="" value=""/>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">运费<span class="required">*</span></label>
        <div class="col-md-5">
            <select name="department">
                <option value="">包邮</option>
                <option value="">10</option>
                <option value="">20</option>
            </select>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">交易状态<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="name" placeholder="请输入交易状态" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">买家用户名<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="price" placeholder="请输入买家用户名" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">应收款<span class="required">*</span></label>
        <div class="col-md-5">
            <textarea name="descriptive" class="input-icon input-icon-price" placeholder="请输入应收款"></textarea>
        </div>
    </div>
    <br/>
    <div class="row">
        <label class="col-md-3 control-label">收货人<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="" placeholder="请输入收货人" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">联系方式<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="" placeholder="请输入联系方式" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">收货地址<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="" placeholder="请输入收货地址" value=""/>
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
    seajs.use('module/order/add', function(mod){
        mod.run();
    });
</script>
