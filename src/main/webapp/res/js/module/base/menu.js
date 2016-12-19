/**
 * 菜单
 * cailuwei<cailuwei@chinamobile.com>
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
            
            var hash = location.hash.split('#')[1];
            _.each(this.menuData, function(item, index){
                item.active = '';
                item._url = '';
                item.type = '';
                item.disabled = item.disabled ? 'disabled' : '';
	            
                if(item.isParent && item.children.length > 0){
                    _.each(item.children, function(t, i){
                        t.active = '';
                        t._url = '',
                        t.type = '';
                        t.disabled = t.disabled ? 'disabled' : '';
                        
        	            if(t.url === ''){
        	            	t._url = 'javascript:';
        	            	t.type = 'select';
        	            }else{
        	            	if(t.url === hash){
                                t.active = 'active';
                            }
        	            	t._url = '\#' + t.url;
        	            	t.type = 'route';
        	            }
                    });
                    
                }
                
                if(item.url === ''){
	            	item._url = 'javascript:';
	            	item.type = 'select';
	            }else{
	            	if(item.url === hash){
                        item.active = 'active';
                    }
	            	item._url = '\#' + item.url;
	            	item.type = 'route';
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

            this.addPanelClass($this);
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
