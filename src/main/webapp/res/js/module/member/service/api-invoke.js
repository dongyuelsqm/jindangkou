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

    var ApiInvokeView = Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            this.pagination = new PaginationView(option.pagination).bind(this.table);
            this.search = new SearchBoxView(option.search).bind(this.table);
        }
    });

    /**
     * 列表其他操作
     */
	var apiInvokeRowView = TableRowView.extend({
    	events: {
 			'click .operation-buy': 'buy'
 		},
        buy: function(ev){
        	var $this = $(ev.currentTarget);
        	
        	location.href = '#product/api/api-detail.action?serviceId=' + this.model.get('serviceId');
        }
    });
	
    module.exports = {
        run: function () {
            new ApiInvokeView({
                el: '#table-wrapper',
                table: {
                    el: '#api-invoke-table',
                    store: {
                        url: 'member/service/api-invoke!list.action',
                        pagination: {
                            enable: true,
                            extractPage: function (res) { return res.page; }
                        },
                        extractResult: function (res) {
                            return res.page.result;
                        }
                    },
                    caption: false,
                    tfoot: false,
                    thead: false,
                    row: {
                        template: util.template('apiinvoke-row'),
                        defaultSetting: BaseItemSetting
                    },
                    rowView: apiInvokeRowView,
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
