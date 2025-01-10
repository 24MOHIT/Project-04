package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.PatientBean;
import in.co.rays.util.JDBCDataSource;

public class PatientModel {

	public void add(PatientBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_patient values(?,?,?,?,?)");

		pstmt.setLong(1, nextpk());
		pstmt.setString(2, bean.getName());
		pstmt.setDate(3, new Date(bean.getVisitdate().getTime()));
		pstmt.setString(4, bean.getMobile());
		pstmt.setString(5, bean.getDecease());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Add = " + i);

	}

	public void update(PatientBean bean) throws Exception {


		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_patient set name=?, visit_date=?, mobile=?, decease=? where id=?");

		
		pstmt.setString(1, bean.getName());
		pstmt.setDate(2, new Date(bean.getVisitdate().getTime()));
		pstmt.setString(3, bean.getMobile());
		pstmt.setString(4, bean.getDecease());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();
		
		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Update=" + i);
	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_patient where id=?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Delete=" + i);
	}
	
	public List list() throws Exception {
		return search(null, 0, 0);
	}
	
	public List search(PatientBean bean, int pageNo, int pageSize) throws Exception {
		

		Connection conn = JDBCDataSource.getConnection();
		
		StringBuffer sql = new StringBuffer("select * from st_patient where 1=1");
		
		if (bean != null) {
			
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "'");
			}
			
			if (bean.getId() > 0) {
				sql.append(" and id = "+ bean.getId() ) ;
			}
			
		}
		
		
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		
		System.out.println("sql =" + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();
		

		while (rs.next()) {
			bean = new PatientBean();
			
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setVisitdate(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setDecease(rs.getString(5));
			
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;
	}

	public int nextpk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_patient");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
			System.out.println("max id=" + pk);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;

	}

	public PatientBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_patient where id=?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		PatientBean bean = null;

		while (rs.next()) {

			bean = new PatientBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setVisitdate(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setDecease(rs.getString(5));

		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}


}
