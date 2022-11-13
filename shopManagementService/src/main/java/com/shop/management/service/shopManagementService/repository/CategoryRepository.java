package com.shop.management.service.shopManagementService.repository;


import com.shop.management.service.shopManagementService.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //--------------------UPDATE QUERIES (BY ANY FIELD) ------------------------//

    @Modifying
    @Query("UPDATE Category c SET c.description = :description " +
            "WHERE c.id = :id")
    void updateCategoryDescriptionById(@Param("id") int id,
                                                 @Param("description") String description);

    @Modifying
    @Query("UPDATE Category c SET c.name = :name " +
            "WHERE c.id = :id")
    void updateCategoryNameById(@Param("id") int id,
                            @Param("name") String name);



    //--------------------SELECT QUERIES (BY ANY FIELD) ------------------------//

    @Modifying
    @Query("SELECT c " +
            "FROM Category c " +
            "WHERE c.name = :name")
    List<Category> getCategoriesByName(@Param("name") String name);


    @Modifying
    @Query("SELECT c " +
            "FROM Category c " +
            "WHERE c.description = :description")
    List<Category> getCategoriesByDescription(@Param("description") String description);



}
