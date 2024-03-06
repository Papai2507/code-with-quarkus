package org.sumit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.ws.rs.WebApplicationException;

//@QuarkusTest
class PersonControllerTest {
	//@InjectMock
	PersonRepository personRepository;

	//@Test
	public void testPanacheRepositoryMocking() throws Throwable {
		// Mocked classes always return a default value
		Assertions.assertEquals(0, personRepository.count());

		// Now let's specify the return value
		Mockito.when(personRepository.count()).thenReturn(23L);
		Assertions.assertEquals(23, personRepository.count());

		// Now let's change the return value
		Mockito.when(personRepository.count()).thenReturn(42L);
		Assertions.assertEquals(42, personRepository.count());

		// Now let's call the original method
		Mockito.when(personRepository.count()).thenCallRealMethod();
		Assertions.assertEquals(0, personRepository.count());

		// Check that we called it 4 times
		Mockito.verify(personRepository, Mockito.times(4)).count();

		// Mock only with specific parameters
		Person p = new Person();
		Mockito.when(personRepository.findById(12L)).thenReturn(p);
		Assertions.assertSame(p, personRepository.findById(12L));
		Assertions.assertNull(personRepository.findById(42L));

		// Mock throwing
		Mockito.when(personRepository.findById(12L)).thenThrow(new WebApplicationException());
		Assertions.assertThrows(WebApplicationException.class, () -> personRepository.findById(12L));

		
		Mockito.verify(personRepository, Mockito.atLeastOnce()).findById(Mockito.any());
		Mockito.verifyNoMoreInteractions(personRepository);
	}

}