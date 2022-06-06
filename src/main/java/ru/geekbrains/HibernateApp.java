package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        try {
            List<String> stringList = Files.lines(Paths.get("query.sql")).collect(Collectors.toList());
            // String sql = Files.lines(Paths.get("query.sql")).collect(Collectors.joining(" "));
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            for (String s : stringList) {
                session.createNativeQuery(s).executeUpdate();
            }
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProductDAO productDAO = new ProductDAO(factory);

        // Поиск необходимого продукта
        System.out.println(productDAO.findById(1));

         // Создание нового продукта
         productDAO.saveOrUpdate(new Product("Ряженка", 14));

         // Удаление продукта по ID
        // productDAO.deleteById(7);

        // Получение всего списка товаров
        System.out.println(productDAO.findByAll());

        factory.close();
    }
}
