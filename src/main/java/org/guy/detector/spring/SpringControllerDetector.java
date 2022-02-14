package org.guy.detector.spring;

import org.guy.annotations.GenerateClient;
import org.guy.detector.ControllerDetector;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class SpringControllerDetector implements ControllerDetector {

    @Override
    public Set<Element> findControllers(RoundEnvironment roundEnvironment) {
        Set<Element> allControllers = this.getAllControllers(roundEnvironment.getRootElements());
        Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(GenerateClient.class);
        if (this.isApplicationClassAnnotated(annotatedElements)) {
            return allControllers;
        }
        return allControllers.stream()
                .filter(controller -> nonNull(controller.getAnnotation(GenerateClient.class)))
                .collect(Collectors.toSet());
    }

    private Set<Element> getAllControllers(Set<? extends Element> rootElements) {
        return rootElements.stream()
                .filter(element -> nonNull(element.getAnnotation(RestController.class)))
                .collect(Collectors.toSet());
    }

    private boolean isApplicationClassAnnotated(Set<? extends Element> annotatedElements) {
        return annotatedElements.stream()
                .anyMatch(element -> nonNull(element.getAnnotation(SpringBootApplication.class)));
    }
}
