package pia.igre;

import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pia.common.IgraDAO;
import pia.common.NewHibernateUtil;
import pia.common.Utils;
import pia.controllers.GameController.KoDobija;
import pia.database.Korisnici;

public class SlagalicaIgra {

    public static String[] GenerisiSlova() {
        String[] ponudenjaSlova = new String[12];

        for (int i = 0; i < 12; i++) {
            int generisano = Utils.GenerisiBroj(0, 29);
            generisano += 65;
            
            if (generisano == 81)
                ponudenjaSlova[i] = "Č";
            else if (generisano == 87)
                ponudenjaSlova[i] = "Ć";
            else if (generisano == 88)
                ponudenjaSlova[i] = "DŽ";
            else if (generisano == 89)
                ponudenjaSlova[i] = "Đ";
            else if (generisano == 91)
                ponudenjaSlova[i] = "LJ";
            else if (generisano == 92)
                ponudenjaSlova[i] = "NJ";
            else if (generisano == 93)
                ponudenjaSlova[i] = "Š";
            else if (generisano == 94)
                ponudenjaSlova[i] = "Ž";
            else
                ponudenjaSlova[i] = new String(Character.toString((char)generisano));
        }

        return ponudenjaSlova;
    }

    public static boolean ValidnaRec(String rec) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ret = false;

        try {
            tx = session.beginTransaction();
            String query = "FROM Slagalica s WHERE LOWER(s.rec) = :rec";
            Query q = session.createQuery(query);
            q.setParameter("rec", rec.toLowerCase().trim());
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

    public static int OdrediBrojPoena(String rec) {
        return rec.length() * 2;
    }

    public static KoDobija OdrediBrojPoena(String plaviRec, String crveniRec) {
        if (!IgraDAO.recPostoji(plaviRec))
            plaviRec = "";
        
        if (!IgraDAO.recPostoji(crveniRec))
            crveniRec = "";
        
        if (plaviRec.length() < crveniRec.length()) {
            return new KoDobija(2 * crveniRec.length(), KoDobija.Takmicar.CRVENI);
        } else if (plaviRec.length() == crveniRec.length()) {
            return new KoDobija(2 * crveniRec.length(), KoDobija.Takmicar.OBA);
        } else {
            return new KoDobija(2 * plaviRec.length(), KoDobija.Takmicar.PLAVI);
        }
    }
}
