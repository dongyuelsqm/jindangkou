<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<c:if test="${empty invoiceType}">
	<c:set var="invoiceType" value="0"/>
</c:if>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
	G.invoiceId = '${invoiceId}';
	G.invoiceType = '${invoiceType}';  //0普通发票 1增资税发票
</script>
<style>
	.content-title > span{font-size: 12px;}
	.form a.btn{color: #fff; text-decoration: none;}
    .block-footer { padding: 35px 15px; text-align: center; border-top: 1px solid #cbcbcb; }
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt; 
        <span>财务中心</span> &gt; 
        <span>开具发票</span>
    </div>
    <div class="outer-content">
	    <div class="content-wrapper">
	        <div class="content content-offset-left" id="invoice-wrapper">
	            <div class="content-block" id="invoice-type-wrapper">
	                <h1 class="content-title">开票抬头信息</h1>
	                <div class="block-body clearfix">
	                	<div class="">
		                    <c:forEach var="type" items="${invoiceTypeList}" varStatus="status">
		                    <div class="radio-inline">
		                        <input type="radio" value="${type.typeKey}" class="radio" name="invoiceType" <c:if test="${invoiceType == type.typeKey}">checked</c:if>/>${type.typeName}
		                    </div>
		                    </c:forEach>
	                    </div>
	                    <div id="invoice-type-info"></div>
	                </div>
	            </div>
	            <div class="content-block">
	                <h1 class="content-title">
	                	开票金额选择
	                    <span>（发票金额按照订单开，每个订单对应一张发票，下表显示用户为开具发票的订单，可多选）</span>
	                </h1>
	                <div id="table-wrapper" class="block-body table-wrapper">
	                    <table class="table" id="invoice-table">
	                        <thead>
	                            <tr>
	                                <th><input type="checkbox" class="checkbox checkbox-all" /></th>
	                                <th>订单编号</th>
	                                <th>服务名称</th>
	                                <th>金额</th>
	                            </tr>
	                        </thead>
	                        <tbody></tbody>
	                        <tfoot>
	                            <tr>
	                                <td colspan="4">当前开票总金额：10000.00元</td>
	                            </tr>
	                        </tfoot>
	                    </table>
	                </div>
	                <div class="block-footer">
	                    <button type="button" class="btn btn-danger btn-submit" role="button">提交发票信息</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    <%@ include file="../personal-menu.jsp"%>
    </div>
</div>

<script id="tmpl-invoice-list-row" type="text/html">
    <td><input type="checkbox" class="checkbox" /></td>
    <td>{{orderNo}}</td>
    <td>{{productName}}</td>
    <td>{{orderAmount}}元</td>
</script>
<script id="tmpl-invoice-list-foot" type="text/html">
    <tr>
        <td colspan="4">当前开票总金额：{{totalAmount.toFixed(2)}}元</td>
    </tr>
</script>

<!--普通发票-->
<script id="tmpl-invoice-common" type="text/html">
    <form class="form">
		<div class="row">
			<div class="col-md-10">
                <b>信息</b>
            </div>
			<div class="col-md-2">
				<a href="#member/account/invoice-managerment.action?invoiceType=0" class="btn btn-submit">管理</a>
			</div>
		</div>
        <div class="row">
            <label class="col-md-3 control-label">发票抬头：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{invoiceTitle}}</p>
            </div>
        </div>
		<div class="row">
            <label class="col-md-3 control-label">发票内容：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{invoiceContent}}</p>
            </div>
        </div>
		<div class="row">
			<div class="col-md-12">
                <b>邮寄信息</b>
            </div>
		</div>
        <div class="row">
            <label class="col-md-3 control-label">接收人：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{contact}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">联系方式：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{tel}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">邮寄地址：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{address}}</p>
            </div>
        </div>
    </form>
</script>

<!--增值税发票-->
<script id="tmpl-invoice-VAT" type="text/html">
    <form class="form">
		<div class="row">
			<div class="col-md-10">
                <b>公司信息</b>
            </div>
			<div class="col-md-2">
				<a href="#member/account/invoice-managerment.action?invoiceType=1" class="btn btn-submit">管理</a>
			</div>
		</div>
        <div class="row">
            <label class="col-md-3 control-label">单位名称：</label>
            <div class="col-md-9">
				<p class="form-control-static">{{campanyName}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">纳税人识别码：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{campanyCode}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">注册地址：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{regAddr}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">注册电话：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{regTel}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">开户银行：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{regBank}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">银行账户：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{bankCode}}</p>
            </div>
        </div>
        <div class="row">
			<div class="col-md-12">
                <b>邮寄信息</b>
            </div>
		</div>
        <div class="row">
            <label class="col-md-3 control-label">接收人：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{contact}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">联系方式：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{tel}}</p>
            </div>
        </div>
        <div class="row">
            <label class="col-md-3 control-label">邮寄地址：</label>
            <div class="col-md-9">
                <p class="form-control-static">{{address}}</p>
            </div>
        </div>
    </form>
</script>

<script type="text/javascript">

	seajs.use('module/member/account/invoice', function(mod) {
		mod.run();
	});
</script>
