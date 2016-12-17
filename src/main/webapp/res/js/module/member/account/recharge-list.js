/**
 * 充值记录
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        util = require('util/util');

    var collectionControls = require('util/collection-controls'),
        collectionExtension = require('util/collection-extension'),
        TableView = collectionControls.TableView,
        TableRowView = collectionControls.TableRowView,
        PaginationView = collectionControls.PaginationView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var rechargeType = {
            '0': '银行卡'
        },
        rechargeStatus = {
            '1': '支付成功',
            '2': '已关闭',
            '3': '待支付'
        };
    var rechargeItemSetting = _.extend({}, BaseItemSetting, {
            getType: function (type) { return rechargeType[type.toString()]; },
            getStatus: function (status) { return rechargeStatus[status.toString()]; },
            getBalance: function (status, balance, amount) {
                if (status.toString() === '1') {
                    return (balance + amount).toFixed(2);
                }
                else {
                    return '------';
                }
            },
            formatDate: BaseItemSetting.formatDate
        });

     /**
    * 列表其他操作
    */
    var rechargeRowView = TableRowView.extend({
        _events: {
            'click .continuePay': 'continuePay'
		},
        initialize: function (options) {
            _.extend(this.events, this._events);
            this._super_initialize(options);
        },
        continuePay: function(e){
            var $this = $(e.currentTarget),
                rechargeId = $this.data('rechargeId');
            
            alert('确定要支付吗？', function () {
                $.ajax({
                    url: './member/account/recharge!continueRecharge.action',
                    data: {
                        rechargeId: rechargeId
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                        if(rsp.successSign){
                            alert('支付成功！', function(){
                                RechargeList.table.store.update();
                            });
                        }else{
                            alert('操作失败！');
                        }
                    },
                    error: function(e){
                        console.log(e);
                    }
                });
            }, true);
            
        }
    });
    
    var RechargeListView = Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            this.pagination = new PaginationView(option.pagination).bind(this.table);
        }
    });

    var RechargeList = {};
    module.exports = {
        run: function () {
            RechargeList = new RechargeListView({
                el: '#content',
                table: {
                    el: '#recharge-table',
                    store: {
                        url: 'member/account/recharge!list.action',
                        pagination: {
                            enable: true,
                            extractPage: function (res) { 
                            	return res.page; 
                            }
                        },
                        extractResult: function (res) {
                            return res.page.result;
                        }
                    },
                    row: {
                        template: util.template('rechargelist-row'),
                        defaultSetting: rechargeItemSetting
                    },
                    rowView: rechargeRowView,
                    sync: true
                },
                pagination: {
                    el: '#pagination'
                }
            });
        }
    };
});
