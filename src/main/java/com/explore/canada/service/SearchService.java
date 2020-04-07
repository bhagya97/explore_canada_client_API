package com.explore.canada.service;

import com.explore.canada.bean.SearchInfo;
import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.configuration.Config;
import java.util.HashMap;
import java.util.Map;

public class SearchService {
    private static String SEARCH_KEYWORD = "keyword";

    public SearchInfo[] loadPlacesByCategory(String keyword){
        String url = ServiceEndPoint.GET_LOCATION_CATEGORY_URL;
        RestServiceClient serviceClient = Config.getInstance().getRestServiceClient();
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put(SEARCH_KEYWORD,keyword);
        serviceClient.makeParametrizedGetRequest(url,parameters);
        return serviceClient.getSearchInfos();
    }

    public SearchInfo[] loadAllPlaces(){
        String url = ServiceEndPoint.GET_LOCATION_URL;
        RestServiceClient serviceClient = Config.getInstance().getRestServiceClient();
        serviceClient.makeSearchGetRequest(url);
        return serviceClient.getSearchInfos();
    }
}
