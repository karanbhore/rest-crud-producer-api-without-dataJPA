package com.prowings.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prowings.entity.Product;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class ProductRepositoryImpl implements ProductRepository{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Product> getAllProductsFromDb() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Product> productList = null;
		try {

			tx = session.beginTransaction();
			Query<Product> query = session.createQuery("from Product");
			productList = query.list();
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace(System.err);
		} 
		finally 
		{
			session.close();
			return productList;
		}

		
	}

	@Override
	public boolean createProduct(Product product) {
		log.info("inside repository - createProduct()");
		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(product);
			txn.commit();
			session.close();
			log.info("Product saved to DB successfully : {}", product);
			return true;
		}
		catch (Exception e) {
			log.error("Error occurred while storing the Product to DB : {} ", e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product, int id) {
		log.info("inside repository - updateProduct()");
		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Product prod=session.get(Product.class, id);
			if(prod != null) {
			prod.setId(product.getId());
			prod.setName(product.getName());
			prod.setPrice(product.getPrice());
			session.save(prod);
			}
			else
			{
			session.save(product);
			}
			txn.commit();
			session.close();
			log.info("Product updated to DB successfully : {}", product);
			return true;
		}
		catch (Exception e) {
			log.error("Error occurred while updating the Product to DB : {} ", e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteProduct(int id) {
		log.info("inside repository - deleteProduct()");
		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Product prod=session.get(Product.class, id);
			if(prod != null) {
			session.delete(prod);
			log.info("Product deleted from DB successfully of id : {}", id);
			txn.commit();
			session.close();
			
			return true;
			}
			else
			{
			log.info("product is not available to db of id : {} \", id");
			log.info("try another id");
			return false;
			}
			
		}
		catch (Exception e) {
			log.error("Error occurred while deleting the Product from DB : {} ", e.getMessage());
			return false;
		}
	}

}
