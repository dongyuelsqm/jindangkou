<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
	.search-input{width: 300px;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt;
        <span><a href="#member/service/service.action">服务中心</a></span> &gt;
        <span>API调用</span>
    </div>
    <div class="outer-content">
    	<div class="content-wrapper">
	        <div id="content" class="content content-offset-left">
	        	<h1 class="content-title"><i class="iconfont">&#xe637;</i>API调用</h1>
	            <div class="table-wrapper block-body" id="table-wrapper">
	                <div class="table-operation" id="search-box">
	                    <div class="clearfix">
	                        <div class="operation-left pull-left">
	                            <label>类型：</label>
	                            <select name="categoryId" class="select">
	                                <option value="" selected>全部</option>
	                                <c:forEach items="${categoryList}" var="category">
	                                    <option value="${category.categoryId}">${category.categoryName}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                        
	                        <div class="operation-right pull-right">
	                        	<div class="input-group search-input">
		                            <input type="text" class="form-control search-text" name="searchContent" placeholder="输入关键字进行搜索">
		                            <span class="input-group-addon btn-search" >搜索</span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="clearfix">
	                        <div class="operation-left pull-left">
	                            <c:forEach items="${orderMapList}" var="order" varStatus="status">
	                                <a href="javascript:" class="order-switch order-desc <c:if test="${status.index == 0}">active</c:if>" data-val="${order.orderKey}">${order.orderValue}<i class="iconfont">&#xe623;</i></a>
	                                <c:if test="${!status.last}"><span class="separate">|</span></c:if>
	                            </c:forEach>
	                        </div>
	                    </div>
	                </div>
	                <table id="api-invoke-table" class="table">
	                	<thead>
	                    	<tr>
	                        	<th>ID</th>
	                            <th>API名称</th>
	                            <th>API类别</th>
	                            <th>购买时间</th>
	                            <th>有效时间</th>
	                            <th>调用时间</th>
	                            <th>已用次数</th>
	                            <th>剩余次数</th>
	                            <th>操作</th>
	                         </tr>
	                    </thead>
	                    <tbody></tbody>
	                </table>
	                <div id="pagination" class="pages"></div>
	            </div>
	        </div>
        </div>
        <%@ include file="../personal-menu.jsp"%>
    </div>
</div>

<script id="tmpl-apiinvoke-head" type="text/html">
    <tr>
        <th>ID</th>
        <th>API名称</th>
        <th>API类别</th>
        <th>购买时间</th>
		<th>有效时间</th>
        <th>调用时间</th>
        <th>已用次数</th>
        <th>剩余次数</th>
        <th>操作</th>
    </tr>
</script>
<script id="tmpl-apiinvoke-row" type="text/html">
    <td><div style="" >{{serviceId}}</div></td>
    <td><div style="width: 145px;" title="{{serviceName}}"><a href="javascript:" class="operation-buy">{{serviceName}}</a></div></td>
    <td><div style="" >{{categoryName}}</div></td>
    <td><div style="width: 100px;" >{{purchaseTime}}</div></td>
	<td><div style="width: 100px;" >{{effectiveDate}}</div></td>
    <td><div style="width: 100px;" >{{visitTime}}</div></td>
    <td><div style="width: 60px;" >{{useCount}}</div></td>
	<td><div style="width: 60px;" >{{leftCount}}</div></td>
    <td><div style="width: 60px;"><a href="javascript:" class="operation-buy">再次购买</a></div></td>
</script>

<script type="text/javascript">
	seajs.use('module/member/service/api-invoke', function(mod) {
		mod.run();
	});	
</script>