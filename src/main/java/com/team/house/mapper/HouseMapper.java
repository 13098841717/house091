package com.team.house.mapper;

import com.team.house.pojo.House;

import com.team.house.pojo.HouseExample;
import com.team.house.utils.HouseCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    /**
     * 查询当前用户的所有房屋信息
     * @param userId 用户的编号
     * @return
     */
    List<House> getHouseByUser(@Param("userId") Integer userId);

    /**
     * 根据房屋编号查询单条房屋信息
     * @param id 房屋编号
     * @return 房屋实体
     */
    House showHouse(@Param("id") String id);

    /**
     * 获取所有的房屋信息
     * @return 返回所有的房屋信息
     */
    List<House> getAllHouse();

    /**
     * @param ids 批量审核的方法
     * @return 返回影响行数
     */
    int isPassMoreHouse(Integer[] ids,Integer status);

    /**
     * 动态查询主页房屋
     * @param houseCondition 封装了分页和条件
     * @return
     */
    List<House> mainHouse(HouseCondition houseCondition);
}