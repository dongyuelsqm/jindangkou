/**
 * 工具函数
 * jinyongcheng <>
 * cailuwei     <>
 */
'use strict';
define(function(require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone');
    
    var templateOptions = {
            evaluate: /<#([\s\S]+?)#>/g,
            interpolate: /\{\{\{([\s\S]+?)\}\}\}/g,
            escape: /\{\{([^\}]+?)\}\}(?!\})/g
        },
        emptyRender = _.template('');
    
    /**
     * template(id)
     *
     * 通过 id 获取一个模板，返回一个与之对应的模板函数
     *
     * @param {string} id 模板 id，函数自动添加前缀 “tmpl-” 去获取模板，如 id 为 submenu，则会去获取 tmpl-submenu 元素
     * @return {function} 模板渲染函数
     */
    function template(id) {
        var tmpl = $('#tmpl-' + id);
        return tmpl.length === 0 ? emptyRender : _.template(tmpl.html(), templateOptions);
    }

    /**
     * render(html)
     *
     * 获取一个html，返回一个与之对应的模板函数
     *
     * @param {string} html 模板
     * @return {function} 模板渲染函数
     */
    function render(html) {
        return _.isString(html) ? _.template(html, templateOptions) : emptyRender;
    }

    /**
     * 解析 key=val 组合的字符串
     * 
     * 使用 parseQueryString(queryString, '&', '=', {});
     *
     * @param {string} qs 待解析的字符串
     * @param {string} seq 分隔字符，默认为 &
     * @param {string} eq 键值之间的字符，默认为 =
     * @param {object} options 选项
     * @return {object} 解析后生成的对象
     */
    function parseQueryString(qs, sep, eq, options) {
        sep = sep || '&';
        eq = eq || '=';

        var obj = {};
        if (!_.isString(qs) || qs.length === 0) {
            return obj;
        }

        var regexp = /\+/g;
        qs = qs.split(sep);

        var maxKeys = 1000;
        if (options && _.isNumber(options.maxKeys)) {
            maxKeys = options.maxKeys;
        }

        var len = qs.length;
        // maxKeys <= 0 则不限制键的个数
        if (maxKeys > 0 && len > maxKeys) {
            len = maxKeys;
        }

        var decode = decodeURIComponent;
        if (options && _.isFunction(options.decodeURIComponent)) {
            decode = options.decodeURIComponent;
        }

        for (var i = 0; i < len; ++i) {
            var x = qs[i].replace(regexp, '%20'),
                idx = x.indexOf(eq),
                kstr, vstr, k, v;

            if (idx >= 0) {
                kstr = x.substr(0, idx);
                vstr = x.substr(idx + 1);
            } else {
                kstr = x;
                vstr = '';
            }

            try {
                k = decode(kstr);
                v = decode(vstr);
            } catch (e) {
                k = decodeURIComponent(kstr, true);
                v = decodeURIComponent(vstr, true);
            }

            if (!_.has(obj, k)) {
                obj[k] = v;
            } else if (_.isArray(obj[k])) {
                obj[k].push(v);
            } else {
                obj[k] = [obj[k], v];
            }
        }
        return obj;
    }

    /**
     * Set a cookie.
     * 
     * The following functions are from Cookie.js class in TinyMCE 3, Moxiecode, used under LGPL.
     * setCookie(), getCookie(), removeCookie()
     *
     * The 'expires' arg can be either a JS Date() object set to the expiration date (back-compat)
     * or the number of seconds until expiration
     * 
     * @param {string} name cookie 的名称
     * @param {string} value cookie 的值
     * @param {Date|Number} expires cookie 的失效时间，可以是一个 JS Date() 对象或秒数
     * @param {string} path cookie 的路径
     * @param {string} domain cookie 所属的域
     * @param {boolean} secure cookie 是否只在 HTTPS 连接下才从客户端传到服务器端
     * @void
     */
    function setCookie(name, value, expires, path, domain, secure) {
        var d = new Date();

        if (typeof(expires) === 'object' && expires.toGMTString) {
            expires = expires.toGMTString();
        } else if (parseInt(expires, 10)) {
            d.setTime(d.getTime() + (parseInt(expires, 10) * 1000)); // time must be in miliseconds
            expires = d.toGMTString();
        } else {
            expires = '';
        }

        document.cookie = name + '=' + encodeURIComponent(value) +
            (expires ? '; expires=' + expires : '') +
            (path ? '; path=' + path : '') +
            (domain ? '; domain=' + domain : '') +
            (secure ? '; secure' : '');
    }
    /**
     * Get a cookie.
     */
    function getCookie(name) {
        var e, b,
            cookie = document.cookie,
            p = name + '=';

        if (!cookie) {
            return;
        }

        b = cookie.indexOf('; ' + p);

        if (b === -1) {
            b = cookie.indexOf(p);

            if (b !== 0) {
                return null;
            }
        } else {
            b += 2;
        }

        e = cookie.indexOf(';', b);

        if (e === -1) {
            e = cookie.length;
        }

        return decodeURIComponent(cookie.substring(b + p.length, e));
    }
    /**
     * Remove a cookie.
     *
     * This is done by setting it to an empty value and setting the expiration time in the past.
     */
    function removeCookie(name, path, domain, secure) {
        setCookie(name, '', -1000, path, domain, secure);
    }

    /**
     * 按层级遍历数组元素
     * 数组元素具有 children 属性且为数组类型，则对其进行遍历
     *
     * @param {array} list 待遍历的数组
     * @param {object} parent 上级对象
     * @param {function} func 遍历函数
     * @return void
     */
    var walkListLevel = 0;

    function walkList(list, parent, func) {
        if (_.isArray(list) && _.isFunction(func)) {
            walkListLevel++;
            _.each(list, function(element, index, list) {
                // 如果 element 是一个对象
                // 为其添加一个 __level 属性，表示当前元素所在的层级
                if (typeof element === 'object')
                    element.__level = walkListLevel;

                func(element, parent);

                if (element.children && _.isArray(element.children)) {
                    // 如果有下一级，则当前元素作为父级元素
                    walkList(element.children, element, func);
                }
            });
            walkListLevel--;
        }
    }

    /**
     * 去除数组的重复元素
     *
     * @param {array} list 待遍历的数组
     * @return 不含重复元素的数组
     */
    function uniqueArray(list) {
        list = list || [];
        var temp = {},
            data = [],
            num = list.length;

        _.each(list, function(element, index) {
            typeof(temp[element]) === 'undefined' ? temp[element] = 1: '';
        });

        _.each(temp, function(value, key) {
            data.push(key);
        });
        return data;
    }
    
    /**
     * 给菜单添加icon
     * @param source 菜单来源
     * @param menu 原数据
     * return 
     */
    var addMenuIcon = function(source, menu){
        var result = []
        _.each(menu, function(item, index){
            item.icon = defualt_icon.menu_icon[source][item.id];
            result.push(item);
        });
        return result;
    }

    return {
        template: template,
        render: render,
        getCookie: getCookie,
        removeCookie: removeCookie,
        setCookie: setCookie,
        walkList: walkList,
        parseQueryString: parseQueryString,
        uniqueArray: uniqueArray,
        addMenuIcon: addMenuIcon
//        param: $.param
    };

});