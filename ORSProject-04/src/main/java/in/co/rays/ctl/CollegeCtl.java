package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet("/CollegeCtl")
public class CollegeCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean isValid = true;
		
		// Validate First Name
		String name = request.getParameter("name");
		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("Name", "Invalid Name");
			isValid = false;
		}
		
		String address = request.getParameter("address");
		if (DataValidator.isNull(address)) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			isValid = false;
		} else if (!DataValidator.isName(address)) {
			request.setAttribute("address", "Invalid Address");
			isValid = false;
		}
		
		String state = request.getParameter("state");
		if (DataValidator.isNull(state)) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			isValid = false;
		} else if (!DataValidator.isName(state)) {
			request.setAttribute("state", "Invalid State");
			isValid = false;
		}
		
		String city = request.getParameter("city");
		if (DataValidator.isNull(city)) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			isValid = false;
		} else if (!DataValidator.isName(city)) {
			request.setAttribute("city", "Invalid City");
			isValid = false;
		}
		
		// Validate Mobile No
				String phoneno = request.getParameter("phoneno");
				if (DataValidator.isNull(phoneno)) {
					request.setAttribute("phoneno", PropertyReader.getValue("error.require", "Phone No"));
					isValid = false;
				} else if (!DataValidator.isPhoneLength(phoneno)) {
					request.setAttribute("phoneno", "Phone No must have 10 digits");
					isValid = false;
				} else if (!DataValidator.isPhoneNo(phoneno)) {
					request.setAttribute("phoneno", "Invalid Phone No");
					isValid = false;
				}
		
		
		return isValid;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneno(DataUtility.getString(request.getParameter("phoneno")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		CollegeModel model = new CollegeModel();
		CollegeBean bean = (CollegeBean) populateBean(request);
		try {
			if (OP_SAVE.equalsIgnoreCase(op)) {

				model.add(bean);
				ServletUtility.setSuccessMessage("Data Added Successfully..!!", request);
				ServletUtility.forward(getView(), request, response);
			}
		} catch (Exception e) {
			
			ServletUtility.setBean(bean, request);
			
			ServletUtility.setErrorMessage("Name Allready Exist...", request);
			ServletUtility.forward(getView(), request, response);
		}
		
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(getView(), request, response);
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_VIEW;
	}

}
