import static org.junit.Assert.*;

import org.junit.Test;

public class _MailBoxTest {

	@Test
	public void newMailBoxWithPassCodeOne() {
		Mailbox mailBox = new Mailbox(null, null);
		mailBox.setPasscode("1");
		
		assertEquals(true, mailBox.checkPasscode("1"));
	}
	
	@Test
	public void newMailBoxWithGreetingHello() {
		Mailbox mailBox = new Mailbox(null, null);
		mailBox.setGreeting("Hello");
		
		assertEquals("Hello", mailBox.getGreeting());
	}

	@Test
	public void addNewMessagueHelloWorld() {
		
		Mailbox mailBox = new Mailbox(null, null);
		Message message = new Message("Hello World");
		
		mailBox.addMessage(message);
		
		assertEquals(message, mailBox.getCurrentMessage());
	}
	
	@Test
	public void saveTheCurrentMessagueHelloWorld() {
		
		Mailbox mailBox = new Mailbox(null, null);
		Message message = new Message("Hello World");
		
		mailBox.addMessage(message);
		mailBox.saveCurrentMessage();
		
		assertEquals(message, mailBox.getCurrentMessage());
	}
	
	@Test
	public void saveTheCurrentTwoMessagesAndRemoveAMessage() {
		
		Mailbox mailBox = new Mailbox(null, null);
		Message messageOne = new Message("Hello World One");
		Message messageTwo = new Message("Hello World Two");
		
		mailBox.addMessage(messageOne);
		mailBox.saveCurrentMessage();

		mailBox.addMessage(messageTwo);
		mailBox.saveCurrentMessage();
		
		mailBox.removeCurrentMessage();
		
		assertEquals(messageTwo, mailBox.getCurrentMessage());
	}
	
	@Test
	public void removeTheMessageNull() {
		
		Mailbox mailBox = new Mailbox(null, null);

		mailBox.saveCurrentMessage();
		
		assertEquals(null, mailBox.getCurrentMessage());
	}
}
