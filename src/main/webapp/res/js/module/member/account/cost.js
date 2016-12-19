/**
 * 消费记录
 * cailuwei<cailuwei@chinamobile.com>
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
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;
    
    var payMentType = {
    		'0': '支付宝支付',
    		'1': '微信支付',
    		'2': '银联支付',
    		'3': '和包支付',
    		'4': '余额支付'
    };
    
    var costItemSetting = _.extend({}, BaseItemSetting, {
        getPayMentType: function (type) { 
        	return payMentType[type.toString()]; 
        },
    });

     /**
    * 列表其他操作
    */
    var costRowView = TableRowView.extend({
        events: {
            'click .order-detail': 'orderDetail'
		},
        orderDetail: function(e){
            var $this = $(e.currentTarget),
            orderNo = $this.data('orderno');
            
            location.href = '#member/service/trade-order.action?orderNo=' + orderNo;      
        }
    });
    
    var costTableView = Backbone.View.extend({
        initialize: function (options) {
            this.table = new TableView(options.table);
            new PaginationView(options.pagination).bind(this.table);
            new SearchBoxView(options.search).bind(this.table);
        }
    });

    module.exports = {
        run: function () {
            var costList = new costTableView({
                el: '#table-wrapper',
                table: {
                    el: '#cost-table',
                    store: {
                        url: 'member/account/cost!list.action',
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
                        template: util.template('costlist-row'),
                        defaultSetting: costItemSetting
                    },
                    rowView: costRowView,
                    sync: true
                },
                pagination: {
                    el: '#pagination'
                },
                search: {
                    el: '#search-box'
                }
            });
        }
    };
});
