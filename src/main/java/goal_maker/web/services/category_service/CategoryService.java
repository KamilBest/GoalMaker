package goal_maker.web.services.category_service;

import goal_maker.database.tables.Category;

import java.util.List;

public interface CategoryService {
     List<Category> findAll();
     Category getCategoryById(long id);
    void addCategory(Category category);
    void editCategory(Long categoryId,String newCategoryName);

}
