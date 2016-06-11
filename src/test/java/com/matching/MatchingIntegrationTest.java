package com.matching;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Integration tests
 * 
 * @author tiago
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/mvc-dispatcher-servlet-test.xml" })
@WebAppConfiguration
public class MatchingIntegrationTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testEmptyCall() throws Exception {
		mockMvc.perform(get("/matches")).andExpect(status().isNotFound());
	}

	@Test
	public void testValidWorkerCall() throws Exception {
		mockMvc.perform(get("/matches/1")).andExpect(status().isOk());
	}

	@Test
	public void testInvalidWorkerCall() throws Exception {
		mockMvc.perform(get("/matches/1000")).andExpect(status().isNotFound());
	}

}
