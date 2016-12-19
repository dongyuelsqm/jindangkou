/**
 * 个人中心 - 菜单
 * cailuwei<923825601@qq.com>
 */
'use strict';
define(function (require, exports, module) {

	var menu = [{
        id: 1,
        name: '服务中心',
        url: 'member/service/service.action',
        isParent: true,
        disabled: false,
        icon: '&#xe63d;',
        children: [{
            id: 1001,
            name: '我的API',
            url: 'member/service/api-invoke.action',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 1002,
            name: '我的报告',
            url: 'member/service/report-load.action',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 1003,
            name: '我的应用',
            url: 'member/service/application-purchase.action',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 1004,
            name: '购买订单',
            url: 'member/service/trade-order.action',
            isParent: false,
            disabled: false,
            children: []
        }]
    },{
        id: 2,
        name: '发布中心',
        url: '',
        isParent: true,
        disabled: true,
        icon: '&#xe63e;',
        children: [{
            id: 2001,
            name: 'API发布',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 2002,
            name: '报告发布',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 2003,
            name: '应用发布',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 2004,
            name: '售出订单',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 2005,
            name: '我要发布',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        }]
    },{
        id: 3,
        name: '财务中心',
        url: 'member/account/finance-center.action',
        isParent: true,
        disabled: true,
        icon: '&#xe63f;',
        children: [{
            id: 3001,
            name: '充值',
            url: 'member/account/recharge.action',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 3002,
            name: '提现',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 3003,
            name: '充值记录',
            url: 'member/account/recharge-rechargelist.action',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 3004,
            name: '提现记录',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 3005,
            name: '消费记录',
            url: 'member/account/cost.action',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 3006,
            name: '银行卡管理',
            url: '',
            isParent: false,
            disabled: true,
            children: []
        },{
            id: 3007,
            name: '开具发票',
            url: 'member/account/invoice.action',
            isParent: false,
            disabled: false,
            children: []
        }]
    },{
        id: 4,
        name: '个人资料',
        url: 'member/personal/personal-data.action',
        isParent: true,
        disabled: false,
        icon: '&#xe640;',
        children: [{
            id: 4001,
            name: '密码修改',
            url: 'member/personal/password.action',
            isParent: false,
            disabled: false,
            children: []
        },{
            id: 4002,
            name: '实名认证',
            url: 'member/personal/identity-verification.action',
            isParent: false,
            disabled: false,
            children: []
        }]
    }];

	return menu;
});
