package pia.common;

import pia.common.Encryption;
import java.sql.*;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pia.database.Korisnici;
import pia.common.NewHibernateUtil;
import pia.database.Korisnici;
import pia.database.Slagalica;

public class KorisniciDAO {

    public static boolean PostojiKorisnickoIme(String korisnickoIme) throws SQLException {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.korisnickoIme = :korisnickoIme";
            Query q = session.createQuery(query);
            q.setParameter("korisnickoIme", korisnickoIme);
            List r = q.list();
            if (!r.isEmpty()) {
                ret = true;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (!ret) {
                session.close();
            }
        }

        return ret;
    }

    public static boolean PostojiEmail(String email) throws SQLException {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.email = :email";
            Query q = session.createQuery(query);
            q.setParameter("email", email);
            List r = q.list();
            if (!r.isEmpty()) {
                ret = true;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (!ret) {
                session.close();
            }
        }

        return ret;
    }

    public static void RegistrujKorisnika(Korisnici k) throws SQLException {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(k);
        session.getTransaction().commit();
    }
    
    public static Korisnici PrijaviSe(String korisnickoIme, String lozinka) throws SQLException {
        Korisnici k = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.korisnickoIme = :korisnickoIme AND k.lozinka = :lozinka";
            Query q = session.createQuery(query);
            q.setParameter("korisnickoIme", korisnickoIme);
            q.setParameter("lozinka", Encryption.HashSHA1(lozinka));
            List r = q.list();
            if (r.size() > 0) {
                k = (Korisnici) r.get(0);
            }

            tx.commit();
        } catch (HibernateException ex) {
            System.out.println(ex.getLocalizedMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return k;
    }

    public static boolean PromeniLozinku(String korisnickoIme, String staraLozinka, String lozinka) throws SQLException {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.korisnickoIme = :korisnickoIme AND k.lozinka = :lozinka";
            Query q = session.createQuery(query);
            q.setParameter("korisnickoIme", korisnickoIme);
            q.setParameter("lozinka", Encryption.HashSHA1(staraLozinka));
            List r = q.list();
            if (!r.isEmpty()) {
                Korisnici k = (Korisnici) r.get(0);
                k.setLozinka(Encryption.HashSHA1(lozinka));
                session.save(k);
                        
                tx.commit();
            }
            else
            {
                tx.commit();
                return false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
}