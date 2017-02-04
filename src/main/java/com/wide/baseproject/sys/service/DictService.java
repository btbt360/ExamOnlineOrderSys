package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wide.common.model.Dict;
import com.wide.common.model.Right;
import com.wide.common.model.Role;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryDict;
import com.wide.common.model.query.QueryRole;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;

public class DictService {

	/**
	 * 获取数据字典
	 * */
	public List<Dict> getDictAll() {
		List<Dict> mlist = new ArrayList<Dict>();
		mlist = Dict.dao.getDictAll();
		return mlist;
	}

	/**
	 * 按id查询数据字典
	 * 
	 * @param id
	 * @return Dict
	 */
	public Dict getDictById(String id) {
		// TODO Auto-generated method stub
		Dict dict = Dict.dao.findById(id);
		return dict;
	}

	/**
	 * 删除数据字典
	 * 
	 * @param id
	 */
	public void deldict(String id) {
		// TODO Auto-generated method stub
		Dict dict = Dict.dao.findById(id);
		dict.setDelFlag("1");
		dict.setCreateDate(new Date());
		dict.setUpdateDate(new Date());
		dict.update();
	}

	/**
	 * 数据字典分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param queryDict
	 * @return DataTablesModel
	 */
	public DataTablesModel getPageDict(int pageNum, int pageSize,
			QueryDict queryDict) {
		DataTablesModel dictpage = Dict.dao.pageDataTables(pageNum, pageSize,
				queryDict);
		if (dictpage != null && !dictpage.equals("")) {
			List<List<String>> rows = dictpage.getRows();
			List<String> ids = dictpage.getIds();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					row.add(5, "<a href ='#' onclick=edit('" + row.get(0).trim()
							+ "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>");
					row.remove(0);
				}

			}
		}

		return dictpage;
	}

	/**
	 * 根据字典key,type查询数据字典
	 * 
	 * @param key
	 * @param type
	 * @return String
	 */
	public String getDictByKeyType(String key, String type) {
		// TODO Auto-generated method stub
		return Dict.dao.getDictByKeyType(key, type);
	}
   /**
    * 根据字典key,type查询数据字典
    * @param type
    * @return List<Dict>
    */
	public List<Dict> getDictByType(String type) {
		List<Dict> mlist = new ArrayList<Dict>();
		mlist = Dict.dao.getDictByType(type);
		return mlist;
	}
}
