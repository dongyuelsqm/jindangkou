/**
 * 密码修改
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        util = require('util/util');
       
    require('jquery-validate');
    require('jquery-validate-add');
    require('jquery-md5');
    var RSA = require('rsa');
    
    var validate_option = {
		rules: {
			pwd: {
				required: true,
				rangelength: [6, 16],
				pwd: true
			},
			pwdnew: {
				required: true,
				rangelength: [6, 16],
				pwd: true
			},
			pwdconfirm: {
		        equalTo: '#pwdnew'
			}
		},
		messages: {
			pwd: {
				required: '请输入原始密码',
				rangelength: '密码必须为6-16个字符',
				pwd: '密码至少包含字母、数字和符号其中的两种'
			},
			pwdnew: {
				required: '请输入新密码',
				rangelength: '密码必须为6-16个字符',
				pwd: '密码至少包含字母、数字和符号其中的两种'
			},
			pwdconfirm: {
		        equalTo: '两次输入密码不一致'
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().parent());
			error.addClass('col-md-10 col-md-offset-2');
		},
		wrapper: 'div'
	},
	urls = {
        submit: './member/personal/password!updatePassword.action'
    };

    var formView = Backbone.View.extend({
        events: {
            'click .btn-submit': 'submit'
        },
        initialize: function(options){
        	this.$el.validate(validate_option);
        },
        submit: function(e){
        	var _this = this,
        		$this = $(e.currentTarget);
        	
        	if(this.$el.valid()){
        		var pwd = _this.encryptPassword($.trim(_this.$('#pwd').val())),
	                pwdnew = _this.encryptPassword($.trim(_this.$('#pwdnew').val())),
	                pwdconfirm = _this.encryptPassword($.trim(_this.$('#pwdconfirm').val()));
	            $.ajax({
	                url: urls.submit,
	                data: {
	                	pwd: pwd,
		                pwdnew: pwdnew,
		                pwdconfirm: pwdconfirm
	                },
	                dataType: 'json',
	                type: 'post',
	                beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
	                success: function (rsp) {
	                    if (rsp.successSign) {
                            $(window).on('hashchange', function(e){
                        		location.reload();
                        	});
                            
	                    	var msg = '密码修改成功，<span class="count-down-time">5s</span>后重新登录';
                            alert(msg, function(){
                                location.href = '#login.action';
                            });
                            _this.$countDown = $('.count-down-time');
                            _this.countdown();
                            
	                    }else {
	                    	alert(rsp.errorMessage || '发生错误，操作失败');
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
    
    var run = function () {
        new formView({
        	el: '#password-form'
        });
    };

    module.exports = {
        run: run
    };
});