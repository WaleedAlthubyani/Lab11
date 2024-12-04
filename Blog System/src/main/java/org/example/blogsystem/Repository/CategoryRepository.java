package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryByCategoryId(Integer id);

    @Query("select c from Category c where c.name=?1")
    Category giveMeCategoryByName(String name);
}
