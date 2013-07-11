package org.scalsys.springPortletMVCHibernate.login;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.xml.bind.ParseConversionEvent;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.runner.Request;
import org.scalsys.springPortletMVCHibernate.HibernateUtil;
import org.scalsys.springPortletMVCHibernate.SchemaModel.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("VIEW")
public class LoginController {

	public LoginController() {
	}

	@ModelAttribute("exampleOfModelAttribute")
	public String exampleOfModelAttribute() {
		System.out
				.println("ModelAttribute will be tested Successfully............");
		return "example of ModelAttribute method called";
	}

	@RequestMapping
	public String defaultRender(ModelMap modelMap, RenderRequest renderRequest) {
		modelMap.addAttribute("ModelMapAttribute1",
				"Hello this is message from ModelMap Add Attribute");
		return "loginForm2";
	}

	@RequestMapping(params = "action=loginSuccess")
	public String loginSuccessAction(ModelMap modelMap,
			RenderRequest renderRequest) {
		return "loginSuccess";
	}

	@RequestMapping(params = "action=loginError")
	public String loginErrorAction(ModelMap modelMap,
			RenderRequest renderRequest) {
		return "loginError";
	}

	@RequestMapping(params = "action=home")
	public String homePageAction(ModelMap modelMap,
			RenderRequest renderRequest, @RequestParam String param1,
			@RequestParam String param2) {
		System.out.println("RequestParam : param1 : " + param1);
		System.out.println("Requestparam : param2 : " + param2);
		List<User> usersList = new LinkedList<User>();

		/*
		 * usersList.add(new User(1, "Sudhir", "sudhir123")); usersList.add(new
		 * User(2, "divyang", "divyang123")); usersList.add(new User(3,
		 * "Chirag", "chirag123")); usersList.add(new User(4, "Ravi",
		 * "ravi123")); usersList.add(new User(5, "Abhishek", "abhishek123"));
		 * usersList.add(new User(6, "Chirag", "Chirag123"));
		 */

		Session session = HibernateUtil.getSessionFactory().openSession();
		usersList = (List<User>) session.createQuery("from User").list();
		modelMap.addAttribute("usersList", usersList);
		return "home";
	}

	@ActionMapping(params = "action=loginAction")
	public void loginAndRedirection(ModelMap modelMap,
			ActionResponse actionResponse,
			@RequestParam("email") String emailId,
			@RequestParam("password") String password) {
		System.out.println("User Name : " + emailId
				+ " is Login... [Login Date : " + new Date() + "]");

		Login login = new Login(emailId, password);
		if (login.validateLogin() == true) {
			actionResponse.setRenderParameter("action", "loginSuccess");
		} else {
			actionResponse.setRenderParameter("action", "loginError");
		}
		System.out.println("login action called");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java
	 * .lang.Object)
	 */
	/*
	 * @ActionMapping(params= {"action=loginAction"}) protected ModelAndView
	 * onSubmit(Object command) throws Exception { Login login = (Login)
	 * command; return new ModelAndView("loginSuccess", "login", login); }
	 */

	@ModelAttribute("Login")
	public Login getCommandObject() {
		return new Login();
	}

	/*
	 * @ActionMapping(params = "{action=loginAction}") public void
	 * loginEvaluate(@ModelAttribute("Login") Login login, ActionRequest
	 * actionRequest, ActionResponse actionResponse) {
	 * System.out.println("inside loginEvaluate");
	 * actionResponse.setRenderParameter("jspPage", "LoginForm"); }
	 */

	@ActionMapping(params = { "action=userCreationAction" })
	public void userCreation(@ModelAttribute User user) {
		System.out.println(user);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		User user2 = (User) session.get(User.class, user.getUserId());
		if (user2 == null) {
			user2 = new User();
		}
		user2.setUsername(user.getUsername());
		user2.setPassword(user.getPassword());
		session.saveOrUpdate(user2);
		transaction.commit();
		session.close();
	}

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * binder.setDisallowedFields(new String[] { "userId" });
	 * 
	 * }
	 */

	@ModelAttribute("user")
	public User getUserObject(PortletRequest renderRequest) {
		if (renderRequest != null
				&& renderRequest.getClass() == RenderRequest.class) {
			Integer userId = (Integer) renderRequest.getAttribute("userId");
			System.out.println("Model Attribute : User Id : " + userId);
			if (userId != null) {
				Session session = HibernateUtil.getSessionFactory()
						.openSession();
				return ((User) session.get(User.class, userId));
			} else {
				return new User();
			}
		}
		return new User();
	}

	@ActionMapping(params = "action=deleteUserInfo")
	public void userdelete(ModelMap modelMap, ActionRequest actionRequest,
			ActionResponse actionResponse) {

		System.out.println("delete method called");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Integer userId = Integer.parseInt(actionRequest.getParameter("userId"));
		User user = (User) session.get(User.class, userId);
		session.delete(user);
		transaction.commit();
		session.close();
	}

	@RenderMapping(params = "action=editUserInfo")
	public String doUserUpdate(ModelMap modelMap,
			@RequestParam("userId") String userId, RenderRequest renderRequest) {
		System.out.println("userEdit called" + userId);
		renderRequest.setAttribute("userId", userId);
		Session session = HibernateUtil.getSessionFactory().openSession();

		modelMap.addAttribute("user",
				(User) session.get(User.class, Integer.parseInt(userId)));
		return "editUser";

	}

	@RenderMapping(params = "action=createUser")
	public String createUser(ModelMap modelMap, RenderRequest renderRequest) {

		modelMap.addAttribute("pageUse", "create");
		return "editUser";

	}

}
