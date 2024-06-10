package pl.kurs.magdalena_pikulska_test_3r.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import pl.kurs.magdalena_pikulska_test_3r.services.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.magdalena_pikulska_test_3r.dto.FigureDto;
import pl.kurs.magdalena_pikulska_test_3r.dto.ShapeDto;

import java.util.Arrays;
import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api/v1/shapes")
@Validated
public class ShapeController {
    private ModelMapper mapper;
    private DynamicManagementService dynamicManagementService;

    private List<String> shapeTypesList;
    @Value("${shapeType}")
    private String shapeTypesString;


    public ShapeController(ModelMapper mapper, DynamicManagementService dynamicManagementService) {
        this.mapper = mapper;
        this.dynamicManagementService = dynamicManagementService;

    }



    @PostConstruct
    private void init() {
        this.shapeTypesList = Arrays.asList(this.shapeTypesString.split(", "));
    }


    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@Validated @RequestBody CreateShapeCommand command) {

        String pathName = "pl.kurs.magdalena_pikulska_test_3r.";
        String classString = command.getType().substring(0, 1).toUpperCase() + command.getType().substring(1).toLowerCase();
        try {
            Class<?> aClass = Class.forName(pathName + "models." + classString);
            Object figureObject = mapper.map(command.getParameters(), aClass);
            Figure newFigure = dynamicManagementService.createFigure(figureObject);
            FigureDto newFigureDao = dynamicManagementService.getFigureDto(newFigure);
            ShapeDto shapeWithDetailsDao = new ShapeDto(classString, newFigureDao);

            return ResponseEntity.status(HttpStatus.CREATED).body(shapeWithDetailsDao);

        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<ShapeDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

    }






    @Transactional
    @GetMapping
    public ResponseEntity<Page<ShapeDto>> getFiguresByTypeAndParams(@Validated  @ModelAttribute FindShapesQuery findShapesQuery) {

        Page<Figure> figureByCriteria = dynamicManagementService.getFigureByCriteria(findShapesQuery);

        Page<FigureDto> pageFigureDto = figureByCriteria.map(
                f -> dynamicManagementService.getFigureDto(f)
        );

        Page<ShapeDto> pageShapeDto = pageFigureDto.map(
                f -> new ShapeDto(f.getClass().getSimpleName().replace("Dto", ""), f)
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pageShapeDto);

    }







}
