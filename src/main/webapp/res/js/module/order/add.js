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
        colorMap = require('module/default/colors').colorMap;

    require('jquery-validate');
    require('jquery-validate-add');

    var validates = {
        rules: {
            postal: {required: true},
            status: {required: true},
            username: {required: true},
            price: {required: true},
            receiver: {required: true},
            phone: {
                required: true,
                contactNum: true
            },
            address: {required: true}
        },
        messages: {
            postal: {required: '请选择运费模式'},
            status: {required: '请选择交易状态'},
            username: {required: '请输入买家用户名'},
            price: {required: '请输入应收款数'},
            receiver: {required: '请输入收货人'},
            phone: {
                required: '请输入联系方式',
                contactNum: '请输入正确格式联系方式'
            },
            address: {required: '请输入收货地址'}
        },
        errorPlacement: function(error, element) {
            error.appendTo(element.parent().parent());
            error.addClass('col-md-9 col-md-offset-3');
        },
        wrapper: 'div'
    };

    /**
     * 商品详情 RowView
     */
    var OrderRowView = Backbone.View.extend({
        tagName: 'tr',
        events: {
            'change input[role="color"]': 'toggleInventory',
            'change input[role="size"]': 'addSize'
        },
        initialize: function(options) {
            this.$checkboxs = [];
            this.$inventory = this.$('#inventory');
            this.inventoryItemTmpl = template('inventoryItem');
            this.sizeItemTmpl = template('sizeItem');

            this.init();

        },
        init: function(){
            var _this = this;

            _.each(this.$('.checkbox-inline'), function(item, index){
                _this.$checkboxs.push(new form.CheckboxView({el: $(item)}));
            });
        },
        'toggleInventory': function(ev){
            var $this = $(ev.currentTarget),
                _this = this,
                color = $this.val();

            if($this.prop('checked')){
                if(this.$('#inventory-' + color).length < 1){
                    this.$inventory.append(this.inventoryItemTmpl({'colorCode': color, 'colorName': colorMap[color]}));
                }

                _.each(this.$('input[role="size"]'), function(item, index){
                    if($(item).prop('checked')){
                        var size = $(item).val();
                        _this.$('#inventory-' + color).find('.inventory-size').append(_this.sizeItemTmpl({'sizeCode': size}));
                    }
                });
            }else{
                if(this.$('#inventory-' + color).length > 0) {
                    this.$('#inventory-' + color).remove();
                }
            }
        },
        'addSize': function(ev){
            var $this = $(ev.currentTarget),
                _this = this,
                size = $this.val();
            // if(this.validator.element(this.$('input[name="color"]'))){
                if($this.prop('checked')){
                    var $inventory_size = this.$inventory.find('.inventory-size')
                    if($inventory_size.length > 0){
                        $inventory_size.append(this.sizeItemTmpl({'sizeCode': size}));
                    }
                }else{
                    if(this.$inventory.find('.inventory-' + size).length > 0) {
                        this.$inventory.find('.inventory-' + size).remove();
                    }
                }
            // }
        },
        render: function(){
            return this;
        }
    });

    /**
     * 商品详情 TableView
     */
    var OrderTableView = Backbone.View.extend({
        initialize: function(options) {
            new OrderRowView({
                el: this.$('tbody>tr')
            });
        }
    });

    /**
     * 商品列表 ListView
     */
    var OrderListView = Backbone.View.extend({});

    /**
     * 表单
     */
    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit',
            'click span[role="btn-search"]': 'searchProducts'
        },
        initialize: function(options){
            this.init();

            this.validator = this.$el.validate(validates);
        },
        init: function(){
            var _this = this;

            new OrderTableView({
                el: '#order-table'
            });
        },
        'searchProducts': function(ev){
            alert('搜索商品');

        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            if(this.validator.form()) {
                $.ajax({
                    url: G.contextPath + 'order/add',
                    data: _this.$el.serializeArray(),
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