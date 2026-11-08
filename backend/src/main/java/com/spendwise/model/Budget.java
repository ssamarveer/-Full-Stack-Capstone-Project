package com.spendwise.model;
import jakarta.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnore; import java.math.BigDecimal;
@Entity @Table(name="budgets")
public class Budget {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @JsonIgnore @ManyToOne(optional=false) @JoinColumn(name="user_id") private AppUser user;
 @Column(nullable=false) private String month; // YYYY-MM
 @Column(nullable=false) private String category; // ALL for overall budget
 @Column(nullable=false, precision=14,scale=2) private BigDecimal limitAmount;
 public Long getId(){return id;} public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;} public String getMonth(){return month;} public void setMonth(String v){month=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;} public BigDecimal getLimitAmount(){return limitAmount;} public void setLimitAmount(BigDecimal v){limitAmount=v;}
}
