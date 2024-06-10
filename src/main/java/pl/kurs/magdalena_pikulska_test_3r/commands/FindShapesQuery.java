package pl.kurs.magdalena_pikulska_test_3r.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kurs.magdalena_pikulska_test_3r.validators.ShapeType;
import pl.kurs.magdalena_pikulska_test_3r.validators.ShapeTypeToFind;

import java.time.LocalDate;

public class FindShapesQuery {
    @ShapeTypeToFind
    private String type;
    @Positive
    private Double radiusFrom;
    @Positive
    private Double radiusTo;
    @Positive
    private Double lengthFrom;
    @Positive
    private Double lengthTo;
    @Positive
    private  Double heightFrom;
    @Positive
    private  Double heightTo;
    @Positive
    private  Double lengthBaseFrom;
    @Positive
    private  Double lengthBaseTo;
    @Positive
    private  Double lengthAFrom;
    @Positive
    private  Double lengthATo;
    @Positive
    private  Double lengthBFrom;
    @Positive
    private  Double lengthBTo;
    @Positive
    private  Double areaFrom;
    @Positive
    private  Double areaTo;
    @Positive
    private  Double perimeterFrom;
    @Positive
    private  Double perimeterTo;
    private LocalDate createdFrom;
    private LocalDate createdTo;

    @Min(0)
    private int page;
    @Positive
    private int size;



    public FindShapesQuery() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRadiusFrom() {
        return radiusFrom;
    }

    public void setRadiusFrom(Double radiusFrom) {
        this.radiusFrom = radiusFrom;
    }

    public Double getRadiusTo() {
        return radiusTo;
    }

    public void setRadiusTo(Double radiusTo) {
        this.radiusTo = radiusTo;
    }

    public Double getLengthFrom() {
        return lengthFrom;
    }

    public void setLengthFrom(Double lengthFrom) {
        this.lengthFrom = lengthFrom;
    }

    public Double getLengthTo() {
        return lengthTo;
    }

    public void setLengthTo(Double lengthTo) {
        this.lengthTo = lengthTo;
    }

    public Double getHeightFrom() {
        return heightFrom;
    }

    public void setHeightFrom(Double heightFrom) {
        this.heightFrom = heightFrom;
    }

    public Double getHeightTo() {
        return heightTo;
    }

    public void setHeightTo(Double heightTo) {
        this.heightTo = heightTo;
    }

    public Double getLengthBaseFrom() {
        return lengthBaseFrom;
    }

    public void setLengthBaseFrom(Double lengthBaseFrom) {
        this.lengthBaseFrom = lengthBaseFrom;
    }

    public Double getLengthBaseTo() {
        return lengthBaseTo;
    }

    public void setLengthBaseTo(Double lengthBaseTo) {
        this.lengthBaseTo = lengthBaseTo;
    }

    public Double getLengthAFrom() {
        return lengthAFrom;
    }

    public void setLengthAFrom(Double lengthAFrom) {
        this.lengthAFrom = lengthAFrom;
    }

    public Double getLengthATo() {
        return lengthATo;
    }

    public void setLengthATo(Double lengthATo) {
        this.lengthATo = lengthATo;
    }

    public Double getLengthBFrom() {
        return lengthBFrom;
    }

    public void setLengthBFrom(Double lengthBFrom) {
        this.lengthBFrom = lengthBFrom;
    }

    public Double getLengthBTo() {
        return lengthBTo;
    }

    public void setLengthBTo(Double lengthBTo) {
        this.lengthBTo = lengthBTo;
    }

    public Double getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(Double areaFrom) {
        this.areaFrom = areaFrom;
    }

    public Double getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(Double areaTo) {
        this.areaTo = areaTo;
    }

    public Double getPerimeterFrom() {
        return perimeterFrom;
    }

    public void setPerimeterFrom(Double perimeterFrom) {
        this.perimeterFrom = perimeterFrom;
    }

    public Double getPerimeterTo() {
        return perimeterTo;
    }

    public void setPerimeterTo(Double perimeterTo) {
        this.perimeterTo = perimeterTo;
    }

    public LocalDate getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(LocalDate createdFrom) {
        this.createdFrom = createdFrom;
    }

    public LocalDate getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(LocalDate createdTo) {
        this.createdTo = createdTo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Pageable getPageable() {
        return PageRequest.of(page, size);
    }

    public void setPageable(Pageable pageable){
        setPage(pageable.getPageNumber());
        setSize(pageable.getPageSize());
    }


}
