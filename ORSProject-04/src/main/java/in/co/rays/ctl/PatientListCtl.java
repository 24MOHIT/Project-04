package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.model.PatientModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/PatientListCtl", urlPatterns = { "/ctl/PatientListCtl" })
public class PatientListCtl extends BaseCtl {

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PatientBean bean = new PatientBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<PatientBean> list = null;
		List<PatientBean> next = null;

		PatientBean bean = (PatientBean) populateBean(request);
		PatientModel model = new PatientModel();

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		try {
			list = model.search(bean, pageNo,pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlistsize", next.size());
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<PatientBean> list = null;
		List<PatientBean> next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		PatientBean bean = (PatientBean) populateBean(request);
		PatientModel model = new PatientModel();
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

				ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);
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
		return ORSView.PATIENT_LIST_VIEW;
	}

}
