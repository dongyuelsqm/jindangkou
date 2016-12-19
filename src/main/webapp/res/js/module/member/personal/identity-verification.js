/**
 * 实名认证
 * Qichen.Zheng <qichen.zheng@pactera.com>
 * cailuwei<cailuwei@chinamobile.com>
 */
define(function (require, exports, module) {
    var $        = require('jquery'),
        _        = require('underscore'),
        Backbone = require('backbone'),
        util     = require('util/util'),
        modalView= require('util/modal'),
        form     = require('util/form');
    
    var CheckboxView = form.CheckboxView;
    
    require('plupload');
    require('jquery-validate');
    require('jquery-validate-add');

    var urls = {
        personal: './member/personal/identity-verification!personalIdentity.action',
        company: './member/personal/identity-verification!companyIdentity.action',
        uploadServlet: './uploadServlet',
        provinceUrl: './member/personal/identity-verification!getProvince.action',
        cityUrl: './member/personal/identity-verification!getCity.action',
        streetUrl: './member/personal/identity-verification!getStreet.action'
    };
    
    var upload_error = {
    		'-100': '发生通用错误',
    		'-200': 'http网络错误',
    		'-300': '磁盘读写错误',
    		'-400': '存在安全问题',
    		'-500': '初始化错误',
    		'-600': '选择文件过大',
    		'-601': '选择文件类型不符合要求',
    		'-602': '选择文件重复',
    		'-700': '图片格式错误',
    		'-701': '内存错误',
    		'-702': '文件大小超过了plupload所能处理的最大值'
    	};

    var verifyTypes = {
            personal: '个人开发者',
            company: '企业用户'
        },
        defualts = {
    		personal: {
//    			userStatus: 0,
				realName: '',
				certificateType: '',
				certificateNo: '',
				certificatePathZm: '',
				certificatePathFm: '',
				personalWebsite: '',
				personalWebsiteIntro: '',
				personalWebsiteUrl: ''
    		},
            company: {
//            	userStatus: 0,
            	companyName: '',
            	websiteUrl: '',
				companyAddrEx: '',
				provinceId: '',
				province: '',
				cityId: '',
				city: '',
				streetId: '',
				street: '',
				companyAddrDetail: '',
				newCompanyAddr: '',
				companyIntro: '',
				licenseNo: '',
				licensePath: '',
				orgCode: '',
				orgPath: '',
				taxRegistrationNo: '',
				taxRegistrationPath: '',
				contactMan: '',
				contactWay: ''
            }
    	},
        validates = {
    		personal: {
    			rules: {
    				realName: {
    					required: true,
    					maxlength: 40,
    					fullname: true
    				},
    				certificateType: {required: true},
    				certificateNo: {
    					required: true,
    					IDnum: true,
    					checkIDnum: true
    				},
    				certificatePathZm: {required: true},
    				certificatePathFm: {required: true},
    				personalWebsite: {maxlength: 50},
    				personalWebsiteIntro: {maxlength: 500},
    				personalWebsiteUrl: {maxlength: 500}
    			},
    			messages: {
    				realName: {
    					required: '请输入真实姓名',
    					maxlength: '请输入40位以内字符',
	                	username: '必须是汉字字符或英文字符'
    				},
    				certificateType: {required: '请选择证件类型'},
    				certificateNo: {
    					required: '请输入证件号码',
    				},
    				certificatePathZm: {required: '请上传证件照片'},
    				certificatePathFm: {required: '请上传证件照片'},
    				personalWebsite: {maxlength: '请输入50位以内字符'},
    				personalWebsiteIntro: {maxlength: '请输入500位以内字符'},
    				personalWebsiteUrl: {maxlength: '请输入500位以内字符'}
    			},
    			errorPlacement: function(error, element) {
    				error.appendTo(element.parent().parent());
    				error.addClass('col-md-9 col-md-offset-3');
    			},
    			wrapper: 'div'
    		},
    		company: {
    			rules: {
    				companyName: {
    					required: true,
    					maxlength: 50
    				},
    				websiteUrl: {
    					required: true,
    					maxlength: 100
    				},
//    				companyAddrEx: {required: true},
    				provinceId: {required: true},
    				cityId: {required: true},
    				streetId: {required: true},
    				companyAddrDetail: {required: true},
    				companyIntro: {
    					required: true,
    					maxlength: 500
    				},
    				licenseNo: {
    					required: true,
    					digits: true,
    					rangelength: [15, 15],
    				},
    				licensePath: {required: true},
    				orgCode: {
    					required: true,
    					rangelength: [9, 9],
    					codeUppercase: true
    				},
    				orgPath: {required: true},
    				taxRegistrationNo: {
    					required: true,
    					rangelength: [15, 15],
    					codeUppercase: true
    				},
    				taxRegistrationPath: {required: true},
    				contactMan: {
    					required: true,
    					maxlength: 40,
    					fullname: true
    				},
    				contactWay: {
    					required: true,
    					contactNum: true
    				}
    			},
    			messages: {
    				companyName: {
    					required: '请输入公司名称',
    					maxlength: '请输入50位以内字符'
    				},
    				websiteUrl: {
    					required: '请输入官网地址',
    					maxlength: '请输入100位以内字符'
    				},
//    				companyAddrEx: {required: '请选择公司所在地'},
    				provinceId: {required: '请选择省份'},
    				cityId: {required: '请选择城市'},
    				streetId: {required: '请选择街道'},
    				companyAddrDetail: {required: '请输入详细公司地址'},
    				companyIntro: {
    					required: '请输入公司简介',
    					maxlength: '请输入500位以内字符'
    				},
    				licenseNo: {
    					required: '请输入营业执照编号',
    					digits: '必须为整数',
    					rangelength: '只能是15位字符'
    				},
    				licensePath: {required: '请上传营业执照'},
    				orgCode: {
    					required: '请输入组织机构代码',
    					rangelength: '只能是9位字符'
    				},
    				orgPath: {required: '请上传组织机构照片'},
    				taxRegistrationNo: {
    					required: '请输入税务登记编号',
    					rangelength: '只能是15位字符'
    				},
    				taxRegistrationPath: {required: '请上传税务登记照片'},
    				contactMan: {
    					required: '请输入企业联系人',
    					maxlength: '请输入40位以内字符',
	                	username: '必须是汉字字符或英文字符'
	                },
    				contactWay: {
    					required: '请输入联系方式',
    					contactNum: '请输入正确格式联系方式'
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
    		}
    	};
    
    jQuery.validator.addMethod('IDnum', function(value, element) {
        var patt = /^(\d{18})|(\d{17}[Xx])$/;
        return this.optional(element) || patt.test(value);
    }, '请输入18位正确格式的身份证号码');
    
    jQuery.validator.addMethod('checkIDnum', function(value, element) {
        var vals = value.split(''),
        	last = parseInt(vals[17]) ? parseInt(vals[17]) : 'x',
        	pow = 0,
//        	remainder = 0, //也可以用，qrcode[remainder % 11]
        	temp = 0,
        	qrcode = [1, 0, 'x', 9, 8, 7, 6, 5, 4, 3, 2];
        
        $.each(vals, function(index, item){
        	if(index < 17){
	        	temp = parseInt(item);
	        	pow += temp * Math.pow(2, 17 - index);
//	        	remainder += temp * (Math.pow(2, 17 - index) % 11); 
        	}
        });
        
        if(last === qrcode[pow % 11]){
        	return true;
        }else{
        	return false;
        }
        
    }, '请输入正确的身份证号码');
    
    /**
     * form表单
     */
	var formView = Backbone.View.extend({
		tagName: 'form',
		className: 'form',
     	events: {
     		'change #provinceId': 'selectProvince',
     		'change #cityId': 'selectCity',
     		'change #streetId': 'selectStreet',
     		'change #certificateType': 'changeCertificateType',
     		'click #btn-submit': 'submit'
     	},
     	initialize: function(options){
     		this.verify_type = options.context.verify_type || '';
     		this.template = options.template || {};
     		this.model = options.model ||{};
     		this.id = options.id || '';
     		this.render();
     		
     		if(G.userStatus === '1' || G.userStatus === '4'){
     			this.initUpload();
     		}
     		
     		if(this.$('#provinceId').length > 0){
     			this.initProvince();
     		}
        },
        render: function(){
        	$('#identity-vertification-container').html(this.$el);
        	this.$el.html(this.template(this.model.toJSON()));
        	_.each(this.$('input[type=checkbox]'), function(item, index){
        		new CheckboxView({
            		el: this.$(item).parent()
            	});
            });
        	
        	_.each(this.$('.img-block'), function(item, index){
                if($(item).children().attr('src') !== ''){
                	$(item).show();
                }
            });
        	
        	this.$el.validate(validates[this.verify_type]);
        	this.delegateEvents();
            return this;
        },
        initUpload: function(){
        	var _this = this, uploader = {};
        	this.uploaders_store = [];
        	this.$upload = this.$('.btn-upload');
        	_.each(this.$upload, function(item, index){
        		
        		uploader = new plupload.Uploader({
        			browse_button: item,
        			url: urls.uploadServlet + '?upload=identityVerfication',
        			flash_swf_url: G.contextPath + '/res/js/plugins/plupload/Moxie.swf',
        			silverlight_xap_url : G.contextPath + '/res/js/plugins/plupload/Moxie.xap',
        			multi_selection: false,
//        			multipart_params: {
//        				upload: 'identityVerfication'
//        			},
        			filters: {
        				max_file_size: '2mb',
        				mime_types: [{
        					title: 'Image files',
        					extensions: 'jpg,png'
        				}],
        				prevent_duplicates : true
        			}
        		});
        		
        		uploader.bind('FilesAdded', function (_uploader, files) {
                    if (files.length > 0) {
                        _uploader.setOption('multipart_params', {
                             'uploadType': 'Cell'
                        });
                        _uploader.start();
                    } else {
                        alert('请选择文件');
                    }
                });
        		
                uploader.bind('FileUploaded', function (_uploader, file, response) {
                    if (response.status !== 200) {
                        alert('发生错误，文件上传失败！');
                        return;
                    }

                    var result = $.parseJSON(response.response);

                    if(result.successSign){
                        var finalName = result.url;
                        _this.$(item).parent().parent().find('input[type=text]').val(finalName);
                        _this.$(item).parent().parent().find('.img-block').show();
                        _this.$(item).parent().parent().find('img').attr('src', finalName);
                    }else{
                        alert(result.errorMessage || '上传出错');
                    }
                });
                
                uploader.bind('Error', function (_uploader, errObject) {
                    if(errObject.code){
                 	   alert(upload_error[errObject.code]);
                    }
                 });
                
                uploader.init();
                
        		_this.uploaders_store.push(uploader);
        	});
        },
        initProvince: function(){
        	var _this = this;
        	
        	$.ajax({
                url: urls.provinceUrl,
                type: 'post',
                dataType: 'json',
                success: function(rsp){
                	if(rsp.successSign){
                		_this.selectType = 'province';
                		_this.selectData = rsp.provinceList || [];
                		_this.setSelectData();
                	}else{
                		alert(rsp.errorMessage || '操作失败');
                	}
                },
                error: function(e){
                	alert('发生错误，请稍后再试');
                }
            });
        },
        selectProvince: function(ev){
        	var _this = this,
	    		$this = {}, 
	    		provinceId = 0;
	    	
	    	if(typeof ev === 'object'){
	    		$this = $(ev.currentTarget),
	    		provinceId = $this.val();
	    	}else{
	    		$this = _this.$('#provinceId');
	    		provinceId = ev;
	    	}
        	
        	if(provinceId !== ''){
        		this.$('#cityId').prop('disabled', false);
        		this.$('#province').val($this.find("option:selected").text());
        		
        		$.ajax({
                    url: urls.cityUrl,
                    data: {
                    	provinceId: provinceId
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                    	if(rsp.successSign){
                    		_this.selectType = 'city';
                    		_this.selectData = rsp.cityList || [];
                    		_this.setSelectData();
                    		_this.selectCity(_this.cityId || _this.selectData[0].id);
                    	}else{
                    		alert(rsp.errorMessage || '操作失败');
                    	}
                    },
                    error: function(e){
                    	alert('发生错误，请稍后再试');
                    }
                });
        	}
        },
        selectCity: function(ev){
        	var _this = this,
        		$this = {}, 
        		cityId = 0;
        	
        	this.cityId = null; 
        	if(typeof ev === 'object'){
        		$this = $(ev.currentTarget),
        		cityId = $this.val();
        	}else{
        		$this = _this.$('#cityId');
        		cityId = ev;
        	}
        	
        	if(cityId !== ''){
        		this.$('#streetId').prop('disabled', false);
        		this.$('#city').val($this.find("option:selected").text());
        		
        		$.ajax({
                    url: urls.streetUrl,
                    data: {
                    	cityId: cityId
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function(rsp){
                    	if(rsp.successSign){
                    		_this.selectType = 'street';
                    		_this.selectData = rsp.streetList || [];
                    		_this.setSelectData();
                    		_this.selectStreet(_this.streetId || _this.selectData[0].id);
                    	}else{
                    		alert(rsp.errorMessage || '操作失败');
                    	}
                    },
                    error: function(e){
                    	alert('发生错误，请稍后再试');
                    }
                });
        	}
        },
        selectStreet: function(ev){
        	var $this = {},
        		streetId = 0;
        	
        	this.streetId = null;
        	if(typeof ev === 'object'){
        		$this = $(ev.currentTarget),
        		streetId = $this.val();
        	}else{
        		$this = this.$('#streetId');
        		streetId = ev;
        	}
        	
        	if(streetId !== ''){
        		this.$('#companyAddr-detail').prop('disabled', false);
        		this.$('#street').val($this.find("option:selected").text());
        	}
        },
        setSelectData: function(){
        	var _this = this;
        	if(_this.selectData.length > 0){
        		var html = '';
        		if(_this.selectType === 'province'){
        			html = '<option selected value="">省份</option>';
        		}
        		
        		var val = _this.$('#' + _this.selectType).val(),
        			id = 0;
        		_.each(_this.selectData, function(item, index){
        			html += '<option value="' + item.id + '" ' + (val === item.name ? 'selected' : '') + '>' + item.name + '</option>';
        			if(_this.selectType === 'province' && val === item.name){
        				_this.provinceId = item.id;
        			}if(_this.selectType === 'city' && val === item.name){
        				_this.cityId = item.id;
        			}
        			if(_this.selectType === 'street' && val === item.name){
        				_this.streetId = item.id;
        			}
        		});
        		_this.$('#' + _this.selectType + 'Id').html(html);
        		
        		if(_this.selectType === 'province' && val && val !== ''){
        			_this.selectProvince(_this.provinceId);  			
        		}
        	}
        },
        changeCertificateType: function(ev){
        	var $this = $(ev.currentTarget),
        		val = $this.val();
        	
        	if(val === '0'){
        		this.$('#certificateNo').rules("remove", 'rangelength code');
        		this.$('#certificateNo').rules("add", {IDnum: true, checkIDnum: true});
        	}else if(val === '1'){
        		this.$('#certificateNo').rules("remove", 'IDnum checkIDnum');
        		this.$('#certificateNo').rules("add", {rangelength: [9,9], code: true, messages: {rangelength: '只能是9位字符'}})
        	}
        },
        submit: function(ev){
    		
    		if(this.$el.valid()){
    			if(!this.$('#isRead').prop('checked')){
        			alert('请阅读认证协议');
        			return;
        		}
    			
	            var param = this.$el.serializeArray(),
	            	verify_type = this.verify_type;
	            
	            param.push({
	            	name: 'companyAddr',
	            	value: this.$('#province').val() + '@' + this.$('#city').val() + '@' + this.$('#street').val()
	            });
	            
	            $.ajax({
	                url: urls[verify_type],
	                data: param,
	                type: 'post',
	                dataType: 'json',
	                success: function(rsp){
	                	if(rsp.successSign){
	                		alert('信息提交成功，我们会尽快审核，请耐心等待！', function(){
	                			location.reload();
	                		});
	                	}else{
	                		alert(rsp.errorMessage || '操作失败');
	                	}
	                },
	                error: function(e){
	                	alert('发生错误，请稍后再试');
	                }
	            });
    		}
    	}
    });

    var IdentityVerificationView = Backbone.View.extend({
        events: {
            'change .radiobox': 'showVerify'
        },
        initialize: function (option) {
//            this.templates = this.initTemplates(templates);
            
            _.each(this.$('.radiobox'), function(item, index){
            	form.radiobox($(item));
            });
            
            this.verify_type = G.userType === '3' ? 'company' : 'personal';
            this.store = new Backbone.Model(_.extend(defualts[this.verify_type]), G.identityData);
            this.showVerify();
        },
        initTemplates: function (templates_map) {
            var template_renders = {};
            for (var name in templates_map) {
                template_renders[name] = util.template(templates_map[name]);
            }
            return template_renders;
        },
        initForm: function(){
        	if(this.form){
        		this.form.remove();
        		this.store.clear({silent: true});
        	}
        	
        	this.store.set(_.extend(defualts[this.verify_type], G.identityData));
        	this.form = new formView({
        		template: util.template('verify-' + this.verify_type),
        		model: this.store,
        		id: this.verify_type + '-form',
        		context: {
        			verify_type: this.verify_type
        		}
        	});
        },
        showVerify: function (ev) {
        	if(ev){
        		var $this = this.$(ev.currentTarget);
                this.verify_type = $this.data('type');
        	}
            
            this.initForm();
        }
    });

    function run () {
        //TODO
        new IdentityVerificationView({
            el: '#content'
        });
	}

    module.exports = {
        run: run
    };
});
