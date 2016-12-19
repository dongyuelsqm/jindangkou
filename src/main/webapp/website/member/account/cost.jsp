<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
.search-input{width: 300px;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt; 
        <span>财务中心</span> &gt; 
        <span>消费记录</span>
    </div>
    <div class="outer-content">
		<div class="content-wrapper">
	        <div class="content-offset-left content" id="content">
	            <h1 class="content-title">消费记录</h1>
		        <div id="table-wrapper" class="table-wrapper block-body">
		        	<div class="table-operation" id="search-box">
	                    <div class="clearfix">
	                        <div class="operation-left left">
	                            <label>支付方式：</label>
	                            <select name="payMentType" class="select">
	                                <option value="" selected>全部</option>
	                                <c:forEach items="${payMentList}" var="payMentType">
	                                    <option value="${payMentType.payKey}">${payMentType.payName}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                        <div class="operation-right right">
	                            <div class="input-group search-input">
		                            <input type="text" class="form-control search-text" name="orderNo" placeholder="输入订单号进行搜索">
		                            <span class="input-group-addon btn-search" >搜索</span>
	                            </div>
	                        </div>
	                    </div>
                    </div>
		            <table class="table" id="cost-table">
		                <thead>
		                    <tr>
		                        <th>序号</th>
		                        <th>消费时间</th>
		                        <th>订单号</th>
		                        <th>服务名称</th>
		                        <th>支付方式</th>
		                        <th>消费前余额</th>
		                        <th>消费金额</th>
		                        <th>消费后余额</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <tbody></tbody>
		            </table>
		        	<div id="pagination" class="pages"></div>
       			</div>
       		</div>
       	</div>
        <%@ include file="../personal-menu.jsp"%>
	</div>
</div>

<script id="tmpl-costlist-row" type="text/html">
    <td>{{index}}</td>
    <td>{{costTime}}</td>
    <td>{{orderNo}}</td>
    <td>{{productName}}</td>
    <td>{{getPayMentType(paymentType)}}</td>
    <td>{{userBalance}}</td>
    <td>{{costAmount}}</td>
	<td>{{costedBalance}}</td>
    <td> 
        <a href="javascript:" class="order-detail" data-orderNo="{{orderNo}}">查看详情</a>
	</td>
</script>

<script type="text/javascript">
	seajs.use('module/member/account/cost', function(mod) {
		mod.run();
	});
</script>
