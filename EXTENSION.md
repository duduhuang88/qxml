
[English](EXTENSION_EN.md)

# 扩展自定义View

在gradle.properties中添加 QXML_VALID_CODE=*** (随机字符串)

**注：当前仅支持使用Java且需使用全限定类名**

### 1. 新建Java类，根据要扩展 View 的继承情况继承特定的Gen类，并添加`ViewParse` 注解，例如TextView继承自View，则TextView继承View支持的所有属性
```java
@ViewParse(TextView.class)
public class TextViewGen extends ViewGen {
}
```
**`ViewParse` 参数说明**
```java
public @interface ViewParse {
    //定义的类，如AppCompatTextView
    Class<?> value();
    //compat类的基类，如AppCompatTextView的基类为TextView
    Class<?> compatOf() default Void.class;
    //compat条件，使用 `AndroidRS.attr.*` 或 `RS.attr.*` 填充，当满足条件时，基类会解析成 compat 类，如：{ RS.attr.lineHeight, AndroidRS.attr.lineHeight }，当出现 `app:lineHeight` 或 `android:lineHeight` 时会解析成 `AppCompatTextView`
    String[] compatCondition() default {};
    //ViewGroup时默认的layoutParam初始化语句，使用确定的值可以避免反射， 如：layoutParamInit = "new android.widget.FrameLayout.LayoutParams(-1, -1)"，请使用全限定类名
    String layoutParamInit() default "";
    //是否调用onFinishInflate
    CallOnInflateConfig callOnFinishInflate() default CallOnInflateConfig.EXTENDS;
}
```

##### 注：当定义一个ViewGroup类型的View时，由于缺失 generateDefaultLayoutParams 过程，需要使用`ViewReplace`添加缺失的属性设置，重新设置 LayoutParam，见下 扩展已定义View的属性
### 2. 根据需要，添加或继承需要扩展的属性

```java
@ViewParse(TextView.class)
public class TextViewGen extends ViewGen {

	//扩展android:gravity属性
	@Attr(AndroidRS.attr.gravity)
    public void textViewGravity(TextView textView, int gravityFlag) {
        textView.setGravity(gravityFlag);
    }
	
	//修改android:clickable逻辑，不支持调用super
	@Override
    public void viewClickable(View view, boolean clickable) {
        //不支持调用super
        //super.viewClickable(view, clickable);
        view.setClickable(clickable);
    }

}
```
##### 注1：Attr注解中请使用 AndroidRS.attr.* 引用Android属性或 RS.attr.* 引用自定义属性，如果找不到RS类，请先 build project 进行生成
##### 注2：Attr注解的方法必须包含两个参数，第一个参数类型为`ViewParse`注解中`value`定义的类型，第二个参数详见下参数说明
##### 注3：方法体中请添加类的全限定类名，例：android.support.v4.content.ContextCompat.getColor...

### 3. 根据需要，使用`LocalVar`与`OnEnd`注解

##### LocalVar注解可标记一个共享变量暂存变量值，供后续使用

```java
public static class $$TestLocalVariable {
    public int testValue = 0;
    //使用全限定类名
    public android.graphics.drawable.Drawable drawable = null;
}

@LocalVar
$$TestLocalVariable __testLocalVar = new $$TestLocalVariable();
```
之后你可以在方法体中使用 __testLocalVar.testValue

##### 注：`ViewLocalVar`提供默认的 Context __context，TypedValue ___typedValue，Resources ___resources，ViewGroup.LayoutParams ___cur_layout_param(当前View的LayoutParam) 可直接引用

##### OnEnd注解可以聚合多个属性同时设置，一般需要用到`LocalVar`注解共享变量

```java
//当有 android:layout_width 或 android:layout_height 出现时(不影响@Attr(AndroidRS.attr.layout_height)方法调用)，在解析完全部属性后会调用此方法
@OnEnd({AndroidRS.attr.layout_width, AndroidRS.attr.layout_height})
public void onMarginEnd(View view) {
    __testLocalVar.testValue.....
}
```
**注：OnEnd标注的函数必须提供一个参数，参数类型为`ViewParse`注解中`value`定义的类型**

# 扩展已定义View的属性

**注：当前仅支持使用Java且需补全全限定类名**

### 新建Java类，继承待扩展的Gen类，并添加`ViewReplace` 注解，继承已有属性方法并重写逻辑(不支持调用super)，或使用`Attr`注解添加或重写属性，如：

```java
@ViewReplace
public class ViewCompatGen extends ViewGen {
    //扩展已有View的app:layout_scrollFlags属性，重新设置AppBarLayout.LayoutParams
    @Attr(RS.attr.layout_scrollFlags)
    public void viewLayoutScrollFlags(View v, int layout_scrollFlags) {
        android.view.ViewGroup.LayoutParams lp = ___cur_layout_param;
        if (lp instanceof android.support.design.widget.AppBarLayout.LayoutParams) {
            android.support.design.widget.AppBarLayout.LayoutParams layoutParams = (android.support.design.widget.AppBarLayout.LayoutParams) lp;
            layoutParams.setScrollFlags(layout_scrollFlags);
        }
    }
}
```
### 4. 方法参数说明
目前支持 `int`, `float`, `boolean`, `String`, `ValueInfo`

**注1：不支持 Integer, Float, Boolean**

**注2：color 值请使用 `ValueInfo`**
##### 1： `int` 

|     attr | result|    
| :----------: | :------: | 
|     reference|   R.x.x  |  
 |   dimension |  int value |
 |       integer       |  int value  |
|     enum/flag |  int value   | 
| @null  | 0  |

##### 2： `float`

|     attr | result| 
| :----------: | :------: |
|     reference|  float value only when REFERENCE_DIMEN type  |
|   dimension |  float value | 
 |       float      |  int value  |
|    fraction |  float value   |
|  @null | 0f  |

##### 3： `boolean`

|     attr | result|
| :----------: | :------: |
|     reference|  boolean value when REFERENCE_STRING and REFERENCE_BOOLEAN type  |
|   "true/false" |  boolean value |
|      @null     | true  |

##### 4： `String`

|     attr | result|
| :----------: | :------: | 
|     reference|  string value only when REFERENCE_STRING type  | 
 |   any "" |  string value |  
 |       @null |  ""  |

##### 5： `ValueInfo`

```java
class ValueInfo {

    @JvmField
    var type: Int = Constants.FORMAT_UN_KNOW  //the value type
    @JvmField
    var stringValue: String? = null   //string value，not null，eg: "true", "abc", "R.dimen.*", (when it is R.string.* reference, will convert to string value)
    @JvmField
    var floatValue: Float = 0f //float value
    @JvmField
    var resourceId: Int = 0 //reference res value
    @JvmField
    var intValue: Int = 0  //int value
    @JvmField
    var longValue: Long = 0 //long value
    @JvmField
    var colorValue: Int = 0 //color value
    @JvmField
    var booleanValue: Boolean = false //boolean value

    @JvmField
    var referenceType: ValueType = ValueType.SOURCE_STRING //the reference type
}
```
### 5. 使用createViewListener
##### 1. 打开配置
qxml {
    ...
    useCreateViewListener true
    ...
}
##### 2. 设置createViewListener
QxmlInflater.createViewListener = object : CreateViewListener {
            override fun onCreateView(
                parentView: View?,
                view: View,
                context: Context,
                viewClassName: String,
                originViewClassName: String
            ) {

            }
        }







