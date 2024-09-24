package com.mysite.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class)
public class ChildPagesModel {

    @Self
    private SlingHttpServletRequest request;

    @SlingObject
    private Resource resource;

    private List<String> childPaths;
    private List<String> pageTitles;

    @PostConstruct
    protected void init() {
        // Get attributes set by the servlet
        childPaths = (List<String>) request.getAttribute("childPaths");
        pageTitles = (List<String>) request.getAttribute("pageTitles");
    }

    public List<String> getChildPaths() {
        return childPaths;
    }

    public List<String> getPageTitles() {
        return pageTitles;
    }
}
