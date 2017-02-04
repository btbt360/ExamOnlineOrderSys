package com.wide.baseproject.sys.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.wide.common.model.Dict;
import com.wide.common.model.Itembank;
import com.wide.common.model.Office;
import com.wide.common.model.OfficeUser;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
import com.wide.common.model.Right;
import com.wide.common.model.Role;
import com.wide.common.model.User;
import com.wide.common.model.UserRole;
import com.wide.common.model.query.QueryUser;
import com.wide.common.model.simple.ImportDOX;
import com.wide.common.model.simple.ImportUser;
import com.wide.util.CGUtil;
import com.wide.util.ExcelUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;
import com.wide.viewmodel.ViewUser;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;

public class UserService {

	/**
	 * 根据空user对象中的id反查user的全部内容
	 * @author cg
	 * @param user用户
	 * @return User
	 * */
	public User getUser(User user) {
		if (user.getId() != null) {
			user = User.dao.findById(user.getId());
		}
		return user;
	}
	/**
	 * 根据角色查询角色用户对照表
	 * @author cg
	 * @param id 角色id
	 * @return List<UserRole>
	 * */
	public List<UserRole> findByRoleId(String id) {
		// TODO Auto-generated method stub
		List<UserRole> list = new ArrayList<UserRole>();
		list = UserRole.dao.findByRoleId(id);
		return list;
	}
	/**
	 * DataTablesModel
	 * @author cg
	 * @param id 角色id
	 * @param pageNum 页数
	 * @param pageSize 页面条数
	 * @param queryUser 用户查询实体
	 * @return DataTablesModel
	 * */
	public DataTablesModel getPageUser(int pageNum, int pageSize, QueryUser queryUser) {
		// TODO Auto-generated method stub
		DataTablesModel userpage = User.dao.pageDataTables(pageNum, pageSize, queryUser);
		List<ViewRole> vls = new ArrayList<ViewRole>();
		if (userpage != null && !userpage.equals("")) {
			List<List<String>> rows = userpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String usertype = row.get(5) + "";// 需要从数据字典中查询
					String typename=Dict.dao.getDictByKeyType(usertype,"1003");
					row.set(5, Integer.parseInt((row.get(6) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
					row.set(6, Integer.parseInt((row.get(7) + "")) == 1 ? "<font color='#00ff66'>在线</font>" : "<font color='#C9C9C9'>离线</font>");
					row.set(7, "<a href ='#' onclick=edit('" + row.get(0).trim()
							+ "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>|" +"<a href='#' onclick=reset('" + row.get(0).trim() + "') >重置密码</a>"  );
					row.set(0, row.get(1));
					row.set(1, row.get(2));
					row.set(2, row.get(3));
					row.set(3, row.get(4));
					row.set(4, typename);

				}
			}
		}
		return userpage;
	}
	/**
	 * 根据用户id查询user对象
	 * @author cg
	 * @param id 用户id
	 * @return User
	 * */
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return User.dao.findById(id);
	}
	/**
	 * 保存用户
	 * @author cg
	 * @param ViewUser用户view
	 * @param createuser创建人
	 * */
	public void saveuserinfo(ViewUser vu, User createuser) {
		// TODO Auto-generated method stub
		Db.query("set foreign_key_checks=0;");
		if (vu.getUser().getId() != null && !vu.getUser().getId().equals("")) {
			delUserOther(vu.getUser().getId(), 1);
			delUserOther(vu.getUser().getId(), 2);
			saveUserOther(vu.getRoleids(),vu.getUser().getId(),1);
			saveUserOther(vu.getOfficeids(),vu.getUser().getId(),2);
			vu.getUser().update();
		} else {
			String ids = CGUtil.createUUid();
			vu.getUser().setId(ids);
			vu.getUser().setCreateBy(createuser.getId());
			vu.getUser().setCreateDate(new Date());
			vu.getUser().setUpdateBy(createuser.getId());
			vu.getUser().setUpdateDate(new Date());
			vu.getUser().setPassword(new Sha256Hash("111111", vu.getUser().getLoginName(), 1024).toBase64());
			vu.getUser().setNo("000000");
			vu.getUser().setDelFlag("0");
			vu.getUser().setLoginFlag("1");
			vu.getUser().setIsonline(0);
			vu.getUser().save();
			saveUserOther(vu.getRoleids(),ids,1);
			saveUserOther(vu.getOfficeids(),ids,2);
		}
		Db.query("set foreign_key_checks=1;");
	}
	/**
	 * 保存用户其他相关
	 * @author cg
	 * @param iss roleid组成的字符串
	 * @param userid用户id
	 * @param i类型
	 * */
	private void saveUserOther(String iss, String userid, int i) {
		String[] str = iss.split("[|]");
		if (str.length > 0) {
			for (String s : str) {
				switch (i) {
				case 1:
					UserRole ur = new UserRole();
					if (!s.equals("")) {
						ur.setUserId(userid);
						ur.setRoleId(s);
						ur.save();
					}
					;
				case 2:
					OfficeUser ou = new OfficeUser();
					if (!s.equals("")) {
						ou.setUserId(userid);
						ou.setOfficeId(s);
						ou.save();
					};
				}
			}
		};
	}
	/**
	 * 删除用户相关
	 * @param userid 用户id
	 * @param i类型
	 * */
	private void delUserOther(String userid, int i) {
		switch (i) {
		case 1:
			Db.update(" delete from sys_user_role where user_id = ? ", userid);
		case 2:
			Db.update(" delete from sys_office_user where user_id = ? ", userid);
		}
	}
	/**
	 * 删除用户信息
	 * @param id 用户id
	 * */
	public void deluserinfo(String id) {
		// TODO Auto-generated method stub
		Db.query("set foreign_key_checks=0;");
		delUserOther(id,1);
		delUserOther(id,2);
		User.dao.deleteById(id);
		Db.query("set foreign_key_checks=1;");
	}
    /**
     * 根据登录名和密码查询用户
     * @param loginname
     * @param password
     * @return Boolean 查询到为true
     */	
	public Boolean getUserBylogininfo(String loginname, String password) {
		
		return User.dao.getUserBylogininfo(loginname,password);
	}
	public List<ImportUser> checkExcel(File source, User user) throws Exception {
		// TODO Auto-generated method stub
		ExcelUtil excelUtil = new ExcelUtil(ImportUser.class);
		List<ImportUser> errorlist = new ArrayList<ImportUser>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(source);
			List<ImportUser> list = new ArrayList<ImportUser>();
			list = excelUtil.importExcel("", fis);
			if(list.size()>0){
				for(ImportUser iuser:list){
					ViewUser vu = new ViewUser();
					Map<String,String> mapbox = new HashMap<String,String>();
					if(TypeChecker.isEmpty(iuser.getName())||TypeChecker.isEmpty(iuser.getOfficestr())){
						errorlist.add(iuser);
					}else{
						List<Office> pofficelist = new ArrayList<Office>();
						List<Office> officelist = new ArrayList<Office>();
						pofficelist= Office.dao.find("select * from sys_office where del_flag = 0 and name like ?",iuser.getOfficestr());
						Office poffice = new Office();
						if(pofficelist.size()>0){
							poffice = pofficelist.get(0);
						}
						if(StrKit.notBlank(iuser.getDepartmaent())){
							officelist = Office.dao.find("select * from sys_office where del_flag = 0 and parent_id = ? and name like ?",poffice.getId(),iuser.getOfficestr());
							if(officelist.size()>0){
								Office officetwo = officelist.get(0);
								vu.setOfficeids(officetwo.getId()+"|");
							}else{
								vu.setOfficeids(poffice.getId()+"|");
							}
						}else{
							vu.setOfficeids(poffice.getId()+"|");
						}
					}
					vu.setRoleids("5589405c-19dd-4b8c-8bfb-a7a513c1ae26|");
					//1.保存用户
					User users =new User();
					users.setName(iuser.getName());
					users.setDuty(iuser.getDuty());
					Dict dict = getDictByName(iuser.getUsertype(),"1003");
					users.setUserType(StrKit.notNull(dict)?Integer.parseInt(dict.getDictkey()):0);
					users.setLoginName(iuser.getCardno());
					users.setCardno(iuser.getCardno());
					users.setSex(iuser.getSex().equals("男")?0:1);
					Dict dictminzu = getDictByName(iuser.getMinzu(),"1005");
					users.setNation(StrKit.notNull(dictminzu)?Integer.parseInt(dictminzu.getDictkey()):0);
					users.setYearling(Integer.parseInt(iuser.getZhouyear()));
					users.setSeniority(iuser.getSeniority());
					Dict dictzz = getDictByName(iuser.getPoliticsstatus(),"1004");
					users.setPoliticsstatus(StrKit.notNull(dictzz)?Integer.parseInt(dictzz.getDictkey()):0);
					vu.setUser(users);
					
					//2.保存用户对应机构
					//3.保存用户对应角色
					
					saveuserinfo(vu,user);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorlist;
	}
	private Dict getDictByName(String dictvalue,String type){
		List<Dict> dictlist = Dict.dao.find("select * from sys_dict where del_flag = 0 and type = '"+type+"' and dictvalue like '%"+dictvalue+"%'");
		Dict dict = null;
		if(dictlist.size()>0){
			dict = dictlist.get(0);
		}
		return dict;
	}
}
