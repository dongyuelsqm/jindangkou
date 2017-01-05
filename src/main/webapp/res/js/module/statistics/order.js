/**
 * 金档口 - 订单统计
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
        TableView  = collectionControls.TableView,
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var urls = {
        listUrl: G.contextPath + 'order/list'
    };

    /**
     * 扩展searchbox
     */
    var SearchView = SearchBoxView.extend({
        initialize: function(options){
            this._super_initialize(options);

            this.initDatePicker();
        },
        initDatePicker: function(){}
    });

    var OrderListView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            new PaginationView(option.pagination).bind(this.table);
            new SearchView(option.search).bind(this.table);
        }
    });

    var OrderView = Backbone.View.extend({
        events: {},
        initialize: function(options){

            //初始化我的消息列表
            new OrderListView({
                el: '#table-wrapper',
                table: {
                    el: '#order-table',
                    store: {
                        url: urls.listUrl,
                        pagination: {
                            enable: true
                        }
                    },
                    row: {
                        template: util.template('order-row'),
                        defaultSetting: BaseItemSetting
                    },
                    sync: true,
                    idAttribute: 'orderId'
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
        new OrderView({
            el: '#table-wrapper'
        });
    }

    return {
        run: run
    };
});