package com.pyg.sellergoods.service;

import java.util.List;
import java.util.Map;


import com.pyg.pojo.PageResult;
import com.pyg.pojo.TbBrand;

/**
 * 品牌接口
 * @author Administrator
 *
 */
public interface BrandService {

	public List<TbBrand> findAll();

	PageResult findPage(int pageNum,int pageSize);

	void add(TbBrand brand);

	void update(TbBrand brand);

	TbBrand findOne(long id);

	void delete(long [] ids);
	PageResult findPage(TbBrand brand,int pageNum,int pageSize);

	List<Map> selectOptionList();
}
