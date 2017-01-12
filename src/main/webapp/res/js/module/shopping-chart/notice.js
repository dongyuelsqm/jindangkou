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
        PaginationView = collectionControls.PaginationView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit'
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            if(this.$('textarea').val() === '') {
                $.ajax({
                    url: G.contextPath + 'customer/add',
                    data: _this.$el.serializeArray(),
                    type: 'post',
                    dateType: 'json',
                    success: function (rsp) {
                        console.log(rsp);
                    }
                });
            }
        }
    });

    var NoticeListView = Backbone.View.extend({
        events: {},
        initialize: function(options){
            new TableView({
                el: '#notice-table',
                store: {
                    url: urls.listUrl,
                    pagination: {
                        enable: true
                    }
                },
                row: {
                    template: util.template('notice-row'),
                    defaultSetting: BaseItemSetting
                },
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