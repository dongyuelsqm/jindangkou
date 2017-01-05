<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
	.table td img{height: 50px; margin-right: 10px; width: 50px;}
</style>
<h1 class="content-title">商品列表</h1>
<div id="table-wrapper" class="table-wrapper block-body">
	<div class="table-operation clearfix" id="search-box">
		<div class="operation-left pull-left">
			<div class="checkbox-inline"><input type="checkbox" class="checkbox checkbox-all" /></div>
			<a class="btn btn-submit" href="javascript:" role="btn-delete-mul">删除</a>
			<a class="btn btn-submit" href="javascript:" role="btn-off-shelf">下架</a>
		</div>
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
				<th></th>
				<th>商品名称</th>
				<th>单价</th>
				<th>总库存</th>
				<th>总销量</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><span class="checkbox-inline"><input type="checkbox" class="checkbox checkbox-all" /></span></td>
				<td>
					<div style="width: 100px;">
						<img src="" alt="" />
						维多利亚同款保暖呢大衣保暖呢大衣批发
					</div>
				</td>
				<td>￥55</td>
				<td>142</td>
				<td>58</td>
				<td>2016-12-12 14:20:20</td>
				<td>
					<div><a href="javascript:" class="editor" role="editor">编辑</a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/product/list', function(mod){
        mod.run();
    });
</script>