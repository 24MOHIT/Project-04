package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.FacultyBean;
import in.co.rays.model.FacultyModel;

public class TestFacultyModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();
//		testfindByPK();
//		testfindByEmail();
	}

	private static void testfindByEmail() throws Exception {

		String email= "avnish@gmail.com";
		
		FacultyModel model = new FacultyModel();
		
		FacultyBean bean=model.findByEmail(email);
		
		if (bean != null) {
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
			
		}else {
			System.out.println("Email not found");
		}
		
	}

	private static void testfindByPK() throws Exception {

		long id = 1;
		
		FacultyModel model = new FacultyModel();
		
		FacultyBean bean=model.findByPK(id);
		
		if (bean != null) {
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
			
		}else {
			System.out.println("id not found");
		}
		
	}

	private static void testSearch() throws Exception {

		FacultyModel model=new FacultyModel();
		FacultyBean bean = new FacultyBean();
		
//		Search Field String Buffer
		bean.setFirstName("Priya");
		
		List list = model.search(bean, 0,0);
		
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			
			bean = (FacultyBean) it.next();
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
		
	}

	private static void testDelete() throws Exception {

		FacultyModel model = new FacultyModel();
		
		model.delete(1);
		
	}

	private static void testUpdate() throws Exception {

		FacultyBean bean = new FacultyBean();

		bean.setId(1);
		bean.setFirstName("Aashu");
		bean.setLastName("Kumhar");
		bean.setDob(new Date());
		bean.setGender("male");
		bean.setMobileNo("1234567890");
		bean.setEmail("avnish@gmail.com");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		FacultyModel model = new FacultyModel();

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		FacultyBean bean = new FacultyBean();

//		bean.setId(1);
		bean.setFirstName("Deepak");
		bean.setLastName("Kumar");
		bean.setDob(new Date());
		bean.setGender("male");
		bean.setMobileNo("1234567891");
		bean.setEmail("dp@gmail.com");
		bean.setCollegeId(2);
		bean.setCourseId(2);
		bean.setSubjectId(2);
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		FacultyModel model = new FacultyModel();

		model.add(bean);

	}
	
}
