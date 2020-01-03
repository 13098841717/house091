package com.team.house.utils;

/**
 * 继承分页做到查询条件分页
 */
public class StreetCondition extends PageUtil {
    private String streetname;
    private Integer districtId;

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
