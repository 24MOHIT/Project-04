package in.co.rays.test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.JDBCDataSource;

public class TestUserModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();
//		testfindByPk();
//		testfindByLogin();
//		testAuthenticate();
	}

	private static void testAuthenticate() throws Exception {

		String login = "meenabhardwaj@gmail.com";
		String password = "password123";
		
		UserModel model = new UserModel();
		UserBean bean = model.authenticate(login, password);
		
		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}else {
			System.out.println("user not found");
		}
	}

	private static void testfindByLogin() throws Exception {
		
		String login="ajju@gmail.com";

		UserModel model=new UserModel();
		
		UserBean bean=model.findByLogin(login);
		

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		} else {
				System.out.println("User Not Found");
		}
				
	}

	private static void testfindByPk() throws Exception {
		
		int id=1;
		
		UserModel model=new UserModel();
		
		UserBean bean=model.findByPk(id);
		
		if (bean != null) {
			
			
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		} else {
				System.out.println("User Not Found");
		}
		
	}

	private static void testSearch() throws Exception {
		
		UserModel model=new UserModel();
		UserBean bean=new UserBean();
		
//		Search Field String Buffer
//		bean.setFirstName("Sneha");
		
		List list =model.search(bean , 0, 0);
		Iterator it =list.iterator();
		
		while (it.hasNext()) {
			
			bean=(UserBean)it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
		
		
	}

	private static void testUpdate() throws Exception {
		
		UserBean bean=new UserBean();
		
		bean.setId(2);
		bean.setFirstName("Ajay");
		bean.setLastName("Sahu");
		bean.setLogin("ajju@gmail.com");
		bean.setPassword("1233");
		bean.setDob(new Date());
		bean.setMobileNo("9876543231");
		bean.setRoleId(2);
		bean.setGender("Male");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		UserModel model=new UserModel();
		
		model.update(bean);
		
	}

	private static void testDelete() throws Exception {
		
		UserModel model=new UserModel();
		
		model.delete(3);
		
	}

	private static void testAdd() throws Exception {

	UserBean bean=new UserBean();
	
//	bean.setId(2);
	bean.setFirstName("Shivkant");
	bean.setLastName("Sikre");
	bean.setLogin("shiv@gmail.com");
	bean.setPassword("0001");
	bean.setDob(new Date());
	bean.setMobileNo("9876543001");
	bean.setRoleId(3);
	bean.setGender("Male");
	bean.setCreatedBy("admin@gmail.com");
	bean.setModifiedBy("admin@gmail.com");
	bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	
	UserModel model=new UserModel();
	
	model.add(bean);
		
	}
}
