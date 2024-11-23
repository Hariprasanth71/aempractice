package com.mysite.core.models;

import com.mysite.core.models.ArticleDemo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleModel implements ArticleDemo {

//    @ScriptVariable
//    private Page currentPage;

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

//    @Override
//    public String getPageTitle() {
//        return currentPage.getPageTitle();
//    }
}
