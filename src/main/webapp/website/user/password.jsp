<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">修改密码</h1>
<form class="block-body form" id="password-form">
    <input type="hidden" name="id" value=""/>
    <div class="row">
        <label class="col-md-3 control-label">原始密码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="password" name="param1" placeholder="请输入原始密码" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">重新设置密码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="password" name="param2" placeholder="请输入新密码" value=""/>
        </div>
    </div>
    <div class="row">
        <label class="col-md-3 control-label">再次确认密码<span class="required">*</span></label>
        <div class="col-md-5">
            <input type="password" name="param3" placeholder="请输入确认密码" value=""/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-3 col-md-5">
            <button type="button" class="btn btn-submit" data-do="submit" role="btn-submit">提交</button>
        </div>
    </div>
</form>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/user/password', function(mod){
        mod.run();
    });
</script>
</body>
</html>
