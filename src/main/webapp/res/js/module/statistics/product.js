/**
 * 金档口 - 商品统计
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form'),
        util = require('util/util');

    var collectionControls = require('util/collection-controls'),
        collectionExtension = require('util/collection-extension'),
        TableView  = collectionControls.CheckableTableView,
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var urls = {
        listUrl: G.contextPath + 'statistics/product/list'
    };

    var ProductTableView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            new PaginationView(option.pagination).bind(this.table);
            new SearchBoxView(option.search).bind(this.table);
        }
    });

    var ProductView = Backbone.View.extend({
        events: {},
        initialize: function(options){

            //初始化我的消息列表
            new ProductTableView({
                el: '#table-wrapper',
                table: {
                    el: '#product-table',
                    store: {
                        url: urls.listUrl,
                        pagination: {
                            enable: true,
                            extractPage: function (res) { return res.page; }
                        },
                        extractResult: function (res) {
                            return res.page.result;
                        }
                    },
                    row: {
                        template: util.template('product-row'),
                        defaultSetting: BaseItemSetting
                    },
                    sync: true,
                    idAttribute: 'productId'
                },
                pagination: {
                    el: '#pagination'
                },
                search: {
                    el: '#search-box'
                }
            });
        }
    });

    function run(){
        new ProductView({
            el: '#table-wrapper'
        });
    }

    return {
        run: run
    };
});