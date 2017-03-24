<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">商品列表</h1>
<div id="table-wrapper" class="table-wrapper block-body">
	<div class="operations clearfix" id="search-box">
		<div class="operation-left pull-left">
			<span class="checkbox-inline"><input type="checkbox" class="checkbox checkbox-all" /></span>
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
		<tbody></tbody>
	</table>
	<div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-productRow">
    <td><span class="checkbox-inline"><input type="checkbox" class="checkbox checkbox-all" /></span></td>
	<td>
        <div style="width: 100px;">
        	<img src="{{pictures}}" alt="{{name}}" />
			{{name}}
        </div>
	</td>
	<td>{{price}}</td>
	<td>总库存</td>
	<td>总销量</td>
	<td>{{date}}</td>
    <td><div><a href="javascript:" class="editor" role="editor">编辑</a></div></td>
</script>

<script type="text/javascript">
    seajs.use('module/product/list', function(mod){
        mod.run();
    });
</script>

</body>
</html>