package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.mapper.TypeMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.pojo.Type;
import com.team.house.pojo.TypeExample;
import com.team.house.service.TypeService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Type> getType(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        return new PageInfo<>(list);
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type listType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }
    //挂起事物
    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    //使用事物支持

    @Override
    //不能使用try catch，因为会让事物扫描不到异常
    //返回值无关了，同步后出现异常就会回滚，成功就执行
//    @Transactional
    public int deleteType(Integer id) {
        //删除区域
        int i = typeMapper.deleteByPrimaryKey(id);
        //删除街道
        return i;
    }

    @Override
    public int deleteMoreType(Integer[] ids) {
        return typeMapper.deleteMoreType(ids);
    }

    @Override
    public List<Type> getALLType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
