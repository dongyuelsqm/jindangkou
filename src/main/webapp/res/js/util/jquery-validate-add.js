/**
 * 金档口 - validate - 自定义验证
 * cailuwei<>
 */
'use strict';
define(function(require) {
    require('jquery');
    require('jquery-validate');

    jQuery.validator.addMethod('hanzi', function(value, element) {
        var patt = /^[\u4E00-\u9FA5]+$/;
        return this.optional(element) || patt.test(value);
    }, '必须是汉字字符');
    jQuery.validator.addMethod('fullname', function(value, element) {
        var patt = /^[a-zA-Z\u4E00-\u9FA5]+$/;
        return this.optional(element) || patt.test(value);
    }, '只能包含汉字字符或英文字符');
    jQuery.validator.addMethod('mobile', function(value, element) {
        var patt = /^[1][3,4,5,7,8][0-9]{9}$/;
        return this.optional(element) || patt.test(value);
    }, '手机号码格式不正确');
    jQuery.validator.addMethod('contactNum', function(value, element) {
        var patt = /\d{11}|^\d{7,8}$|^\d{7,8}-\d{3,4}$|^\d{3,4}-\d{7,8}-\d{3,4}$|^\d{3,4}-\d{7,8}$/;
        return this.optional(element) || patt.test(value);
    }, '联系号码格式不正确');
    jQuery.validator.addMethod('illegalCharacter', function(value, element) {
        var patt = /[\[\]`~!_@#$%^&*+={}':;',.<>\/?！￥……&*——【】‘；：”“’。，、？]/;
        return this.optional(element) || !patt.test(value);
    }, '不能含有非法字符');
    jQuery.validator.addMethod('username', function(value, element) {
        var patt = /^[a-zA-Z\d-@_]{4,16}$/;
        return this.optional(element) || patt.test(value);
    }, '只能包含英文符号、数字以及符号“@”、“_”、“-”三种');
    jQuery.validator.addMethod('pwd', function(value, element) {
        var patt = /(?!^[0-9]+$)(?!^[A-z]+$)(?![\W_]+$)^.{6,16}$/;
        return this.optional(element) || patt.test(value);
    }, '6~16位字符，至少包含字母、数字和符号其中的两种');
    jQuery.validator.addMethod('code', function(value, element) {
        var patt = /^[a-zA-Z0-9]+$/;
        return this.optional(element) || patt.test(value);
    }, '只能含有数字或英文字符');
    jQuery.validator.addMethod('codeUppercase', function(value, element) {
        var patt = /^[A-Z0-9]+$/;
        return this.optional(element) || patt.test(value);
    }, '只能含有数字或大写英文字符');
})