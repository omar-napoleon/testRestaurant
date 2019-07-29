package com.restaurant;

import com.restaurant.model.Sales;
import com.restaurant.repository.SalesRepository;
import com.restaurant.response.Message;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantApplicationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private SalesRepository salesRepository;

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void createSale() throws Exception {
        Sales sale = new Sales();
        sale.setDate(LocalDate.now());
        sale.setTime(LocalTime.now());
        sale.setAmount(123.34);
        sale.setCurrencyType("clp");
        sale.setOperationCode("12345");
        sale.setPaymentType("tdc");

        Sales retorno = salesRepository.save(sale);

        assertEquals(sale.getOperationCode(), retorno.getOperationCode());
    }

    @Test
    public void testCreateSales() throws Exception {
        Sales sale = new Sales();
        sale.setDate(LocalDate.now());
        sale.setTime(LocalTime.now());
        sale.setAmount(123.34);
        sale.setCurrencyType("clp");
        sale.setOperationCode("12345");
        sale.setPaymentType("tdc");
        headers.add("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTY0MzM5NTYwNzAyLCJleHAiOjE1NjQ5NDQzNjB9.r4wKHk_Kpd63QxquK44r_DPzTA4lMLbVYeqXJyCGwD4wu0tqAFXR51vvIJzejORxwpIxTSZLguvzO8twhQV81A");
        HttpEntity<Sales> entity = new HttpEntity<>(sale, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<Message> response = restTemplate.exchange(
                createURLWithPort("/sales"), HttpMethod.POST, entity, Message.class);

        assertEquals(response.getBody().getDescription(), "success");
    }

    @Test
    public void testGetListSalesByDate() throws Exception {
        headers.add("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTY0MzM5NTYwNzAyLCJleHAiOjE1NjQ5NDQzNjB9.r4wKHk_Kpd63QxquK44r_DPzTA4lMLbVYeqXJyCGwD4wu0tqAFXR51vvIJzejORxwpIxTSZLguvzO8twhQV81A");
        HttpEntity<Sales> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<Message> response = restTemplate.exchange(
                createURLWithPort("/sales/date/" + LocalDate.now()), HttpMethod.GET, entity, Message.class);

        assertEquals(response.getBody().getDescription(), "success");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
