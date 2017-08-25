package com.idoctors.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.idoctors.domain.Doctor;
import com.idoctors.repositories.DoctorRepository;
import com.idoctors.service.impl.DoctorServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorServiceTest {
	
	@Mock
	private DoctorRepository doctorRepository;
	
	@InjectMocks
	private DoctorServiceImpl doctorService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllDoctors(){
		List<Doctor> doctorList = new ArrayList<Doctor>();
		
		doctorList.add(new Doctor(1, "Shukri", "Shukriev", "shukri@shukriev.com"));
		doctorList.add(new Doctor(2, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(3, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(4, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(5, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
			
		when(doctorRepository.findAll()).thenReturn(doctorList);
		
		Iterable<Doctor> doctorServiceResult = doctorService.findAllDoctors();
		List<Doctor> resultDoctorList = new ArrayList<Doctor>();
		
		doctorServiceResult.iterator().forEachRemaining(resultDoctorList::add);

		assertEquals(5, resultDoctorList.size());
	}
	
	@Test
	public void testGetDoctorById() {
		Doctor doctor = new Doctor(1, "Shukri", "Shukriev", "shukri@shukriev.com");
		
		when(doctorRepository.findOne(1)).thenReturn(doctor);
		
		Doctor doctorServiceResult = doctorService.getDoctorById(1);
		
		assertEquals(new Integer(1), doctorServiceResult.getId());
		assertEquals("Shukri", doctorServiceResult.getFirstName());
		assertEquals("Shukriev", doctorServiceResult.getLastName());
		assertEquals("shukri@shukriev.com", doctorServiceResult.getEmail());
	}
	
	@Test
	public void testCreateDoctor() {
		Doctor drIvanPivanovich = new Doctor(6, "Ivan", "Pivanovich", "Ivan@Pivanovich.org");

		when(doctorRepository.save(drIvanPivanovich)).thenReturn(drIvanPivanovich);
		when(doctorRepository.findOne(6)).thenReturn(drIvanPivanovich);

		int doctorId = doctorService.saveDoctor(drIvanPivanovich);
		
		Doctor doctorServiceResult = doctorService.getDoctorById(doctorId);
		
		assertEquals(new Integer(6), doctorServiceResult.getId());
		assertEquals("Ivan", doctorServiceResult.getFirstName());
		assertEquals("Pivanovich", doctorServiceResult.getLastName());
		assertEquals("Ivan@Pivanovich.org", doctorServiceResult.getEmail());
	}
	
	@Test
	public void testDeleteDoctor() 
	{
		List<Doctor> doctorList = new ArrayList<Doctor>();
		
		doctorList.add(new Doctor(1, "Shukri", "Shukriev", "shukri@shukriev.com"));
		doctorList.add(new Doctor(2, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(3, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(4, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(5, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		doctorList.add(new Doctor(6, "Ivan", "Pivanovich", "Ivan@Pivanovich.org"));
		
		doctorService.deleteDoctorById(6);
		
		verify(doctorRepository, times(1)).delete(6);
	}
}
