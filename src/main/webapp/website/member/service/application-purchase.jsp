<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
    .table-operation > div{height: 50px;}
    .search-input{width: 300px;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt;
        <span><a href="#member/service/service.action">服务中心</a></span> &gt;
        <span>应用购买</span>
    </div>
    <div class="outer-content" id="content">
    	<div class="content-wrapper">
    		<div class="content-offset-left content">
		        <h1 class="content-title"><i class="iconfont">&#xe63c;</i>应用购买</h1>
	            <div class="table-wrapper block-body" id="table-wrapper">
	                <div class="table-operation" id="search-box">
	                    <div class="clearfix">
	                        <div class="operation-left pull-left">
	                            <c:forEach items="${orderMapList}" var="order" varStatus="status">
	                                <a href="javascript:" class="order-switch order-desc <c:if test="${status.index == 0}">active</c:if>" data-val="${order.orderKey}">${order.orderValue}<i class="iconfont">&#xe623;</i></a>
	                                <c:if test="${!status.last}"><span class="separate">|</span></c:if>
	                            </c:forEach>
	                        </div>
	                        <div class="operation-right pull-right">
	                            <div class="input-group search-input">
		                            <input type="text" class="form-control search-text" name="searchContent" placeholder="输入关键字进行搜索">
		                            <span class="input-group-addon btn-search" >搜索</span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                    <table id="application-purchase-table" class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>应用名称</th>
                                <th>购买类别</th>
                                <th>购买时间</th>
                                <th>有效期</th>
                                <th>生效时间</th>
                                <th>失效时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<tr><td colspan=8 >暂无购买记录</td></tr>
                        </tbody>
                    </table>
                    <div id="pagination" class="pages"></div>
            	</div>
            </div>
        </div>
        <%@ include file="../personal-menu.jsp"%>
    </div>
</div>

<script id="tmpl-producttrade-head" type="text/html">
    <tr>
        <th>ID</th>
        <th>应用名称</th>
        <th>购买类别</th>
        <th>购买时间</th>
        <th>有效期</th>
        <th>生效时间</th>
        <th>失效时间</th>
        <th>操作</th>
    </tr>
</script>
<script id="tmpl-applicationpurchase-row" type="text/html">
    <td>productId</td>
    <td>productName</td>
    <td>purchaseType</td>
    <td>purchaseTime</td>
    <td>period</td>
    <td>validTime</td>
    <td>invalidTime</td>
    <td><div style="width: 60px;"><a href="javascript:" class="operation-buy">再次购买</a></div></td>
</script>

<script type="text/javascript">
	seajs.use('module/member/service/application-purchase', function(mod) {
		mod.run();
	});	
</script>