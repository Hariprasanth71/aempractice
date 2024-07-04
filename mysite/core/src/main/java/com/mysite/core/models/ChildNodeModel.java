package com.mysite.core.models;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChildNodeModel {

    @Inject
    @Via("resource")
    private String linkLabel;

    @Inject
    @Via("resource")
    private String linkPath;

    @SlingObject
    private ResourceResolver resourceResolver;

    @Self
    private SlingHttpServletRequest request;

    private Page childPage;

    public String getLinkLabel() {
        return linkLabel;
    }

    public String getLinkPath() {
        return linkPath;
    }

    @PostConstruct
    protected void init() {

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        if (pageManager != null && linkPath != null) {
            Page rootPage = pageManager.getPage(linkPath);
            if (rootPage != null) {
                Iterator<Page> rootPageIterator = rootPage.listChildren(new PageFilter(false, false), false);
                while (rootPageIterator.hasNext()) {
                    childPage = rootPageIterator.next();
                    String childTitle = childPage.getTitle();
                }
            } 
        } 
    }
}
