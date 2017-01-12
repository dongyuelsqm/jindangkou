<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">短信群发</h1>
<div class="block-body"></div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/wechart/sms-group', function(mod){
        mod.run();
    });
</script>
