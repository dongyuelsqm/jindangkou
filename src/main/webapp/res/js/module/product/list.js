/**
 * 金档口 - 产品列表
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
        CheckableTableView  = collectionExtension.CheckableTableView,
        CheckableRowView = collectionExtension.CheckableRowView,
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var urls = {
        listUrl: G.contextPath + 'product/list'
    };

    /**
     * 扩展行
     */
    var ProductRowView = CheckableRowView.extend({
        _events: {
            'click .editor': 'editor'
        },
        initialize: function (options) {},
        render: function () {
            this._super_invoke('render');
        },
        editor: function(e){
            location.href = G.contextPath + '/product/update/' + this.model.get('productId');
        }
    });

    /**
     * 扩展searchbox
     */
    var searchView = SearchBoxView.extend({
        _events: {
        },
        initialize: function (options) {

            _.extend(this.events, this._events);
            this._super_initialize(options);

        }
    });

    var ProductListView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new CheckableTableView(option.table);
            new PaginationView(option.pagination).bind(this.table);
            new searchView(option.search).bind(this.table);
        }
    });

    var ProductView = Backbone.View.extend({
        events: {},
        initialize: function(options){

            //初始化我的消息列表
            new ProductListView({
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
                    rowView: ProductRowView,
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