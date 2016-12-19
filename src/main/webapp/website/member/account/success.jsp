<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
	.success-msg { text-align: center; padding: 30px 0; }
	.success-msg > span { color: #f00; }
	.success-links > div { text-align: center; }
</style>
<div class="container">
	<div class="breadcrumb">
		<span><a href="#member/personal-center.action">个人中心</a></span> &gt; 
        <span>财务中心</span> &gt; 
        <span>充值记录</span>
	</div>
	<div class="outer-content">
		<div class="content-wrapper">
	        <div class="content-offset-left content" id="content">
	            <h1 class="content-title">账户充值</h1>
				<div class="block-body">
		            <div class="row">
		                <div class="col-md-6 col-md-offset-3">
							<h3 class="success-msg">恭喜，您已成功充值<span>100.00</span>元</h3>
						</div>
					</div>
					<div class="row success-links">
						<div class="col-md-2 col-md-offset-3">
							<a href="#/member/account/recharge.action">继续充值</a>
						</div>
						<div class="col-md-2">
							<a href="#/member/account/recharge!rechargeList.action">查看充值记录</a>
						</div>
						<div class="col-md-2">
							<a href="#/member/account/finance-center.action">返回财务中心</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../personal-menu.jsp"%>
	</div>
</div>