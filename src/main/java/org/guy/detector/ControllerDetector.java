package org.guy.detector;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Set;

public interface ControllerDetector {
    Set<Element> findControllers (RoundEnvironment roundEnvironment);
}
