<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/head.jsp"%>
<h1 class="content-title">添加商品</h1>
<form class="block-body form">
    <div class="row">
        <label class="col-md-3 control-label">商品名称<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="text" name="realName" id="realName" placeholder="请输入商品名称" value="{{}}"/>
        </div>
        <div class="col-md-9 col-md-offset-3 comment">请输入商品名称</div>
    </div>
</form>
<%@include file="../common/footer.jsp"%>

<script type="text/javascript">
    seajs.use('module/product/add', function(mod){
        mod.run();
    });
</script>
