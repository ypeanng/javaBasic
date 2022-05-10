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
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDocumentHighlight {

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
        List<Emp> empList = new ArrayList<>();

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("*");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>")
            .postTags("</span>");
        SearchResponse searchResponse = transportClient.prepareSearch("ems")
            .setTypes("emp")
            .setQuery(QueryBuilders.multiMatchQuery("我喜欢编程","name","content"))
            .highlighter(highlightBuilder).get();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Emp emp = new Emp();
            Map<String,Object> sourceAsMap= hit.getSourceAsMap();
            emp.setId(hit.getId());
            emp.setName(sourceAsMap.get("name").toString());
            emp.setContent(sourceAsMap.get("content").toString());
            emp.setAge(Integer.valueOf(sourceAsMap.get("age").toString()));
            emp.setAddress(sourceAsMap.toString());
            empList.add(emp);

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(highlightFields.containsKey("name")){
                String name = highlightFields.get("name").fragments()[0].toString();
                emp.setName(name);
            }
            if(highlightFields.containsKey("content")){
                String content = highlightFields.get("content").fragments()[0].toString();
                emp.setContent(content);
            }
        }
        for (Emp emp : empList) {
            System.out.println(emp.toString());
        }
    }

}
