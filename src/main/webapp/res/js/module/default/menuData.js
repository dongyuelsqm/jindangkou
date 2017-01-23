/**
 * 金档口 - 菜单数据
 * cailuwei<>
 */
'use strict';
define(function (require, exports, module) {

	var menu = [{
        id: 1,
        name: '店铺管理',
        url: '',
        isParent: true,
        disabled: false,
        // icon: '&#xe61e;',
        icon: 'cf-c14',
        children: [{
            id: 1001,
            name: '店铺公告',
            url: 'shopping-chart/notice.jsp',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 1002,
            name: '商户信息',
            url: 'shopping-chart/customer.jsp',
            isParent: false,
            disabled: false,
            children: []
        }]
    },{
        id: 2,
        name: '商城管理',
        url: '',
        isParent: true,
        disabled: false,
        // icon: '&#xe6f2;',
        icon: 'shouji',
        children: [{
            id: 2001,
            name: '添加商品',
            url: 'product/add.jsp',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 2002,
            name: '商品列表',
            url: 'product/list.jsp',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 2003,
            name: '预览商城',
            url: '',
            isParent: false,
            disabled: false,
            children: []
        }]
    },{
        id: 3,
        name: '订单管理',
        url: '',
        isParent: true,
        disabled: false,
        // icon: '&#xe66c;',
        icon: 'dingdan',
        children: [{
            id: 3001,
            name: '添加订单',
            url: 'order/add.jsp',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 3002,
            name: '订单列表',
            url: 'order/list.jsp',
            isParent: false,
            disabled: false,
            children: []
        }]
    }, {
        id: 4,
        name: '统计报表',
        url: '',
        isParent: true,
        disabled: false,
        // icon: '&#xe6f3;',
        icon: 'tongji',
        children: [{
            id: 4001,
            name: '订单统计',
            url: 'statistics/order.jsp',
            isParent: false,
            disabled: false,
            children: []
        }, {
            id: 4002,
            name: '商品统计',
            url: 'statistics/product.jsp',
            isParent: false,
            disabled: false,
            children: []
        }, {
            id: 4003,
            name: '客户统计',
            url: 'statistics/customer.jsp',
            isParent: false,
            disabled: false,
            children: []
        }]
    }, {
        id: 5,
        name: '微信管理',
        url: '',
        isParent: true,
        disabled: false,
        icon: 'weixin',
        children: [{
            id: 5001,
            name: '微信群发',
            url: 'wechat/wechat-group.jsp',
            isParent: false,
            disabled: false,
            children: []
        }, {
            id: 5002,
            name: '短信群发',
            url: 'wechat/sms-group.jsp',
            isParent: false,
            disabled: false,
            children: []
        }, {
            id: 5003,
            name: '客户分析',
            url: 'wechat/',
            isParent: false,
            disabled: false,
            children: []
        }, {
            id: 5004,
            name: '用户管理',
            url: 'wechat/',
            isParent: false,
            disabled: false,
            children: []
        }, {
            id: 5005,
            name: '素材管理',
            url: 'wechat/',
            isParent: false,
            disabled: false,
            children: []
        }]
    }, {
        id: 6,
        name: '售后管理',
        url: '',
        isParent: false,
        disabled: false,
        icon: 'anquan',
        children: []
    }, {
        id: 7,
        name: '系统运维中心',
        url: '',
        isParent: false,
        disabled: false,
        icon: 'tongzhi',
        children: []
    }];

	return menu;
});
