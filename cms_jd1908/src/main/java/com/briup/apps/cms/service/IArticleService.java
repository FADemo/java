package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.utils.CustomerException;

public interface IArticleService {
	List<Article> findAll();
	
	//查询所有文章信息
	List<ArticleExtend> cascadeFindAll();
	
	//根据文章id查询文章
	ArticleExtend findById(long id);
	
	void saveOrUpdate(Article article) throws CustomerException;
	
	void deleteById(long id) throws CustomerException;
}
