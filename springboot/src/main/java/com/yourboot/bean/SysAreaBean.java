package com.yourboot.bean;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Calendar;

/**
 * 系统区域实体Bean
 *
 * @author 宿继申
 * @version 1.0
 * @File: SysAreaBean.java
 * @package com.youdy.bean
 * @date: 2016年10月17日 下午3:53:21
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 */
@Data
public class SysAreaBean extends CommonBean implements Comparable<SysAreaBean> {

    private static final long serialVersionUID = -5505278375125054618L;
    private Integer areaID;

    private String areaName;

    private Integer areaCode;

    private Integer parentID;

    private Integer areaLevel;

    private String areaLevelName;

    private Integer countryID;

    private Short status;

    private transient String areaSearchName;


    public String getAreaLevelName() {
        return areaLevelName;
    }

    public void setAreaLevelName(String areaLevelName) {
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
        if (this.areaLevel == null) {
            this.areaLevelName = "";
        } else if (this.areaLevel.equals(new Integer(1))) {
            this.areaLevelName = "省直辖市";
        } else if (this.areaLevel.equals(new Integer(2))) {
            this.areaLevelName = "市";
        } else if (this.areaLevel.equals(new Integer(3))) {
            this.areaLevelName = "区县";
        } else if (this.areaLevel.equals(new Integer(4))) {
            this.areaLevelName = "乡镇";
        } else {
            this.areaLevelName = "";
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((areaID == null) ? 0 : areaID.hashCode());
        result = prime * result + ((areaName == null) ? 0 : areaName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SysAreaBean other = (SysAreaBean) obj;
        if (areaID == null) {
            if (other.areaID != null)
                return false;
        } else if (!areaID.equals(other.areaID))
            return false;
        if (areaName == null) {
            if (other.areaName != null)
                return false;
        } else if (!areaName.equals(other.areaName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        // 通过反射重写toString
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

    @Override
    public int compareTo(SysAreaBean o) {
        if (o != null) {
            long oCreateTimeMili = o.getCreateTime().getTime();
            long cCreateTimeMili = super.getCreateTime().getTime();

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTimeInMillis(oCreateTimeMili);
            c2.setTimeInMillis(cCreateTimeMili);
            return c1.compareTo(c2);
        }
        return 0;
    }

}
