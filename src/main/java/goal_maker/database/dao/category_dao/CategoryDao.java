package goal_maker.database.dao.category_dao;

import goal_maker.database.tables.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();
    public Category getCategoryById(long id);
}
