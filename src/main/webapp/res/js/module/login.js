/**
 * 金档口 - 登录
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
    
    require('jquery-md5');
    var RSA = require('rsa');
    
    var formView = Backbone.View.extend({
        events: {
            'click .btn-login': '_login',
            'click .verifyImg-change': 'verifyImgChange',
            'keydown .form-control': 'stepByOne'
        },
        initialize: function(options){
            new CheckboxView({
            	el: this.$('input[type=checkbox]').parent()
            });
            
            this.cacheEls();
            
            this.isRemember = util.getCookie('isRemember');
            if (this.isRemember && this.isRemember.toLowerCase() === 'true') {
                this.username = util.getCookie('userName');
                this.$isRemember.trigger('check');
                this._login();
            }
        },
        cacheEls: function(){
        	this.$isRemember = this.$('#isRemember');
            this.$userName = this.$('#userName');
            this.$password = this.$('#userPassword');
            this.$verifyCode = this.$('#verifyCode');
            this.$error_msg = this.$('.error-msg');
            this.$error = this.$error_msg.parent();
        },
        valid: function(){
        	
        	this.userName = $.trim(this.$userName.val());
            this.password = $.trim(this.$password.val());
            this.verifyCode = $.trim(this.$verifyCode.val());

            if (this.userName == '') {
                this.$error_msg.html('请输入您的用户名/邮箱/手机号码');
                this.$error.show();
                return false;
            } else if (this.password == '') {
                this.$error_msg.html('请输入您的密码');
                this.$error.show();
                return false;
            } else if (this.verifyCode == '') {
                this.$error_msg.html('请输入验证码');
                this.$error.show();
                return false;
            }
            return true;
        },
        _login: function(ev){
        	
        	var _this = this,
        		$this = {};
        	
        	if(ev){
        		ev.preventDefault();
                var $this = $(ev.currentTarget);
                _this.$btn = $this;
        	}else{
        		_this.$btn = this.$('.btn-login');
        	}
            if(!ev || _this.valid()) {

                $.ajax({
                    url: './login!isRepeatLogin.action',
                    data: {
                        'userName': _this.userName
                    },
                    beforeSend: function () {
                        $this.prop('disabled', true);
                    },
                    complete: function () {
                        //$this.prop('disabled', false);
                    },
                    success: function (rsp) {
                        if (rsp.successSign) {
                            // true 表示重复登录
                            alert('该账号已在其他设备上登录，确认在此设备上登录吗？', function(){
                                _this.login();
                            }, function(){
                            	$this.prop('disabled', false);
                            });
                        } else {
                            // false 表示没有重复登录
                            _this.login();
                        }
                    },
                    error: function (e) {
                        $this.prop('disabled', false);
                        alert('操作失败，请稍后重试！');
                    }
                });
            }
        },
        login: function(){
            var _this = this;
            _this.password = _this.old_pwd === _this.password ? _this.password : _this.encryptPassword(_this.password);

            $.ajax({
                url: './login!login.action',
                data: {
                    'userName': _this.userName,
                    'userPassword': _this.password,
                    'verifyCode': _this.verifyCode,
                    'isRemember': _this.isRemember.val()
                },
                beforeSend: function(){
                    _this.$error.hide();
                    G.$mask_msg.html('正在登录，请稍候...').addClass('loading');
                    G.$mask.show();
                },
                complete: function(){
                	_this.$btn.prop('disabled', false);
                    var timer = setTimeout(function() {
                        G.$mask.hide();
                        clearTimeout(timer);
                    }, 500);
                },
                success: function(rsp){
                    console.log(rsp);
                    var msg = rsp.successSign ? '登录成功！跳转中...' : '登录失败！';
                    G.$mask_msg.html(msg).removeClass('loading');
                    if (rsp.successSign) {
                        $(window).on('hashchange', function(e){
                            location.reload();
                        });

                        _this.userName = rsp.resultObj[0].userName;
                        _this.rememberme();
                        if(G.returnUrl){
                            location.href = G.returnUrl === 'index.action' ? './index.action' : '#' + G.returnUrl;
                        }else {
                            location.href = './index.action';
                        }

                    } else {
                        if(rsp.errorMessage){
                            alert(rsp.errorMessage);
                            return false;
                        }
                        var timer = setTimeout(function() {
                            _this.$error_msg.html(rsp.loginId || rsp.password || rsp.validCode);
                            _this.$error.show();
                            _this.$verifyCode.val('');
                            _this.verifyImg();
                            clearTimeout(timer);
                        }, 500);
                    }
                },
                error: function(e){
                    G.$mask_msg.html('登录失败，请稍后重试活或联系管理员');
                }
            });
        },
        encryptPassword: function(password){
        	try{
        		var pk = RSA.getKeyPair();
        		password = RSA.encryptedString(pk, password);
        	}catch(e){
        		console.log(e);
        	}
        },
        verifyImgChange: function(e){
            e.preventDefault();
            this.verifyImg();
        },
        verifyImg: function(){
            this.$('#verifyImg').attr('src', './imgBuilder?t=' + new Date());
        },
        rememberme: function() {
            if (this.$isRemember.prop('checked')) {
                var seconds = 7 * 24 * 3600;    //保存一周
                util.setCookie('userName', this.userName, seconds);
                util.setCookie('isRemember', true, seconds);
            }else {
                util.removeCookie('isRemember');
                util.setCookie('userName', this.userName, '');
            }
        },
        stepByOne: function(ev){
//            ev.preventDefault();
            var $this = $(ev.currentTarget);
            if (ev.which == 13) {
                ev.preventDefault();
                if ($this.attr('id') == 'userName') {
                    this.$password.focus();
                } else if ($this.attr('id') == 'userPassword') { 
                    this.$verifyCode.focus();
                } else { 
                    this.$('.btn-submit').trigger('click');
                }
            }
        }
    });
    
    function run(){
        new formView({
            el: '#form-login'
        });
    }
    
    return {
    	run: run
    };
});