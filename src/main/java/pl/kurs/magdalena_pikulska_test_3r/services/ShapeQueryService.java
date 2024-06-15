package pl.kurs.magdalena_pikulska_test_3r.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.factory.FigureQueryFactory;
import pl.kurs.magdalena_pikulska_test_3r.models.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShapeQueryService implements FigureQueryFactory {

    @PersistenceContext
    private EntityManager entityManager;

    private List<Predicate> predicates;


    @Override
    public List<Figure> getFromQuery(String shapeType, FindShapesQuery findShapesQuery, Pageable pageable) {
        CriteriaQuery<?> query = createQuery(shapeType, findShapesQuery);
        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<?> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<?> resultList = typedQuery.getResultList();

        return (List<Figure>) resultList;
    }


    public CriteriaQuery<? extends Figure> createQuery(String type, FindShapesQuery findShapesQuery) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        predicates = new ArrayList<>();
        switch (type.toLowerCase()) {
            case "circle":
                return createCircleQuery(builder, findShapesQuery);
            case "rectangle":
                return createRectangleQuery(builder, findShapesQuery);
            case "square":
                return createSquareQuery(builder, findShapesQuery);
            case "triangle":
                return createTriangleQuery(builder, findShapesQuery);
            default:
                throw new IllegalArgumentException("No service found for " + type);
        }
    }

    private CriteriaQuery<Circle> createCircleQuery(CriteriaBuilder builder, FindShapesQuery findShapesQuery) {
        CriteriaQuery<Circle> query = builder.createQuery(Circle.class);
        Root<Circle> root = query.from(Circle.class);

        addCommonPredicates(builder, root, findShapesQuery);

        if (findShapesQuery.getRadiusFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("radius"), findShapesQuery.getRadiusFrom()));
        }
        if (findShapesQuery.getRadiusTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("radius"), findShapesQuery.getRadiusTo()));
        }



        if (findShapesQuery.getAreaFrom() != null || findShapesQuery.getAreaTo() != null) {
            Expression<Double> areaExpression = null;
            Expression<Double> radius = root.get("radius");
            areaExpression = builder.function("calculateAreaCircle", Double.class, radius);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(areaExpression, findShapesQuery.getAreaFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(areaExpression, findShapesQuery.getAreaTo()));
            }
        }


        if (findShapesQuery.getPerimeterFrom() != null || findShapesQuery.getPerimeterTo() != null) {
            Expression<Double> perimeterExpression = null;
            Expression<Double> radius = root.get("radius");
            perimeterExpression = builder.function("calculatePerimeterCircle", Double.class, radius);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterTo()));
            }
        }

        return query;
    }

    private CriteriaQuery<Rectangle> createRectangleQuery(CriteriaBuilder builder, FindShapesQuery findShapesQuery) {
        CriteriaQuery<Rectangle> query = builder.createQuery(Rectangle.class);
        Root<Rectangle> root = query.from(Rectangle.class);

        addCommonPredicates(builder, root, findShapesQuery);

        if (findShapesQuery.getLengthFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("length"), findShapesQuery.getLengthFrom()));
        }
        if (findShapesQuery.getLengthTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("length"), findShapesQuery.getLengthTo()));
        }

        if (findShapesQuery.getHeightFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("height"), findShapesQuery.getHeightFrom()));
        }
        if (findShapesQuery.getHeightTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("height"), findShapesQuery.getHeightTo()));
        }



        Expression<Double> length = root.get("length");
        Expression<Double> height = root.get("height");

        if (findShapesQuery.getAreaFrom() != null || findShapesQuery.getAreaTo() != null) {
            Expression<Double> areaExpression = null;
            areaExpression = builder.function("calculateAreaRectangle", Double.class, length, height);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(areaExpression, findShapesQuery.getAreaFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(areaExpression, findShapesQuery.getAreaTo()));
            }
        }


        if (findShapesQuery.getPerimeterFrom() != null || findShapesQuery.getPerimeterTo() != null) {
            Expression<Double> perimeterExpression = null;
            perimeterExpression = builder.function("calculatePerimeterRectangle", Double.class, length, height);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterTo()));
            }
        }

        return query;
    }

    private CriteriaQuery<Square> createSquareQuery(CriteriaBuilder builder, FindShapesQuery findShapesQuery) {
        CriteriaQuery<Square> query = builder.createQuery(Square.class);
        Root<Square> root = query.from(Square.class);

        addCommonPredicates(builder, root, findShapesQuery);

        if (findShapesQuery.getLengthFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("length"), findShapesQuery.getLengthFrom()));
        }
        if (findShapesQuery.getLengthTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("length"), findShapesQuery.getLengthTo()));
        }

        Expression<Double> length = root.get("length");

        if (findShapesQuery.getAreaFrom() != null || findShapesQuery.getAreaTo() != null) {
            Expression<Double> areaExpression = null;
            areaExpression = builder.function("calculateAreaSquare", Double.class, length);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(areaExpression, findShapesQuery.getAreaFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(areaExpression, findShapesQuery.getAreaTo()));
            }
        }


        if (findShapesQuery.getPerimeterFrom() != null || findShapesQuery.getPerimeterTo() != null) {
            Expression<Double> perimeterExpression = null;
            perimeterExpression = builder.function("calculatePerimeterSquare", Double.class, length);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterTo()));
            }
        }
        return query;
    }

    private CriteriaQuery<Triangle> createTriangleQuery(CriteriaBuilder builder, FindShapesQuery findShapesQuery) {
        CriteriaQuery<Triangle> query = builder.createQuery(Triangle.class);
        Root<Triangle> root = query.from(Triangle.class);

        addCommonPredicates(builder, root, findShapesQuery);

        if (findShapesQuery.getLengthBaseFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("lengthBase"), findShapesQuery.getLengthBaseFrom()));
        }
        if (findShapesQuery.getLengthBaseTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("lengthBase"), findShapesQuery.getLengthBaseTo()));
        }

        if (findShapesQuery.getLengthAFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("lengthA"), findShapesQuery.getLengthAFrom()));
        }
        if (findShapesQuery.getLengthATo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("lengthA"), findShapesQuery.getLengthATo()));
        }

        if (findShapesQuery.getLengthBFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("lengthB"), findShapesQuery.getLengthBFrom()));
        }
        if (findShapesQuery.getLengthBTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("lengthB"), findShapesQuery.getLengthBTo()));
        }

        if (findShapesQuery.getHeightFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("height"), findShapesQuery.getHeightFrom()));
        }
        if (findShapesQuery.getHeightTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("height"), findShapesQuery.getHeightTo()));
        }

        Expression<Double> lengthBase = root.get("lengthBase");
        Expression<Double> lengthA = root.get("lengthA");
        Expression<Double> lengthB = root.get("lengthB");
        Expression<Double> height = root.get("height");

        if (findShapesQuery.getAreaFrom() != null || findShapesQuery.getAreaTo() != null) {
            Expression<Double> areaExpression = null;
            areaExpression = builder.function("calculateAreaTriangle", Double.class, lengthBase, height);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(areaExpression, findShapesQuery.getAreaFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(areaExpression, findShapesQuery.getAreaTo()));
            }
        }


        if (findShapesQuery.getPerimeterFrom() != null || findShapesQuery.getPerimeterTo() != null) {
            Expression<Double> perimeterExpression = null;
            perimeterExpression = builder.function("calculatePerimeterTriangle", Double.class, lengthBase, lengthA, lengthB);

            if (findShapesQuery.getAreaFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterFrom()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(perimeterExpression, findShapesQuery.getPerimeterTo()));
            }
        }
        return query;
    }

    private <T> void addCommonPredicates(CriteriaBuilder builder, Root<T> root, FindShapesQuery findShapesQuery){

        if (findShapesQuery.getCreatedFrom() != null) {
            LocalDateTime fromDateTime = findShapesQuery.getCreatedFrom().atStartOfDay();
            Timestamp fromTimestamp = Timestamp.valueOf(fromDateTime);
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdTime"), fromTimestamp));
        }
        if (findShapesQuery.getCreatedTo() != null) {
            LocalDateTime toDateTime = findShapesQuery.getCreatedTo().atTime(LocalTime.MAX);
            Timestamp toTimestamp = Timestamp.valueOf(toDateTime);
            predicates.add(builder.lessThanOrEqualTo(root.get("createdTime"), toTimestamp));
        }

    }
}
