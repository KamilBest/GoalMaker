package goal_maker.web.services.category_service;

import goal_maker.database.tables.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
    public Category getCategoryById(long id);

}
