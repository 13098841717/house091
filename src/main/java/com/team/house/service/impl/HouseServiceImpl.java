package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.mapper.HouseMapper;
import com.team.house.pojo.House;

import com.team.house.pojo.HouseExample;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUser(Integer userId, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list = houseMapper.getHouseByUser(userId);
        return new PageInfo<>(list);
    }

    @Override
    public House showHouse(String id) {
        return houseMapper.showHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int deleteHouse(String id,Integer status) {
        House house=new House();
        house.setId(id);
        house.setIsdel(status);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> showAllHouse(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> allHouse = houseMapper.getAllHouse();
        return new PageInfo<>(allHouse);
    }

    @Override
    public int isPassHouse(String id,Integer ispass) {
        House house=new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int isPassMoreHouse(Integer[] ids, Integer status) {
        return houseMapper.isPassMoreHouse(ids,status);
    }

    @Override
    public PageInfo<House> mainHouse(HouseCondition houseCondition) {
        PageHelper.startPage(houseCondition.getPage(),houseCondition.getRows());
        List<House> list = houseMapper.mainHouse(houseCondition);
        return new PageInfo<>(list);
    }
}
