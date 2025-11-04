package com.example.product_composite.Dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recommendation {
    private Long recId; private Long productId; private String author; private int rate;
    public Long getRecId(){return recId;} public void setRecId(Long recId){this.recId=recId;}
    public Long getProductId(){return productId;} public void setProductId(Long productId){this.productId=productId;}
    public String getAuthor(){return author;} public void setAuthor(String author){this.author=author;}
    public int getRate(){return rate;} public void setRate(int rate){this.rate=rate;}
}
