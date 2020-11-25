package com.deepblue.searchPicture.service;

import com.alibaba.fastjson.JSON;
import com.deepblue.searchPicture.entity.PictureResource;
import com.deepblue.searchPicture.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Service
public class BoolQueryPictureService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private void  mergeTermQueryConditions(Object values,BoolQueryBuilder boolQueryBuilder,String name){
        if(values != null)
        {
            QueryBuilder queryBuilder = QueryBuilders.termQuery(name, values);
            boolQueryBuilder.must(queryBuilder);
        }
    }

    private void  mergeMatchQueryConditions(Object values,BoolQueryBuilder boolQueryBuilder,String name){
        if(values != null)
        {
            QueryBuilder queryBuilder = QueryBuilders.matchQuery(name, values);
            boolQueryBuilder.must(queryBuilder);
        }
    }

    private void  mergeFuzzyQueryConditions(Object values,BoolQueryBuilder boolQueryBuilder,String name){
        if(values != null)
        {
            QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery(name, values).fuzziness(Fuzziness.AUTO);
            boolQueryBuilder.must(queryBuilder);
        }
    }

    private void  mergeRangeQueryConditions(String values,BoolQueryBuilder boolQueryBuilder,String name,String sign){
        QueryBuilder queryBuilder = null;

        if(values != null)
        {
            Date date = Tools.stringToDate(values,"yyyy-MM-dd");
            log.info(date.toString());
            if(sign.equals(">"))
            {
                queryBuilder = QueryBuilders.rangeQuery(name).gte(values);
            }
            if(sign.equals("<"))
            {
                queryBuilder = QueryBuilders.rangeQuery(name).lte(values);
            }
            if(queryBuilder != null)
            {
                boolQueryBuilder.must(queryBuilder);
            }
        }
    }

    public ArrayList<PictureResource> boolQueryPictrue(PictureResource in_pr) {

        ArrayList<PictureResource> list_picture = new ArrayList<PictureResource>();
        try {
            // 创建 Bool 查询构建器
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            // 构建查询条件
//            boolQueryBuilder.must(QueryBuilders.termsQuery("address.keyword", "北京市昌平区", "北京市大兴区", "北京市房山区"))
//                    .filter().add(QueryBuilders.rangeQuery("birthDate").format("yyyy").gte("1990").lte("1995"));
            if(in_pr.isEmpty()){
                //boolQueryBuilder.must();
                return list_picture;
            }
            else
            {
                mergeTermQueryConditions(in_pr.getMark_class(),boolQueryBuilder,"mark_class.keyword");
                mergeMatchQueryConditions(in_pr.getMark_type(),boolQueryBuilder,"mark_type");
                mergeTermQueryConditions(in_pr.getProject(),boolQueryBuilder,"project.keyword");

                mergeMatchQueryConditions(in_pr.getMark_stuff_class(),boolQueryBuilder,"mark_stuff_class");
                mergeMatchQueryConditions(in_pr.getMark_stuff_desc(),boolQueryBuilder,"mark_stuff_desc");
                mergeMatchQueryConditions(in_pr.getSence(),boolQueryBuilder,"sence");

                mergeRangeQueryConditions(in_pr.getStart_markStartTime(),boolQueryBuilder,"markStartTime",">");
                mergeRangeQueryConditions(in_pr.getEnd_markStartTime(),boolQueryBuilder,"markStartTime","<");
                //QueryBuilder queryBuilder = QueryBuilders.matchQuery("mark_stuff_class", in_pr.getMark_stuff_class());
//                boolQueryBuilder.must(queryBuilder);
            }

            // 构建查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //分页 pageNum页码,pageSize每页数量
            searchSourceBuilder.from(in_pr.getPageNum()-1).size(in_pr.getPageSize());
            searchSourceBuilder.query(boolQueryBuilder);
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest("picture-resource");
            searchRequest.source(searchSourceBuilder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            // 根据状态和数据条数验证是否返回了数据
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().totalHits > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    PictureResource pictureResource = JSON.parseObject(hit.getSourceAsString(), PictureResource.class);
                    pictureResource.setId(hit.getId());
                    // 输出查询信息
                    //log.info(hit.getId() + ","+ hit.getSourceAsString());
                    list_picture.add(pictureResource);
                }
            }
        }catch (IOException e){
            log.error("",e);
        }

        log.info(JSON.toJSONString(list_picture));
        return list_picture;
    }

    public long getQueryCount(PictureResource in_pr){
        long result = 0L;
        try {
            // 创建 Bool 查询构建器
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(in_pr.isEmpty()){
                //boolQueryBuilder.must();
                return result;
            }
            else
            {
                mergeTermQueryConditions(in_pr.getMark_class(),boolQueryBuilder,"mark_class.keyword");
                mergeTermQueryConditions(in_pr.getMark_type(),boolQueryBuilder,"mark_type");
                mergeTermQueryConditions(in_pr.getProject(),boolQueryBuilder,"project.keyword");

                mergeMatchQueryConditions(in_pr.getMark_stuff_class(),boolQueryBuilder,"mark_stuff_class");
                mergeMatchQueryConditions(in_pr.getMark_stuff_desc(),boolQueryBuilder,"mark_stuff_desc");
                mergeMatchQueryConditions(in_pr.getSence(),boolQueryBuilder,"sence");

                mergeRangeQueryConditions(in_pr.getStart_markStartTime(),boolQueryBuilder,"markStartTime",">");
                mergeRangeQueryConditions(in_pr.getEnd_markStartTime(),boolQueryBuilder,"markStartTime","<");
            }

            // 构建查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //分页 pageNum页码,pageSize每页数量
            searchSourceBuilder.from(in_pr.getPageNum()-1).size(in_pr.getPageSize());
            searchSourceBuilder.query(boolQueryBuilder);
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest("picture-resource");
            searchRequest.source(searchSourceBuilder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            // 根据状态和数据条数验证是否返回了数据
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().totalHits > 0) {
                result = searchResponse.getHits().totalHits;
        }
    } catch (IOException e) {
            log.error("",e);
        }
        return result;
    }

}
