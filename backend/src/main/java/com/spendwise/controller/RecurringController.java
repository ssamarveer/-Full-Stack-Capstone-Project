package com.spendwise.controller;
import com.spendwise.model.RecurringTransaction;import com.spendwise.repo.RecurringRepository;import com.spendwise.service.CurrentUser;import org.springframework.web.bind.annotation.*;import java.math.BigDecimal;import java.time.LocalDate;import java.util.*;
@RestController @RequestMapping("/api/recurring") public class RecurringController{
 private final RecurringRepository repo;private final CurrentUser current;public RecurringController(RecurringRepository r,CurrentUser c){repo=r;current=c;}
 public record Request(String title,BigDecimal amount,String type,String category,String frequency,LocalDate nextDate,Boolean active){}
 private RecurringTransaction owned(Long id){return repo.findById(id).filter(x->x.getUser().getId().equals(current.get().getId())).orElseThrow(()->new NoSuchElementException("Recurring item not found"));}
 @GetMapping public List<RecurringTransaction> all(){return repo.findAllByUserOrderByIdDesc(current.get());}
 @PostMapping public RecurringTransaction create(@RequestBody Request r){return save(new RecurringTransaction(),r);}
 @PutMapping("/{id}") public RecurringTransaction update(@PathVariable Long id,@RequestBody Request r){return save(owned(id),r);}
 private RecurringTransaction save(RecurringTransaction x,Request r){if(r.title()==null||r.amount()==null||r.amount().compareTo(BigDecimal.ZERO)<=0||r.type()==null||r.category()==null||r.frequency()==null)throw new IllegalArgumentException("Complete all recurring transaction fields");x.setUser(current.get());x.setTitle(r.title());x.setAmount(r.amount());x.setType(r.type().toUpperCase());x.setCategory(r.category());x.setFrequency(r.frequency());x.setNextDate(r.nextDate());x.setActive(r.active()==null||r.active());return repo.save(x);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.delete(owned(id));}
}
