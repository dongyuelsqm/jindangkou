<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
                            </div>
                        </div>
                        <div class="content-menu content-left" id="menu">
                            <ul>
                                <li id="item-{{item.id}}" class="item {{item.active}}">
                                    <a href="{{item._url}}" class="{{item.type}} {{item.disabled}}" data-menuid="{{item.id}}">{{item.name}}</a>
                                    <ul class="sub {{item.id}}-sub">
                                        <li id="sub-item-{{child.id}}" class="sub-item {{child.active}}">
                                            <a href="{{child._url}}" class="{{child.type}} {{child.disabled}}" data-menuid="{{child.id}}"><i class="iconfont">&#xe62a;</i></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer" class="footer">
            <div class="container">
                <div class="about pull-left">
                    &copy; 2014-2015
                    <a href="//hy.10086.cn/" target="_blank">中国移动杭州研发中心</a><span>|</span>
                    <a href="//hy.10086.cn/about/index.html" target="_blank">公司简介</a><span>|</span>
                    <a href="//hy.10086.cn/about/contact.html" target="_blank">联系方式</a><span>|</span>
                    京ICP备05002571号 © 中国移动通信版权所有
                </div>
                <div class="link pull-right">
                    <a href="mailto:gouxin@chinamobile.com" title="联系我们"><i class="iconfont">&#xe605;</i></a>
                    <a href="javascript:" title="关注我们" class="attention">
                        <i class="iconfont">&#xe603;</i>
                        <img src="${contextPath}/res/css/base/img/qrcode.png" alt="关注我们 - 二维码" class="qrcode"/> 
                    </a>
                </div>
            </div>
        </div>
    </div>
<!-- seajs -->
<script src="../../res/js/lib/seajs/sea.js" id="seajsnode"></script>
<script type="text/javascript">
    seajs.config({
        debug: true,
        //map: [
        //    ['.js', '.js?' + G.nowTime]
        //],
        base: G.contextPath + '/res/js',
        alias: {
            'jquery': 'lib/jquery/jquery-debug',
            'underscore': 'lib/underscore/1.8.3/underscore',
            'backbone': 'lib/backbone/1.2.1/backbone',

            'perfect-scrollbar': 'plugins/perfectscroll/perfect-scrollbar-debug',
            'jquery-validate': 'plugins/jquery-validate/jquery-validate-debug',
            'jquery-validate-add': 'util/jquery-validate-add',
            'jquery-flexslider': 'plugins/jquery-flexslider/jquery-flexslider',
            //'jquery-uploadify': 'plugins/jquery-uploadify/jquery-uploadify',
            'jquery-md5': 'plugins/jquery-md5',
            'rsa': 'plugins/rsa',
            
            'bootstrap-modal': 'plugins/bootstrap/modal',
            'plupload': 'plugins/plupload/plupload',
            'pdf-object': 'plugins/pdf-object/pdf-object',
            'echarts': 'plugins/echarts/echarts-debug'
        }
    });
    seajs.use('module/base/main');
</script>
</body>
</html>