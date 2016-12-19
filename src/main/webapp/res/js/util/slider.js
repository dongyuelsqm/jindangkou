/**
 *  轮播
 *  cailuwei <cailuwei@chinamobile.com>
 */
'use strict';
define(function (require, exports, module) {

    var $ = require('jquery'),
        Backbone = require('backbone'),
        _ = require('underscore');
    
    require('plugins/jquery-mousewheel');
    require('jquery-flexslider');
    
    var sliderView = Backbone.View.extend({
        initialize: function (options) {
        	var context = options.context || {};
        	var default_opt = {
                    animation: 'slide',          //播放方式 slide or fade
//                    animationLoop: true,         //是否循环
                    direction: 'horizontal',     //滚动方向 vertival or horizontal
//                    slideshow: true,             //是否自动播放
                    directionNav: false,         //是否显示左右（上一张/下一张）控制 
//                    controlNav: true,            //是否显示底部导航
//                    mousewheel: true             //是否通过鼠标滚轮控制，需要配合 jquery.mousewheel.js
                };
            
            _.extend(default_opt, context);
            
            if(options.callback){
            	_.extend(default_opt, options.callback);
            }
            
            this.$el.flexslider(default_opt);
        }
    });
    
    return sliderView;
});