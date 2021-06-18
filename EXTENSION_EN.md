
[中文](EXTENSION.md)

# Extend custom view

**Note: Currently, only Java is supported and fully qualified class names are required**

### 1. Create a new Java class, inherit the Specific Gen class according to the inheritance of the view to be extended, and add the 'ViewParse' annotation. For example, textview inherits from view, then textview inherits all the properties supported by view
```java
@ViewParse(TextView.class)
public class TextViewGen extends ViewGen {
}
```
**`ViewParse` parameter description**
```java
public @interface ViewParse {
    //Class defined, such as AppCompatTextView
    Class<?> value();
    //The base class of compat class, such as AppCompatTextView, is TextView
    Class<?> compatOf() default Void.class;
    //When the condition is satisfied, the base class will be resolved into a compat class, such as: { RS.attr.lineheight, AndroidRS.attr.lineheight }, when there has `app:lineHeight` or `android:lineHeight`, it Will be resolved to `AppCompatTextView`
    String[] compatCondition() default {};
    //The default layoutparam initialization statement when value is a ViewGroup class. Using a certain value can avoid reflection. For example: layoutparaminit = "new Android. Widget. FrameLayout. Layoutparams (- 1, - 1)", please use fully qualified class name
    String layoutParamInit() default "";
    //call onFinishInflate or not
    CallOnInflateConfig callOnFinishInflate() default CallOnInflateConfig.EXTENDS;
}
```

##### Note: when defining a view of ViewGroup type, because the generatedefaultlayoutparams method call is missing, you need to use 'ViewReplace' to add the missing property settings and reset the layoutparam. See extending the properties of the defined view below

### 2. Add or inherit properties that need to be extended as needed

```java
@ViewParse(TextView.class)
public class TextViewGen extends ViewGen {

	//extend android:gravity attribute
	@Attr(AndroidRS.attr.gravity)
    public void textViewGravity(TextView textView, int gravityFlag) {
        textView.setGravity(gravityFlag);
    }
	
	//modify android:clickable Logic, calling super is not supported
	@Override
    public void viewClickable(View view, boolean clickable) {
        //calling super is not supported
        //super.viewClickable(view, clickable);
        view.setClickable(clickable);
    }

}
```
##### Note 1: in Attr annotation, please use androidrs.attr. * to refer to Android property or rs.attr. * to reference custom property. If RS class is not found, build project first to generate
##### Note 2: the method of Attr annotation must contain two parameters. The first parameter type is the type defined by 'value' in the 'ViewParse' annotation. For the second parameter, please refer to the parameter description below
##### Note 3: use the fully qualified class name of the class in the method body, for example: android.support.v4.content.contextcompact.getcolor......

### 3. Use 'LocalVar' and 'OnEnd' annotations as needed

##### LocalVar annotation can mark a shared variable and temporarily store the variable value for later use

```java
public static class $$TestLocalVariable {
    public int testValue = 0;
    //use the fully qualified class name
    public android.graphics.drawable.Drawable drawable = null;
}

@LocalVar
$$TestLocalVariable __testLocalVar = new $$TestLocalVariable();
```
Then you can use __testLocalVar.testValue in the method body 

##### Note: `ViewLocalVar` provides the default Context __context，TypedValue ___typedValue，Resources ___resources，ViewGroup.LayoutParams ___cur_layout_param(layoutparam of the current view) which can be directly referenced

##### OnEnd annotation can aggregate multiple attributes and set them at the same time. Generally, the 'localvar' annotation is used to OnEnd

```java
//When there is android:layout_width or android:layout_height appears (does not affect @Attr(AndroidRS.attr.layout_height) method call) onMarginEnd method will be call after all properties are resolved
@OnEnd({AndroidRS.attr.layout_width, AndroidRS.attr.layout_height})
public void onMarginEnd(View view) {
    __testLocalVar.testValue.....
}
```
**Note: the function annotated by OnEnd must provide a parameter. The parameter type is the type defined by 'value' in the 'ViewParse' annotation**


# Extend properties for defined view

**Note: at present, only Java is supported and the fully qualified class name required**

### Create a new Java class, inherit the gen class to be extended, add 'ViewReplace' annotation, inherit existing attribute methods and override logic (super is not supported), or add or override attributes with 'Attr' annotation, for example:

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
### 4. Method parameter description
support `int`, `float`, `boolean`, `String`, `ValueInfo`

**Note 1：Integer, Float, Boolean is not support**

**Note 2：color value use `ValueInfo`**
##### 1： `int` 

|     attr | result|    
| :----------: | :------: | 
|     reference|   R.x.x  |  
 |   dimension |  int value |
 |       integer       |  int value  |
|     enum|flag |  int value   | 
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






