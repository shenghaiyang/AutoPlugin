package com.shenghaiyang.auto.plugin.processor;

import com.google.auto.service.AutoService;
import com.shenghaiyang.auto.plugin.AutoPlugin;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * file content: implementation-class=org.example.GreetingPlugin
 */
@AutoService(Processor.class)
public class AutoPluginProcessor extends AbstractProcessor {

    private final Map<String, String> providers = new HashMap<>();

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(AutoPlugin.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            generatePropertyFiles();
        } else {
            processAnnotations(annotations, roundEnv);
        }
        return false;
    }

    private void processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(AutoPlugin.class);
        debug("elements:" + Arrays.toString(elements.toArray()));
        for (Element e : elements) {
            if (e instanceof TypeElement) {
                String className = ((TypeElement) e).getQualifiedName().toString();
                AutoPlugin annotation = e.getAnnotation(AutoPlugin.class);
                String pluginId = annotation.pluginId();
                checkPluginId(pluginId);
                providers.put(pluginId, className);
            } else {
                throw new IllegalStateException("invalid annotation target:" + e.getSimpleName() + ", kind:" + e.getKind());
            }
        }
    }

    private static void checkPluginId(String pluginId) {
        if (pluginId.isEmpty()) {
            throw new IllegalArgumentException("can not use empty plugin id");
        }
        // TODO check name pattern
        // TODO check for duplication
    }

    private void generatePropertyFiles() {
        Filer filer = processingEnv.getFiler();

        info("plugins:" + Arrays.toString(providers.entrySet().toArray()));

        for (String pluginId : providers.keySet()) {
            String resourceFile = "META-INF/gradle-plugins/" + pluginId + ".properties";
            info("processing file: " + resourceFile);
            try {
                FileObject existingFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "", resourceFile);
                String className = providers.get(pluginId);
                try (Writer writer = existingFile.openWriter()) {
                    String content = "implementation-class=" + className;
                    writer.write(content);
                }
                info("processing done: " + resourceFile);
            } catch (IOException e) {
                info("processing failed: " + resourceFile);
                throw new RuntimeException(e);
            }
        }
    }


    private void debug(String msg) {
        System.out.println(msg);
    }

    private void info(String msg) {
        System.out.println(msg);
    }

}
