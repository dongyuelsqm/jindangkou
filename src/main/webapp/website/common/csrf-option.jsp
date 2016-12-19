<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<script>
	G.tokenMark = '${tokenMark}';
	if(!G.deltaTime){
		G.serverTime = '${serverTime}';
		G.deltaTime = (new Date(G.serverTime)).getTime() - (new Date()).getTime();
	}
</script>