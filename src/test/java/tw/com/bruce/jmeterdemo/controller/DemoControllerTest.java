package tw.com.bruce.jmeterdemo.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.bruce.jmeterdemo.model.bo.DemoReservationBo;
import tw.com.bruce.jmeterdemo.model.dto.DemoReservationDto;
import tw.com.bruce.jmeterdemo.service.DemoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String API_ROUTE = "/api/v1.0/demo";

    @SpyBean
    private DemoService demoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL)
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .build();
    }

    @Test
    @DisplayName("test do reservation is correct.")
    void testDemoReservation() throws Exception {
        String apiUrl = API_ROUTE.concat("/reservation");

        DemoReservationDto dto = new DemoReservationDto();
        dto.setName("bruce");
        dto.setMail("bruce.hsu@com");
        dto.setContent("hello world!");
        dto.setTrigger(LocalDateTime.now().plusDays(1));
        mockMvc.perform(
                post(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("test get reservation data is correct.")
    void testDemoReservationGetOne() throws Exception {
        String apiUrl = API_ROUTE.concat("/get/bruce");

        DemoReservationBo bo = new DemoReservationBo();
        bo.setContent("hello world!");
        bo.setMail("bruce.hsu@com");
        bo.setName("bruce");
        bo.setTrigger(LocalDateTime.now());
        doReturn(bo).when(demoService).getReservation(anyString());

        mockMvc.perform(
                get(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("test get reservation all data is correct.")
    void testDemoReservationGetAll() throws Exception {
        String apiUrl = API_ROUTE.concat("/getAll");

        DemoReservationBo bo1 = new DemoReservationBo();
        bo1.setContent("hello world!");
        bo1.setMail("bruce.hsu@com");
        bo1.setName("bruce1");
        bo1.setTrigger(LocalDateTime.now());
        DemoReservationBo bo2 = new DemoReservationBo();
        bo2.setContent("hello world!");
        bo2.setMail("bruce.hsu@com");
        bo2.setName("bruce2");
        bo2.setTrigger(LocalDateTime.now());
        doReturn(Arrays.asList(bo1, bo2)).when(demoService).getAllReservation();
        mockMvc.perform(
                get(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
