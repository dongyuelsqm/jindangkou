/**
 *     对话框组件 SeaJS模块 V0.6.1
 *     Date: 2015-06-29
 *     高英健 <gaoyingjian@chinamobile.com>
 */
'use strict';
define(function(require, exports, module) {

	var cmccDialog = function(config) {
		return this.constructor(config);
	};

	/**
	 * 构造器
	 * @param {Object} config
	 */
	cmccDialog.prototype.constructor = function(config) {
		var $this = this;
		if(config.type != 'tip' && config.type != 'note') {
			config.type = 'common';
		}
		if(!config.id) {
			do {
				config.id = 'dialog' + Math.floor(Math.random() * 100000);
			} while(cmccDialog.list[config.id]);
		} else {
			if(cmccDialog.list[config.id] != undefined) {
				this.show();
				return this;
			}
		}
		//tip模式下未设置指向元素则自动转为common模式，note模式下找不到跟随元素则转为跟随页面
		if(config.type == 'common' && config.follow && $(config.follow).length > 0) {
			config.type = 'tip';
		} else if(config.type == 'tip' && ( !config.follow || $(config.follow).length == 0) ) {
			config.type = 'common';
		} else if(config.type == 'note' && $(config.follow).length == 0) {
			config.follow = window;
		}
		config = this.config = $.extend({}, cmccDialog._default[config.type], config);
		//读取模板
		var type = config.type,
			dialogBox = $(cmccDialog._tpl[config.type]).appendTo('body'),
			fixed;
		//锁屏遮罩，仅common模式下可用
		if(type == 'common' && config.lock) {
			$('<div class="dialog-lock"></div>').insertBefore(dialogBox)
				.css('zIndex', config.zIndex)
				.on('dblclick', function() {
					$this.close();
				});
		}
		//定位方式，tip模式下始终绝对定位
		if(type == 'common') {
			fixed = config.fixed ? 'fixed' : 'absolute';
		} else if(type == 'note') {
			var follow = $(config.follow);
			fixed = ( follow[0] != document && follow.css('position') == 'fixed' ) ? 'fixed' : 'absolute';
		}
		dialogBox
			.attr('id', config.id)
			.css({
				position: fixed,
				zIndex: config.zIndex
			});
		//标题，note模式下不允许使用标题
		if(config.title) {
			if(type == 'common') {
				dialogBox.find('.dialog-title')
					.html(config.title)
					.css({
						display: 'block',
						cursor: config.draggable ? 'move' : 'default'
					});
			} else if(type == 'tip') {
				dialogBox.find('.dialog-title')
					.html(config.title)
					.show();
			}
		}
		//内容
		//note模式，上下padding无效
		dialogBox.find('.dialog-content')
			.html(config.content)
			.css('padding', config.padding);
		//关闭按钮
		if(config.closeButton) {
			dialogBox.find('.dialog-close')
				.show()
				.on('click', function() {
					$this.close();
				});
		}
		//按钮
		if(config.button) {
			this._buttons();
		}
		//定位
		if(!config.hidden) {
			this.show();
			dialogBox.css({
				left: -9999,
				top: -9999
			})
		}
		this._position();
		//拖拽，仅common模式下可用
		if(type == 'common' && config.draggable) this._drag();
		//初始化完成后执行函数
		if(config.onload) {
			config.onload.apply(this);
		}
		//定时关闭
		if(!isNaN(config.time) && config.time > 0) {
			setTimeout(function() {
				$this.close();
			}, config.time * 1000);
		}

		return cmccDialog.list[config.id] = this;
	};

	/**
	 * 关闭浮动窗口
	 */
	cmccDialog.prototype.close = function() {
		var config = this.config,
			dialogBox = $('#' + config.id);
		if(config.unload && config.unload.apply(this) == false) return false;
		if(config.lock) dialogBox.prev().remove();
		dialogBox.remove();
		delete(cmccDialog.list[config.id]);
	};

	/**
	 * 显示浮动窗口
	 */
	cmccDialog.prototype.show = function() {
		var config = this.config,
			dialogBox = $('#' + config.id);
		config.hidden = false;
		if(config.lock) dialogBox.prev().show();
		dialogBox.show();
		return this;
	};

	/**
	 * 隐藏浮动窗口
	 */
	cmccDialog.prototype.hide = function() {
		var config = this.config,
			dialogBox = $('#' + config.id);
		config.hidden = true;
		if(config.lock) dialogBox.prev().hide();
		dialogBox.hide();
		return this;
	};

	/**
	 * 修改标题
	 * @param {String} value
	 */
	cmccDialog.prototype.title = function(value) {
		var config = this.config,
			dialogBox = $('#' + config.id);
		if(config.type == 'note') return false;
		config.title = value;
		if(config.type == 'common') {
			dialogBox.find('.dialog-title')
				.html(config.title)
				.css({
					display: config.title ? 'block' : 'none',
					cursor: config.draggable ? 'move' : 'default'
				});
		} else if(config.type == 'tip') {
			dialogBox.find('.dialog-title')
				.html(config.title)
				.css({
					display: config.title ? 'block' : 'none'
				});
		}
		return this;
	};

	/**
	 * 修改内容
	 * @param {String} value
	 */
	cmccDialog.prototype.content = function(value) {
		var config = this.config,
			dialogBox = $('#' + config.id);
		config.content = value;
		dialogBox.find('.dialog-content')
			.html(config.content);
		return this;
	};

	/**
	 * 跟随指定元素
	 * @param {HTMLElement} element
	 */
	cmccDialog.prototype.follow = function(element) {
		var config = this.config,
			follow = $(element);
		if(follow.length == 0) {
			if(config.type == 'note') {
				follow = $(window);
			} else {
				return false;
			}
		}
		config.follow = follow;
		if(config.type == 'common') {
			config.type = 'tip';
			this.close();
			this.constructor(config);
		}
		this._position();
		return this;
	};

	/**
	 * 修改内容尺寸
	 * @param {Number} width
	 * @param {Number} height
	 */
	cmccDialog.prototype.size = function(width, height) {
		if(width != 0) this.config.width = width;
		if(height != 0) this.config.height = height;
		if(width != 0 || height != 0) this._position(true);
		return this;
	};

	/**
	 * 修改位置
	 * @param {String} value
	 */
	cmccDialog.prototype.position = function(value) {
		var config = this.config;
		config.position = value;
		this._position();
		return this;
	};

	/**
	 * 开启锁屏遮罩
	 */
	cmccDialog.prototype.lock = function() {
		var $this = this,
			config = this.config,
			dialogBox = $('#' + config.id);
		if(config.type != 'common' || config.lock == true) return false;
		config.lock = true;
		$('<div class="dialog-lock"></div>').insertBefore(dialogBox)
			.css('zIndex', config.zIndex)
			.on('dblclick', function() {
				$this.close();
			}).show();
		return this;
	};

	/**
	 * 关闭锁屏遮罩
	 */
	cmccDialog.prototype.unlock = function() {
		var config = this.config,
			dialogBox = $('#' + config.id);
		if(config.type != 'common' || config.lock == false) return false;
		config.lock = false;
		dialogBox.prev().remove();
		return this;
	};

	/**
	 * 设置显示时间
	 * @param {Number} value
	 */
	cmccDialog.prototype.time = function(value) {
		var $this = this;
		if(!isNaN(value) && value > 0) {
			setTimeout(function() {
				$this.close();
			}, value * 1000);
		}
		return this;
	};

	/**
	 * 拖拽浮动窗口
	 */
	cmccDialog.prototype._drag = function() {
		var config = this.config,
			dialogBox = $('#' + config.id),
			dragBox = dialogBox;
		if(config.type != 'common') return false;
		if(config.title) dragBox = dialogBox.find('.dialog-title');
		dragBox.on('mousedown', function(e) {
			if (e.which != 1) return;
			e.preventDefault();
			var x = e.pageX,
				y = e.pageY,
				size = dialogBox.offset(),
				container = config.fixed ? $(window) : $(document),
				offsetLimit = {top: 0, left: 0},
				moveEvent = function(ev) {
					var top = ev.pageY - y + size.top,
						left = ev.pageX - x + size.left;
					if(config.fixed) {
						top -= $(document).scrollTop();
						left -= $(document).scrollLeft();
					}
					dialogBox.css({
						top: Math.min(offsetLimit.bottom, Math.max(top, offsetLimit.top)),
						left: Math.min(offsetLimit.right, Math.max(left, offsetLimit.left))
					});
					if(document.selection) document.clearSelection();
				};
			offsetLimit.right = offsetLimit.left + container.width() - dialogBox.width() - 1;
			offsetLimit.bottom = offsetLimit.top + container.height() - dialogBox.height();
			$(document).on({
				'mousemove': moveEvent,
				'mouseup': function upEvent() {
					$(document).off({
						'mousemove': moveEvent,
						'mouseup': upEvent
					});
				}
			});
		});
	};

	/**
	 * 显示按钮
	 */
	cmccDialog.prototype._buttons = function() {
		var $this = this,
			config = this.config,
			dialogBox = $('#' + config.id),
			buttons = dialogBox.find('.dialog-buttons'),
			button;
		$.each(config.button, function(key, value) {
			value = $.extend({}, cmccDialog._default.button, value);
			value.id = value.id || 'button' + Math.floor(Math.random() * 100000);
			if(config.type == 'note') {
				button = $('<a></a>');
				button.attr({
						id: value.id,
						href: value.href ? value.href : 'javascript:'
					}).html(value.value)
					.width(value.width);
				if(value.disabled) button.addClass('disabled');
				button.on('click', function() {
					if(value.disabled) return false;
					if(value.callback) {
						if(value.callback.apply($this) != false) $this.close();
					} else {
						$this.close();
					}
				});
			} else {
				button = $('<button></button>');
				button.attr('id', value.id)
					.addClass(value.cssClass)
					.html(value.value)
					.width(value.width);
				if(value.disabled) {
					button.prop('disabled', true);
				}
				button.on('click', function() {
					if(value.callback) {
						if(value.callback.apply($this) != false) $this.close();
					} else {
						$this.close();
					}
					if(value.href) window.location = value.href;
				});
			}
			button.appendTo(buttons);
		});
		buttons.show();
	};

	/**
	 * 对话框定位
	 * @param {Boolean} resize
	 */
	cmccDialog.prototype._position = function(resize) {
		var config = this.config,
			dialogBox = $('#' + config.id),
			dialogHeight, dialogWidth,
			follow, followOffset, followWidth, followHeight,
			position, arrow,
			left, top, radius;
		switch(config.type) {
		case 'tip':
			dialogHeight = dialogBox.height(config.height).width(config.width).outerHeight();
			dialogWidth = dialogBox.outerWidth();
			follow = $(config.follow);
			followOffset = follow.offset();
			followWidth = follow.outerWidth();
			followHeight = follow.outerHeight();
			position = config.position.split(' ');
			arrow = dialogBox.find('.dialog-arrow').attr('class', 'dialog-arrow');
			switch(position[0]) {
			case 'top':
				top = followOffset.top - dialogHeight - 11;
				arrow.eq(0).addClass('dialog-arrow-ta').end()
					.eq(1).addClass('dialog-arrow-tb');
				break;
			case 'bottom':
				top = followOffset.top + followHeight + 11;
				arrow.eq(0).addClass('dialog-arrow-ba').end()
					.eq(1).addClass('dialog-arrow-bb');
				break;
			case 'left':
				left = followOffset.left - dialogWidth - 11;
				arrow.eq(0).addClass('dialog-arrow-la').end()
					.eq(1).addClass('dialog-arrow-lb');
				break;
			case 'right':
				left = followOffset.left + followWidth + 11;
				arrow.eq(0).addClass('dialog-arrow-ra').end()
					.eq(1).addClass('dialog-arrow-rb');
				break;
			}
			arrow.removeAttr('style');
			switch(position[1]) {
			case 'top':
				arrow.css('top', 10);
				top = followOffset.top;
				break;
			case 'bottom':
				arrow.css('bottom', 10);
				top = followOffset.top + followHeight - dialogHeight;
				break;
			case 'left':
				arrow.css('left', 10);
				left = followOffset.left;
				break;
			case 'right':
				arrow.css('right', 10);
				left = followOffset.left + followWidth - dialogWidth;
				break;
			default:
				if(position[0] == 'left' || position[0] == 'right') {
					arrow.css('top', dialogHeight / 2 - 10);
					top = followOffset.top + followHeight / 2 - dialogHeight / 2;
				} else {
					arrow.css('left', dialogBox.width() / 2 - 10);
					left = followOffset.left + followWidth / 2 - dialogWidth / 2;
				}
			}
			dialogBox.css({
				left: left,
				top: top
			});
			break;
		case 'note':
			dialogHeight = dialogBox.height(config.height).width(config.width).outerHeight();
			dialogWidth = dialogBox.outerWidth();
			follow = $(config.follow);
			followOffset = (follow[0] == window || follow[0] == document) ? {left: 0, top: 0} : follow.offset();
			followWidth = follow.outerWidth();
			followHeight = follow.outerHeight();

			switch(config.position) {
			case 'outer top':
				top = followOffset.top + followHeight;
				if(top + dialogHeight > $(document).height()) {
					config.position = 'inner top';
				} else {
					radius = '0 0 5px 5px';
					break;
				}
			case 'inner top':
				top = followOffset.top;
				radius = '0 0 5px 5px';
				break;
			case 'outer bottom':
				top = followOffset.top - dialogHeight;
				if(top < 0) {
					config.position = 'inner bottom';
				} else {
					radius = '5px 5px 0 0';
					break;
				}
			case 'inner bottom':
				top = followOffset.top + followHeight - dialogHeight;
				radius = '5px 5px 0 0';
				break;
			}
			dialogBox.css({
				left: followOffset.left + followWidth / 2 - dialogWidth / 2 - (dialogBox.css('position') == 'fixed' ? $(document).scrollLeft() : 0),
				top: top - (dialogBox.css('position') == 'fixed' ? $(document).scrollTop() : 0),
				borderRadius: radius
			});
			break;
		case 'common':
			//计算整个对话框高度
			dialogHeight = config.height;
			if(dialogHeight != 'auto') {
				var contentBox = dialogBox.find('.dialog-content');
				contentBox.height(dialogHeight - parseInt(contentBox.css('paddingTop')) - parseInt(contentBox.css('paddingBottom')));
				if(config.title) {
					dialogHeight += dialogBox.find('.dialog-title').outerHeight();
				}
				if(config.button) {
					dialogHeight += dialogBox.find('.dialog-buttons').outerHeight();
				}
			}
			dialogHeight = dialogBox.find('.dialog').height(dialogHeight).width(config.width).end().outerHeight();
			dialogWidth = dialogBox.outerWidth();
			if($(window).width() == 0) {
				dialogBox.hide();
				setTimeout(function() {
					dialogBox.css({
						left: ($(window).width() - dialogWidth) / 2,
						top: ($(window).height() - dialogHeight) / 2
					}).show();
				}, 10);
			} else if(resize && config.draggable) {
				var container = config.fixed ? $(window) : $(document);
				dialogBox.css({
					left: Math.min(dialogBox.position().left, container.width() - dialogWidth),
					top: Math.min(dialogBox.position().top, container.height() - dialogHeight)
				});
			} else if(!resize || config.fixed) {
				dialogBox.css({
					left: ($(window).width() - dialogWidth) / 2 + (config.fixed ? 0 : $(document).scrollLeft()),
					top: ($(window).height() - dialogHeight) / 2 + (config.fixed ? 0 : $(document).scrollTop())
				});
			}
			break;
		}
	};

	//对话框模板
	cmccDialog._tpl = {
		common:
'<div class="dialog-box">' +
	'<div class="dialog">' +
		'<div class="dialog-title"></div>' +
		'<div class="dialog-content"></div>' +
		'<div class="dialog-buttons"></div>' +
		'<div class="dialog-close">×</div>' +
	'</div>' +
'</div>',
		tip:
'<div class="dialog-tip">' +
	'<span class="dialog-arrow"></span>' +
	'<span class="dialog-arrow"></span>' +
	'<div class="dialog-title"></div>' +
	'<div class="dialog-content"></div>' +
	'<div class="dialog-buttons"></div>' +
	'<div class="dialog-close">×</div>' +
'</div>',
		note:
'<div class="dialog-note">' +
	'<span class="dialog-content"></span>' +
	'<span class="dialog-buttons"></span>' +
	'<div class="dialog-close">×</div>' +
'</div>'
	};

	//默认参数
	cmccDialog._default = {
		common: {
			title: '',  //标题
			content: 'Loading...',  //内容
			button: null,  //自定义按钮
			width: 300,  //宽度
			height: 'auto',  //高度
			fixed: false,  //是否相对于浏览器窗口定位
			lock: false,  //锁屏遮罩
			padding: '8px 15px',  //内容的边缘留白
			zIndex: 1000,  //显示层级  //TODO: 自动叠加
			hidden: false,  //是否隐藏
			closeButton: true,  //是否显示关闭按钮
			draggable: false,  //是否允许拖拽
			onload: null,  //初始化完成后执行函数
			unload: null,  //关闭前执行函数
			time: 0  //指定时间后关闭，单位秒
		},
		tip: {
			position: 'right top',  //气泡的位置  //TODO: 放不下的时候自动换位置
			title: '',  //标题
			content: 'Loading...',  //内容
			button: null,  //自定义按钮
			width: 'auto',  //宽度
			height: 'auto',  //高度
			padding: '8px 28px 8px 15px',  //内容的边缘留白
			zIndex: 500,  //显示层级
			hidden: false,  //是否隐藏
			closeButton: true,  //是否显示关闭按钮
			onload: null,  //初始化完成后执行函数
			unload: null,  //关闭前执行函数
			time: 0  //指定时间后关闭，单位秒
		},
		note: {
			position: 'inner top',  //通知框的位置
			content: 'Loading...',  //内容
			button: null,  //自定义按钮
			width: 600,  //宽度
			height: 'auto',  //高度
			padding: '0 5px',  //内容的边缘留白
			zIndex: 1000,  //显示层级
			hidden: false,  //是否隐藏
			closeButton: true,  //是否显示关闭按钮
			onload: null,  //初始化完成后执行函数
			unload: null,  //关闭前执行函数
			time: 0  //指定时间后关闭，单位秒
		},
		button: {  //TODO: 按钮的操作
			value: '',  //按钮文字
			href: '',  //链接
			callback: null,  //回调函数,默认点击按钮后关闭对话框，return false即可禁止关闭
			width: 'auto',  //按钮宽度
			cssClass: '',  //附加的CSS
			disabled: false  //是否禁用
		}
	};

	//对话框列表
	cmccDialog.list = {};

	cmccDialog.get = function(id) {
		return cmccDialog.list[id];
	};

	$(window).on('resize', function() {
		$.each(cmccDialog.list, function(key, value) {
			value._position(true);
		});
	});

	return {
		cmccDialog: cmccDialog
	}

});