<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="common/upper-part.jsp"%>
<c:set var="currentPath" value="index" />
<link rel="stylesheet" href="${contextPath}/res/css/module/index.css" />
<div class="index-block">
	<ul class="index-nav block-body">
		<li class="active"><a href="javascript:">今日订单数据</a></li>
		<li><a href="javascript:" >7天订单数据</a></li>
	</ul>
	<div class="ec index-ec" id="ec-order"></div>
	<div class="index-val">
		<div class="block-body">
			<p>22</p>
			<p>在线用户数</p>
		</div>
		<div class="block-body">
			<p>66</p>
			<p>成交订单数</p>
		</div>
	</div>
</div>
<div class="index-block">
	<ul class="index-nav block-body">
		<li class="active"><a href="javascript:">今日交易数据</a></li>
		<li><a href="javascript:" >7天交易数据</a></li>
	</ul>
	<div class="ec index-ec" id="ec-trade"></div>
	<div class="index-val">
		<div class="block-body">
			<p>￥666</p>
			<p>成交金额</p>
		</div>
		<div class="block-body">
			<p>66</p>
			<p>成交商品数</p>
		</div>
	</div>
</div>
<%@include file="common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/index', function(mod){
        mod.run();
    });
</script>
</body>
</html>