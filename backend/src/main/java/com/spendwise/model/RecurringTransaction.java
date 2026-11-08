package com.spendwise.model;
import jakarta.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnore; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="recurring_transactions")
public class RecurringTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @JsonIgnore @ManyToOne(optional=false) @JoinColumn(name="user_id") private AppUser user;
 @Column(nullable=false) private String title; @Column(nullable=false) private BigDecimal amount;
 @Column(nullable=false) private String type; @Column(nullable=false) private String category;
 @Column(nullable=false) private String frequency; private LocalDate nextDate; private boolean active=true;
 public Long getId(){return id;} public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;} public String getType(){return type;} public void setType(String v){type=v;} public String getCategory(){return category;} public void setCategory(String v){category=v;}
 public String getFrequency(){return frequency;} public void setFrequency(String v){frequency=v;} public LocalDate getNextDate(){return nextDate;} public void setNextDate(LocalDate v){nextDate=v;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
