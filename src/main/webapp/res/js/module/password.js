/**
 * 金档口 - 找回密码
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form'),
        util = require('util/util');
    
    var CheckboxView = form.CheckboxView;
    
    require('jquery-validate');
    require('jquery-validate-add');
    require('jquery-md5');
    var RSA = require('rsa');
    
    var template = util.template;
    
    var confirm_opt = {
			rules: {
                userName: {
                    required: true
                },
                verifyCode: {
                    required: true
                }
            },
            messages: {
                userName: {
                    required: '请输入您的用户名'
                },
                verifyCode: {
                    required: '请输入验证码'
                }
			},
			errorPlacement: function(error, element) {
				error.addClass('col-sm-offset-4 col-sm-8');
//                if(!element.hasClass('verify-code')){
//                    error.addClass('error-abs');
//                }
                error.appendTo(element.parent().parent());
			},
			wrapper: 'div'
		},
        safe_opt = {
			rules: {
//				userMobile: {
//					required: true,
//					rangelength: [11, 11],
//					phone: true
//				},
//                mobileCode: {
//                    required: true,
//                },
//                userEmail: {
//					required: true,
//					email: true
//				},
				randomCode: {
                    required: true,
                }
			},
			messages: {
//				userMobile: {
//					required: '请输入手机号码',
//					rangelength: '手机号码为11位数字',
//					phone: '请输入以13、14、15、17、18开头的电话号码'
//				},
//				mobileCode: {
//                    required: '请输入手机验证码',
//                },
//                userEmail: {
//					required: '请输入邮箱',
//					email: '请输入正确格式的邮箱'
//				},
				randomCode: {
                    required: '请输入验证码',
                }
			},
			errorPlacement: function(error, element) {
				error.addClass('col-sm-offset-4 col-sm-8');
				error.appendTo(element.parent().parent());
			},
			wrapper: 'div'
		},
        update_opt = {
			rules: {
				newPassword: {
					required: true,
					rangelength: [6, 16],
					pwd: true
				},
				confirmPassword: {
					equalTo: '#newPassword'
				}
			},
			messages: {
				newPassword: {
					required: '请输入密码',
					rangelength: '密码必须为6-16个字符',
					pwd: '密码至少包含字母、数字和符号其中的两种'
				},
				confirmPassword: {
					equalTo: '两次输入密码不一致'
				}
			},
			errorPlacement: function(error, element) {
				error.addClass('col-sm-offset-4 col-sm-8');
				error.appendTo(element.parent().parent());
			},
			wrapper: 'div'
		};
    
    /**
    * 确认账号
    */
    var confirmAccountView = Backbone.View.extend({
        events: {
            'click .btn-submit': 'next',
            'click .verifyImg-change': 'verifyImgChange',
            'keydown .form-control': 'stepByOne'
        },
        initialize: function(){
            this.$el.validate(confirm_opt);
        },
        next: function(e){
            e.preventDefault();
            var _this = this,
                $this = $(e.currentTarget); 
            
            if(this.$el.valid()){
//                var param = _this.$el.serializeArray();
                var userName = $.trim(_this.$('#userName').val()),
                    verifyCode = $.trim(_this.$('#verifyCode').val());
                
                $.ajax({
                    url: './password!checkUserName.action',
                    data:{
                        userName: userName,
                        verifyCode: verifyCode                  
                    },
                    beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
                    success: function(rsp){
                        console.log(rsp);
                        if(rsp.successSign){
                        	var user = rsp.user.userName || userName;
                            location.href = '#password!verify.action?userName=' + user;
                        }else if(!rsp.successSign && rsp.errorMessage){
                            alert(rsp.errorMessage, function(){
                                _this.verifyImg();
                            });
                        }
                    },
                    error: function(xhr){
                    	alert('发生错误，请稍后再试');
                    }
                });
            }
        },
        verifyImg: function(){
            this.$('#verifyImg').attr('src', './imgBuilder?t=' + new Date());
        },
        verifyImgChange: function(e){
            e.preventDefault();
            this.verifyImg();
        },
        stepByOne: function(e){
//            e.preventDefault();
            var $this = $(e.currentTarget);
            if (e.which == 13) {
                e.preventDefault();
                if ($this.attr('id') == 'userName') {
                    this.$('#verifyCode').focus();
                }else { 
                    this.$('.btn-submit').trigger('click');
                }
            }
        }
    });
    
    /**
    * 验证身份
    */
    var safeVerifyView = Backbone.View.extend({
        events: {
            'change input[type=radio]': 'selectValide',
            'click .btn-valideCode': 'getValideCode',
            'click .btn-submit': 'next',
            'click .btn-back': 'back',
            'keydown .form-control': 'stepByOne'
        },
        initialize: function(){
            this.$radioboxs = this.$('.radiobox');
            this.$info = this.$('#info-wrapper');
//            this.mobileView = template('mobileView');
//            this.emailView = template('emailView');
            
            this.userName = G.userName;
            
            _.each(this.$radioboxs, function(item, index){
                form.radiobox($(item));
            });
            
            this.originText = '发送验证码';
            this.id = 'userMobile';
            this.render();
        },
        selectValide: function(e){
            var $this = $(e.currentTarget);
            
            this.key = $this.attr('data-key');
            this.id = this.key === '2' ? 'userEmail' : 'userMobile';
            this.render();
        },
        valide: function(){
            this.$el.validate(safe_opt);
        },
        setStar: function(){
        	var $msg = this.$('#' + this.id).val(),
        		$p = this.$('#' + this.id).prev(),
        		str = '';
        	
        	if(this.key === '2'){
        		str = $msg.substr(0, 2) + '****@' + $msg.split('@')[1];
            	$p.html(str);
            }else{
            	str = $msg.substr(0, 4) + '****' + $msg.substr(7);
            	$p.html(str);
            }
        },
        render: function(){
        	this.$info.empty();
        	
        	var tmpl = template(this.id + 'View');
        	this.$info.html(tmpl());
        	this.setStar();
            this.valide();
        },
        getValideCode: function(e){
            e.preventDefault();
            var _this = this,
                $this = $(e.currentTarget);
            
//            if(this.validator.element($('#' + this.id))){
                var param = {};
                param['userName'] = this.userName;
                param[this.id] = $.trim($('#' + this.id).val());
                
                $.ajax({
                    url: './password!sendRandomCode.action',
                    data: param,
                    beforeSend: function(){
                        $this.prop('disabled', true);
                        $this.addClass('disabled');
                    },
                    complete: function(){
//                        $this.prop('disabled', false);
                    },
                    success: function(rsp){
                        console.log(rsp);
                        if(rsp.successSign){
                            _this.countdown($this);
                        }else{
                            alert(rsp.errorMessage || '发生错误，操作失败', function(){
                            	$this.html(_this.originText);
                                $this.removeClass('disabled');
                                $this.prop('disabled', false);
                            });
                        }
                    },
                    error: function(xhr){
                    	alert('发生错误，请稍后再试', function(){
	                    	$this.removeClass('disabled').text(_this.originText).prop('disabled', false);
	                    });
                    }
                });
//            }
        },
        countdown: function($this){
        	var _this = this;
        	$this.prop('disabled', true);
            $this.html('验证码已发送(15)').addClass('disabled');
            
            var t = 14,
                timer = setInterval(function(){
                    if(t < 0){
                        $this.html(_this.originText)
                        $this.removeClass('disabled');
                        $this.prop('disabled', false);
                        clearInterval(timer);
                    }else{
	                    $this.html('验证码已发送('+t+')');
	                    t--;
                    }
                }, 1000);
        },
        back: function(e){
        	e.preventDefault();
//            location.href = '#password!confirmAccount.action';
        	history.back();
        },
        next: function(e){
            e.preventDefault();
            var _this = this,
                $this = $(e.currentTarget); 
            
            if(this.$el.valid()){
                var randomCode = $.trim(_this.$('.randomCode').val());
                
                $.ajax({
                    url: './password!verifyCode.action',
                    data: {
                        userName: _this.userName,
                        randomCode: randomCode
                    },
                    beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
                    success: function(rsp){
                        console.log(rsp);
                        if(rsp.successSign){
                            location.href = '#password!resetPassword.action';
                        }else if(!rsp.successSign && rsp.errorMessage){
                            alert(rsp.errorMessage);
                        }
                    },
                    error: function(xhr){
                    	alert('发生错误，请稍后再试');
                    }
                });
            }
        },
        stepByOne: function(e){
//            e.preventDefault();
            var $this = $(e.currentTarget);
            if (e.which == 13) {
                e.preventDefault();
                this.$('.btn-submit').trigger('click');
            }
        },
    });
    
    /**
    * 重置密码
    */
    var resetPasswordView = Backbone.View.extend({
        events: {
            'click .btn-submit': 'submit',
            'click .btn-back': 'back',
            'keydown .form-control': 'stepByOne'
        },
        initialize: function(){
            this.$el.validate(update_opt);
        },
        back: function(e){
        	e.preventDefault();
//            location.href = '#password!verify.action?userName=' + userName;
        	history.back();
        },
        submit: function(e){
            e.preventDefault();
            var _this = this,
                $this = $(e.currentTarget); 
            
            if(this.$el.valid()){
//                var param = _this.$el.serializeArray();
                var newPassword = _this.encryptPassword($.trim(_this.$('#newPassword').val())),
                    confirmPassword = _this.encryptPassword($.trim(_this.$('#confirmPassword').val()));
                
                $.ajax({
                    url: './password!savePassword.action',
                    data: {
                        newPassword: newPassword,
                        confirmPassword: confirmPassword
                    },
                    beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
                    success: function(rsp){
                        console.log(rsp);
                        var msg = '密码修改成功，<span class="count-down-time">5s</span>后重新登录';
                            alert(msg, function(){
                                location.href = '#login.action';
                            });
                            _this.$countDown = $('.count-down-time');
                            _this.countdown();
                    },
                    error: function(xhr){
                    	alert('发生错误，请稍后再试');
                    }
                });
            }
        },
        encryptPassword: function(password){
        	try{
        		var pk = RSA.getKeyPair();
        		password = RSA.encryptedString(pk, password);
        	}catch(e){
        		console.log(e);
        	}
        },
        stepByOne: function(e){
//            e.preventDefault();
            var $this = $(e.currentTarget);
            if (e.which == 13) {
                e.preventDefault();
                if ($this.attr('id') == 'newPassword') {
                    this.$('#confirmPassword').focus();
                } else { 
                    this.$('.btn-submit').trigger('click');
                }
            }
        },
        countdown: function(){
            var t = 4,
                _this = this,
                timer = setInterval(function(){
                    if(t < 1){
                        clearInterval(timer);
                        location.href = '#login.action';
                        return;
                    }
                    _this.$countDown.html(t+'s');
                    t--;
                }, 1000);
        }
    });
    return {
        confirmAccountView: confirmAccountView,
        safeVerifyView: safeVerifyView,
        resetPasswordView: resetPasswordView
    };
});