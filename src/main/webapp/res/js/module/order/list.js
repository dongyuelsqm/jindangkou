/**
 * 金档口 - 订单列表
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
        listUrl: G.contextPath + 'order/list'
    };

    /**
     * 扩展行
     */
    var OrderRowView = CheckableRowView.extend({
        events: {
            'click .editor': 'editor'
        },
        initialize: function(options){
            this._super_initialize(options);
        },
        editor: function(ev){
            location.href = G.contextPath + '/order/update/' + this.model.get('orderId');
        }
    });

    /**
     * 扩展searchbox
     */
    var SearchView = SearchBoxView.extend({
        events: {
            'change input.checkbox-all': 'checkAll',
            'click a[role="btn-delete-mul"]': 'batchDelete',
            'click a[role="btn-download"]': 'download'
        },
        initialize: function(options){
            this._super_initialize(options);
        },
        'checkAll': function(ev){
            this.table.checkAll(ev);
        },
        'batchDelete': function(ev){
            alert('delete');
        },
        'download': function(ev){
            alert('download');
        },
        bind: function(collectionview){
            this._super_invoke('bind', collectionview);

            this.table.$checkboxAll = new form.CheckboxView({
                el: this.$('.checkbox-all').parent()
            });
        }
    });

    var OrderListView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new CheckableTableView(option.table);
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
                    rowView: OrderRowView,
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