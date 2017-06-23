## MVC ##
![screenshot_1](./images/mvc.png)

- View: 对应于 Activity 和 XML，负责 View 的绘制以及与用户的交互。
- Controller：业务逻辑
- Model: 实体模型（数据的获取、存储、数据状态变化）
    
    1. View 层传送指令到 Controller 层。
    2. Controller 层完成业务逻辑后，要求 Model 层改变状态。
    3. Model 层将新的数据发送到 View 层，使用户得到反馈。

缺陷: View 层和 Model 层是相互可知，耦合性大，像 Activity 或者 Fragment 既在 Controller 层，又在 View 层，造成工程的可扩展性可维护性非常差。


## MVP ##
![screenshot_1](./images/mvp.png)

- View: 对应于 Activity 和 XML，负责 View 的绘制以及与用户的交互。
- Model: 实体模型（数据的获取、存储、数据状态变化）
- Presenter: 负责完成 View 与 Model 间的交互和业务逻辑。

    1. MVP 模式各层之间的通信，都是双向的。
    2. View 层与 Model 层不直接发生联系，都通过 Presenter 层进行间接通信。
    3. Model 层与 Presenter 层，Presenter 层与 View 层之间通过接口建立联系。
    
缺陷:由于我们使用了接口的方式去连接 View 层和 Presenter 层，这样就导致了特定场景下的一些问题，当你的页面逻辑很复杂的时候，你的 View 层实现的接口会有很多，如果你的 App 中有很多个这样复杂的页面，维护接口的成本就会变的非常的大。


## 区别
MVP 与 MVC 最大的不同，其实是 Activity 职责的变化，由原来的 C (控制层) 变成了 V (视图层)，不再管控制层的问题，只管如何去显示。控制层的角色就由我们的新人 Presenter 来担当，这种架构就解决了 Activity 过度耦合控制层和视图层的问题。


## Libs
- [AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)
- [PageStateManager](https://github.com/hss01248/PageStateManager)
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [KLog](https://github.com/ZhaoKaiQiang/KLog)
- [FastJson](https://github.com/alibaba/fastjson)
- [EventBus](https://github.com/greenrobot/EventBus)
- [Glide](https://github.com/bumptech/glide)
- [LRecyclerView](https://github.com/jdsjlzx/LRecyclerView)
- [Dagger2](https://github.com/google/dagger)
- [RxJava2](https://github.com/ReactiveX/RxJava)
- [Retrofit](https://github.com/square/retrofit)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [RxLifecycle](https://github.com/trello/RxLifecycle)
- [Leakcanary](https://github.com/square/leakcanary)
- [PermissionsDispatcher](https://github.com/hotchemi/PermissionsDispatcher)


## Thanks
- [googlesamples/android-architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)
- [haibuzou](https://github.com/haibuzou/MVPSample)
- [google官方demo比较分析](http://www.jianshu.com/p/14283d8d3a60)


## 进阶 ##
[MVVM](https://github.com/KobeBryant824/MVVM-RxJava)


## License
```
Copyright 2017 XinHai Chen

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
