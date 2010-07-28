package com.gale.answers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gale.domain.Foo;

@RunWith(MockitoJUnitRunner.class)
public class DeepStubsTest {
	
	@Mock(answer = Answers.RETURNS_DEEP_STUBS) Foo mockFoo;
	
	@Test
	public void deepStubsTest() {
		when(mockFoo.getBar().getName()).thenReturn("deep");
		
		String result = mockFoo.getBar().getName();

		assertEquals("deep", result);
	}

}
