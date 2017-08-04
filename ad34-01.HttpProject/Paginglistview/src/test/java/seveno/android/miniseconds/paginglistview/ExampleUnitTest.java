package seveno.android.miniseconds.paginglistview;

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
    public void get_item_list() throws Exception {
     List<ModelItem> itemList =  new HttpItem().get_item_list(10, 20);

        assertNotNull(itemList);
        assertEquals(11, itemList.size());

    }
}