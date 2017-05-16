Date.prototype.Format = function(fmt)   
{ 
  var o = {   
    "M+" : this.getMonth()+1,                 //月份
    "d+" : this.getDate(),                    //日
    "h+" : this.getHours(),                   //小时
    "m+" : this.getMinutes(),                 //分
    "s+" : this.getSeconds(),                 //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S"  : this.getMilliseconds()             //毫秒
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};

var TimeObjectUtil;
/**
 * @title 时间工具类
 * @note 本类一律违规验证返回false
 * @author 
 * @date 2016-07-02
 * @formatter "2016-07-02 00:00:00" , "2016-07-02"
 */
TimeObjectUtil = {
  /**
   * 获取当前时间毫秒数
   */
  getCurrentMsTime : function() {
    var myDate = new Date();
    return myDate.getTime();
  },
  /**
   * 毫秒转时间格式
   */
  longMsTimeConvertToDateTime : function(time) {
    var myDate = new Date(time);
    return this.formatterDateTime(myDate);
  },
  /**
   * 时间格式转毫秒
   */
  dateToLongMsTime : function(date) {
    var myDate = new Date(date);
    return myDate.getTime();
  },
  /**
   * 格式化日期（不含时间）
   */
  formatterDate : function(date) {
    var datetime = date.getFullYear()
        + "-"// "年"
        + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
        + (date.getMonth() + 1))
        + "-"// "月"
        + (date.getDate() < 10 ? "0" + date.getDate() : date
            .getDate());
    return datetime;
  },
  /**
   * 格式化日期（含时间"00:00:00"）
   */
  formatterDate2 : function(date) {
    var datetime = date.getFullYear()
        + "-"// "年"
        + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
        + (date.getMonth() + 1))
        + "-"// "月"
        + (date.getDate() < 10 ? "0" + date.getDate() : date
            .getDate()) + " " + "00:00:00";
    return datetime;
  },
  /**
   * 格式化去日期（含时间）
   */
  formatterDateTime : function(date) {
    var datetime = date.getFullYear()
        + "-"// "年"
        + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
        + (date.getMonth() + 1))
        + "-"// "月"
        + (date.getDate() < 10 ? "0" + date.getDate() : date
            .getDate())
        + " "
        + (date.getHours() < 10 ? "0" + date.getHours() : date
            .getHours())
        + ":"
        + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
            .getMinutes())
        + ":"
        + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
            .getSeconds());
    return datetime;
  },
  /**
   * 时间比较{结束时间大于开始时间}
   */
  compareDateEndTimeGTStartTime : function(startTime, endTime) {
    return ((new Date(endTime.replace(/-/g, "/"))) > (new Date(
        startTime.replace(/-/g, "/"))));
  },
  /**
   * 验证开始时间合理性{开始时间不能小于当前时间{X}个月}
   */
  compareRightStartTime : function(month, startTime) {
    var now = formatterDayAndTime(new Date());
    var sms = new Date(startTime.replace(/-/g, "/"));
    var ems = new Date(now.replace(/-/g, "/"));
    var tDayms = month * 30 * 24 * 60 * 60 * 1000;
    var dvalue = ems - sms;
    if (dvalue > tDayms) {
      return false;
    }
    return true;
  },
  /**
   * 验证开始时间合理性{结束时间不能小于当前时间{X}个月}
   */
  compareRightEndTime : function(month, endTime) {
    var now = formatterDayAndTime(new Date());
    var sms = new Date(now.replace(/-/g, "/"));
    var ems = new Date(endTime.replace(/-/g, "/"));
    var tDayms = month * 30 * 24 * 60 * 60 * 1000;
    var dvalue = sms - ems;
    if (dvalue > tDayms) {
      return false;
    }
    return true;
  },
  /**
   * 验证开始时间合理性{结束时间与开始时间的间隔不能大于{X}个月}
   */
  compareEndTimeGTStartTime : function(month, startTime, endTime) {
    var sms = new Date(startTime.replace(/-/g, "/"));
    var ems = new Date(endTime.replace(/-/g, "/"));
    var tDayms = month * 30 * 24 * 60 * 60 * 1000;
    var dvalue = ems - sms;
    if (dvalue > tDayms) {
      return false;
    }
    return true;
  },
  /**
   * 获取最近几天[开始时间和结束时间值,时间往前推算]
   */
  getRecentDaysDateTime : function(day) {
    var daymsTime = day * 24 * 60 * 60 * 1000;
    var yesterDatsmsTime = this.getCurrentMsTime() - daymsTime;
    var startTime = this.longMsTimeConvertToDateTime(yesterDatsmsTime);
    var pastDate = this.formatterDate2(new Date(startTime));
    var nowDate = this.formatterDate2(new Date());
    var obj = {
      startTime : pastDate,
      endTime : nowDate
    };
    return obj;
  },
  /**
   * 获取今天[开始时间和结束时间值]
   */
  getTodayDateTime : function() {
    var daymsTime = 24 * 60 * 60 * 1000;
    var tomorrowDatsmsTime = this.getCurrentMsTime() + daymsTime;
    var currentTime = this.longMsTimeConvertToDateTime(this.getCurrentMsTime());
    var termorrowTime = this.longMsTimeConvertToDateTime(tomorrowDatsmsTime);
    var nowDate = this.formatterDate2(new Date(currentTime));
    var tomorrowDate = this.formatterDate2(new Date(termorrowTime));
    var obj = {
      startTime : nowDate,
      endTime : tomorrowDate
    };
    return obj;
  },
  /**
   * 获取明天[开始时间和结束时间值]
   */
  getTomorrowDateTime : function() {
    var daymsTime = 24 * 60 * 60 * 1000;
    var tomorrowDatsmsTime = this.getCurrentMsTime() + daymsTime;
    var termorrowTime = this.longMsTimeConvertToDateTime(tomorrowDatsmsTime);
    var theDayAfterTomorrowDatsmsTime = this.getCurrentMsTime()+ (2 * daymsTime);
    var theDayAfterTomorrowTime = this.longMsTimeConvertToDateTime(theDayAfterTomorrowDatsmsTime);
    var pastDate = this.formatterDate2(new Date(termorrowTime));
    var nowDate = this.formatterDate2(new Date(theDayAfterTomorrowTime));
    var obj = {
      startTime : pastDate,
      endTime : nowDate
    };
    return obj;
  }
};