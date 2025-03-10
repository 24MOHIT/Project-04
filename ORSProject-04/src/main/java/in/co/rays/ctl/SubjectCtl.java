package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/SubjectCtl", urlPatterns = {"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean isValid = true;
		
		String name = request.getParameter("name");
		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("Name", "Invalid Name");
			isValid = false;
		}
		
		String courseid = request.getParameter("courseid");
		if (DataValidator.isNull(courseid)) {
			request.setAttribute("courseid", PropertyReader.getValue("error.require", "CorseName"));
			isValid = false;
		} 
		
		String description = request.getParameter("description");
		if (DataValidator.isNull(description)) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("description", "Invalid Description");
			isValid = false;
		}
		
		return isValid;
	}
	
	@Override
	protected void preload(HttpServletRequest request) {

		CourseModel coursemodel = new CourseModel();

		try {
			List courselist =coursemodel.list();
			request.setAttribute("courselist", courselist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		SubjectBean bean = new SubjectBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseid")));
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
		
		SubjectModel model = new SubjectModel();
		
		SubjectBean bean = (SubjectBean) populateBean(request);
		try {	
		if (OP_SAVE.equalsIgnoreCase(op)) {
			System.out.println("aya");
				model.add(bean);
				System.out.println("aya 1");
				ServletUtility.setSuccessMessage("Data Added Successfully...", request);
				ServletUtility.forward(getView(), request, response);
		}
		
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
		}
			} catch (Exception e) {
//				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Name Allready Exist", request);
				ServletUtility.forward(getView(), request, response);

			}
		
	}
	
	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}
