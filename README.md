# qxml

[English](README_EN.md)

一个低侵入，可配置的 Android 库，用于将 layout xml 文件转换为 Java 代码以提高性能。

# 性能表现

与 inflate 比较可减少 30%+加载时间，随布局复杂度提高而提高，详见 demo。

[support-test-demo.apk](support-test-release.apk)

[androidx-test-demo.apk](androidx-test-release.apk)

# 要求

Gradle 3.5.0 以上

# 目前支持的组件

### Base

|     组件     | 支持属性 |     组件     | 支持属性 |         组件         | 支持属性 |
| :----------: | :------: | :----------: | :------: | :------------------: | :------: |
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

|       组件        | 支持属性 |     组件     | 支持属性 |          组件           | 支持属性 |
| :---------------: | :------: | :----------: | :------: | :---------------------: | :------: |
|   AppBarLayout    |  almost  |   CardView   |  almost  | CollapsingToolbarLayout |  almost  |
| ConstraintLayout  |  almost  |    Group     |  almost  |        Guideline        |  almost  |
|    Placeholder    |  almost  |   Barrier    |  almost  |      MotionLayout       |  almost  |
| CoordinatorLayout |  almost  | DrawerLayout |  almost  |      RecyclerView       |  almost  |
| NestedScrollView  |  almost  |  TabLayout   |  almost  |         TabItem         |  almost  |
|      Toolbar      |  almost  |  ViewPager   |  almost  |        Fragment         |  almost  |

### Third Part

|       组件        | 支持属性 |     组件     | 支持属性 |          组件           | 支持属性 |
| :---------------: | :------: | :----------: | :------: | :---------------------: | :------: |
|   Lottie    |  almost  |      |    |  |    |

# 基础使用

### 1. 在 Project `build.gradle` 中添加依赖

最新版本为 **0.9.8**

```groovy
buildscript {
    ...
    ext.qxml_version = "0.9.8"
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

### 2. 在 module `build.gradle` 中添加依赖

```groovy
// 于 apply plugin: 'com.android.application' or 'com.android.library' 之后
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

### 3. 配置

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
    enable true		//是否开启
    useFactory false		//是否使用layoutInflater的factory，开启时性能有损耗
    viewDebug false		//是否在转换的View上显示标志
    logEnable true		//是否开启log
    compatMode com.qxml.CompatMode.AUTO  //compat模式
    acceptReferenceStyle true    //是否接受style引用，仅支持很少部分引用
    useCreateViewListener true  //使用createViewListener
    //以上为默认设置
    buildType {
        debug { //单独配置debug，没有设置的值会使用默认设置
            viewDebug true
        }
        release {
            viewDebug false
        }
    }
}
```

### 4. layout配置选项

在layout.xml根节点中可使用：

`app:qxml` : genIgnore(忽略) genWithUnImplementAttr(生成) genWithoutUnImplementAttr(当没有未实现的属性时才生成)

`app:qxml_use_factory`： 使用 Layoutinflater factory

`app:qxml_debug`：是否显示view的debug标志

`app:qxml_compat`：never(不使用compat)  auto(自动选择)  force(强制使用)

**注：配置对根节点下的全部View都生效，且优先级高于gradle中的配置**

### 5. 简单的构建结果的图表显示

构建结束后，在 build\qxml\report.html 中有简单的结果图表

### 6. 混淆

暂无需要

# [扩展使用](EXTENSION.md)

# 缺点

#### 1. 不支持 Android 内置 style

#### 2. 增加编译时间，根据 layout 数量线性增长

#### 3. 增加 apk 体积，在 demo 中 52 个 layout 文件时，release apk 体积增加了约 38K，后续可能会添加重打包移除已转换 layout 文件的选项

#### 4. 目前扩展 View 需使用全限定类名，后续可能会改善这种情况

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
