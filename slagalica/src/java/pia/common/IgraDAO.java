package pia.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pia.controllers.GameController.AsocijacijaInfo;
import pia.database.Asocijacija;
import pia.database.AsocijacijaGrupa;
import pia.database.IgraDana;
import pia.database.Partija;
import pia.database.Rezultat;
import pia.igre.SlagalicaIgra;

public class IgraDAO {

    public static int napraviPartiju(int plavi, boolean samostalno, java.sql.Date datum) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int ret = -1;

        try {
            tx = session.beginTransaction();
            String query = "FROM Partija k WHERE k.plaviTakmicar = :plavi AND k.crveniTakmicar IS NULL AND k.samostalno = 0";
            Query q = session.createQuery(query);
            q.setParameter("plavi", plavi);
            List r = q.list();
            for (int i = 0; i < r.size(); i++) {
                session.delete((Partija) r.get(i));
            }

            Partija p = new Partija();
            p.setPlaviTakmicar(plavi);
            p.setSamostalno((byte) (samostalno ? 1 : 0));
            p.setDatum(datum);
            session.save(p);

            ret = p.getIdPartija();

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return ret;
    }

    public static boolean cekajDrugogIgraca(int igra, int plavi) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try {
            tx = session.beginTransaction();
            String query = "FROM Partija k WHERE k.plaviTakmicar = :plavi AND k.idPartija = :igra";
            Query q = session.createQuery(query);
            q.setParameter("igra", igra);
            q.setParameter("plavi", plavi);
            List r = q.list();
            if (!r.isEmpty()) {
                Partija p = (Partija) r.get(0);
                if (p.getCrveniTakmicar() != null) {
                    ret = true;
                } else {
                    ret = false;
                }
            } else {
                ret = false;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return ret;
    }

    public static List<Partija> spisakPartijaNaCekanju(int plavi) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Partija> ret = new LinkedList<>();

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM Partija k WHERE k.plaviTakmicar != :plavi AND k.datum >= NOW() - INTERVAL 3 MINUTE ORDER BY k.Datum DESC LIMIT 1";
            SQLQuery q = session.createSQLQuery(query).addEntity(Partija.class);
            q.setParameter("plavi", plavi);
            List r = q.list();
            for (int i = 0; i < r.size(); i++) {
                ret.add((Partija) r.get(i));
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return ret;
    }

    public static void dodajDrugogIgraca(Integer idPartija, int crveni) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try {
            tx = session.beginTransaction();
            String query = "FROM Partija k WHERE k.idPartija = :id";
            Query q = session.createQuery(query);
            q.setParameter("id", idPartija);
            List r = q.list();
            if (!r.isEmpty()) {
                Partija k = (Partija) r.get(0);
                k.setCrveniTakmicar(crveni);
                session.save(k);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
    }

    public static void upisiPoeneSamostalno(Session sesija, int idPartija, int igra, int poenaPlavi) {
        Rezultat rezultat = new Rezultat();
        rezultat.setIdPartija(idPartija);
        rezultat.setIgra(igra);
        rezultat.setPoenaPlavi(poenaPlavi);

        sesija.save(rezultat);
    }

    public static void upisiPoeneMultiplayer(int idPartija, int igra, int poenaPlavi, int poenaCrveni) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        upisiPoeneMultiplayer(session, idPartija, igra, poenaPlavi, poenaCrveni);
        session.getTransaction().commit();
    }

    private static void upisiPoeneMultiplayer(Session sesija, int idPartija, int igra, int poenaPlavi, int poenaCrveni) {
        Rezultat rezultat = new Rezultat();
        rezultat.setIdPartija(idPartija);
        rezultat.setIgra(igra);
        rezultat.setPoenaPlavi(poenaPlavi);
        rezultat.setPoenaCrveni(poenaCrveni);

        sesija.save(rezultat);
    }

    public static boolean recPostoji(String unetaRecPlavi) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean rez = false;

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM Slagalica WHERE LOWER(Rec) = :rec1";
            SQLQuery q = session.createSQLQuery(query).addEntity(pia.database.Slagalica.class);
            q.setParameter("rec1", unetaRecPlavi.toLowerCase());
            List r = q.list();

            if (!r.isEmpty()) {
                rez = true;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rez;
    }

    public static void potvrdiMojBrojSamostalno(int idIgre, int brojPoena) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Rezultat rezultat = new Rezultat();
        rezultat.setIdPartija(idIgre);
        rezultat.setIgra(1);
        rezultat.setPoenaPlavi(brojPoena);

        session.save(rezultat);
        session.getTransaction().commit();
    }

    public static boolean igraDanaPostoji() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean rez = false;

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM igra_dana where DATE(Datum) = DATE(NOW())";
            SQLQuery q = session.createSQLQuery(query);
            List r = q.list();

            if (!r.isEmpty()) {
                rez = true;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rez;
    }

    public static int odigraoIgruDana(int plavi) {
        if (!igraDanaPostoji()) {
            return 1;
        }

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int rez = -1;

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM partija where PlaviTakmicar = :plavi and samostalno = 1 and DATE(datum) = DATE(now())";
            SQLQuery q = session.createSQLQuery(query);
            q.setParameter("plavi", plavi);
            List r = q.list();

            if (r.isEmpty()) {
                rez = 0;
            } else {
                rez = 2;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rez;
    }

    public static IgraDana dohvatiIgruDana() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        IgraDana rez = null;

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM igra_dana WHERE (datum) = DATE(now())";
            SQLQuery q = session.createSQLQuery(query).addEntity(IgraDana.class);
            List r = q.list();

            if (!r.isEmpty()) {
                rez = (IgraDana) r.get(0);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rez;
    }

    public static List<StatistikaProtivDrugih> dohvatiNedeljnuStatistiku() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<StatistikaProtivDrugih> rezultat = new LinkedList<>();

        try {
            tx = session.beginTransaction();
            String setQuery = "set @rn = 0";
            SQLQuery q1 = session.createSQLQuery(setQuery);
            q1.executeUpdate();

            String query = "SELECT (@rn \\:= @rn + 1) RB, t.* from (select CONCAT(Ime, ' ', Prezime) ImePrezime, SUM(SumaPoDanu) suma \n"
                    + "FROM pregledrezultatapodanu \n"
                    + "where week(datum) = week(now()) \n"
                    + "group by idkorisnik \n"
                    + "order by suma desc) t";
            SQLQuery q = session.createSQLQuery(query);
            List<Object[]> r = q.list();

            for (int i = 0; i < r.size(); i++) {
                StatistikaProtivDrugih novi = new StatistikaProtivDrugih();

                novi.redniBroj = ((BigInteger) r.get(i)[0]).intValue();
                novi.imePrezime = (String) r.get(i)[1];
                novi.crveni = ((BigDecimal) r.get(i)[2]).intValue();

                rezultat.add(novi);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rezultat;
    }

    public static List<StatistikaProtivDrugih> dohvatiMesecnuStatistiku() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<StatistikaProtivDrugih> rezultat = new LinkedList<>();

        try {
            tx = session.beginTransaction();
            String setQuery = "set @rn = 0";
            SQLQuery q1 = session.createSQLQuery(setQuery);
            q1.executeUpdate();

            String query = "SELECT (@rn \\:= @rn + 1) RB, t.* from (select CONCAT(Ime, ' ', Prezime) ImePrezime, SUM(SumaPoDanu) suma\n"
                    + "                    FROM pregledrezultatapodanu\n"
                    + "                    where month(datum) = month(now()) and year(datum) = year(now())\n"
                    + "                    group by idkorisnik\n"
                    + "                    order by suma desc) t";
            SQLQuery q = session.createSQLQuery(query);
            List<Object[]> r = q.list();

            for (int i = 0; i < r.size(); i++) {
                StatistikaProtivDrugih novi = new StatistikaProtivDrugih();

                novi.redniBroj = ((BigInteger) r.get(i)[0]).intValue();
                novi.imePrezime = (String) r.get(i)[1];
                novi.crveni = ((BigDecimal) r.get(i)[2]).intValue();

                rezultat.add(novi);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rezultat;
    }

    public static StatistikaProtivDrugih dohvatiTrenutnuStatistikuIgre(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        StatistikaProtivDrugih rezultat = null;

        try {
            tx = session.beginTransaction();
            String query = "SELECT SUM(PoenaPlavi), SUM(PoenaCrveni) FROM pia.rezultat where idpartija = :id group by idpartija";
            SQLQuery q = session.createSQLQuery(query);
            q.setParameter("id", id);
            List<Object[]> r = q.list();

            if (!r.isEmpty()) {
                rezultat = new StatistikaProtivDrugih();

                rezultat.plavi = ((BigDecimal) r.get(0)[0]).intValue();
                rezultat.crveni = ((BigDecimal) r.get(0)[1]).intValue();
            } else {
                rezultat = new StatistikaProtivDrugih();
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rezultat;
    }

    private static AI dohvatiCiljaniIdAsocijacije(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        AI r = new AI();

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM asocijacija WHERE IdAsocijacija = :id";
            SQLQuery q = session.createSQLQuery(query).addEntity(pia.database.Asocijacija.class);
            q.setParameter("id", id);
            List rr = q.list();

            if (!rr.isEmpty()) {
                r.idUBazi = ((Asocijacija) rr.get(0)).getIdAsocijacija();
                r.a = ((Asocijacija) rr.get(0)).getIdGrupa1();
                r.b = ((Asocijacija) rr.get(0)).getIdGrupa2();
                r.c = ((Asocijacija) rr.get(0)).getIdGrupa3();
                r.d = ((Asocijacija) rr.get(0)).getIdGrupa4();
                r.konacno = ((Asocijacija) rr.get(0)).getResenjeAsocijacije();
            } else {
                r = null;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return r;
    }

    public static class Tuple<T, U> {

        public T t;
        public U u;

        public Tuple() {
        }

        public Tuple(T t, U u) {
            this.t = t;
            this.u = u;
        }

        public void setT(T t) {
            this.t = t;
        }

        public void setU(U u) {
            this.u = u;
        }
    }

    public static Tuple<Integer, Integer> dohvatiRezultat(int idIgre) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        Tuple<Integer, Integer> rezultat = new Tuple<>();

        try {
            tx = session.beginTransaction();
            String query = "SELECT SUM(PoenaPlavi) PoenaPlavi, SUM(PoenaCrveni) PoenaCrveni FROM rezultat WHERE IdPartija = :partija GROUP BY IdPartija";
            SQLQuery q = session.createSQLQuery(query);
            q.setParameter("rec1", idIgre);
            List<Object[]> r = q.list();

            if (!r.isEmpty()) {
                rezultat.t = (int) r.get(0)[0];
                rezultat.u = (int) r.get(0)[1];
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rezultat;
    }

    private static int dohvatiSlucajniIdSlagalice() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        int r = -1;

        try {
            tx = session.beginTransaction();
            String query = "SELECT IdSpojnica FROM spojnice ORDER BY RAND() LIMIT 1";
            SQLQuery q = session.createSQLQuery(query);
            List rr = q.list();

            if (!rr.isEmpty()) {
                r = (int) rr.get(0);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return r;
    }

    public static Map<String, String> dohvatiSlucajnuSpojnicu() {
        return dohvatiSpojnicu(-1);
    }

    public static Map<String, String> dohvatiKonkretnuSpojnicu(int id) {
        return dohvatiSpojnicu(id);
    }

    private static Map<String, String> dohvatiSpojnicu(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx;

        Map<String, String> rezultat = new HashMap<>();

        try {
            tx = session.beginTransaction();
            String query = "SELECT LevaRec, DesnaRec from spojnice_rec inner join spojnice using (IdSpojnica) where IdSpojnica = :id";
            SQLQuery q = session.createSQLQuery(query);
            if (id == -1) {
                q.setParameter("id", dohvatiSlucajniIdSlagalice());
            } else {
                q.setParameter("id", id);
            }
            List<Object[]> r = q.list();

            for (Object[] red : r) {
                rezultat.put((String) red[0], (String) red[1]);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return rezultat;
    }

    private static class AI {

        int idUBazi;
        int a, b, c, d;
        String konacno;
    }

    private static AI dohvatiSlucajniIdAsocijacijeSlagalice() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        AI r = new AI();

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM asocijacija ORDER BY RAND() LIMIT 1";
            SQLQuery q = session.createSQLQuery(query).addEntity(pia.database.Asocijacija.class);
            List rr = q.list();

            if (!rr.isEmpty()) {
                r.idUBazi = ((Asocijacija) rr.get(0)).getIdAsocijacija();
                r.a = ((Asocijacija) rr.get(0)).getIdGrupa1();
                r.b = ((Asocijacija) rr.get(0)).getIdGrupa2();
                r.c = ((Asocijacija) rr.get(0)).getIdGrupa3();
                r.d = ((Asocijacija) rr.get(0)).getIdGrupa4();
                r.konacno = ((Asocijacija) rr.get(0)).getResenjeAsocijacije();
            } else {
                r = null;
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return r;
    }

    public static AsocijacijaInfo dohvatiAsocijaciju() {
        return dohvatiAsocijaciju(-1);
    }

    public static AsocijacijaInfo dohvatiAsocijaciju(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        AI ai;
        if (id == -1) {
            ai = dohvatiSlucajniIdAsocijacijeSlagalice();
        } else {
            ai = dohvatiCiljaniIdAsocijacije(id);
        }

        AsocijacijaInfo info = new AsocijacijaInfo();
        info.setIdUBazi(ai.idUBazi);

        try {
            tx = session.beginTransaction();
            String query = "SELECT * FROM asocijacija_grupa WHERE IdGrupa = :id";
            SQLQuery q1 = session.createSQLQuery(query).addEntity(pia.database.AsocijacijaGrupa.class);
            SQLQuery q2 = session.createSQLQuery(query).addEntity(pia.database.AsocijacijaGrupa.class);
            SQLQuery q3 = session.createSQLQuery(query).addEntity(pia.database.AsocijacijaGrupa.class);
            SQLQuery q4 = session.createSQLQuery(query).addEntity(pia.database.AsocijacijaGrupa.class);

            q1.setParameter("id", ai.a);
            q2.setParameter("id", ai.b);
            q3.setParameter("id", ai.c);
            q4.setParameter("id", ai.d);

            List r1 = q1.list();
            List r2 = q2.list();
            List r3 = q3.list();
            List r4 = q4.list();

            AsocijacijaGrupa a = ((AsocijacijaGrupa) r1.get(0));
            AsocijacijaGrupa b = ((AsocijacijaGrupa) r2.get(0));
            AsocijacijaGrupa c = ((AsocijacijaGrupa) r3.get(0));
            AsocijacijaGrupa d = ((AsocijacijaGrupa) r4.get(0));

            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    info.getBazaA()[i] = a.getRec1().toUpperCase();
                    info.getBazaB()[i] = b.getRec1().toUpperCase();
                    info.getBazaC()[i] = c.getRec1().toUpperCase();
                    info.getBazaD()[i] = d.getRec1().toUpperCase();
                } else if (i == 1) {
                    info.getBazaA()[i] = a.getRec2().toUpperCase();
                    info.getBazaB()[i] = b.getRec2().toUpperCase();
                    info.getBazaC()[i] = c.getRec2().toUpperCase();
                    info.getBazaD()[i] = d.getRec2().toUpperCase();
                } else if (i == 2) {
                    info.getBazaA()[i] = a.getRec3().toUpperCase();
                    info.getBazaB()[i] = b.getRec3().toUpperCase();
                    info.getBazaC()[i] = c.getRec3().toUpperCase();
                    info.getBazaD()[i] = d.getRec3().toUpperCase();
                } else if (i == 3) {
                    info.getBazaA()[i] = a.getRec4().toUpperCase();
                    info.getBazaB()[i] = b.getRec4().toUpperCase();
                    info.getBazaC()[i] = c.getRec4().toUpperCase();
                    info.getBazaD()[i] = d.getRec4().toUpperCase();

                    info.setResenjeA(split(a.getResenjeGrupe()));
                    info.setResenjeB(split(b.getResenjeGrupe()));
                    info.setResenjeC(split(c.getResenjeGrupe()));
                    info.setResenjeD(split(d.getResenjeGrupe()));
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        info.setResenjeKonacno(split(ai.konacno));

        return info;
    }

    private static Set<String> split(String s) {
        String[] sa = s.split(",");
        for (int i = 0; i < sa.length; i++) {
            sa[i] = sa[i].toUpperCase();
        }

        return new HashSet<>(Arrays.asList(sa));
    }

    public static List<Tuple<Integer, Tuple<Integer, Integer>>> dohvatiRezultatGrupisanoPoIgrama(int idIgre) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        List<Tuple<Integer, Tuple<Integer, Integer>>> vrati = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            String query = "SELECT Igra, SUM(PoenaPlavi) PoenaPlavi, SUM(PoenaCrveni) PoenaCrveni FROM rezultat WHERE IdPartija = :partija GROUP BY IdPartija, Igra";
            SQLQuery q = session.createSQLQuery(query);
            q.setParameter("partija", idIgre);
            List<Object[]> r = q.list();

            for (int i = 0; i < r.size(); i++) {
                Tuple<Integer, Tuple<Integer, Integer>> rezultat = new Tuple<>();

                BigDecimal tmp1 = (BigDecimal) r.get(i)[1];
                BigDecimal tmp2 = (BigDecimal) r.get(i)[2];

                rezultat.t = (int) r.get(i)[0];
                rezultat.u = new Tuple(tmp1.intValue(), tmp2.intValue());

                vrati.add(rezultat);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return vrati;
    }

    public static class StatistikaDana {

        int redniBroj;
        String imePrezime;
        int brojPoena;

        public int getRedniBroj() {
            return redniBroj;
        }

        public String getImePrezime() {
            return imePrezime;
        }

        public int getBrojPoena() {
            return brojPoena;
        }
    }

    public static List<StatistikaDana> dohvatiStatistikuDana(int idKorisnika) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        List<StatistikaDana> vrati = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            String setQuery = "set @rn = 0";
            SQLQuery q1 = session.createSQLQuery(setQuery);
            q1.executeUpdate();

            String query = "select (@rn \\:= @rn + 1) RedniBroj, t.* FROM (SELECT IdKorisnik, CONCAT(Ime, ' ', Prezime) ImePrezime, sum(r.PoenaPlavi) Suma \n"
                    + "                    FROM partija p \n"
                    + "                    inner join korisnici k on (p.plavitakmicar = k.idkorisnik)\n"
                    + "                    inner join rezultat r on (p.idpartija = r.idpartija)\n"
                    + "                    where p.samostalno = 1 and DATE(p.datum) = DATE(NOW())\n"
                    + "                    group by r.idpartija\n"
                    + "                    order by suma desc) t";

            SQLQuery q = session.createSQLQuery(query);
            List<Object[]> r = q.list();

            for (int i = 0; i < r.size(); i++) {
                if ((int) r.get(i)[1] == idKorisnika || vrati.size() <= 10) {
                    StatistikaDana rezultat = new StatistikaDana();

                    rezultat.redniBroj = ((BigInteger) r.get(i)[0]).intValue();
                    rezultat.imePrezime = (String) r.get(i)[2];
                    rezultat.brojPoena = ((BigDecimal) r.get(i)[3]).intValue();
                    vrati.add(rezultat);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return vrati;
    }

    public static class StatistikaProtivDrugih {

        int redniBroj;
        String imePrezime;
        int plavi = 0;
        int crveni = 0;

        public int getRedniBroj() {
            return redniBroj;
        }

        public String getImePrezime() {
            return imePrezime;
        }

        public int getPlavi() {
            return plavi;
        }

        public int getCrveni() {
            return crveni;
        }
    }

    public static List<StatistikaProtivDrugih> dohvatiStatistikuProtivDrugih(int ja) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        List<StatistikaProtivDrugih> vrati = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            String setQuery = "set @rn = 0";
            SQLQuery q1 = session.createSQLQuery(setQuery);
            q1.executeUpdate();

            String query = "select (@rn \\:= @rn + 1) RedniBroj, t.* from (select CONCAT(k2.Ime, ' ', k2.Prezime) ImePrezime, sum(r.PoenaPlavi) SumaPlavi, sum(r.PoenaCrveni) SumaCrveni\n"
                    + "                    FROM partija p \n"
                    + "                    inner join korisnici k1 on (p.plavitakmicar = k1.idkorisnik) \n"
                    + "                    inner join korisnici k2 on (p.crveniTakmicar = k2.idkorisnik)\n"
                    + "                    inner join rezultat r on (p.idpartija = r.idpartija)\n"
                    + "                    where p.samostalno = 0 and DATE(p.datum) >= DATE(DATE_ADD(NOW(), INTERVAL -10 DAY)) and\n"
                    + "                    p.plaviTakmicar = :id\n"
                    + "                    group by r.idpartija\n"
                    + "                    order by datum desc) t\n"
                    + "union\n"
                    + "select (@rn \\:= @rn + 1) RedniBroj, t.* from (select CONCAT(k2.Ime, ' ', k2.Prezime) ImePrezime, sum(r.PoenaPlavi) SumaPlavi, sum(r.PoenaCrveni) SumaCrveni\n"
                    + "                    FROM partija p \n"
                    + "                    inner join korisnici k1 on (p.crveniTakmicar = k1.idkorisnik) \n"
                    + "                    inner join korisnici k2 on (p.plaviTakmicar = k2.idkorisnik)\n"
                    + "                    inner join rezultat r on (p.idpartija = r.idpartija)\n"
                    + "                    where p.samostalno = 0 and DATE(p.datum) >= DATE(DATE_ADD(NOW(), INTERVAL -10 DAY)) and\n"
                    + "                    p.crveniTakmicar = :id\n"
                    + "                    group by r.idpartija\n"
                    + "                    order by datum desc) t";

            SQLQuery q = session.createSQLQuery(query);
            q.setParameter("id", ja);
            List<Object[]> r = q.list();

            for (int i = 0; i < r.size(); i++) {
                StatistikaProtivDrugih rezultat = new StatistikaProtivDrugih();

                rezultat.redniBroj = ((BigInteger) r.get(i)[0]).intValue();
                rezultat.imePrezime = (String) r.get(i)[1];
                rezultat.plavi = ((BigDecimal) r.get(i)[2]).intValue();
                rezultat.crveni = ((BigDecimal) r.get(i)[3]).intValue();

                vrati.add(rezultat);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

        return vrati;
    }
}
