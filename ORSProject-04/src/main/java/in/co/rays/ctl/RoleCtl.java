package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name="/RoleCtl", urlPatterns = {"/ctl/RoleCtl"})
public class RoleCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		// Validate First Name
		String Name = request.getParameter("Name");
		if (DataValidator.isNull(Name)) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(Name)) {
			request.setAttribute("Name", "Invalid Name");
			isValid = false;
		}

		// Validate First Name
		String Description = request.getParameter("Description");
		if (DataValidator.isNull(Description)) {
			request.setAttribute("Description", PropertyReader.getValue("error.require", "Description"));
			isValid = false;
		} else if (!DataValidator.isName(Description)) {
			request.setAttribute("Description", "Invalid Description");
			isValid = false;
		}

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		RoleBean bean = new RoleBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setDescription(DataUtility.getString(request.getParameter("Description")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		RoleModel model = new RoleModel();

		if (id > 0) {

			try {
				RoleBean bean = model.findByPk(id);
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

		RoleBean bean = (RoleBean) populateBean(request);

		RoleModel model = new RoleModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("User Added Successfully..!!", request);
				ServletUtility.forward(getView(), request, response);
				
			} catch (Exception e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("login id already exist", request);
				ServletUtility.forward(getView(), request, response);
				
				e.printStackTrace();
			}
		} else if (op.equalsIgnoreCase(OP_RESET)) {
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}

		if (OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				model.update(bean);
				System.out.println("aya");
				ServletUtility.setSuccessMessage("User Update Successfully...", request);
				ServletUtility.forward(getView(), request, response);
			} catch (Exception e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Name already exist", request);
				ServletUtility.forward(getView(), request, response);
				e.printStackTrace();
			}
		} else if (op.equalsIgnoreCase(OP_CANCEL)) {
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}

//		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}
