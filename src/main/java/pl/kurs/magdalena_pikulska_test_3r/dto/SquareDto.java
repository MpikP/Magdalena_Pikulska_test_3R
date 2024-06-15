package pl.kurs.magdalena_pikulska_test_3r.dto;


public class SquareDto extends ShapeDto {
    private Long id;
    private Double length;

    public SquareDto() {
        super("Square");
    }

    public SquareDto(String type) {
        super(type);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
