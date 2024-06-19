package pl.kurs.magdalena_pikulska_test_3r.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import pl.kurs.magdalena_pikulska_test_3r.dto.RectangleDto;
import pl.kurs.magdalena_pikulska_test_3r.dto.SquareDto;
import pl.kurs.magdalena_pikulska_test_3r.dto.TriangleDto;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;

@Configuration
public class BeansConfig {
    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Square.class, SquareDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getLengthSquare(), SquareDto::setLength);
        });
        modelMapper.typeMap(CreateShapeCommand.class, Square.class).addMappings(mapper -> {
            mapper.map(src -> src.getLength(), Square::setLengthSquare);
        });

        modelMapper.typeMap(Rectangle.class, RectangleDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getLengthRectangle(), RectangleDto::setLength);
            mapper.map(src -> src.getHeightRectangle(), RectangleDto::setHeight);
        });
        modelMapper.typeMap(CreateShapeCommand.class, Rectangle.class).addMappings(mapper -> {
            mapper.map(src -> src.getLength(), Rectangle::setLengthRectangle);
            mapper.map(src -> src.getHeight(), Rectangle::setHeightRectangle);
        });

        modelMapper.typeMap(Triangle.class, TriangleDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getHeightTriangle(), TriangleDto::setHeight);
        });
        modelMapper.typeMap(CreateShapeCommand.class, Triangle.class).addMappings(mapper -> {
            mapper.map(src -> src.getHeight(), Triangle::setHeightTriangle);
        });

        return modelMapper;
    }
}
