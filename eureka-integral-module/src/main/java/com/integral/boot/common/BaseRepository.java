//package com.integral.boot.common;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
//
//
////    定义公共查询方法
//
////    客户细分查询
//    /**
//     * 查询所有客户a
//     */
//    @Query(value = "select name,author,price from Book b where b.name like %:name%")
//    List<T> findAllByCustomer();
//
//
//}
