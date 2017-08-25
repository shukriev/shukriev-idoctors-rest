package com.idoctors.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.idoctors.controllers.DoctorController;
import com.idoctors.domain.Doctor;
import com.idoctors.service.impl.DoctorServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private DoctorServiceImpl doctorService;

	@InjectMocks
	DoctorController doctorController;
	
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);    	
    	mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }
    
    @Test
	public void testGetAllDoctorsSuccessfully() throws Exception {
		List<Doctor> doctors = Arrays.asList(new Doctor(1, "Shukri", "Shukriev", "shukri@shukriev.com"), 
					new Doctor(2, "Shukri2", "Shukriev2", "shukri2@shukriev.com"));
		
		when(doctorService.findAllDoctors()).thenReturn(doctors);
		when(doctorService.getDoctorById(1)).thenReturn(new Doctor(1, "Shukri", "Shukriev", "shukri@shukriev.com"));
		mockMvc.perform(get("/doctor/6"));
//			.andExpect(status().isOk());
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$[0].id", is(1)))
//			.andExpect(jsonPath("$[0].firstName", is("Shukri")))
//			.andExpect(jsonPath("$[0].lastName", is("Shukriev")))
//			.andExpect(jsonPath("$[0].email", is("shukri@shukriev.com")))
//			.andExpect(jsonPath("$[0].id", is(1)))
//			.andExpect(jsonPath("$[0].firstName", is("Shukri2")))
//			.andExpect(jsonPath("$[0].lastName", is("Shukriev2")))
//			.andExpect(jsonPath("$[0].email", is("shukri2@shukriev.com")));
		
		verify(doctorService, times(1)).findAllDoctors();
		verifyNoMoreInteractions(doctorService);

	}
	
}
