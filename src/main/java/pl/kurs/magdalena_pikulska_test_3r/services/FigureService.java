package pl.kurs.magdalena_pikulska_test_3r.services;

import org.springframework.stereotype.Component;

public interface FigureService <T>{
    double PI_NUMBER = Math.PI;
    Double calculateArea(T shape);
    Double calculatePerimeter(T shape);
    T add(T figure);
}
