package com.gale.basics;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import com.gale.domain.Bar;
import com.gale.domain.Foo;

public class BasicsTest {

	@Test
	public void simpleStub() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);
		when(bar.getName()).thenReturn("foobar");

		String result = bar.getName();

		assertThat(result, containsString("foo"));
	}

	@Test
	public void simpleVerify() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);

		foo.getBarName();

		verify(bar).getName();
	}

	@Test(expected = RuntimeException.class)
	public void stubVoidMethod() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);
		doThrow(new RuntimeException()).when(bar).wuzzle(isA(Bar.class));

		foo.wuzzle();
	}

	@Test
	public void consecutiveStubs() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);
		when(bar.getName()).thenReturn("name1").thenReturn("name2");

		assertEquals(foo.getBarName(), "name1");
		assertEquals(foo.getBarName(), "name2");
	}

	@Test
	public void verifyTimes() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);

		foo.getBarName();
		foo.getBarName();

		verify(bar, times(2)).getName();
	}

	@Test
	public void verifyNoMore() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);

		foo.getBarName();

		verify(bar).getName();
		verifyNoMoreInteractions(bar);
	}
	
	@Test
	public void verificationInOrderTest() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);
		 
		 //execute
		 foo.setBarName("once");
		 foo.setBarName("twice");
		 
		 InOrder inOrder = inOrder(bar);
		 inOrder.verify(bar).setName("once");
		 inOrder.verify(bar).setName("twice");
	}

	@Test
	public void captorTest() {
		ArgumentCaptor<Bar> captor = ArgumentCaptor.forClass(Bar.class);
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);

		foo.wuzzle();

		verify(bar).wuzzle(captor.capture());
		assertThat(captor.getValue().getName(), containsString("wuzzle"));
	}
	
	@Test
	public void hamcrestMatchers() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);

		foo.wuzzle();

		verify(bar).wuzzle(argThat(hasBarName("wuzzle")));
	}
	
	private Matcher<Bar> hasBarName(final String string) {
		return new TypeSafeMatcher<Bar>() {

			@Override
			public boolean matchesSafely(Bar item) {
				return string.equals(item.getName());
			}

			@Override public void describeTo(Description arg0) {}
		};
	}

	@Test
	public void simpleSpy() {
		Foo foo = new Foo();
		Foo fooSpy = spy(foo);

		when(fooSpy.getBar()).thenReturn(new Bar());

		assertThat(fooSpy.getBar(), not(nullValue()));
		assertThat(fooSpy.foo(), is("foo!"));
	}

	@Test
	public void partialMock() {
		Foo foo = mock(Foo.class);
		when(foo.foo()).thenCallRealMethod();

		assertThat(foo.foo(), is("foo!"));
	}

}
