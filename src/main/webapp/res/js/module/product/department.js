/**
 * 金档口 - 整理分类
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
        CheckableListView = collectionExtension.CheckableListView,
        CheckableItemView = collectionExtension.CheckableItemView;

    var CheckboxView = form.CheckboxView;

    var urls = {
        list: G.contextPath + 'department/list',
        delete: G.contextPath + '',
        save: G.contextPath + '',
        add: G.contextPath + ''
    };

    var ItemView = CheckableItemView.extend({
        'check': function(ev){
            this._super_invoke('check', [ev]);
            var $this = $(ev.currentTarget);
            this.$checkbox.getStatus() ? $this.parent().parent().addClass('active') : $this.parent().parent().removeClass('active');
        }
    });

    var DepartmentView = Backbone.View.extend({
        events: {
            'click [data-do="add"]': 'addDepartment',
            'change input.checkbox-all': 'checkAll',
            'click a[data-do="delete"]': 'batchDelete',
            'click a[data-do="edit"]': 'edit',
            'click buton[data-do="save"]': 'save'
        },
        initialize: function(options){
            this.initList();
        },
        initList: function(){
            this.list = new CheckableListView({
                el: '#departments',
                store: {
                    url: urls.list
                },
                item: {
                    template: util.template('departmentItem')
                },
                itemView: ItemView,
                sync: true
            });

            this.list.$checkboxAll = new CheckboxView({
                el: this.$('.checkbox-all').parent()
            });
        },
        'addDepartment': function(ev){
            // this.list.addItem({
            //     'name': $(ev.currentTarget).prev().val(),
            //     'id': ''
            // });
            var _this = this,
                name = $(ev.currentTarget).prev().val();
            if(name === ''){
                alert('请输入类型！');
                return false;
            }
            $.ajax({
                url: urls.add,
                data: {name: name},
                type: 'post',
                dataType: 'json',
                success: function(rsp){
                    _this.list.update();
                }
            });
        },
        'checkAll': function(ev){
            this.list.checkAll(ev);
        },
        'batchDelete': function(ev){
            var _this = this;
            if(this.list.checkedList.length < 1){
                alert('请选择要删除的一项！');
                return false;
            }
            $.ajax({
                url: urls.delete,
                data: this.list.getCheckedModels(),
                type: 'post',
                dataType: 'json',
                success: function(rsp){
                    _this.list.update();
                }
            });
        },
        'edit': function(ev){
            var _this = this;
            if(this.list.checkedList.length < 1){
                alert('请选择要编辑的一项！');
                return false;
            }

            _.each(this.$('#departments').find('input[type=checkbox]'), function(item, index){
                if($(item).prop('checked')) {
                    $(item).parent().prev().prop('readonly', false);
                }
                $(item).prop('readonly', true);
            });

            _this.$('#title').hide();
            _this.$('#edit-title').show();
            this.$('button[data-do="save"]').show();
        },
        'save': function(ev){
            var _this = this,
                param = [];
            // param = 遍历输入框的值
            $.ajax({
                url: urls.save,
                data: param,
                type: 'post',
                dataType: 'json',
                success: function(rsp){
                    // _.each(_this.$('#departments').find('li'), function(item, index){
                    //     $(item).find('input[type=checkbox]').prop('readonly', true);
                    //     $(item).find('input[type=text]').prop('readonly', false);
                    // });
                    //
                    // _this.$('#title').show();
                    // _this.$('#edit-title').hide();
                    // _this.$('button[data-do="save"]').hide();
                }
            });
        }
    });

    var run = function(){
        new DepartmentView({el: '#department-wrapper'});
    }

    return {
        run: run
    };
});