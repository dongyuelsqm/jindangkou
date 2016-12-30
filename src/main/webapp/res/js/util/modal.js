/**
 * 金档口 - 模态框
 * cailuwei<>
 */
'use strict';
define(function (require, exports, module) {

    var $ = require('jquery'),
        Backbone = require('backbone'),
        _ = require('underscore'),
        util = require('util/util');
    
    var template = util.template;
    
    require('bootstrap-modal');
    require('perfect-scrollbar');
    
    var modalView = Backbone.View.extend({
    	className: 'modal fade',
        initialize: function (options) {
        	options = options || {};
        	
        	var tmpl = options.context.template || '',
        		content = options.context.contentTmpl || '';
        	this.template = template(tmpl);     	
        	this.content = content;
        	
        	this.$el.on('hide.bs.modal', _.bind(this.hideModal, this));
        	this.$el.on('show.bs.modal', _.bind(this.showModal, this));
        	
        	this.render();
        	
        	if(options.title){
        		this.$('#modal-label').html(options.title);
        	}
        	
        	if(options.button){
        		this.$('.modal-footer').html(options.button);
        	}
        },
        hideModal: function(e){
        	this.remove();
        },
        render: function(){
        	
        	this.$el.html(this.template());
        	$('body').append(this.$el);
        	this.$el.modal({
//        		backdrop: false
        	});
        	
        	return this;
        },
        showModal: function(){        	
        	this.$('.modal-body').html(this.content);
        	this.addScroll(); 
        },
        addScroll: function(){
        	this.$('.modal-body').perfectScrollbar({suppressScrollX: true});
        },
        bind: function(otherview){
        	this.form = otherview || {};
        	this.store = otherview.store || {};
        }
        
    });
    
    return modalView;
});