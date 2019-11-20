package com.briup.apps.cms.web.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 文章控制器类
 * @author htj
 *
 */
//@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	
	@GetMapping("findAll")
	public Message findAll(){
		List<Article> list = articleService.findAll();
		return MessageUtil.success(list);
	}
	
	//查询所有
	@GetMapping("cascadeFindAll")
	public Message cascadeFindAll(){
		List<ArticleExtend> list = articleService.cascadeFindAll();
		return MessageUtil.success(list);
	}
	
	//通过id查询
	@GetMapping("findById")
	public Message findById(long id){
		ArticleExtend articleExtend = articleService.findById(id);
		return MessageUtil.success(articleExtend);
	}
	
	/*@ApiOperation(value = "保存或更新")
	@PostMapping("saveOrupdate")	//保存一般用post
	public Message saveOrupdate(
			@ApiParam(value="编号") @RequestParam(value = "id", required = false) Long id,
			@ApiParam(value="标题",required = true) @RequestParam(value = "title") String title,
			@ApiParam(value="内容",required = true) @RequestParam(value = "content") String content,
			@ApiParam(value="源内容") @RequestParam(value = "source", required = false) String source,
			@ApiParam(value="栏目id") @RequestParam(value = "categoryId", required = false) Long categoryId,
			@ApiParam(value="作者id") @RequestParam(value = "authorId", required = false) Long authorId) {
		
		Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setContent(content);
		article.setSource(source);
		article.setCategoryId(categoryId);
		article.setAuthorId(authorId);
		articleService.saveOrUpdate(article);
		return MessageUtil.success("更新成功");
	}*/
	
	@ApiOperation(value = "保存或更新文章信息",notes = "如果参数中包含id后端认为是更新操作，如果参数中不包含id认为是插入操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号",paramType = "form",required = false),
            @ApiImplicitParam(name = "title", value = "标题",paramType = "form",required = true),
            @ApiImplicitParam(name = "content", value = "内容",paramType = "form",required = true),
            @ApiImplicitParam(name = "source", value = "源内容",paramType = "form",required = false),
            @ApiImplicitParam(name = "categoryId", value = "所属栏目id",paramType = "form",required = true),
            @ApiImplicitParam(name = "authorId", value = "所属作者id",paramType = "form",required = false)
    })
    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(
            Long id,
            @NotNull String title,
            @NotNull String content,
            String source,
            @NotNull long categoryId,
            Long authorId){
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setSource(source);
        article.setCategoryId(categoryId);
        article.setAuthorId(authorId);
        articleService.saveOrUpdate(article);
        return MessageUtil.success("更新成功");
    }
	
	@ApiOperation("通过id删除")
	@ApiImplicitParams(
		@ApiImplicitParam(name="id",value="编号",required=true,paramType = "query")	
	)
	@GetMapping("deleteById")
	public Message deleteById(@RequestParam("id") Long id) {
		articleService.deleteById(id);
		return MessageUtil.success("删除成功");
		
	}
}
