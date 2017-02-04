package com.wide.common.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.wide.common.model.base.BaseExampapersQtypes;
import com.wide.common.model.query.QueryCase;
import com.wide.common.model.query.QueryStatistics;
import com.wide.viewmodel.DataTablesModel;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ExampapersQtypes extends BaseExampapersQtypes<ExampapersQtypes> {
	public static final ExampapersQtypes dao = new ExampapersQtypes();

	public List<ExampapersQtypes> findByExampapersId(String id) {
		// TODO Auto-generated method stub
		List<ExampapersQtypes> list = new ArrayList<ExampapersQtypes>();
		list = find("select * from sys_exampapers_qtypes where exampapers_id = ? ", id);
		return list;
	}

	
}
