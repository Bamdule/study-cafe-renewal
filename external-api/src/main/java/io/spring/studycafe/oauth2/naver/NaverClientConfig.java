package io.spring.studycafe.oauth2.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.studycafe.oauth2.naver.authorization.NaverAuthorizationApi;
import io.spring.studycafe.oauth2.naver.resource.NaverResourceApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class NaverClientConfig {

    private final ObjectMapper objectMapper;
    private static final HttpLoggingInterceptor loggingInterceptor
        = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public NaverClientConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean(name = "naverAuthorizationApi")
    public NaverAuthorizationApi naverOauth2Api() {
        //retrofit 상세 설정
        OkHttpClient client = new OkHttpClient.Builder()
            //서버로 요청하는데 걸리는 시간을 제한 (15초 이내에 서버에 요청이 성공해야한다. (handshake))
            .connectTimeout(15, TimeUnit.SECONDS)
            //서버로 요청이 성공했고, 응답데이터를 받는데 시간을 제한한다. (15초 이내에 응답 데이터를 전달받아야한다)
            .addInterceptor(loggingInterceptor)
            .build();

        return new Retrofit.Builder()
            .baseUrl("https://nid.naver.com")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(client)
            .build()
            .create(NaverAuthorizationApi.class);
    }

    @Bean(name = "naverResourceApi")
    public NaverResourceApi naverOpenApi() {
        //retrofit 상세 설정
        OkHttpClient client = new OkHttpClient.Builder()
            //서버로 요청하는데 걸리는 시간을 제한 (15초 이내에 서버에 요청이 성공해야한다. (handshake))
            .connectTimeout(15, TimeUnit.SECONDS)
            //서버로 요청이 성공했고, 응답데이터를 받는데 시간을 제한한다. (15초 이내에 응답 데이터를 전달받아야한다)
            .addInterceptor(loggingInterceptor)
            .build();

        return new Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(client)
            .build()
            .create(NaverResourceApi.class);
    }

//    @Bean(name = "naverOpenApiRestClient")
//    public RestClient naverOpenApiRestClient() {
//        return RestClient.create("https://openapi.naver.com");
//    }
}
