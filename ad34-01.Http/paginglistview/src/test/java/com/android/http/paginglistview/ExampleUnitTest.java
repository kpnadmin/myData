package com.android.http.paginglistview;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test_get_itemlist() throws Exception {
        List<ModelItem> list = new HttpItem().itemlist(1, 10);

        assertNotNull(list);
        assertEquals(10, list.size() );
    }
}