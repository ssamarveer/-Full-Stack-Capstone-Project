package com.spendwise.model;
import jakarta.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnore; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="savings_goals")
public class SavingsGoal {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @JsonIgnore @ManyToOne(optional=false) @JoinColumn(name="user_id") private AppUser user;
 @Column(nullable=false) private String name;
 @Column(nullable=false, precision=14,scale=2) private BigDecimal targetAmount;
 @Column(nullable=false, precision=14,scale=2) private BigDecimal savedAmount=BigDecimal.ZERO;
 private LocalDate targetDate;
 public Long getId(){return id;} public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;} public String getName(){return name;} public void setName(String v){name=v;}
 public BigDecimal getTargetAmount(){return targetAmount;} public void setTargetAmount(BigDecimal v){targetAmount=v;} public BigDecimal getSavedAmount(){return savedAmount;} public void setSavedAmount(BigDecimal v){savedAmount=v;} public LocalDate getTargetDate(){return targetDate;} public void setTargetDate(LocalDate v){targetDate=v;}
}
