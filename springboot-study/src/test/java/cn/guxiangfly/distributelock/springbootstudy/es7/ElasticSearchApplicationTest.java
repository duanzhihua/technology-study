package cn.guxiangfly.distributelock.springbootstudy.es7;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

/**
 * @Author guxiang02
 * @Date 2020/6/29
 **/
@SpringBootTest
public class ElasticSearchApplicationTest {

    public static String KEY = "comment";

    public static String KEY_INDEX_DB = "digoduct" + KEY;

    public static String PRE_FILE = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/springboot-study/src/test/java/cn/guxiangfly/distributelock/springbootstudy/es7/data/";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testCreateIndex() throws Exception {

        if (true){
            GetIndexRequest getIndexRequest = new GetIndexRequest(KEY_INDEX_DB);
            boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            if (exists){
                DeleteIndexRequest deleteRequest = new DeleteIndexRequest(KEY_INDEX_DB);
                restHighLevelClient.indices().delete(deleteRequest, RequestOptions.DEFAULT);
            }
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(KEY_INDEX_DB);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println("创建索引库:"+createIndexResponse.isAcknowledged());
        }




        String string = FileUtils.readFileToString(new File(PRE_FILE + KEY + ".json"), "UTF-8");
        List<Object> objects = JSONArray.parseArray(string, Object.class);
        for (Object object : objects) {
            System.out.println(JSON.toJSONString(object));
        }
//        GetIndexRequest getIndexRequest = new GetIndexRequest("digoductcomment");
//        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        for (int i = 0; i < objects.size(); i++) {
            bulkRequest.add(new IndexRequest(KEY_INDEX_DB).id("" + (i + 1))
                    .source(JSON.toJSONString(objects.get(i)), XContentType.JSON));
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());

    }


}
