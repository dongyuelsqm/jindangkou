<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<style>
.service-detail{margin-bottom:20px; overflow:hidden; width:100%;}
	.service-detail>div{float: left; margin: 0 28px; width: 199px;}
	.service-detail>.line-vertical{border-color: #cbcbcb; margin: 33px 0 0 0; height: 210px; width: 0;}
		.detail-top{font-size: 15px; text-align: center; margin-bottom: 20px;}
			.detail-top .iconfont{background: url(${contextPath}/res/css/module/img/member/service/service-pop.png); color: #fff; display: inline-block; font-size: 30px; height: 45px; line-height: 40px; margin: 35px auto 0; width: 40px;}
			.service-detail-1 .detail-top .iconfont{background-position:0 center;}
			.service-detail-2 .detail-top .iconfont{background-position:-40px center;}
			.service-detail-3 .detail-top .iconfont{background-position:-80px center;}
		.detail-center{border:1px dashed; border-radius:5px; -o-border-radius:5px; -webkit-border-radius:5px; -moz-border-radius:5px; height:90px; margin-bottom:20px; width:208px; border-color: #da5a5a; position: relative; padding: 9px 0.5em; text-align: center; overflow: hidden;}
			.service-detail-1 > .detail-center{border-color:#da5a5a;}
			.service-detail-2 > .detail-center{border-color:#3697dc;}
			.service-detail-3 > .detail-center{border-color:#b2b738;}
				.detail-center > p{color:#000; margin: 0 0.5em; line-height: 2em;}
				.detail-center > .iconfont{position: absolute; display: block; left: 0.5em; top: 0.5em;}
				.service-detail-1 .detail-center > .iconfont{color:#da5a5a;}
				.service-detail-2 .detail-center > .iconfont{color:#3697dc;}
				.service-detail-3 .detail-center > .iconfont{color:#b2b738;}
		.detail-bottom{background:url(${contextPath}/res/css/module/img/member/service/service-rec.png) no-repeat; color:#242424; display:inline-block; height:27px; line-height:27px; margin:0 35px 20px; text-align:center; width:140px;}
			.detail-bottom:hover{color: #242424;}
			.service-detail-1 .detail-bottom{background-position:center 0;}
			.service-detail-2 .detail-bottom{background-position:center -28px;}
			.service-detail-3 .detail-bottom{background-position:center -56px;}

	.msg-detail h2.content-title{font-size: 15px; height: 35px; line-height: 35px;}
</style>
<div class="container">
    <div class="breadcrumb">
        <span><a href="#member/personal-center.action">个人中心</a></span> &gt;
        <span>服务中心</span>
    </div>
	<div class="outer-content">
		<div class="content-wrapper">
			<div class="content-offset-left content">
				<div class="service-detail">
					<div class="service-detail-1">
						<div class="detail-top">
							<p><i class="iconfont">&#xe637;</i></p>
							<p><span>${serviceMap["serviceCount"]}</span>个调用</p>
						</div>
						<div class="detail-center">
							<i class="iconfont">&#xe602;</i>
		                    <c:forEach items="${serviceMap[\"serviceNameList\"]}" var="item">
		                        <p>${item}</p>
		                    </c:forEach>
						</div>
						<a href="#member/service/api-invoke.action" class="detail-bottom">我的API</a>
					</div>
					<div class="line-vertical"></div>
					<div class="service-detail-2">
						<div class="detail-top">
							<p><i class="iconfont">&#xe638;</i></p>
							<p><span>${reportMap["downloadCount"]}</span>份报告</p>
						</div>
						<div class="detail-center">
							<i class="iconfont">&#xe602;</i>
		                    <c:forEach items="${reportMap[\"reportNameList\"]}" var="item">
		                        <p>${item}</p>
		                    </c:forEach>
						</div>
						<a href="#member/service/report-load.action" class="detail-bottom">我的报告</a>
					</div>
					<div class="line-vertical"></div>
					<div class="service-detail-3">
						<div class="detail-top">
							<p><i class="iconfont">&#xe63c;</i></p>
							<p><span>${productMap["productCount"]}</span>个应用</p>
						</div>
						<div class="detail-center">
							<i class="iconfont">&#xe602;</i>
		                    <c:forEach items="${productMap[\"productNameList\"]}" var="item">
		                        <p>${item}</p>
		                    </c:forEach>
						</div>
						<a href="#member/service/application-purchase.action" class="detail-bottom">我的应用</a>
					</div>
				</div>
				<div class="msg-detail">
					<h2 class="content-title"><i class="iconfont">&#xe653;</i>我的收藏</h2>
		            <div class="table-wrapper block-body" id="table-wrapper">
		                <div class="table-operation clearfix" id="search-box">
							<div class="operation-left left">
								<label>类型</label>
								<select name="storeType" id="storeType" class="select">
									<option value="0">全部</option>
									<c:forEach items="${storeTypeArr}" var="item">
		                                <option value="${item[1]}">${item[0]}</option>
		                            </c:forEach>
								</select>
							</div>
							<div class="operation-right right">
								<a href="javascript:" class="order-switch order-desc <c:if test="${status.index == 0}">active</c:if>" data-val="">按收藏时间排序<i class="iconfont">&#xe623;</i></a>
							</div>
						</div>
						<table id="service-table" class="table">
		                    <thead>
		                        <tr>
		                            <th>序号</th>
		                            <th>服务名称</th>
		                            <th>类别</th>
		                            <th>收藏时间</th>
		                            <th>操作</th>
		                        </tr>
		                    </thead>
		                    <tbody></tbody>
		                </table>
		                <div id="pagination" class="pages"></div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../personal-menu.jsp"%>
	</div>
</div>

<script id="tmpl-services-row" type="text/html">
	<td>{{storeIndex}}</td>
    <td><div style="width: 150px;" title="{{serviceName}}"><a href="javascript:" class="service-detail">{{serviceName}}</a></div></td>
    <td>{{categoryName}}</td>
    <td>{{storeTime}}</td>
    <td><div style="width: 60px;"><a href="javascript:" class="update-store">取消收藏</a></div></td>
</script>

<script type="text/javascript">
	seajs.use('module/member/service/service', function(mod) {
		mod.run();
	});	
</script>