/**
 *     时间控件
 *     cailuwei <cailuwei@chinamobile.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery'),
		moment = require('moment');

	/**
	 * datetimepicker内部使用国际时间，即比外部操作少8小时
	 * 点击后返回event值会换算成本地时间，+8:00
	 * 要与绑定控件显示一致
	 * 输出：转换为utc()，减8小时;
	 * 输入：加8小时，控件显示的时间才能与本地一致
	 * 
	 * 但对于字符串，则没有时区问题
	 */
	require('jquery-datetimepicker');

    var pickers = [];
    var $startTimeStr, $endTimeStr,
        $startTime, $endTime;
    var day = moment(),
    	now = day.format('YYYY-MM-DD HH:mm'),
        startTime = day.format('YYYY-MM-DD 00:00'),
        endDay = day.format('YYYY-MM-DD'),
        startDay = day.subtract(1, 'd').format('YYYY-MM-DD');
    
    function initBase () {
        while (pickers.length > 0) {
            pickers.pop().remove();
        };

        $startTimeStr = $('.startTimeStr');
        $endTimeStr = $('.endTimeStr');
        $startTime = $('.startTime');
        $endTime = $('.endTime');
    }

	function initHour () {
        initBase();
        $startTime.val(startTime + ':00');
        $startTimeStr.val(startTime);
        $endTime.val(now + ':00');
        $endTimeStr.val(now);
		$startTimeStr.each(function () {
			var $this = $(this),
			$p = $this.parent();
			$this.datetimepicker({
			    format: 'yyyy-mm-dd hh:ii',
			    language: 'zh-CN',
			    autoclose: true,
			    maxView: 2,
			    endDate: now,
			}).on('changeDate', function (event) {
				if (event.date.valueOf()) {
			    	var d = moment(event.date).utc();
			        var dStr0 = d.format('YYYY-MM-DD HH:mm');
			        $p.find('.endTimeStr').datetimepicker('setStartDate', dStr0);
			        $this.next().val(dStr0 + ':00');
				}
			});

            pickers.push($this.data('datetimepicker'));
		});

		$endTimeStr.each(function () {
			var $this = $(this),
				$p = $this.parent();
			$this.datetimepicker({
			    format: 'yyyy-mm-dd hh:ii',
			    language: 'zh-CN',
			    autoclose: true,
			    maxView: 2,
			    endDate: now,
			    startDate: startTime
			}).on('changeDate', function (event) {
				if (event.date.valueOf()) {
			    	var d = moment(event.date).utc();
			        var dStr0 = d.format('YYYY-MM-DD HH:mm');
			        $p.find('.startTimeStr').datetimepicker('setEndDate', dStr0);
			        $this.next().val(dStr0 + ':00');
			    }
			});

            pickers.push($this.data('datetimepicker'));
		});
	}

	function initDay () {
        initBase();
        $startTime.val(startDay);
        $endTime.val(endDay);
		$startTime.each(function () {
			var $this = $(this),
				$p = $this.parent();
			$this.datetimepicker({
			    format: 'yyyy-mm-dd',
			    language: 'zh-CN',
			    autoclose: true,
			    minView: 2,
			    maxView: 2,
			    endDate: now
			}).on('changeDate', function (event) {
				if (event.date.valueOf()) {
			    	var d = moment(event.date);
			        var dStr = d.format('YYYY-MM-DD');
			        $p.find('.endTime').datetimepicker('setStartDate', dStr);
				}
			});

            pickers.push($this.data('datetimepicker'));
		});

		$endTime.each(function () {
			var $this = $(this),
				$p = $this.parent();
			$this.datetimepicker({
			    format: 'yyyy-mm-dd',
			    language: 'zh-CN',
			    autoclose: true,
			    minView: 2,
			    maxView: 2,
			    endDate: now,
			    startDate: startDay,
			}).on('changeDate', function (event) {
				if (event.date.valueOf()) {
			    	var d = moment(event.date);
			        var dStr = d.format('YYYY-MM-DD');
			        $p.find('.startTime').datetimepicker('setEndDate', dStr);
			    }
			});

            pickers.push($this.data('datetimepicker'));
		});
	}
	
	function initTime () {
		while (pickers.length > 0) {
            pickers.pop().remove();
        }

        var $dateTime = $('.dateTime'),
        	$dateTimeStr = $('.dateTimeStr');
        $dateTime.val(now + ':00');
        $dateTimeStr.val(now);
        
        $dateTimeStr.each(function () {
			var $this = $(this);
			$this.datetimepicker({
			    format: 'yyyy-mm-dd hh:ii',
			    language: 'zh-CN',
			    autoclose: true,
			    maxView: 2,
			    endDate: now
			}).on('changeDate', function (event) {
				if (event.date.valueOf()) {
			    	var d = moment(event.date).utc();
			        var dStr0 = d.format('YYYY-MM-DD HH:mm');
			        $this.next().val(dStr0 + ':00');
				}
			});

            pickers.push($this.data('datetimepicker'));
		});
	}

	return {
		initTime: initTime,
		initDay: initDay,
		initHour: initHour
	};
});
