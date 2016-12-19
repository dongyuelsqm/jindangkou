/**
 * API调用
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
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    /**
    * 列表其他操作
    */
    var orderRowView = TableRowView.extend({
        events: {
			'click .operation-delete': '_delete',
            'click .operation-continuePay': 'continuePay',
            'click .operation-cancelPay': 'cancelPay',
            'click .operation-buy': 'buy'
		},
        initialize: function (options) {
//            _.extend(this.events, this._events);
            this._super_initialize(options);
        },
        _delete: function(ev){
            var $this = $(ev.currentTarget),
                orderNo = this.model.get('orderNo');
            
            alert('确定要删除订单吗？', function () {
                $.ajax({
                    url: './member/service/trade-order!delete.action',
                    data: {
                    	orderNo: orderNo
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                        if(rsp.successSign){
                            alert('删除成功！', function(){
                                tradeOrder.table.store.update();
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
        },
        continuePay: function(ev){
            var $this = $(ev.currentTarget),
                orderId = this.model.get('orderId');
            
            alert('确定要支付吗？', function () {
                $.ajax({
                    url: './member/service/trade-order!continuePay.action',
                    data: {
                        orderId: orderId
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                        if(rsp.successSign){
                            alert('支付成功！', function(){
                                tradeOrder.table.store.update();
                            });
                        }else{
                            alert('敬请期待！');
                        }
                    },
                    error: function(e){
                        console.log(e);
                    }
                });
            }, true);
            
        },
        cancelPay: function(ev){
            var $this = $(ev.currentTarget),
                orderId = this.model.get('orderId');

            alert('确定取消支付吗？', function () {
                $.ajax({
                    url: './member/service/trade-order!cancelPay.action',
                    data: {
                        orderId: orderId
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                        if(rsp.successSign){
                            alert('成功取消支付！', function(){
                                tradeOrder.table.store.update();
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
        },
        buy: function(ev){
        	var $this = $(ev.currentTarget),
        		product_type = this.model.get('productType'),
        		product_id = this.model.get('productId'),
        		url = '';
        	
        	if(product_type === 1){
        		//应用产品
        		url = '#product/app/product-detail.action?productId=';
        	}else if(product_type === 2){
        		//api接口
        		url = '#product/api/api-detail.action?serviceId=';
        	}else if(product_type === 3){
        		//数据报告
        		url = '#product/report/report-detail.action?reportId=';
        	}else{
        		return false;
        	}
        	
        	location.href = url + product_id;
        }
    });
    
    var tradeOrderView = Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            this.pagination = new PaginationView(option.pagination).bind(this.table);
            this.search = new SearchBoxView(option.search).bind(this.table);
        }
    });

    var tradeOrder = {};
    module.exports = {
        run: function () {
            tradeOrder = new tradeOrderView({
                el: '#table-wrapper',
                table: {
                    el: '#trade-order-table',
                    store: {
                        url: 'member/service/trade-order!list.action',
                        pagination: {
                            enable: true,
                            extractPage: function (res) { return res.page; }
                        },
                        extractResult: function (res) {
                            return res.page.result;
                        }
                    },
                    row: {
                        template: util.template('tradeorder-row'),
                        defaultSetting: BaseItemSetting
                    },
                    rowView: orderRowView,
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
