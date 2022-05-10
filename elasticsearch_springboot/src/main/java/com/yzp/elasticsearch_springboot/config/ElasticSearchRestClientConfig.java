package com.yzp.elasticsearch_springboot.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchRestClientConfig extends AbstractElasticsearchConfiguration {

    //这个client 用来替换 transportclient 管理
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        //定义一个客户端配置对象
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo("182.92.107.17:9200")
            .build();
        //通过restClient 对象创建
        return RestClients.create(clientConfiguration).rest();
    }
}
