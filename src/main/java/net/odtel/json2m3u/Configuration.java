package net.odtel.json2m3u;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@org.springframework.context.annotation.Configuration
@Slf4j
public class Configuration {

    @Bean
    public Retrofit retrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        return new Retrofit.Builder()
                .baseUrl("http://sys.bonus-tv.tv/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();

    }

    @Bean
    public AgentEndpoint agentEndpoint(Retrofit retrofit) {
        return retrofit.create(AgentEndpoint.class);
    }
}
