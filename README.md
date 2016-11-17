# 强大的SpannableStringBuilder

##前言
工作找完了，已经干了两个星期。虽然经常加班，不过相比之前的工作，现在过得更加充实、更有意义。
现在有点空闲时间，继续我的分享之旅~~
##效果
什么都不说，先看个炫酷的效果。

![效果](http://upload-images.jianshu.io/upload_images/1638147-ee5d631a05dffb5d.gif?imageMogr2/auto-orient/strip)


*‘什么，这也算炫酷？’*也许你会这么说
如果我告诉你，**这个页面中只用了一个`TextView`**，对！只有一个，没别的。
这时候你还觉得不炫酷？

##实现
是时候把`SpannableStringBuilder`介绍给大家了
先看看Google官方的介绍
> This is the class for text whose content and markup can both be changed.
（这是一个内容和标记都可以更改的文本类）

不同于我们平时赋值时使用的`String`、`StringBuffer`等，只能给`TextView`设置文本内容，而文本的样式只能用`TextView`来控制，而且该样式的可定制性还不大好。
#### 介绍
`SpannableStringBuilder`有个亲兄弟——`SpannableString`。是不是觉得有点熟悉？似乎看到了`StringBuilder`、`String`的影子...
是的，`SpannableStringBuilder`和`SpannableString`的区别类似与`StringBuilder`、`String`，就是`SpannableStringBuilder`可以拼接，而`SpannableString`不可拼接。
![Class General Hierarchy](http://upload-images.jianshu.io/upload_images/1638147-e632df95127dbbde.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
图片来自http://blog.csdn.net/fengkuanghun/article/details/7904284
由图中可以看出，他们都继承了`CharSequence`，因此，他们可以直接在`TextView`的`setText`中使用

#### 主要的方法
`SpannableStringBuilder`和`SpannableString`主要通过使用`setSpan(Object what, int start, int end, int flags)`改变文本样式。
对应的参数：
- start： 指定Span的开始位置
- end：  指定Span的结束位置，并不包括这个位置。
- flags：取值有如下四个
 - `Spannable.SPAN_EXCLUSIVE_INCLUSIVE`：在 Span前面输入的字符不应用 Span的效果，在后面输入的字符应用Span效果。 
 - `Spannable.SPAN_INCLUSIVE_EXCLUSIVE`：在 Span前面输入的字符应用 Span 的效果，在后面输入的字符不应用Span效果。
 - `Spannable.SPAN_INCUJSIVE_INCLUSIVE`：在 Span前后输入的字符都应用 Span 的效果。
 - `Spannable.SPAN_EXCLUSIVE_EXCLUSIVE`：前后都不包括。
- what： 对应的各种Span，不同的Span对应不同的样式。已知的可用类有：
 - `BackgroundColorSpan` : 文本背景色
 - `ForegroundColorSpan` : 文本颜色
 - `MaskFilterSpan` : 修饰效果，如模糊(BlurMaskFilter)浮雕
 - `RasterizerSpan` : 光栅效果
 - `StrikethroughSpan` : 删除线
 - `SuggestionSpan` : 相当于占位符
 - `UnderlineSpan` : 下划线
 - `AbsoluteSizeSpan` : 文本字体（绝对大小）
 - `DynamicDrawableSpan` : 设置图片，基于文本基线或底部对齐。
 - `ImageSpan` : 图片
 - `RelativeSizeSpan` : 相对大小（文本字体）
 - `ScaleXSpan` : 基于x轴缩放
 - `StyleSpan` : 字体样式：粗体、斜体等
 - `SubscriptSpan` : 下标（数学公式会用到）
 - `SuperscriptSpan` : 上标（数学公式会用到）
 - `TextAppearanceSpan` : 文本外貌（包括字体、大小、样式和颜色）
 - `TypefaceSpan` : 文本字体
 - `URLSpan` : 文本超链接
 - `ClickableSpan` : 点击事件

####用法
先在xml中创建一个`TextView`：
```java
    <TextView
        android:id="@+id/mode1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18dp" />
```
`SpannableStringBuilder`和`SpannableString`的用法差不多，这边先举一个`SpannableString`的例子
- **SpannableString**
 - 修改字体颜色
```java
    /**
     * 使用SpannableString设置样式——字体颜色
     */
    private void mode1() {
        SpannableString spannableString = new SpannableString("暗影IV已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode1)).setText(spannableString);
    }
```
创建`SpannableString`的时候，传入需要显示的字符串。使用`ForegroundColorSpan`为文字设置颜色。
效果：
![字体颜色](http://upload-images.jianshu.io/upload_images/1638147-b8aaecee25a743d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

后面都以`SpannableStringBuilder`为例子
- **SpannableStringBuilder**
 - 修改字体颜色
```java

    /**
     * 使用SpannableStringBuilder设置样式——字体颜色
     */
    private void mode2() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV");
        spannableString.append("已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode2)).setText(spannableString);
    }
```
这里就可以看出`SpannableStringBuilder`的可拼接性，这里同样采用了`ForegroundColorSpan`为文本设置颜色。
效果：
![字体颜色](http://upload-images.jianshu.io/upload_images/1638147-3097e0950012c871.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 设置背景颜色
```java
    /**
     * 使用SpannableStringBuilder设置样式——背景颜色
     */
    private void mode3() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(bgColorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode3)).setText(spannableString);
    }
```
使用`BackgroundColorSpan`设置背景颜色。
效果：
![背景颜色](http://upload-images.jianshu.io/upload_images/1638147-e5380fea95673c7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 设置字体大小
```java
    /**
     * 使用SpannableStringBuilder设置样式——字体大小
     */
    private void mode4() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(20);
        spannableString.setSpan(absoluteSizeSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode4)).setText(spannableString);
    }
```
使用`AbsoluteSizeSpan`设置字体大小。
效果：
![字体大小](http://upload-images.jianshu.io/upload_images/1638147-a2c921a2c623b38a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 设置粗体\斜体
```java
    /**
     * 使用SpannableStringBuilder设置样式——粗体\斜体
     */
    private void mode5() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        //setSpan可多次使用
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.ITALIC);//斜体
        spannableString.setSpan(styleSpan2, 3, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan3 = new StyleSpan(Typeface.BOLD_ITALIC);//粗斜体
        spannableString.setSpan(styleSpan3, 6, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode5)).setText(spannableString);
    }
```
使用`StyleSpan`设置粗体\斜体，从例子中可以看出，多次使用`setSpan`并不影响。
效果：
![粗体\斜体](http://upload-images.jianshu.io/upload_images/1638147-5cdc70cbe024d3a7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 删除线
```java
    /**
     * 使用SpannableStringBuilder设置样式——删除线
     */
    private void mode6() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode6)).setText(spannableString);
    }
```
StrikethroughSpa使用`StrikethroughSpan`设置删除线。
效果：
![删除线](http://upload-images.jianshu.io/upload_images/1638147-859685de6cc5fe87.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 下划线
```java
    /**
     * 使用SpannableStringBuilder设置样式——下划线
     */
    private void mode7() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode7)).setText(spannableString);
    }
```
使用`UnderlineSpan`设置下划线。
效果：
![下划线](http://upload-images.jianshu.io/upload_images/1638147-16c756a53a157f9f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 图片
不仅支持文字样式，还可以插入图片。厉害了我的`SpannableStringBuilder`~~
```java
   /**
     * 使用SpannableStringBuilder设置样式——图片
     */
    private void mode8() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        //也可以这样
        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        //drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //ImageSpan imageSpan1 = new ImageSpan(drawable);
        //将index为6、7的字符用图片替代
        spannableString.setSpan(imageSpan, 6, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode8)).setText(spannableString);
    }
```
使用ImageSpan设置图片，将index为6、7的字符替换成了图片，也就是说，该图片占有index6和7的位置。
效果：
![图片](http://upload-images.jianshu.io/upload_images/1638147-4bd160ce0783de81.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - 点击事件
插入图片就已经很变态了，居然还支持点击事件。
```java
    /**
     * 使用SpannableStringBuilder设置点击事件
     */
    private void mode9() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView textView = (TextView)findViewById(R.id.mode9);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
```
使用`ClickableSpan`设置点击事件，最后还需要加上`textView.setMovementMethod(LinkMovementMethod.getInstance());`。代码中指定index为5、6、7的字符都成了可点击的文本，其他区域还是不可点击的。
效果：
![点击事件](http://upload-images.jianshu.io/upload_images/1638147-96d2253094d6a9aa.gif?imageMogr2/auto-orient/strip)
 - 组合使用
当然，上面的这些用法都能组合使用。来个🌰
```java
    /**
     * 使用SpannableStringBuilder事件组合使用
     */
    private void mode10() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        //图片
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        spannableString.setSpan(imageSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //文字颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        spannableString.setSpan(colorSpan,5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //文字背景颜色
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(bgColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView textView = (TextView)findViewById(R.id.mode10);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
```
例子中将`ImageSpan`、`ClickableSpan `、`ForegroundColorSpan `、`BackgroundColorSpan `进行了组合使用，大家根据自己的需求，来随意搭配。
效果：（就是刚开始展示的那张gif）
![效果](http://upload-images.jianshu.io/upload_images/1638147-ee5d631a05dffb5d.gif?imageMogr2/auto-orient/strip)

## 总结
看完后，感觉`SpannableStringBuilder`和`SpannableString`相比`String`要强大太多了。额~~没别的了，发表睡觉

## 参考资料
[SpannableString与SpannableStringBuilder](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1009/3553.html)
[API 文档(自备梯子)](https://developer.android.com/reference/android/text/SpannableStringBuilder.html)
[SpannableString与SpannableStringBuilder](http://blog.csdn.net/harvic880925/article/details/38984705)

> 以上有错误之处，感谢指出


