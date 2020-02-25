package com.hik.log.logmodule.dao;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BaseElasticService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void createIndex(String idxName){
        try {
            CreateIndexRequest request = new CreateIndexRequest(idxName);
            buildSetting(request);
//            可以理解为设置索引字段
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    builder.startObject("message");
                    {
                        builder.field("type", "text");
                        builder.field("title","text");
                        builder.field("content","text");
                        builder.field("create","date");
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
//            设置结束
            request.mapping(builder);
            // 索引别名
            request.alias(new Alias("doc_alias").filter(QueryBuilders.termQuery("user", "kimchy")));
//            异步创建并监听
            restHighLevelClient.indices().createAsync(request, RequestOptions.DEFAULT,
              new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(CreateIndexResponse createIndexResponse) {
                  System.out.println(createIndexResponse.index());
                }

                @Override
                public void onFailure(Exception e) {}
              });

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public boolean indexExist(String idxName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(idxName);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 设置索引的参数
     * @param request
     */
    public void buildSetting(CreateIndexRequest request){
        request.settings(Settings.builder().put("index.number_of_shards",3)
                .put("index.number_of_replicas",2));
    }

}
