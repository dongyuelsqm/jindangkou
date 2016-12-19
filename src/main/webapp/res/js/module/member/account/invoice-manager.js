/**
 * 发票开具
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $        = require('jquery'),
	    _        = require('underscore'),
	    Backbone = require('backbone'),
	    util     = require('util/util'),
	    form     = require('util/form'),
	    modalView= require('util/modal');
    
    var collectionControls = require('util/collection-controls'),
	    collectionExtension = require('util/collection-extension'),
	    TableView = collectionControls.TableView,
        TableRowView = collectionControls.TableRowView,
//	    PaginationView = collectionControls.PaginationView,
	    BaseItemSetting = collectionExtension.BaseItemSetting,
        CheckboxView = form.CheckboxView;
    
    require('jquery-validate');
    require('jquery-validate-add');

    var urls = {
    	'listUrl': ['./member/account/invoice-managerment!listCommon.action',
    	            './member/account/invoice-managerment!listVAT.action'],
        'saveUrl': ['./member/account/invoice-managerment!saveCommon.action',
                    './member/account/invoice-managerment!saveVAT.action'],
        'deleteUrl': ['./member/account/invoice-managerment!deleteCommon.action',
                      './member/account/invoice-managerment!deleteVAT.action'],
        'defualtUrl': ['./member/account/invoice-managerment!setDefaultCommon.action',
                       './member/account/invoice-managerment!setDefaultVAT.action'],
        'useUrl': ['./member/account/invoice-managerment!useCommon.action',
                   './member/account/invoice-managerment!useVAT.action']
    };

    var tmpls = ['invoice-common', 'invoice-VAT'],
    	template = util.template,
    	defualts = [{
    			invoiceType: 0,
	    		invoiceId: '',
	    		invoiceTitle: '',
	    		invoiceContent: '',
	    		address: '',
	    		contact: '',
	    		tel: ''
    		},{
    			invoiceType: 1,
    			invoiceId: '',
    			address: '',
    			contact: '',
    			tel: '',
    			campanyName: '',
    			campanyCode: '',
    			regAddr: '',
    			regTel: '',
    			regBank: '',
    			bankCode: ''
    		}],
    	validates = [{
    		rules: {
	    		invoiceTitle: {
	    			required: true,
	    			rangelength:[1, 64]
	    		},
//	    		invoiceContent: '',
	    		address: {
	    			required: true,
	    			rangelength:[1, 64]
	    		},
	    		contact: {
	    			required: true,
	    			rangelength:[1, 64]
	    		},
	    		tel: {
	    			required: true,
					rangelength:[11, 11],
					mobile: true
	    		}
			},
			messages: {
				invoiceTitle: {
					required: '请选择/输入发票抬头',
					rangelength: '输入不能超过64个字符'
				},
				address: {
					required: '请输入邮寄地址',
					rangelength: '输入不能超过64个字符'
				},
	    		contact: {
	    			required: '请输入接收人姓名',
	    			rangelength: '输入不能超过64个字符'
	    		},
	    		tel: {
	    			required: '请输入联系方式',
					rangelength: '请输入11位的手机号码',
					mobile: '请输入以13、14、15、17、18开头的电话号码'
	    		}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent().parent());
				error.addClass('col-md-10 col-md-offset-3');
			},
			wrapper: 'div'
    	},{
    		rules: {
    			address: {
    				required: true,
    				rangelength:[1, 64]
    			},
	    		contact: {
	    			required: true,
	    			rangelength:[1, 64]
	    		},
	    		tel: {
	    			required: true,
					rangelength:[11, 11],
					mobile: true,
	    		},
	    		campanyName: {
	    			required: true,
	    			rangelength:[1, 64]
	    		},
    			campanyCode: {
    				required: true,
    				rangelength:[1, 64],
    				code: true
    			},
    			regAddr: {
    				required: true,
    				rangelength:[1, 64]
    			},
    			regTel: {
	    			required: true,
	    			contactNum: true
	    		},
    			regBank: {
    				required: true,
    				rangelength:[1, 64]
    			},
    			bankCode: {
    				required: true,
    				rangelength:[1, 64],
    				code: true
    			}
			},
			messages: {
				address: {
					required: '请输入邮寄地址',
					rangelength: '输入不能超过64个字符'
				},
	    		contact: {
	    			required: '请输入接收人姓名',
	    			rangelength: '输入不能超过64个字符'
	    		},
	    		tel: {
	    			required: '请输入联系方式',
	    			rangelength: '请输入11位的手机号码',
	    			mobile: '请输入以13、14、15、17、18开头的电话号码'
	    		},
	    		campanyName: {
	    			required: '请输入公司名称',
	    			rangelength: '输入不能超过64个字符'
	    		},
    			campanyCode: {
    				required: '请输入纳税人识别码',
    				rangelength: '输入不能超过64个字符',
    				code: '只能含有数字和英文字符 '
    			},
    			regAddr: {
    				required: '请输入注册地址',
    				rangelength: '输入不能超过64个字符'
    			},
    			regTel: {
    				required: '请输入注册电话号码',
    				contactNum: '请输入正确格式电话号码'
				},
    			regBank: {
    				required: '请输入开户银行',
    				rangelength: '输入不能超过64个字符'
    			},
    			bankCode: {
    				required: '请输入银行账户',
    				rangelength: '输入不能超过64个字符',
    				code: '只能含有数字和英文字符 '
    			}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent().parent());
				error.addClass('col-md-10 col-md-offset-3');
			},
			wrapper: 'div'
    	}],
    	model = new Backbone.Model(defualts[parseInt($('#invoice-type').val())]); //form的model;
    
    /**
     * form表单
     */
	var formView = Backbone.View.extend({
		tagName: 'form',
     	className: 'form',
     	events: {
     		'change input[name="title"]': 'otherInvoiceTitle'
     	},
     	initialize: function(options){
     		this.model = options.model;
     		this.template = options._template;
     		this.listenTo(this.model, 'change', this.render);
        },
        render: function(){
        	this.invoiceType= this.model.get('invoiceType');
        	
        	this.$el.html(this.template(this.model.toJSON()));
        	
        	_.each(this.$('input[type=radio]'), function(item, index){
                form.radiobox($(item));
            });
        	
        	this.$title_wrapper = this.$('#invoice-title-wrapper');
        	this.initTitle = this.model.get('invoiceTitle') === '' ? '个人' : this.model.get('invoiceTitle');
        	
        	if(this.initTitle === '个人'){
        		this.$title_wrapper.hide();
        		this.$('input[value="0"]').triggerHandler('select');
        	}else{
        		this.$title_wrapper.show();
        		this.$('input[value="1"]').triggerHandler('select');
        	}
        	
        	this.validator = this.$el.validate(validates[this.invoiceType]);
        	this.delegateEvents();
            return this;
        },
        setTemplate: function (template) {
            this.template = template;
        },
        setModel: function (model) {
            this.model = model;
        },
        otherInvoiceTitle: function(ev){
        	var $this = $(ev.currentTarget),
        		val = $this.val(),
        		title = '';
        	
        	if(val === '1'){
        		this.$title_wrapper.show();
        		title = this.initTitle === '个人' ? '其他' : this.initTitle;
        	}else{
        		this.$title_wrapper.hide();
        		title = '个人';
        	}
        	this.$('input[name="invoiceTitle"]').val(title);
        	this.validator.resetForm();
        }
	});
     
    /**
     * modal 模态框
     */
    var modal = modalView.extend({
    	events:{
    		'click #btn-submit': 'submit'
    	},
    	submit: function(ev){
    		var $this = $(ev.currentTarget);
    		
            if(this.form.$el.valid()){
            	var _this = this,
            		array = _this.form.$el.serializeArray(),
            		param = {};
            	
            	_.each(array, function(item, index){
            		if(item.name !== 'title'){
            			param[item.name] = item.value;
            		}
            	});
            	
	            $.ajax({
	                url: urls.saveUrl[_this.form.invoiceType],
	                data: param,
	                type: 'post',
	                dataType: 'json',
	                beforeSend: function(){
                        $this.prop('disabled', true);
                    },
                    complete: function(){
                        $this.prop('disabled', false);
                    },
	                success: function(rsp){
	                	if(rsp.successSign){
	                		alert('保存成功', function(){
	                			baseView.table.update();
	                			_this.$el.modal('hide');
	                		});
	                	}else{
	                		alert(rsp.errorMessage || '认证发生错误，请稍后再试');
	                	}
	                },
	                error: function(e){
	                	alert('操作失败，请稍后再试');
	                }
	            });
            }
    	}
    });
    
    /**
     * 扩展row
     */
    var invoiceRowView = TableRowView.extend({
    	events: {
            'click .update': 'update',
            'click .defualt': 'setDefualt',
            'click .delete': '_delete',
            'click .use': 'setUse'
        },
        initialize: function (options) {
        	this._super_initialize(options);
            this.invoiceType = baseView.invoiceType;
        },
        update: function(ev){
        	var $this = this.$(ev.currentTarget);
        	baseView.model.set(this.model.toJSON(), {silent: true}).trigger('change');
        	
        	this.modal = new modal({
            	id: 'invoice-manager-modal',
        		context: {
	        		template: 'invoiceManagerModal',
	        		contentTmpl: baseView.form.$el
        		}
        	});
            this.modal.bind(baseView.form);
        },
        setDefualt: function(ev){
        	var $this = this.$(ev.currentTarget),
        		_this = this;
        	$.ajax({
        		url: urls.defualtUrl[this.invoiceType],
        		data: {
        			invoiceId: _this.model.get('invoiceId')
        		},
        		type: 'post',
        		dataType: 'json',
        		success: function(rsp){
        			if(rsp.successSign){
        				alert('设置成功！', function(){
        					_this.model.trigger('update');
        				});
        			}else{
        				alert(rsp.errorMessage || '设置失败！');
        			}
        		},
        		error: function(xhr){
        			alert('操作失败，请稍后再试！');
        		}
        	});
        },
        _delete: function(ev){
        	var $this = this.$(ev.currentTarget),
    			_this = this;
        	
        	alert('确定要删除该条发票信息?', function(){
	        	$.ajax({
	        		url: urls.deleteUrl[_this.invoiceType],
	        		data: {
	        			invoiceId: _this.model.get('invoiceId')
	        		},
	        		type: 'post',
	        		dataType: 'json',
	        		success: function(rsp){
	        			if(rsp.successSign){
	        				alert('删除成功！', function(){
	        					_this.model.trigger('update');
	        				});
	        			}else{
	        				alert(rsp.errorMessage || '删除失败！');
	        			}
	        		},
	        		error: function(xhr){
	        			alert('操作失败，请稍后再试！');
	        		}
	        	});
        	}, true);
        },
        setUse: function(ev){
        	var $this = this.$(ev.currentTarget),
    			_this = this;
//        	$.ajax({
//        		url: urls.useUrl[this.invoiceType],
//        		data: {
//        			invoiceId: _this.model.get('invoiceId')
//        		},
//        		type: 'post',
//        		dataType: 'json',
//        		success: function(rsp){
//        			if(rsp.successSign){
//        				alert('设置成功！', function(){
//        					_this.model.trigger('update');
//        				});
//        			}else{
//        				alert(rsp.errorMessage || '设置失败！');
//        			}
//        		},
//        		error: function(xhr){
//        			alert('操作失败，请稍后再试！');
//        		}
//        	});
        	location.href = '#member/account/invoice.action?invoiceId=' + _this.model.get('invoiceId') + '&invoiceType=' + _this.invoiceType; 
        }
    });
    
    var invoiceTableView =  Backbone.View.extend({
        initialize: function (options) {
            this.table = new TableView(options.table);
        },
        update: function(){
        	this.table.update();
        }
    }); 
    
    var InvoiceManagerView = Backbone.View.extend({
        events: {
            'change input[name="invoiceType"]': 'toggelInvoiceType',
            'click #add-invoice': 'showInvoiceModal'
        },
        initialize: function(){
        	_.each(this.$('input[type=radio]'), function(item, index){
        		form.radiobox($(item));
        	});
        	
        	this.invoiceType = parseInt(this.$('#invoice-type').val());  //初始化
        	
        	this.model = model;
//        	this.listenTo(this.model, 'change', this.showInvoiceModal);
        	
        	this.initList();
        	this.initForm();
        },
        initList: function(){
        	
        	var table = new invoiceTableView({
                el: '#table-wrapper',
                table: {
                    el: '#invoice-table',
                    store: {
                        url: urls.listUrl[this.invoiceType],
//                        pagination: {
//                            enable: true,
//                            extractPage: function (res) { return res.page; }
//                        },
                        extractResult: function (res) {
                            return res.result;
                        }
                    },
                    row: {
                        template: template('invoiceRow-' + this.invoiceType),
                        defaultSetting: BaseItemSetting
                    },
                    rowView: invoiceRowView,
                    thead: template('invoiceThead-' + this.invoiceType),
                    sync: true,
                    param: {
                    	invoiceType: this.invoiceType
                    }
                }
        	});
        	
        	this.table = table.table;
        },
        initForm: function(){
        	if(this.form){
        		this.form.remove();
        	}
        	this.form = new formView({
        		_template: template(tmpls[this.invoiceType]),
        		model: this.model
        	});
        },
        queryList: function(){
        	var tmpl_thead = template('invoiceThead-' + this.invoiceType);
        	this.table.$('thead').html(tmpl_thead());
        	this.table.itemOption.template = template('invoiceRow-' + this.invoiceType);
        	this.table.data.url = urls.listUrl[this.invoiceType];
        	this.table.store.query({
        		invoiceType: this.invoiceType
        	});
        },
        queryForm: function(){
        	this.form.setTemplate(template(tmpls[this.invoiceType]));
        	this.model.clear({silent: true});
//        	this.model.set(_.extend({invoiceType: this.invoiceType}, defualts[this.invoiceType]));
//        	this.form.setModel(this.model);
        },
        toggelInvoiceType: function(ev){
        	var $this = this.$(ev.currentTarget),
        		_this = this;
        	
        	this.invoiceType = parseInt($this.val());
        	this.queryList();
//        	this.queryForm();
        	this.initForm();
        },
        showInvoiceModal: function(ev) {
        	if(ev && ev.currentTarget){
        		var $this = this.$(ev.currentTarget);
        	}

        	this.model.set(_.extend({}, defualts[this.invoiceType]), {silent: true}).trigger('change');
            this.modal = new modal({
            	id: 'invoice-manager-modal',
        		context: {
	        		template: 'invoiceManagerModal',
	        		contentTmpl: this.form.$el
        		}
        	});
            this.modal.bind(this.form);
        }
    });

    var baseView = {};
	function run () {
		baseView = new InvoiceManagerView({
            el: '#invoice-managerment-content'
        });
	}

    module.exports = {
        run: run
    };
});
