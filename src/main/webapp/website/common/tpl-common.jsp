<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<script type="text/html" id="tmpl-submenu">
<# var i = 0, j = 0, children, item; #>
<ul>
	<# 
		while(item = items[i++]){
	 #>
    <li id="item-{{item.id}}" class="item {{item.active}}">
        <a href="{{item.url}}" class="{{item.type}} {{item.disabled}}" data-menuid="{{item.id}}"><i class="iconfont">{{{item.icon}}}</i>{{item.name}}</a>
        <# 
			if(item.children && item.children.length > 0) { 
				j = 0; 
		#>
        <ul class="sub {{item.id}}-sub">
			<#
				while(child = item.children[j++]){ 
			#>
            <li id="sub-item-{{child.id}}" class="sub-item {{child.active}}">
				<a href="{{child.url}}" class="{{child.type}} {{child.disabled}}" data-menuid="{{child.id}}"><i class="iconfont">&#xe617;</i>{{child.name}}</a>
			</li>
			<# } #>
        </ul>
        <# } #>
    </li>
    <# } #>
</ul>
</script>

<script id="tmpl-pageGuide" type="text/html">
<span class="total-count">共 {{totalCount}} 项</span>
<a href="javascript:" class="first">&lt;&lt;</a>
<a href="javascript:" class="prev">&lt;</a>
<span><input type="text" class="paged" value="{{pageNo}}"> of {{totalPages}}</span>
<a href="javascript:" class="next">&gt;</a>
<a href="javascript:" class="last">&gt;&gt;</a>
<a href="javascript:" class="go">go</a>
</script>

<script id="tmpl-mask" type="text/html">
<div class="mask" id="mask"><div class="mask-msg" id="mask-msg"></div></div>
</script>