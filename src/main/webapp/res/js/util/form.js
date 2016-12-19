/**
 * form 控件
 * cailuwei<cailuwei@chinamobile.com>
 */
'use strict';
define(function(require, exports, module){
	var $ = require('jquery'),
	    _ = require('underscore'),
	    Backbone = require('backbone');
	
	/**
	 * 模拟checkbox 
	 * box 不可以用label包裹
	 */
	var CheckboxView = Backbone.View.extend({
		events: {
			'click .checkLabel': 'changeCkb'
		},
		initialize: function(options){
			this.model = new Backbone.Model();
			
			this.$box = this.$('input[type="checkbox"]');
			this.uncheck = false;
			this.check = true;
			this.checked = false;
			this.render();
			this.listenTo(this.model, 'change', this.changeCkbStatus);
		},
		'changeCkb': function(ev){
			var $this = $(ev.currentTarget);
			if(this.$box.prop('readonly') || this.$box.prop('disabled')){
            	return false;
            }

//			this.model.set({'checked': checked});
			this.model.trigger('change', this.$box.prop('checked') ? 'uncheck' : 'check');
		},
		changeCkbStatus: function(_checked, fromParent){
			this.checked = _checked === 'check' ? this.check : this.uncheck;
			if(this.checked){
				this.$box.prop('checked', true);
				this.$label.addClass('checked');
			}else{
				this.$box.prop('checked', false);
				this.$label.removeClass('checked');
			}
			
			if(!fromParent){
				this.$box.trigger('change');
			}
		},
		render: function(){
			this.$box.hide();
			if(!this.$box.next().hasClass('checkLabel')){
				this.$box.after('<label class="checkLabel"></label>');
			}
			
			this.$label = this.$('.checkLabel');
			if(this.$box.hasClass('checkbox-all')){
				this.$label.addClass('checkLabel-all');
			}
			
			if(this.$box.prop('checked')){
				this.$label.addClass('checked');
			}else{
				this.$label.removeClass('checked');
			}
			
			return this;
		},
		getStatus: function(){
			return this.$('.checkbox').prop('checked');
		}
	});
    
    /**
	 * 模拟radio 
	 * box 不可以用label包裹
	 */
	function radiobox(box){
        var label = $('<label class="radioLabel"></label>');
		box.prop('checked') ? label.addClass('selected') : label.removeClass('selected');
        var name = box.attr('name');
		if(!box.next().hasClass('radioLabel')){
			label.insertAfter(box);
		}
		box.hide().on({
			'select': function() {
                $('input[name=' + name + ']').next().removeClass('selected');
                label.addClass('selected');
				box.prop('checked', true);
			}
		});
        
		label.on('click.base', function() {
			var $this = $(this);
            if($this.hasClass('selected')){
                return;
            }else if(box.prop('readonly') || box.prop('disabled')){
            	return;
            }else {
                box.triggerHandler('select');
                box.trigger('change');
            }
		});
	}
	
	/**
	 * 模拟select
	 */
	function select(box){
		var $p_box = box.parent(),
			$arrow = $p_box.find('.select-arrow');
		$arrow.on('click', function(){
			box.show();
			return false;
		});		
		box.on('click.base', 'li', function(){
			var $this = $(this),
				value = $this.attr('data-value'),
				key = $this.html();
			$p_box.find('input[type=hidden]').val(value);
			$p_box.find('input[type=text]').val(key);
			box.hide();
		});
		$('body').on('click', function(){
			box.hide();
		});
	}
	
	return {
		select: select,
		CheckboxView: CheckboxView,
        radiobox: radiobox
	}
});