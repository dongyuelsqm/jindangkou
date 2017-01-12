<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<!DOCTYPE html>
<head>
	<title>金档口</title>
	<link href="${contextPath}/res/css/base/img/favicon.ico" rel="shortcut icon" />
	<link rel="stylesheet" href="${contextPath}/res/css/base/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${contextPath}/res/css/plugins/perfectscroll/perfect-scrollbar.min.css" />
	<link rel="stylesheet" href="${contextPath}/res/css/base/layout.css" />
	<link rel="stylesheet" href="${contextPath}/res/css/base/style.css" />
	<!--[if lte IE 8]>
	<script type="text/javascript" src="${contextPath}/res/js/plugins/respond.js"></script>
	<![endif]-->
	<!-- seajs -->
	<script src="${contextPath}/res/js/lib/seajs/sea.js" id="seajsnode"></script>
	<script type="text/javascript">
        seajs.config({
            debug: true,
            //map: [
            //    ['.js', '.js?' + G.nowTime]
            //],
            base: '${contextPath}/res/js',
            alias: {
                'jquery': 'lib/jquery/jquery-debug',
                'underscore': 'lib/underscore/1.8.3/underscore',
                'backbone': 'lib/backbone/1.2.1/backbone',

                'perfect-scrollbar': 'plugins/perfectscroll/perfect-scrollbar',
                'jquery-validate': 'plugins/jquery-validate/jquery-validate',
                'jquery-validate-add': 'util/jquery-validate-add',
                'jquery-flexslider': 'plugins/jquery-flexslider/jquery-flexslider',
                'jquery-md5': 'plugins/jquery-md5',
                'rsa': 'plugins/rsa',

                'bootstrap-modal': 'plugins/bootstrap/modal',
                'plupload': 'plugins/plupload/plupload',
//                'pdf-object': 'plugins/pdf-object/pdf-object',
                'echarts': 'plugins/echarts/echarts-debug'
            }
        });
	</script>
	<script>
		//全局变量
		window.G = {};
		G.contextPath = '${contextPath}/';
	</script>
</head>