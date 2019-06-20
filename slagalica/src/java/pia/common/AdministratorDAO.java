package pia.common;

import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pia.database.Asocijacija;
import pia.database.AsocijacijaGrupa;
import pia.database.IgraDana;
import pia.database.Korisnici;
import pia.database.Spojnice;
import pia.database.SpojniceRec;

public class AdministratorDAO
{

    public static List<Korisnici> DohvatiSpisakZahteva()
    {
        List<Korisnici> result = new LinkedList<>();

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.odobren = 0";
            Query q = session.createQuery(query);
            List r = q.list();
            result = r;

            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }

        return result;
    }

    public static void OdobriZahtev(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.idKorisnik = :id";
            Query q = session.createQuery(query);
            q.setParameter("id", id);
            List r = q.list();
            if (!r.isEmpty())
            {
                Korisnici k = (Korisnici) r.get(0);
                k.setOdobren((byte) 1);
                session.save(k);

                tx.commit();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }
    }

    public static void ObrisiZahtev(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM Korisnici k WHERE k.idKorisnik = :id";
            Query q = session.createQuery(query);
            q.setParameter("id", id);
            List r = q.list();
            if (!r.isEmpty())
            {
                Korisnici k = (Korisnici) r.get(0);
                session.delete(k);

                tx.commit();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }
    }

    public static List<Spojnice> SpisakSpojnica()
    {
        List<Spojnice> result = new LinkedList<>();

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM Spojnice k ORDER BY k.idSpojnica";
            Query q = session.createQuery(query);
            List r = q.list();
            result = r;

            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }

        return result;
    }

    public static List<Asocijacija> SpisakAsocijacija()
    {
        List<Asocijacija> result = new LinkedList<>();

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM Asocijacija a ORDER BY a.idAsocijacija";
            Query q = session.createQuery(query);
            List r = q.list();
            result = r;

            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }

        return result;
    }

    public static List<SpojniceRec> UcitajSpojnicu(int id)
    {
        List<SpojniceRec> result = new LinkedList<>();

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "SELECT * FROM spojnice_rec s WHERE s.IdSpojnica = :id";
            SQLQuery q = session.createSQLQuery(query).addEntity(SpojniceRec.class);
            q.setInteger("id", id);
            List r = q.list();
            result = r;

            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }

        return result;
    }

    public static Asocijacija UcitajAsocijaciju(int id)
    {
        Asocijacija result = null;

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM Asocijacija a WHERE a.idAsocijacija = :id";
            Query q = session.createQuery(query);
            q.setInteger("id", id);
            List r = q.list();
            if (!r.isEmpty())
            {
                result = (Asocijacija) r.get(0);
            }

            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }

        return result;
    }

    public static AsocijacijaGrupa UcitajGrupuAsocijacije(int id)
    {
        AsocijacijaGrupa result = null;

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            String query = "FROM AsocijacijaGrupa a WHERE a.idGrupa = :id";
            Query q = session.createQuery(query);
            q.setInteger("id", id);
            List r = q.list();
            if (!r.isEmpty())
            {
                result = (AsocijacijaGrupa) r.get(0);
            }

            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            session.close();
        }

        return result;
    }

    public static boolean dodajIgruDana(IgraDana igraDana)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try
        {
            tx = session.beginTransaction();
            String query = "select IdPartija from igra_dana \n"
                    + "inner join partija on (DATE(igra_dana.datum) = DATE(partija.datum)) \n"
                    + "where partija.samostalno = 1 and DATE(partija.datum) = DATE(:param1)";

            SQLQuery q = session.createSQLQuery(query);
            q.setParameter("param1", igraDana.getDatum());
            if (q.list().isEmpty())
            {
                String q2 = "select * from igra_dana where DATE(datum) = DATE(:param2)";
                SQLQuery t = session.createSQLQuery(q2).addEntity(IgraDana.class);
                t.setParameter("param2", igraDana.getDatum());
                List oo = t.list();
                if (oo.size() > 0)
                    session.delete(oo.get(0));
                
                session.save(igraDana);
                ret = true;
            }
            
            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            tx.rollback();
        }
        finally
        {
            session.close();
        }

        return ret;
    }
}
