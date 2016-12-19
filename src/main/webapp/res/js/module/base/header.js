/**
 * 头部导航
 * cailuwei<cailuwei@chinamobile.com>
 */
'use strict';
define(function(require, exports, module) {
	var $ = require('jquery'),
	    _ = require('underscore'),
	    Backbone = require('backbone');
	 
    var headerNavView = Backbone.View.extend({
        events: {
            //二级菜单显示
            'mouseover .navs>li':         '_showSubMenu',
            'mouseover .subnavs-wrapper': 'showSubMenu',
            'mouseout .navs>li':          'hideSubMenu',
            'mouseout .subnavs-wrapper':  'hideSubMenu',
            'click .navs>li>a':           'addNavCurrentClass',
//            'click .nav-link>li>a':       'addNavCurrentClass',
            'click .subnavs>li>a':        'addSubnavCurrentClass'
       },
       initialize: function(){
            this.$user = this.$('#user-name');
            if(this.$user.length > 0 && this.$user.html() !== ''){
                this.getMessageCount();
            }
            
            this.$subnavs_wrapper = this.$('.subnavs-wrapper');
            this.$subnavs = this.$('.subnavs');
            this.$navs = this.$('.navs');
        },
	    _showSubMenu: function(ev){
            var $this = $(ev.currentTarget),
            	i = $this.attr('id').substr(4),
                $ul = this.$('#subnavs-' + i);
	        if($ul.length < 1){
                return;
	    	}
	        
	        this.i = i;
	        this.$navs.find('.active-arrow').remove();
	        this.$navs.find('#nav-' + this.i).append('<i class="active-arrow"></i>');
	        
	        this.$subnavs_wrapper.find('.subnavs').hide();
            $ul.show();
            this.showSubMenu();
	    },
	    showSubMenu: function(ev){
	    	var path = location.hash;
	    	if(path.indexOf('#product') > -1){
	    		return;
	    	}
	    	this.$navs.find('.active-arrow').show();
	    	this.$navs.find('#nav-' + this.i).addClass('active');
	    	this.$subnavs_wrapper.show();
	    },
	    hideSubMenu: function(ev){
	    	var path = location.hash;
	    	if(path.indexOf('#product') > -1){
	    		return;
	    	}
	    	this.$navs.find('.active-arrow').hide();
	    	this.$navs.find('#nav-' + this.i).removeClass('active');
	    	this.$subnavs_wrapper.hide();
	    },
        addNavCurrentClass: function(ev){
            ev.preventDefault();
	        var $this = $(ev.currentTarget),
                id = $this.attr('id');
	        this.href = $this.data('href');
	        if(id === 'logout'){
                this.logout();
                return false;
	        }else if(id === 'login'){
	        	this.login();
	        	return false;
	        }else if(this.href === 'waiting'){
	        	alert('功能还在开发中，敬请期待...');
                return false;
	        }else if(this.href === ''){  
                return false;
	        }
	        
            this.route();
        },
	    addSubnavCurrentClass: function(ev){
            ev.preventDefault();
            var $this = $(ev.currentTarget),
                $li = $this.parent(),
	        	i = this.$subnavs.attr('id').substr(7);
	        this.href = $this.data('href');
	        
            this.route();
        },
        addCurrentClass: function(path){
        	this.clearClass();
        	this.path = path.split('#')[1];
    		
        	if(path.indexOf('#product') > -1 && path.indexOf('returnUrl') < 0){
        		this.$subnavs_wrapper.show();
	    	}else{
	    		this.$subnavs_wrapper.hide();
	    	}
        	
        	if(this.path.indexOf('notice/notice.action') > -1 || this.path.indexOf('help/help.action') > -1 || this.path.indexOf('contacts.action') > -1){
        		
        		var n = this.path.split('.action')[0].split('/')[1];
        		$('.nav-link').find('.link-' + n).parent().addClass('active');
        		return false;
        	}
        	this.$a = null;
        	this.walklist(this.$('a'));
        	
        	if(this.$a){
        		var i = this.current_id.substr(8);
            
        		this.$a.parent().addClass('active');	        	
		        this.$navs.find('#nav-' + i).addClass('active');
		        this.$navs.find('#nav-' + i).append('<i class="active-arrow"></i>');
        	}else{
        		if(this.current_id){
        			var i = this.current_id.substr(4);
    		        this.$navs.find('#nav-' + i).addClass('active'); 
        		}
        	}
        },
        clearClass: function(){
	        this.$navs.find('.active-arrow').remove();
	        this.$navs.find('.active').removeClass('active');
    		this.$subnavs.find('.active').removeClass('active');
    		$('.nav-link').find('.active').removeClass('active');
        },
        walklist: function(navs){
        	
        	var _this = this;
        	_.each(navs, function(item, index){
        		var a = $(item).data('href').split('.')[0].split('-')[0],
        			b = _this.path.split('?')[0].split('.')[0].split('-')[0];
        		if(a === b){
        			if($(item).parent().parent().hasClass('subnavs')){
        				_this.current_id = $(item).parent().parent().attr('id');
        				_this.$a = $(item);
        			}else{
        				_this.current_id = $(item).parent().attr('id');

        			}        			
        		}
        	});
        	
        },
        getMessageCount: function(){
            var _this = this;
            $.ajax({
                url: './member/personal-center!getMessageCount.action',
                type: 'post',
                dataType: 'json',
                success: function(rsp){
                    var count = (rsp.messageCount && rsp.messageCount > 0) ? rsp.messageCount : '';
                    _this.$('#message-count').html(count);
                    var t = setTimeout(function() {
                        _this.getMessageCount();
                        clearTimeout(t);
                    }, 60 * 1000);
//                    G.timers.push(t);
                }
            });
        },
        logout: function(ev){
            $.ajax({
                url: './login!logout.action',
                type: 'post',
                dataType: 'json',
                success: function(rsp){
                    if(rsp.successSign){
                    	location.reload();
                    }else if(!rsp.successSign && rsp.errorMessage){
                        alert(rsp.errorMessage);
                    }
                },
                error: function(e){
                    console.log(e);
                }
            });
        },
        login: function(ev){
        	if(location.hash.indexOf('#login') > -1){
        		return false;
        	}
        	
        	var u = location.hash.indexOf('#repeat-login') > -1 || 
        			location.hash.indexOf('#member') > -1 || 
        			location.hash.indexOf('#register') > -1 ||
        			location.hash.indexOf('#password') > -1 ? '' : location.hash.split('#')[1],
        		url = u ? u : '';
            location.href = '#' + this.href + '?returnUrl=' + url;
        },
        route: function(){
            location.href = '#' + this.href;
        }
    });
	
	var header = new headerNavView({
        el: '#header'
    });
	
	return header;
});