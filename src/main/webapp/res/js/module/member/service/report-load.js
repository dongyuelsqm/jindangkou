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
    
    var reportLoadView = Backbone.View.extend({
        events: {
            'click .order-by': 'orderBy'
        },
        initialize: function (option) {
            this.table = new TableView(option.table);
            this.pagination = new PaginationView(option.pagination).bind(this.table);
            this.search = new SearchBoxView(option.search).bind(this.table);
        }
    });
    
    /**
     * 列表其他操作
     */
	var reportLoadRowView = TableRowView.extend({
    	events: {
 			'click .operation-buy': 'buy'
 		},
        buy: function(ev){
        	var $this = $(ev.currentTarget);
        	
        	location.href = '#product/report/report-detail.action?reportId=' + this.model.get('reportId');
        }
    });

    module.exports = {
        run: function () {
            new reportLoadView({
                el: '#table-wrapper',
                table: {
                    el: '#report-load-table',
                    store: {
                        url: 'member/service/report-load!list.action',
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
                        template: util.template('reportload-row'),
                        defaultSetting: BaseItemSetting
                    },
                    rowView: reportLoadRowView,
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
