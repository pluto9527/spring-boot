package com.jcfc.springboot;

import com.jcfc.springboot.entity.Article;
import com.jcfc.springboot.entity.Book;
import com.jcfc.springboot.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot12ElasticsearchApplicationTests {

	@Autowired
	JestClient jestClient;

	//建立索引
	@Test
	public void index() {
		//1. 给ES中索引（保存）一个文档
		Article article = new Article();
		article.setId(1);
		article.setAuthor("zhangsan");
		article.setTitle("好消息");
		article.setContent("Hello !");

		//2. 构建一个索引功能
		Index index = new Index.Builder(article).index("test_index").type("test_type").build();

		//3. 执行
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//搜索
	@Test
	public void search() {
		//1. 查询表达式
		String json = "{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"Hello\"\n" +
				"        }\n" +
				"    }\n" +
				"}";

		//2. 构建搜索功能
		Search search = new Search.Builder(json).addIndex("test_index").addType("test_type").build();

		//3. 执行
		try {
			SearchResult result = jestClient.execute(search);
			System.out.println(result.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	BookRepository bookRepository;

	@Test
	public void index2() {
		Book book = new Book(1, "张三", "李四");

		//索引一个文档
		bookRepository.index(book);
	}

	@Test
	public void search2() {
		//搜索
		List<Book> list = bookRepository.findByNameLike("张");

		for (Book book : list) {
			System.out.println(book);
		}
	}

	@Test
	public void contextLoads() {
	}

}
