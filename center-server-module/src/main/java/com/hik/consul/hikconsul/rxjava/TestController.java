package com.hik.consul.hikconsul.rxjava;

import okhttp3.*;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/api/v1/user/")
public class TestController {


    @Resource(name = "defaultThreadPool")
    private ThreadPoolTaskExecutor executor;


    private final AtomicReference<String> atomicReference = new AtomicReference<>();


    @GetMapping(value = "getUserInfo")
    public String getUserInfo(){
        String main = main();
        System.out.println("已经取到缓存了"+main);
        return main;
    }

    private final static String BASE_URL = "https://rqm.hikyun.com/rqm";

    private final static String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRob3JpemF0aW9uIiwicGF5bG9hZCI6IntcImRlcGFydG1lbnRJZFwiOjQ2MTI5NTMyNDM0NjMwMDk2MzIsXCJleHBpcmVkXCI6MTM1MDAsXCJuaWNrTmFtZVwiOlwiMTIzXCIsXCJwcm9kdWN0Q29kZVwiOlwiMTU4OTAxMDg5MTg0MjQ2M1wiLFwicHJvamVjdElkXCI6MTI2NzIyNTAzNTYyMTcyOCxcInJlbGF0aW9uVHlwZVwiOjAsXCJ0aW1lXCI6XCIxNjA0MjQyOTY3Mzc0XCIsXCJ0eXBlXCI6MSxcInVzZXJJZFwiOjI4MTg5MTIxNjQyMjExMixcInVzZXJOYW1lXCI6XCJ0ZXN0XCIsXCJ1c2VyVHlwZVwiOjF9In0.013c_6Kc1CgN9HM3Hp_wymuGS4oJOL9xQAIRNhURFjHmGWigCvn9IqxYsvMD6JgOnSRY5KqfWIOXiL9ET3xFMg";

    private OkHttpClient okHttpClient;

    @Autowired
    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    private final static ConcurrentHashMap<String, Object> CACHE_MAP = new ConcurrentHashMap<>(1 << 3 << 3);

    // 异步操作
    public String main() {
    @NonNull
    Observable<Object> memory =
        Observable.create(
                emitter -> {
                  Object userInfo = CACHE_MAP.get("userInfo");
                  if (userInfo != null) {
                    emitter.onNext("在缓存中请求到的"+userInfo);
                  } else {
                    emitter.onComplete();
                  }
                });

    @NonNull
    Observable<Object> network =
        Observable.create(
            emitter -> {
              Object userInfo = CACHE_MAP.get("userInfo");
              if (userInfo == null) {
                String post = postRequest(BASE_URL + "/ui/user/checkUserInfo", "user");
                if (!TextUtils.isEmpty(post)) {
                  CACHE_MAP.putIfAbsent("userInfo", post);
                  System.out.println("在网络中请求到的"+post);
                  emitter.onNext("在网络中请求到的" + post);
                  emitter.onNext(post);
                } else {
                  emitter.onComplete();
                }
              }
            });

        @NonNull
    Disposable subscribe =
        Observable.concat(memory, network)
            .subscribeOn(Schedulers.from(executor))
            .subscribe(s -> atomicReference.set((String) s));

    return atomicReference.get();
  }

  private String execNewCall(Request request){
      try (Response response = okHttpClient.newCall(request).execute()) {
          int code = response.code();
          if (response.isSuccessful() && code == 200) {
              Assert.notNull(response.body(), () -> "response must is not null");
              return response.body().string();
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }

  private String postRequest(String url, String json){
      RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
      Request request = new Request.Builder()
              .url(url)
              .header("Authorization",TOKEN)
              .post(requestBody)
              .build();
        return execNewCall(request);
  }

    
}
