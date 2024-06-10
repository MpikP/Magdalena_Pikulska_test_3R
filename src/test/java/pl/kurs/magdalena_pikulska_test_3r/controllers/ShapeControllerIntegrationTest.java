package pl.kurs.magdalena_pikulska_test_3r.controllers;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.services.DynamicManagementService;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShapeControllerIntegrationTest {

    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private DynamicManagementService dynamicManagementService;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private ShapeController shapeController;

    private ModelMapper modelMapper;

    private List<Circle> circleList;
    private List<Square> squareList;
    private List<Rectangle> rectangleList;
    private List<Triangle> triangleList;




    @BeforeEach
    public void init() {

        circleList = new ArrayList<>();

        circleList.add(new Circle(2.0));
        circleList.add(new Circle(15.4));
        circleList.add(new Circle(0.9));


        squareList = new ArrayList<>();
        squareList.add(new Square(2.0));
        squareList.add(new Square(12.0));

        rectangleList = new ArrayList<>();
        rectangleList.add(new Rectangle(3.4, 4.0));
        rectangleList.add(new Rectangle(6.1, 3.3));
        rectangleList.add(new Rectangle(12.5, 14.0));

        triangleList = new ArrayList<>();
        triangleList.add(new Triangle(3.0, 4.0, 5.0, 4.0));
        triangleList.add(new Triangle(4.0, 6.0, 8.0, 6.0));

        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();
        ReflectionTestUtils.setField(dynamicManagementService, "shapeTypesString", "circle, rectangle, square, triangle");
        ReflectionTestUtils.setField(dynamicManagementService, "entityManager", entityManager);
        dynamicManagementService.init();

        mockMvc = MockMvcBuilders.standaloneSetup(shapeController).build();


    }


    @Test
    public void testCreateShapeCircle() {


        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"circle\", \"parameters\": { \"radius\": 15 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testCreateShapeSquare() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Square\", \"parameters\": { \"length\": 2.2 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void testCreateShapeRectangle() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Rectangle\", \"parameters\": { \"length\": 10.2, \"height\": 15.0 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void testCreateShapeTriangle() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Triangle\", \"parameters\": { \"lengthBase\": 4, \"lengthA\": 6, \"lengthB\": 8, \"height\": 6 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testNotCreateShapeSquare() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Square\", \"parameters\": { \"length\": -2.2 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isIn(HttpStatus.BAD_REQUEST, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Test
    public void testNotCreateShapeRectangle() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Rectangle\", \"parameters\": { \"length\": 0, \"height\": 15.0 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isIn(HttpStatus.BAD_REQUEST, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @Test
    public void testGetAll() throws Exception {


        List<Figure> listFigures = new ArrayList<>();
        listFigures.addAll(circleList);
        listFigures.addAll(squareList);
        listFigures.addAll(rectangleList);
        listFigures.addAll(triangleList);

        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Figure> pageFigures = new PageImpl<>(listFigures, pageable, listFigures.size());

        FindShapesQuery findShapesQuery = new FindShapesQuery();
        findShapesQuery.setPageable(pageable);

        when(dynamicManagementService.getFigureByCriteria(any(FindShapesQuery.class))).thenReturn(pageFigures);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("page", "0")
                        .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }



}
