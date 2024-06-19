package pl.kurs.magdalena_pikulska_test_3r.dto;


public class CircleDto extends ShapeDto {
    private Double radius;

    public CircleDto() {
        super("Circle");
    }

    public CircleDto(String type) {
        super(type);
    }

    public Double getRadius() {
        return radius;
    }


    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
