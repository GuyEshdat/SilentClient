package org.guy.detector.spring;

import org.guy.detector.EndPointsDetector;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SpringEndPointsDetector implements EndPointsDetector {
    private static final List<Class<? extends Annotation>> springEndPointsAnnotations = getSpringEndPointsAnnotations();

    @Override
    public Set<Element> findEndPoints(Element controller, RoundEnvironment roundEnvironment) {
        return controller.getEnclosedElements().stream()
                .filter(element -> element.getKind().equals(ElementKind.METHOD))
                .filter(this::isEndPoint)
                .collect(Collectors.toSet());
    }

    private boolean isEndPoint(Element method) {
        return springEndPointsAnnotations.stream()
                .map(method::getAnnotation)
                .anyMatch(Objects::nonNull);
    }

    private static List<Class<? extends Annotation>> getSpringEndPointsAnnotations() {
        return List.of(RequestMapping.class,
                PostMapping.class,
                GetMapping.class,
                PatchMapping.class,
                PutMapping.class,
                DeleteMapping.class);
    }

}
