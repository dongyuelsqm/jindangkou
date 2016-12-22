<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/head.jsp"%>
<h1 class="content-title">订单列表</h1>
<div id="table-wrapper" class="table-wrapper block-body">
	<div class="table-operation clearfix" id="search-box">
		<div class="operation-left left">
			<label>支付方式：</label>
			<select name="payMentType" class="select">
				<option value="" selected>全部</option>
				<c:forEach items="${payMentList}" var="payMentType">
				<option value="${payMentType.payKey}">${payMentType.payName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="operation-right right">
			<div class="input-group search-input">
				<input type="text" class="form-control search-text" name="orderNo" placeholder="输入订单号进行搜索">
				<span class="input-group-addon btn-search" >搜索</span>
			</div>
		</div>
	</div>
	<table class="table" id="cost-table">
		<thead>
			<tr>
				<th>序号</th>
				<th>消费时间</th>
				<th>订单号</th>
				<th>服务名称</th>
				<th>支付方式</th>
				<th>消费前余额</th>
				<th>消费金额</th>
				<th>消费后余额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<div id="pagination" class="pages"></div>
</div>
<%@include file="../common/footer.jsp"%>

<script type="text/javascript">
    seajs.use('module/order/list', function(mod){
        mod.run();
    });
</script>