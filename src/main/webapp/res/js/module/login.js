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
            'click .btn-login': 'login',
            // 'click .verifyImg-change': 'verifyImgChange',
            'keydown .form-control': 'stepByOne'
        },
        initialize: function(options){
            new CheckboxView({
            	el: this.$('input[type=checkbox]').parent()
            });
            
            this.cacheEls();
            
            // this.isRemember = util.getCookie('isRemember');
            // if (this.isRemember && this.isRemember.toLowerCase() === 'true') {
            //     this.username = util.getCookie('userName');
            //     this.$isRemember.trigger('check');
            //     this._login();
            // }
        },
        cacheEls: function(){
            this.$userName = this.$('#userName');
            this.$password = this.$('#userPassword');
            // this.$isRemember = this.$('#isRemember');
            // this.$verifyCode = this.$('#verifyCode');
            this.$error_msg = this.$('.error-msg');
            this.$error = this.$error_msg.parent();

            this.$mask = $('.mask');
            this.$mask_msg = $('.mask_msg');
        },
        valid: function(){
        	
        	this.userName = $.trim(this.$userName.val());
            this.password = $.trim(this.$password.val());
            // this.verifyCode = $.trim(this.$verifyCode.val());

            if (this.userName == '') {
                this.$error_msg.html('请输入您的用户名/邮箱/手机号码');
                this.$error.show();
                return false;
            } else if (this.password == '') {
                this.$error_msg.html('请输入您的密码');
                this.$error.show();
                return false;
            }
            // } else if (this.verifyCode == '') {
            //     this.$error_msg.html('请输入验证码');
            //     this.$error.show();
            //     return false;
            // }
            return true;
        },
        login: function(){
            var _this = this;
            // _this.password = _this.old_pwd === _this.password ? _this.password : _this.encryptPassword(_this.password);
            if(!_this.valid()) {
                $.ajax({
                    url: G.contextPath + 'website/login',
                    data: {
                        // 'userName': _this.userName,
                        // 'userPassword': _this.password
                        'username': $.trim(this.$userName.val()),
                        'password': $.trim(this.$password.val())
                        // 'verifyCode': _this.verifyCode,
                        // 'isRemember': _this.isRemember.val()
                    },
                    type: 'post',
                    beforeSend: function () {
                        _this.$error.hide();
                        _this.$mask_msg.html('正在登录，请稍候...').addClass('loading');
                        _this.$mask.show();
                    },
                    complete: function () {
                        _this.$btn.prop('disabled', false);
                        var timer = setTimeout(function () {
                            _this.$mask.hide();
                            clearTimeout(timer);
                        }, 500);
                    },
                    success: function (rsp) {
                        console.log(rsp);
                        var msg = rsp.successSign ? '登录成功！跳转中...' : '登录失败！';
                        G.$mask_msg.html(msg).removeClass('loading');
                        if (rsp.successSign) {
                            $(window).on('hashchange', function (e) {
                                location.reload();
                            });

                            _this.userName = rsp.resultObj[0].userName;
                        } else {
                            if (rsp.errorMessage) {
                                alert(rsp.errorMessage);
                                return false;
                            }
                            var timer = setTimeout(function () {
                                _this.$error_msg.html(rsp.loginId || rsp.password || rsp.validCode);
                                _this.$error.show();
                                clearTimeout(timer);
                            }, 500);
                        }
                    },
                    error: function (e) {
                        _this.$mask_msg.html('登录失败，请稍后重试活或联系管理员');
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
                    // this.$verifyCode.focus();
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