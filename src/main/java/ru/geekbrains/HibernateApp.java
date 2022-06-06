package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HibernateApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        try {
            String sql = Files.lines(Paths.get("query.sql")).collect(Collectors.joining(" "));
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProductDAO productDAO = new ProductDAO(factory);

        // Поиск необходимого продукта
        System.out.println(productDAO.findById(3));

        // Создание нового продукта
        // productDAO.saveOrUpdate(new Product("Ряженка", 14));

        // Удаление продукта по ID
        // productDAO.deleteById(7);

        // Получение всего списка товаров
        System.out.println(productDAO.findByAll());

        factory.close();
    }
}
