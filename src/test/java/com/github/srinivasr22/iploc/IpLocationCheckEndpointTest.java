package com.github.srinivasr22.iploc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IpLocationCheckEndpointTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckIfUSIp() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/check/us?ip=1.1.1.1"))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("false"));
	}
}
