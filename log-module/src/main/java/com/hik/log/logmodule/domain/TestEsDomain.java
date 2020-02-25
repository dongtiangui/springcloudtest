//package com.hik.log.logmodule.domain;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//@Document(indexName = "test",type = "article")
//public class TestEsDomain {
//
//    @Id
//    @Field(store = true, type = FieldType.Integer)
//    private Integer id;
//
//    @Field(store = true,analyzer = "ik",searchAnalyzer = "ik",type = FieldType.Auto)
//    private String title;
//
//    @Field(store = true,analyzer = "ik",searchAnalyzer = "ik",type = FieldType.Auto)
//    private String context;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContext() {
//        return context;
//    }
//
//    public void setContext(String context) {
//        this.context = context;
//    }
//
//    @Override
//    public String toString() {
//        return "TestEsDomain{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", context='" + context + '\'' +
//                '}';
//    }
//}
