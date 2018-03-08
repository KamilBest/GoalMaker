package goal_maker.web.services.category_service;

import goal_maker.database.dao.category_dao.CategoryDao;
import goal_maker.database.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public void addCategory(Category category) { categoryDao.addCategory(category);
    }

    @Override
    public void editCategory(Long categoryId, String newCategoryName) {categoryDao.editCategory(categoryId,newCategoryName);
    }
}
