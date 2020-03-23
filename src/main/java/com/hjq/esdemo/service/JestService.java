package com.hjq.esdemo.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.cluster.UpdateSettings;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * es jest服务类
 * @author ibm
 */
@Service
public class JestService {

	@Autowired
	JestClient jestClient;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 创建索引
	 * @param indexName
	 * @return
	 * @throws Exception
	 */
	public boolean createIndex(String indexName) throws Exception {
	    JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
		log.info("mapping msg:"+jr.getErrorMessage());
	    return jr.isSucceeded();
	}
	
	/**
	 * Put映射
	 * @param indexName
	 * @param typeName
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public boolean createIndexMapping(String indexName, String typeName, String source) throws Exception {
	    PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
	    JestResult jr = jestClient.execute(putMapping);
	    log.info("mapping msg:"+jr.getErrorMessage());
	    return jr.isSucceeded();
	}
	
	/**
	 * Get映射
	 * @param indexName
	 * @param typeName
	 * @return
	 * @throws Exception
	 */
	public String getIndexMapping(String indexName, String typeName) throws Exception {
	    GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
	    JestResult jr = jestClient.execute(getMapping);
	    return jr.getJsonString();
	}
	
	/**
	 * 索引文档
	 * @param indexName
	 * @param typeName
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	public <T> boolean indexList( String indexName, String typeName, List<T> objs) throws Exception {
	    Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
	    for (T obj : objs) {
	      Index index = new Index.Builder(obj).build();
	      bulk.refresh(true);
	      bulk.addAction(index);
	    }
	    BulkResult br = jestClient.execute(bulk.build());
		log.debug("indexList-bulkResult:"+br.getErrorMessage());
		String jsonString = br.getJsonString();
		log.debug(jsonString);
		return br.isSucceeded();
	}

	/**
	 * 索引文档
	 * @param indexName
	 * @param typeName
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean index( String indexName, String typeName, Object obj) throws Exception {
		Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
		bulk.refresh(true);
		Index index = new Index.Builder(obj).build();
		bulk.addAction(index);
		BulkResult br = jestClient.execute(bulk.build());
		return br.isSucceeded();
	}
	/**
	 * 搜索文档
	 * @param indexName
	 * @param typeName
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(String indexName, String typeName, String query) throws Exception {
	    Search search = new Search.Builder(query)
	        .addIndex(indexName)
	        .addType(typeName)
	        .build();
	    return jestClient.execute(search);
	}
	
	/**
	 * Count文档
	 * @param indexName
	 * @param typeName
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Double count( String indexName, String typeName, String query) throws Exception {
	    Count count = new Count.Builder()
	        .addIndex(indexName)
	        .addType(typeName)
	        .query(query)
	        .build();
	    CountResult results = jestClient.execute(count);
	    return results.getCount();
	}
	
	/**
	 * Get文档
	 * @param indexName
	 * @param typeName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public <T> T getById(String indexName, String typeName, String id, Class<T> clazz) throws Exception {
	    Get get = new Get.Builder(indexName, id).type(typeName).build();
		JestResult result = jestClient.execute(get);
		if (result.isSucceeded()) {
		  return result.getSourceAsObject(clazz);
		}else{
			return null;
		}
	}
	
	/**
	 * Delete索引
	 * @param indexName
	 * @return
	 * @throws Exception
	 */
	public boolean delete( String indexName) throws Exception {
	    JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
	    return jr.isSucceeded();
	}
	
	/**
	 * Delete文档
	 * @param indexName
	 * @param typeName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete( String indexName, String typeName, String id) throws Exception {
	    DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
	    return dr.isSucceeded();
	}


	/**
	 * 判断索引目录是否存在
	 * @throws Exception
	 */
	public  boolean indicesExists(String indexName) throws Exception {
		IndicesExists indicesExists = new IndicesExists.Builder(indexName).build();
		JestResult result = jestClient.execute(indicesExists);
		if (result.isSucceeded()) {
			return  true;
		} else {
			return false;
		}

	}

	/**
	 * 设置最大查询数量
	 * @throws IOException
	 */
	public void updateSetting() throws IOException {
		String body = "{ \"index\" : { \"max_result_window\" : 100000000} }";
		UpdateSettings.Builder builder = new UpdateSettings.Builder( body );
		jestClient.execute(builder.build());
	}
}