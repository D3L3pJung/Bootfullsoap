package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class MathematicsAddTest {

	@Mock
	private MathService mathService;

	@InjectMocks
	private Mathematics mathematics = new Mathematics();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testAddWhenNum1Is4() {
		// stubing the mathService magic behavior
		when(mathService.magic(4, 2)).thenReturn(16);
		int result = mathematics.add(4, 2);
		assertEquals(21, result);
		verify(mathService, times(1)).magic(4, 2);

	}

	@Test
	public void testAddWhenNum2Is4() {
		// stubing the mathService magic behavior
		when(mathService.magic(3, 4)).thenReturn(17);
		int result = mathematics.add(3, 4);
		assertEquals(22, result);
		verify(mathService, times(1)).magic(3, 4);
		verifyNoMoreInteractions(mathService);

	}

	@Test
	public void testAddWhenNum1andNum2Is2() {
		int result = mathematics.add(2, 2);
		assertEquals(4, result);
		verify(mathService, times(0)).magic(2, 2);
	}

	@Test
	public void testAddWhenNum1OrNum2Not4() {
		int result = mathematics.add(2, 45);
		assertEquals(47, result);
		verify(mathService, times(0)).magic(2, 45);
	}

}
