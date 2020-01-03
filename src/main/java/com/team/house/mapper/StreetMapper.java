package com.team.house.mapper;

import com.team.house.pojo.Street;
import com.team.house.pojo.StreetExample;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    /**
     * @param id 区域编号
     * @return 影响行数
     */
    @Delete("delete from street where DISTRICT_ID=#{id}")
    int deleteStreetByDistrictId(Integer id);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreStreet(Integer [] ids);
}