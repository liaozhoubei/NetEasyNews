package cn.bproject.neteasynews;

import org.junit.Before;
import org.junit.Test;

import cn.bproject.neteasynews.http.NewsProtocol;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    NewsProtocol newsProtocol;

    @Before
    public void setUp() throws Exception {
        newsProtocol = new NewsProtocol();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(5, 2 + 2);
    }

}