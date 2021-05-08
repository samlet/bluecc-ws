package com.bluecc.ws.facader.repository;
import com.bluecc.ws.facader.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA为我们完成了大部分工作，我们只需要定义接口即可。
 * `@Repository`注解可帮助Spring在组件扫描期间引导JPA功能。
 * 它JpaRepository提供了多种OOTB方法来帮助我们入门。
 *
 * save(S entity)
 * findById(ID id)
 * findOne()
 * findAll()
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> { }
