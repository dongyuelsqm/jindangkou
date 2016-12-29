<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ include file="../common/upper-part.jsp"%>
<h1 class="content-title">店铺公告</h1>
<form class="block-body form" id="notice-form">
    <div class="row">
        <div class="col-md-12">
            <textarea name="code" id="code" placeholder="请输入公告内容" ></textarea>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <button type="button" class="btn btn-submit" data-do="submit" role="btn-submit">发布公告</button>
        </div>
    </div>
</form>
<div id="table-wrapper" class="table-wrapper block-body">
    <table class="table" id="notice-table">
        <thead>
            <tr>
                <th>历史查询</th>
            </tr>
        </thead>
        <tbody>
        <tr>
            <td>2016-12-12 14:20:20</td>
            <td>100</td>
            <td>
                <div><a href="javascript:" class="editor" role="delete">删除</a></div>
            </td>
        </tr>
        </tbody>
    </table>
    <div id="pagination" class="pages"></div>
</div>
<%@include file="../common/lower-part.jsp"%>

<script type="text/javascript">
    seajs.use('module/shopping-chart/notice', function(mod){
        mod.run();
    });
</script>
