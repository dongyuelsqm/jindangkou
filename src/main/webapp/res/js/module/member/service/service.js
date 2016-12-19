/**
 * 个人中心 - 服务中心
 * cailuwei<cailuwei@chinamobile.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        util = require('util/util');

    var collectionControls = require('util/collection-controls'),
        collectionExtension = require('util/collection-extension'),
        TableView = collectionControls.TableView,
        TableRowView = collectionControls.TableRowView,
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;
    
    /**
    * 列表所需其他参数
    */
    var other_param = {
        'orderBy': 2,
        'searchType': 0
    };

    var ServiceTableView = Backbone.View.extend({
        initialize: function (option) {
            this.table = new TableView(option.table);
            this.pagination = new PaginationView(option.pagination).bind(this.table);
            this.search = new SearchBoxView(option.search).bind(this.table);
        }
    });
    
    var serviceRowView = TableRowView.extend({
        events: {
            'click .update-store': 'updateStore',
            'click .service-detail': 'detail'
		},
        updateStore: function(ev){
        	var $this = $(ev.currentTarget),
        		storeId = this.model.get('storeId'),
        		storeType = this.model.get('storeType');
        	
        	alert('确定要取消收藏吗？', function () {
                $.ajax({
                    url: './member/service/service!updateStore.action',
                    data: {
                    	storeId: storeId,
                    	storeType: storeType
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                        if(rsp.successSign){
                            alert('取消收藏成功！', function(){
                            	ServiceTable.table.update();
                            });
                        }else{
                            alert('操作失败！');
                        }
                    },
                    error: function(e){
                        alert('发生错误，请稍后再试');
                    }
                });
            }, true);
        },
        detail: function(ev){
        	var $this = $(ev.currentTarget);
        	
        	location.href = '#' + this.model.get('url') + '?' + this.model.get('requestParam') + '=' + this.model.get('storeId');
        }
    });

    var ServiceTable = {};
    module.exports = {
        run: function () {
        	ServiceTable = new ServiceTableView({
                el: '#table-wrapper',
                table: {
                    el: '#service-table',
                    store: {
                        url: 'member/service/service!list.action',
                        pagination: {
                            enable: true,
                            extractPage: function (res) { return res.page; }
                        },
                        extractResult: function (res) {
                            return res.page.result;
                        }
                    },
                    row: {
                        template: util.template('services-row'),
                        defaultSetting: BaseItemSetting
                    },
                    rowView: serviceRowView,
                    sync: true,
                    param: other_param
                },
                pagination: {
                    el: '#pagination'
                },
                search: {
                    el: '#search-box'
                }
            });
        }
    };
});
