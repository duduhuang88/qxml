
[English](EXTENSION_EN.md)

# ��չ�Զ���View

��gradle.properties����� QXML_VALID_CODE=*** (����ַ���)

**ע����ǰ��֧��ʹ��Java**

### 1. �½�Java�࣬����Ҫ��չ View �ļ̳�����̳��ض���Gen�࣬�����`ViewParse` ע�⣬����TextView�̳���View����TextView�̳�View֧�ֵ���������
```java
@ViewParse(TextView.class)
public class TextViewGen extends ViewGen {
}
```
**`ViewParse` ����˵��**
```java
public @interface ViewParse {
    //������࣬��AppCompatTextView
    Class<?> value();
    //compat��Ļ��࣬��AppCompatTextView�Ļ���ΪTextView
    Class<?> compatOf() default Void.class;
    //compat������ʹ�� `AndroidRS.attr.*` �� `RS.attr.*` ��䣬����������ʱ������������ compat �࣬�磺{ RS.attr.lineHeight, AndroidRS.attr.lineHeight }�������� `app:lineHeight` �� `android:lineHeight` ʱ������� `AppCompatTextView`
    String[] compatCondition() default {};
    //ViewGroupʱĬ�ϵ�layoutParam��ʼ����䣬ʹ��ȷ����ֵ���Ա��ⷴ�䣬 �磺layoutParamInit = "new android.widget.FrameLayout.LayoutParams(-1, -1)"����ʹ��ȫ�޶�����
    String layoutParamInit() default "";
    //�Ƿ����onFinishInflate
    CallOnInflateConfig callOnFinishInflate() default CallOnInflateConfig.EXTENDS;
}
```

##### ע��������һ��ViewGroup���͵�Viewʱ������ȱʧ generateDefaultLayoutParams ���̣���Ҫʹ��`ViewReplace`���ȱʧ���������ã��������� LayoutParam������ ��չ�Ѷ���View������
### 2. ������Ҫ����ӻ�̳���Ҫ��չ������

```java
@ViewParse(TextView.class)
public class TextViewGen extends ViewGen {

    //��չandroid:gravity����
	@Attr(AndroidRS.attr.gravity)
    public void textViewGravity(TextView textView, int gravityFlag) {
        textView.setGravity(gravityFlag);
    }
	
	//�޸�android:clickable�߼�����֧�ֵ���super
	@Override
    public void viewClickable(View view, boolean clickable) {
        //��֧�ֵ���super
        //super.viewClickable(view, clickable);
        view.setClickable(clickable);
    }

}
```
##### ע1��Attrע������ʹ�� AndroidRS.attr.* ����Android���Ի� RS.attr.* �����Զ������ԣ�����Ҳ���RS�࣬���� build project ��������
##### ע2��Attrע��ķ����������������������һ����������Ϊ`ViewParse`ע����`value`��������ͣ��ڶ�����������²���˵��
##### ע3��Attrע��ķ����ᰴ��������˳��ִ��

### 3. ������Ҫ��ʹ��`LocalVar`��`OnEnd`ע��

##### LocalVarע��ɱ��һ����������ݴ����ֵ��������ʹ��

**ע�����干�������ʹ��ȫ�޶�����**

```java
public static class $$TestLocalVariable {
    public int testValue = 0;
    //ʹ��ȫ�޶�����
    public android.graphics.drawable.Drawable drawable = null;
}

@LocalVar
$$TestLocalVariable __testLocalVar = new $$TestLocalVariable();
```
֮��������ڷ�������ʹ�� __testLocalVar.testValue

##### ע��`ViewLocalVar`�ṩĬ�ϵ� Context __context��TypedValue ___typedValue��Resources ___resources��ViewGroup.LayoutParams ___cur_layout_param(��ǰView��LayoutParam) ��ֱ������

##### OnEndע����Ծۺ϶������ͬʱ���ã�һ����Ҫ�õ�`LocalVar`ע�⹲�����

```java
//���� android:layout_width �� android:layout_height ����ʱ(��Ӱ��@Attr(AndroidRS.attr.layout_height)��������)���ڽ�����ȫ�����Ժ����ô˷���
@OnEnd({AndroidRS.attr.layout_width, AndroidRS.attr.layout_height})
public void onMarginEnd(View view) {
    __testLocalVar.testValue.....
}
```
**ע��OnEnd��ע�ĺ��������ṩһ����������������Ϊ`ViewParse`ע����`value`���������**

# ��չ�Ѷ���View������

**ע����ǰ��֧��ʹ��Java**

### �½�Java�࣬�̳д���չ��Gen�࣬�����`ViewReplace` ע�⣬�̳��������Է�������д�߼�(��֧�ֵ���super)����ʹ��`Attr`ע����ӻ���д���ԣ��磺

```java
@ViewReplace
public class ViewCompatGen extends ViewGen {
    //��չ����View��app:layout_scrollFlags���ԣ���������AppBarLayout.LayoutParams
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
### 4. ��������˵��
Ŀǰ֧�� `int`, `float`, `boolean`, `String`, `ValueInfo`

**ע1����֧�� Integer, Float, Boolean**

**ע2��color ֵ��ʹ�� `ValueInfo`**
##### 1�� `int` 

|     attr | result|    
| :----------: | :------: | 
|     reference|   R.x.x  |  
 |   dimension |  int value |
 |       integer       |  int value  |
|     enum/flag |  int value   | 
| @null  | 0  |

##### 2�� `float`

|     attr | result| 
| :----------: | :------: |
|     reference|  float value only when REFERENCE_DIMEN type  |
|   dimension |  float value | 
 |       float      |  int value  |
|    fraction |  float value   |
|  @null | 0f  |

##### 3�� `boolean`

|     attr | result|
| :----------: | :------: |
|     reference|  boolean value when REFERENCE_STRING and REFERENCE_BOOLEAN type  |
|   "true/false" |  boolean value |
|      @null     | true  |

##### 4�� `String`

|     attr | result|
| :----------: | :------: | 
|     reference|  string value only when REFERENCE_STRING type  | 
 |   any "" |  string value |  
 |       @null |  ""  |

##### 5�� `ValueInfo`

```java
class ValueInfo {

    @JvmField
    var type: Int = Constants.FORMAT_UN_KNOW  //the value type
    @JvmField
    var stringValue: String? = null   //string value��not null��eg: "true", "abc", "R.dimen.*", (when it is R.string.* reference, will convert to string value)
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
### 5. ʹ��createViewListener
##### 1. ������
```gradle
qxml {
    ...
    useCreateViewListener true
    ...
}
```
##### 2. ����createViewListener
```java
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
```







