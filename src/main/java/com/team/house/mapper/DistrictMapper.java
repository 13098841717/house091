package com.team.house.mapper;

import com.team.house.pojo.District;
import com.team.house.pojo.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreDistrict(Integer [] ids);
}