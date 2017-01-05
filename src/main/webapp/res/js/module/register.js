/**
 * 金档口 - 注册
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form'),
        util = require('util/util'),
        modalView = require('util/modal');
    
    var CheckboxView = form.CheckboxView;
    
    require('jquery-validate');
    require('jquery-validate-add');
    require('jquery-md5');
    var RSA = require('rsa');
    
    var template = util.template;
    
    var urls = {
    		checkUserMobile: './register!phoneValidate.action',
    		randomCodeUrl: './register!sendPhoneRandomCode.action',
    		registerUrl: './register!register.action'
    	},
    	remoteValid = false;
    
    var validate_opt = {
			rules: {
//                userName: {
//                    required: true,
////                    username: true,
////                    remote: {
////                        url: './register!checkUserName.action'
////                    }
//                },
				userMobile: {
					required: true,
					rangelength: [11, 11],
					mobile: true,
					remote: {
						url: urls.checkUserMobile,
						beforeSend: function(xhr){
							remoteValid = false;
						},
						complete: function(xhr, textStatus){
							remoteValid = textStatus === 'success' ? true : false;
						},
						error: function(xhr){
							alert('操作失败，请稍后重试！');
						}
					}
				},
                mobileCode: {
                    required: true
                },
				userPassword: {
					required: true,
					rangelength: [6, 16],
                	pwd: true
				},
				userPasswordConfirm: {
					equalTo: '#userPassword'
				},
                verifyCode: {
                    required: true
                }
			},
			messages: {
//                userName: {
//                    required: '请输入用户名',
////                    username: '请输入正确格式用户名',
////                    remote: '用户名已经存在'
//                },
				userMobile: {
					required: '请输入手机号码',
					rangelength: '手机号码为11位数字',
					mobile: '请输入以13、14、15、17、18开头的电话号码',
					remote: '当前手机号已经存在'
				},
                mobileCode: {
                    required: '请输入手机验证码'
                },
				userPassword: {
					required: '请输入密码',
					rangelength: '密码必须为6-16个字符',
					pwd: '密码至少包含字母、数字和符号其中的两种'
				},
				userPasswordConfirm: {
					equalTo: '两次输入密码不一致'
				},
                verifyCode: {
                    required: '请输入验证码'
                }
			},
			errorPlacement: function(error, element) {
				error.addClass('col-sm-offset-4 col-sm-8');
				error.appendTo(element.parent().parent());
			},
			wrapper: 'div'
		};
    
    /**
     * 注册表单
     */
    var formView = Backbone.View.extend({
        events: {
            'click .btn-register': 'register',
            'click .btn-mobileCode': 'getMobileCode',
            'click .verifyImg-change': 'verifyImgChange'
//            'click .show-protocol': 'showProtocol'
        },
        initialize: function(options){
            new CheckboxView({
            	el: this.$('input[type=checkbox]').parent()
            });
            
            this.validator  = this.$el.validate(validate_opt);           
            this.$isRead = this.$('#isRead');
            this.protocol = template('protocol');
        },
        register: function(e){
            e.preventDefault();
            var $this = $(e.currentTarget),
                _this = this;
            
            if(!_this.$isRead.prop('checked')){
                alert('请阅读购买须知');
                return false;
            }
            
            if(_this.$el.valid()){
                var array = _this.$el.serializeArray(),
                    param = {};
                
                _.each(array, function(item, index){
                	if(item.name.indexOf('userPassword') > -1){
                		param[item.name] = _this.encryptPassword($.trim(item.value));
                	}else{
                		param[item.name] = $.trim(item.value);
                	}
                });
                
                
                $.ajax({
                    url: urls.registerUrl,
                    data: param,
                    beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
                    success: function(rsp){
                        console.log(rsp);
                        if(rsp.successSign) {
							var msg = '注册成功，<span class="count-down-time">5s</span>后重新登录';
                            alert(msg, function(){
                                location.href = '#login.action';
                            });
                            
                            _this.$countDown = $('.count-down-time');
                            var t = 4;
                            var timer = setInterval(function(){
                                if(t < 0){
                                    clearInterval(timer);
                                    location.href = '#login.action';
                                    return;
                                }
                                _this.$countDown.html(t+'s');
                                t--;
                            }, 1000);
						} else {
							var msgs = [];
							if(rsp.errorMessage.indexOf('|') < 0) {
								alert(rsp.errorMessage, function(){
                                    _this.verifyImg();
                                });
							} else {
								msgs = rsp.errorMessage.split(',');
                                var r = '';
								for(var i in msgs) {
									var index = msgs[i].split('|');
									r += index[1] + '</br>';
								}
                                alert(r, function(){
                                    _this.verifyImg();
                                });
							}
						}
                    },
                    error: function (xhr, status, err) {
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
        verifyImg: function(){
            this.$('#verifyImg').attr('src', './imgBuilder?t=' + new Date());
        },
        verifyImgChange: function(e){
            e.preventDefault();
            this.verifyImg();
        },
        getMobileCode: function(e){
            e.preventDefault();
            var _this = this,
                $this = $(e.currentTarget),
                userMobile = this.$('#userMobile').val();
            if(this.validator.element($('#userMobile')) && remoteValid){
                $.ajax({
                    url: urls.randomCodeUrl,
                    data: {
                        userMobile: userMobile
                    },
                    beforeSend: function(){
                        $this.prop('disabled', true);
                        $this.addClass('disabled');
                    },
                    complete: function(){
//                        $this.prop('disabled', false);
//                        $this.removeClass('disabled');
                    },
                    success: function(rsp){
                        console.log(rsp);
                        if(rsp.successSign){
                            $this.prop('disabled', true);
                            $this.html('验证码已发送(15)').addClass('disabled');
                            _this.countdown($this);
                        }else{
                        	alert(rsp.errorMessage || '发生错误，操作失败', function(){
                        		$this.prop('disabled', false);
                                $this.removeClass('disabled');
                        	});
                        }
                        
                    },
                    error: function(rsp){
                        console.log(rsp);
                    }
                });
            }
        },
        countdown: function($this){
            var t = 14,
                timer = setInterval(function(){
                    if(t < 0){
                        $this.html('发送验证码');
                        $this.removeClass('disabled');
                        $this.prop('disabled', false);
                        clearInterval(timer);
                        return;
                    }
                    $this.html('验证码已发送('+t+')');
                    t--;
                }, 1000);
        }
    });
    
    function run(){
        new formView({
            el: '#form-register'
        });
    }
    
    return {
    	run: run
    };
});