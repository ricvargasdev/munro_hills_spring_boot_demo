package com.xdesign.munro.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 
 * @author Ricardo Vargas
 */
@AutoConfigureMockMvc
@SpringBootTest
public class MunroRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
        
    @Test
    public void testContextLoads() throws Exception {
    	assertThat(mockMvc).isNotNull();
    }

    @Test
    public void testDefaultCall() throws Exception {
        mockMvc.perform(get("/munro/hills").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].name", containsStringIgnoringCase("Ben")));
    }
    
    @Test
    public void testCallWithCategoryTop() throws Exception {
        mockMvc.perform(get("/munro/hills?category=TOP").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].category", is("TOP")))
          .andExpect(jsonPath("$[10].category", not("MUN")));
    }

    @Test
    public void testCallWithCategoryMun() throws Exception {
        mockMvc.perform(get("/munro/hills?category=MUN").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].category", is("MUN")))
          .andExpect(jsonPath("$[10].category", not("TOP")));
    }
    
    @Test
    public void testCallHeightBetween980And985() throws Exception {
        mockMvc.perform(get("/munro/hills?minHeight=980&maxHeight=985").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].heightMeters", greaterThanOrEqualTo(980D)))
          .andExpect(jsonPath("$[0].heightMeters", lessThanOrEqualTo(985D)));
    }

    @Test
    public void testCallSortField1Asc() throws Exception {
        mockMvc.perform(get("/munro/hills?sortField1=heightMeters&sortOrder1=asc").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].heightMeters", lessThan(900D)))
          .andExpect(jsonPath("$[1].heightMeters", greaterThan(900D)));
    }

    @Test
    public void testCallSortField1Desc() throws Exception {
        mockMvc.perform(get("/munro/hills?sortField1=heightMeters&sortOrder1=desc").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].heightMeters", greaterThan(1340D)))
          .andExpect(jsonPath("$[1].heightMeters", lessThan(1340D)));
    }
    
    @Test
    public void testCallSortField2Asc() throws Exception {
        mockMvc.perform(get("/munro/hills?total=2&sortField2=name&sortOrder2=asc").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].name", is("A' Bhuidheanach Bheag")))
          .andExpect(jsonPath("$[1].name", is("A' Bhuidheanach Bheag - Glas Mheall Mor")));
    }

    @Test
    public void testCallSortField2Desc() throws Exception {
        mockMvc.perform(get("/munro/hills?total=2&sortField2=name&sortOrder2=desc").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].name", is("Tom a' Choinich - Tom a' Choinich Beag")))
          .andExpect(jsonPath("$[1].name", is("Tom a' Choinich - An Leth-chreag")));
    }    
    
    @Test
    public void testCallWithSize() throws Exception {
        mockMvc.perform(get("/munro/hills?total=2").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(2)));
    }
}