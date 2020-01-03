package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.pojo.House;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.PageUtil;

public interface HouseService {

    /**
     * 添加房屋信息
     * @param house 房屋实体
     * @return
     */
    int addHouse(House house);
    /**
     * 分页显示该用户的所有房屋信息
     * @param userId 用户编号
     * @param pageUtil 分页工具
     * @return
     */
    PageInfo<House> getHouseByUser(Integer userId, PageUtil pageUtil);

    /**
     * 显示单条房屋信息
     * @param id
     * @return
     */
    House showHouse(String id);

    /**
     * 更新房屋信息
     * @param house 房屋实体
     * @return
     */
    int updateHouse(House house);

    /**
     * 逻辑删除房屋
     * @param id 房屋id
     * @param status 状态id
     * @return
     */
    int deleteHouse(String id,Integer status);

    /**
     * 显示所有的房屋信息
     * @param pageUtil 分页
     * @return
     */
    PageInfo<House> showAllHouse(PageUtil pageUtil);

    /**
     * 审核房屋
     * @param id 房屋id
     * @param ispass 状态
     * @return
     */
    int isPassHouse(String id,Integer ispass);


    /**
     * @param ids 批量审核的方法
     * @return 返回影响行数
     */
    int isPassMoreHouse(Integer[] ids,Integer status);

    /**
     * 动态展示房屋
     * @param houseCondition
     * @return
     */
    PageInfo<House> mainHouse(HouseCondition houseCondition);
}
