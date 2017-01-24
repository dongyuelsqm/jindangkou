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

    var template = util.template,
        SelectView = form.SelectView,
        CheckboxView = form.CheckboxView,
        colorMap = require('module/default/colors').colorMap;

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
    var html_img = '<img src="" alt="" class="upload-default" />';
    var validates = {
            rules: {
                name: {required: true},
                price: {
                    required: true,
                    number: true
                },
                department: {required: true},
                minimum: {
                    required: true,
                    number: true
                },
                color: {required: true},
                postal: {required: true},
                descriptive: {required: true}
                //size: {required: true},
                // pictures: {required: true},
                // videos: {required: true},
                //postal: {required: true},
                //quantity: {required: true}
            },
            messages: {
                name: {required: '请输入商品名称'},
                price: {
                    required: '请输入售价',
                    number: '请输入数字'
                },
                department: {required: '请选择商品分类'},
                minimum: {
                    required: '请输入起批件数',
                    number: '请输入数字'
                },
                color: {required: '请选择颜色'},
                postal: {required: '请选择运费模版'},
                descriptive: {required: '请输入商品描述'}
                //size: {required: true},
                // pictures: {required: true},
                // videos: {required: true},
                //quantity: {required: true}
            },
            errorPlacement: function(error, element) {
                error.appendTo(element.parent().parent());
                error.addClass('col-md-9 col-md-offset-3');
            },
            wrapper: 'div'
        };

    var FormView = Backbone.View.extend({
        events: {
            'click img[role="upload-img"]': 'uploadImage',
            'click [role="upload-video"]': 'uploadVideo',
            // 'click img[role="upload-video"]': 'uploadVideo',
            'change input[role="color"]': 'toggleInventory',
            'change input[role="size"]': 'addSize',
            'click button[role="btn-submit"]': 'submit'
        },
        initialize: function(options){
            this.init();
            this.initImgUpload();
            this.initVideoUpload();

            this.validator = this.$el.validate(validates);
        },
        init: function(){
            var _this = this;

            this.cacheEls();
            this.initColors();
            this.initSizes();
            this.initDepartment();

            new SelectView({el: this.$('#postal-dropdown')});

            if(this.$('#video').attr('src') !== ''){
                this.$('#video').show();
            }else{
                this.$('#video-default').show();
            }
        },
        cacheEls: function(){
            this.$checkboxs = [];
            this.uploaders_store = [];
            this.videos = [];
            this.pictures = [];

            this.$inventory = this.$('#inventory');
            this.$imgDefault = this.$('img.upload-default[role="upload-img"]');
            this.$videoDefault = this.$('img.upload-default[role="upload-video"]');
            this.$imgWrapper = this.$imgDefault.parent();
            this.$videoWrapper = this.$videoDefault.parent();
            this.$colorsWrapper = this.$('#color-wrapper');
            this.$sizesWrapper = this.$('#size-wrapper');

            this.inventoryItemTmpl = template('inventoryItem');
            this.sizeItemTmpl = template('sizeItem');
            this.colorTmpl = template('color');
            this.sizeTmpl = template('size');
        },
        initColors: function(){
            var _this = this;
            $.ajax({
                url: G.contextPath + 'website/color',
                type: 'get',
                success: function(rsp){
                    console.log(rsp);
                    _this.$colorsWrapper.empty();
                    _.each(rsp, function(item, index){
                        _this.$colorsWrapper.append(_this.colorTmpl(item));
                    });

                    _.each(_this.$colorsWrapper.find('input[type=checkbox]'), function(item, index){
                        _this.$checkboxs.push(new CheckboxView({el: $(item).parent()}));
                    });
                }
            });
        },
        initSizes: function(){
            var _this = this;
            $.ajax({
                url: G.contextPath + 'website/size',
                type: 'get',
                success: function(rsp){
                    console.log(rsp);
                    _this.$sizesWrapper.empty();
                    _.each(rsp, function(item, index){
                        _this.$sizesWrapper.append(_this.sizeTmpl(item));
                    });

                    _.each(_this.$sizesWrapper.find('input[type=checkbox]'), function(item, index){
                        _this.$checkboxs.push(new CheckboxView({el: $(item).parent()}));
                    });
                }
            });
        },
        initDepartment: function(){
            new SelectView({
                el: this.$('#department-dropdown'),
                template: '<li><a href="javascript:" data-val="{{id}}" >{{name}}</a></li>',
                store:{
                    url: G.contextPath + 'department/list',
                    extractResult: function(rsp){
                        return rsp.objs;
                    }
                }
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

                    if(result.success){
                        // var finalName = result.url;
                        console.log(result.detail[0]);
                        var url = G.contextPath + 'upload/' + result.detail[0];
                        if(_this.$img){
                            _this.$img.attr('src', url);
                        }else{
                            if(_this.$imgWrapper.find('img').length > 4 && _this.$imgDefault.css('display') === 'block'){
                                _this.$imgDefault.hide();
                            }
                            _this.$imgDefault.before('<img src="' + url + '" alt="" role="upload-img" class="upload-default"/>/');
                        }
                        _this.pictures.push(result.detail[0]);
                    }else{
                        alert(result.detail || '上传出错');
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

                if(result.success){
                    // this.$('#video').show();
                    // this.$('#video-default').hide();
                    var url = G.contextPath + 'upload/' + result.detail[0];
                    if(this.$video){
                        _this.$video.attr('src', url);
                    }else{
                        if(_this.$videoDefault.css('display') === 'block'){
                            _this.$videoDefault.hide();
                            this.$('#video').attr('src', url).show();
                        }
                    }

                    _this.videos.push(result.detail[0]);
                }else{
                    alert(result.detail || '上传出错');
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
            this.$img = $this.hasClass('upload-default') ? false : $this;
            this.$('#btn-img').trigger('click');
        },
        'uploadVideo': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;
            this.$video = $this.hasClass('upload-default') ? false : $this;
            this.$('#btn-video').trigger('click');
        },
        'toggleInventory': function(ev){
            var $this = $(ev.currentTarget),
                _this = this,
                color = $this.val(),
                colorName = $this.data('name');

            if($this.prop('checked')){
                if(this.$('#inventory-' + color).length < 1){
                    this.$inventory.append(this.inventoryItemTmpl({'colorCode': color, 'colorName': colorName}));

                    _.each(this.$('input[role="size"]'), function(item, index){
                        if($(item).prop('checked')){
                            var size = $(item).val(),
                                sizeName = $(item).data('name');
                            _this.$('#inventory-' + color).find('.inventory-size').append(_this.sizeItemTmpl({'sizeName': sizeName, 'sizeCode': size}));
                        }
                    });
                }
            }else{
                if(this.$('#inventory-' + color).length > 0) {
                    this.$('#inventory-' + color).remove();
                }
            }
        },
        'addSize': function(ev){
            var $this = $(ev.currentTarget),
                _this = this,
                size = $this.val(),
                name = 'xl';
            // if(this.validator.element(this.$('input[name="color"]'))){
                if($this.prop('checked')){
                    var $inventory_size = this.$inventory.find('.inventory-size')
                    if($inventory_size.length > 0){
                        $inventory_size.append(this.sizeItemTmpl({'sizeName': name, 'sizeCode': size}));
                    }
                }else{
                    if(this.$inventory.find('.inventory-' + size).length > 0) {
                        this.$inventory.find('.inventory-' + size).remove();
                    }
                }
            // }
        },
        getQuantity: function(){
            /*
             * 获取库存数据
             */
            var _this = this,
                $inventorys = this.$('ul.inventory>li'),
                array = [];
            _.each($inventorys, function(item, index){
                $(item).find('input[role="quantity"]').each(function(i, input){
                    if($(input).val() !== ''){
                        array.push('{color: "' +  $(item).data('color') + '",size: "' +  $(input).data('size') + '",quantity: "' +  $(input).val() + '"}');
                    }
                });
            });
            return array;
        },
        'submit': function(ev){
            var $this = $(ev.currentTarget),
                _this = this;

            if(this.validator.form()) {
                var param = this.$el.serializeObject();
                param.pictures = '["dddd.gif","ffff.gif"]';
                param.videos = '["dddd.mp4","ffff.mp4"]';
                param.quantity = '[' + this.getQuantity().join(',')  + ']';
                // param.pictures = '[' + this.pictures.join(',') + ']';
                // param.videos =  '[' + this.videos.join(',') + ']';
                param.label = '["1"]';

                $.ajax({
                    url: G.contextPath + 'website/product/add',
                    data: param,
                    success: function (rsp) {
                        if(rsp.success){
                            alert('商品添加成功！');
                        }
                    }
                });
            }
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