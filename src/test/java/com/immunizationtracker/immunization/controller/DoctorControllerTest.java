package com.immunizationtracker.immunization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.service.DoctorService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class DoctorControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    private List<Doctor> doctorList;

    @BeforeEach
    void setUp() throws Exception
    {
        doctorList = new ArrayList<>();

        doctorList.add(new Doctor("Mr. Magoo"));
        doctorList.add(new Doctor("Tom Clancy"));
        doctorList.add(new Doctor("Mr. Bill"));
;
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void listAllDoctors() throws Exception
    {
        String apiUrl = "/doctors/alldoctors";

        Mockito.when(doctorService.findAll()).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper(); // apart of jackson dependency obj -> json
        String er = mapper.writeValueAsString(doctorList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    void addNewDoctor() throws Exception
    {
        String apiUrl = "/doctors/doctor";

//        Permission p1 = new Permission(guardianid, doctorid);
        Doctor d1 = new Doctor("A Test Doctor");
        d1.setDoctorid(60);

        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(d1);

        Mockito.when(doctorService.save(any(Doctor.class))).thenReturn(d1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(courseString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
}
