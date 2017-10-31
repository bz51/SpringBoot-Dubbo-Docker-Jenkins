package com.gaoxi.controller.example;


import com.gaoxi.rsp.Result;
import com.gaoxi.entity.user.ArticleEntity;

import java.util.List;

public interface ExampleController {

    public Result<String> hello();

    /**
     * 查询指定作者的所有文章
     * @param userId 作者id
     * @return 该作者的所有文章
     */
    public Result<List<ArticleEntity>> findArticlesByUserId(String userId);
}
