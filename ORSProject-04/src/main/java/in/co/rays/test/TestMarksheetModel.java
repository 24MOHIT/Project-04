package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.model.MarksheetModel;

public class TestMarksheetModel {

	public static void main(String[] args) throws Exception {
		
//		testadd();
//		testUpdate();
//		testDelete();
		testSearch();
//		testfindByPK();
//		testfindByRollNo();
	}
	
	private static void testfindByRollNo() throws Exception {

		String rollno="BE101";
		
		MarksheetModel model=new MarksheetModel();
		
		MarksheetBean bean=model.findByRollNo(rollno);
		
		if (bean != null) {
			
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		} else {
			System.out.println("user not found");
		}
		
	}

	private static void testfindByPK() throws Exception {

		long id=1;
		
		MarksheetModel model=new MarksheetModel();
		
		MarksheetBean bean=model.findByPK(id);
		
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		} else {
			System.out.println("id not found");
		}
		
	}

	private static void testSearch() throws Exception {
		
		MarksheetModel model=new MarksheetModel();
		
		MarksheetBean bean=new MarksheetBean();
		
//		Search Field String Buffer
		bean.setName("Pooja Verma");
		
		List list=model.search(bean, 0,0);
		
		Iterator it=list.iterator();
		
		while (it.hasNext()) {
			
			bean=(MarksheetBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}
		
	}

	private static void testDelete() throws Exception {

		MarksheetModel	model=new MarksheetModel();
		
		model.delete(2);
	}

	private static void testUpdate() throws Exception {
		
		MarksheetBean bean=new MarksheetBean();
		
		bean.setId(1);
		bean.setRollNo("BE101");
		bean.setStudentId(1);
		bean.setChemistry(50);
		bean.setPhysics(50);
		bean.setMaths(50);
		bean.setCreatedBy("omni");
		bean.setModifiedBy("omni");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
				
		MarksheetModel model=new MarksheetModel();
		
		model.update(bean);
		
	}
	
	private static void testadd() throws Exception {
		
		MarksheetBean bean=new MarksheetBean();
		
//		bean.setId(2);
		bean.setRollNo("BE102");
		bean.setStudentId(2);
		bean.setChemistry(60);
		bean.setPhysics(60);
		bean.setMaths(60);
		bean.setCreatedBy("om");
		bean.setModifiedBy("om");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
				
		MarksheetModel model=new MarksheetModel();
		
		model.add(bean);
		
	}
	
}
