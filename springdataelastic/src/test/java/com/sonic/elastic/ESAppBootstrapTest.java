package com.sonic.elastic;

import com.sonic.elastic.bean.Article;
import com.sonic.elastic.bean.Book;
import com.sonic.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * ESAppBootstrapTest
 *
 * @author Sonic
 * @since 2019/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ESAppBootstrap.class})
public class ESAppBootstrapTest {

    Logger logger = LoggerFactory.getLogger(ESAppBootstrapTest.class);

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;


    @Test
    public void testRepository(){

        Book book = new Book();
        book.setId(1);
        book.setBookName("西游记");
        book.setAuthor("吴承恩");
        bookRepository.save(book);
    }

    @Test
    public void testESOperation(){
        Article article = new Article();
        article.setId(1);
        article.setTitle("Good news");
        article.setAuthor("zhangsan");
        article.setContent("Hello World");

        Index index = new Index.Builder(article).index("atguigu").type("news").build();

        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search(){

        String json = "";

        Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();
        try {
            SearchResult result = jestClient.execute(search);
            logger.info("result: {}", result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
