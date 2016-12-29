/**
 * 金档口 - 添加/编辑订单
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form'),
        util = require('util/util');

    require('jquery-validate');
    require('jquery-validate-add');

    var validates = {
        rules: {
            // code: {require: true},
            name: {require: true},
            price: {require: true},
            department: {require: true},
            minimum: {require: true},
            // pictures: {require: true},
            // videos: {require: true},
            descriptive: {require: true}
        },
        messages: {
            name: {require: '请输入商品名称'},
            price: {require: '请输入售价'},
            department: {require: '请选择商品分类'},
            minimum: {require: '请输入起批件数'},
            // pictures: {require: true},
            // videos: {require: true},
            descriptive: {require: '请输入商品描述'}
        },
        errorPlacement: function(error, element) {
            error.appendTo(element.parent().parent());
            error.addClass('col-md-9 col-md-offset-3');
        },
        wrapper: 'div'
    };

    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit',
            'click spen[role="btn-search"]': 'searchProducts'
        },
        initialize: function(opstions){
            this.$checkboxs = [];
            this.init();
        },
        init: function(){
            var _this = this;
            _.each(this.$('.checkbox-inline'), function(item, index){
                _this.$checkboxs.push(new form.CheckboxView({el: $(item)}));
            });
        },
        'searchProducts': function(ev){

        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            console.log(_this.$el.serializeArray());
            $.ajax({
                url: G.contextPath + 'order/add',
                data: _this.$el.serializeArray(),
                type: 'post',
                dateType: 'json',
                success: function(rsp){
                    console.log(rsp);
                }
            });
        }
    });

    function run(){
        new FormView({
            el: '#product-form'
        });
    }

    return {
        run: run
    };
});