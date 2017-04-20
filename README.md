## 简述
MVP与MVC最大的不同，其实是Activity职责的变化，由原来的C (控制层) 变成了 V(视图层)，不再管控制层的问题，只管如何去显示。控制层的角色就由我们的新人 Presenter来担当，这种架构就解决了Activity过度耦合控制层和视图层的问题。

- View: 对应于Activity和XML，负责View的绘制以及与用户的交互。
- Model: 实体模型（数据的获取、存储、数据状态变化）
- Presenter: 负责完成View与Model间的交互和业务逻辑。
- Ps：Sample1、2对比，两者思想一样，只是写法不同，Sample2是依据google例子来写的

## Libraries
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [PermissionsDispatcher](https://github.com/hotchemi/PermissionsDispatcher)
- [KLog](https://github.com/ZhaoKaiQiang/KLog)
- [FastJson](https://github.com/alibaba/fastjson)
- [EventBus](https://github.com/greenrobot/EventBus)
- [Glide](https://github.com/bumptech/glide)
- [LRecyclerView](https://github.com/jdsjlzx/LRecyclerView)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [RxJava2](https://github.com/ReactiveX/RxJava)
- [Retrofit](https://github.com/square/retrofit)
- [Leakcanary](https://github.com/square/leakcanary)
- [AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)
- [PageStateManager](https://github.com/hss01248/PageStateManager)


## Thanks
- [haibuzou](https://github.com/haibuzou/MVPSample/tree/master)
- [Android_小宝](http://www.jianshu.com/p/14283d8d3a60)
- [GoogleMvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)


## 弊端 ##
- Presenter（以下简称P）层与View（以下简称V）层是通过接口进行交互的，接口粒度不好控制。粒度太小，就会存在大量接口的情况，使代码太过碎版化；粒度太大，解耦效果不好。同时对于UI的输入和数据的变化，需要手动调用V层或者P层相关的接口，相对来说缺乏自动性、监听性。如果数据的变化能自动响应到UI、UI的输入能自动更新到数据，那该多好！

- MVP是以UI和事件为驱动的传统模型，更新UI都需要保证能获取到控件的引用，同时更新UI的时候要考虑当前是否是UI线程，也要考虑Activity的生命周期（是否已经销毁等）。数据都是被动地通过UI控件做展示，但是由于数据的时变性，我们更希望数据能转被动为主动，希望数据能更有活性，由数据来驱动UI。

- V层与P层还是有一定的耦合度。一旦V层某个UI元素更改，那么对应的接口就必须得改，数据如何映射到UI上、事件监听接口这些都需要转变，牵一发而动全身。如果这一层也能解耦就更好了。

- 复杂的业务同时也可能会导致P层太大，代码臃肿的问题依然不能解决。


## TODO ##

- 解决mvp的弊端，使用MVVM


## License
   Apache-2.0
