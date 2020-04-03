package com.hjq.esdemo.controller;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.hjq.esdemo.service.JestService;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class TestController {

    JestService jestService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/queryListByEs")
    public HashMap queryListByEs(String index){
        HashMap result = Maps.newHashMap();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (false) {
            queryBuilder.must(QueryBuilders.termQuery("id", 121212));
        }
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        searchSourceBuilder.sort("id", SortOrder.DESC);
        String queryStr = searchSourceBuilder.query(queryBuilder).toString();
        try {
            SearchResult searchResult = jestService.search(
                    index,
                    index, queryStr);
            List<JsonObject> list= searchResult.getSourceAsObjectList(JsonObject.class, true);
            result.put("totalCount",searchResult.getTotal());
            result.put("list",list);
        } catch (Exception e) {
            log.error("查询报错",e);
        }

        return result;
    }
}
