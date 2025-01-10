package in.co.rays.bean;

import java.util.Date;

public class CartBean extends BaseBean{

	private String customername;
	private String product;
	private Date transactiondate;
	private String quantityorder;
	
	
	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}

	public String getQuantityorder() {
		return quantityorder;
	}

	public void setQuantityorder(String quantityorder) {
		this.quantityorder = quantityorder;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return customername;
	}

}
