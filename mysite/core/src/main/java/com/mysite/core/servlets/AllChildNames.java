package com.mysite.core.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;


@Component(service = Servlet.class, property = {
		Constants.SERVICE_DESCRIPTION + "=Getting All the Child Node Details",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/bin/allChildrenNodes" })
public class AllChildNames extends SlingAllMethodsServlet{

	List<Node> childrenList = null;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServerException, IOException {
		try {
			childrenList = new ArrayList<Node>();
			 resp.setHeader("Content-Type", "text/html");
			PrintWriter pw = resp.getWriter();
					Node node = req.getResourceResolver().getResource("/content/mysite/us/en")
					.adaptTo(Node.class);
					//+ "/jcr:content"

			collectChildList(node);
		
			Iterator<Node> it = childrenList.iterator();
			while (it.hasNext() ) {
				pw.write(it.next().getPath()+"<br>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resp.getWriter().close();
		}
	}
	
	private void collectChildList(Node node) {
		try {
			childrenList.add(node);
			if (node.hasNodes()) {
				NodeIterator ni = node.getNodes();
				while (ni.hasNext()) {
						collectChildList(ni.nextNode());
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

}
