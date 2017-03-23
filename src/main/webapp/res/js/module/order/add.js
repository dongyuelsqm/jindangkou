/**
 * 金档口 - 添加/编辑订单
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form'),
        util = require('util/util');

    var template = util.template,
        SelectView = form.SelectView,
        CheckboxView = form.CheckboxView,
        cityMap = require('module/default/cityMap');

    require('jquery-validate');
    require('jquery-validate-add');

    var validates = {
        rules: {
            // postal: {required: true},
            // status: {required: true},
            username: {required: true},
            // price: {
            //     required: true,
            //     number: true
            // },
            receiver: {required: true},
            phone: {
                required: true,
                contactNum: true
            },
            // address: {required: true}
            province: {required: true},
            city: {required: true},
            district: {required: true},
            detail: {
                required: true,
                maxlength: 200
            },
        },
        messages: {
            // postal: {required: '请选择运费模式'},
            // status: {required: '请选择交易状态'},
            username: {required: '请输入买家用户名'},
            // price: {
            //     required: '请输入应收款数',
            //     number: '请输入数字'
            // },
            receiver: {required: '请输入收货人'},
            phone: {
                required: '请输入联系方式',
                contactNum: '请输入正确格式联系方式'
            },
            // address: {required: '请输入收货地址'}
            province: {required: '请选择省份'},
            city: {required: '请选择城市'},
            district: {required: '请选择街道'},
            detail: {
                required: '请输入详细公司地址',
                maxlength: '请输入200位以内字符'
            }
        },
        errorPlacement: function(error, element) {
            if(element.parent().parent().hasClass('nest')){
                error.appendTo(element.parent().parent().parent().parent());
            }else{
                error.appendTo(element.parent().parent());
            }
            error.addClass('col-md-9 col-md-offset-3');
        },
        wrapper: 'div'
    };

    var urls = {
        save: G.contextPath + 'website/order/add',
        sizes: G.contextPath + 'website/size/list',
        colors: G.contextPath + 'website/color',
        search: G.contextPath + 'product/list'
    };

    /**
     * 商品详情 RowView
     */
    var OrderRowView = Backbone.View.extend({
        tagName: 'tr',
        events: {
            'blur input[role="quantity"]': 'validNum'
        },
        initialize: function(options) {
            this.model = options.model;
            this.init();
        },
        init: function(){
            var _this = this;

            this.cacheEls();

            _.each(this.$('.checkbox-inline'), function(item, index){
                _this.$checkboxs.push(new form.CheckboxView({el: $(item)}));
            });
        },
        cacheEls: function(){
            this.$checkboxs = [];

            this.template = template('productItem');
            this.inventoryItemTmpl = template('inventoryItem');
        },
        render: function(){
            var data = this.model.toJSON(),
                _this = this;
            this.$el.html(this.template(data));
            this.$inventory = this.$('.inventory');
            _.map(data.storages, function(item, index){
                _this.$inventory.append(_this.inventoryItemTmpl({
                    model: item.sizeEntity.name + ' ' + item.colorEntity.name,
                    sizeCode: item.sizeEntity.id,
                    colorCode: item.colorEntity.id,
                    number: item.number,
                    productId: item.id
                }));
            });
            return this;
        },
        'validNum': function(ev){
            var $this = $(ev.currentTarget),
                max = parseInt($this.attr('placeholder')),
                val = parseInt($this.val());

            if(val > max){
                alert('输入数值不能大于' + max + '，请重新输入');
                $this.val('');
                return false;
            }

            if(_.isNaN(val)){
                alert('请输入正整数');
                $this.val('');
                return false;
            }
        }
    });

    /**
     * 商品详情 TableView
     */
    var OrderTableView = Backbone.View.extend({
        initialize: function(options) {
            this.content = this.$('tbody');
        },
        addItem: function(model){
            var row = new OrderRowView({
                model: model
            });
            this.content.append(row.render().el);
        }
    });

    /**
     * 商品collection
     */
    var ProductCollection = Backbone.Collection.extend({
        url: urls.search,
        parse: function(rsp){
            return rsp.detail;
        }
    });

    /**
     * 商品列表 ListView
     */
    var noDataTemplate = '<li class="no-data">暂无数据</li>',
        loadingDataTemplate = '<li class="loading">正在请求数据...</li>',
        errorDataTemplate = '<li class="error-data">{{errorMessage}}</li>';

    var ProductListView = Backbone.View.extend({
        tagName: 'ul',
        el: '#product-list',
        template: template('orderItem'),
        events: {
            'change input[role="product"]': 'addProduct'
        },
        initialize: function(options){
            this.collection = new ProductCollection();

            this.listenTo(this.collection, 'request', this.loading);
            this.listenTo(this.collection, 'error', this.error);
            // this.listenTo(this.collection, 'reset', this.render);
            this.listenTo(this.collection, 'sync', this.render);
        },
        'addProduct': function(ev){
            var _this = this,
                $this = $(ev.currentTarget);

            if($this.prop('checked')) {
                var id = $this.data('productid'),
                    model = this.collection.get(id);

                this.table.addItem(model);
            }
        },
        update: function(data){
            this.collection.fetch(data, {reset: true});
        },
        loading: function(){
            this.$el.html(loadingDataTemplate);
        },
        error: function(){
            this.$el.html(errorDataTemplate);
        },
        render: function(){
            var _this = this;
            this.$el.empty();

            if(_this.collection.length > 0) {
                _this.collection.map(function (item, index, list) {
                    _this.$el.append(_this.template(item.toJSON()));
                });

                _.each(_this.$('input[type=checkbox]'), function(item, index){
                    new CheckboxView({el: $(item).parent()});
                });

            }else{
                _this.$el.append('<li>无结果</li>');
            }
            return this;
        },
        bind: function(table){
            this.table = table;
        }
    });

    /**
     * 表单
     */
    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit',
            'click span[role="btn-search"]': 'searchProducts',
            'change #province_select>li>a': 'selectProvince',
            'change #city_select>li>a': 'selectCity',
            'change #district_select>li>a': 'selectDistrict'
        },
        initialize: function(options){
            this.init();

            this.validator = this.$el.validate(validates);
        },
        init: function(){
            var _this = this;
             _this.select_map = {};

            this.orderTable = new OrderTableView({
                el: '#order-table'
            });

            this.productList = new ProductListView();
            this.productList.bind(this.orderTable);

            _.each(this.$('.dropdown'), function(item, index){
                _this.select_map[item.id] = new SelectView({
                    el: $(item),
                    init_data: true
                });
            });
            this.initProvince();
        },
        initProvince: function(){
            var _this = this;
            _this.selectType = 'province';
            _this.selectData = cityMap.provinceMap || [];
            _this.setSelectData();

            // this.$('#province').parent().find('input[type=text]').val('省份');
            // this.$('#city').parent().find('input[type=text]').val('城市');
            // this.$('#district').parent().find('input[type=text]').val('区/县');
        },
        setSelectData: function(){
            var _this = this;
            if(_this.selectData.length > 0){
                var html = '';
                // if(_this.selectType === 'province'){
                //     html = '<li class="selected"><a href="javascript:" data-val="" >省份</a></li>';
                // }else if(_this.selectType === 'city'){
                //     html = '<li class="selected"><a href="javascript:" data-val="" >城市</a></li>';
                // }else if(_this.selectType === 'district'){
                //     html = '<li class="selected"><a href="javascript:" data-val="" >区/县</a></li>';
                // }

                var val = _this.$('#' + _this.selectType).val(),
                    id = 0;
                _.each(_this.selectData, function(item, index){
                    if(_this.selectType === 'province'){
                        html += '<li><a href="javascript:" data-val="' + item.ProID + '">' + item.name + '</a></li>';
                        _this.provinceId = (val === item.name ? item.ProID : '');
                    }
                    if(_this.selectType === 'city'){
                        html += '<li><a href="javascript:" data-val="' + item.CityID + '">' + item.name + '</a></li>';
                        _this.cityId = (val === item.name ? item.CityID : '');
                    }
                    if(_this.selectType === 'district'){
                        html += '<li><a href="javascript:" data-val="' + item.Id + '">' + item.name + '</a></li>';
                        _this.districtId = (val === item.name ? item.Id : '');
                    }
                });
                _this.$('#' + _this.selectType + '_dropdown').find('ul.ul-dropdown').html(html);
            }
        },
        'selectProvince': function(ev){
            var _this = this,
                $this = {},
                provinceId = 0,
                array = [];

            if(typeof ev === 'object'){
                $this = $(ev.currentTarget);
                provinceId = $this.data('val');
            }else{
                provinceId = ev;
                this.select_map['province_dropdown'].setText(provinceId);
            }

            if(provinceId !== ''){
                _.each(cityMap.cityMap, function(item, index){
                    if(item.ProID === provinceId){
                        array.push(item);
                    }
                });

                _this.selectType = 'city';
                _this.selectData = array;
                _this.setSelectData();
                _this.selectCity(_this.cityId || _this.selectData[0].CityID);
            }
        },
        'selectCity': function(ev){
            var _this = this,
                $this = {},
                cityId = 0,
                array = [];

            this.cityId = null;
            if(typeof ev === 'object'){
                $this = $(ev.currentTarget);
                cityId = $this.data('val');
            }else{
                // $this = _this.$('#city_select');
                cityId = ev;
                this.select_map['city_dropdown'].setText(cityId);
            }

            if(cityId !== ''){
                _.each(cityMap.districtMap, function(item, index){
                    if(item.CityID === cityId){
                        array.push(item);
                    }
                });

                _this.selectType = 'district';
                _this.selectData = array;
                _this.setSelectData();
                _this.selectDistrict(_this.districtId || _this.selectData[0].Id);
            }
        }, 
        'selectDistrict': function(ev){
            var $this = {},
                districtId = 0;

            this.districtId = null;
            if(typeof ev === 'object'){
                $this = $(ev.currentTarget);
                districtId = $this.data('val');
            }else{
                // $this = this.$('#district_select');
                districtId = ev;
                this.select_map['district_dropdown'].setText(districtId);
            }
        },
        'searchProducts': function(ev){
            var _this = this;
            this.productList.update(this.$('.search-text').val());
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            if(this.validator.form()) {
                var param = _this.$el.serializeObject(),
                    array = [],
                    obj = {};
                // _.each($('.inventory'), function(item, index){
                    _.each($('input[role="quantity"]'), function(item, index){
                        var $input = $(item);
                        if($input.val() !== ''){
                            array.push({
                                number: $input.val(),
                                color: $input.data('color'),
                                size: $input.data('size'),
                                product_id: $input.data('productid')
                            });
                        }
                    });
                // });

                _.each(param, function (value, key) {
                    switch (key){
                        case 'city':
                        case 'detail':
                        case 'district':
                        case 'province':
                        case 'phone':
                            obj[key] = value;
                            delete param[key];
                            break;
                        case 'receiver':
                            obj['name'] = value;
                            delete param[key];
                            break;
                        default:
                            break;
                    }
                })
                param.sub_orders = JSON.stringify(array);
                param.address = JSON.stringify(obj);
                $.ajax({
                    url: urls.save,
                    data: param,
                    success: function (rsp) {
                        console.log(rsp);
                    }
                });
            }
        }
    });

    function run(){
        new FormView({
            el: '#order-form'
        });
    }

    return {
        run: run
    };
});