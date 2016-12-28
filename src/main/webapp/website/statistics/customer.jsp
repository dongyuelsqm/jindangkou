<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">客户统计</h1>
<div id="table-wrapper" class="table-wrapper block-body">
    <div class="table-operation clearfix" id="search-box">
        <div class="operation-right pull-right">
            <div class="input-group search-input">
                <input type="text" class="form-control search-text" name="orderNo" placeholder="输入商品名称进行搜索">
                <span class="input-group-addon btn-search" >搜索</span>
            </div>
        </div>
    </div>
    <table class="table" id="customer-table">
        <thead>
        <tr>
            <th>客户名称</th>
            <th>成交订单数<span class="order"></span></th>
            <th>购买商品种类<span class="order"></span></th>
            <th>交易额度<span class="order order-desc"></span></th>
            <th>收获地</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <div>维多利亚同款保暖呢大衣保暖</div>
            </td>
            <td>100</td>
            <td>19</td>
            <td><span class="color-rect">$22</span></td>
            <td><div style="">浙江</div></td>
        </tr>
        </tbody>
    </table>
    <div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/statistics/customer', function(mod){
        mod.run();
    });
</script>
