﻿# qxml

[中文](README.md)

A low intrusive, configurable android library that converts layout XML files into Java code to improve performance.

# Performance

Compared with inflate, it can reduce 30% + loading time, which increases with the increase of layout complexity. See demo for details.

[support-test-demo.apk](support-test-release.apk)

[androidx-test-demo.apk](androidx-test-release.apk)

# Require

Gradle 3.5.0 above

# Currently supported components

### Base

|  Component  |  Attr  |  Component   |  Attr  |      Component       |  Attr  |
| :---------: | :----: | :----------: | :----: | :------------------: | :----: |
|     View     |  almost  |   TextView   |  almost  |       EditText       |  almost  |
|    Button    |  almost  |  ImageView   |  almost  |        Button        |  almost  |
|   CheckBox   |  almost  | ImageButton  |  almost  |      ImageView       |  almost  |
|   SeekBar    |  almost  |  RatingBar   |  almost  |       Spinner        |  almost  |
|    Switch    |  almost  | tableLayout  |  almost  |       TableRow       |  almost  |
|   ViewStub   |  almost  |  ScrollView  |  almost  | HorizontalScrollView |  almost  |
| ProgressBar  |  almost  | SurfaceView  |  almost  |      RadioGroup      |  almost  |
| RadioButton  |  almost  | LinearLayout |  almost  |    RelativeLayout    |  almost  |
| FrameLayout  |  almost  | TextureView  |  almost  |       WebView        |  almost  |
|   ListView   |  almost  |  GridLayout  |  almost  |  ExpandableListView  |  almost  |
|   ViewFlipper   |  almost  |  ViewSwitcher  |  almost  |  TextSwitcher  |  almost  |
|              |          |              |          |                      |          |
|   include    |  almost  |    merge     |  almost  |     DataBinding      |  almost  |
| custom style |  almost  | system style |   **none**   |                      |          |

### Support & Androidx

|     Component     |  Attr  |  Component   |  Attr  |        Component        |  Attr  |
| :---------------: | :----: | :----------: | :----: | :---------------------: | :----: |
|   AppBarLayout    |  almost  |   CardView   |  almost  | CollapsingToolbarLayout |  almost  |
| ConstraintLayout  |  almost  |    Group     |  almost  |        Guideline        |  almost  |
|    Placeholder    |  almost  |   Barrier    |  almost  |      MotionLayout       |  almost  |
| CoordinatorLayout |  almost  | DrawerLayout |  almost  |      RecyclerView       |  almost  |
| NestedScrollView  |  almost  |  TabLayout   |  almost  |         TabItem         |  almost  |
|      Toolbar      |  almost  |  ViewPager   |  almost  |        Fragment         |  almost  |

### Third Part

|     Component     |  Attr  |  Component   |  Attr  |        Component        |  Attr  |
| :---------------: | :------: | :----------: | :------: | :---------------------: | :------: |
|   Lottie    |  almost  |      |    |  |    |

# Use

### 1. Add dependency in Project `build.gradle`

The newest version code is **0.9.7**

```groovy
buildscript {
    ...
    ext.qxml_version = "0.9.7"
    repositories {
        ...
        mavenCentral()
    }
    ...
    dependencies {
        ...
        classpath "io.github.duduhuang88:gradle-plugin:$qxml_version"
        ...
    }
}

```

### 2. Add dependency in module `build.gradle`

```groovy
// after apply plugin: 'com.android.application' or 'com.android.library'
apply plugin: 'com.qxml.code.plugin'

compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
}

dependencies {
	...

	kapt "io.github.duduhuang88:qxml-processor:$qxml_version"
		or
	annotationProcessor "io.github.duduhuang88:qxml-processor:$qxml_version"

	//support
	implementation "io.github.duduhuang88:qxml-support:$qxml_version"

	//androidx
	implementation "io.github.duduhuang88:qxml-androidx:$qxml_version"
	...
}

```

### 3. config

```groovy
android {
    ......
    sourceSets {
        main {
            ......
            resources.srcDirs += "build/qxml/tempRes"
            ......
        }
    }
}

qxml {
    enable true		//qxml enable option
    useFactory false		//whether or not to use the factory of the layoutinflate, there is a loss of performance when it is turned on
    viewDebug false		//show flag on converted view
    logEnable true		//log option
    compatMode com.qxml.CompatMode.AUTO  //compat mode
    acceptReferenceStyle true    //whether to accept style reference, only a few references are supported
    useCreateViewListener true  //using createViewListener
    //default config above
    buildType {
        debug { //the default option will be used for values that are not set
            viewDebug true
        }
        release {
            viewDebug false
        }
    }
}
```

### 4. Simple build result chart

After the build, there is a simple result chart in build/qxml/report.html

### 5. Proguard

no need

# [Extended use](EXTENSION_EN.md)

# Shortcoming

#### 1. Android system style is not supported

#### 2. Increase the build time and increase linearly according to the number of layouts

#### 3. Increase the raw size of APK. When there are 52 layout files in the demo, the size of release APK increases by about 38K. The option of repackaging and removing converted layout files may be added later

#### 4. The extension of view needs to use fully qualified class names, which may be improved in the future

# LICENSE
```
Copyright 2021 duduhuang88

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

