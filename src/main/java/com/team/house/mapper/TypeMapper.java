package com.team.house.mapper;

import com.team.house.pojo.Type;
import com.team.house.pojo.TypeExample;
import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    /**
     *
     * @param ids 类型id数组
     * @return 影响行数
     */
    int deleteMoreType(Integer [] ids);
}