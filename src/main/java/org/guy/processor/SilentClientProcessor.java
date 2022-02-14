package org.guy.processor;

import com.google.auto.service.AutoService;
import org.guy.annotations.GenerateClient;
import org.guy.detector.spring.SpringApiDetector;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Map;
import java.util.Set;

@AutoService(Processor.class)
public class SilentClientProcessor extends AbstractProcessor implements Processor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //todo - allow multi detectors (don't be coupled to spring)
        Map<Element, Set<Element>> endPointsPerController = new SpringApiDetector().getEndPointsPerController(roundEnv);
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(GenerateClient.class.getName());
    }
}
