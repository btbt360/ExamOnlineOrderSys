package com.wide.baseproject.resource.service;

import java.util.List;

import com.wide.common.model.Cases;
import com.wide.common.model.Subject;
import com.wide.common.model.query.QueryCase;
import com.wide.common.model.query.QuerySubject;
import com.wide.viewmodel.DataTablesModel;

public class CaseService {

	public DataTablesModel getPageCase(int pageNum, int pageSize, QueryCase queryCase) {
		// TODO Auto-generated method stub
		DataTablesModel casepage = Cases.dao.pageDataTables(pageNum, pageSize, queryCase);
		
		if (casepage != null && !casepage.equals("")) {
			List<List<String>> rows = casepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String info = row.get(3);
					row.set(3, Integer.parseInt((row.get(4) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
					row.set(4, "<a href ='#' onclick=edit('" + row.get(0).trim()
							+ "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>"  );
					row.set(0, row.get(1));
					row.set(1, row.get(2));
					row.set(2, info);
				}
			}
		}
		return casepage;
		
	}

	

}
