package com.raj.struts.customer.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.raj.hibernate.common.plugin.HibernatePlugin;
import com.raj.struts.customer.form.CustomerForm;
import com.raj.struts.customer.model.Customer;
 
public class AddCustomerAction extends Action{
 
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,HttpServletResponse response) 
        throws Exception {
 
		SessionFactory sessionFactory = 
			(SessionFactory) servlet.getServletContext().getAttribute(HibernatePlugin.KEY_NAME);
		Session session = sessionFactory.openSession();
		
		CustomerForm customerForm = (CustomerForm)form;
		Customer customer = new Customer();
		
		//copy customerform to model
		BeanUtils.copyProperties(customer, customerForm);
		
		//save it
		customer.setCreatedDate(new Date());
		
		Transaction tx = session.beginTransaction();
		session.save(customer);
		tx.commit();
		
		return mapping.findForward("success");
	  
	}
 
}
