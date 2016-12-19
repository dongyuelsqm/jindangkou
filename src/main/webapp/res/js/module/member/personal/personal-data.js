/**
 * 个人资料
 * Qichen.Zheng <qichen.zheng@pactera.com>
 * cailuwei<cailuwei@chinamobile.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        util = require('util/util'),
        modalView= require('util/modal'),
        form = require('util/form');
    
    var CheckboxView = form.CheckboxView;
    
    require('jquery-validate');
    require('jquery-validate-add');
    
    var urls = {
	        sendSMS: './member/personal/personal-data!sendPhoneRandomCode.action',
	        sendEmail: './member/personal/personal-data!sendEmailRandomCode.action',
	        
	        username: './member/personal/personal-data!modifyUserName.action',
    		email: './member/personal/personal-data!modifyUserEmail.action',
    		mobile: './member/personal/personal-data!modifyUserMobile.action'
	    },
	    verifyTypes = {
    		username: '修改用户名',
    		email: '绑定邮箱',
    		mobile: '修改手机号码'
    	},
    	options = {
    		username: {
    			rules: {
    				userName: {
    					required: true,
    					rangelength:[4, 16],
    					username: true
//                      remote: {
//                          url: './register!checkUserName.action'
//                      }
    				}
	  			},
	  			messages: {
	  				userName: {
	                	required: '请输入用户名',
	                	rangelength: '请输入4~16位以内字符',
	                	username: '只能包含英文符号、数字以及符号”@“、”_“、”-“'
	//                      remote: '用户名已经存在'
	                }
	  			},
	  			errorPlacement: function(error, element) {
	  				error.appendTo(element.parent().parent());
	  				error.addClass('col-md-9 col-md-offset-3');
	  			},
	  			wrapper: 'div'
    		},
    		email: {
    			rules: {
    				userEmail: {
    					required: true,
    					email: true
    				},
    				emailRandomCode: {
    					required: true
    				}
	  			},
	  			messages: {
	  				userEmail: {
    					required: '请输入电子邮箱',
    					email: '请输入正确格式电子邮箱'
    				},
    				emailRandomCode: {
    					required: '请输入验证码'
    				}
	  			},
	  			errorPlacement: function(error, element) {
	  				error.appendTo(element.parent().parent());
	  				error.addClass('col-md-9 col-md-offset-3');
	  			},
	  			wrapper: 'div'
    		},
    		mobile: {
    			rules: {
//    				oldMobile: {
//    					required: true,
//    					mobile: true,
//    					rangelength:[11, 11]
//    				},
    				oldMobileVerification: {
    					required: true
    				},
    				newMobile: {
    					required: true,
    					rangelength:[11, 11],
    					mobile: true
    				},
    				newMobileVerification: {
    					required: true
    				}
	  			},
	  			messages: {
//	  				oldMobile: {
//    					required: '请输入原手机号码',
//    					mobile: '请输入以13、14、15、17、18开头的电话号码',
//    					rangelength: '请输入11位的手机号码'
//    				},
    				oldMobileVerification: {
    					required: '请输入手机验证码'
    				},
    				newMobile: {
    					required: '请输入新手机号码',
    					rangelength: '请输入11位的手机号码',
    					mobile: '请输入以13、14、15、17、18开头的电话号码'
    				},
    				newMobileVerification: {
    					required: '请输入手机验证码'
    				}
	  			},
	  			errorPlacement: function(error, element) {
	  				error.appendTo(element.parent().parent());
	  				error.addClass('col-md-9 col-md-offset-3');
	  			},
	  			wrapper: 'div'
    		}
    	};
    
    /**
     * form表单
     */
    var formView = Backbone.View.extend({
     	tagName: 'form',
     	className: 'form psrsonal-container',
     	events: {
     		'click #btn-oldSmsCode': 'sendValidSMS',
            'click #btn-newSmsCode': 'sendValidSMS',
            'click #btn-emailCode': 'sendValidEmail',
     	},
     	initialize: function(options){
     		this.verify_type = options.context.verify_type || '';
     		this.template = options.template || {};
     		this.id = options.id || '';
     		this.userName = G.userName;
     		this.originText = '发送验证码';
     		this.render();
        },
        render: function(){      	
        	this.$el.html(this.template());
        	this.validator = this.$el.validate(options[this.verify_type]);
            return this;
        },
        sendValidSMS: function (ev) {
            var _this = this;
            var mobileNumber, 
            	$this = $(ev.currentTarget),
            	flag = true,
            	type = '';
            if ($this.hasClass('disabled')) { return; }
            if ($this.attr('id') === 'btn-newSmsCode') {
            	flag = _this.validator.element(this.$('input[name=newMobile]'));
            	mobileNumber = this.$('input[name=newMobile]').val();
            	type = 'new';
            }
            else {
                mobileNumber = this.$('input[name=oldMobile]').val();
                type = 'old';
            }
            
            if(flag){            	
	            $.ajax({
	                url: urls.sendSMS,
	                data: { 
//	                	userName: _this.userName,
	                	userMobile: mobileNumber,
	                	type: type
	                },
	                dataType: 'json',
	                type: 'post',
	                beforeSend: function(){
	                	$this.prop('disabled', true);
	                	$this.addClass('disabled');
                    },
	                success: function (rsp) {
	                    if (rsp.successSign) {
	                        _this.reSendCountdown($this);
	                    }
	                    else {
	                        alert(rsp.errorMessage || '发生错误，操作失败', function(){
	                        	$this.removeClass('disabled').text(_this.originText).prop('disabled', false);
	                        });
	                    }
	                },
	                error: function (xhr, status, err) {
	                    alert('发生错误，请稍后再试', function(){
	                    	$this.removeClass('disabled').text(_this.originText).prop('disabled', false);
	                    });
	                } 
	            });
            }
        },
        sendValidEmail: function (ev) {
            var _this = this;
            var $this = $(ev.currentTarget);
            if ($this.hasClass('disabled')) { 
            	return;
            }
            if(_this.validator.element(this.$('input[name=userEmail]'))){
            	var email = this.$('input[name=userEmail]').val();
	            $.ajax({
	                url: urls.sendEmail,
	                data: { 
//	                	userName: _this.userName,
	                	userEmail: email 
	                },
	                beforeSend: function(){
	                	$this.prop('disabled', true);
	                	$this.addClass('disabled');
                    },
//                    complete: function(){
//                        $this.prop('disabled', false);
//                    },
	                dataType: 'json',
	                type: 'post',
	                success: function (rsp) {
	                    if (rsp.successSign) {
	                        _this.reSendCountdown($this);
	                    }
	                    else {
	                        alert(rsp.errorMessage || '发生错误，操作失败', function(){
	                        	$this.removeClass('disabled').text(_this.originText).prop('disabled', false);
	                        });
	                    }
	                },
	                error: function (xhr, status, err) {
	                    alert('发生错误，请稍后再试', function(){
	                    	$this.removeClass('disabled').text(_this.originText).prop('disabled', false);
	                    });
	                } 
	            });
            }
        },
        reSendCountdown: function (element) {
            var count = 60,
            	_this = this;
            var key = setInterval(function () {
                if (count > 0) {
                    element.text('已发送（' + count + '）');
                    count--;
                }else {
                    clearInterval(key);
                    element.removeClass('disabled').text(_this.originText).prop('disabled', false);
                }
            }, 1000);
        }
    });
    
    /**
     * modal 模态框
     */
    var modal = modalView.extend({
    	events:{
    		'click #btn-submit': 'submit'
    	},
    	submit: function(ev){
    		var $this = $(ev.currentTarget);
    		
    		if(this.form.$el.valid()){
	            var param = this.form.$el.serialize(),
	            	verify_type = this.form.verify_type;
	            
	            $.ajax({
	                url: urls[verify_type],
	                data: param,
	                type: 'post',
	                dataType: 'json',
	                beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
	                success: function(rsp){
	                	if(rsp.successSign){
	                		alert('修改成功！', function(){
	                			location.reload();
	                		});
	                	}else{
	                		alert(rsp.errorMessage || '操作失败');
	                	}
	                },
	                error: function (xhr, status, err) {
	                    alert('发生错误，请稍后再试');
	                } 
	            });
    		}
    	}
    });
    
    var PersonalDataView = Backbone.View.extend({
        events: {
            'click .modify': 'modify',
            'click .radioLabel': 'changeAccoutType'
        },
        initialize: function (options) {
        	_.each(this.$('input[type=radio]'), function(item, index){
        		form.radiobox($(item));
        	});
        },
        modify: function (e) {
            var type = $(e.currentTarget).data('type');
            this.form = new formView({
        		template: util.template('verify-' + type),
        		id: type + '-form',
        		context: {
        			verify_type: type
        		}
        	});

            this.modal = new modal({
            	id: 'personal-data-modal',
        		context: {
	        		template: 'personalDataModal',
	        		contentTmpl: this.form.$el
        		},
        		title: verifyTypes[type]
        	});
            this.modal.bind(this.form);
        },
        changeAccoutType: function(e){
        	var $this = $(e.currentTarget),
        		val = $this.prev().val(),
        		msg = '查看认证申请信息';
        	
        	if(!this.$('.account-type-1').prop('checked')){
        		return false;
        	}
        	
        	if(val === '2'){
        		var dialog = alert(G.userStatus === '2' ? msg : '如果您想成为个人开发者需要实名认证', function(){
        			location.href = '#member/personal/identity-verification.action';
        		}, {value: G.userStatus === '2' ? '查看' : '去认证'});
        	}else if(val === '3'){
        		var dialog = alert(G.userStatus === '2' ? msg : '如果您想成为公司开发者需要实名认证', function(){
        			location.href = '#member/personal/identity-verification.action';
        		}, {value: G.userStatus === '2' ? '查看' : '去认证'});
        	}
        }
    });
    
    function run () {
        //TODO
        new PersonalDataView({
            el: '#content'
        });
	}

    module.exports = {
        run: run
    };
});