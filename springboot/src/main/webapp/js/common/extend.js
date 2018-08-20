function _extend(d, e, c) {
    var b = function () {};

    for (var i = 1; i < arguments.length; i++) {
        var argument = arguments[i];
        // 如果参数上一个Function
        if (ObjectUtils.isFunc(argument)) {
            b.prototype = argument.prototype;
            d.prototype = new b();
            d.prototype.constructor = d;
            d.superclass = argument.prototype;

            if (argument.prototype.constructor == Object.prototype.constructor) {
                argument.prototype.constructor = argument;
            }
        } else {
            if (argument) {
                for (a in argument) {
                    if (argument.hasOwnProperty(a) || argument.isPrototypeOf(a)) {
                        d.prototype[a] = argument[a]
                    }
                }
            }
        }
    }
}
