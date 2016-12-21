package com.demo.improve.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.demo.improve.service.ContactService;

@SlingServlet(paths="/bin/searchServlet", methods = "POST")
@Properties({
    @Property(name="service.pid", value="com.demo.improve.servlet.SaveContactServlet"),
    @Property(name="service.description",value="Servlet "),
    @Property(name="service.vendor",value="Digitas")
})
public class SaveContactServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 7703297480152359807L;
	
	@Reference
	private ContactService contactService;
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
			IOException {
		try{
			String message = request.getParameter("description");
			message = (StringUtils.isNotBlank(message) ? new String(message.getBytes("8859_1"), "UTF-8") : "");
			String name = request.getParameter("name");
			name = (StringUtils.isNotBlank(name) ? new String(name.getBytes("8859_1"), "UTF-8") : "");
			String email = request.getParameter("email");
			email = (StringUtils.isNotBlank(email) ? new String(email.getBytes("8859_1"), "UTF-8") : "");
			String phone = request.getParameter("tel");
			phone = (StringUtils.isNotBlank(phone) ? new String(phone.getBytes("8859_1"), "UTF-8") : "");
			String title = request.getParameter("title");
			title = (StringUtils.isNotBlank(title) ? new String(title.getBytes("8859_1"), "UTF-8") : "");
			contactService.save(name, email, phone, title, message);
			response.sendRedirect(request.getParameter("pathToRedirect"));
		}catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		}
	}	
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}
}