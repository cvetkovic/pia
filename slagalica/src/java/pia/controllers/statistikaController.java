package pia.controllers;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.IgraDAO.StatistikaProtivDrugih;
import pia.common.SessionUtils;

@ManagedBean
@RequestScoped
public class statistikaController implements Serializable
{

    List<IgraDAO.StatistikaProtivDrugih> nedeljnaStatistika;
    List<IgraDAO.StatistikaProtivDrugih> mesecnaStatistika;
    StatistikaProtivDrugih mec;

    public StatistikaProtivDrugih getMec()
    {
        return mec;
    }

    public List<IgraDAO.StatistikaProtivDrugih> getNedeljnaStatistika()
    {
        return nedeljnaStatistika;
    }

    public List<IgraDAO.StatistikaProtivDrugih> getMesecnaStatistika()
    {
        return mesecnaStatistika;
    }

    public statistikaController()
    {
        nedeljnaStatistika = IgraDAO.dohvatiNedeljnuStatistiku();
        mesecnaStatistika = IgraDAO.dohvatiMesecnuStatistiku();

        HttpSession session = SessionUtils.getSession();

        if (session != null && session.getAttribute("idIgre") != null)
        {
            int id = (int) session.getAttribute("idIgre");
            mec = IgraDAO.dohvatiTrenutnuStatistikuIgre(id);
        }
    }

    public boolean samostalno()
    {
        HttpSession session = SessionUtils.getSession();
        if (session != null && session.getAttribute("samostalno") != null)
        {
            if ((int) session.getAttribute("samostalno") == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
            return false;
    }
}