package pl.kurs.magdalena_pikulska_test_3r.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.dto.*;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import pl.kurs.magdalena_pikulska_test_3r.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api/v1/shapes")
@Validated
public class ShapeController {
    private ModelMapper mapper;
    private DynamicManagementService dynamicManagementService;
    private ShapeQueryService shapeQueryService;


    public ShapeController(ModelMapper mapper, DynamicManagementService dynamicManagementService, ShapeQueryService shapeQueryService) {
        this.mapper = mapper;
        this.dynamicManagementService = dynamicManagementService;
        this.shapeQueryService = shapeQueryService;

    }


    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@Validated @RequestBody CreateShapeCommand command) {

        Shape newShape = convertCommandToShape(command);
        Shape addedShape = dynamicManagementService.addFigure(newShape);
        ShapeDto shapeDto = convertShapeToShapeDto(addedShape);

        return ResponseEntity.status(HttpStatus.CREATED).body(shapeDto);

    }


    @GetMapping
    public ResponseEntity<Page<ShapeDto>> getFiguresByTypeAndParams(@Validated @ModelAttribute FindShapesQuery findShapesQuery) {
        Page<Shape> figureByCriteria = shapeQueryService.getFigureByCriteria(findShapesQuery);


        Page<ShapeDto> pageShapeDto = figureByCriteria.map(
                f -> convertShapeToShapeDto(f)
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pageShapeDto);


    }


    private Shape convertCommandToShape(CreateShapeCommand command) {

        String type = command.getType();
        if (type.equalsIgnoreCase("circle")) {
            return mapper.map(command, Circle.class);
        } else if (type.equalsIgnoreCase("rectangle")) {
            return mapper.map(command, Rectangle.class);
        } else if (type.equalsIgnoreCase("square")) {
            return mapper.map(command, Square.class);
        } else if (type.equalsIgnoreCase("triangle")) {
            return mapper.map(command, Triangle.class);
        } else {
            throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }

    private ShapeDto convertShapeToShapeDto(Shape shape) {

        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null");
        }

        String type = shape.getClass().getSimpleName();
        ShapeDto shapeDto;
        if (type.equalsIgnoreCase("circle")) {
            shapeDto = mapper.map(shape, CircleDto.class);
        } else if (type.equalsIgnoreCase("rectangle")) {
            shapeDto = mapper.map(shape, RectangleDto.class);
        } else if (type.equalsIgnoreCase("square")) {
            shapeDto = mapper.map(shape, SquareDto.class);
        } else if (type.equalsIgnoreCase("triangle")) {
            shapeDto = mapper.map(shape, TriangleDto.class);
        } else {
            throw new IllegalArgumentException("Unknown shape type: " + type);
        }

        return shapeDto;
    }


}
