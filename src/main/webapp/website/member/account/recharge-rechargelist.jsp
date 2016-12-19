<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
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
	            <h1 class="content-title">充值记录</h1>
		        <div class="block-body">
			        <div id="table-wrapper" class="table-wrapper block-body">
			            <table class="table" id="recharge-list-table">
			                <thead>
			                    <tr>
			                        <th>序号</th>
			                        <th>流水号</th>
			                        <th>充值时间</th>
			                        <th>充值方式</th>
			                        <th>充值前余额</th>
			                        <th>充值金额</th>
			                        <th>充值状态</th>
			                        <th>充值后余额</th>
			                        <th>操作</th>
			                    </tr>
			                </thead>
			                <tbody>
			                </tbody>
			            </table>
			        	<div id="pagination" class="pages"></div>
	       			</div>
       			</div>
       		</div>
       	</div>
        <%@ include file="../personal-menu.jsp"%>
	</div>
</div>

<script id="tmpl-rechargelist-row" type="text/html">
    <td>{{rechargeId}}</td>
    <td>{{rechargeNo}}</td>
    <td>{{formatDate(rechargeTime, 'yyyy-MM-dd')}}</td>
    <td>{{getType(paymentType)}}</td>
    <td>{{rechargedBalance}}</td>
    <td>{{rechargeAmount}}</td>
    <td>{{getStatus(rechargeStatus)}}</td>
    <td>{{getBalance(rechargeStatus,rechargedBalance,rechargeAmount)}}</td>
    <td><# if (rechargeStatus === 3) { #> 
        <a href="javascript:" class="continuePay" data-rechargeId="{{rechargeId}}">继续支付</a>
    <# } #></td>
</script>

<script type="text/javascript">
	seajs.use('module/member/account/recharge-list', function(mod) {
		mod.run();
	});
</script>
