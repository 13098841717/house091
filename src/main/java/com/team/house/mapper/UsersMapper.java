package com.team.house.mapper;

import com.team.house.pojo.Users;
import com.team.house.pojo.UsersExample;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreUsers(Integer [] ids);
}