package com.gale.annotations;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gale.domain.Bar;
import com.gale.domain.Foo;

@RunWith(MockitoJUnitRunner.class)
public class InjectionTest {
	
	@Mock private Bar bar;
	@InjectMocks private Foo foo = new Foo();

	@Test
	public void simpleStub() {
		//look Ma, no setter!
		when(bar.getName()).thenReturn("foobar");
		
		String result = foo.getBarName();
		
		assertThat(result, containsString("foo"));
	}
	
	
	//imagine..
	//@InjectMocks private CreateReaderPortlet portlet = new CreateReaderPortlet();
	//@Mock private ReaderRepository mockReaderRepository;
	//@Mock private ReaderService mockReaderService;
	//@Mock private BannerManager bannerManager;
	//@Mock private PortletValidator mockPortletValidator;
	//@Mock private UserSessionFacade userSessionFacade;
	
	
	@Captor ArgumentCaptor<Bar> captor;
	@Test
	public void captorTest() {
		Foo foo = new Foo();
		Bar bar = mock(Bar.class);
		foo.setBar(bar);
		
		foo.wuzzle();
		
		verify(bar).wuzzle(captor.capture());
		assertThat(captor.getValue().getName(), containsString("wuzzle"));
	}
	
}
