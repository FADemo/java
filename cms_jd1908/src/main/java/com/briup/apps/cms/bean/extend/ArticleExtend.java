package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.Comment;

/**
 * 文章拓展类
 * @author htj
 *
 */
public class ArticleExtend extends Article {
	public static final String STATUS_UNCHECK = "未审核";
	public static final String STATUS_UNCHECK_PASS = "审核通过";
	public static final String STATUS_UNCHECK_NOPASS = "审核不通过";
	//父级表
	private Category category;
	private List<Comment> comments;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
