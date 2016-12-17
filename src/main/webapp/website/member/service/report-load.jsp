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
        <span>报告下载</span>
    </div>
	<div class="outer-content">
	    <div class="content-wrapper" id="content">
	        <div id="content-default" class="content content-offset-left">
	        	<h1 class="content-title"><i class="iconfont">&#xe626;</i>报告下载</h1>
	            <div class="table-wrapper block-body" id="table-wrapper">
	                <div class="table-operation" id="search-box">
	                    <div class="clearfix">
	                        <div class="operation-left pull-left">
	                            <label>类型：</label>
	                            <select name="typeId" class="select">
	                                <option value="" selected>全部</option>
	                                <c:forEach items="${typeList}" var="type">
	                                    <option value="${type.typeId}">${type.typeName}</option>
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
	                <table id="report-load-table" class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>报告名称</th>
                                <th>报告类别</th>
                                <th>下载时间</th>
                                <th>下载地址</th>
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

<script id="tmpl-reportload-head" type="text/html">
    <tr>
        <th>ID</th>
        <th>报告名称</th>
        <th>报告类别</th>
        <th>下载时间</th>
        <th>下载地址</th>
		<th>操作</th>
    </tr>
</script>
<script id="tmpl-reportload-row" type="text/html">
    <td><div style="" >{{reportId}}</div></td>
    <td><div style="width: 145px;" title="{{reportName}}"><a href="javascript:" class="operation-buy">{{reportName}}</a></div></td>
    <td><div style="width: 80px;" title="{{typeName}}" >{{typeName}}</div></td>
    <td><div style="width: 100px;" >{{downLoadTime}}</div></td>
    <td><div style="width: 300px;" title="{{downloadUrl}}"><a href="{{downloadUrl}}" target="_blank">{{downloadUrl}}</a></div></td>
	<td><div style="width: 60px;"><a href="javascript:" class="operation-buy">再次购买</a></div></td>
</script>

<script type="text/javascript">
	seajs.use('module/member/service/report-load', function(mod) {
		mod.run();
	});	
</script>