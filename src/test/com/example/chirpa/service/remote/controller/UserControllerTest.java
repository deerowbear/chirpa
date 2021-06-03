package com.example.chirpa.service.remote.controller;


import com.example.chirpa.model.UserModel;
import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserControllerTest {

    private static final Logger LOG = Logger.getLogger(UserControllerTest.class.getName());

    private static UserModel userModel;
    private static RestTemplate restTemplate;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        userModel = new UserModel();
        userModel.setUserName("MyTestUser33");
        userModel.setFollowerModels(new ArrayList<>());
    }



    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testList(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/ws/v1/user/list");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(new String(), headers);
        ResponseEntity<UserModel[]> response =
                restTemplate.exchange(builder.build().encode().toUriString(), HttpMethod.GET, entity, UserModel[].class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUser(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080//ws/v1/user/search/" + userModel.getUserName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(userModel.toString(), headers);
        ResponseEntity<UserModel[]> response =
                restTemplate.exchange(builder.build().encode().toUriString(), HttpMethod.GET, entity, UserModel[].class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }




}
