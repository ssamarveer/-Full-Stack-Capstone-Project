package com.spendwise.controller;
import com.spendwise.model.SavingsGoal;import com.spendwise.repo.SavingsGoalRepository;import com.spendwise.service.CurrentUser;import org.springframework.web.bind.annotation.*;import java.math.BigDecimal;import java.time.LocalDate;import java.util.*;
@RestController @RequestMapping("/api/goals") public class GoalController{
 private final SavingsGoalRepository repo;private final CurrentUser current;public GoalController(SavingsGoalRepository r,CurrentUser c){repo=r;current=c;}
 public record Request(String name,BigDecimal targetAmount,LocalDate targetDate){}
 public record Contribution(BigDecimal amount){}
 private SavingsGoal owned(Long id){return repo.findById(id).filter(x->x.getUser().getId().equals(current.get().getId())).orElseThrow(()->new NoSuchElementException("Goal not found"));}
 @GetMapping public List<SavingsGoal> all(){return repo.findAllByUserOrderByIdDesc(current.get());}
 @PostMapping public SavingsGoal create(@RequestBody Request r){return save(new SavingsGoal(),r);}
 @PutMapping("/{id}") public SavingsGoal update(@PathVariable Long id,@RequestBody Request r){return save(owned(id),r);}
 private SavingsGoal save(SavingsGoal g,Request r){if(r.name()==null||r.name().isBlank()||r.targetAmount()==null||r.targetAmount().compareTo(BigDecimal.ZERO)<=0)throw new IllegalArgumentException("Enter goal name and positive target amount");g.setUser(current.get());g.setName(r.name());g.setTargetAmount(r.targetAmount());g.setTargetDate(r.targetDate());if(g.getSavedAmount()==null)g.setSavedAmount(BigDecimal.ZERO);return repo.save(g);}
 @PostMapping("/{id}/contributions") public SavingsGoal contribute(@PathVariable Long id,@RequestBody Contribution c){if(c.amount()==null||c.amount().compareTo(BigDecimal.ZERO)<=0)throw new IllegalArgumentException("Contribution must be positive");SavingsGoal g=owned(id);g.setSavedAmount(g.getSavedAmount().add(c.amount()));return repo.save(g);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.delete(owned(id));}
}
