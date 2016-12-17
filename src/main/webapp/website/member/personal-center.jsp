<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<script type="text/javascript">
	G.tokenMark = '${tokenMark}';
</script>
<link rel="stylesheet" href="${contextPath}/res/css/plugins/jquery-uploadify/uploadify.css" />
<link rel="stylesheet" href="${contextPath}/res/css/module/personal.css" />
<div class="container">
    <div class="breadcrumb">
        <span>个人中心</span>
    </div>
    <div class="outer-content">
		<div class="content-wrapper">
			<div class="content-offset-left content" id="content">
				<div class="user-info">
					<div class="user-info-left col-sm-5">
						<!-- ${userAvatar} 头像路径 -->
						<div class="user-head pull-left">
							<c:choose> 
								<c:when test="${!empty userAvatar}">   
							    	<img src="${userAvatar}" alt="头像" class="user-head-img"/>
							  	</c:when> 
							  	<c:otherwise>   
							    	<img src="${contextPath}/res/css/module/img/member/personal/header.jpg" alt="头像" class="user-head-img"/>
							  	</c:otherwise> 
							</c:choose>
							<a href="javascript:" class="change-user-head">编辑头像</a>
						</div>
						<b>${userName},<span id="time"></span></b>
					</div>
					<div class="line-vertical pull-left"></div>
					<div class="user-info-right col-sm-5">
						<p>
							账户类型 : ${userType} 
							<c:if test="${authentication}"><a href="#member/personal/identity-verification.action">个人开发/企业开发者认证</a></c:if>
						</p>
						<p>APIKEY : ${apiKey}</p>
					</div>
				</div>
				<div class="user-detail block-body">
					<div class="user-detail-1">
						<h2 class="bold">服务中心</h2>
						<div class="detail-left">
							<p><i class="iconfont">&#xe637;</i></p>
							<p>调用API<span><c:if test="${!empty callAPICount}">${callAPICount}</c:if><c:if test="${empty callAPICount}">0</c:if></span>个</p>
						</div>
						<div class="detail-center"></div>
						<div class="detail-right">
							<p><i class="iconfont">&#xe626;</i></p>
							<p>下载报告<span><c:if test="${!empty reportDownloadCount}">${reportDownloadCount}</c:if><c:if test="${empty reportDownloadCount}">0</c:if></span>份</p>
						</div>
					</div>
					<div class="user-detail-2">
						<h2 class="bold">发布中心</h2>
						<div class="detail-left">
							<p><i class="iconfont">&#xe630;</i></p>
							<p>发布API<span><c:if test="${!empty releaseAPICount}">${releaseAPICount}</c:if><c:if test="${empty releaseAPICount}">0</c:if></span>个</p>
						</div>
						<div class="detail-center"></div>
						<div class="detail-right">
							<p><i class="iconfont">&#xe633;</i></p>
							<p>发布报告<span><c:if test="${!empty releaseReportCount}">${releaseReportCount}</c:if><c:if test="${empty releaseReportCount}">0</c:if></span>份</p>
						</div>
					</div>
					<div class="user-detail-3">
						<h2 class="bold">我的余额</h2>
						<div class="detail-left">
							<p><i class="iconfont">&#xe615;</i></p>
							<p>余额<span><c:if test="${!empty userBanlance}">${userBanlance}</c:if><c:if test="${empty userBanlance}">0</c:if></span>元</p>
						</div>
						<div class="detail-center"></div>
						<div class="detail-right">
							<p><i class="iconfont">&#xe631;</i></p>
							<p>收益<span><c:if test="${!empty income}">${income}</c:if><c:if test="${empty income}">0</c:if></span>元</p>
						</div>
					</div>
				</div>
				<div class="msg-detail">
					<h2 class="content-title"><i class="iconfont">&#xe645;</i>消息中心</h2>
		            <div class="table-wrapper block-body" id="table-wrapper">
			            <div class="table-operation clearfix" id="search-box">
							<div class="operation-left pull-left">
								<a href="javascript:" data-val="0" class="active order-by" data-name="type"><i class="iconfont">&#xe653;</i>全部</a>
								<span class="separate">|</span>
								<a href="javascript:" data-val="1" class="order-by" data-name="type"><i class="iconfont">&#xe643;</i>系统消息(<label class="highlight" id="sys-message-count"></label>)</a>
								<span class="separate">|</span>
								<a href="javascript:" data-val="2" class="order-by" data-name="type"><i class="iconfont">&#xe644;</i>审核消息(<label class="highlight" id="check-message-count"></label>)</a>
								<!-- 
								<span class="separate">|</span>
								<a href="javascript:" data-val="3" class="order-by" data-name="type"><i class="iconfont">&#xe646;</i>收藏更新</a>
								 -->
							</div>
							<div class="operation-right pull-right">
								<a href="javascript:" class="update-status-all"><i class="iconfont">&#xe627;</i>全部标记为已读</a>
								<a href="javascript:" class="update-status"><i class="iconfont">&#xe641;</i>标记为已读</a>
								<a href="javascript:" class="delete-mul"><i class="iconfont">&#xe63b;</i>删除</a>
							</div>
						</div>
						<table id="personal-center-table" class="table">
		                    <thead>
		                        <tr>
		                            <th><input type="checkbox" class="checkbox checkbox-all" id="checkbox-all" />全选</th>
		                            <th>内容</th>
		                            <th>时间</th>
		                        </tr>
		                    </thead>
		                    <tbody></tbody>
		                </table>
		                <div id="pagination" class="pages"></div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="./personal-menu.jsp"%>
	</div>
</div>

<script id="tmpl-persenMessage-row" type="text/html">
    <td><input type="checkbox" class="checkbox" name="id[]" value="{{messageId}}"/></td>
    <td><div class="message-content" style="width: 400px;" >[{{messageTag}}]{{messageContent}}</div></td>
    <td><div class="" style="width: 120xp;">{{createTime}}</div></td>
</script>

<script type="text/html" id="tmpl-msgDetail">
	<b>消息内容:</b>
	<div class="">
		[{{messageTag}}]{{messageContent}}
	</div>
</script>

<script id="tmpl-userHeaderUpload" type="text/html">
	<input type="hidden" value="" class="userAvatar" name="userAvatar" />
	<div class="row">
		<div class="col-md-2">
			<b>本地上传</b>
		</div>
		<div class="col-md-10">仅支持JPG、GIF、PNG、JPEG格式，且文件小于2M</div>
		<div class="col-md-10 col-md-offset-2 comment">欧朋浏览器暂不支持flash，请下载falsh插件并开启falsh或者更换浏览器查看</div>
	</div>
	<div class="row">
		<div class="col-md-12" style="width: 600px;">
			<div id="alt-content"></div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-12">
			<b>系统推荐</b>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" class="photos">
			<c:forEach var="photo" items="${systemPhotoList}">
				<img src="${photo.photoUrl}" alt="头像预览" class="user-head-img sys-recommendation" />
			</c:forEach>
		</div>
	</div>
</script>

<script id="tmpl-userModal" type="text/html">
	<div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-label">消息</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript">
/**
 * faustcplus - 回调
 */
function uploadevent(json) {
	console.log(json);
	var status = json.status.toString();
	switch (status) {
		case '0':
			alert('图片上传成功!');
			$('input[name="userAvatar"]').val(json.url);
			$('.photos').find('.active').removeClass('active');
			break;
		case '-1':
			alert('没有接收到图片！');
            break;
		case '-2':
			alert('图片尺寸异常！');
			break;
		case '-3':
			alert('图片上传失败！');
			break;
		default:
			console.log(typeof (status) + ' ' + status);
	}
}
</script>

<script type="text/javascript">
	seajs.use('module/member/personal-center', function(mod){
		mod.run();
	});	
</script>