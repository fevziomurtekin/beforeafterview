# BeforeAfterView

Before After animation library write in Kotlin.

<br>

[![](https://jitpack.io/v/fevziomurtekin/BeforeAfterView.svg)](https://jitpack.io/#fevziomurtekin/BeforeAfterView) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-BeforeAfterView-green.svg?style=flat )]( https://android-arsenal.com/details/1/7782 )

# Demo

<p align="center">
<img src="https://github.com/fevziomurtekin/BeforeAfterView/blob/master/art/record.gif" width="270"  height="480" />
</p>

# Setup
## Gradle
```Gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
  
  .....

dependencies {
      implementation 'com.github.fevziomurtekin:BeforeAfterView:1.0.0'
   }
}
```

## Layout

```xml
   <com.fevziomurtekin.beforeafterview.BeforeAfterView
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           app:bgColor="@android:color/black"
           app:sliderTintColor="@android:color/white"
           app:sliderIconTint="@android:color/white"
           app:afterSrc="@drawable/after"
           app:beforeSrc="@drawable/before"
           app:imageHeightPercent="0.5"
           app:sliderWidthPercent="0.75"
   />

```

 # Attributes

  | Attribute | Description |
| --- | --- |
| `bgColor` |The color in int of the background color (by default @android:color/black) | 
| `sliderTintColor` | The color int of the slider tint color (by default @android:color/white) |
| `sliderIconTint` | The size in int of the slider icon color (by default @android:color/white)|
| `afterSrc` | The resource in int of the imageview src|
| `beforeSrc` | The resource in int of the imageview src|
| `imageHeightPercent`|The height percent of the imageView (by default 0.55f) |
| `sliderWidthPercent` |The width percent of the sliderView (by default 0.65f) |

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details
