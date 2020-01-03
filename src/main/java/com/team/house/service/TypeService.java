package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.pojo.Type;
import com.team.house.utils.PageUtil;

import java.util.List;

public interface TypeService {

    /**
     *
     * @param pageUtil 分页工具
     * @return 返回的是pageInfo
     */
    PageInfo<Type> getType(PageUtil pageUtil);

    /**
     *
     * @param type 区域pojo
     * @return 影响行数
     */
    int addType(Type type);

    /**
     *
     * @param id 区域ID
     * @return 查询区域的单挑数据
     */
     Type listType(Integer id);

    /**
     *
     * @param type 页面获取的更新数据
     * @return 影响行数
     */
     int updateType(Type type);

    /**
     *
     * @param id 区域编号
     * @return 影响行数
     * 使用事物支持，删除区域同时删除街道
     */
     int deleteType(Integer id);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreType(Integer[] ids);

    /**
     * 查询所有的房屋类型
     * @return
     */
    List<Type> getALLType();
}
