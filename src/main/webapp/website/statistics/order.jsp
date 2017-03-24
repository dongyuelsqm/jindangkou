<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    .ec-order{height: 400px;}
</style>
<h1 class="content-title">订单统计</h1>
<div id="ec-wrapper" class="ec-wrapper block-body">
    <div class="ec ec-order"></div>
</div>
<div id="table-wrapper" class="table-wrapper block-body">
    <div class="operations clearfix" id="search-box">
        <div class="operation-left pull-left">
            <%--<input type="text" class="form-control" placeholder="请选择时间" value="2016-01-01">--%>
            <a class="btn btn-submit" href="javascript:" role="btn-today">当天</a>
        </div>
        <div class="operation-right pull-right">
            <div class="input-group search-input">
                <input type="text" class="form-control search-text" name="orderNo" placeholder="输入商品名称进行搜索">
                <span class="input-group-addon btn-search" >搜索</span>
            </div>
        </div>
    </div>
    <table class="table" id="order-table">
        <thead>
            <tr>
                <th>时间</th>
                <th>交易数量<span class="order order-desc"></span></th>
                <th>交易额度<span class="order"></span></th>
                <th>客户数<span class="order"></span></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>2016-12-12 14:20:20</td>
                <td>100</td>
                <td>19</td>
                <td>88</td>
            </tr>
        </tbody>
    </table>
    <div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-order-row">
    <td>2016-12-12 14:20:20</td>
    <td>100</td>
    <td>19</td>
    <td>88</td>
</script>

<script type="text/javascript">
    seajs.use('module/statistics/order', function(mod){
        mod.run();
    });
</script>
</body>
</html>
