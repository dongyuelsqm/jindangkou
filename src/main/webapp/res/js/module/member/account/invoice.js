/**
 * 发票开具
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $        = require('jquery'),
	    _        = require('underscore'),
	    Backbone = require('backbone'),
	    util     = require('util/util'),
	    form     = require('util/form');

    var collectionControls  = require('util/collection-controls'),
        collectionExtension = require('util/collection-extension'),
        CheckableTableView  = collectionExtension.CheckableTableView,
        BaseItemSetting     = collectionExtension.BaseItemSetting,
        CheckboxView = form.CheckboxView;

    var urls = {
	        saveUrl: './member/account/invoice!saveInvoice.action',
	        invoiceUrl: ['./member/account/invoice!getCommonInvoice.action',
	                     './member/account/invoice!getAddedTaxInvoice.action'],
	        listUrl: './member/account/invoice!getOrderList.action'
	    },
	    defualts = [{
	    	contact: '',
	    	address: '',
	    	invoiceContent: '',
	    	invoiceTitle: '',
	    	tel: '',
	    	invoiceId: 0
	    },{
			address: '',
			contact: '',
			tel: '',
			campanyName: '',
			campanyCode: '',
			regAddr: '',
			regTel: '',
			regBank: '',
			bankCode: '',
			invoiceId: 0
	    }];

    /**
     * 扩展tableView
     */
    var tableView = CheckableTableView.extend({
        tfootRender: util.template('invoice-list-foot'),
        initialize: function (options) {
            this._super_initialize(options);
            this.renderFooter();
            
            this.listenTo(this.checkedList, 'add', this._renderFooter);
            this.listenTo(this.checkedList, 'remove', this._renderFooter);
        },
        _renderFooter: function(){
        	this.renderFooter();
        },
        renderFooter: function () {
            var totalAmount = 0;
            _.each(this.getCheckedModels(), function (item) { totalAmount += item.orderAmount; });
            this.$tfoot.html(this.tfootRender({ totalAmount: totalAmount }));
        }
//        checkall: function () {
//            this._super_invoke('checkall');
//            this.renderFooter();
//        }
    });
    
    var InvoiceTableView = Backbone.View.extend({
    	initialize: function (options) {
    		this.table = new tableView(options.table);
        },
    });
    
    var InvoiceTypeView = Backbone.View.extend({
    	events: {
            'change .radio': 'invoiceManager'
        },
        initialize: function(options){
        	_.each(this.$('.radio'), function(item, index){
        		form.radiobox($(item));
        	});
        	
        	this.initType = parseInt(G.invoiceType);
        	this.initId = G.invoiceId;
        	
        	this.model = new Backbone.Model({});
        	this.$info = this.$('#invoice-type-info');
        	
        	this.invoiceManager();
        	
        },
        invoiceManager: function (ev) {       	
        	var _this = this,
        		data = {};
        	if(ev){
        		var $this = $(ev.currentTarget);
        		_this.invoiceType = parseInt($this.val());
        		if(_this.initType === _this.invoiceType){
        			data.invoiceId = _this.initId;
        		}
        	}else{
        		_this.invoiceType = _this.initType;
        		data.invoiceId = _this.initId;
        	}
        	
        	_this.model.clear();       	
        	$.ajax({
        		url: urls.invoiceUrl[_this.invoiceType],
        		data: data,
        		type: 'post',
        		dataType: 'json',
        		success: function(rsp){
        			if(rsp.successSign){
        				_this.model.set(rsp.resultMap);
        				_this.invoiceRender();
//        				_this.invoiceId = rsp.resultMap.invoiceId;
        			}else{
        				alert(rsp.errorMessage || '获取数据失败', function(){
        					_this.model.set(defualts[_this.invoiceType]);
            				_this.invoiceRender();
        				});
        			}
        		},
        		error: function(xhr){
        			alert('操作失败，请稍后重试！');
        		}
        	});
        },
        invoiceRender: function(){
        	var type = this.invoiceType === 0 ? 'common' : 'VAT';
        	this.template = util.template('invoice-' + type);
        	this.$info.html(this.template(this.model.toJSON()));
        }
    });
    
    var InvoiceView = Backbone.View.extend({
        events: {
            'click button.btn-submit': 'submit'
        },
        initialize: function (options) {
//        	this.store = options.store.listData;
        	this.initType();
        	this.initTable();
        },
        initType: function(){
        	this.typeView = new InvoiceTypeView({
        		el: '#invoice-type-wrapper'
        	});
        },
        initTable: function(){
        	var table = new InvoiceTableView({
                el: '#table-wrapper',
                table: {
                    el: '#invoice-table',
                    store: {
                        url: urls.listUrl,
                        extractResult: function (res) {
                            return res.orderList;
                        }
                    },
                    row: {
                        template: util.template('invoice-list-row'),
                        defaultSetting: BaseItemSetting
                    },
                    sync: true
                },
            });
        	
        	this.table = table.table;
        },
        submit: function (ev) {
            var orderIds = _.map(this.table.getCheckedModels(), function (item) {
                    return item.orderId;
                }),
                _this = this;

            if(orderIds.length < 1){
            	alert('请选择订单！');
            	return;
            }
            $.ajax({
            	url: urls.saveUrl,
                dataType: 'json',
                type: 'post',
                data: { 
                	orderIds: orderIds,
                	invoiceId: G.invoiceId
                },
                success: function (res) {
                    if (res.successSign) {
                        alert('提交成功!', function(){
                        	_this.table.update();
                        });
                    }
                    else {
                        alert('提交失败!');
                    }
                },
                error: function(xhr){
                	alert('操作失败，请稍后再试！');
                }
            });
        }
    });

	function run () {
		new InvoiceView({
			el: '#invoice-wrapper',
//			store: {
//				listData: orderListData
//			}
		});
	}

    module.exports = {
        run: run
    };
});
