package com.mysite.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.commons.Console;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageNodeModel {

    @SlingObject
    private ResourceResolver resourceResolver;

    @Self
    private Resource resource;

    private List<PageInfo> childPages;

    @PostConstruct
    protected void init() {
        childPages = new ArrayList<>();
        Resource contentResource = resource.getChild("jcr:content");
        if (contentResource != null) {
            contentResource.getChildren().forEach(child -> {
                String title = child.getValueMap().get("jcr:title", String.class);
                if (title != null) {
                    childPages.add(new PageInfo(title));
                    
                }
                else{
                    
                }
            });
        }
    }

    public List<PageInfo> getChildPages() {
        return childPages;
    }

    public static class PageInfo {
        private String title;

        public PageInfo(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
