package goal_maker.database.dao.category_dao;

import goal_maker.database.tables.Category;
import goal_maker.database.tables.GmUser;
import goal_maker.web.services.category_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class CategoryDaoImpl implements CategoryDao{

    @PersistenceContext
    EntityManager entityManager;



    @Override
    public List<Category> findAll() {
        String sqlSelect="Select * From goal_maker.category";
        List<Category> categoryList = entityManager.createNativeQuery(sqlSelect, Category.class).getResultList();
        return categoryList;
    }

    @Override
    public Category getCategoryById(long id) {
        String sqlSelect="Select * From goal_maker.category WHERE id_category="+id;
        Query query=entityManager.createNativeQuery(sqlSelect,Category.class);
        Category category=(Category)query.getSingleResult();
        return category;
    }

    @Override
    public void addCategory(Category category) {
        String sqlInsert ="    INSERT INTO goal_maker.category(name) VALUES (?);";
        Query query = entityManager.createNativeQuery(sqlInsert, Category.class);
        query.setParameter(1, category.getName());
        query.executeUpdate();
    }

    @Override
    public void editCategory(Long categoryId,String newCategoryName) {
        String sqlUpdateCategory = "UPDATE goal_maker.category SET  name=? WHERE id_category=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdateCategory, Category.class);
        updateQuery.setParameter(1, newCategoryName);
        updateQuery.setParameter(2,categoryId);
        updateQuery.executeUpdate();

    }
}

