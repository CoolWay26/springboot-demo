package com.coolway.controller.elasticsearch;

import com.coolway.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/elasticSearch")
@Api(tags = "ElasticSearch测试")
public class ElasticsearchController {

    @PutMapping("/addIndex")
    @ApiOperation(value = "索引测试-增加")
    public void addIndex(String indexName) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);

        esClient.close();
    }

    @GetMapping("/queryIndex")
    @ApiOperation(value = "索引测试-查询")
    public void queryIndex(String indexName) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        GetIndexRequest request = new GetIndexRequest(indexName);
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println(response.getAliases());
        System.out.println(response.getMappings());
        System.out.println(response.getSettings());

        esClient.close();
    }

    @DeleteMapping("/deleteIndex")
    @ApiOperation(value = "索引测试-删除")
    public void deleteIndex(String indexName) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        DeleteIndexRequest request = new DeleteIndexRequest();
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());

        esClient.close();
    }

    @PostMapping("/addDoc")
    @ApiOperation(value = "文档测试-增加")
    public void addDoc(String indexName, String docId) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        IndexRequest request = new IndexRequest();


        Student student = Student.builder()
                .name("zhangsan")
                .age(25)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student);

        request.index(indexName).id(docId);
        request.source(studentJson, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        esClient.close();
    }

    @GetMapping("/updateDoc")
    @ApiOperation(value = "文档测试-修改")
    public void updateDoc(String indexName, String docId) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        UpdateRequest request = new UpdateRequest();
        request.index(indexName).id(docId);
        request.doc(XContentType.JSON, "name", "lisi");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        esClient.close();
    }

    @DeleteMapping("/queryDoc")
    @ApiOperation(value = "文档测试-修改")
    public void queryDoc() throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        esClient.close();
    }

    @DeleteMapping("/deleteDoc")
    @ApiOperation(value = "文档测试-删除")
    public void deleteDoc() throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        esClient.close();
    }
}
