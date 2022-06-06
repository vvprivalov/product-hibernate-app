package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final SessionFactory sessionFactory;

    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Сохранение продукта
    public void saveOrUpdate(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    // Поиск продукта по ID
    public Product findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    // Удаление продукта по ID
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
        System.out.println("Продукт с id " + id + " успешно удален");
    }

    // Получение всего списка продуктов из БД
    public List<Product> findByAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> productList;
        session.beginTransaction();
        productList = session.createQuery("FROM Product ", Product.class).getResultList();
        session.getTransaction().commit();
        return productList;
    }


}
