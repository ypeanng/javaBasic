package com.yzp.esconfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDocumentFilter {

    private TransportClient transportClient;
    @BeforeEach
    public void before() throws UnknownHostException {
        this.transportClient = new PreBuiltTransportClient(Settings.EMPTY);
        this.transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName("182.92.107.17"),9300));
    }

    @AfterEach
    public void after(){
        this.transportClient.close();
    }

    @Test
    public void testQuerySizeSort(){
        SearchResponse searchResponse= transportClient.prepareSearch("ems")
            .setTypes("emp")
            .setPostFilter(QueryBuilders.rangeQuery("age").gte(20).lt(25))
            .setQuery(QueryBuilders.matchAllQuery())
            .get();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * 复杂搜索
     * 基于多字段关键字查询，同时要分页排序，查询前过滤，指定字段返回，高亮字段
     */
    @Test
    public void testQuery(){
        SearchResponse searchResponse = transportClient.prepareSearch("ems").setTypes("emp")
            .setFrom(1)
            .setSize(20)
            .addSort("age", SortOrder.DESC)
            .setSource(SearchSourceBuilder.searchSource().fetchSource("*","bir"))
            //过滤出对应的结果
//            .setPostFilter(QueryBuilders.termQuery("content","组件"))
            .setPostFilter(QueryBuilders.termQuery("name","小明"))
            .setQuery(QueryBuilders.termQuery("content","框架"))
            .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false)
                .preTags("<span style='color:red' >").postTags("</span>"))
            .get();

        System.out.println("符合条件的总条数："+searchResponse.getHits().getTotalHits());

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println("原始字段"+sourceAsString);
            System.out.println("高亮字段" + hit.getHighlightFields());
        }
    }


}
