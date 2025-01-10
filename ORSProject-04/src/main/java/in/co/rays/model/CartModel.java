package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CartBean;
import in.co.rays.util.JDBCDataSource;

public class CartModel {

	public void add(CartBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_cart values(?,?,?,?,?)");

		pstmt.setLong(1, nextpk());
		pstmt.setString(2, bean.getCustomername());
		pstmt.setString(3, bean.getProduct());
		pstmt.setDate(4, new Date(bean.getTransactiondate().getTime()));
		pstmt.setString(5, bean.getQuantityorder());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Add = " + i);

	}

	public void update(CartBean bean) throws Exception {


		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_cart set customer_name=?, product=?, transaction_date=?, quantity_ordered=? where id=?");

		
		
		pstmt.setString(1, bean.getCustomername());
		pstmt.setString(2, bean.getProduct());
		pstmt.setDate(3, new Date(bean.getTransactiondate().getTime()));
		pstmt.setString(4, bean.getQuantityorder());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();
		
		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Update=" + i);
	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_cart where id=?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Delete=" + i);
	}
	
	public List list() throws Exception {
		return search(null, 0, 0);
	}
	
	public List search(CartBean bean, int pageNo, int pageSize) throws Exception {
		

		Connection conn = JDBCDataSource.getConnection();
		
		StringBuffer sql = new StringBuffer("select * from st_cart where 1=1");
		
		if (bean != null) {
			
			if (bean.getCustomername() != null && bean.getCustomername().length() > 0) {
				sql.append(" and customer_name like '" + bean.getCustomername() + "'");
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
			bean = new CartBean();
			
			bean.setId(rs.getLong(1));
			bean.setCustomername(rs.getString(2));
			bean.setProduct(rs.getString(3));
			bean.setTransactiondate(rs.getDate(4));
			bean.setQuantityorder(rs.getString(5));
			
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;
	}

	public int nextpk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_cart");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
			System.out.println("max id=" + pk);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;

	}

	public CartBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_cart where id=?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		CartBean bean = null;

		while (rs.next()) {

			bean = new CartBean();

			bean.setId(rs.getLong(1));
			bean.setCustomername(rs.getString(2));
			bean.setProduct(rs.getString(3));
			bean.setTransactiondate(rs.getDate(4));
			bean.setQuantityorder(rs.getString(5));
			

		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}
	
}
