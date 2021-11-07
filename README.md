# AutoPlugin

**Attention: Development on this tool is winding down. Please consider switching to [AutoGradlePlugin](https://github.com/shenghaiyang/auto-ksp/tree/main/gradle-plugin).**

Auto generate gradle plugin properties file.

___status: working in progress___

### Usage

First, add these dependencies in your build.gradle file:
```groovy
dependencies {
    def autoPluginVersion = "0.0.1-SNAPSHOT"
    implementation("com.shenghaiyang.auto.plugin:annotations:${autoPluginVersion}")
    annotationProcessor("com.shenghaiyang.auto.plugin:processor:${autoPluginVersion}")
}
```
Then, write your plugin class with `@AutoPlugin` annotation:
```java
package com.example.auto.plugin;

import com.shenghaiyang.auto.plugin.AutoPlugin;

@AutoPlugin(pluginId = "plugin1")
public class Plugin1 {
}
```

AutoPlugin will generate the file `META-INF/gradle-plugins/plugin1.properties` in the output classes folder. The file will contain:`implementation-class=com.example.auto.plugin.Plugin1`.

### Snapshots
Snapshots of the development version are available in [Sonatype's `snapshots` repository](https://s01.oss.sonatype.org/content/repositories/snapshots/com/shenghaiyang/auto/plugin/).
```groovy
repositories {
  mavenCentral()
  maven {
    url 'https://s01.oss.sonatype.org/content/repositories/snapshots/'
  }
}
```

### TODO

- incremental support
- check plugin id pattern
- check plugin id duplication


### License

```
Copyright 2021 盛海洋

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
