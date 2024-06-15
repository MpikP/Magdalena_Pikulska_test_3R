package pl.kurs.magdalena_pikulska_test_3r.dto;


public class TriangleDto extends ShapeDto {
    private Double lengthBase;
    private Double lengthA;
    private Double lengthB;
    private Double height;

    public TriangleDto() {
        super("Triangle");
    }

    public TriangleDto(String type) {
        super(type);
    }

    public Double getLengthBase() {
        return lengthBase;
    }

    public void setLengthBase(Double lengthBase) {
        this.lengthBase = lengthBase;
    }

    public Double getLengthA() {
        return lengthA;
    }

    public void setLengthA(Double lengthA) {
        this.lengthA = lengthA;
    }

    public Double getLengthB() {
        return lengthB;
    }

    public void setLengthB(Double lengthB) {
        this.lengthB = lengthB;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
