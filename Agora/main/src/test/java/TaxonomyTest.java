
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.util.HashMap;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by drew on 10/19/14.
// */
//public class TaxonomyTest {
//    Taxonomy tester;
//    @BeforeClass
//    public void testSetup(){
//        HashMap<String,Category> stringCategoryHashMap = new HashMap<String, Category>();
//        tester = new Taxonomy(stringCategoryHashMap);
//    }
//
//
//    @Test
//    public void testAddCategory(){
//        Category category = new Category("TestCategory");
//        assertEquals(category,tester.getCategory(category.getName()));
//    }
//
//    @Test
//    public void testRemoveCategory(){
//        Category category = new Category("TestCategory");
//        assertTrue(tester.removeCategory(category.getName()));
//    }
//
//
//
//    @AfterClass
//    public void testCleanup(){
//
//    }
//}
