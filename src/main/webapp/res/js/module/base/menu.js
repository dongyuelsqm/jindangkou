/**
 * 金档口 - 菜单
 * cailuwei<>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        util = require('util/util');

    var template = util.template;

    var menu = Backbone.View.extend({
//        timer: null,
//        interval: 500,
//        opened: false,
        events: {
            'click a.route': 'route'
        },
        initialize: function (options) {
        	this.menuData = options.params;

        	// 菜单界面
        	if (!_.isArray(this.menuData)) {
        	    this.menuData = [];
        	}
            
            var href = location.href;
            _.each(this.menuData, function(item, index){
                item.active = '';
                // item._url = '';
                item.type = '';
                item.disabled = item.disabled ? 'disabled' : '';
	            
                if(item.isParent && item.children.length > 0){
                    _.each(item.children, function(t, i){
                        t.active = '';
                        // t._url = '',
                        t.type = '';
                        t.disabled = t.disabled ? 'disabled' : '';
                        
        	            if(t.url === ''){
        	            	t.url = 'javascript:';
        	            	t.type = 'select';
        	            }else{
        	            	if(href.indexOf(t.url) > -1){
                                t.active = 'active';
                            }
        	            	t.type = 'route';
                            t.url = G.contextPath + 'website/' + t.url;
        	            }
                    });
                    
                }
                
                if(item.url === ''){
	            	item.url = 'javascript:';
	            	item.type = 'select';
	            }else{
	            	if(href.indexOf(item.url) > -1){
                        item.active = 'active';
                    }
	            	item.type = 'route';
                    item.url = G.contextPath + 'website/'  + item.url;
	            }
            });
            this.render();
        	
        },
        addPanelClass: function ($this) {
            var $p = $this.parent().parent();
            this.$el.find('.active').removeClass('active');
            if($p.hasClass('sub')){
                $p.parent().addClass('active');
            }
        	$this.parent().addClass('active');

        },
        route: function(e) {
            e.preventDefault();
            var $this = $(e.currentTarget);

            var href = $this.attr('href');
            if (href === 'javascript:' || $this.hasClass('disabled')) return false;

            location.href = href;
        },
        render: function(){
            if (this.menuData) {
        	    var submenuRender = template('submenu');
        	    this.$el.html(submenuRender({
        	        items: this.menuData
        	    }));
        	}
            
            return this;
        }
    });

    return menu;
});
