/**
 * 个人中心
 * cailuwei<cailuwei@chinamobile.com>
 */
'use strict';
define(function(require, exports, module) {
	var $ = require('jquery'),
	    _ = require('underscore'),
	    Backbone = require('backbone'),
	    util = require('util/util'),
	    modalView = require('util/modal');
	
	require('plugins/faust-cplus/swfobject');
    
    var collectionControls = require('util/collection-controls'),
        collectionExtension = require('util/collection-extension'),
        CheckableTableView  = collectionExtension.CheckableTableView,
        CheckableRowView = collectionExtension.CheckableRowView,
        PaginationView = collectionControls.PaginationView,
        SearchBoxView = collectionControls.SearchBoxView,
        BaseItemSetting = collectionExtension.BaseItemSetting;
    
    var urls = {
	    	deleteUrl: './member/personal-center!delete.action',
	    	updateStatusUrl: './member/personal-center!updateStatus.action',
	    	uploadServlet: '/uploadServlet',
	    	listUrl: './member/personal-center!message.action',
	    	saveUrl: './member/personal-center!save.action'
	    },
	    title_type = {
	    	upload: '保存头像',
	    	update: '消息已读'
	    },
	    button_type = {
	    	upload: '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button><button type="button" class="btn btn-submit" id="submit">保存头像</button>',
	    	update: '<button type="button" class="btn btn-default btn-submit" data-dismiss="modal" id="readed">消息已读</button>'
	    };

	
	/**
	 * 获取时间字符串
	 */
	function getTime(){
		var now = new Date(),
		 	hours = now.getHours();
		if(hours < 8){
			$('#time').html('早晨好！');
		}else if(8 <= hours && hours < 11){
			$('#time').html('上午好！');
		}else if(11 <= hours && hours < 13){
			$('#time').html('中午好！');
		}else if(13 <= hours && hours < 19){
			$('#time').html('下午好！');
		}else if(19 <= hours && hours < 23 || hours === 0){
			$('#time').html('晚上好！');
		}
	}
	
	/**
     * modal 模态框
     */
    var modal = modalView.extend({
    	events: {
    		'click #submit' : 'uploadHeader',
    		'click #readed': 'changeMessageStatus'
    	},
    	changeMessageStatus: function(e){
    		
    		var _this = this,
    			model = this.store.model.toJSON();
    		
    		$.ajax({
                data: {
    				'ids[]': model.messageId
	    		},
                dataType: 'json',
                type: 'post',
                url: urls.updateStatusUrl,
                success: function (rsp) {
                    if (rsp.successSign) {
                    	_this.store.model.trigger('update');
                    }
                },
                error: function () {
                	alert('发生错误，请稍后再试！');
                }
            });
    		
    	},
    	uploadHeader: function(ev){
    		var _this = this,
    			avatarPath = _this.$('.userAvatar').val();
    		
    		$.ajax({
                data: {
    				'userAvatar': avatarPath
	    		},
                dataType: 'json',
                type: 'post',
                url: urls.saveUrl,
                success: function (rsp) {
                    if (rsp.successSign) {
                    	alert('头像保存成功！', function(){
                    		_this.$el.modal('hide');
                    		location.reload();
                    	});
                    }else{
                    	alert(rsp.errorMessage || '头像保存失败！');
                    }
                },
                error: function () {
                	alert('发生错误，请稍后再试！');
                }
            });
    		
//    		swfobject.getObjectById('FaustCplus').jscall_updateAvatar();
    	},
    	hideModal: function(e){
        	this.remove();
//        	location.reload();
        }
    });
    
    /**
     * form表单
     */
     var formView = Backbone.View.extend({
     	tagName: 'form',
     	className: 'form user-header-form',
     	id: 'user-header-form',
     	events: {
     		'click .sys-recommendation': 'selectAvatar'
     	},
     	initialize: function(options){
     		this.template = options.template || {};
     		this.store = {};
     		
     		this.render();
        },
        render: function(){      	
        	this.$el.html(this.template());
        	return this;
        },
        initUpload: function(){

            var flashvars = {
            	'jsfunc': 'uploadevent',
                'pid': '75642723',
                'pSize': '350|350|68|68',
                'uploadSrc': true,
                'showBrow': true,
//                'showCame': true,
                'uploadUrl': G.contextPath + urls.uploadServlet + '?size=68p68p&upload=headPhoto'
            };       

            var params = {
                menu: 'false',
                scale: 'noScale',
                allowFullscreen: 'true',
                allowScriptAccess: 'always',
                wmode: 'transparent',
                bgcolor: '#ffffff'
            };
            
            swfobject.embedSWF(G.contextPath + '/res/js/plugins/faust-cplus/FaustCplus.swf', 
        		'alt-content', 
        		'565', 
        		'500', 
        		'9.0.0', 
        		G.contextPath + '/res/js/plugins/faust-cplus/expressInstall.swf', 
        		flashvars, 
        		params, 
        		{
	                id: 'FaustCplus'
	            }
            );
        },
        selectAvatar: function(ev){
        	var $this = $(ev.currentTarget),
        		avatarPath = $this.attr('src');
        	
        	this.$('.userAvatar').val(avatarPath);
        	$this.parent().find('.active').removeClass('active');
        	$this.addClass('active');
        }
    });
	
    /**
     * 扩展行
     */
	var personalMsgRowView = CheckableRowView.extend({
		_events: {
            'click .message-content': 'showMsgDetail'
        },
        initialize: function (options) {
            
             _.extend(this.events, this._events);
            this._super_initialize(options);
            this.detail = util.template('msgDetail');
            
            this.store = {};
        },
		render: function () {
			this._super_invoke('render');
			this.store.model = this.model;
            var model = this.model.toJSON();
            if(model.messageStatus === 0){
            	this.$el.addClass('bold');
            }
		},
		showMsgDetail: function(e){
			this.modal = new modal({
            	id: 'detail-modal',
        		context: {
	        		template: 'userModal',
	        		contentTmpl: this.detail(this.model.toJSON())
        		},
        		title: title_type.update,
        		button: button_type.update
        	});
        	this.modal.bind(this);
		}
	});
	
	 /**
	  * 扩展searchbox
	  */	
    var searchView = SearchBoxView.extend({
        _events: {
            'click .update-status-all': 'updateStatusAll',
            'click .update-status': 'updateStatus',
            'click .delete-mul': 'batchDelete'
        },
        initialize: function (options) {
            
             _.extend(this.events, this._events);
            this._super_initialize(options);
            
        },
		updateStatus: function(e){
			e.preventDefault();
			var ids = this.getIdAttribute();
            this._updateStatus(ids);
		},
		updateStatusAll: function(e){
			e.preventDefault();
            if(!this.table.$checkboxAll.prop('checked')){
            	this.table.$checkboxAll.triggerHandler('check');
            	this.table.$checkboxAll.trigger('change');
            }

			var ids = this.getIdAttribute();
            this._updateStatus(ids);		
		},
		_updateStatus: function (ids) {
            if (ids.length === 0) {
                alert('请先选择要标记的项！');
                return;
            }
            var _this = this, 
                param = {};
            param['ids'] = ids;
            alert('确定要标记吗？', function () {
                $.ajax({
                    data: param,
                    dataType: 'json',
                    type: 'post',
                    url: urls.updateStatusUrl,
                    success: function (rsp) {
                        if (rsp.successSign) {
                            alert('标记成功!', function () {
                                _this.update();
                            });
                        }
                        else {
                            alert(rsp.errorMessage || '发生错误！');
                        }
                    },
                    error: function () {
                    	alert('发生错误，请稍后再试 ！');
                    }
                });
            }, true);
		},
        batchDelete: function (e) {
        	e.preventDefault();
			var ids = this.getIdAttribute();
            this._delete(ids);
		},
		_delete: function (ids) {
            if (ids.length === 0) {
                alert('请先选择要删除的项！');
                return;
            }
            var _this = this,
            	param = {};
            param[_this.idAttribute + 's'] = ids;
            alert('确定要删除吗？', function () {
                $.ajax({
                    data: param,
                    dataType: 'json',
                    type: 'post',
                    url: urls.deleteUrl,
                    beforeSend: function(){
                    	//$('#cover').show();
                    },
                    success: function (rsp) {
                        if (rsp.successSign) {
                            alert('删除成功!', function () {
                                _this.update();
                            });
                        }
                        else {
                            alert(rsp.errorMessage || '发生错误,删除失败！');
                        }
                    },
                    error: function () {
                    	alert('发生错误，请稍后再试！');
                    }
                });
            }, true);
        },
        getIdAttribute: function(){
            var _this = this,
                ids = _.map(this.table.getCheckedModels(), function (item) {
                return item.messageId;
            });
            
            return ids;
        }
	});
    
    var personalMsgView =  Backbone.View.extend({
        initialize: function (option) {
            this.table = new CheckableTableView(option.table);
            new PaginationView(option.pagination).bind(this.table);
            new searchView(option.search).bind(this.table);
        }
    }); 
    
    var personalCenterView = Backbone.View.extend({
    	events: {
    		'click .change-user-head': 'changeUserHead',
    		'mouseout .change-user-head': 'hideHeadMask',
    		'mouseover .user-head': 'showHeadMask'
    	},
    	initialize: function(options){
    		
    		//初始化我的消息列表
    		new personalMsgView({
                el: '#table-wrapper',
                table: {
                    el: '#personal-center-table',
                    store: {
                        url: urls.listUrl,
                        pagination: {
                            enable: true,
                            extractPage: function (res) { return res.page; }
                        },
                        extractResult: function (res) {
                        	//显示系统消息
                        	
                        	$('#sys-message-count').html((res.systemMessageCount && res.systemMessageCount > 0) ? res.systemMessageCount : 0);
                        	$('#check-message-count').html((res.checkMessageCount && res.checkMessageCount > 0) ? res.checkMessageCount : 0);
                            return res.page.result;
                        }
                    },
                    row: {
                        template: util.template('persenMessage-row'),
                        defaultSetting: BaseItemSetting
                    },
                    rowView: personalMsgRowView,
                    sync: true,
                    param: {
                        type: 1
                    },
                    idAttribute: 'messageId'
                },
                pagination: {
                    el: '#pagination'
                },
                search: {
                    el: '#search-box'
                }
            });
    	},
    	changeUserHead: function(e){
    		e.preventDefault();
    		this.form = new formView({
        		template: util.template('userHeaderUpload')
        	});
    		
    		this.modal = new modal({
            	id: 'detail-modal',
        		context: {
	        		template: 'userModal',
	        		contentTmpl: this.form.$el
        		},
        		button: button_type.upload,
        		title: title_type.upload
        	});
    		this.modal.bind(this.form);
    		this.form.initUpload();
    	},
    	showHeadMask: function(e){
    		this.$('.change-user-head').show();
    	},
    	hideHeadMask: function(e){
    		this.$('.change-user-head').hide();
    	}
    });
	
	function run(){
		getTime();
		new personalCenterView({
			el: '#content'
		});
	}
	 
	return {
		run: run
	}
});