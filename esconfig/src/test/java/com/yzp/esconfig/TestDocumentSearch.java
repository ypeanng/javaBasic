package com.yzp.esconfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDocumentSearch {

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
    public void testQuery(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        SearchResponse searchResponse = transportClient.prepareSearch("ems")
            .setTypes("emp")
            .setQuery(matchAllQueryBuilder)
            .get();

        System.out.println("查询符合条件总数："+ searchResponse.getHits().getTotalHits());
        System.out.println("查询符合条件文档的最大得分："+searchResponse.getHits().getMaxScore());
        //获取每个文档详细信息
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println("====>"+hit.getSourceAsString());
        }

    }

    @Test
    public void testQuerySizeSort(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name","紫");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gte(0).lte(25);
        // 通配符 ?  * 0到多个
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("content","框?");
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("content","Spring");
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery().addIds("12","-TFsTHMBqA9jl-BNQ6RL");
        //fuzzy 模糊查询  0-2 不允许模糊  3-5 可以出现一个模糊  >5 最多出现2个模糊
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name","紫");
        //bool
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().
            must(QueryBuilders.termQuery("content","spring"))
            .filter(QueryBuilders.termQuery("content","框架"));
        testResult(matchAllQueryBuilder);
    }

    private void testResult(QueryBuilder matchAllQueryBuilder) {
        SearchResponse searchResponse = transportClient.prepareSearch("ems")
            .setTypes("emp")
            .setQuery(matchAllQueryBuilder)
            .setFrom(0)
            .setSize(20)
            // 手动排序后，系统自动的最大得分就没有意义了，将不会赋值
            .addSort("age", SortOrder.ASC)
            //执行结果中返回哪些字段
            .setSource(SearchSourceBuilder.searchSource().fetchSource("*","age"))
            .get();

        System.out.println("查询符合条件总数："+ searchResponse.getHits().getTotalHits());
        System.out.println("查询符合条件文档的最大得分："+searchResponse.getHits().getMaxScore());
        //获取每个文档详细信息
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println("====>"+hit.getSourceAsString());
        }
    }


}
