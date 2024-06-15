package pl.kurs.magdalena_pikulska_test_3r.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.factory.ShapeServiceFactory;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DynamicManagementService {
    private List<String> shapeTypesList;
        @Value("${shapeType}")
    private String shapeTypesString;

    private ShapeServiceFactory factory;
    private ShapeQueryService query;

    public DynamicManagementService(ShapeServiceFactory factory, ShapeQueryService query) {
        this.factory = factory;
        this.query = query;
    }

    @PostConstruct
    public void init() {
        this.shapeTypesList = Arrays.asList(this.shapeTypesString.split(", "));
    }


    public <T> T addFigure(Shape shape) {
        FigureService<T> service = factory.getService(shape.getClass());
        return service.add((T) shape);
    }

    public <T> Double getArea(Shape shape) {
        FigureService<T> service = factory.getService(shape.getClass());
        return service.calculateArea((T) shape);
    }


    public <T> Double getPerimeter(Shape shape) {
        FigureService<T> service = factory.getService(shape.getClass());
        return service.calculatePerimeter((T) shape);
    }



    @Transactional
    public PageImpl<Figure> getFigureByCriteria(FindShapesQuery findShapesQuery) {
        Pageable pageable = findShapesQuery.getPageable();
        String type = findShapesQuery.getType();
        List<String> types = type != null ? Arrays.asList(type) : shapeTypesList;
        List<Figure> finalResults = new ArrayList<>();

        for (String shapeType : types) {
            finalResults.addAll(query.getFromQuery(shapeType, findShapesQuery, pageable));
        }

        return  new PageImpl<>(finalResults, pageable, finalResults.size());


    }




}

