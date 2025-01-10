package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.bean.PurchaseBean;
import in.co.rays.model.PatientModel;
import in.co.rays.model.PurchaseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/PatientCtl", urlPatterns = {"/ctl/PatientCtl"})

public class PatientCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean isValid = true;
		
		String name = request.getParameter("name");
		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("name", "Invalid name");
			isValid = false;
		}
		
		String visitdate = request.getParameter("visitdate");
		if (DataValidator.isNull(visitdate)) {
			request.setAttribute("visitdate", PropertyReader.getValue("error.require", "visitdate"));
			isValid = false;
		} 
		
		String mobile = request.getParameter("mobile");
		if (DataValidator.isNull(mobile)) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "mobile No"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(mobile)) {
			request.setAttribute("mobile", "Mobile No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(mobile)) {
			request.setAttribute("mobile", "Invalid mobile No");
			isValid = false;
		}
		
		String decease = request.getParameter("decease");
		if (DataValidator.isNull(decease)) {
			request.setAttribute("decease", PropertyReader.getValue("error.require", "decease"));
			isValid = false;
		} 
		
		return isValid;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		PatientBean bean = new PatientBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setVisitdate(DataUtility.getDate(request.getParameter("visitdate")));
		bean.setMobile(DataUtility.getString(request.getParameter("mobile")));
		bean.setDecease(DataUtility.getString(request.getParameter("decease")));
		
		populateDTO(bean, request);
		
		return bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long id = DataUtility.getLong(request.getParameter("id"));
		PatientModel model = new PatientModel();
		
		if (id > 0) {
			
			try {
				PatientBean bean = model.findByPk(id);
				
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String op = DataUtility.getString(request.getParameter("operation"));
		
		PatientModel model = new PatientModel();
		
		PatientBean bean = (PatientBean) populateBean(request);

		try {
		if (OP_SAVE.equalsIgnoreCase(op)) {
			
				model.add(bean);
				ServletUtility.setSuccessMessage("Data Add Successfully", request);
		}
		
		if (OP_UPDATE.equalsIgnoreCase(op)) {
			
			model.update(bean);
			ServletUtility.setSuccessMessage("Data Update Successfully", request);
		}
		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		ServletUtility.forward(getView(), request, response);
	}
	
	
	
	@Override
	protected String getView() {
		
		return ORSView.PATIENT_VIEW;
	}

	
}
