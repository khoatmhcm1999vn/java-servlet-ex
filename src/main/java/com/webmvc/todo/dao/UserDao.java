package com.webmvc.todo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.Todo;
import com.webmvc.todo.model.User;
import com.webmvc.todo.utils.HibernateUtils;

public class UserDao {
	private Session session;
	

	public void saveUser(User user) {
		Transaction transaction = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			session.save(user);
			
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
	
	public User login(String email, String password) {
		Transaction transaction = null;
		User user = null;
		String query = "from User U where U.email = :user_email";
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			user = (User)session.createQuery(query).setParameter("user_email", email).uniqueResult();
			
			if (user != null && user.getPassword().equals(password)) {
				return user;
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
		
		return null;
	}
	
	
	// Same existUser
	public User EmailExist(String email) {
		Transaction transaction = null;
		User user = null;
		String query = "from User U where U.email = :user_email";
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			System.out.println("Session");
			transaction = session.beginTransaction();
			user = (User)session.createQuery(query).setParameter("user_email", email).uniqueResult();
			System.out.println("Query");
			if (user != null) {
				System.out.println("User co ton tai");
				return user;
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
		
		return null;
	}
	
	public User existUser(String email) {
		Transaction transaction = null;
		User user = null;
		String query = "from User U where U.email = :user_email";
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			user = (User)session.createQuery(query).setParameter("user_email", email).uniqueResult();
			
			if (user != null) {
				return user;
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
		
		return null;
	}
	
	public void resetPassword(String email, String password, String salt) {
		Transaction transaction = null;
		String query = "update User set password = :password, salt = :salt where email = :email";
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.createQuery(query).setParameter("password", password).setParameter("salt", salt).setParameter("email", email).executeUpdate();
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
	
	public void updateUser(User user) {
		Transaction transaction = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			
			transaction = session.beginTransaction();
			
			session.update(user);
			
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
	
	public void deleteUser(int id) {
		Transaction transaction = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			
			transaction = session.beginTransaction();
			
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				System.out.println("User is deleted");
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
	
	public User getUser(int id) {
		Transaction transaction = null;
		User user = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			
			transaction = session.beginTransaction();
			
			user = session.get(User.class, id);
			
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Todo> getTodosByUser(int id) {
		Transaction transaction = null;
		List<Todo> listOfTodo = null;
		String query = "select U.todos from User U where U.id = :user_id";
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			listOfTodo = session.createQuery(query).setParameter("user_id", id).getResultList();
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
	public List<Tag> getTagsByUser(int id) {
		Transaction transaction = null;
		List<Tag> listOfTag = null;
		String query = "select U.tags from User U where U.id = :user_id";
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			listOfTag = session.createQuery(query).setParameter("user_id", id).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return listOfTag;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		Transaction transaction = null;
		List <User> listOfUser = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			
			transaction = session.beginTransaction();
			
			listOfUser = session.createQuery("from User").getResultList();
			
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return listOfUser;
		
	}
}
