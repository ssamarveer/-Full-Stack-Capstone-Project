package com.spendwise.model;
import jakarta.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnore; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="transactions", indexes={@Index(name="idx_tx_user_date", columnList="user_id,transactionDate")})
public class Transaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @JsonIgnore @ManyToOne(optional=false) @JoinColumn(name="user_id") private AppUser user;
 @Column(nullable=false, precision=14, scale=2) private BigDecimal amount;
 @Column(nullable=false) private String type;
 @Column(nullable=false) private String category;
 @Column(nullable=false) private LocalDate transactionDate;
 private String description; @Column(length=1000) private String notes;
 public Long getId(){return id;} public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;} public String getType(){return type;} public void setType(String v){type=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;} public LocalDate getTransactionDate(){return transactionDate;} public void setTransactionDate(LocalDate v){transactionDate=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}
