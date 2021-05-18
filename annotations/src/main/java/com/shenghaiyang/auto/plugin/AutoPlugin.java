package com.shenghaiyang.auto.plugin;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * An annotation for gradle plugin.
 */
@Documented
@Retention(CLASS)
@Target(TYPE)
public @interface AutoPlugin {
    /** Return the gradle plugin id. */
    String pluginId();
}
