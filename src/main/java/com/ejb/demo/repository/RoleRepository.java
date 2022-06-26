package com.ejb.demo.repository;

import java.util.List;

import javax.persistence.Query;

import com.ejb.demo.hibernate.HibernateUtils;
import com.ejb.demo.model.Role;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleRepository {

	public static List<Role> getAll() {
		List<Role> result = null;
		
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("FROM Role");
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
	
	public static Role getRoleById(int id) {
		Role result = null;
		
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("FROM Role WHERE id=:id");
			q.setParameter("id", id);
			result = (Role) q.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public static Role insertRole(Role role) {
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.save(role);
			transaction.commit();
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	public static Role updateRole(Role role) {
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.update(role);
			transaction.commit();
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	public static boolean deleteRole(Role role) {
		Session session = HibernateUtils.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query q = session.createQuery("DELETE FROM Role WHERE id=:id");
			q.setParameter("id", role.getId());
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
