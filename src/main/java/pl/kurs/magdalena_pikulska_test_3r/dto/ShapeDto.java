package pl.kurs.magdalena_pikulska_test_3r.dto;


public class ShapeDto {
    private String type;
    private FigureDto parameters;

    public ShapeDto() {
    }

    public ShapeDto(String type, FigureDto parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FigureDto getParameters() {
        return parameters;
    }

    public void setParameters(FigureDto parameters) {
        this.parameters = parameters;
    }
}
