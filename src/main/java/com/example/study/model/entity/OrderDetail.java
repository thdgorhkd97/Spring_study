package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // order_detail 에 자동 연결
@ToString(exclude = {"orderGroup","item"})
@EntityListeners(AuditingEntityListener.class)
@Builder // User u = User.builder().account(account).password(password).status(status).email(email).build();
@Accessors(chain = true) //User u = new User().setAccount("").setEmail("");
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // OrderDetail : OrderGroup = N : 1
    @ManyToOne
    private OrderGroup orderGroup;

    // OrderDetail : Item = N : 1
    @ManyToOne
    private Item item;




}
