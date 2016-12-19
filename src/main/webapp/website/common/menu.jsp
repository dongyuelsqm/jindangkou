<%@ page language="java" pageEncoding="UTF-8"%>
<div class="content-menu content-left" id="menu"></div>
<script type="text/javascript">
	'use strict';
	seajs.use(['module/base/menu', 'module/defualt/menuData'], function(menu, menuData){
		new menu({
			 el: '#menu',
			 params: menuData
		 });
	});
</script>