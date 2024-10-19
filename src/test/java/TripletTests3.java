import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mpei.Containerable;
import ru.mpei.TripletDeque;

import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TripletTests3 {

    private Deque<String> tQueue = new TripletDeque<>();
    private Containerable cQueue = (Containerable) tQueue;

    @Test
    void addTest(){
        tQueue.addFirst("one");
        tQueue.addFirst("two");
        assertEquals("two", tQueue.getFirst());
        assertEquals("one", tQueue.getLast());
        tQueue.addFirst("three");
        assertEquals("three", tQueue.getFirst());
        assertEquals("one", tQueue.getLast());
        tQueue.addFirst("four");
        tQueue.addLast("five");
        tQueue.addFirst("six");
        assertEquals("six", tQueue.getFirst());
        assertEquals("five", tQueue.getLast());
    }

    @Test
    void addTest2(){
        for (int i=0; i < 15 ;i++){
            tQueue.addFirst("n_"+i);
        }
        assertEquals("n_14", tQueue.getFirst());
        assertEquals("n_0", tQueue.getLast());
    }

    @Test
    void iterTest(){
        for (int i=0; i < 15 ;i++){
            tQueue.addFirst("n_"+i);
        }

        for (String s : tQueue) {
            System.out.println(s);
        }
    }

    @Test
    void removeTest(){
        for (int i=0; i < 15 ;i++){
            tQueue.addFirst("n_"+i);
        }

        Assertions.assertTrue(tQueue.contains("n_3"));
        tQueue.remove("n_3");
        Assertions.assertFalse(tQueue.contains("n_3"));

    }


    @Test
    public void testIsEmptyAfterAddRemoveFirst() {
        tQueue.addFirst("Something");
        boolean empty = tQueue.isEmpty();
        assertFalse( empty );
        tQueue.removeFirst();

        empty = tQueue.isEmpty();
        assertTrue(empty);

    }


    @Test
    public void testAddFirst() {
        String[] aBunchOfString = {
                "One",
                "Two",
                "Three",
                "Four"
        };

        for(String aString : aBunchOfString){
            tQueue.addFirst(aString);
        }

        for(int i = aBunchOfString.length - 1; i >= 0; i--){
            assertEquals(aBunchOfString[i], tQueue.removeFirst());
        }
    }

    @Test
    public void testAddLast() {
        String[] aBunchOfString = {
                "One",
                "Two",
                "Three",
                "Four"
        };

        for(String aString : aBunchOfString){
            tQueue.addLast(aString);
        }

        for(int i = aBunchOfString.length - 1; i >= 0; i--){
            assertEquals(aBunchOfString[i], tQueue.removeLast());
        }
    }

    @Test
    public void testAddNull(){
        try {
            tQueue.addFirst(null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException npe){
            // Continue
        } catch (Exception e){
            fail("Wrong exception catched." + e);
        }

        try {
            tQueue.addLast(null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException npe){
            // Continue
        } catch (Exception e){
            fail("Wrong exception catched." + e);
        }
    }

    @Test
    public void testRemoveFirst() {
        for(int i = 0; i < 100; i++){
            tQueue.addFirst( String.valueOf(i) );
            assertEquals(String.valueOf(i), tQueue.removeFirst());
        }


        for(int i = 0; i < 100; i++){
            tQueue.addLast( String.valueOf(i) );
            assertEquals(String.valueOf(i), tQueue.removeFirst());
        }


        for(int i = 0; i < 100; i++){
            tQueue.addLast( String.valueOf(i) );
        }

        for(int i = 0; i < 100; i++){
            assertEquals(String.valueOf(i), tQueue.removeFirst());
        }

    }

    @Test
    public void testRemoveLast() {
        for(int i = 0; i < 100; i++){
            tQueue.addFirst( String.valueOf(i) );
            assertEquals(String.valueOf(i), tQueue.removeLast());
        }


        for(int i = 0; i < 100; i++){
            tQueue.addLast( String.valueOf(i) );
            assertEquals(String.valueOf(i), tQueue.removeLast());
        }


        for(int i = 0; i < 100; i++){
            tQueue.addFirst( String.valueOf(i) );
        }

        for(int i = 0; i < 100; i++){
            assertEquals(String.valueOf(i), tQueue.removeLast());
        }
    }

  
    @Test
    public void testIterator() {

        Iterator<String> anIterator = tQueue.iterator();
        assertFalse( anIterator.hasNext() );

        for(int i = 0; i < 100; i++){
            tQueue.addFirst( String.valueOf(i) );
        }

        anIterator = tQueue.iterator();

        assertTrue( anIterator.hasNext() );

        int i = 100 - 1;
        for(String aString : tQueue){
            assertEquals( String.valueOf(i), aString);
            i--;
        }

        anIterator = tQueue.iterator();

        assertTrue( anIterator.hasNext() );

        int j = 100 - 1;
        while( anIterator.hasNext() ){
            assertEquals( String.valueOf(j), anIterator.next());
            j--;
        }
    }

    @Test
    public void testIteratorNoMoreItem() {
        Iterator<String> anIterator = tQueue.iterator();
        while( anIterator.hasNext() ){
            anIterator.next();
        }
        try {
            anIterator.next();
            fail( "Should have thrown a NoSuchElementException.");
        } catch( NoSuchElementException nsee ){
            // Continue
        } catch( Exception e ){
            fail( "Should have thrown a NoSuchElementException, but received" +
                    " : " + e);
        }
    }

    @Test
    public void testQueueBehavior(){

        String[] aBunchOfString = {
                "One", "Two", "Three", "Four"
        };

        for(String aString : aBunchOfString){
            tQueue.addFirst(aString);
        }

        for(String aString : aBunchOfString){
            assertEquals(aString, tQueue.removeLast());
        }
    }

    @Test
    void holeTest(){
        for (int i =0; i < 5; i++){
            tQueue.addFirst(i+"");
        }
        Object[] cntr = cQueue.getContainerByIndex(0);
        Assertions.assertTrue(cntr[0] != null && cntr[cntr.length-1] != null);
        tQueue.remove("2");
        Assertions.assertTrue(cntr[0] == null || cntr[cntr.length-1] == null);

        for (int i=1; i < cntr.length-2; i++){
            assertNotNull(cntr[i]);
        }
    }

    @Test
    void removeContainerTest(){
        for (int i =0; i < 15; i++){
            tQueue.addFirst(i+"");
        }
        Object[] cntr = cQueue.getContainerByIndex(0);
        Object[] cntr1 = cQueue.getContainerByIndex(1);
        Object[] cntr2 = cQueue.getContainerByIndex(2);

        Assertions.assertTrue(cntr!=null && cntr1 != null && cntr2 !=null);

        tQueue.remove("5");
        tQueue.remove("6");
        tQueue.remove("7");
        tQueue.remove("8");
        tQueue.remove("9");

        cntr = cQueue.getContainerByIndex(0);
        cntr1 = cQueue.getContainerByIndex(1);
        cntr2 = cQueue.getContainerByIndex(2);

        Assertions.assertTrue(cntr!=null && cntr1 != null && cntr2 ==null);

    }

    @BeforeEach
    void beforeEach(){
        tQueue.clear();
    }
}
