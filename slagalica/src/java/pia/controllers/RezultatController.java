package pia.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.IgraDAO.Tuple;
import pia.common.SessionUtils;

@ManagedBean(name = "rezultatController")
@ViewScoped
public class RezultatController
{
    GameController gameController;
    
    public static class StrukturaZaPrikaz
    {
        String igra;
        int plavi;
        int crveni;

        public StrukturaZaPrikaz(String igra, int plavi, int crveni)
        {
            this.igra = igra;
            this.plavi = plavi;
            this.crveni = crveni;
        }        

        public String getIgra()
        {
            return igra;
        }

        public int getPlavi()
        {
            return plavi;
        }

        public int getCrveni()
        {
            return crveni;
        }
    }
    
    List<StrukturaZaPrikaz> prikaz = new ArrayList<>();
    int sumaPlavi, sumaCrveno;

    public int getSumaPlavi()
    {
        return sumaPlavi;
    }

    public int getSumaCrveno()
    {
        return sumaCrveno;
    }
    
    public List<StrukturaZaPrikaz> getPrikaz()
    {
        return prikaz;
    }

    public RezultatController() throws IOException
    {
        FacesContext context = FacesContext.getCurrentInstance();
        gameController = (GameController) context.getApplication().evaluateExpressionGet(context,"#{gameController}", GameController.class);
        
        HttpSession session = SessionUtils.getSession();
        if (session != null)
        {
            if (session.getAttribute("idIgre") == null)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("meni.xhtml");
                return;
            }
            
            int idIgre = (int) session.getAttribute("idIgre");            
            List<Tuple<Integer, Tuple<Integer, Integer>>> r = IgraDAO.dohvatiRezultatGrupisanoPoIgrama(idIgre);
            
            gameController.partijeUToku.remove(idIgre);
            
            for (Tuple<Integer, Tuple<Integer,Integer>> tuple : r)
            {
                String naziv = "";
                if (tuple.t == 0)
                    naziv = "Slagalica";
                else if (tuple.t == 1)
                    naziv = "Moj broj";
                else if (tuple.t == 2)
                    naziv = "Skočko";
                else if (tuple.t == 3)
                    naziv = "Spojnice";
                else if (tuple.t == 4)
                    naziv = "Asocijacije";
                
                prikaz.add(new StrukturaZaPrikaz(naziv, tuple.u.t, tuple.u.u));
                
                sumaPlavi += tuple.u.t;
                sumaCrveno += tuple.u.u;
            }        
        }
    }
    
    public void naPocetak() throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("meni.xhtml");
    }
}
