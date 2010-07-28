package com.gale.answers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gale.domain.Bar;
import com.gale.domain.Foo;


@RunWith(MockitoJUnitRunner.class)
public class SmartNullsTest {
	
	@Mock(answer = Answers.RETURNS_SMART_NULLS) Foo mockFoo;
	
	@Test
	public void nullTest_shouldFail() {
		Bar b = new Foo().getBar();
		
		String result = b.getName(); //throws null pointer
	}
	
	@Test
	public void smartNullTest_shouldFail() {
		Bar b = mockFoo.getBar();
		
		String result = b.getName(); //throws "smart" null pointer
	}

}
