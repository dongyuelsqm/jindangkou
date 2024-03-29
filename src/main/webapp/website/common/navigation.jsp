<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<div id="navigation" class="navigation">
      <div class="navs-wrapper">
          <div class="container clearfix">
              <span class="brand pull-left"></span>
              <ul class="navs pull-right" id="log">
                  <li id="nav-7" style="padding-right: 0;">
                      <a href="${contextPath}/website/member/personal-center.action" >
                        <span id="user-name">ll</span>
                        <label class="highlight" id="message-count"></label>
                      </a>
                  </li>
                  <li id="" style="padding: 0 5px;">|</li>
                  <li id="nav-8" style="padding: 0;"><a href="javascript:" id="logout">退出</a></li>
              </ul>
              <ul class="navs pull-right" id="navs">
                  <li id="nav-1" <c:if test="${currentPath == 'index'}">class="active"</c:if> ><a href="${contextPath}/website/index.jsp" >首页</a></li>
                  <li id="nav-2" <c:if test="${currentPath == 'products'}">class="active"</c:if> ><a href="${contextPath}/website/products.jsp" >产品大全</a></li>
                  <li id="nav-3" <c:if test="${currentPath == 'try-use'}">class="active"</c:if> ><a href="${contextPath}/website/try-use.jsp" >免费试用</a></li>
                  <li id="nav-4" <c:if test="${currentPath == 'agent'}">class="active"</c:if> ><a href="${contextPath}/website/agent.jsp" >区域代理</a></li>
                  <li id="nav-5" <c:if test="${currentPath == 'about'}">class="active"</c:if> ><a href="${contextPath}/website/about.jsp" >关于我们</a></li>
              </ul>
          </div>
      </div>
  </div>