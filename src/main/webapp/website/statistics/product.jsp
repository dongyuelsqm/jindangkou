<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">商品统计</h1>
<div id="table-wrapper" class="table-wrapper block-body">
    <div class="table-operation clearfix" id="search-box">
        <div class="operation-right pull-right">
            <div class="input-group search-input">
                <input type="text" class="form-control search-text" name="orderNo" placeholder="输入商品名称进行搜索">
                <span class="input-group-addon btn-search" >搜索</span>
            </div>
        </div>
    </div>
    <table class="table" id="product-table">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>单价</th>
            <th>销量<span class="order order-desc"></span></th>
            <th>点击量<span class="order order-asc"></span></th>
            <th>浏览量<span class="order"></span></th>
            <th>收藏量<span class="order"></span></th>
            <th>发布时间<span class="order"></span></th>
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
            <td>￥55</td>
            <td>19</td>
            <td>58</td>
            <td>19</td>
            <td>58</td>
            <td>2016-12-12 14:20:20</td>
        </tr>
        </tbody>
    </table>
    <div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/statistics/product', function(mod){
        mod.run();
    });
</script>
