/**
 * 产品购买
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
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    /**
    * 列表所需其他参数
    */
    var other_param = {
        'orderBy': 1,
        'productName': ''
    };
    
    var ApplicationView = Backbone.View.extend({
        events: {
            'click .order-by': 'orderBy'
        },
        initialize: function (option) {
            this.table = new TableView(option.table);
            this.pagination = new PaginationView(option.pagination).bind(this.table);
            this.search = new SearchBoxView(option.search).bind(this.table);
        }
    });

    module.exports = {
        run: function () {
//            new ApplicationView({
//                el: '#table-wrapper',
//                table: {
//                    el: '#application-purchase-table',
//                    store: {
//                        url: 'member/service/application-purchase!list.action',
//                        pagination: {
//                            enable: true,
//                            extractPage: function (res) { return res.page; }
//                        },
//                        extractResult: function (res) {
//                            return res.page.result;
//                        }
//                    },
//                    caption: false,
//                    tfoot: false,
//                    thead: false,
//                    row: {
//                        template: util.template('applicationpurchase-row'),
//                        defaultSetting: BaseItemSetting
//                    },
//                    sync: true,
//                    param: other_param
//                },
//                pagination: {
//                    el: '#pagination'
//                },
//                search: {
//                    el: '#search-box'
//                }
//            });
        }
    };
});
