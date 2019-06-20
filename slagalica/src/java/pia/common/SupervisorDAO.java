package pia.common;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pia.database.Asocijacija;
import pia.database.AsocijacijaGrupa;
import pia.database.Korisnici;
import pia.database.Slagalica;
import pia.database.Spojnice;
import pia.database.SpojniceRec;

public class SupervisorDAO {

    public static boolean NovaRec(String novaSlagalicaRec) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        // provera najpre da li rec postoji
        try {
            tx = session.beginTransaction();
            String query = "FROM Slagalica s WHERE LOWER(s.rec) = :rec";
            Query q = session.createQuery(query);
            q.setParameter("rec", novaSlagalicaRec.toLowerCase().trim());
            List r = q.list();
            if (r.isEmpty()) {
                ret = true;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (!ret) {
                session.close();
                return false;
            }
        }

        // rec ne postoji -> dodavanje
        try {
            Slagalica slagalica = new Slagalica();
            slagalica.setRec(novaSlagalicaRec);

            tx = session.beginTransaction();
            session.save(slagalica);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return true;
    }
    
    public static boolean DodajSpojnicu(SpojniceRec spojnice[])
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            SQLQuery q = session.createSQLQuery("INSERT INTO Spojnice VALUES()");
            q.executeUpdate();
            SQLQuery q1 = session.createSQLQuery("SELECT last_insert_id()");
            int ss = Integer.parseInt(q1.list().get(0).toString());
            
            for (int i = 0; i < spojnice.length; i++)
            {
                spojnice[i].setIdSpojnica(ss);
                session.save(spojnice[i]);
            }
            
            tx.commit();
        }
         catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (tx != null)
                tx.rollback();
            
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }

    public static boolean DodajAsocijaciju(AsocijacijaGrupa g1, AsocijacijaGrupa g2, AsocijacijaGrupa g3, AsocijacijaGrupa g4, String konacnoResenje) 
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            SQLQuery q = session.createSQLQuery("INSERT INTO asocijacija_grupa (Rec1,Rec2,Rec3,Rec4,ResenjeGrupe) VALUES(:rec1,:rec2,:rec3,:rec4,:resenje)");
            q.setString("rec1", g1.getRec1());
            q.setString("rec2", g1.getRec2());
            q.setString("rec3", g1.getRec3());
            q.setString("rec4", g1.getRec4());
            q.setString("resenje", g1.getResenjeGrupe());
            q.executeUpdate();
            SQLQuery query1 = session.createSQLQuery("SELECT last_insert_id()");
            int grupa1 = Integer.parseInt(query1.list().get(0).toString());
            
            q = session.createSQLQuery("INSERT INTO asocijacija_grupa (Rec1,Rec2,Rec3,Rec4,ResenjeGrupe) VALUES(:rec1,:rec2,:rec3,:rec4,:resenje)");
            q.setString("rec1", g2.getRec1());
            q.setString("rec2", g2.getRec2());
            q.setString("rec3", g2.getRec3());
            q.setString("rec4", g2.getRec4());
            q.setString("resenje", g2.getResenjeGrupe());
            q.executeUpdate();
            SQLQuery query2 = session.createSQLQuery("SELECT last_insert_id()");
            int grupa2 = Integer.parseInt(query2.list().get(0).toString());
            
            q = session.createSQLQuery("INSERT INTO asocijacija_grupa (Rec1,Rec2,Rec3,Rec4,ResenjeGrupe) VALUES(:rec1,:rec2,:rec3,:rec4,:resenje)");
            q.setString("rec1", g3.getRec1());
            q.setString("rec2", g3.getRec2());
            q.setString("rec3", g3.getRec3());
            q.setString("rec4", g3.getRec4());
            q.setString("resenje", g3.getResenjeGrupe());
            q.executeUpdate();
            SQLQuery query3 = session.createSQLQuery("SELECT last_insert_id()");
            int grupa3 = Integer.parseInt(query3.list().get(0).toString());
            
            q = session.createSQLQuery("INSERT INTO asocijacija_grupa (Rec1,Rec2,Rec3,Rec4,ResenjeGrupe) VALUES(:rec1,:rec2,:rec3,:rec4,:resenje)");
            q.setString("rec1", g4.getRec1());
            q.setString("rec2", g4.getRec2());
            q.setString("rec3", g4.getRec3());
            q.setString("rec4", g4.getRec4());
            q.setString("resenje", g4.getResenjeGrupe());
            q.executeUpdate();
            SQLQuery query4 = session.createSQLQuery("SELECT last_insert_id()");
            int grupa4 = Integer.parseInt(query4.list().get(0).toString());
            
            Asocijacija novaAsocijacija = new Asocijacija();
            g1.setIdGrupa(grupa1);
            g2.setIdGrupa(grupa2);
            g3.setIdGrupa(grupa3);
            g4.setIdGrupa(grupa4);            
            novaAsocijacija.setIdGrupa1(g1.getIdGrupa());
            novaAsocijacija.setIdGrupa2(g2.getIdGrupa());
            novaAsocijacija.setIdGrupa3(g3.getIdGrupa());
            novaAsocijacija.setIdGrupa4(g4.getIdGrupa());
            novaAsocijacija.setResenjeAsocijacije(konacnoResenje);
            
            session.save(novaAsocijacija);
            
            tx.commit();
        }
         catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (tx != null)
                tx.rollback();
            
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
}