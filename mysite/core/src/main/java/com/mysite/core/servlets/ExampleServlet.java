package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletName;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "mysite/components/page",
        selectors = "once",
        extensions = "json")

//property = {
//        "sling.servlet.paths=/bin/exampleservlet",
//        "sling.servlet.selectors=one",
//        "sling.servlet.extensions=json"
//        }
public class ExampleServlet extends SlingAllMethodsServlet {

    public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res)throws IOException {
        res.setContentType("application/json");
        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        jsonObject.add("Name","HariPrasanth");
        jsonObject.add("Company","UST Global");

        res.getWriter().write(jsonObject.build().toString());

    }
}
