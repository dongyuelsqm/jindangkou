/**
 * 充值
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $        = require('jquery'),
        _        = require('underscore'),
        Backbone = require('backbone'),
        util     = require('util/util');

    var urls = {
        success: 'member/account/recharge.action',
        recharge: 'member/account/recharge!recharge.action'
    };

    var checkAmount = function (amount) {
        return /^\d{1,6}(\.\d{0,2}){0,1}$/.test(amount) && parseFloat(amount) > 0;
    };
    
    var defaultState = {
        maskShow: false,
        alipayShow: false,
        wechatShow: false
    };
    var RechargeView = Backbone.View.extend({
        events: {
//            'click .btn.alipay': 'aliPay',
//            'click .btn.wechat': 'wechatPay',
            'click .btn-pay': 'pay',
            'click .close': 'closePay',
            'input .recharge-input': 'amountChange',
//            
//            //弹出框
            'click .btn-finish': 'finishPay',
            'click .btn-problem': 'problemPay'
        },
        viewUpdater: {
            maskShow: function (state) {
                var element = this.$('.mask');
                if (state === true) { element.show(); }
                else { element.hide(); }
            },
            alipayShow: function (state) {
                var element = this.$('#pop-alipay');
                if (state === true) {
                    element.show();
                    var w = element.width(), h = element.height();
                    element.css({
                        marginLeft: '-' + (w / 2) + 'px',
                        marginTop: '-' + (h / 2) + 'px'
                    });
                }
                else { element.hide(); }
            },
            wechatShow: function (state) {
                var element = this.$('#pop-wechat');
                if (state === true) {
                    element.show();
                    var w = element.width(), h = element.height();
                    element.css({
                        marginLeft: '-' + (w / 2) + 'px',
                        marginTop: '-' + (h / 2) + 'px'
                    });
                }
                else { element.hide(); }
            }
        },
        initialize: function (option) {
            this.state = new Backbone.Model(defaultState);

            this.listenTo(this.state, 'change', this.dispatcher);
        },
        pay: function () {
            var amount = this.$('.recharge-input').val();
            if (checkAmount(amount)) {
                var newState = _.extend({}, defaultState, { maskShow: true, alipayShow: true });
                this.state.set(newState);
                
                this.$('#recharge-form').submit();
                //window.open(urls.recharge + '?amount=0.1');
            }
        },
//        aliPay: function () {
//            var amount = this.$('.recharge-input').val();
//            if (checkAmount(amount)) {
//                var newState = _.extend({}, defaultState, { maskShow: true, alipayShow: true });
//                this.state.set(newState);
//                
//                this.$('#recharge-form').submit();
//                //window.open(urls.recharge + '?amount=0.1');
//            }
//        },
//        wechatPay: function () {
//            var amount = this.$('.recharge-input').val();
//            if (checkAmount(amount)) {
//                var newState = _.extend({}, defaultState, { maskShow: true, wechatShow: true });
//                this.state.set(newState);
//                //window.open(urls.recharge + '?amount=0.1');
//            }
//        },
        finishPay: function(){
            location.href = '#member/account/finance-center.action';
        },
        problemPay: function(){
            location.href = '#member/account/recharge-rechargelist.action';
        },
        closePay: function () {
            var newState = _.extend({}, defaultState);
            this.state.set(newState);
        },
        amountChange: function (e) {
            var val = $(e.target).val();
            
            if (checkAmount(val)) {
                this.$('.amount-error').hide();
                this.$('.amount-value').text(parseFloat(val).toFixed(2));
            }
            else {
                this.$('.amount-error').show();
                this.$('.amount-value').text('0.00');
            }
        },
        dispatcher: function (state, option) {
            var changed = state.changedAttributes();
            for ( var changeKey in changed ) {
                if (!this.viewUpdater[changeKey] || typeof this.viewUpdater[changeKey] !== 'function') continue;
                this.viewUpdater[changeKey].apply(this, [changed[changeKey]]);
            }
        }
    });

	function run () {
        new RechargeView({
            el: '#content'
        });
	}

    module.exports = {
        run: run
    };
});
