package com.explore.canada.bean;

import com.explore.canada.configuration.Config;
import com.explore.canada.service.SearchService;

import java.util.ArrayList;
import java.util.List;

public class SearchInfo {
    String searchID;
    String place;
    String location;
    String name;
    String category;
    String url;

    public SearchInfo() {
        super();
    }

    public SearchInfo(String searchID, String place, String location, String name, String category, String url) {
        super();
        this.searchID = searchID;
        this.place = place;
        this.location = location;
        this.name = name;
        this.category = category;
        this.url = url;
    }

    public String getSearchID() {
        return searchID;
    }

    public String getPlace() {
        return place;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }
    public void setSearchID(String searchID) {
        this.searchID = searchID;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public SearchInfo[] loadSearchByCategory(String category)
    {
        SearchInfo[] searchInfos = null;
        SearchService searchService = Config.getInstance().getSearchService();
        try {
            searchInfos = searchService.loadPlacesByCategory(category);
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        System.out.println(searchInfos.length);
        return searchInfos;
    }

    public SearchInfo[] loadAllPlaces()
    {
        SearchInfo[] searchInfos = null;
        SearchService searchService = Config.getInstance().getSearchService();
        try {
            searchInfos = searchService.loadAllPlaces();
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        return searchInfos;
    }

}
