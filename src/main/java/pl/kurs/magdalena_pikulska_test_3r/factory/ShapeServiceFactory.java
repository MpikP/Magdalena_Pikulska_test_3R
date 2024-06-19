package pl.kurs.magdalena_pikulska_test_3r.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.kurs.magdalena_pikulska_test_3r.models.*;
import pl.kurs.magdalena_pikulska_test_3r.services.*;

@Component
public class ShapeServiceFactory {
    private ApplicationContext ctx;

    public ShapeServiceFactory(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public <T> FigureService<T> getService(Class<? extends Shape> figureClass) {
        if (Circle.class.equals(figureClass)) {
            return (FigureService<T>) ctx.getBean(CircleService.class);
        } else if (Rectangle.class.equals(figureClass)) {
            return (FigureService<T>) ctx.getBean(RectangleService.class);
        } else if (Square.class.equals(figureClass)) {
            return (FigureService<T>) ctx.getBean(SquareService.class);
        } else if (Triangle.class.equals(figureClass)) {
            return (FigureService<T>) ctx.getBean(TriangleService.class);
        } else {
            throw new IllegalArgumentException("No service found for " + figureClass.getSimpleName());
        }
    }

}
