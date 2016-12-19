<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<!DOCTYPE html>
<head>
	<title>数据工坊</title>
	<link href="../../res/css/base/img/favicon.ico" rel="shortcut icon" />
	<link rel="stylesheet" href="../../res/css/base/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../../res/css/plugins/perfectscroll/perfect-scrollbar.min.css" />
	<link rel="stylesheet" href="../../res/css/base/layout.css" />
	<link rel="stylesheet" href="../../res/css/module/style.css" />
	<!--[if lte IE 8]>
	<script type="text/javascript" src="${contextPath}/res/js/plugins/respond.js"></script>
	<![endif]-->
	<script>
        //全局变量
        window.G = {};
	</script>
</head>
<body>
<div id="J_body" class="J_body">
	<div id="J_main" class="J_main">
		<div id="header" class="header">
			<div class="navs-wrapper">
				<div class="container clearfix">
					<span class="brand pull-left"></span>
					<ul class="navs pull-right" id="log">
						<li id="nav-7" style="padding-right: 0;">
							<a href="#member/personal-center.action" data-href="member/personal-center.action" >
								<span id="user-name">${}</span>
								<label class="highlight" id="message-count"></label>
							</a>
						</li>
						<li id="" style="padding: 0 5px;">|</li>
						<li id="nav-8" style="padding: 0;"><a href="javascript:" data-href="logout.action" id="logout">退出</a></li>
						<!--<c:if test="${empty UserUserBind}">
							<li id="nav-7"><a href="javascript:" data-href="login.action" id="login">登录</a></li>
							</c:if>-->
					</ul>
					<ul class="navs pull-right" id="navs">
						<li id="nav-1"><a href="#welcome.action" data-href="welcome.action">首页</a></li>
						<li id="nav-2"><a href="javascript:" data-href="">产品大全</a></li>
						<li id="nav-3"><a href="#solution/solution.action" data-href="solution/solution.action">免费试用</a></li>
						<li id="nav-4"><a href="#" data-href="waiting">签约服务</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main" id="main">
			<div class="container">
				<div class="outer-content">
					<div class="content-wrapper">
						<div class="content-offset-left">
							<ul class="content-top clearfix">
								<li>
									<p>8</p>
									<p>test</p>
								</li>
								<li>
									<p>8</p>
									<p>test</p>
								</li>
								<li>
									<p>8</p>
									<p>test</p>
								</li>
								<li>
									<p>8</p>
									<p>test</p>
								</li>
								<li>
									<p>8</p>
									<p>test</p>
								</li>
							</ul>
							<div class="content">
								<h1 class="content-title">实名认证</h1>
								<form class="block-body form">
									<div class="row">
										<label class="col-md-3 control-label">商品名称<span class="required">*</span></label>
										<div class="col-md-5">
											<input type="text" name="realName" id="realName" placeholder="请输入真实姓名" value="{{realName}}"/>
										</div>
										<div class="col-md-9 col-md-offset-3 comment">请输入与身份证或护照相符的真实姓名</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="content-menu content-left" id="menu">
						<ul>
							<li id="item-{{item.id}}" class="item {{item.active}}">
								<a href="{{item._url}}" class="{{item.type}} {{item.disabled}}" data-menuid="{{item.id}}">{{item.name}}</a>
								<ul class="sub {{item.id}}-sub">
									<li id="sub-item-{{child.id}}" class="sub-item {{child.active}}">
										<a href="{{child._url}}" class="{{child.type}} {{child.disabled}}" data-menuid="{{child.id}}"><i class="iconfont">&#xe62a;</i></a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer" class="footer">
		<div class="container">
			<div class="about pull-left">
				&copy; 2014-2015
				<a href="//hy.10086.cn/" target="_blank">中国移动杭州研发中心</a><span>|</span>
				<a href="//hy.10086.cn/about/index.html" target="_blank">公司简介</a><span>|</span>
				<a href="//hy.10086.cn/about/contact.html" target="_blank">联系方式</a><span>|</span>
				京ICP备05002571号 © 中国移动通信版权所有
			</div>
			<div class="link pull-right">
				<a href="mailto:gouxin@chinamobile.com" title="联系我们"><i class="iconfont">&#xe605;</i></a>
				<a href="javascript:" title="关注我们" class="attention">
					<i class="iconfont">&#xe603;</i>
					<img src="${contextPath}/res/css/base/img/qrcode.png" alt="关注我们 - 二维码" class="qrcode"/>
				</a>
			</div>
		</div>
	</div>
</div>
<!-- seajs -->
<script src="../../res/js/lib/seajs/sea.js" id="seajsnode"></script>
<script type="text/javascript">
    seajs.config({
        debug: true,
        //map: [
        //    ['.js', '.js?' + G.nowTime]
        //],
        base: G.contextPath + '/res/js',
        alias: {
            'jquery': 'lib/jquery/jquery-debug',
            'underscore': 'lib/underscore/1.8.3/underscore',
            'backbone': 'lib/backbone/1.2.1/backbone',

            'perfect-scrollbar': 'plugins/perfectscroll/perfect-scrollbar-debug',
            'jquery-validate': 'plugins/jquery-validate/jquery-validate-debug',
            'jquery-validate-add': 'util/jquery-validate-add',
            'jquery-flexslider': 'plugins/jquery-flexslider/jquery-flexslider',
            //'jquery-uploadify': 'plugins/jquery-uploadify/jquery-uploadify',
            'jquery-md5': 'plugins/jquery-md5',
            'rsa': 'plugins/rsa',

            'bootstrap-modal': 'plugins/bootstrap/modal',
            'plupload': 'plugins/plupload/plupload',
            'pdf-object': 'plugins/pdf-object/pdf-object',
            'echarts': 'plugins/echarts/echarts-debug'
        }
    });
    seajs.use('module/base/main');
</script>
</body>
</html>