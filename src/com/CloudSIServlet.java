package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class CloudSIServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String nom = req.getParameter("nom");
	
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Transaction tx = dataStore.beginTransaction();
		Entity person = new Entity("Personne");
		person.setProperty("nom", nom);
		person.setProperty("age", 20);
		dataStore.put(person);
		tx.commit();
		
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder.withUrl("/urlTacheDeFond").method(Method.POST));
		
		
		req.setAttribute("nom", nom);
		getServletContext().getRequestDispatcher("/pageConfirmation.jsp").forward(req, resp);
	}
}
