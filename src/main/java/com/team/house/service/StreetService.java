package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.pojo.Street;
import com.team.house.utils.StreetCondition;

import java.util.List;

public interface StreetService {

    /**
     *
     * @param condition 分页工具
     * @return 返回的是pageInfo
     */
    PageInfo<Street> getStreet(StreetCondition condition);

    /**
     *
     * @param street 区域pojo
     * @return 影响行数
     */
    int addStreet(Street street);

    /**
     *
     * @param id 区域ID
     * @return 查询区域的单挑数据
     */
     Street listStreet(Integer id);

    /**
     *
     * @param street 页面获取的更新数据
     * @return 影响行数
     */
     int updateStreet(Street street);

    /**
     *
     * @param id 区域编号
     * @return 影响行数
     * 使用事物支持，删除区域同时删除街道
     */
     int deleteStreet(Integer id);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreStreet(Integer[] ids);

    /**
     * 通过区域id查询所属街道
     * @param did 区域id
     * @return
     */
    List<Street> getStreetByDid(Integer did);
}
