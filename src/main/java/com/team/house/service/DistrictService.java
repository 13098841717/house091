package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.pojo.District;
import com.team.house.utils.PageUtil;

import java.util.List;

public interface DistrictService {

    /**
     *
     * @param pageUtil 分页工具
     * @return 返回的是pageInfo
     */
    PageInfo<District> getDistrict(PageUtil pageUtil);

    /**
     *
     * @param district 区域pojo
     * @return 影响行数
     */
    int addDistrict(District district);

    /**
     *
     * @param id 区域ID
     * @return 查询区域的单挑数据
     */
     District listDistrict(Integer id);

    /**
     *
     * @param district 页面获取的更新数据
     * @return 影响行数
     */
     int updateDistrict(District district);

    /**
     *
     * @param id 区域编号
     * @return 影响行数
     * 使用事物支持，删除区域同时删除街道
     */
     int deleteDistrict(Integer id);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreDistrict(Integer [] ids);


    /**
     * 查询所有的区域
     * @return
     */
    List<District> getAllDistrict();
}
