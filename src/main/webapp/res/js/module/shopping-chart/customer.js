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

    var validates = {
        rules: {
            userName: {require: true},
            userMobile: {
                require: true,
                mobile: true
            },
            qq: {require: true},
            userEmail: {
                require: true,
                email: true
            },
            shopName: {require: true},
            // pictures: {require: true},
            address: {require: true},
            telephone: {
                require: true,
                contactNum: true
            }
        },
        messages: {
            userName: {require: '请输入用户名'},
            userMobile: {
                require: '请输入手机号码',
                mobile: '请输入正确格式的手机号码'
            },
            qq: {require: '请选择qq号码'},
            userEmail: {
                require: '请输入常用邮箱',
                mobile: '请输入正确格式的电子邮箱'
            },
            shopName: {require: '请输入店铺名称'},
            // pictures: {require: true},
            address: {require: '请输入发货地址'},
            telephone: {
                require: '请输入服务电话',
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
        },
        initialize: function(opstions){
            this.$checkboxs = [];

            this.initImgUpload();
        },
        initImgUpload: function(){
            var _this = this, uploader = {};
            this.uploader_img = new plupload.Uploader({
                browse_button: document.getElementById('btn-img'),
                url: 'file/upload',
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
                    _this.$('input[name="pictures"]').val(finalName);
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
        'uploadImage': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;

            this.$('#btn-img').trigger('click');
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            console.log(_this.$el.serializeArray());
            $.ajax({
                url: G.contextPath + 'customer/add',
                data: _this.$el.serializeArray(),
                type: 'post',
                dateType: 'json',
                success: function(rsp){
                    console.log(rsp);
                }
            });
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