package com.team.house.utils;

/**
 * 继承分页做到查询条件分页
 */
public class UserCondition extends PageUtil {
    private String username;
    private Integer telephone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
}
