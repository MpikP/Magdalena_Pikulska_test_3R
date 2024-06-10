package pl.kurs.magdalena_pikulska_test_3r.services;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.dto.FigureDto;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DynamicManagementService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ApplicationContext context;


    private List<String> shapeTypesList;
    @Value("${shapeType}")
    private String shapeTypesString;
    private final String pathName = "pl.kurs.magdalena_pikulska_test_3r.";

    public DynamicManagementService() {
    }

    @PostConstruct
    public void init() {
        this.shapeTypesList = Arrays.asList(this.shapeTypesString.split(", "));
    }


    public <T extends Figure> T createFigure(Object object) {
        try {
            String classString = object.getClass().getSimpleName();
            String serviceClassString = "services." + classString + "Service";
            Class<?> serviceClass = Class.forName(pathName + serviceClassString);

            Object serviceObject = context.getBean(serviceClass);
            Method method = serviceClass.getMethod("add", object.getClass());
            T figure = (T) method.invoke(serviceObject, object);

            return figure;
        } catch (Exception e) {
            return null;
        }
    }





    @Transactional
    public <T extends Figure> PageImpl<T> getFigureByCriteria(FindShapesQuery findShapesQuery) {
        Pageable pageable = findShapesQuery.getPageable();
        String type = findShapesQuery.getType();
        String classString = type != null ? type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase() : null;

        try {
            List<Class<?>> classList = new ArrayList<>();
            if (classString != null) {
                classList.add(Class.forName(pathName + "models." + classString));
            } else {
                for (String shapeType : shapeTypesList) {
                    classList.add(Class.forName(pathName + "models." + shapeType.substring(0, 1).toUpperCase() + shapeType.substring(1).toLowerCase()));
                }
            }

            List<T> finalResults = new ArrayList<>();
            long totalRecords = 0;

            for (Class<?> classClass : classList) {
                CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                CriteriaQuery<T> query = builder.createQuery((Class<T>) classClass);
                Root<T> root = query.from((Class<T>) classClass);

                List<Predicate> predicates = new ArrayList<>();
                List<Predicate> countPredicates = new ArrayList<>();

                if (findShapesQuery.getCreatedFrom() != null) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.get("createdTime"),
                            java.sql.Date.valueOf(findShapesQuery.getCreatedFrom())
                    ));
                }
                if (findShapesQuery.getCreatedTo() != null) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.get("createdTime"),
                            java.sql.Date.valueOf(findShapesQuery.getCreatedTo())
                    ));
                }


                Field[] fields = classClass.getDeclaredFields();
                Field field1toCalculateAreaOrPerimeter = null;
                Field field2toCalculateAreaOrPerimeter = null;
                Field field2toCalculatePerimeter = null;
                Field field3toCalculatePerimeter = null;

                for (Field field : fields) {
                    String fieldName = field.getName();
                    Class<?> fieldClass = field.getType();

                    if (Number.class.isAssignableFrom(fieldClass)) {
                        Object valueFrom = getFieldFromFindShapesQuery(findShapesQuery, fieldName + "From");
                        Object valueTo = getFieldFromFindShapesQuery(findShapesQuery, fieldName + "To");

                        if (valueFrom != null) {
                            predicates.add(builder.greaterThanOrEqualTo(root.get(fieldName), (Double) valueFrom));
                        }
                        if (valueTo != null) {
                            predicates.add(builder.lessThanOrEqualTo(root.get(fieldName), (Double) valueTo));
                        }
                    }

                    if (field.getName().equalsIgnoreCase("radius") || field.getName().equalsIgnoreCase("length") || field.getName().equalsIgnoreCase("lengthBase")) {
                        field1toCalculateAreaOrPerimeter = field;
                    }
                    if (field.getName().equalsIgnoreCase("height")) {
                        field2toCalculateAreaOrPerimeter = field;
                    }
                    if (field.getName().equalsIgnoreCase("lengthA") && classClass.getSimpleName().equals("Triangle")) {
                        field2toCalculatePerimeter = field;
                    } else if (field.getName().equalsIgnoreCase("height") && !classClass.getSimpleName().equals("Triangle")){
                        field2toCalculatePerimeter = field;
                    }
                    if (field.getName().equalsIgnoreCase("lengthB")) {
                        field3toCalculatePerimeter = field;
                    }
                }



                if (field1toCalculateAreaOrPerimeter != null) {

                    if(findShapesQuery.getAreaFrom() != null || findShapesQuery.getAreaTo() != null){
                        Expression<Double> areaExpression = null;
                        Expression<Double> field1toCalculateAreaExpression = root.get(field1toCalculateAreaOrPerimeter.getName());
                        if (field2toCalculateAreaOrPerimeter != null) {
                            Expression<Double> field2toCalculateAreaExpression = root.get(field2toCalculateAreaOrPerimeter.getName());
                            areaExpression = builder.function("calculateArea" + classClass.getSimpleName(), Double.class, field1toCalculateAreaExpression, field2toCalculateAreaExpression);
                        } else {
                            areaExpression = builder.function("calculateArea" + classClass.getSimpleName(), Double.class, field1toCalculateAreaExpression);
                        }

                        Double areaFromParam = findShapesQuery.getAreaFrom() == null ? 0.0 : findShapesQuery.getAreaFrom();
                        Double areaToParam = findShapesQuery.getAreaTo() == null ? Double.MAX_VALUE : findShapesQuery.getAreaTo();

                        predicates.add(builder.between(areaExpression, areaFromParam, areaToParam));
                    }

                    if(findShapesQuery.getPerimeterFrom() != null || findShapesQuery.getPerimeterTo() != null){
                        Expression<Double> perimeterExpression = null;
                        Expression<Double> field1toCalculatePerimeterExpression = root.get(field1toCalculateAreaOrPerimeter.getName());
                        if (field2toCalculateAreaOrPerimeter != null) {
                            Expression<Double> field2toCalculatePerimeterExpression = root.get(field2toCalculatePerimeter.getName());
                            if (field3toCalculatePerimeter != null) {//dla trójkąta
                                Expression<Double> field3toCalculatePerimeterExpression = root.get(field3toCalculatePerimeter.getName());
                                perimeterExpression = builder.function("calculatePerimeter" + classClass.getSimpleName(), Double.class, field1toCalculatePerimeterExpression, field2toCalculatePerimeterExpression, field3toCalculatePerimeterExpression);
                            } else {
                                perimeterExpression = builder.function("calculatePerimeter" + classClass.getSimpleName(), Double.class, field1toCalculatePerimeterExpression, field2toCalculatePerimeterExpression);
                            }
                        } else {
                            perimeterExpression = builder.function("calculatePerimeter" + classClass.getSimpleName(), Double.class, field1toCalculatePerimeterExpression);
                        }

                        Double perimeterFromParam = findShapesQuery.getPerimeterFrom() == null ? 0.0 : findShapesQuery.getPerimeterFrom();
                        Double perimeterToParam = findShapesQuery.getPerimeterTo() == null ? Double.MAX_VALUE : findShapesQuery.getPerimeterTo();

                        predicates.add(builder.between(perimeterExpression, perimeterFromParam, perimeterToParam));
                    }




                }


                query.where(predicates.toArray(new Predicate[0]));

                TypedQuery<T> typedQuery = entityManager.createQuery(query);
                typedQuery.setFirstResult((int) pageable.getOffset());
                typedQuery.setMaxResults(pageable.getPageSize());

                List<T> resultList = typedQuery.getResultList();
                finalResults.addAll(resultList);


                CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
                Root<T> countRoot = countQuery.from((Class<T>) classClass);
                countQuery.select(builder.count(countRoot));
                countQuery.where(countPredicates.toArray(new Predicate[0]));
                long countRecords = entityManager.createQuery(countQuery).getSingleResult();
                totalRecords += countRecords;

            }

            return  new PageImpl<>(finalResults, pageable, totalRecords);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Object getFieldFromFindShapesQuery(FindShapesQuery findShapesQuery, String fieldName) {
        try {
            Field field = FindShapesQuery.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(findShapesQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }









    public <T extends Figure> FigureDto getFigureDto(Object figure){
        String classString = figure.getClass().getSimpleName();
        String methodString = "create" + classString + "Dto";
        return getFigureDtoModelClass("dto.FigureDto", methodString, (Figure) figure);
    }






    public FigureDto getFigureDtoModelClass(String className, String methodName, Figure figure) {
        try {
            Class<?> figureClass = Class.forName(pathName + className);
            Method method = figureClass.getMethod(methodName, figure.getClass());
            FigureDto figureDto = (FigureDto) method.invoke(null, figure);

            return figureDto;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }


}

