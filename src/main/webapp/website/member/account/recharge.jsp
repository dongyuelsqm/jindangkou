<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
	
    .block-body {height: 592px;}
    .block-body > .row { margin-botton: 20px; margin-top: 20px;}
        .form .recharge-input { width: 130px; height: 28px; padding: 0 5px; }
        .form .amount-error { color: #f00; margin-left: 20px; display: none; }
        
    .qrcode { padding: 30px 90px; }
    .qrcode > img { border: 1px solid #cbcbcb; padding: 5px; }
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt; 
        <span>财务中心</span> &gt; 
        <span>充值</span>
    </div>
    <div class="outer-content">
		<div class="content-wrapper">
	        <div class="content-offset-left content" id="content">
	            <h1 class="content-title">账户充值</h1>
	            <form id="recharge-form" class="form block-body" method="post" action="member/account/recharge!recharge.action" target="_blank">
		            <div class="row">
		            	<label class="col-md-3 control-label" >充值金额：</label>
		                <div class="col-md-9">
							<input type="text" value="10" class="recharge-input" name="amount" autocomplete="off">
							<span>元</span>
		                </div>
		                <div class="col-md-9 col-md-offset-3">
		                        <span class="amount-error"><i class="iconfont">&#xe612;</i>充值金额必须是1,000,000以内大于0的整数或两位以内小数</span>
		                </div>
		            </div>
		            <div class="row">
		            	<label class="col-md-3 control-label" >支付金额：</label>
		                <div class="col-md-9">
		                    <b class="amount-value">10.00</b>
		                    <span>元</span>
		                </div>
		            </div>
		            <div class="row">
		                <div class="col-md-4 col-md-offset-3">
		<!--
		                    <span>充值方式：</span>
		                    <a href="javascript:" class="btn btn-default btn-pay alipay" role="button">
		                        <img src="${contextPath}/res/css/sys/img/member/alipay.png" alt="支付宝">
		                    </a>
		                    <a href="javascript:" class="btn btn-default btn-pay wechat" role="button">
		                        <img src="${contextPath}/res/css/sys/img/member/wechatpay.png" alt="微信支付">
		                    </a>
		-->
		                </div>
		            <div class="row">
		                <div class="col-md-3 col-md-offset-3">
		                    <button type="button" class="btn btn-submit btn-pay" role="button">前去充值</button>
		                </div>
		            </div>
		        </div>
		        </form>
			</div>
		</div>
		<%@ include file="../personal-menu.jsp"%>
	</div>
</div>
        
<!--
        <div class="mask" style="display:none;"></div>
        <div id="pop-alipay" class="popup" style="display:none;">
            <div class="popup-header">
                支付
                <a class="close"><span>&times;</span></a>
            </div>
            <div class="popup-content">请您在打开的支付界面进行支付，支付完成前请不要关闭该窗口</div>
            <div class="popup-footer">
                <a href="javascript:" class="btn btn-default btn-problem">支付遇到问题</a>
                <a href="javascript:" class="btn btn-danger btn-finish">完成支付</a>
            </div>
        </div>

        <div id="pop-alipay" class="popup" style="display:none;">
            <div class="popup-header">
                登录支付宝支付
                <a class="close"><span>&times;</span></a>
            </div>
            <div class="popup-content">请您在打开的支付宝界面进行支付，支付完成前请不要关闭该窗口</div>
            <div class="popup-footer">
                <a href="javascript:" class="btn btn-default">支付遇到问题</a>
                <a href="javascript:" class="btn btn-danger">完成支付</a>
            </div>
        </div>
        <div id="pop-wechat" class="popup" style="display:none;">
            <div class="popup-header">
                微信支付
                <a class="close"><span>&times;</span></a>
            </div>
            <div class="popup-content">
                <div style="text-align:center">打开微信“扫一扫”，使用微信支付</div>
                <div class="qrcode">
                    <img src="${contextPath}/res/css/module/img/member/qrcode.png" alt="">
                </div>
            </div>
        </div>
-->

<script id="tmpl-rechargeModal" type="text/html">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-label">提示</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">完成支付</button>
                <button type="button" class="btn btn-primary">支付遇到问题</button>
            </div>
        </div>
    </div>
</script>


<script type="text/javascript">
	seajs.use('module/member/account/recharge', function(mod) {
		mod.run();
	});	
</script>