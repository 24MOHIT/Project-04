package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/CourseCtl", urlPatterns = {"/ctl/CourseCtl"})
public class CourseCtl  extends BaseCtl{

	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean isValid = true;
		
		String name = request.getParameter("name");
		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("name", "Invalid Name");
			isValid = false;
		}
		
		String duration = request.getParameter("duration");
		if (DataValidator.isNull(duration)) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			isValid = false;
		}
		
		String description = request.getParameter("description");
		if (DataValidator.isNull(description)) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			isValid = false;
		} else if (!DataValidator.isName(description)) {
			request.setAttribute("description", "Invalid Description");
			isValid = false;
		}
		return isValid;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		CourseBean bean = new CourseBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		populateDTO(bean, request);
		
		return bean;

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		
		CourseModel model = new CourseModel();
		CourseBean bean = (CourseBean) populateBean(request);
		try {
		if (OP_SAVE.equalsIgnoreCase(op)) {
			
			
				model.add(bean);
				ServletUtility.setSuccessMessage("Data Add Successfully..", request);
				ServletUtility.forward(getView(), request, response);
				
		}
			} catch (Exception e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Name Allready Exist...", request);
				ServletUtility.forward(getView(), request, response);
				e.printStackTrace();
			}
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
