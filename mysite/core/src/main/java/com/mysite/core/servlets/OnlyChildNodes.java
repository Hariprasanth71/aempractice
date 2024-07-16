package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.Constants;
import org.apache.sling.api.servlets.HttpConstants;

@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Getting Only the child Nodes Page",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/onlyChildNodes"
        }
)
public class OnlyChildNodes extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(OnlyChildNodes.class);

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ServletException, IOException {
        ResourceResolver resourceResolver = req.getResourceResolver();
        // Get the parent resource
        Resource parentResource = resourceResolver.getResource("/content/mysite/us/en");
        LOG.info("\n=========ParenetResource=======");
        LOG.info("\nParent Resource Path : {}",parentResource);

        if (parentResource != null) {
            resp.getWriter().println("Parent Path: " + parentResource.getPath());
            // Iterate through the child resources
            Iterator<Resource> childResources = parentResource.listChildren();
            while (childResources.hasNext()) {
                Resource childResource = childResources.next();
                // Adapt each child resource to a Node
                Node childNode = childResource.adaptTo(Node.class);
                if (!childResource.getPath().contains("jcr:content")) {
                    try {
                        // Get the jcr:content node
                        Node contentNode = childNode.getNode("jcr:content");
                        // Get the page title
                        String pageTitle = contentNode.hasProperty("jcr:title") ? contentNode.getProperty("jcr:title").getString() : "No Title";

                        resp.getWriter().println("Child Page Path: " + childNode.getPath());
                        resp.getWriter().println("Page Title: " + pageTitle);
                        LOG.info("\nChild Page Path : {}, Page Title : {}",childNode.getPath(),pageTitle);
                        
                    } catch (RepositoryException e) {
                        LOG.error("Error getting child node path:{}",e);
                    }
                }
            }
        } else {
            // resp.getWriter().println("Parent resource not found.");
            LOG.error("\n-------Parent resource not found.---------");
        }
    }
}
