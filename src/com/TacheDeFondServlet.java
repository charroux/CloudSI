package com;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class TacheDeFondServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Personne");
		PreparedQuery pq = dataStore.prepare(query);
		for(Entity e: pq.asIterable()){
			String nom = (String) e.getProperty("nom");
			System.out.println("Je suis la TacheDeFondServlet " + nom);
		}
	
	}
}
