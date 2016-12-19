<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
	.table-operation > div{height: 50px;}
    .search-input{width: 300px;}
    .table > tbody > tr > td{padding: 0;}
    .row-header,
    .row-body{line-height: 30px;}
    .row-header{background: #fafafa; padding: 0 8px;}
    .row-body > div{display: table-cell; overflow: hidden; padding: 8px; text-overflow: ellipsis; vertical-align: top; white-space: nowrap;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt;
        <span><a href="#member/service/service.action">服务中心</a></span> &gt;
        <span>我的订单</span>
    </div>
    <div class="outer-content" id="content">
    	<div class="content-wrapper">
    		<div class="content-offset-left content">
		        <h1 class="content-title"><i class="iconfont">&#xe65c;</i>我的订单</h1>
	            <div class="table-wrapper block-body" id="table-wrapper">
	                <div class="table-operation" id="search-box">
	                    <div class="clearfix">
	                        <div class="operation-left pull-left">
	                            <label>订单状态：</label>
	                            <select name="orderStatus" class="select">
	                                <option value="" selected>全部</option>
	                                <c:forEach items="${orderStatusList}" var="orderStatus">
	                                    <option value="${orderStatus.statusKey}">${orderStatus.statusName}</option>
	                                </c:forEach>
	                            </select>
	                            <label>服务类别：</label>
	                            <select name="searchType" class="select">
	                                <option value="" selected>全部</option>
	                                <c:forEach items="${typeList}" var="type">
	                                    <option value="${type.typeId}">${type.typeName}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                        <div class="operation-right pull-right">
	                            <div class="input-group search-input">
		                            <input type="text" class="form-control search-text" name="searchContent" placeholder="输入关键字进行搜索">
		                            <span class="input-group-addon btn-search" >搜索</span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	        		<table id="trade-order-table" class="table">
                        <thead>
                            <tr>
                                <th style="width:110px;">服务名称</th>
                                <th style="width:110px;">套餐名称</th>
                                <th style="width:60px;">价格</th>
                                <th style="width:50px;">数量</th>
                                <th style="width:80px;">总价</th>
                                <th style="width:110px;">收费方</th>
                                <th style="width:80px;">订单状态</th>
                                <th style="width:120px;">操作</th>
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

<script id="tmpl-tradeorder-row" type="text/html">
    <td colspan="9" class="row-block">
        <div class="row-header clearfix">
            <b class="order-time left">订单生成时间：{{createTime}}</b>
            <b class="order-code right">订单编号：{{orderNo}}</b>
        </div>
        <div class="row-body clearfix">
            <div style="width:110px;" title="{{productName}}"><a href="javascript:" class="operation-buy">{{productName}}</a></div>
            <div style="width:110px;" title="{{packageName}}">{{packageName}}</div>
            <div style="width:60px;">{{packagePrice}}</div>
            <div style="width:50px;">{{packageNum}}</div>
            <div style="width:80px;">{{totalPrice}}</div>
            <div style="width:110px;" title="{{sellerName}}">{{sellerName}}</div>
            <div style="width:110px;">
                <span class="text-danger">{{orderStatus}}</span>
            </div>
            <# 
                switch((orderStatus)){
                    case '待付款':
            #>
                <div style="width:120px;">
                    <a href="javascript:" class="operation-link operation-continuePay" data-orderId="{{orderId}}">继续支付</a>
                    <a href="javascript:" class="operation-link operation-cancelPay"  data-orderId="{{orderId}}">取消支付</a>
                </div>
            <#
                        break;
                    case '已付款':
            #>
                <div style="width:120px;">
                    <a href="javascript:" class="operation-link operation-delete" data-orderNo="{{orderNo}}">删除</a>
                </div>
            <#
                        break;
                    case '已关闭':
            #>
                <div style="width:120px;">
                    <a href="javascript:" class="operation-link operation-delete" data-orderNo="{{orderNo}}">删除</a>
                </div>
            <#
                        break;
                    case '订单异常':
            #>
                <div style="width:120px;">
                    <a href="javascript:" class="operation-link operation-delete" data-orderNo="{{orderNo}}">删除</a>
                </div>
            <#
                        break;
                    default:
            #>
                <div style="width:120px;"></div>
            <#
                        break;
                }   
            #>
        </div>
    </td>
</script>

<script type="text/javascript">
	seajs.use('module/member/service/trade-order', function(mod) {
		mod.run();
	});	
</script>