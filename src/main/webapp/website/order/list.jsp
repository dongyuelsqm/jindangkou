<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
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
	<table class="table" id="cost-table">
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
		<tbody>
			<tr class="order-title">
				<td colspan="7">
					<span class="checkbox-inline"><input type="checkbox" class="checkbox" /></span>
					订单号:102501220011
					成交时间：2016-11-28 14:20:13
				</td>
			</tr>
			<tr>
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
				</td>
			</tr>
		</tbody>
	</table>
	<div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/order/list', function(mod){
        mod.run();
    });
</script>