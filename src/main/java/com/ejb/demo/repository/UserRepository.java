package com.ejb.demo.repository;

import java.util.List;

import javax.persistence.Query;

import com.ejb.demo.hibernate.HibernateUtils;
import com.ejb.demo.model.Role;
import com.ejb.demo.model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {
	
	public static List<User> getAll() {
		List<User> result = null;
		
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("FROM User");
			result = q.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public static User getUserByUsername(String username) {
		User result = null;
		
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("FROM User WHERE username=:username");
			q.setParameter("username", username);
			
			result = (User) q.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public static User getUserById(int id) {
		User result = null;
		
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("FROM User WHERE id=:id");
			q.setParameter("id", id);
			
			result = (User) q.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public static User insertUser(User user) {
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.save(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	public static User updateUser(User user) {
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.update(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	public static boolean deleteUser(User user) {
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("DELETE FROM User WHERE id=:id");
			q.setParameter("id", user.getId());
			q.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return false;
	}
}
