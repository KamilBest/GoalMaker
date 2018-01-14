package goal_maker.database.dao.category_dao;

import goal_maker.database.tables.Category;
import goal_maker.database.tables.GmUser;
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
}
