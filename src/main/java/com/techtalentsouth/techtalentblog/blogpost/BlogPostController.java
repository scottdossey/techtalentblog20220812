package com.techtalentsouth.techtalentblog.blogpost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//You use @RestController if you want a controller for a REST API.
//Anything you return, is typically displayed directly.
//If you return a string, that will become your web page.
//if you return an object, it will JSONify that object and return JSON

@Controller // Make this BlogPostController class into a controller
            // class that is scanned for web mappings/endpoints.
public class BlogPostController {
	@Autowired // tell springboot to setup the blogPostRepository.
	BlogPostRepository blogPostRepository;
	private static List<BlogPost> posts = new ArrayList<>();
	
	@GetMapping(path="/")
	public String index(Model model) {
		BlogPost blogPost = new BlogPost();	
		model.addAttribute("blogPost", blogPost);
		
		model.addAttribute("posts", posts);				
		return "blogpost/index";				
	}
	
	@PostMapping(path="/")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		BlogPost dbBlogPost = blogPostRepository.save(blogPost);
		posts.add(dbBlogPost);
				
		model.addAttribute("title", dbBlogPost.getTitle());
		model.addAttribute("author", dbBlogPost.getAuthor());
		model.addAttribute("blogEntry", dbBlogPost.getBlogEntry());
		
		return "blogpost/result";					
	}

}
