<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
    .invoice-container .row { line-height: 30px; margin: 10px 0; }
        .invoice-container .row > div { padding: 0 10px 0 5px; }
        .invoice-container .row > .description { text-align: right; }
    .invoice-container input[type=text],
    .invoice-container input[type=password],
    .invoice-container textarea { line-height: 30px; width: 100%; padding: 0 5px; }
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt; 
        <span>财务中心</span> &gt; 
        <span>开票抬头信息</span>
    </div>
    <div class="outer-content">
		<div class="content-wrapper">
	        <div class="content-offset-left content" id="invoice-managerment-content">
	        	<input type="hidden" value="${invoiceType}" id="invoice-type" />
	            <h1 class="content-title">开票抬头信息</h1>
                <div class="block-body clearfix">
                    <c:forEach var="type" items="${invoiceTypeList}" varStatus="status">
                    <div class="radio-inline">
                        <input type="radio" value="${type.typeKey}" class="radio" name="invoiceType" <c:if test="${invoiceType == type.typeKey}">checked="checked"</c:if> />${type.typeName}
                    </div>
                    </c:forEach>
                </div>
                <div id="table-wrapper">
	                <h2 class="content-title">
	                	开票金额选择
	                	<button type="button" class="btn btn-submit pull-right" id="add-invoice">新增</button>
	                </h2>
	                <div id="table-wrapper"  class="block-body" >
	                    <table class="table" id="invoice-table">
	                        <thead></thead>
	                        <tbody></tbody>
	                    </table>
	                    <!-- <div id="pagination" class="pages"></div>  -->
	                </div>
				</div>
	        </div>
	    </div>
	    <%@ include file="../personal-menu.jsp"%>
	</div>
</div>

<!--普通发票-->
<script id="tmpl-invoiceRow-0" type="text/html">
    <td><div title="{{invoiceTitle}}" style="width: 120px;" >{{invoiceTitle}}</div></td>
    <td><div title="{{invoiceContent}}" style="width: 100px;" >{{invoiceContent}}</div></td>
    <td><div title="{{address}}" style="width: 120px;">{{address}}</div></td>
	<td><div title="{{contact}}" style="width: 120px;">{{contact}}</div></td>
	<td><div title="{{tel}}" style="width: 120px;">{{tel}}</div></td>
	<td>
		<div class="" style="width: 300px;">
			<a href="javascript:" class="update">修改</a>
			<a href="javascript:" class="delete">删除</a>
			<a href="javascript:" class="use">使用</a>
			<# if(isDefault === 0) { #>
				<a href="javascript:" class="defualt">设为默认</a>
			<#}#>
		</div>
	</td>
</script>
<script id="tmpl-invoiceThead-0" type="text/html">
    <tr>
	<th>发票抬头</th>
    <th>发票内容</th>
    <th>邮寄地址</th>
	<th>接收人</th>
	<th>联系方式</th>
	<th>操作</th>
	</tr>
</script>
<script id="tmpl-invoice-common" type="text/html">
	<#
		invoiceTitle = invoiceTitle === '' ? '个人' : invoiceTitle;
	#>
	<input type="hidden" name="invoiceId" value="{{invoiceId}}" />
	<div class="row">
		<label class="col-md-3 control-label">发票抬头<span class="required">*</span></label>
		<div class="col-md-8">
        	<span class="radio-inline"><input type="radio" name="title" value="0" />个人</span>
			<span class="radio-inline"><input type="radio" name="title" value="1" />其他</span>
		</div>
		<div class="col-md-offset-3 col-md-8" id="invoice-title-wrapper" style="display: none;">
			<input type="text" name="invoiceTitle" value="{{invoiceTitle}}" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">发票内容<span class="required">*</span></label>
		<div class="col-md-8">
            <span class="radio-inline"><input type="radio" name="invoiceContent" value="数据服务" checked />数据服务</span>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<b>邮寄信息</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">接收人<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="contact" value="{{contact}}" placeholder="请输入接收人姓名" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">联系方式<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="tel" value="{{tel}}" placeholder="请输入联系方式" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">邮寄地址<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="address" value="{{address}}" placeholder="请输入邮寄地址"/>
		</div>
	</div>
</script>

<!--增值税发票-->
<script id="tmpl-invoiceRow-1" type="text/html">
    <td><div title="{{campanyName}}" style="width: 60px;">{{campanyName}}</div></td>
	<td><div title="{{regAddr}}" style="width: 60px;" >{{regAddr}}</div></td>
	<td><div title="{{regTel}}" style="width: 60px;" >{{regTel}}</div></td>
	<td><div title="{{regBank}}" style="width: 60px;" >{{regBank}}</div></td>
    <td><div title="{{address}}" style="width: 60px;">{{address}}</div></td>
	<td><div title="{{contact}}" style="width: 60px;">{{contact}}</div></td>
	<td><div title="{{tel}}" style="width: 50px;">{{tel}}</div></td>
	<td>
		<div class="" style="width: 150px;">
			<a href="javascript:" class="update">修改</a>
			<a href="javascript:" class="delete">删除</a>
			<a href="javascript:" class="use">使用</a>
			<# if(isDefault === 0) { #>
				<a href="javascript:" class="defualt">设为默认</a>
			<#}#>
		</div>
	</td>
</script>
<script id="tmpl-invoiceThead-1" type="text/html">
    <tr>
	<th>单位名称</th>
    <th>注册地址</th>
	<th>注册电话</th>
	<th>开户银行</th>
	<th>邮寄地址</th>
	<th>接收人</th>
	<th>联系方式</th>
	<th>操作</th>
	</tr>
</script>
<script id="tmpl-invoice-VAT" type="text/html">
	<input type="hidden" name="invoiceId" value="{{invoiceId}}" />
	<div class="row">
		<div class="col-md-12">
			<b>公司信息</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">单位名称<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="campanyName" value="{{campanyName}}" placeholder="请输入单位名称" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">纳税人识别码<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="campanyCode" value="{{campanyCode}}" placeholder="请输入纳税人识别码" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">注册地址<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="regAddr" value="{{regAddr}}" placeholder="请输入注册地址" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">注册电话<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="regTel" value="{{regTel}}" placeholder="请输入注册电话" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">开户银行<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="regBank" value="{{regBank}}" placeholder="请输入开户银行" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">银行账户<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="bankCode" value="{{bankCode}}"  placeholder="请输入银行账户"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<b>邮寄信息</b>
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">接收人<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="contact" value="{{contact}}" placeholder="请输入接收人姓名" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">联系方式<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="tel" value="{{tel}}" placeholder="请输入联系方式" />
		</div>
	</div>
	<div class="row">
		<label class="col-md-3 control-label">邮寄地址<span class="required">*</span></label>
		<div class="col-md-8">
			<input type="text" name="address" value="{{address}}" placeholder="请输入邮寄地址" />
		</div>
	</div>
</script>

<script id="tmpl-invoiceManagerModal" type="text/html">
	<div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-label">发票信息</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-submit" id="btn-submit">保存发票信息</button>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript">
	seajs.use('module/member/account/invoice-manager', function(mod) {
		mod.run();
	});
</script>
