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

    var template = util.template;
    var collectionControls = require('util/collection-controls'),
        collectionExtension = require('util/collection-extension'),
        CheckableTableView  = collectionExtension.CheckableTableView,
        CheckableRowView = collectionExtension.CheckableRowView,
        TableRowView = collectionControls.TableRowView,
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var urls = {
        listUrl: G.contextPath + 'website/order/list/all'
    };
    //基础视图
    var noDataTemplate = '<span class="no-data">暂无数据</span>',
        loadingDataTemplate = '<span class="loading">正在请求数据...</span>',
        errorDataTemplate = '<span class="error-data">发生错误，请稍后重试</span>';

    /**
     * 每个订单子内容
     */
    var OrderSubRowView = Backbone.View.extend({
        tagName: 'tr',
        className: 'order-sub-list',
        template: template('order-sub-row'),
        initialize: function(options){
            this.model = options.model;
        },
        render: function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });

    /**
     * 扩展行
     */
    var OrderRowView = CheckableRowView.extend({
        tagName: 'tbody',
        noDataTemplate: util.render('<tr><td colspan="{{colCount}}">' + noDataTemplate + '</td></tr>'),
        events: {
            'click .editor': 'editor'
        },
        initialize: function(options){
            this._super_initialize(options);
            this.items = [];
        },
        editor: function(ev){
            location.href = G.contextPath + '/order/update/' + this.model.get('orderId');
        },
        render: function(param){
            this._super_invoke('render', param);
            var _this = this;
            var collection_sub_orders = new Backbone.Collection(this.model.get('sub_orders'));
            collection_sub_orders.map(function(item, index, list){
                item.set({'index': index});
                item.set({'list_length': list.length});
                _this.items[index] = new OrderSubRowView({
                    model: item
                });
                return _this.items[index].el;
            });

            _this.$('tr[role="order-info"]').next(collection_sub_orders);
        }
    });

    /**
     * 扩展tableView
     */
    var TableView= CheckableTableView.extend({
        tagName: 'div',
        errorDataTemplate: util.render('<tr><td colspan="{{colCount}}">' + errorDataTemplate + '</td></tr>'),
        loadingDataTemplate: util.render('<tr><td colspan="{{colCount}}">' + loadingDataTemplate + '</td></tr>'),
        initialize: function(options) {
            this._super_initialize(options);
            this.$content = this.$tbody = this.$('#order-table-detail');
            this.store.update();
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

    var OrderTableView =  Backbone.View.extend({
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
            new OrderTableView({
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