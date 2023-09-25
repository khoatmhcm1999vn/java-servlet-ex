package com.webmvc.todo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.User;
import com.webmvc.todo.utils.HibernateUtils;

public class TagDao {
	private Session session;
	
	public void saveTag(Tag tag) {
		Transaction transaction = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(tag);
			
			session.flush();
			
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
	
	public void updateTag(Tag tag) {
		Transaction transaction = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(tag);
			
			session.flush();
			
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
	
	public void deleteTag(int id) {
		Transaction transaction = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Tag tag = session.get(Tag.class, id);
			
			if(tag != null) {
				session.delete(tag);
				System.out.println("Tag is deleted");
			}
			
			session.flush();
			
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
	
	public Tag getTag(int id) {
		Transaction transaction = null;
		Tag tag = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			tag = session.get(Tag.class, id);
			
			session.flush();
			
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return tag;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> getTagByUser(User user) {
		Transaction transaction = null;
		List<Tag> listOfTag = null;
		
		String query = "from Tag T where T.user = :user";
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			listOfTag = session.createQuery(query).setParameter("user", user).getResultList();
			
			session.flush();
			
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
	public List<Tag> getAllTag() {
		Transaction transaction = null;
		List<Tag> listOfTag = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			listOfTag = session.createQuery("from Tag").getResultList();
			
			session.flush();
			
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
}
