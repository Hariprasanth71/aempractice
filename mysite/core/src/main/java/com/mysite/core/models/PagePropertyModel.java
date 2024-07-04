package com.mysite.core.models;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PagePropertyModel {
    
    @Self
    private Resource resource;

    @ScriptVariable
    private PageManager pageManager;

    @ScriptVariable
    private Page currentPage;

    @PostConstruct
    protected void init() {
        if (resource != null && pageManager != null) {
            currentPage = pageManager.getContainingPage(resource);
        }
    }

    public String getPageTitle() {
        if (currentPage != null) {
            return currentPage.getTitle();
        }
        return null;
    }

    public String getPageDescription() {
        if (currentPage != null) {
            return currentPage.getDescription();
        }
        return null;
    }

    public String getPageModified() {
        if(currentPage != null){
            return currentPage.getLastModifiedBy();
        }
        return null; 
    }

    public String getPageTitles() {
        if(currentPage != null){
            return currentPage.getPageTitle();
        }
        return null; 
    }

    // public ValueMap getPageModified() {
    //     if(currentPage != null){
    //         return currentPage.getProperties();
    //     }
    //     return null; 
    // }
}
