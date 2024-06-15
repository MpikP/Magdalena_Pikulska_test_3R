package pl.kurs.magdalena_pikulska_test_3r.dto;


public class RectangleDto extends ShapeDto {
    private Double length;
    private Double height;

    public RectangleDto() {
        super("Rectangle");
    }

    public RectangleDto(String type) {
        super(type);
    }


    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
