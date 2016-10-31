/**
 * js公共文件, si_common.js和common-1.0.1.js由王婧统一维护.
 */

//--------------------------------//
/**
 * 对象工具类
 */
var ObjectUtils = {
    /**
     * jquery对象是否存在
     * @param obj
     * @returns {Boolean}
     */
    exists: function (obj) {
        return $(obj).length > 0;
    },
    //判断非空
    isEmpty: function (val) {
        if (undefined == val || val == null) {
            return true;
        }
        val = $.trim(val);
        if (val == null)
            return true;
        if (val == undefined || val == 'undefined')
            return true;
        if (val == "")
            return true;
        if (val.length == 0)
            return true;
        if (!/[^(^\s*)|(\s*$)]/.test(val))
            return true;
        return false;
    },

    isNotEmpty: function (val) {
        return !ObjectUtils.isEmpty(val);
    },

    equals: function (str, tstr, ignoreCase) {
        var ignore = true;
        if (ignoreCase) {
            ignore = ignoreCase;
        }
        if (ObjectUtils.isNotEmpty(str) && ObjectUtils.isNotEmpty(tstr)) {
            if (ignore) {
                return str == tstr || str.toString().toUpperCase() == tstr.toString().toUpperCase();
            } else {
                return str == tstr
            }
        }
        return false;
    },

    nullToSpace: function (val) {
        if (ObjectUtils.isEmpty(val)) {
            return "";
        }
        return val;
    },

    isTrue: function (val) {
        if (ObjectUtils.isNotEmpty(val)) {
            if (ObjectUtils.equals(val, 'true') || val == true) {
                return true;
            }
        }
        return false;
    },

    isFalse: function (val) {
        return !ObjectUtils.isTrue(val);
    },

    coalesce: function () {
        if (CollectionUtils.isBlank(arguments)) {
            return null;
        }
        for (var i in arguments) {
            var arg = arguments[i];
            if (ObjectUtils.isNotEmpty(arg)) {
                return arg;
            }
        }
        return null;
    },

    isJson: function (jsonStr) {
        if (ObjectUtils.isNotEmpty(jsonStr)) {
            try {
                var json = $.parseJSON(jsonStr);
                return true;
            } catch (e) {
                console.log(jsonStr + " is not a json !");
                return false;
            }
        }
        return false
    },

    isNotJson: function (jsonStr) {
        return !ObjectUtils.isJson(jsonStr);
    }
}

/**
 * 数组 / 集合 工具对象
 */
var CollectionUtils = {
    // 数组是否为空
    isBlank: function (arrays) {
        return undefined == arrays || arrays == null || typeof(arrays) == 'undefined' || arrays.length < 1;
    },

    // 数组是否不为空
    isNotBlank: function (arrays) {
        return !CollectionUtils.isBlank(arrays);
    },

    // 对象是否为数组
    isArray: function (arrays) {
        return Object.prototype.toString.call(arrays) === '[object Array]';
    },

    // 清除数组中空值
    clearBlanks: function (arrays) {
        var newArr = new Array();

        if (CollectionUtils.isBlank(arrays)) {
            return arrays;
        }
        for (var i in arrays) {
            var arr = arrays[i];
            if (ObjectUtils.isNotEmpty(arr)) {
                newArr.push(arr);
            }
        }
        return newArr;
    },

    /**
     * 判断某个字符是否在数组里
     * @param str
     * @param array
     * @returns {Boolean}
     */
    inArray: function (str, array) {
        if (CollectionUtils.isNotBlank(array)) {
            for (var i in array) {
                if (array[i].toString().toLowerCase() == str.toString().toLowerCase()) {
                    return true;
                }
            }
        }
        return false;
    },

    // 复制数组 - 深度复制
    copyArr: function (arr) {
        var _arr = [];
        for (var ii = 0; ii <= arr.length - 1; ii++) {
            _arr[ii] = jQuery.extend(true, {}, arr[ii])
        }
        return _arr;
    },

    numberSortAsc: function (x, y) {
        return x - y;
    },

    numberSortDesc: function (x, y) {
        return y - x;
    }
}


/* RGB颜色转换为16进制 */
String.prototype.colorHex = function () {
    var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
    var that = this;
    if (/^(rgb|RGB)/.test(that)) {
        var aColor = that.replace(/(?:\(|\)|rgb|RGB)*/g, "").split(",");
        var strHex = "#";
        for (var i = 0; i < aColor.length; i++) {
            var hex = Number(aColor[i]).toString(16);
            if (hex === "0") {
                hex += hex;
            }
            strHex += hex;
        }
        if (strHex.length !== 7) {
            strHex = that;
        }
        return strHex;
    } else if (reg.test(that)) {
        var aNum = that.replace(/#/, "").split("");
        if (aNum.length === 6) {
            return that;
        } else if (aNum.length === 3) {
            var numHex = "#";
            for (var i = 0; i < aNum.length; i += 1) {
                numHex += (aNum[i] + aNum[i]);
            }
            return numHex;
        }
    } else {
        return that;
    }
};

// -------------------------------------------------

/* 16进制颜色转为RGB格式 */
String.prototype.colorRgb = function () {
    var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
    var sColor = this.toLowerCase();
    if (sColor && reg.test(sColor)) {
        if (sColor.length === 4) {
            var sColorNew = "#";
            for (var i = 1; i < 4; i += 1) {
                sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
            }
            sColor = sColorNew;
        }
        //处理六位的颜色值
        var sColorChange = [];
        for (var i = 1; i < 7; i += 2) {
            sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
        }
        return sColorChange.join(",");
    } else {
        return sColor;
    }
};