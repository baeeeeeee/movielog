package com.movielog.domain;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageMaker {

    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev,next;

    private int displayPageNum = 10;

    private Criteria criteria;

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;

        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
       
    }
    
    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .queryParam("category", criteria.getCategory())
                .queryParam("bgno", criteria.getBgno()).build();
        return uriComponents.toUriString();
    }
    
    public String makeSearch(int page) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pagePageNum", criteria.getPerPageNum())
                .queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
                .queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword()))
                .queryParam("category", criteria.getCategory())
                .queryParam("bgno", criteria.getBgno()).build();

        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        if (keyword == null || keyword.trim().length() == 0) {
            return "";
        }

        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
    
}