package org.guy.detector.spring;

import org.guy.detector.ApiDetector;

import javax.annotation.processing.RoundEnvironment;

public class SpringApiDetector extends ApiDetector {

    public SpringApiDetector() {
        super(new SpringControllerDetector(), new SpringEndPointsDetector());
    }

    @Override
    //todo - implement logic that decide if we compile a spring boot application
    protected boolean shouldRun(RoundEnvironment roundEnvironment) {
        return true;
    }
}
