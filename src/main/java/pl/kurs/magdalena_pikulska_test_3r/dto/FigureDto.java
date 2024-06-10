package pl.kurs.magdalena_pikulska_test_3r.dto;

import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;

public interface FigureDto {
    static CircleDto createCircleDto(Circle circle){
        CircleDto circleDao = new CircleDto();
        circleDao.setCreatedTime(circle.getCreatedTime());
        circleDao.setId(circle.getId());
        circleDao.setRadius(circle.getRadius());
        circleDao.setArea(circle.calculateArea());
        circleDao.setPerimeter(circle.calculatePerimeter());
        return circleDao;
    }
    static SquareDto createSquareDto(Square square){
        SquareDto squareDao = new SquareDto();
        squareDao.setCreatedTime(square.getCreatedTime());
        squareDao.setId(square.getId());
        squareDao.setLength(square.getLength());
        squareDao.setArea(square.calculateArea());
        squareDao.setPerimeter(square.calculatePerimeter());
        return squareDao;
    }
    static RectangleDto createRectangleDto(Rectangle rectangle){
        RectangleDto rectangleDao = new RectangleDto();
        rectangleDao.setCreatedTime(rectangle.getCreatedTime());
        rectangleDao.setId(rectangle.getId());
        rectangleDao.setLength(rectangle.getLength());
        rectangleDao.setHeight(rectangle.getHeight());
        rectangleDao.setArea(rectangle.calculateArea());
        rectangleDao.setPerimeter(rectangle.calculatePerimeter());
        return rectangleDao;
    }
    static TriangleDto createTriangleDto(Triangle triangle){
        TriangleDto triangleDao = new TriangleDto();
        triangleDao.setCreatedTime(triangle.getCreatedTime());
        triangleDao.setId(triangle.getId());
        triangleDao.setLengthBase(triangle.getLengthBase());
        triangleDao.setLengthA(triangle.getLengthA());
        triangleDao.setLengthB(triangle.getLengthB());
        triangleDao.setHeight(triangle.getHeight());
        triangleDao.setArea(triangle.calculateArea());
        triangleDao.setPerimeter(triangle.calculatePerimeter());
        return triangleDao;
    }
}
