package cn.guxiangfly.distributelock.springbootstudy.es;


import org.apache.http.auth.*;
import org.apache.http.HttpHost;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author guxiang02
 * @Date 2020/6/29
 **/
@Configuration
public class ElasticSearchConfig {

    String username = "elastic";
    String password= "MYaohfMCQNGqg7vqwp4C";

    @Bean
    public RestHighLevelClient restHighLevelClient(){

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(username, password));

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("39.100.17.251",9200,"http")
        ).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        }));

        return restHighLevelClient;
    }
}
