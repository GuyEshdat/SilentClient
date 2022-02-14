package org.guy.detector;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import java.util.Set;

public interface EndPointsDetector {
    Set<Element> findEndPoints(Element controller, RoundEnvironment roundEnvironment);
}
