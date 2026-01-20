package com.maven.restapi.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.maven.restapi.model.Livre;

public class LivreDao {
    public String getMessage() { return "hibernate jpa ok"; }

    public List<Livre> getLivres() {
        Session session = HibernateSessionFactory.currentSession();
        List<Livre> livres = session.createQuery("FROM Livre", Livre.class).list();
        session.close();
        return livres;
    }

    public Livre getLivreById(int id) {
        Session session = HibernateSessionFactory.currentSession();
        Livre livre = session.get(Livre.class, id);
        session.close();
        return livre;
    }

    public Livre getLivreByTitre(String titre) {
        Session session = HibernateSessionFactory.currentSession();
        Query<Livre> query = session.createQuery("FROM Livre WHERE titre = :titre", Livre.class);
        query.setParameter("titre", titre);
        Livre result = query.uniqueResult();
        session.close();
        return result;
    }

    public Livre saveLivre(Livre livre) {
        Session session = HibernateSessionFactory.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(livre);
        tx.commit();
        session.close();
        return livre;
    }

    public int updateLivre(int id, Livre livre) {
        Session session = HibernateSessionFactory.currentSession();
        Transaction tx = session.beginTransaction();
        Query<?> query = session.createQuery("UPDATE Livre SET titre = :titre, auteur = :auteur, prix = :prix WHERE id = :id");
        query.setParameter("titre", livre.getTitre());
        query.setParameter("auteur", livre.getAuteur());
        query.setParameter("prix", livre.getPrix());
        query.setParameter("id", id);
        int rows = query.executeUpdate();
        tx.commit();
        session.close();
        return rows;
    }

    public int deleteLivre(int id) {
        Session session = HibernateSessionFactory.currentSession();
        Transaction tx = session.beginTransaction();
        Query<?> query = session.createQuery("DELETE FROM Livre WHERE id = :id");
        query.setParameter("id", id);
        int rows = query.executeUpdate();
        tx.commit();
        session.close();
        return rows;
    }
}
