<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
	.table{margin-bottom: 0;}
		.table tbody{margin-bottom: 20px;}
			tr.order-title{background-color: #f7f7f7;}
</style>
<h1 class="content-title">订单列表</h1>
<div id="table-wrapper" class="table-wrapper block-body">
	<div class="table-operation clearfix" id="search-box">
		<div class="operation-left left">
			<div class="checkbox-inline"><input type="checkbox" class="checkbox checkbox-all" /></div>
			<a class="btn btn-submit" href="javascript:" role="btn-delete-mul">删除</a>
			<a class="btn btn-submit" href="javascript:" role="btn-download">下载</a>
		</div>
		<div class="operation-right right">
			<div class="input-group search-input">
				<input type="text" class="form-control search-text" name="orderNo" placeholder="输入订单号进行搜索">
				<span class="input-group-addon btn-search" >搜索</span>
			</div>
		</div>
	</div>
	<div id="order-table">
		<table class="table">
			<thead>
				<tr>
					<th>商品名称</th>
					<th>商品属性</th>
					<th>单价</th>
					<th>数量</th>
					<th>买家</th>
					<th>交易状态</th>
					<th>实收款</th>
				</tr>
			</thead>
		</table>
		<table class="table" id="order-table-detail">
			<!--
			<tbody class="order-0">
				<tr class="order-title">
					<td>
						<span class="checkbox-inline"><input type="checkbox" class="checkbox" /></span>
						订单号:102501220011
					</td>
					<td colspan="6">
						成交时间：2016-11-28 14:20:13
					</td>
				</tr>
				<tr class="order-sub-list">
					<td>
						<div style="width: 100px;">
							<img src="" alt="" />
							维多利亚同款保暖呢大衣保暖呢大衣批发
						</div>
					</td>
					<td>
						<p>尺码: <strong>XL</strong></p>
						<p>颜色: <strong>蓝色</strong></p>
					</td>
					<td>￥55</td>
					<td>1</td>
					<td>XXX</td>
					<td>已发货</td>
					<td>
						<p><strong>￥55</strong></p>
						<p>(含快递0.00)</p>
						<p><a href="javascript:" role="check-express">查看物流</a></p>
					</td>
				</tr>
			</tbody>
			-->
		</table>
	</div>
	<div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-order-row">
	<tr class="order-title" role="order-info">
		<td>
			<span class="checkbox-inline"><input type="checkbox" class="checkbox" /></span>
			订单号:{{}}
		</td>
		<td colspan="6">
			成交时间：{{}}
		</td>
	</tr>
</script>
<script type="text/html" id="tmpl-order-sub-row">
	<td>
		<div style="width: 100px;">
			<img src="" alt="" />
			{{}}
		</div>
	</td>
	<td>
		<p>尺码: <strong>{{}}</strong></p>
		<p>颜色: <strong>{{}}</strong></p>
	</td>
	<td>￥{{}}</td>
	<td>{{}}/td>
	<td>{{}}</td>
	<#
		if(index === 0){
	#>
	<td rowspan="{{list_length}}">已发货</td>
	<td rowspan="{{list_length}}">
		<p><strong>￥55</strong></p>
		<p>(含快递0.00)</p>
		<p><a href="javascript:" role="check-express">查看物流</a></p>
	</td>
	<# } #>
</script>

<script type="text/javascript">
    seajs.use('module/order/list', function(mod){
        mod.run();
    });
</script>