<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
	.content {padding: 15px; height: 642px;}
	
    .finance-item { border: 1px solid #cbcbcb; width: 48.5%; float: left; }
        .finance-item > div {line-height: 34px; height: 34px; margin: 10px 20px; }
    .finance-icon { background: url(${contextPath}/res/css/module/img/member/account/finance-icon.png) no-repeat; display: inline-block; height: 25px; vertical-align: middle; width: 25px;}
        .finance-icon.balance { background-position: 0 center; }
        .finance-icon.income { background-position: -25px center; }
    .finance-item .btn{border-radius: 0; width: 100px;}
    .finance-item .caption{font-size: 18px;}
    .finance-space { width: 3%; float: left; min-height: 1px; }
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt; 
        <span>财务中心</span>
    </div>	
	<div class="outer-content">
		<div class="content-wrapper">
	        <div class="content-offset-left content" id="content">
                <div class="finance-item">
                    <div>
                        <span class="finance-icon balance"></span>
                        <b class="caption">账户余额</b>
                    </div>
                    <div>
                        <span>充值余额：</span><b>￥200.00</b>
                        <a href="#member/account/recharge.action" class="btn btn-danger right" role="button">充值</a>
                    </div>
                </div>
                <div class="finance-space"></div>
                <div class="finance-item">
                    <div>
                        <span class="finance-icon income"></span>
                        <b class="caption">我的收益</b>
                    </div>
                    <div>
                        <span>收益：</span><b>￥200.00</b>
                        <a href="javascript:" class="btn btn-primary right" role="button">提现</a>
                    </div>
                </div>
        	</div>
        </div>
        <%@ include file="../personal-menu.jsp"%>
	</div>
</div>
<script type="text/javascript">
	seajs.use('module/member/account/finance', function(mod) {
		mod.run();
	});	
</script>