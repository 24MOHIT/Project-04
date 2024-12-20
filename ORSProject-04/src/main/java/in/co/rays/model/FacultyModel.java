package in.co.rays.model;

import java.awt.geom.CubicCurve2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.util.JDBCDataSource;

public class FacultyModel {
	
	public int nextPK() throws Exception {

		int PK = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			PK = rs.getInt(1);

		}
		JDBCDataSource.closeConnection(conn);
		return PK + 1;
	}

	public void add(FacultyBean bean) throws Exception {

		CollegeModel collegemodel = new CollegeModel();
		CollegeBean collegebean = collegemodel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegebean.getName());

		CourseModel coursemodel = new CourseModel();
		CourseBean coursebean = coursemodel.findByPk(bean.getCourseId());
		bean.setCourseName(coursebean.getName());

		SubjectModel subjectmodel = new SubjectModel();
		SubjectBean subjectbean = subjectmodel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectbean.getName());
		
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new Exception("email already exist..!!");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn
				.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		pstmt.setLong(1, nextPK());
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(5, bean.getGender());
		pstmt.setString(6, bean.getMobileNo());
		pstmt.setString(7, bean.getEmail());
		pstmt.setLong(8, bean.getCollegeId());
		pstmt.setString(9, bean.getCollegeName());
		pstmt.setLong(10, bean.getCourseId());
		pstmt.setString(11, bean.getCourseName());
		pstmt.setLong(12, bean.getSubjectId());
		pstmt.setString(13, bean.getSubjectName());
		pstmt.setString(14, bean.getCreatedBy());
		pstmt.setString(15, bean.getModifiedBy());
		pstmt.setTimestamp(16, bean.getCreatedDatetime());
		pstmt.setTimestamp(17, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data Add= " + i);
	}

	public void update(FacultyBean bean) throws Exception {

		CollegeModel collegemodel = new CollegeModel();
		CollegeBean collegebean = collegemodel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegebean.getName());

		CourseModel coursemodel = new CourseModel();
		CourseBean coursebean = coursemodel.findByPk(bean.getCourseId());
		bean.setCourseName(coursebean.getName());

		SubjectModel subjectmodel = new SubjectModel();
		SubjectBean subjectbean = subjectmodel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectbean.getName());
		
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new Exception("email already exist..!!");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?  where id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getMobileNo());
		pstmt.setString(6, bean.getEmail());
		pstmt.setLong(7, bean.getCollegeId());
		pstmt.setString(8, bean.getCollegeName());
		pstmt.setLong(9, bean.getCourseId());
		pstmt.setString(10, bean.getCourseName());
		pstmt.setLong(11, bean.getSubjectId());
		pstmt.setString(12, bean.getSubjectName());
		pstmt.setString(13, bean.getCreatedBy());
		pstmt.setString(14, bean.getModifiedBy());
		pstmt.setTimestamp(15, bean.getCreatedDatetime());
		pstmt.setTimestamp(16, bean.getModifiedDatetime());
		pstmt.setLong(17, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data Update= " + i);
	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id=?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Deleted= " + i);
	}
	
	public List list() throws Exception {
		return search(null, 0, 0);
	}
	

	public List search(FacultyBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		
		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");
		
		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "'");
			}
		}
		
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," +pageSize);
		}
		System.out.println("sql=" +sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new FacultyBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDatetime(rs.getTimestamp(16));
			bean.setModifiedDatetime(rs.getTimestamp(17));

			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;
	}

	public FacultyBean findByPK(long id) throws Exception {
		
		Connection conn = JDBCDataSource.getConnection();
		
		PreparedStatement pstmt=conn.prepareStatement("select * from st_faculty where id=?");
		
		pstmt.setLong(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		List list = new ArrayList();
		
		FacultyBean bean=null;
		
		while (rs.next()) {
			
			bean = new FacultyBean();
			
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDatetime(rs.getTimestamp(16));
			bean.setModifiedDatetime(rs.getTimestamp(17));		
	}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}	
	public FacultyBean findByEmail(String email) throws Exception {
		
		Connection conn=JDBCDataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where email=?");
		
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		List list = new ArrayList();
		
		FacultyBean bean=null;
		
		while (rs.next()) {
			
			bean = new FacultyBean();
			
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDatetime(rs.getTimestamp(16));
			bean.setModifiedDatetime(rs.getTimestamp(17));	
		
	}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}	
}