package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.mapper.StreetMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.pojo.Street;
import com.team.house.pojo.StreetExample;
import com.team.house.service.StreetService;
import com.team.house.utils.StreetCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> getStreet(StreetCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        if(condition.getStreetname()!=null){
            criteria.andNameLike("%"+condition.getStreetname()+"%");
        }
        if(condition.getDistrictId()!=null){
            criteria.andDistrictIdEqualTo(condition.getDistrictId());
        }
        List<Street> list = streetMapper.selectByExample(streetExample);
        return new PageInfo<>(list);
    }

    @Override
    public int addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public Street listStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }
    //挂起事物
    @Override
    public int updateStreet(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }

    //使用事物支持

    @Override
    //不能使用try catch，因为会让事物扫描不到异常
    //返回值无关了，同步后出现异常就会回滚，成功就执行
//    @Transactional
    public int deleteStreet(Integer id) {
        //删除区域
        int i = streetMapper.deleteByPrimaryKey(id);
        //删除街道
        return i;
    }

    @Override
    public int deleteMoreStreet(Integer[] ids) {
        return streetMapper.deleteMoreStreet(ids);
    }

    @Override
    public List<Street> getStreetByDid(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }
}
