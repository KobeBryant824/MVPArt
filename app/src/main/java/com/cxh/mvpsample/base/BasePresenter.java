/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cxh.mvpsample.base;

public interface BasePresenter {

//    void start(); 原生
    void subscribe();

    // 注销V层引用、手动释放后台请求，避免造成内存泄漏（当然用rxlifecycle2就可以很好解决但是只能在RxActivity中、RxFragment调用，或者m层持有context主动关联）
    void unSubscribe();

}
