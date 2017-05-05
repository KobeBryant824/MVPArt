## MVC ##
![screenshot_1](./images/mvc.png)

- View: 对应于Activity和XML，负责View的绘制以及与用户的交互。
- Controller：业务逻辑
- Model: 实体模型（数据的获取、存储、数据状态变化）
    
    1. View层传送指令到Controller层。
    2. Controller 层完成业务逻辑后，要求Model层改变状态。
    3. Model层将新的数据发送到View层，使用户得到反馈。

**缺陷:**View层和Model层是相互可知，耦合性大，像Activity或者Fragment既在Controller层，又在View层，造成工程的可扩展性可维护性非常差。


## MVP ##
![screenshot_1](./images/MVP.png)

- View: 对应于Activity和XML，负责View的绘制以及与用户的交互。
- Model: 实体模型（数据的获取、存储、数据状态变化）
- Presenter: 负责完成View与Model间的交互和业务逻辑。

    1. MVP模式各层之间的通信，都是双向的。
    2. View层与Model层不直接发生联系，都通过Presenter层进行间接通信。
    3. Model层与Presenter层，Presenter层与View层之间通过接口建立联系。
    
**缺陷:**由于我们使用了接口的方式去连接View层和Presenter层，这样就导致了特定场景下的一些问题，当你的页面逻辑很复杂的时候，你的View层实现的接口会有很多，如果你的App中有很多个这样复杂的页面，维护接口的成本就会变的非常的大。


## 区别
MVP与MVC最大的不同，其实是Activity职责的变化，由原来的C (控制层) 变成了 V(视图层)，不再管控制层的问题，只管如何去显示。控制层的角色就由我们的新人Presenter来担当，这种架构就解决了Activity过度耦合控制层和视图层的问题。


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
- [GoogleMvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)
- [haibuzou](https://github.com/haibuzou/MVPSample/tree/master)
- [Android_小宝](http://www.jianshu.com/p/14283d8d3a60)


## TODO ##
解决mvp的弊端，使用[MVVM](https://github.com/KobeBryant824/MVVMSample)


## License
   Apache-2.0
