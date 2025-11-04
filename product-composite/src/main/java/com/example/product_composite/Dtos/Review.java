package com.example.product_composite.Dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
    private Long reviewId; private Long productId; private String author; private String subject; private String content;
    public Long getReviewId(){return reviewId;} public void setReviewId(Long reviewId){this.reviewId=reviewId;}
    public Long getProductId(){return productId;} public void setProductId(Long productId){this.productId=productId;}
    public String getAuthor(){return author;} public void setAuthor(String author){this.author=author;}
    public String getSubject(){return subject;} public void setSubject(String subject){this.subject=subject;}
    public String getContent(){return content;} public void setContent(String content){this.content=content;}
}
