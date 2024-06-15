package pl.kurs.magdalena_pikulska_test_3r.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import pl.kurs.magdalena_pikulska_test_3r.services.DynamicManagementService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ShapeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DynamicManagementService dynamicManagementService;

    private List<Circle> circleList;
    private List<Square> squareList;
    private List<Rectangle> rectangleList;
    private List<Triangle> triangleList;

    @BeforeEach
    public void init() {

        circleList = new ArrayList<>();

        circleList.add(new Circle(5.0));
        circleList.add(new Circle(15.4));


        squareList = new ArrayList<>();
        squareList.add(new Square(12.0));

        rectangleList = new ArrayList<>();
        Rectangle rectangle = new Rectangle(3.4, 4.0);
        String dateString = "2024-06-10T10:29:33.017+00:00";
        OffsetDateTime odt = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Instant instant = odt.toInstant();
        Timestamp timestamp = Timestamp.from(instant);
        rectangle.setCreatedTime(timestamp);
        rectangleList.add(rectangle);

        triangleList = new ArrayList<>();
        triangleList.add(new Triangle(3.0, 4.0, 5.0, 4.0));
        triangleList.add(new Triangle(4.0, 6.0, 8.0, 6.0));
    }

    @Test
    void shouldCreateCircle() throws Exception {
        String shapeJson = "{\"type\":\"circle\", \"radius\":5}";
        Circle circle = new Circle(5.0);
        when(dynamicManagementService.addFigure(any(Circle.class))).thenReturn(circle);


        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shapeJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldCreateRectangle() throws Exception {
        String shapeJson = "{\"type\":\"Rectangle\", \"length\":2, \"height\":3}";
        Rectangle rectangle = new Rectangle(2.0, 3.0);
        when(dynamicManagementService.addFigure(any(Rectangle.class))).thenReturn(rectangle);

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shapeJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldCreateSquare() throws Exception {
        String shapeJson = "{\"type\":\"square\", \"length\":5}";
        Square square = new Square(5.0);
        when(dynamicManagementService.addFigure(any(Square.class))).thenReturn(square);

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shapeJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldCreateTriangle() throws Exception {
        String shapeJson = "{\"type\":\"triangle\", \"lengthBase\":3, \"lengthA\":4, \"lengthB\":5, \"height\":4}";
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, 4.0);
        when(dynamicManagementService.addFigure(any(Triangle.class))).thenReturn(triangle);

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shapeJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateCirclexxx() throws Exception {
        String shapeJson = "{\"type\":\"circlexxx\", \"radius\":5}";

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shapeJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotCreateSquareLength0() throws Exception {
        String shapeJson = "{\"type\":\"square\", \"length\":0}";

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shapeJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetFiguresByTypeCircleAndParamsRadiusFrom4AndRadiusTo6() throws Exception {
        String type = "circle";
        Double radiusFrom = 4.0;
        Double radiusTo = 16.0;
        int page = 0;
        int size = 10;

        PageImpl<Figure> pageImpl = (PageImpl<Figure>) (PageImpl<?>) new PageImpl<>(circleList);
        when(dynamicManagementService.getFigureByCriteria(any())).thenReturn(pageImpl);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("type", type)
                        .param("radiusFrom", String.valueOf(radiusFrom))
                        .param("radiusTo", String.valueOf(radiusTo))
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].type").value("Circle"))
                .andExpect(jsonPath("$.content[0].radius").value(5.0));
    }


    @Test
    void shouldGetFiguresByTypeSquareAndParamsLengthFrom4() throws Exception {
        String type = "square";
        Double lengthFrom = 4.0;
        int page = 0;
        int size = 10;

        PageImpl<Figure> pageImpl = (PageImpl<Figure>) (PageImpl<?>) new PageImpl<>(squareList);
        when(dynamicManagementService.getFigureByCriteria(any())).thenReturn(pageImpl);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("type", type)
                        .param("lengthFrom", String.valueOf(lengthFrom))
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].type").value("Square"))
                .andExpect(jsonPath("$.content[0].length").value(12.0));
    }


    @Test
    void shouldGetFiguresByTypeRectangleAndCreatedTo2024_06_12() throws Exception {
        String type = "Rectangle";
        String createdTo = "2024-06-12";
        int page = 0;
        int size = 10;

        PageImpl<Figure> pageImpl = (PageImpl<Figure>) (PageImpl<?>) new PageImpl<>(rectangleList);
        when(dynamicManagementService.getFigureByCriteria(any())).thenReturn(pageImpl);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("type", type)
                        .param("createdTo", createdTo)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].type").value("Rectangle"))
                .andExpect(jsonPath("$.content[0].createdTime").value("2024-06-10T10:29:33.017+00:00"));
    }

    @Test
    void shouldNotGetFiguresByTypeTriangleAndRadiusTo2() throws Exception {
        String type = "Triangle";
        Double radiusTo = 2.0;
        int page = 0;
        int size = 10;

        PageImpl<Figure> pageImpl = (PageImpl<Figure>) (PageImpl<?>) new PageImpl<>(triangleList);
        when(dynamicManagementService.getFigureByCriteria(any())).thenReturn(pageImpl);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes")
                        .param("type", type)
                        .param("radiusTo", String.valueOf(radiusTo))
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}
