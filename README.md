# JsonPlaceHolderExample

[JSONPlaceholder](https://jsonplaceholder.typicode.com/)에서 제공하는 fake API 활용 Android sample.

Android AAC-ViewModel과 Adapter ViewModel을 사용하였다.
MainActivity와 DetailActivity 2개로 구분되어 있으며, 각각의 ViewModel에서 데이터를 불러와 갱신하는 작업을 한다.
[JSONPlaceholder](https://jsonplaceholder.typicode.com/)에서 제공하는 fake API 활용 Android sample.

다음 샘플은 아래와 같은 API을 활용한다.

- get posts : @GET https://jsonplaceholder.typicode.com/posts
  - option : _start={page}, _limit={per_page}
- get post : @GET https://jsonplaceholder.typicode.com/posts/{postId}
- get comments : @GET https://jsonplaceholder.typicode.com/posts/{postId}/comments/
- delete post : @DELETE https://jsonplaceholder.typicode.com/posts/{postId}


# Use Android API

아래의 Android API을 활용.

- <a href="https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat">android support appcompat-v7</a>
- <a href="https://developer.android.com/topic/libraries/support-library/packages#v7-recyclerview">android support recyclerview-v7</a>
- [android support constraint-layout](https://developer.android.com/training/constraint-layout/)
- [android lifecycle extensions](https://developer.android.com/topic/libraries/architecture/adding-components)
- [airbnb lottie](http://airbnb.io/lottie/android/android.html#getting-started)
- [squareup retrofit2](http://square.github.io/retrofit/)
- [squareup retrofit2:adapter-rxjava2](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2)
- [squareup retrofit2:converter-gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
- [squareup okhttp3](https://github.com/square/okhttp)
- [squareup okhttp3:logging-interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
- [reactivex.rxjava2:rxandroid](https://github.com/ReactiveX/RxAndroid)
- [reactivex.rxjava2:rxjava](https://github.com/ReactiveX/RxJava)
- [junit:junit](https://developer.android.com/training/testing/junit-rules)


# License

```
Copyright 2018 Tae-hwan

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
