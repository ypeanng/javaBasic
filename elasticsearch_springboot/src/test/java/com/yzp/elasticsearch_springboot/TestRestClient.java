package com.yzp.elasticsearch_springboot;

import java.io.IOException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestOperations;

@SpringBootTest
public class TestRestClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void test() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("ems","emp","1");
        System.out.println(restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT).status());

    }
    @Test
    public void testSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("ems");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery())
        .from(0)
        .size(10)
        .sort("age", SortOrder.DESC)
        .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false))
        ;

        searchRequest.types("emp").source(builder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);

        System.out.println("符合条件的文档总数："+searchResponse.getHits().getTotalHits());
        System.out.println("符合条件的文档最大得分："+searchResponse.getHits().getMaxScore());
        System.out.println(searchResponse.getHits().getHits());

    }

    @Test
    public void testUpdate() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("  ems","emp","-TFsTHMBqA9jl-BNQ6RL");
        updateRequest.index("ems").type("emp")
        .doc("{\"name\":\"王华\"}", XContentType.JSON);

        UpdateResponse response = restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    public void testBulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest indexRequest = new IndexRequest("ems","emp");
        indexRequest.source("{}",XContentType.JSON);

        UpdateRequest updateRequest = new UpdateRequest("ems","emp","1");
        bulkRequest.add(indexRequest).add(updateRequest);
        restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
    }

}
