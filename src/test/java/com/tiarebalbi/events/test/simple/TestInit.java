package com.tiarebalbi.events.test.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tiarebalbi.events.process.Event;
import com.tiarebalbi.events.test.config.ApplicationTestContext;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestContext.class)
public class TestInit {
	
	@Autowired
	private Event<SimpleObject> event;
	
	@Test
	public void testeInit() {
		event.fire(new SimpleObject("Nome"));
		System.out.println("Init");
	}

}
