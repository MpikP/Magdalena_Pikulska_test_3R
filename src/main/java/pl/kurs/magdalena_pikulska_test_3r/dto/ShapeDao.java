package pl.kurs.magdalena_pikulska_test_3r.dto;


public class ShapeDao{
    private String type;
    private FigureDao parameters;

    public ShapeDao() {
    }

    public ShapeDao(String type, FigureDao parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FigureDao getParameters() {
        return parameters;
    }

    public void setParameters(FigureDao parameters) {
        this.parameters = parameters;
    }
}
