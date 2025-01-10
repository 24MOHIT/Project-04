package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CartBean;
import in.co.rays.model.CartModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = ("/CartListCtl"), urlPatterns = { "/ctl/CartListCtl" })
public class CartListCtl extends BaseCtl {

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CartBean bean = new CartBean();

		bean.setCustomername(DataUtility.getString(request.getParameter("customername")));
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<CartBean> list = null;
		List<CartBean> next = null;

		CartModel model = new CartModel();
		CartBean bean = (CartBean) populateBean(request);

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		try {
			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlistsize", next.size());

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CartBean> list = null;
		List<CartBean> next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		CartBean bean = (CartBean) populateBean(request);
		CartModel model = new CartModel();
		try {

			if (OP_NEXT.equalsIgnoreCase(op)) {
				pageNo++;
			}

			if (OP_PREVIOUS.equalsIgnoreCase(op)) {
				pageNo--;
			}

			if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				for (String id : ids) {

					model.delete(DataUtility.getLong(id));

				}
			}
			if (OP_NEW.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.CART_CTL, request, response);
				return;
			}
			if (OP_SEARCH.equalsIgnoreCase(op)) {
				pageNo = 1;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlistsize", next.size());

			ServletUtility.setList(list, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getView() {

		return ORSView.CART_LIST_VIEW;
	}

}
