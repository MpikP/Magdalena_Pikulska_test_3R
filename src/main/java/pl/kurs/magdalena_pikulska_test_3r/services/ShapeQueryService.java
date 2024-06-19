package pl.kurs.magdalena_pikulska_test_3r.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.models.Shape;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ShapeQueryService {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Page<Shape> getFigureByCriteria(FindShapesQuery findShapesQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
        Root<Shape> root = query.from(Shape.class);

        Predicate predicate = criteriaBuilder.conjunction();

        if (findShapesQuery.getType() != null) {
            switch (findShapesQuery.getType().toLowerCase()) {
                case "circle":
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.type(), Circle.class));
                    break;
                case "rectangle":
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.type(), Rectangle.class));
                    break;
                case "square":
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.type(), Square.class));
                    break;
                case "triangle":
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.type(), Triangle.class));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape type: " + findShapesQuery.getType());
            }
        }

        if (findShapesQuery.getCreatedFrom() != null) {
            LocalDateTime fromDateTime = findShapesQuery.getCreatedFrom().atStartOfDay();
            Timestamp fromTimestamp = Timestamp.valueOf(fromDateTime);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("createdTime"), fromTimestamp));
        }

        if (findShapesQuery.getCreatedTo() != null) {
            LocalDateTime fromDateTime = findShapesQuery.getCreatedFrom().atStartOfDay();
            Timestamp fromTimestamp = Timestamp.valueOf(fromDateTime);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("createdTime"), fromTimestamp));
        }

        if (findShapesQuery.getRadiusFrom() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("radius"), findShapesQuery.getRadiusFrom()));

        if (findShapesQuery.getRadiusTo() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("radius"), findShapesQuery.getRadiusTo()));

        if (findShapesQuery.getLengthFrom() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(getLengthExpression(criteriaBuilder, root), findShapesQuery.getLengthFrom()));

        if (findShapesQuery.getLengthTo() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(getLengthExpression(criteriaBuilder, root), findShapesQuery.getLengthTo()));

        if (findShapesQuery.getHeightFrom() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(getHeightExpression(criteriaBuilder, root), findShapesQuery.getHeightFrom()));

        if (findShapesQuery.getHeightTo() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(getHeightExpression(criteriaBuilder, root), findShapesQuery.getHeightTo()));

        if (findShapesQuery.getLengthBaseFrom() != null)
            criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("lengthBase"), findShapesQuery.getLengthBaseFrom()));

        if (findShapesQuery.getLengthBaseTo() != null)
            criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("lengthBase"), findShapesQuery.getLengthBaseTo()));

        if (findShapesQuery.getLengthAFrom() != null)
            criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("lengthA"), findShapesQuery.getLengthAFrom()));

        if (findShapesQuery.getLengthATo() != null)
            criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("lengthA"), findShapesQuery.getLengthATo()));

        if (findShapesQuery.getLengthBFrom() != null)
            criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("lengthB"), findShapesQuery.getLengthBFrom()));

        if (findShapesQuery.getLengthBTo() != null)
            criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("lengthB"), findShapesQuery.getLengthBTo()));


        if (findShapesQuery.getAreaFrom() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(getAreaExpression(criteriaBuilder, root), findShapesQuery.getAreaFrom()));

        if (findShapesQuery.getAreaTo() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(getAreaExpression(criteriaBuilder, root), findShapesQuery.getAreaTo()));

        if (findShapesQuery.getPerimeterFrom() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(getPerimeterExpression(criteriaBuilder, root), findShapesQuery.getPerimeterFrom()));

        if (findShapesQuery.getPerimeterTo() != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(getPerimeterExpression(criteriaBuilder, root), findShapesQuery.getPerimeterTo()));


        query.where(predicate);

        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
        List<Shape> results = typedQuery.getResultList();

        int pageNumber = findShapesQuery.getPageable().getPageNumber();
        int pageSize = findShapesQuery.getPageable().getPageSize();
        int totalResults = results.size();
        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalResults);
        List<Shape> pagedResults = results.subList(fromIndex, toIndex);

        Page<Shape> page = new PageImpl<>(pagedResults, findShapesQuery.getPageable(), totalResults);
        return page;
    }


    private Expression<Double> getHeightExpression(CriteriaBuilder criteriaBuilder, Root<Shape> root) {
        Expression<Double> rectangleExpression = root.get("heightRectangle");
        Expression<Double> squareExpression = root.get("heightSquare");

        Expression<Double> heightExpression = criteriaBuilder.<Double>selectCase()
                .when(criteriaBuilder.equal(root.type(), Rectangle.class), rectangleExpression)
                .when(criteriaBuilder.equal(root.type(), Square.class), squareExpression)
                .otherwise(0.0);
        return heightExpression;
    }

    private Expression<Double> getLengthExpression(CriteriaBuilder criteriaBuilder, Root<Shape> root) {
        Expression<Double> rectangleExpression = root.get("lengthRectangle");
        Expression<Double> squareExpression = root.get("lengthSquare");

        Expression<Double> lengthExpression = criteriaBuilder.<Double>selectCase()
                .when(criteriaBuilder.equal(root.type(), Rectangle.class), rectangleExpression)
                .when(criteriaBuilder.equal(root.type(), Square.class), squareExpression)
                .otherwise(0.0);
        return lengthExpression;
    }

    private Expression<Double> getAreaExpression(CriteriaBuilder criteriaBuilder, Root<Shape> root) {
        Expression<Double> circleExpression = criteriaBuilder.prod(criteriaBuilder.prod(root.get("radius"), root.get("radius")), 3.14159);
        Expression<Double> rectangleExpression = criteriaBuilder.prod(root.get("lengthRectangle"), root.get("heightRectangle"));
        Expression<Double> squareExpression = criteriaBuilder.prod(root.get("lengthSquare"), root.get("lengthSquare"));
        Expression<Double> triangleExpression = criteriaBuilder.prod(criteriaBuilder.prod(root.get("lengthBase"), root.get("heightTriangle")), 0.5);

        return getDoubleExpressionForAllShapes(criteriaBuilder, root, circleExpression, rectangleExpression, squareExpression, triangleExpression);
    }

    private Expression<Double> getPerimeterExpression(CriteriaBuilder criteriaBuilder, Root<Shape> root) {
        Expression<Double> circleExpression = criteriaBuilder.prod(criteriaBuilder.prod(2.0, root.get("radius")), 3.14159);
        Expression<Double> rectangleExpression = criteriaBuilder.prod(criteriaBuilder.sum(root.get("lengthRectangle"), root.get("heightRectangle")), 2.0);
        Expression<Double> squareExpression = criteriaBuilder.prod(root.get("lengthSquare"), 4.0);
        Expression<Double> triangleExpression = criteriaBuilder.prod(criteriaBuilder.prod(root.get("lengthBase"), root.get("lengthA")), root.get("lengthB"));

        return getDoubleExpressionForAllShapes(criteriaBuilder, root, circleExpression, rectangleExpression, squareExpression, triangleExpression);
    }

    private Expression<Double> getDoubleExpressionForAllShapes(CriteriaBuilder criteriaBuilder, Root<Shape> root, Expression<Double> circleExpression, Expression<Double> rectangleExpression, Expression<Double> squareExpression, Expression<Double> triangleExpression) {
        Expression<Double> perimeterExpression = criteriaBuilder.<Double>selectCase()
                .when(criteriaBuilder.equal(root.type(), Circle.class), circleExpression)
                .when(criteriaBuilder.equal(root.type(), Rectangle.class), rectangleExpression)
                .when(criteriaBuilder.equal(root.type(), Square.class), squareExpression)
                .when(criteriaBuilder.equal(root.type(), Triangle.class), triangleExpression)
                .otherwise(0.0);
        return perimeterExpression;
    }

}