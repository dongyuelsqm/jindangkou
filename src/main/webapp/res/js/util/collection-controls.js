/**
 * 集合类 基础控件集
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        util = require('util/util'),
        form = require('util/form');

    var SelectView = form.SelectView;

    //数据
    /**
     * 数据集合
     * @param {object} options: 配置项
     *   {function} checkSuccess: 检查response是否成功的function,默认检查successSign字段标识
     *   {function} extractResult: 取得response中数据数组的function,默认取得result字段
    */
    var DataList = Backbone.Collection.extend({
		idAttribute: 'id',
		initialize: function (options) {
            this.checkSuccess = options.checkSuccess || function (res) {
                return res.result;
            };
            this.extractResult = options.extractResult || function (res) {
                return res.objs;
            };
		},
		parse: function (response, options) {
            if (!this.checkSuccess(response)) {
                this.trigger('failed');
                return [];
            }
            else {
                return this.extractResult(response);
            }
        },
        getCollection: function (filter) {
            //TODO
        }
	});
    /**
     * 分页模型
     * @param {object} options: 配置项
     *   {object} defaults: 初始分页，包括pageNo pageSize totalCount totalPages,默认值分别为1 15 0 0
     *   {function} extractPage: 取得response中分页数据的function,默认取得page字段
    */
	var PageGuideModel = Backbone.Model.extend({
		defaults: {
			pageNo: 1,
            pageSize: 15,
			totalCount: 0,
			totalPages: 0
		},
        initialize: function (options) {
            this.pageQuery = new Backbone.Model(options.defaults || this.defaults);
            this.extractPage = options.extractPage || function (res) {
                return res;
            };
        },
        setPage: function (response) {
            this.pageQuery.set(this.parse(this.extractPage(response)));

            this.trigger('pageChange');
        },
        getPage: function () {
            return this.parse(this.pageQuery.toJSON());
        },
		parse: function (attrs) {
			var attrs = _.pick(attrs, 'pageNo', 'totalCount', 'totalPages');
			return attrs;
		}
	});
    
    /*
    * 全选模型
    */
    var selectAllCollection = Backbone.Collection.extend({});
    
    /**
     * 集合数据模型,整合DataList和PageGuideModel
     * @param {object} options: 基于DataList的配置项
     *  {object} pagination: PageGuideModel配置项，其中enable字段标识是否启用分页
    */
    var CollectionStore = Backbone.Model.extend({
        initialize: function (options) {
            this.data = new DataList(options);
            this.queryModel = new Backbone.Model({});
            if (options.pagination && options.pagination.enable) {
                this.pagination = new PageGuideModel(options.pagination);
                this._set(this.pagination.getPage());
            }
            if (options.url) {
                this.data.url = options.url;
            }

            this.listenTo(this.data, 'sync', this.sync);
            this.listenTo(this.data, 'failed', this.failed);
        },
        _set: function(query){
            this.queryModel.set(query);
        },
        reset: function (models, options) {
            this.data.reset(models, options);
        },
        size: function () {
            return this.data.length;
        },
        query: function (query) {
            this._set(query);
            this.update();
        },
        update: function () {
            var query = this.queryModel.toJSON();
            this.data.fetch({
                reset: true,
				data: query
            });
            
        },
        sync: function (list, response, options) {
            if (this.pagination) {
                this.pagination.setPage(response);
            }

            this.trigger('sync');
		},
        failed: function () {
            this.trigger('failed');
        }
    });

    //基础视图
    var noDataTemplate = '<span class="no-data">暂无数据</span>',
    	loadingDataTemplate = '<span class="loading">正在请求数据...</span>',
    	errorDataTemplate = '<span class="error-data">{{errorMessage}}</span>';
    
    /**
     * 基础视图,提供子类调用基类override的同名方法的方法
    */
    var BaseView = Backbone.View.extend({
        _super_initialize: function (options) {
            var proto = this._super_invoke('initialize', [options]);
            if (proto.events) {
                this.delegateEvents(_.extend({}, this.events, proto.events));
            }
        },
        _super_invoke: function (superFn, params) {
            if(!params){
                params = [];
            }else if(!_.isArray(params)){
                params = [params];
            }
            if (!this._super_level) { this._super_level = {}; }
            if (!this._super_level[superFn]) { this._super_level[superFn] = 0; }
            var proto = this.constructor.__super__,
                currentLevel = 0;
            while (proto !== BaseView && typeof proto !== 'undefined') {
                currentLevel++;
                if (proto[superFn] && this._super_level[superFn] < currentLevel) {
                    this._super_level[superFn] = currentLevel;
                    proto[superFn].apply(this, params);
                    break;
                }
                proto = proto.constructor.__super__;
            }

            if (currentLevel >= this._super_level[superFn]) {
                delete this._super_level[superFn];
            }
            return proto;
        }
    });
    
    /**
     * 集合类基础视图
     * @param {object} options: 配置项
     *  {object} store: CollectionStore 配置项
     *  {string | DOMElement | Sizzle} appendTo: 将视图元素添加到的父元素
     *  {template} noDataTemplate: 无数据时的模板,不配置时使用默认模板
     *  {template} errorDataTemplate: 请求出错模板,不配置时使用默认模板
     *  {template} loadingDataTemplate: loading时的模板,不配置时使用默认模板
     *  {object} itemOption: 该视图子项的配置
    */
    var CollectionView = BaseView.extend({
        initialize: function (options) {
            this.store = new CollectionStore(options.store);
            this.data = this.store.data;
            this.items = [];

            this.errorDataTemplate = options.errorDataTemplate || this.errorDataTemplate || util.render(errorDataTemplate);
            this.loadingDataTemplate = options.loadingDataTemplate || this.loadingDataTemplate || util.render(loadingDataTemplate);
            this.noDataTemplate = options.noDataTemplate || this.noDataTemplate || util.render(noDataTemplate);

            this.$content = this.$el;
            if (options.appendTo) {
                $(options.appendTo).append(this.el);
            }

            this.listenTo(this.store, 'sync', this.render);
            this.listenTo(this.data, 'update', this.update);
            this.listenTo(this.data, 'request', this.loading);
            this.listenTo(this.data, 'error', this.error);
        },
        render: function () {
            var _this = this;
            _this.$content.empty();
            _this.items = [];

            if (_this.data.length > 0) {
                var elItems = _this.data.map(function (item, index, list) {
                    _this.items[index] = new _this.ItemView(_this.itemOption);
                    item.set({index: index});
                    _this.items[index].bind(item);
                    //_this.$content.append(_this.items[index].el);
                    //_this.listenTo(item, 'update', _this.update);
                    return _this.items[index].el;
                });

                _this.$content.append(elItems);
            }
            else {
                _this.setTemplateHtml(_this.noDataTemplate(_this.noDataModel));
            }
        },
        setData: function (data) {
            this.store.reset(data);
            this.render();
        },
        update: function(){
        	this.store.update();
        },
        loading: function(model, rsp, options){
            this.setTemplateHtml(this.loadingDataTemplate(this.noDataModel));
        	
        	//滚动条 - updata
        	if(!G.isOldIE8()){
                $('#J_body').perfectScrollbar('update');
            }
        },
        error: function(model, rsp, options){
            this.setTemplateHtml(this.errorDataTemplate(_.extend({}, {errorMessage: '发生错误，请稍后重试'}, this.noDataModel)));
        },
        setTemplateHtml: function(html){
            if (this.items.length === 0) {
                this.items[0] = new this.ItemView(this.itemOption);
            }
            this.items[0].$el.html(html);
            this.$content.html(this.items[0].$el);
        }
    });
    /**
     * 集合类子项基础视图
     * @param {object} options: 配置项
     *  {template} template: 模板
     *  {object} defaultSetting: 默认设置,render时合并于model
    */
    var ItemView = BaseView.extend({
        initialize: function (options) {
            this._super_initialize(options);
            this.template = options.template;
            this.defaultSetting = options.defaultSetting || {};
        },
        bind: function (model) {
            this.model = model;
            
            if(this._listenTo){
                this._listenTo();
            }
            
            this.render();
        },
        // noData: function (noDataModel) {
        //     this.$el.html(this.noDataTemplate(noDataModel));
        // },
        render: function () {
            var model = _.extend({}, this.defaultSetting, this.model.toJSON());
            this.$el.html(this.template(model));
        },
        setTemplate: function (template) {
            this.template = template;
        },
        setDefaultSetting: function (defaultSetting) {
            this.defaultSetting = defaultSetting;
        }
    });

    //table视图
    /**
     * 表格子项(行)视图
     * @param {object} options: 配置项,同父类
     * {function} _listenTo: 用于添加监听事件
    */
    var TableRowView = ItemView.extend({
        tagName: 'tr',
        initialize: function (options) {
            this._super_initialize(options);
        },
        // noDataTemplate: util.render('<td colspan="{{colCount}}">' + noDataTemplate + '</td>')
    });
    
    var tableViewTemplate = '\
        <# if (typeof caption !== "undefined" && caption) { #> \
            <caption></caption> \
        <# } #> \
        <# if (typeof thead !== "undefined" && thead) { #> \
            <thead></thead> \
        <# } #> \
        <# if (typeof tfoot !== "undefined" && tfoot) { #> \
            <tfoot></tfoot> \
        <# } #> \
        <tbody></tbody>\
    ';
    /**
     * 表格视图
     * @param {object} options: 配置项,基于父类
     *   {Backbone.View} rowView: 子项视图,默认TableRowView
     *   {template} caption: 表格标题模板,不配置el时生效
     *   {template} thead: 表格表头模板,不配置el时生效
     *   {template} tfoot: 表格表尾模板,不配置el时生效
     *   {number} colCount: 列数,默认根据表头列数获取
     *   {boolean} sync: 初始化时是否同步数据
     *   {object} param: 初始化时的额外参数
     *   {boolean} selectall: 列表是否有批量操作功能
    */
    var TableView = CollectionView.extend({
        tagName: 'table',
        ItemView: TableRowView,
        errorDataTemplate: util.render('<td colspan="{{colCount}}">' + errorDataTemplate + '</td>'),
        loadingDataTemplate: util.render('<td colspan="{{colCount}}">' + loadingDataTemplate + '</td>'),
        noDataTemplate: util.render('<td colspan="{{colCount}}">' + noDataTemplate + '</td>'),
        initialize: function (options) {
            this._super_initialize(options);
            this.ItemView = options.rowView || this.ItemView;
            this.itemOption = options.row;

            if (this.$el.context === this.$el[0]) {
                this.$el.addClass('table');
                this.$el.html(util.render(tableViewTemplate)(options));
                if (options.caption) {
                    this.$('caption').html(options.caption());
                }
                if (options.thead) {
                    this.$('thead').html(options.thead());
                }
                if (options.tfoot) {
                    this.$('tfoot').html(options.tfoot());
                }
            }

            if (options.thead) {
                this.$('thead').html(options.thead());
            }
            this.$content = this.$tbody = this.$('tbody');
            this.$caption = this.$('caption');
            this.$thead   = this.$('thead');
            this.$tfoot   = this.$('tfoot');

            this.noDataModel = {
                colCount: options.colCount || (this.$thead ? this.$thead.find('tr').eq(0).children().length : 1)
            };
            
            if (options.param) {
                this.param = options.param;
                this.store._set(options.param);
            }
            
            if (options.sync) {
                this.store.update();
            }
            
            if(options.idAttribute){
                this.idAttribute = options.idAttribute;
                this.store.data.idAttribute = options.idAttribute;
            }

        }
    });

    //list视图
    /**
     * 列表子项(行)视图
     * @param {object} options: 配置项,同父类
    */
    var ListItemView = ItemView.extend({
        tagName: 'li'
    });
    /**
     * 列表视图
     * @param {object} options: 配置项,基于父类
     *   {Backbone.View} itemView: 子项视图,默认ListItemView
     *   {boolean} sync: 初始化时是否同步数据
    */
    var ListView = CollectionView.extend({
        tagName: 'ul',
        ItemView: ListItemView,
        initialize: function (options) {
            this._super_initialize(options);

            this.ItemView = options.itemView || this.ItemView;
            this.itemOption = options.item;
            
            if (options.param) {
//                this.param = options.param;
                this.store._set(options.param);
            }

            if (options.sync) {
                this.store.update();
            }
        }
    });

    //分页视图
    var pageTemplate = '\
        <span class="total-count">共 {{totalCount}} 项</span>\
        <a href="javascript:" class="first">&lt;&lt;</a>\
        <a href="javascript:" class="prev">&lt;</a>\
        <span><input type="text" class="paged" value="{{pageNo}}"> of {{totalPages}}</span>\
        <a href="javascript:" class="next">&gt;</a>\
        <a href="javascript:" class="last">&gt;&gt;</a>\
        <a href="javascript:" class="go">go</a>\
    ';
    /**
     * 分页视图
     * @param {object} options: 配置项
     *   {template} template: 模板,省略时使用pageTemplate作为默认模板
     *   {object} operationMap: 操作元素配置
    */
    var PaginationView = BaseView.extend({
        pageChange: {
            next: function () {
                var page = this.pagination.getPage();
                if (page.pageNo < page.totalPages) { page.pageNo++; }
                this.store.query(page);
            },
            last: function () {
                var page = this.pagination.getPage();
                page.pageNo = page.totalPages;
                this.store.query(page);
            },
            prev: function () {
                var page = this.pagination.getPage();
                if (page.pageNo > 1) { page.pageNo--; }
                this.store.query(page);
            },
            first: function () {
                var page = this.pagination.getPage();
                page.pageNo = 1;
                this.store.query(page);
            },
            'goto': function () {
                var pageNo = this.$(this.operationMap.pageno).val();
                var page = this.pagination.getPage();
                if (/^\d+$/.test(pageNo)) {
                    pageNo = parseInt(pageNo);
                    if (pageNo >= 1 && pageNo <= page.totalPages) {
                        page.pageNo = pageNo;
                        this.store.query(page);
                    }
                }
            }
        },
        initialize: function (options) {
            this.template = options.template || util.render(pageTemplate);

            this.operationMap = _.extend({
                    next: 'a.next',
                    last: 'a.last',
                    prev: 'a.prev',
                    first: 'a.first',
                    'goto': 'a.go',
                    pageno: '.paged'
                }, options.operationMap || {});
        },
        render: function () {
            this.$el.html(this.template(this.pagination.getPage()));
        },
        click: function (e) {
            var el = $(e.target);
            if (el.is(this.operationMap.next)) { this.pageChange.next.apply(this); }
            else if (el.is(this.operationMap.last)) { this.pageChange.last.apply(this); }
            else if (el.is(this.operationMap.prev)) { this.pageChange.prev.apply(this); }
            else if (el.is(this.operationMap.first)) { this.pageChange.first.apply(this); }
            else if (el.is(this.operationMap['goto'])) { this.pageChange['goto'].apply(this); }
        },
        bind: function (collectionview) {
            var store = collectionview.store;
            if (store.pagination) {
                this.store = store;
                this.pagination = store.pagination;
                this.delegateEvents({'click': 'click'});
                this.listenTo(this.pagination, 'pageChange', this.render);

                this.render();
            }
        }
    });
    
    /**
     * 搜索视图
     * @param {object} options: 配置项
    */
    var order_config = {
        'asc': {
            key: '1',
            icon: 'unfold'
        },
        'desc': {
            key: '2',
            icon: 'fold'
        }
    };
    var SearchBoxView = BaseView.extend({
        events: {
            'click .btn-search': 'search',
            'click .btn-clear': 'clear',
            'change .select' : 'searchType',
            'click .order-by'     : 'orderBy',
            'click .order-switch' : 'orderSwitch'
        },
        initialize: function (options) { 
            this._super_initialize(options);

            this.model = new Backbone.Model({});
            this.listenTo(this.model, 'change', this.query);
        },
        search: function(e){
            var $this = e.currentTarget ? $(e.currentTarget) : e;
           	var $p = $this.parent(),
                $inputs = $p.find('.search-text'),
                attr = {},
                name = '',
                val = '';
            
            _.each($inputs, function(item, index){
//                if($(item).val() !== ''){
                    name = $(item).attr('name');
                    val = $(item).val();
                    attr[name] = val;
//                    num++;
//                }
            });
            
//            if(num === 0){
//                alert('请输入搜索条件！');
//                return false;
//            }
            
            this.model.set(attr, {silent: true}).trigger('change');
        },
        clear: function(e){
        	var $this = $(e.currentTarget),
	            $p = $this.parent(),
	            $input = $p.find('.search-text');
        	
        	$input.val('');
        },
        searchType: function(e){
            var $this = $(e.currentTarget),
                type = $this.val(),
                name = $this.attr('name'),
                attr = {};
            attr[name] = type;
            this.model.set(attr);
        },
        orderBy: function(e){
            var $this = $(e.currentTarget),
                order_val = $this.data('val'),
                order_name = $this.data('name'),
                attr = {};
            
            attr[order_name] = order_val;
            this.model.set(attr);    
            this.addCurrentClass($this);
        },
        orderSwitch: function(e){
            var $this = $(e.currentTarget),
                $iconfont = $this.find('.iconfont'),
                order_val = $this.data('val'),
                type = '',
                attr = {
                    orderType: order_val,
                    orderBy: ''
                };
            
            if($this.hasClass('order-asc')){
                $this.removeClass('order-asc');
                type = 'desc';
                $iconfont.removeClass('icon-fold').addClass('icon-unfold');
            }else{
                $this.removeClass('order-desc');
                type = 'asc';
                $iconfont.removeClass('icon-unfold').addClass('icon-fold');
            }
            
            $this.addClass('order-' + type)
            attr.orderBy = order_config[type].key;
            
            this.model.set(attr); 
            this.addCurrentClass($this);
        },
        addCurrentClass: function($this){
            $this.parent().find('.active').removeClass('active');
            $this.addClass('active');
        },
        query: function(){
            var query = this.model.toJSON();
            this.store.query(query);
        },
        update: function(){
        	this.store.update();
        },
        bind: function (collectionview) {
            this.table = collectionview;
            this.store = collectionview.store;
            this.selection = collectionview.selection || {};
            this.idAttribute = collectionview.idAttribute || 'id';
            
            if(this.table.param){
                this.model.set(this.table.param, {silent: true});
            }
        }
    });

    module.exports = {
        BaseView: BaseView,
        CollectionView: CollectionView,
        CollectionItemView: ItemView,

        TableView: TableView,
        TableRowView: TableRowView,
        ListView: ListView,
        ListItemView: ListItemView,
        PaginationView: PaginationView,
        SearchBoxView: SearchBoxView,

        DataList: DataList,
        PageGuideModel: PageGuideModel,
        CollectionStore: CollectionStore
    };
});
