import static org.junit.Assert.*;

import org.junit.Test;

public class _MessageQueueTest {

	@Test
	public void newQueueShouldHaveSizeZero	() {
		MessageQueue queue = new MessageQueue();
		assertEquals(0, queue.size());
	}
	
	@Test
	public void queueWithOneMessageShouldHaveSizeOne() {
		MessageQueue queue = new MessageQueue();
		queue.add(new Message("Hola"));
		assertEquals(1, queue.size());
	}
	
	@Test
	public void removingMessageFromQueueWithTwoMessageShouldHaveSizeOne() {
		MessageQueue queue = new MessageQueue();
		queue.add(new Message("Hola"));
		queue.add(new Message("Mundo"));
		assertEquals(2, queue.size());
		queue.remove();
		assertEquals(1, queue.size());
	}
	
	@Test
	public void peekShouldReturnTheLastMessageRemovingMessage() {
		MessageQueue queue = new MessageQueue();
		Message hola = new Message("Hola");
		queue.add(hola);
		Message mundo = new Message("Mundo");
		queue.add(mundo);
		
		assertEquals(hola, queue.peek());
		queue.remove();
		assertEquals(mundo, queue.peek());
	}

}
