<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<style>
    .departments{margin-left: -15px; }
    .departments > li{float: left; padding-left: 15px; position: relative; width: 20%;}
        .departments > li .inline-checkbox{left: 15px; position: absolute; top: 0;}
        .departments > li input[type=text]{width: 100%;}
            .departments > li.active input[type=text]{border-color: #ea3527;}
</style>
<h1 class="content-title">整理分类</h1>
<div class="" id="department-wrapper">
    <div class="operations block-body clearfix">
        <div class="operation-left pull-left" id="title">
            <span class="checkbox-inline"><input type="checkbox" class="checkbox checkbox-all" /></span>
            <a class="btn btn-submit" href="javascript:" role="btn-delete" data-do="delete">删除分类</a>
            <a class="btn btn-submit" href="javascript:" role="btn-edit" data-do="edit">编辑分类</a>
        </div>
        <div class="operation-left pull-left" id="edit-title" style="display: none;">编辑分类</div>
        <div class="operation-right pull-right">
            <div class="input-group search-input">
                <input type="text" class="form-control" name="" placeholder="">
                <span class="input-group-addon" data-do="add" role="btn-add">添加分类</span>
            </div>
        </div>
    </div>
    <ul class="block-body departments clearfix" id="departments">
        <li>
            <input class="" type="text" value="" readonly name=""/>
            <span class="inline-checkbox">
        <input class="checkbox" type="checkbox" value="" name="" />
    </span>
        </li>
    </ul>
    <div class="block-body text-center">
        <button type="button" class="btn btn-submit" data-do="save" role="btn-save">保存分类</button>
    </div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/html" id="tmpl-departmentItem">
    <input class="" type="text" value="{{name}}" readonly name=""/>
    <span class="inline-checkbox">
        <input class="checkbox" type="checkbox" value="{{id}}" name="" />
    </span>
</script>

<script type="text/javascript">
    seajs.use('module/product/department', function(mod){
        mod.run();
    });
</script>

</body>
</html>