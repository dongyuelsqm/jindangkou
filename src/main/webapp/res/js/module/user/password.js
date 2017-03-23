/**
 * 金档口 - 修改密码
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form');

    require('jquery-validate');
    require('jquery-validate-add');

    var urls = {
        modify: G.contextPath + ''
    };

    var validates = {
        rules: {
            param1: {
                required: true,
                rangelength: [6, 16],
                pwd: true
            },
            param2: {
                required: true,
                rangelength: [6, 16],
                pwd: true
            },
            param3: {equalTo: '[name="param2"]'}
        },
        messages: {
            param1: {
                required: '请输入密码',
                rangelength: '密码必须为6-16个字符',
                pwd: '密码至少包含字母、数字和符号其中的两种'
            },
            param2: {
                required: '请输入密码',
                rangelength: '密码必须为6-16个字符',
                pwd: '密码至少包含字母、数字和符号其中的两种'
            },
            param3: {equalTo: '两次输入密码不一致'}
        },
        errorPlacement: function(error, element) {
            error.appendTo(element.parent().parent());
            error.addClass('col-md-4');
        },
        wrapper: 'div'
    };

    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit'
        },
        initialize: function(opstions){
            var _this = this;

            this.validator = this.$el.validate(validates);
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            if(this.validator.form()) {
                $.ajax({
                    url: urls.add,
                    data: this.$el.serializeObject(),
                    success: function (rsp) {
                        console.log(rsp);
                    }
                });
            }
        }
    });

    function run(){
        new FormView({
            el: '#password-form'
        });
    }

    return {
        run: run
    };
});