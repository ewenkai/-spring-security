package com.pyg.sellergoods.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.dao.TbBrandMapper;
import com.pyg.pojo.PageResult;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrandExample;
import com.pyg.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
 TbBrandMapper brandMapper;
    @Override
    public List<TbBrand> findAll() {

        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
        //获取每页的数据
        Page<TbBrand> tbBrands = (Page<TbBrand>) brandMapper.selectByExample(null);
        return new PageResult(tbBrands.getTotal(),tbBrands.getResult());
    }

    @Override
    public void add(TbBrand brand) {

        brandMapper.insert(brand);

    }

    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public TbBrand findOne(long id) {
        TbBrand tbBrand = brandMapper.selectByPrimaryKey(id);
        return tbBrand;
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public PageResult findPage(TbBrand brand,int pageNum, int pageSize) {
        TbBrandExample tbBrandExample = new TbBrandExample();
        if (brand!=null){

            TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
            if (brand.getName()!=null&&brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if (brand.getFirstChar()!=null&&brand.getFirstChar().length()>0){
                criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
            }
        }
        //分页
        PageHelper.startPage(pageNum,pageSize);
        //获取每页的数据
        Page<TbBrand> tbBrands = (Page<TbBrand>) brandMapper.selectByExample(tbBrandExample);
        return new PageResult(tbBrands.getTotal(),tbBrands.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        List<Map> map = brandMapper.selectOptionList();
        return map;
    }
}
