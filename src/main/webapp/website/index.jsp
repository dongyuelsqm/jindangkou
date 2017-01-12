<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibs.jsp"%>
<%@ include file="common/upper-part.jsp"%>
<style>
	.block-body{padding: 10px;}
	.index-block{border: 1px solid #cdcdcd; margin: 10px;}
		.index-nav{overflow: hidden;}
			.index-nav > li{float: left; line-height: 30px;}
				.index-nav > li > a{color: #333; font-weight: bold; margin-left: 10px; margin-right: 10px;}
					.index-nav > li > a:hover,
					.index-nav > li.active > a{border-bottom: 2px solid #ea3527;}
	.index-ec{border-top: 1px solid #cdcdcd; height: 200px;}
	.index-val{overflow: hidden;}
		.index-val > div:first-child{border-right: 1px solid #cdcdcd;}
		.index-val > div{float: left; padding-top: 10px; padding-bottom: 10px; text-align: center; width: 50%;}
			.index-val > div > p:first-child{color: #ea3527; font-weight: bold; font-size: 14px;}
</style>
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