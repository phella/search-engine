package com.SE.SearchEngineAPI;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import Models.VisitedUrl;
import Queries.QueryProcessor;

@Service
public class VisitedUrlsService {

	private MongoOperations mongoOperations;
	
	@Autowired
	public VisitedUrlsService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

    public void saveVisitedUrl(String visitedUrl, String _query) {
    	 	QueryProcessor.setQuery(_query);
    	 	List<String> QueryStrings = QueryProcessor.process();
    	 	Query query = new Query();
    	 	for (String term : QueryStrings) 
    	 	{
    	 		query.addCriteria(new Criteria().and("queryTerm").is(term).and("visitedUrl").is(visitedUrl));
    	 		Update incFreqUpdate = new Update().inc("frequency", 1).set("queryTerm", term).set("visitedUrl", visitedUrl);
    	 		mongoOperations.findAndModify(query, incFreqUpdate, options().returnNew(true).upsert(true), VisitedUrl.class);
    	 		query = new Query();
    	 	}
    }
    
    public List<VisitedUrl> getVisitedUrls(ArrayList<String> queryWords){
		Query query = new Query();
		Criteria orCrit = new Criteria();
		List<Criteria> orExpr = new ArrayList<>();
		for (String q : queryWords) {
			Criteria temp = new Criteria();
			temp.and("queryTerm").is(q);
			orExpr.add(temp);
		}

		if(orExpr.isEmpty())    return new ArrayList<>();

		query.addCriteria(orCrit.orOperator(orExpr.toArray(new Criteria[orExpr.size()])));

		return this.mongoOperations.find(query, VisitedUrl.class);
	}
} 