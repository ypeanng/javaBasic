package com.yzp.esconfig;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDocumentCRUD {

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

    @Test
    public void testCreateDocument() throws ExecutionException, InterruptedException {
        //参数： 类型名   参数2：映射数据 参数3 ：映射json格式
        DangDangBook book = new DangDangBook("黄帝内经",1000,"这是一本古老的医学专著，阐明了中华大地千年积累的，对人的理解，其名称为黄帝内经",new BigDecimal(10.3),
            "2020-02-03","1");
        IndexRequestBuilder builder = transportClient.prepareIndex("dangdang","book",book.getId()).setSource(JsonUtil.toJson(book),XContentType.JSON);
        IndexResponse indexResponse  = builder.get();
        System.out.println(indexResponse.status());
    }
    @Test
    public void testUpdateDocument(){
        DangDangBook book = new DangDangBook();
        book.setContent("dsafdfa这是一本值得深入研究诵读的好书");
        UpdateResponse indexResponse=  transportClient.prepareUpdate("dangdang","book","1")
            .setDoc(JsonUtil.toJsonWithoutNull(book),XContentType.JSON).get();
        System.out.println(indexResponse.status());
    }
    @Test
    public void testDelDocument(){
        DangDangBook book = new DangDangBook("黄帝内经1",1000,"这是一本古老的医学专著，阐明了中华大地千年积累的，对人的理解，其名称为黄帝内经",new BigDecimal(10.3),
            "2020-02-03","1");
        DeleteResponse indexResponse=  transportClient.prepareDelete("dangdang","book",book.getId()).get();
        System.out.println(indexResponse.status());
    }
    @Test
    public void findOne(){
        GetResponse getResponse = transportClient.prepareGet("dangdang","book","1").get();
        System.out.println(getResponse.getSourceAsString());

        DangDangBook book = JsonUtil.fromJsonLax(getResponse.getSourceAsString(),DangDangBook.class);
        System.out.println(book.getContent());

    }

    @Test void testSearch(){
        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")
            .setTypes("book")
            .setQuery(QueryBuilders.matchAllQuery())
            .get();
        System.out.println(searchResponse.getHits().getTotalHits());

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println();
    }

    @Test
    public void testQuery(){
        TermQueryBuilder builder = QueryBuilders.termQuery("content","好书");
        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")
            .setTypes("book")
            .setQuery(builder)
            .get();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }
    //批量操作
    @Test
    public void testQueryBulk(){
        Emp emp = new Emp("12","张三丰",23,new Date(),"","北京");
        //添加
        IndexRequest indexRequest = new IndexRequest("ems","emp",emp.getId());
        indexRequest.source(JsonUtil.toJsonWithoutNull(emp),XContentType.JSON);
        //删除
        DeleteRequest deleteRequest = new DeleteRequest("ems","emp",emp.getId());
        //修改
        emp= new Emp();
        emp.setId("-TFsTHMBqA9jl-BNQ6RL");
        emp.setContent("Spring Boot 可以轻松创建，production-grade Spring-based Applications，你可以 run。我们对 Spring 平台和 third-party libraries 采取了自以为是的观点，");
        UpdateRequest updateRequest = new UpdateRequest("ems","emp",emp.getId());
        updateRequest.doc(JsonUtil.toJsonWithoutNull(emp),XContentType.JSON);


        BulkRequestBuilder updateBuilder = transportClient.prepareBulk();
        BulkResponse indexResponse = updateBuilder
            .add(indexRequest)
            .add(deleteRequest)
            .add(updateRequest)
            .get();
        for (BulkItemResponse item : indexResponse.getItems()) {
            System.out.println(item.status());
        }
    }
}