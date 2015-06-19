package com.wizmacau.androidrecipes;

import com.wizmacau.androidrecipes.model.Articles;

/**
 * Created by llitfkitfk on 6/19/15.
 */
public interface ArticlesConsumer {
    void setArticles(Articles articles);

    void handleError(String message);
}