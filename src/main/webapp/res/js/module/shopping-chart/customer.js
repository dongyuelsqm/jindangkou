/**
 * 金档口 - 商户信息
 * cailuwei<>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        form = require('util/form'),
        util = require('util/util');

    require('plupload');
    require('jquery-validate');
    require('jquery-validate-add');

    var template = util.template,
        SelectView = form.SelectView,
        cityMap = require('module/default/cityMap');

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

    var urls = {
        add: G.contextPath + 'website/storekeeperinfo/add',
        upload: G.contextPath + 'file/upload'
    };

    var validates = {
        rules: {
            username: {required: true},
            phone: {
                required: true,
                mobile: true
            },
            QQ: {required: true},
            email: {
                required: true,
                email: true
            },
            storeName: {required: true},
            // pictures: {required: true},
            // address: {required: true},
            province: {required: true},
            city: {required: true},
            district: {required: true},
            detail: {
                required: true,
                maxlength: 200
            },
            tel: {
                required: true,
                contactNum: true
            }
        },
        messages: {
            username: {required: '请输入用户名'},
            phone: {
                required: '请输入手机号码',
                mobile: '请输入正确格式的手机号码'
            },
            QQ: {required: '请选择qq号码'},
            email: {
                required: '请输入常用邮箱',
                mobile: '请输入正确格式的电子邮箱'
            },
            storeName: {required: '请输入店铺名称'},
            // pictures: {required: true},
            // address: {required: '请输入发货地址'},
            province: {required: '请选择省份'},
            city: {required: '请选择城市'},
            district: {required: '请选择街道'},
            detail: {
                required: '请输入详细公司地址',
                maxlength: '请输入200位以内字符'
            },
            tel: {
                required: '请输入服务电话',
                contactNum: '请输入正确格式的服务电话'
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo(element.parent().parent());
            error.addClass('col-md-9 col-md-offset-3');
        },
        wrapper: 'div'
    };

    var FormView = Backbone.View.extend({
        events: {
            'click button[role="btn-submit"]': 'submit',
            'click img[role="upload-img"]': 'uploadImage',
            'change #province_select>li>a': 'selectProvince',
            'change #city_select>li>a': 'selectCity',
            'change #district_select>li>a': 'selectDistrict'
        },
        initialize: function(opstions){
            var _this = this;
            _this.$checkboxs = [];
            _this.select_map = {};

            _this.initImgUpload();

            _.each(this.$('.dropdown'), function(item, index){
                _this.select_map[item.id] = new SelectView({
                    el: $(item),
                    init_data: true
                });
            });
            this.initProvince();

            this.validator = this.$el.validate(validates);
        },
        initImgUpload: function(){
            var _this = this, uploader = {};
            this.uploader_img = new plupload.Uploader({
                browse_button: document.getElementById('btn-img'),
                url: urls.upload,
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
                    prevent_duplicates : false
                }
            });

            this.uploader_img.bind('FilesAdded', function (_uploader, files) {
                if (files.length > 0) {
                    _uploader.setOption('multipart_params', {
                        'uploadType': 'Cell'
                    });
                    _uploader.start();
                } else {
                    alert('请选择文件');
                }
            });

            this.uploader_img.bind('FileUploaded', function (_uploader, file, response) {
                if (response.status !== 200) {
                    alert('发生错误，文件上传失败！');
                    return;
                }

                var result = $.parseJSON(response.response);

                if(result.successSign){
                    var finalName = result.url;
                    _this.$('input[name="picture"]').val(finalName);
                    _this.$('img.upload-default').attr('src', G.contextPath + 'upload/' + result.detail[0]);
                }else{
                    alert(result.errorMessage || '上传出错');
                }
            });

            this.uploader_img.bind('Error', function (_uploader, errObject) {
                if(errObject.code){
                    alert(upload_error[errObject.code]);
                }
            });

            this.uploader_img.init();
        },
        initProvince: function(){
            var _this = this;
            _this.selectType = 'province';
            _this.selectData = cityMap.provinceMap || [];
            _this.setSelectData();
        },
        setSelectData: function(){
            var _this = this;
            if(_this.selectData.length > 0){
                var html = '';

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
        'uploadImage': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;

            this.$('#btn-img').trigger('click');
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            console.log(_this.$el.serializeArray());
            if(this.validator.form()) {
                var param = _this.$el.serializeObject(),
                    obj = {'openID': 'sss'};

                _.each(param, function (value, key) {
                    switch (key) {
                        case 'city':
                            obj['cityName'] = value;
                            delete param[key];
                            break;
                        case 'detail':
                            obj['expAreaName'] = value;
                            delete param[key];
                            break;
                        case 'district':
                            obj['address'] = value;
                            delete param[key];
                            break;
                        case 'province':
                            obj['provinceName'] = value;
                            delete param[key];
                            break;
                        default:
                            break;
                    }
                });
                param.address = JSON.stringify(obj);
                param.picture = 'picture';
                $.ajax({
                    url: urls.add,
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
            el: '#customer-form'
        });
    }

    return {
        run: run
    };
});