package pl.kurs.magdalena_pikulska_test_3r.controllers;

import org.springframework.context.annotation.ComponentScan;
import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import pl.kurs.magdalena_pikulska_test_3r.services.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.magdalena_pikulska_test_3r.dto.FigureDao;
import pl.kurs.magdalena_pikulska_test_3r.dto.ShapeDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@ComponentScan
@RestController
@RequestMapping("/api/v1/shapes")
@Validated
public class ShapeController {
    private ModelMapper mapper;
    private CircleService circleService;
    private SquareService squareService;
    private RectangleService rectangleService;
    private TriangleService triangleService;

    public ShapeController(ModelMapper mapper, CircleService circleService, SquareService squareService, RectangleService rectangleService, TriangleService triangleService) {
        this.mapper = mapper;
        this.circleService = circleService;
        this.squareService = squareService;
        this.rectangleService = rectangleService;
        this.triangleService = triangleService;
    }

    @PostMapping
    public ResponseEntity<ShapeDao> createShape(@Validated @RequestBody CreateShapeCommand command) {

        ShapeDao shapeWithDetailsDao = new ShapeDao();

        switch (command.getType().toLowerCase()) {
            case "circle":
                Circle circle = mapper.map(command.getParameters(), Circle.class);
                circleService.add(circle);
                shapeWithDetailsDao.setType(circle.getClass().getSimpleName());
                shapeWithDetailsDao.setParameters(FigureDao.createCircleDao(circle));
                break;
            case "square":
                Square square = mapper.map(command.getParameters(), Square.class);
                squareService.add(square);
                shapeWithDetailsDao.setType(square.getClass().getSimpleName());
                shapeWithDetailsDao.setParameters(FigureDao.createSquareDao(square));
                break;
            case "rectangle":
                Rectangle rectangle = mapper.map(command.getParameters(), Rectangle.class);
                rectangleService.add(rectangle);
                shapeWithDetailsDao.setType(rectangle.getClass().getSimpleName());
                shapeWithDetailsDao.setParameters(FigureDao.createRectangleDao(rectangle));
                break;
            case "triangle":
                Triangle triangle = mapper.map(command.getParameters(), Triangle.class);
                triangleService.add(triangle);
                shapeWithDetailsDao.setType(triangle.getClass().getSimpleName());
                shapeWithDetailsDao.setParameters(FigureDao.createTriangleDao(triangle));
                break;
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(shapeWithDetailsDao);
    }



    @Transactional
    @GetMapping("/all")
    public ResponseEntity<Page<ShapeDao>> getAll(
            @RequestParam int page,
            @RequestParam int size) {

        List<Circle> circleList = circleService.getAll();

        List<Square> squareList = squareService.getAll();

        List<Rectangle> rectangleList = rectangleService.getAll();

        List<Triangle> triangleList = triangleService.getAll();

        List<ShapeDao> shapeDaoList = new ArrayList<>();

        getShapeDaoListFromFigures(circleList).forEach(e -> shapeDaoList.add(e));
        getShapeDaoListFromFigures(squareList).forEach(e -> shapeDaoList.add(e));
        getShapeDaoListFromFigures(rectangleList).forEach(e -> shapeDaoList.add(e));
        getShapeDaoListFromFigures(triangleList).forEach(e -> shapeDaoList.add(e));


        return ResponseEntity.ok(convertListToPage(shapeDaoList, page, size));

    }



    @Transactional
    @GetMapping
    public ResponseEntity<Page<ShapeDao>> getFiguresByTypeAndParams(@RequestParam("type") String type
            , @RequestParam(value = "RadiusFrom", required = false) Double radiusFrom, @RequestParam(value = "RadiusTo", required = false) Double radiusTo
            , @RequestParam(value = "LengthFrom", required = false) Double lengthFrom, @RequestParam(value = "LengthTo", required = false) Double lengthTo
            , @RequestParam(value = "HeightFrom", required = false) Double heightFrom, @RequestParam(value = "HeightTo", required = false) Double heightTo
            , @RequestParam(value = "LengthBaseFrom", required = false) Double lengthBaseFrom, @RequestParam(value = "LengthBaseTo", required = false) Double lengthBaseTo
            , @RequestParam(value = "LengthAFrom", required = false) Double lengthAFrom, @RequestParam(value = "LengthATo", required = false) Double lengthATo
            , @RequestParam(value = "LengthBFrom", required = false) Double lengthBFrom, @RequestParam(value = "LengthBTo", required = false) Double lengthBTo
            , @RequestParam(value = "AreaFrom", required = false) Double areaFrom, @RequestParam(value = "AreaTo", required = false) Double areaTo
            , @RequestParam(value = "PerimeterFrom", required = false) Double perimeterFrom, @RequestParam(value = "PerimeterTo", required = false) Double perimeterTo
            , @RequestParam(value = "CreatedFrom", required = false) LocalDate createdFrom, @RequestParam(value = "CreatedTo", required = false) LocalDate createdTo
            , @RequestParam int page
            , @RequestParam int size
    ) {

        List<Figure> figureList = new ArrayList<>();


        switch (type.toLowerCase()) {
            case "circle":
                if (areaFrom != null || areaTo != null) {
                    figureList = getFigureListByArea(circleService, areaFrom, areaTo);
                } else if (perimeterFrom != null || perimeterTo != null) {
                    figureList = getFigureListByPerimeter(circleService, perimeterFrom, perimeterTo);
                } else if (createdFrom != null || createdTo != null) {
                    figureList = getFigureListByCreatedTime(circleService, createdFrom, createdTo);
                } else {
                    figureList = getCircleList(radiusFrom, radiusTo);
                }
                break;
            case "square":
                if (areaFrom != null || areaTo != null) {
                    figureList = getFigureListByArea(squareService, areaFrom, areaTo);
                } else if (perimeterFrom != null || perimeterTo != null) {
                    figureList = getFigureListByPerimeter(squareService, perimeterFrom, perimeterTo);
                } else if (createdFrom != null || createdTo != null) {
                    figureList = getFigureListByCreatedTime(squareService, createdFrom, createdTo);
                } else {
                    figureList = getSquareList(lengthFrom, lengthTo);
                }
                break;
            case "rectangle":
                if (areaFrom != null || areaTo != null) {
                    figureList = getFigureListByArea(rectangleService, areaFrom, areaTo);
                } else if (perimeterFrom != null || perimeterTo != null) {
                    figureList = getFigureListByPerimeter(rectangleService, perimeterFrom, perimeterTo);
                } else if (createdFrom != null || createdTo != null) {
                    figureList = getFigureListByCreatedTime(rectangleService, createdFrom, createdTo);
                } else {
                    figureList = getRectangleList(lengthFrom, lengthTo, heightFrom, heightTo);
                }
                break;
            case "triangle":
                if (areaFrom != null || areaTo != null) {
                    figureList = getFigureListByArea(triangleService, areaFrom, areaTo);
                } else if (perimeterFrom != null || perimeterTo != null) {
                    figureList = getFigureListByPerimeter(triangleService, perimeterFrom, perimeterTo);
                } else if (createdFrom != null || createdTo != null) {
                    figureList = getFigureListByCreatedTime(triangleService, createdFrom, createdTo);
                } else {
                    figureList = getTriangleList(lengthBaseFrom, lengthBaseTo, lengthAFrom, lengthATo, lengthBFrom, lengthBTo, heightFrom, heightTo);
                }
                break;
            default:
                throw new WrongEntityStateException("Invalid figure type.");
        }

        List<ShapeDao> shapeDaoList = getShapeDaoListFromFigures(figureList);


        return ResponseEntity.ok(convertListToPage(shapeDaoList, page, size));

    }



    private Page<ShapeDao> convertListToPage(List<ShapeDao> list, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        int start = 0;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        List<ShapeDao> paginatedShapeDaoList = list.subList(start, end);

        Page<ShapeDao> shapeDaoPage = new PageImpl<>(paginatedShapeDaoList, pageable, list.size());

        return shapeDaoPage;
    }



    private <T extends Identificationable & Figure> List<Figure> getFigureListByArea(GenericManagementService<T, ?> service, Double areaFrom, Double areaTo) {
        if (areaFrom != null & areaTo != null) {
            return service.getAllByAreaBetween(areaFrom, areaTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (areaFrom != null) {
            return service.getAllByAreaGreaterThanEqual(areaFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (areaTo != null) {
            return service.getAllByAreaLessThanEqual(areaTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private <T extends Identificationable & Figure> List<Figure> getFigureListByPerimeter(GenericManagementService<T, ?> service, Double perimeterFrom, Double perimeterTo) {
        if (perimeterFrom != null & perimeterTo != null) {
            return service.getAllByPerimeterBetween(perimeterFrom, perimeterTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (perimeterFrom != null) {
            return service.getAllByPerimeterGreaterThanEqual(perimeterFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (perimeterTo != null) {
            return service.getAllByPerimeterLessThanEqual(perimeterTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private <T extends Identificationable & Figure> List<Figure> getFigureListByCreatedTime(GenericManagementService<T, ?> service, LocalDate createdTimeFrom, LocalDate createdTimeTo) {
        if (createdTimeFrom != null & createdTimeTo != null) {
            return service.getAllByCreatedTimeBetween(createdTimeFrom, createdTimeTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (createdTimeFrom != null) {
            return service.getAllByCreatedTimeGreaterThanEqual(createdTimeFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (createdTimeTo != null) {
            return service.getAllByCreatedTimeLessThanEqual(createdTimeTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }



    private List<Figure> getCircleList(Double radiusFrom, Double radiusTo) {
        if (radiusFrom != null && radiusTo != null) {
            return circleService.getAllByRadiusBetween(radiusFrom, radiusTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (radiusFrom != null) {
            return circleService.getAllByRadiusGreaterThanEqual(radiusFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (radiusTo != null) {
            return circleService.getAllByRadiusLessThanEqual(radiusTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return circleService.getAll().stream().map(f -> (Figure) f).collect(Collectors.toList());
        }
    }


    private List<Figure> getSquareList(Double lengthFrom, Double lengthTo) {
        if (lengthFrom != null && lengthTo != null) {
            return squareService.getAllByLengthBetween(lengthFrom, lengthTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthFrom != null) {
            return squareService.getAllByLengthGreaterThanEqual(lengthFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthTo != null) {
            return squareService.getAllByLengthLessThanEqual(lengthTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return squareService.getAll().stream().map(f -> (Figure) f).collect(Collectors.toList());
        }
    }


    private List<Figure> getRectangleList(Double lengthFrom, Double lengthTo, Double heightFrom, Double heightTo) {
        if (lengthFrom != null && lengthTo != null && heightFrom != null && heightTo != null) {
            return rectangleService.getAllByLengthBetweenAndHeightBetween(lengthFrom, lengthTo, heightFrom, heightTo)
                    .stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthFrom != null && lengthTo != null) {
            return rectangleService.getAllByLengthBetween(lengthFrom, lengthTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthFrom != null) {
            return rectangleService.getAllByLengthGreaterThanEqual(lengthFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthTo != null) {
            return rectangleService.getAllByLengthLessThanEqual(lengthTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (heightFrom != null && heightTo != null) {
            return rectangleService.getAllByHeightBetween(heightFrom, heightTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (heightFrom != null) {
            return rectangleService.getAllByHeightGreaterThanEqual(heightFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (heightTo != null) {
            return rectangleService.getAllByHeightLessThanEqual(heightTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return rectangleService.getAll().stream().map(f -> (Figure) f).collect(Collectors.toList());
        }
    }


    private List<Figure> getTriangleList(Double lengthBaseFrom, Double lengthBaseTo, Double lengthAFrom, Double lengthATo, Double lengthBFrom, Double lengthBTo, Double heightFrom, Double heightTo) {
        if (lengthBaseFrom != null && lengthBaseTo != null) {
            return triangleService.getAllByLengthBaseBetween(lengthBaseFrom, lengthBaseTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthBaseFrom != null) {
            return triangleService.getAllByLengthBaseGreaterThanEqual(lengthBaseFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthBaseTo != null) {
            return triangleService.getAllByLengthBaseLessThanEqual(lengthBaseTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthAFrom != null && lengthATo != null) {
            return triangleService.getAllByLengthBaseBetween(lengthAFrom, lengthATo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthAFrom != null) {
            return triangleService.getAllByLengthBaseGreaterThanEqual(lengthAFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthATo != null) {
            return triangleService.getAllByLengthBaseLessThanEqual(lengthATo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthBFrom != null && lengthBTo != null) {
            return triangleService.getAllByLengthBaseBetween(lengthBFrom, lengthBTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthBFrom != null) {
            return triangleService.getAllByLengthBaseGreaterThanEqual(lengthBFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (lengthBTo != null) {
            return triangleService.getAllByLengthBaseLessThanEqual(lengthBTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (heightFrom != null && heightTo != null) {
            return triangleService.getAllByLHeightBetween(heightFrom, heightTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (heightFrom != null) {
            return triangleService.getAllByHeightGreaterThanEqual(heightFrom).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else if (heightTo != null) {
            return triangleService.getAllByHeightLessThanEqual(heightTo).stream().map(f -> (Figure) f).collect(Collectors.toList());
        } else {
            return triangleService.getAll().stream().map(f -> (Figure) f).collect(Collectors.toList());
        }
    }


    private List<ShapeDao> getShapeDaoListFromFigures(List<? extends Figure> list) {

        List<ShapeDao> shapeDaoList = new ArrayList<>();
        list.stream()
                .map(y -> getFigureDao(y))
                .collect(Collectors.toList())
                .forEach(figureDao -> shapeDaoList.add(new ShapeDao(figureDao.getClass().getSimpleName().replace("Dao", ""), figureDao)));

        return shapeDaoList;
    }


    private FigureDao getFigureDao(Figure figure) {
        switch (figure.getClass().getSimpleName().toLowerCase()) {
            case "circle":
                return FigureDao.createCircleDao((Circle) figure);
            case "square":
                return FigureDao.createSquareDao((Square) figure);
            case "rectangle":
                return FigureDao.createRectangleDao((Rectangle) figure);
            case "triangle":
                return FigureDao.createTriangleDao((Triangle) figure);
            default:
                throw new WrongEntityStateException("Invalid figure type: " + figure.getClass().getSimpleName());
        }
    }


}
