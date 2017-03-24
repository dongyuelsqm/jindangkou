/**
 * 金档口 - 首页
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        Backbone = require('backbone'),
        slider = require('util/slider');

    /**
     * form
     */
    var FormView = Backbone.View.extend({
        events: {
            'click [data-do="login"]': 'login',
            'keydown .form-control': 'stepByOne'
        },
        initialize: function(options){this.cacheEls();
            this.cacheEls();
        },
        cacheEls: function(){
            this.$userName = this.$('#userName');
            this.$password = this.$('#userPassword');
            this.$error_msg = this.$('.error-msg');
            this.$error = this.$error_msg.parent();
        },
        valid: function(){

            this.userName = $.trim(this.$userName.val());
            this.password = $.trim(this.$password.val());

            if (this.userName == '') {
                this.$error_msg.html('请输入您的用户名/邮箱/手机号码');
                this.$error.show();
                return false;
            } else if (this.password == '') {
                this.$error_msg.html('请输入您的密码');
                this.$error.show();
                return false;
            }
            return true;
        },
        login: function(){
            var _this = this;
            // _this.password = _this.old_pwd === _this.password ? _this.password : _this.encryptPassword(_this.password);
            if(_this.valid()) {
                $.ajax({
                    url: G.contextPath + 'website/login',
                    data: {
                        'username': $.trim(this.$userName.val()),
                        'password': $.trim(this.$password.val())
                    },
                    type: 'post',
                    beforeSend: function () {
                        _this.$error.hide();
                    },
                    complete: function () {
                        _this.$btn.prop('disabled', false);
                        var timer = setTimeout(function () {
                            clearTimeout(timer);
                        }, 500);
                    },
                    success: function (rsp) {
                        console.log(rsp);
                        // var msg = rsp.successSign ? '登录成功！跳转中...' : '登录失败！';
                        // G.$mask_msg.html(msg).removeClass('loading');
                        if (rsp.successSign) {
                            alert('登录成功');
                        } else {

                            var timer = setTimeout(function () {
                                _this.$error_msg.html(rsp.detail || '登录失败！');
                                _this.$error.show();
                                clearTimeout(timer);
                            }, 500);
                        }
                    },
                    error: function (e) {
                        _this.$error_msg.html('登录失败，请稍后重试活或联系管理员');
                        _this.$error.show();
                    }
                });
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

    var WelcomeView = Backbone.View.extend({
        initialize: function(options){
            this.initSlider();
            this.initForm();
        },
        initSlider: function(){
            new slider({
                el: '#slider-wrapper',
                context: {
                    pauseOnAction: false,        //操作幻灯片时是否暂停自动播放
                    slideshowSpeed: 5000         //幻灯片切换间隔，单位为毫秒
                }
            });
        },
        initForm: function(){
            new FormView({el: '#form-login'});
        }
    });

    var run = function(){
        new WelcomeView({el: '#main'});
    }

    return {
        run: run
    }
});