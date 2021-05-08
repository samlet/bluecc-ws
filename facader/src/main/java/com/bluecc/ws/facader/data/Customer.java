package com.bluecc.ws.facader.data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

// 我们没有使用@Table注释，Hibernate会将其映射到名为“Customer”的表。
// 可以使用其他表名@Table(name = "User")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    // 该@Id注释标记ID字段作为唯一ID字段
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 该firstName和lastName是未映射，它将它们映射到相同的列名作为属性。
    // 我们可以选择使用@Column注释来自定义列名称。
    private String firstName;
    private String lastName;
    private String email;

    // Spring JPA提供@CreatedBy，@LastModifiedBy以捕获创建或更改实体的用户
    // @CreatedDate并@LastModifiedDate捕获发生这种情况的时间点。
    // 这是我们更改后的JPA实体类，其中包含审核更改。
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdTime;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedTime;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}