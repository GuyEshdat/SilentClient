package org.guy.detector;

import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public abstract class ApiDetector {
    private final ControllerDetector controllerDetector;
    private final EndPointsDetector endPointsDetector;

    public Map<Element, Set<Element>> getEndPointsPerController(RoundEnvironment roundEnvironment) {
        if (this.shouldRun(roundEnvironment)) {
            Set<Element> controllers = this.controllerDetector.findControllers(roundEnvironment);
            return controllers.stream()
                    .collect(toMap(identity(), controller -> this.endPointsDetector.findEndPoints(controller, roundEnvironment)));
        }
        return emptyMap();
    }

    protected abstract boolean shouldRun(RoundEnvironment roundEnvironment);
}
