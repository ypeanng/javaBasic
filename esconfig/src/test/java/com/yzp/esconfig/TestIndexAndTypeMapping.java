package com.yzp.esconfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexAction;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestIndexAndTypeMapping {

    private TransportClient transportClient;

    @BeforeEach
    public void before(){
        this.transportClient = new PreBuiltTransportClient(Settings.EMPTY);
        try {
            transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName("182.92.107.17"),9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void after(){
        transportClient.close();
    }

    //创建索引
    @Test
    public void testIndex(){
        CreateIndexResponse createIndexResponse = transportClient.admin().indices().prepareCreate("dangdang").get();
        boolean acknowledged =  createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    @Test void testDelIndex(){
        AcknowledgedResponse response = transportClient.admin().indices().prepareDelete("dangdang").get();
        System.out.println(response.isAcknowledged());
    }

    @Test
    public void testCreateIndexAndTypeMapping() throws ExecutionException, InterruptedException {
        CreateIndexRequest request  = new CreateIndexRequest("dangdang");
        //参数： 类型名   参数2：映射数据 参数3 ：映射json格式
        request.mapping("book","{\"properties\":{\"name\":{\"type\":\"keyword\"},\"words\":{\"type\":\"integer\"},\"content\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"},\"price\":{\"type\":\"double\"},\"publishDate\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"}}}", XContentType.JSON);
        ActionFuture<CreateIndexResponse> response = transportClient.admin().indices().create(request);

        System.out.println(response.get().isAcknowledged());
    }



}