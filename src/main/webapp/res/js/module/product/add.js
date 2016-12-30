/**
 * 金档口 - 添加/编辑产品
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
    var html_img = '<img src="${contextPath}/res/css/base/img/upload-default.png" alt="" class="upload-default"/>/' +
                    '<input type="hidden" value="" name="pictures"/>';
    var validates = {
            rules: {
                // code: {require: true},
                name: {require: true},
                price: {require: true},
                department: {require: true},
                minimum: {require: true},
                // pictures: {require: true},
                // videos: {require: true},
                descriptive: {require: true}
            },
            messages: {
                name: {require: '请输入商品名称'},
                price: {require: '请输入售价'},
                department: {require: '请选择商品分类'},
                minimum: {require: '请输入起批件数'},
                // pictures: {require: true},
                // videos: {require: true},
                descriptive: {require: '请输入商品描述'}
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
            'click video[role="upload-video"]': 'uploadVideo'
        },
        initialize: function(opstions){
            this.$checkboxs = [];
            this.uploaders_store = [];

            this.init();
            this.initImgUpload();
            this.initVideoUpload();
        },
        init: function(){
            var _this = this;
            _.each(this.$('.checkbox-inline'), function(item, index){
                _this.$checkboxs.push(new form.CheckboxView({el: $(item)}));
            });
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
        initVideoUpload: function(){
            var _this = this, uploader = {};
            this.uploader_video = new plupload.Uploader({
                browse_button: document.getElementById('btn-video'),
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
                        title: 'Video files',
                        extensions: 'mp4,ogv'
                    }],
                    prevent_duplicates : false
                }
            });

            this.uploader_video.bind('FilesAdded', function (_uploader, files) {
                if (files.length > 0) {
                    _uploader.setOption('multipart_params', {
                        'uploadType': 'Cell'
                    });
                    _uploader.start();
                } else {
                    alert('请选择文件');
                }
            });

            this.uploader_video.bind('FileUploaded', function (_uploader, file, response) {
                if (response.status !== 200) {
                    alert('发生错误，文件上传失败！');
                    return;
                }

                var result = $.parseJSON(response.response);

                if(result.successSign){
                    var finalName = result.url;

                }else{
                    alert(result.errorMessage || '上传出错');
                }
            });

            this.uploader_video.bind('Error', function (_uploader, errObject) {
                if(errObject.code){
                    alert(upload_error[errObject.code]);
                }
            });

            this.uploader_video.init();
        },
        'uploadImage': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;

            this.$('#btn-img').trigger('click');
        },
        'uploadVideo': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;

            this.$('#btn-video').trigger('click');
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            console.log(_this.$el.serializeArray());
            $.ajax({
                url: G.contextPath + 'product/add',
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
            el: '#product-form'
        });
    }

    return {
        run: run
    };
});