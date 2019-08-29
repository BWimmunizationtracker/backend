package com.immunizationtracker.immunization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.service.DoctorService;
import com.immunizationtracker.immunization.service.GuardianService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GuardianControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuardianService guardianService;

    private List<Guardian> guardianList;

    @BeforeEach
    void setUp() throws Exception
    {
        guardianList = new ArrayList<>();

        guardianList.add(new Guardian("firstname1", "lastname1"));
        guardianList.add(new Guardian("firstname2", "lastname2"));
        guardianList.add(new Guardian("firstname3", "lastname3"));
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void listAllGuardians() throws Exception
    {
        String apiUrl = "/guardians/allguardians";

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
