package org.guy.processor;

import org.guy.annotations.GenerateClient;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class SilentClientProcessor extends AbstractProcessor implements Processor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(GenerateClient.class.getName());
    }
}
