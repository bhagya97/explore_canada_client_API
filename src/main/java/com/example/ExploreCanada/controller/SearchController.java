package com.example.ExploreCanada.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ExploreCanada.beans.SearchInfo;
import com.example.ExploreCanada.config.Configuration;
import com.example.ExploreCanada.dao.ISearchDAO;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE })
public class SearchController {

	    @RequestMapping(value="/locations/places", method= {RequestMethod.GET, RequestMethod.POST})
	    @ResponseBody
	    public ModelAndView getCategory(Model model ,@RequestParam("search") String category) {
	    	System.out.println(category);
	        SearchInfo searchInfo = new SearchInfo();
	        ISearchDAO searchDAO = Configuration.instance().getSearchDAO();
	        List<SearchInfo> places= searchInfo.loadSearchByCategory(searchDAO, category, (SearchInfo) searchInfo);
	        model.addAttribute("locations",places);
	        ModelAndView mav = new ModelAndView("/locations");
	        return  mav;
	    }

	    @RequestMapping(value="/locations", method= {RequestMethod.GET, RequestMethod.POST})
	    @ResponseBody
	    public ModelAndView getAllPlaces(Model model) {
	    	
	        SearchInfo searchInfo = new SearchInfo();
	        ISearchDAO searchDAO = Configuration.instance().getSearchDAO();
	        List<SearchInfo> locations =searchInfo.loadAllPlaces(searchDAO);	        
	        model.addAttribute("locations",locations);
	        ModelAndView mav = new ModelAndView("/locations");
	        return  mav;
	    }
	}

