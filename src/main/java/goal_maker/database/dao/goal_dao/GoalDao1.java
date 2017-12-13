package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalDao1 extends JpaRepository<Goal, Long>{
}
