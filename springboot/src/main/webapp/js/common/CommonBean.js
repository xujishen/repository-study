/**
 * 公共实体类
 */


var CommonBean = function () {
	this.pageNumber = 1;
	this.pageCnt = 20;
};

CommonBean.prototype.beanId = undefined;
CommonBean.prototype.pageNum = 1;       // 当前页
CommonBean.prototype.pageCnt = 20;      // 每页记录数
CommonBean.prototype.beanTotal = 0;     // 总记录数
CommonBean.prototype.pageTotal = 0;     // 总页数