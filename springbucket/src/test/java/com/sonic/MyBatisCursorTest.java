package com.sonic;

import com.sonic.bean.Coffee;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MyBatisCursorTest
 *
 * @author Sonic
 * @since 2019/4/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClientApplication.class})
public class MyBatisCursorTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testCursor() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            Cursor<Coffee> coffees = sqlSession.selectCursor("queryCoffee");

            Iterator<Coffee> iter = coffees.iterator();

            List<Coffee> smallChunk = new ArrayList(10);
            while (iter.hasNext()) {
                // Fetch next 10 employees
                for (int i = 0; i < 3 && iter.hasNext(); i++) {
                    Coffee coffee = iter.next();
                    System.out.println("Coffee: " + coffee);
                    smallChunk.add(coffee);
                }
                smallChunk.clear();

            }
        } finally {
            sqlSession.close();
        }

    }

}
