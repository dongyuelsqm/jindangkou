/**
 * 金档口 - 客户统计
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
        listUrl: G.contextPath + 'statistics/customer/list'
    };

    var CustomerTableView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            new PaginationView(option.pagination).bind(this.table);
            new SearchBoxView(option.search).bind(this.table);
        }
    });

    var CustomerView = Backbone.View.extend({
        events: {},
        initialize: function(options){

            //初始化我的消息列表
            new CustomerTableView({
                el: '#table-wrapper',
                table: {
                    el: '#customer-table',
                    store: {
                        url: urls.listUrl,
                        pagination: {
                            enable: true
                        }
                    },
                    row: {
                        template: util.template('customer-row'),
                        defaultSetting: BaseItemSetting
                    },
                    sync: true,
                    idAttribute: 'customerId'
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
        new CustomerView({
            el: '#table-wrapper'
        });
    }

    return {
        run: run
    };
});