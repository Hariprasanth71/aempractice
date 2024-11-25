package com.mysite.core.models;

import com.day.cq.wcm.api.Page;
import com.mysite.core.models.ArticleDemo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleModel implements ArticleDemo {

    @ResourcePath(paths = "/content/mysite/us/en")@Via("resource")
    Resource resource;

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    private String articleName;

    @ValueMapValue
    private int articleDate;

    @ValueMapValue
    private String articleDescription;

    @ValueMapValue
    private String articleAuthorName;

    @Override
    public String getArticleName() {
        return articleName;
    }

    @Override
    public int getArticleDate() {
        return articleDate;
    }

    @Override
    public String getArticleDescription() {
        return articleDescription;
    }

    @Override
    public String getArticleAuthorName() {
        return articleAuthorName;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getPageTitle();
    }

    @Override
    public String getHomePageName(){
        return resource.getName();
    }
}
