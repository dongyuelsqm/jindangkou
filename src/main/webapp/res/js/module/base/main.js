/**
 * 金档口 - main
 * cailuwei<>
 */
'use strict';
/**
 * 防止跨域攻击
 * 传tokenTime
 */
function getTokenTime() {
    var str = '';
    var localTime = new Date();
    var newTime = new Date();
    var millisec = localTime.getTime() - parseInt(G.deltaTime);
    newTime.setTime(millisec);
    var y = newTime.getFullYear().toString();
    var M = (newTime.getMonth() + 1) > 9 ? (newTime.getMonth() + 1).toString() : '0' + (newTime.getMonth() + 1);
    var d = newTime.getDate() > 9 ? newTime.getDate().toString() : '0' + newTime.getDate();
    var h = newTime.getHours() > 9 ? newTime.getHours().toString() : '0' + newTime.getHours();
    var m = newTime.getMinutes() > 9 ? newTime.getMinutes().toString() : '0' + newTime.getMinutes();
    var s = newTime.getSeconds() > 9 ? newTime.getSeconds().toString() : '0' + newTime.getSeconds();
    str = y + M + d + h + m + s;
    return G.tokenMark + '-' + str;
}
G.getTokenTime = getTokenTime;

/**
 * 判断浏览器版本是否是ie8 以下
 */
function isOldIE8(){
	var browser = navigator.appName 
    if(browser === "Microsoft Internet Explorer"){
    	var b_version=navigator.appVersion,
    		version=b_version.split(';'),
        	trim_Version=version[1].replace(/[ ]/g, '');
    	if(trim_Version === 'MSIE6.0' || trim_Version === 'MSIE7.0' || trim_Version === 'MSIE8.0'){
    		return true;
    	}else{
    		return false;
    	}
    }else{
    	return false;
    }
}
G.isOldIE8 = isOldIE8;

/**
 * 判断浏览器版本是否是ie8 以下
 */
function isOldIE9(){
	var browser = navigator.appName 
    if(browser === "Microsoft Internet Explorer"){
    	var b_version=navigator.appVersion,
    		version=b_version.split(';'),
        	trim_Version=version[1].replace(/[ ]/g, '');
    	if(trim_Version === 'MSIE6.0' || trim_Version === 'MSIE7.0' || trim_Version === 'MSIE8.0' || trim_Version === 'MSIE9.0'){
    		return true;
    	}else{
    		return false;
    	}
    }else{
    	return false;
    }
}
G.isOldIE9 = isOldIE9;

/**
 * 判断是否支持canvas
 */
function isSupportCanvas(){
	var elem = document.createElement('canvas');
	return (elem.getContext && elem.getContext('2d'));
}
G.isSupportCanvas =isSupportCanvas;

define(function(require, exports, module){
	var $ = require('jquery'),
	    _ = require('underscore'),
	    util = require('util/util'),
	    dialog = require('util/dialog').cmccDialog;

    require('perfect-scrollbar');

	$.ajaxSetup({
        timeout: 30000,
		error: function(xhr, status){
        	alert('发生错误，请稍候重试！');
		}
    });
    $.ajaxPrefilter(function(options, originalOptions, jqXHR) {
        var data = originalOptions.data || {};
        if (_.isArray(data)) {
        	$.each(data, function(index, item){
        		if(item.name === 'tokenTime'){
        			data.splice(index, 1);
        			return false;
        		}
        	});
            data.push({
                name: 'tokenTime',
                value: getTokenTime()
            });
        } else if (_.isObject(data)) {
            data['tokenTime'] = getTokenTime();
        } else if (_.isString(data)) {
        	data += '&tokenTime=' + getTokenTime();
        }

        if (options.dataType === 'json') {
        	if(_.isString(data)){
            	options.data = data;
            }else{
            	options.data = $.param(data);
            }
        }
    });
    
    /**
    * 获取mask
    */
    var tmpl_mask = util.template('mask');
    $('body').append(tmpl_mask());
    G.$mask = $('#mask');
    G.$mask_msg = G.$mask.find('#mask-msg');
    
    /**
     * 初始化modal
     */
    var modal = util.template('modal');
    $('body').append(modal());
    G.$modal = $('#modal');
    G.$modal_body = G.$modal.find('.modal-body');
    
    /**
     * 重写对话框
     */
    window.alert = function(content, callback, options) {
    	var button = [{
				value: '确定',
				width: 150,
				callback: function(){
					return callback && callback();
				}
			}],
			default_options = {
                title: '提示',
                content: content,
                width: 400,
                padding: '20px 15px',
                lock: true,
                fixed: true,
                zIndex: 1050,
                button: button
            };
		
		return new dialog($.extend(true,{}, default_options, options));
	};

    window.confirm = function(content, callback1, callback2, options) {
        var opt = options ? options : (callback2 && _.isObject(callback2) ? callback2 : {}),
			buttons = [{
				value: '确定',
				width: 100,
				callback: function(){
					return callback1 && callback1();
				}
			}, {
				value: '取消',
				width: 100,
				cssClass: 'btn-white',
				callback: function(){
                    return callback2 && _.isFunction(callback2) && callback2();
				}
			}],
            default_options = {
                title: '提示',
                content: content,
                width: 400,
                padding: '20px 15px',
                lock: true,
                fixed: true,
                zIndex: 1050,
                button: buttons
            };

        return new dialog($.extend(true,{}, default_options, opt));
    };
    
	/**
	 * 重新加载 
	 */
	// $(window).on('resize', function(e){
	// 	location.reload();
	// });

	/**
	 * 添加滚动条
	 */
    if(G.isOldIE8()){
        $('#J_body').addClass('overflow-auto');
    }else{
        $('#J_body').perfectScrollbar();
    }

	/**
	 * 公司信息
	 */
	try {
	    if (window.console && window.console.log) {
	        console.log('中移（杭研）信息技术有限公司');
	        console.log('创新业务产品部 - 大数据团队');
	        console.log('来加入我们吧  %c biubiubiu@chinamobile.com', 'color:red');
	    }
	} catch (e){}
	
    // require('module/base/router');
});