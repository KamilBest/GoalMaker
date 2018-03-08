package goal_maker.database.dao.category_dao;

import goal_maker.database.tables.Category;

import java.util.List;

public interface CategoryDao {
     List<Category> findAll();
     Category getCategoryById(long id);
     void addCategory(Category category);
     void editCategory(Long categoryId,String newCategoryName);
}
