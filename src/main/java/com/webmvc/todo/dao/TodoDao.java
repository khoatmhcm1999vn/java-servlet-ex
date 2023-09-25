package com.webmvc.todo.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webmvc.todo.model.Todo;
import com.webmvc.todo.model.User;
import com.webmvc.todo.utils.HibernateUtils;

public class TodoDao {
	
	private Session session;
	
	public void saveTodo(Todo todo) {
		Transaction  transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(todo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateTodo(Todo todo) {
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(todo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteTodo(int id) {
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Todo todo = session.get(Todo.class, id);
			if (todo != null) {
				session.delete(todo);
				System.out.println("Todo is deleted");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public Todo getTodo(int id) {
		Transaction transaction = null;
		Todo todo = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			todo = session.get(Todo.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return todo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Todo> getTodoByUser(User user) {
		Transaction transaction = null;
		List<Todo> listOfTodo = null;
		
		String query = "from Todo T where T.user = :user";
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			listOfTodo = session.createQuery(query).setParameter("user", user).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listOfTodo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Todo> getAllTodo() {
		Transaction transaction = null;
		List<Todo> listOfTodo = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			listOfTodo = session.createQuery("from Todo").getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listOfTodo;
	}
	
	


    public List<Object[]> statisticTodoGroupByTag(User user, Date date){
        List<Object[]> resultList = null;
        

        try {

        	session = HibernateUtils.getSessionFactory().openSession();
			
			
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Todo> root = criteriaQuery.from(Todo.class);
            criteriaQuery.multiselect(root.get("tag"),builder.count(root.get("tag")));
            criteriaQuery.groupBy(root.get("tag"));

            System.out.println(date.toString());
            System.out.println(user.getFullname());

            Predicate eqUser = builder.equal(root.get("user"), user);
            Predicate eqDate = builder.equal(root.get("date"), date);

            criteriaQuery.where(builder.and(eqUser, eqDate));
            Query<Object[]> query = session.createQuery(criteriaQuery);
            System.out.println(user.getFullname());

            resultList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return resultList;
    }

    public List<Object[]> statisticTodoDone(User user, Date date){
        List<Object[]> resultList = null;
        
        

        try {
        	session = HibernateUtils.getSessionFactory().openSession();
			
			
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Todo> root = criteriaQuery.from(Todo.class);
            criteriaQuery.multiselect(root.get("done"),builder.count(root.get("done")));
            criteriaQuery.groupBy(root.get("done"));

            Predicate eqUser = builder.equal(root.get("user"), user);
            Predicate eqDate = builder.equal(root.get("date"), date);

            criteriaQuery.where(builder.and(eqUser, eqDate));

            Query<Object[]> query = session.createQuery(criteriaQuery);

            resultList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return resultList;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> statisticWeekly(User user, Date dbegin, Date dend) {
        Transaction transaction = null;
        List<Object[]> listResult = null;

        String query = "select date, done, count(done) from Todo where user=:user and date >= :dbegin and date <= :dend group by date, done";
        try {
        	session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query<Object[]> queryObj = session.createQuery(query).setParameter("user", user)
                    .setParameter("dbegin", dbegin)
                    .setParameter("dend", dend);

            listResult = queryObj.getResultList();

            System.out.println("__________________________________________________");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(listResult);
        return listResult;
    }
    
}
