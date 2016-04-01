import static org.junit.Assert.*;

import org.junit.Test;

public class _MailSystemTest {

	@Test
	public void newMailBoxesWithOneMessageAndFindMessage() {
		MailSystem mailSystem = new MailSystem(1);
		Mailbox mailBox = new Mailbox("1", "You have reached mailbox 1. \nPlease leave a message now.");

		assertEquals(true, mailSystem.findMailbox("1").checkPasscode("1"));
		assertEquals(mailBox.getGreeting(), mailSystem.findMailbox("1").getGreeting());
		
	}
	
}
