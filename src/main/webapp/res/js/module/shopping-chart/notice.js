/**
 * 金档口 - 店铺公告
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
        TableRowView = collecttionControls.TableRowView,
        PaginationView = collectionControls.PaginationView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var urls = {
        list: G.contextPath + 'notification/list',
        add: G.contextPath + 'website/notification/add',
        delete: G.contextPath + 'website/notification/remove'
    };

    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit'
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            if(_this.$('textarea').val() === '') {
                alert('请输入公告内容');
                return false;
            }

            $.ajax({
                url: urls.add,
                data: _this.$el.serializeArray(),
                type: 'post',
                dateType: 'json',
                success: function (rsp) {
                    if(rsp.success){
                        alert('发布公告成功！', function(){
                            _this.$('textarea').val('');
                        });
                    }
                }
            });
        }
    });

    var NoticeTableRowView = TableRowView.extend({
        events:{
            'click a[data-do="delete"]': 'delete'
        },
        'delete': function(ev){
            var _this = this;
            alert('是否删除公告？', function(){
                $.ajax({
                    url: urls.delete,
                    data: 'id=' + _this.model.get('id'),
                    type: 'post',
                    dateType: 'json',
                    success: function (rsp) {
                        if(rsp.success){
                            alert('删除公告成功！', function(){
                                //更新列表
                                _this.parentView.update;
                            });
                        }
                    }
                });
            }, true)
        }
    });

    var NoticeListView = Backbone.View.extend({
        events: {},
        initialize: function(options){
            new TableView({
                el: '#notice-table',
                store: {
                    url: urls.list,
                    pagination: {
                        enable: true
                    }
                },
                row: {
                    template: util.template('notice-row'),
                    defaultSetting: BaseItemSetting
                },
                rowView: NoticeTableRowView,
                sync: true,
                idAttribute: 'noticeId'
            });
            new PaginationView({
                el: '#pagination'
            }).bind(this.table);
        }
    });

    function run(){
        new FormView({
            el: '#notice-form'
        });

        new NoticeListView({
            el: '#table-wrapper'
        });
    }

    return {
        run: run
    };
});