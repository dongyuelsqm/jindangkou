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
        events: {
            'click .editor': 'editor'
        },
        initialize: function(options){
            this._super_initialize(options);
        },
        editor: function(ev){
            location.href = G.contextPath + '/product/update/' + this.model.get('productId');
        }
    });

    /**
     * 扩展searchbox
     */
    var SearchView = SearchBoxView.extend({
        events: {
            'change input.checkbox-all': 'checkAll',
            'click a[role="btn-delete-mul"]': 'batchDelete',
            'click a[role="btn-off-shelf"]': 'offShelf'
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
        'offShelf': function(ev){
            alert('offShelf');
        },
        bind: function(collectionview){
            this._super_invoke('bind', collectionview);

            this.table.$checkboxAll = new form.CheckboxView({
                el: this.$('.checkbox-all').parent()
            });
        }
    });

    var ProductListView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new CheckableTableView(option.table);
            new PaginationView(option.pagination).bind(this.table);
            new SearchView(option.search).bind(this.table);
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