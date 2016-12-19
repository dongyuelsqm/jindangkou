'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone');

    var util = require('util/util'),
    	header = require('module/base/header');
    
    require('plugins/jquery-placeholder');
    require('perfect-scrollbar');
	
    /*
     * 请求处理
     *
     * 对请求进行配置
     * 开启监听对请求进行管理，url hash 部分的变化会引起操作。
     * 能够引起 hash 变化的只有界面变化的操作，如新建和编辑。
     * 动作，如删除、预览等将会留在当前界面进行操作，实时反馈，不引起 hash 变化。
     * 带有 query 部分的 url 需要进行特殊的处理。
     */
    var $content = $('#main'),
    	$subContent = $('#content');
    var Router = Backbone.Router.extend({
        routes: {
            "": "welcome",
            "*path:action!:subAction.action(/)": "subAction",
            "*path:action.action(/)": "action"
        },
        initialize: function() {
            G.$mask_msg.html('请稍候...').addClass('loading');
            if(G.isOldIE8()){
            	$('#J_body').addClass('overflow-auto');
            }else{
            	$('#J_body').perfectScrollbar();
            }
  
            this.listenTo(Backbone, "navigate", this._navigate);
        },
        execute: function(callback, args) {

            var path = Backbone.history.getFragment();

            args.unshift(path);

            if (callback) {
                callback.apply(this, args);
            }
        },
        welcome: function(path, queryString, query) {
            location.href = '#welcome.action';
//        	this.path = 'index.action';
        },
        action: function(path, subPath, action) {
            var query = {};
            query['t'] = (new Date).getTime();
            query['sync'] = true;
            //$content.load(path, query);
            this.path = path;
            this.query = query;
            this.checkLogin();
            this.loadContent();
        },
        subAction: function(path, subAction, action) {
            var query = {};
            query['t'] = (new Date).getTime();
            query['sync'] = true;
            //$content.load(path, query);
            this.path = path;
            this.query = query;
            this.checkLogin();
            this.loadContent();
        },
        _navigate: function(url, params, options) {
            options || (options = {trigger: false});
            params || (params = {});
            params = $.param(params);
            if (params != "") {
                url += (url.indexOf("?") > -1) ? "&" + params : "?" + params;
            }
            this.navigate(url, options);
        },
        addNavCurrentClass: function(){
        	header.addCurrentClass('#' + this.path);
        },
        checkLogin: function(){
        	if($('#user-name').length < 1 || $('#user-name').html === ''){
        		//未登录
        		this.path = location.hash.indexOf('member') > -1  ? 'login.action' : this.path;
        	}
        },
        clearOverlay: function(){
        	
        	//关闭定时器
        	if(G.timers.length > 0){
                _.each(G.timers, function(timer, index){
                	clearTimeout(timer);
                });
            }
            
            //关闭对话框
            $('.dialog-box').remove();
            $('.dialog-lock').remove();

            //remove modal
            if($('.modal').length > 0){
            	$('.modal').modal('hide');
            }
            
            //remove palceholder
            if(G.isOldIE9()){
            	$('.placeholder').remove();
            }
        },
        loadContent: function() {
        	this.clearOverlay();
           
            var _this = this;
            $.ajax({
                url: _this.path,
                data: _this.query,
                dataType: 'text',
//                sync: true,
                beforeSend: function(xhr){
                	$('#mask-msg').html('请稍候...').addClass('loading');
                    $('#mask').show();
                },
                complete: function(xhr, textStatus){
                	$('#mask-msg').removeClass('loading');
                	$('#mask').hide();
                },
                error: function(xhr, textStatus){
                	console.log(textStatus);
                	$('#mask-msg').html('请求页面失败，请稍后重试活或联系管理员');
                },
                success: function(rsp) {
                    var json;
                    try {
                        var json = JSON.parse(rsp);
                        if (!json.successSign && json.errorMessage) {
                            alert(json.errorMessage, function(){
                            	location.href = '#welcome.action';  //没有权限跳回首页
                            });
                        }
                    }
                    catch(err) { }
    
                    if (!json) {
                        $content.html(rsp);
                        
                        if($('#form-login').length > 0 && ($('#user-name').length > 0 || $('#user-name').html() === '')){
                        	location.reload();
                        }
                        
                        if($('#user-name').length > 0 && $('#user-name').html() === ''){
                        	location.reload();
                        }
                    }
                    
                    if(!G.isOldIE8()){
                    	$('#J_body').scrollTop(0);
                        $('#J_body').perfectScrollbar('update');
                    }
                    
//                    if(G.isOldIE9()){
//                    	$.each($('input[type=text], input[type=password], textarea'), function(){
//                			var $this = $(this),
//                				word = $this.attr('placeholder');
//                			$(this).placeholder({word: word})
//                		});
//                    }
                    
                    _this.addNavCurrentClass();
                }
            });
        }
    });
    
    var router = new Router();
    Backbone.history.start();
});
