package pl.kurs.magdalena_pikulska_test_3r.config;

public class FunctionsDb {

    public static double calculateAreaTriangle(double dimension1, double dimension2) {
        return (dimension1 * dimension2) / 3;
    }

    public static double calculateAreaCircle(double dimension1) {
        return Math.PI * dimension1 * dimension1;
    }

    public static double calculateAreaRectangle(double dimension1, double dimension2) {
        return dimension1 * dimension2;
    }

    public static double calculateAreaSquare(double dimension1) {
        return dimension1 * dimension1;
    }



    public static double calculatePerimeterTriangle(double dimension1, double dimension2, double dimension3) {
        return dimension1 + dimension2 + dimension3;
    }

    public static double calculatePerimeterCircle(double dimension1) {
        return Math.PI * 2 * dimension1;
    }

    public static double calculatePerimeterRectangle(double dimension1, double dimension2) {
        return 2 * (dimension1 + dimension2);
    }

    public static double calculatePerimeterSquare(double dimension1) {
        return dimension1 * 4;
    }
}
