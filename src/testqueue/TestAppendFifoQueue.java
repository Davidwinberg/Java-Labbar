package testqueue;
import queue_singlelinkedlist.FifoQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

public class TestAppendFifoQueue {
    
    private Queue<Integer> q1;
    private Queue<Integer> q2;
	

	@BeforeEach
	void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown(){
		q1 = null;
		q2 = null;
	}

    @Test
    void TommaKöer(){
        q1.append(q2); 
        
        assertEquals(0, q1.size()); //kollar storlek == 0
        assertEquals(0, q2.size());
    }

    @Test
    void tomKöTillKö(){
        q2.offer(1); //lägger till element längst bak i listan
        q2.offer(2);

        q1.append(q2);

        assertEquals(2, q1.size()); //testar storlek == 2
        assertEquals(0, q2.size()); //testar att q2 är tömd
        assertEquals(Integer.valueOf(1), q1.poll()); //kollar ordningen och tar bort framifrån
        assertEquals(Integer.valueOf(2), q1.poll());
    }
    @Test
    void KöTillTomKö(){
        q1.offer(1); 
        q1.offer(2);

        q1.append(q2);

        assertEquals(2, q1.size());
        assertEquals(0, q2.size()); 
        assertEquals(Integer.valueOf(1), q1.poll()); 
        assertEquals(Integer.valueOf(2), q1.poll());
    }
    @Test
    void KöTillKö(){
        q1.offer(1); 
        q1.offer(2);
        q2.offer(3); 
        q2.offer(4);
        
        q1.append(q2);

        assertEquals(4, q1.size());
        assertEquals(0, q2.size()); 
        assertEquals(Integer.valueOf(1), q1.poll()); 
        assertEquals(Integer.valueOf(2), q1.poll());
        assertEquals(Integer.valueOf(3), q1.poll()); 
        assertEquals(Integer.valueOf(4), q1.poll());
    }

    @Test
    void KöTillSigSjälv(){
        q1.offer(5);
        q1.append(q1);
        

    }
}
