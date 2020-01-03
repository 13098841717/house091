package com.team.house.service.impl;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.pojo.District;
import com.team.house.pojo.DistrictExample;
import com.team.house.service.DistrictService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<District> getDistrict(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        return new PageInfo<>(list);
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District listDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }
    //挂起事物
    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    //使用事物支持

    @Override
    //不能使用try catch，因为会让事物扫描不到异常
    //返回值无关了，同步后出现异常就会回滚，成功就执行
    @Transactional
    public int deleteDistrict(Integer id) {
        //删除区域
        int i = districtMapper.deleteByPrimaryKey(id);
        //int j=0,k=2;
        //k=k/j;
        //删除街道
        int i1 = streetMapper.deleteStreetByDistrictId(id);
        return i+i1;
    }

    @Override
    public int deleteMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
