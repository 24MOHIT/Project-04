package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CartBean;
import in.co.rays.model.CartModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/CartCtl", urlPatterns = { "/ctl/CartCtl" })
public class CartCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;

		String customername = request.getParameter("customername");
		if (DataValidator.isNull(customername)) {
			request.setAttribute("customername", PropertyReader.getValue("error.require", "customername"));
			isValid = false;
		} else if (!DataValidator.isName(customername)) {
			request.setAttribute("customername", "Invalid customername");
			isValid = false;
		}

		String product = request.getParameter("product");
		if (DataValidator.isNull(product)) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "product"));
			isValid = false;
		}

		String transactiondate = request.getParameter("transactiondate");
		if (DataValidator.isNull(transactiondate)) {
			request.setAttribute("transactiondate", PropertyReader.getValue("error.require", "transactiondate"));
			isValid = false;
		}

		String quantityorder = request.getParameter("quantityorder");
		if (DataValidator.isNull(quantityorder)) {
			request.setAttribute("quantityorder", PropertyReader.getValue("error.require", "quantityorder"));
			isValid = false;
		} else if (!DataValidator.isInteger(quantityorder)) {
			request.setAttribute("quantityorder", "Invalid quantityorder");
			isValid = false;
		}

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CartBean bean = new CartBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCustomername(DataUtility.getString(request.getParameter("customername")));
		bean.setProduct(DataUtility.getString(request.getParameter("product")));
		bean.setTransactiondate(DataUtility.getDate(request.getParameter("transactiondate")));
		bean.setQuantityorder(DataUtility.getString(request.getParameter("quantityorder")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = DataUtility.getLong(request.getParameter("id"));
		CartModel model = new CartModel();

		if (id > 0) {

			try {
				CartBean bean = model.findByPk(id);

				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		CartBean bean = (CartBean) populateBean(request);
		CartModel model = new CartModel();
		try {
			if (OP_SAVE.equalsIgnoreCase(op)) {

				model.add(bean);
				ServletUtility.setSuccessMessage("Data Added Successfully..", request);

			}

			if (OP_UPDATE.equalsIgnoreCase(op)) {

				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data Update Successfully", request);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.CART_VIEW;
	}

}
