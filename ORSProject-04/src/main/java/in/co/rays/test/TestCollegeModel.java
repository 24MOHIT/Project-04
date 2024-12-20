package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.model.CollegeModel;

public class TestCollegeModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();
//		testfindByPk();
//		testfindByName();
		
		
	}

	private static void testfindByName() throws Exception {

		String name="Viit";
		
		CollegeModel model=new CollegeModel();
		
		CollegeBean bean=model.findByName(name);
		
		if (bean != null) {
			
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneno());
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
		
		CollegeModel model=new CollegeModel();
		
		CollegeBean bean=model.findByPk(id);
		
		if (bean != null) {
			
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneno());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}else {
			
			System.out.println("user not found");
		}
	}

	private static void testSearch() throws Exception {
		
		CollegeBean bean=new CollegeBean();
	
		CollegeModel model=new CollegeModel();
		
//		Search Field String Buffer
//		bean.setName("Sunrise College");
		
		List list=model.search(bean, 0, 0);
		
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			
			bean=(CollegeBean)it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneno());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}
		
	}

	private static void testDelete() throws Exception {

		CollegeModel model=new CollegeModel();
		
		model.delete(1);
	}

	private static void testUpdate() throws Exception {

		CollegeBean bean = new CollegeBean();

		bean.setId(2);
		bean.setName("PG");
		bean.setAddress("Barwani");
		bean.setState("MP");
		bean.setCity("Barwani");
		bean.setPhoneno("3216549873");
		bean.setCreatedBy("pg@gmail.com");
		bean.setModifiedBy("pg@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CollegeModel model = new CollegeModel();

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		CollegeBean bean = new CollegeBean();

//		bean.setId(1);
		bean.setName("Viit");
		bean.setAddress("Barwani");
		bean.setState("MP");
		bean.setCity("Barwani");
		bean.setPhoneno("3216549878");
		bean.setCreatedBy("viit@gmail.com");
		bean.setModifiedBy("viit@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CollegeModel model = new CollegeModel();

		model.add(bean);

	}

}
