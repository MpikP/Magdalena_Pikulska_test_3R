package pl.kurs.magdalena_pikulska_test_3r.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kurs.magdalena_pikulska_test_3r.dto.FigureDao;
import pl.kurs.magdalena_pikulska_test_3r.dto.ShapeDao;
import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import pl.kurs.magdalena_pikulska_test_3r.services.CircleService;
import pl.kurs.magdalena_pikulska_test_3r.services.RectangleService;
import pl.kurs.magdalena_pikulska_test_3r.services.SquareService;
import pl.kurs.magdalena_pikulska_test_3r.services.TriangleService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShapeControllerIntegrationTest {

    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private CircleService circleService;
    private SquareService squareService;
    private RectangleService rectangleService;
    private TriangleService triangleService;

    private List<Circle> circleList;
    private List<Square> squareList;
    private List<Rectangle> rectangleList;
    private List<Triangle> triangleList;

    private ModelMapper modelMapper;


    @BeforeEach
    public void init() {
        circleService = mock(CircleService.class);
        squareService = mock(SquareService.class);
        rectangleService = mock(RectangleService.class);
        triangleService = mock(TriangleService.class);

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


        modelMapper = new ModelMapper();

        ShapeController shapeController = new ShapeController(modelMapper, circleService, squareService, rectangleService, triangleService);

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

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    public void testCreateShapeSquare() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Square\", \"parameters\": { \"length\": 2.2 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    public void testCreateShapeRectangle() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Rectangle\", \"parameters\": { \"length\": 10.2, \"height\": 15.0 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    public void testCreateShapeTriangle() {
        String url = "http://localhost:" + port + "/api/v1/shapes";

        String rectangleJson = "{ \"type\": \"Triangle\", \"parameters\": { \"lengthBase\": 4, \"lengthA\": 6, \"lengthB\": 8, \"height\": 6 } }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(rectangleJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
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

        int page = 0;
        int size = 10;

        List<ShapeDao> shapeDaoList = new ArrayList<>();
        shapeDaoList.add(new ShapeDao("Circle", FigureDao.createCircleDao(circleList.get(0))));
        shapeDaoList.add(new ShapeDao("Circle", FigureDao.createCircleDao(circleList.get(1))));
        shapeDaoList.add(new ShapeDao("Circle", FigureDao.createCircleDao(circleList.get(2))));
        shapeDaoList.add(new ShapeDao("Square", FigureDao.createSquareDao(squareList.get(0))));
        shapeDaoList.add(new ShapeDao("Square", FigureDao.createSquareDao(squareList.get(1))));
        shapeDaoList.add(new ShapeDao("Rectangle", FigureDao.createRectangleDao(rectangleList.get(0))));
        shapeDaoList.add(new ShapeDao("Rectangle", FigureDao.createRectangleDao(rectangleList.get(1))));
        shapeDaoList.add(new ShapeDao("Rectangle", FigureDao.createRectangleDao(rectangleList.get(2))));
        shapeDaoList.add(new ShapeDao("Triangle", FigureDao.createTriangleDao(triangleList.get(0))));
        shapeDaoList.add(new ShapeDao("Triangle", FigureDao.createTriangleDao(triangleList.get(1))));

        when(circleService.getAll()).thenReturn(circleList);
        when(squareService.getAll()).thenReturn(squareList);
        when(rectangleService.getAll()).thenReturn(rectangleList);
        when(triangleService.getAll()).thenReturn(triangleList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes/all")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(shapeDaoList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(page));

        verify(circleService, times(1)).getAll();
        verify(squareService, times(1)).getAll();
        verify(rectangleService, times(1)).getAll();
        verify(triangleService, times(1)).getAll();
    }


    @Test
    public void testGetFiguresByTypeCircle() throws Exception {

        int page = 0;
        int size = 10;
        String type = "Circle";

        List<ShapeDao> shapeDaoList = new ArrayList<>();
        shapeDaoList.add(new ShapeDao("Circle", FigureDao.createCircleDao(circleList.get(0))));
        shapeDaoList.add(new ShapeDao("Circle", FigureDao.createCircleDao(circleList.get(1))));
        shapeDaoList.add(new ShapeDao("Circle", FigureDao.createCircleDao(circleList.get(2))));

        when(circleService.getAll()).thenReturn(circleList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("type", type)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(shapeDaoList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(page));

        verify(circleService, times(1)).getAll();

    }


    @Test
    public void testGetFiguresByTypeSquareAndLengthFrom1AndLengthTo5() throws Exception {
        Double lengthFrom = 1.0;
        Double lengthTo = 5.0;
        int page = 0;
        int size = 10;
        String type = "Square";

        List<ShapeDao> shapeDaoList = new ArrayList<>();
        shapeDaoList.add(new ShapeDao("Square", FigureDao.createSquareDao(squareList.get(0))));

        when(squareService.getAllByLengthBetween(lengthFrom, lengthTo)).thenReturn(Arrays.asList(squareList.get(0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("type", type)
                        .param("LengthFrom", String.valueOf(lengthFrom))
                        .param("LengthTo", String.valueOf(lengthTo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(shapeDaoList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(page));

        verify(squareService, times(1)).getAllByLengthBetween(lengthFrom, lengthTo);

    }


    @Test
    public void testGetFiguresByTypeRectangleAndAreaTo22() throws Exception {
        Double areaTo = 22.0;
        int page = 0;
        int size = 10;
        String type = "Rectangle";

        List<ShapeDao> shapeDaoList = new ArrayList<>();
        shapeDaoList.add(new ShapeDao("Rectangle", FigureDao.createRectangleDao(rectangleList.get(0))));
        shapeDaoList.add(new ShapeDao("Rectangle", FigureDao.createRectangleDao(rectangleList.get(1))));

        when(rectangleService.getAllByAreaLessThanEqual(areaTo)).thenReturn(Arrays.asList(rectangleList.get(0), rectangleList.get(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("type", type)
                        .param("AreaTo", String.valueOf(areaTo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(shapeDaoList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(page));

        verify(rectangleService, times(1)).getAllByAreaLessThanEqual(areaTo);

    }


    @Test
    public void testGetFiguresByTypeTriangleAndPerimeterFrom100() throws Exception {
        Double perimeterFrom = 100.0;
        int page = 0;
        int size = 10;
        String type = "Triangle";

        List<ShapeDao> shapeDaoList = new ArrayList<>();

        when(triangleService.getAllByPerimeterGreaterThanEqual(perimeterFrom)).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("type", type)
                        .param("PerimeterFrom", String.valueOf(perimeterFrom))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(shapeDaoList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(page));

        verify(triangleService, times(1)).getAllByPerimeterGreaterThanEqual(perimeterFrom);

    }


    @Test
    public void testGetFiguresByTypeTriangleAndCreatedFrom2024_05_01() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate createdFrom = LocalDate.parse("2024-05-01", formatter);
        int page = 0;
        int size = 10;
        String type = "Triangle";

        List<ShapeDao> shapeDaoList = new ArrayList<>();
        shapeDaoList.add(new ShapeDao("Triangle", FigureDao.createTriangleDao(triangleList.get(0))));
        shapeDaoList.add(new ShapeDao("Triangle", FigureDao.createTriangleDao(triangleList.get(1))));

        when(triangleService.getAllByCreatedTimeGreaterThanEqual(createdFrom)).thenReturn(Arrays.asList(triangleList.get(0), triangleList.get(1)));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("type", type)
                        .param("CreatedFrom", String.valueOf(createdFrom))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(shapeDaoList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(page));

        verify(triangleService, times(1)).getAllByCreatedTimeGreaterThanEqual(createdFrom);

    }

}
