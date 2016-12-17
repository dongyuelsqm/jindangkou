/**
 *     tabs 导航
 *     cailuwei <cailuwei@chinamobile.com>
 */
'use strict';
define(function (require, exports, module) {

    var $ = require('jquery'),
        Backbone = require('backbone');
    
    /**
    * api服务 view
    */
    var navView = Backbone.View.extend({
        events: {
            'click .nav-tabs>li>a': 'addClass'
        },
        initialize: function (options) {
            this.$tabs = this.$('.nav-tabs');
            this.callback = options.callback;
        },
        addClass: function(ev){
            var $this = $(ev.currentTarget),
                $li = $this.parent();
            this.index = $this.data('index');
            
            this.$('.active').removeClass('active');
            $li.addClass('active');
            
            this.execution() || {};
            
            if(this.callback && typeof this.callback === 'function'){
            	this.callback(ev);
            }
        },
        execution: function(){
        	this.$('#tab-content-'+ this.index).parent().find('.display-block').removeClass('display-block');
            this.$('#tab-content-'+ this.index).addClass('display-block');
        }
    });
    
    return navView;
});