package com.example.study.model.entity;

import com.example.study.model.enumclass.ItemStatus;
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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder // User u = User.builder().account(account).password(password).status(status).email(email).build();
@Accessors(chain = true) //User u = new User().setAccount("").setEmail("");
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status; // 등록 / 해지 / 대기

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;



    // Item : OrderDetail = 1 : N
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    // Item : Partner = N : 1
    @ManyToOne
    private Partner partner;



    // FetchType => LAZY(지연로딩), EAGER(즉시로딩)
    // LAZY = select * from item where id = ?

    /* EAGER -> 연관관계가 설정된 모든 테이블에 대해서 모두 로딩(하나만 해도 모두 select)
        OneToOne, OneToMany에서 많이 사용
    item id = order_detail.item_id
     user id = order_detail.user_id
     where item.id = ?

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
    */

}
