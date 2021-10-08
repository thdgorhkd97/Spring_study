package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// @Table(name = "user") 이름이 같기 때문에 필요 X (클래스이름 = DB테이블 이름)
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString(exclude = {"orderGroup"})
@EntityListeners(AuditingEntityListener.class)
@Builder // User u = User.builder().account(account).password(password).status(status).email(email).build();
@Accessors(chain = true) //User u = new User().setAccount("").setEmail("");
public class User {

    @Id // 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status; // REGISTERED or UNREGISTERED

    private String email;

    // @Column(name = "phone_number") 없어도 자동 매칭
    private String phoneNumber; // 이름 같으면 JPA에서 자동 매칭

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

    // User : OrderGroup = 1 : N
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup> orderGroupList;


}
