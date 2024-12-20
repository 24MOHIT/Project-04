package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();
//		testfindByPk();
//		testfindByName();
		
		
	}

	private static void testfindByName() throws Exception {


		String name="student";
		
		RoleModel model=new RoleModel();
		
		RoleBean bean=model.findByName(name);
		
		if (bean != null) {
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}else {
			System.out.println("user not found");
		}
	} 

	private static void testfindByPk() throws Exception {
		
		int id=1;
		
		RoleModel model=new RoleModel();
		
		RoleBean bean=model.findByPk(id);
		
		if (bean != null) {
			
//			System.out.print(bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t"+bean.getDescription());
			System.out.print("\t"+bean.getCreatedBy());
			System.out.print("\t"+bean.getModifiedBy());
			System.out.print("\t"+bean.getCreatedDatetime());
			System.out.println("\t"+bean.getModifiedDatetime());
			
		}else {
			
			System.out.println("user not found");
		}
				
	}

	private static void testSearch() throws Exception {
		
		RoleBean bean=new RoleBean();
		RoleModel model=new RoleModel();
		
//		Search Field String Buffer
//		bean.setName("admin");
		
		List list=model.search(bean, 0,0);
		
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			bean = (RoleBean) it.next();
			
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t"+bean.getDescription());
			System.out.print("\t"+bean.getCreatedBy());
			System.out.print("\t"+bean.getModifiedBy());
			System.out.print("\t"+bean.getCreatedDatetime());
			System.out.println("\t"+bean.getModifiedDatetime());
				
		}
			
	}

	private static void testDelete() throws Exception {
		
		RoleModel model=new RoleModel();

		model.delete(1);
	}

	private static void testUpdate() throws Exception {
		
		RoleBean bean=new RoleBean();
		bean.setId(1);
		bean.setName("admin");
		bean.setDescription("admin");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()) );
		
		RoleModel model=new RoleModel();
		
		model.update(bean);		
		
	}

	private static void testAdd() throws Exception {
		
		RoleBean bean=new RoleBean();
		
//		bean.setId(1);
		
		bean.setName("kiosk");
		bean.setDescription("kiosk");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		RoleModel model = new RoleModel();
		
		model.add(bean);
		
	}
	
}
