/**
 * 金档口 - 色彩
 * cailuwei<>
 */
'use strict';
define(function (require, exports, module) {
    var colorMap ={
            'red':     '红色',
            'coffee': '咖啡色',
            'blue':   '蓝色',
            'yellow': '黄色',
            'pink':   '粉色',
            'oringe': '橙色',
            'purple': '紫色',
            'green':  '绿色'
        },
        colorList = ['red', 'coffee', 'blue', 'yellow', 'pink', 'oringe', 'purple', 'green'];

    module.exports = {
        colorMap: colorMap,
        colorList: colorList
    };
});