package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.SessionUtils;
import pia.controllers.GameController.SkockoInfo.Kombinacija;
import pia.controllers.GameController.SkockoInfo.Znak;
import pia.igre.SkockoIgra;

@ManagedBean
@ViewScoped
public class SkockoController implements Serializable
{
    GameController.SkockoInfo.Kombinacija[] skocka;
    GameController.SkockoInfo.Kombinacija protivnikDaProba;
    GameController.SkockoInfo.Kombinacija dobitnaKombinacija;
    GameController gameController;

    GameController.SkockoInfo info;

    int idIgre;
    int samostalno;
    boolean poslato = false;
    
    int identifikatorKorisnika;

    int brojPogadanja = 0;

    boolean crveniTakmicar = false;
    boolean dozvoli = false;

    public SkockoController()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        gameController = (GameController) context.getApplication().evaluateExpressionGet(context, "#{gameController}", GameController.class);

        HttpSession session = SessionUtils.getSession();
        if (session != null)
        {
            if (session.getAttribute("samostalno") == null)
            {
                samostalno = 0;
            }
            else
            {
                samostalno = (int) session.getAttribute("samostalno");
            }

            if ((int) session.getAttribute("mestoKorisnika") == 1)
            {
                crveniTakmicar = true;
            }

            idIgre = (int) session.getAttribute("idIgre");

            if (samostalno == 1)
            {
                info = gameController.skockoUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");
                identifikatorKorisnika = id;

                skocka = info.unetaKombinacijaPlavi1;
                dobitnaKombinacija = info.dobitnaKombinacija1;
                protivnikDaProba = info.unetaKombinacijaCrveni1;
                dozvoli = true;
            }
            else
            {
                info = gameController.skockoUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");
                identifikatorKorisnika = id;

                if (info.idPlavi == id)
                {
                    if (!info.plaviOdigrao1)
                    {
                        skocka = info.unetaKombinacijaPlavi1;
                        dobitnaKombinacija = info.dobitnaKombinacija1;
                        protivnikDaProba = info.unetaKombinacijaCrveni1;
                        dozvoli = true;
                    }
                    else if (!info.plaviOdigrao2)
                    {
                        skocka = info.unetaKombinacijaCrveni2;
                        dobitnaKombinacija = info.dobitnaKombinacija2;
                        protivnikDaProba = info.unetaKombinacijaPlavi2;
                        dozvoli = false;
                    }
                }
                else if (info.idCrveni == id)
                {
                    if (!info.crveniOdigrao1)
                    {
                        skocka = info.unetaKombinacijaPlavi1;
                        dobitnaKombinacija = info.dobitnaKombinacija1;
                        protivnikDaProba = info.unetaKombinacijaCrveni1;
                        dozvoli = false;
                    }
                    else if (!info.crveniOdigrao2)
                    {
                        skocka = info.unetaKombinacijaCrveni2;
                        dobitnaKombinacija = info.dobitnaKombinacija2;
                        protivnikDaProba = info.unetaKombinacijaPlavi2;
                        dozvoli = true;
                        info.preostaloVreme = 60;
                    }
                }
            }
        }
    }

    public Kombinacija getProtivnikDaProba()
    {
        return protivnikDaProba;
    }

    public void setProtivnikDaProba(Kombinacija protivnikDaProba)
    {
        this.protivnikDaProba = protivnikDaProba;
    }

    public int getPreostaloVreme()
    {
        return info.preostaloVreme;
    }

    public GameController getGameController()
    {
        return gameController;
    }

    public void setGameController(GameController gameController)
    {
        this.gameController = gameController;
    }

    public GameController.SkockoInfo.Kombinacija[] getSkocka()
    {
        return skocka;
    }

    public void setSkocka(GameController.SkockoInfo.Kombinacija[] skocka)
    {
        this.skocka = skocka;
    }

    public int getIdIgre()
    {
        return idIgre;
    }

    public void setIdIgre(int idIgre)
    {
        this.idIgre = idIgre;
    }

    public int getSamostalno()
    {
        return samostalno;
    }

    public void setSamostalno(int samostalno)
    {
        this.samostalno = samostalno;
    }

    public boolean isPoslato()
    {
        return poslato;
    }

    public void setPoslato(boolean poslato)
    {
        this.poslato = poslato;
    }

    public void tajmerListener() throws IOException
    {
        if (info.idCrveni == identifikatorKorisnika || ((info.idPlavi == identifikatorKorisnika) && samostalno == 1))
            info.preostaloVreme--;

        if (info.preostaloVreme == 0)
        {
            for (int i = 0; i < 6; i++)
            {
                if (!skocka[i].potvrdeno)
                {
                    potvrdiSkocka(skocka[i], true);
                    
                    break;
                }
            }
        }

        if (crveniTakmicar)
        {
            if (info.plaviOdigrao1 && !info.zavrseno1)
            {
                dozvoli = true;
            }
            else if (info.zavrseno1 && dobitnaKombinacija == info.dobitnaKombinacija1)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("skocko.xhtml");
            }
            else if (info.zavrseno1 && info.zavrseno2)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("spojnice.xhtml");
            }
        }
        else
        {
            if (info.crveniOdigrao2 && !info.zavrseno2)
            {
                dozvoli = true;
            }
            else if (info.zavrseno1 && dobitnaKombinacija == info.dobitnaKombinacija1)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("skocko.xhtml");
            }
            else if (info.zavrseno1 && info.zavrseno2)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("spojnice.xhtml");
            }
        }
    }

    public void simbolListener(int simbol)
    {
        if (dozvoli)
        {
            GameController.SkockoInfo.Znak znak = GameController.SkockoInfo.Kombinacija.IntToZnak(simbol);
            
            for (int i = 0; i < skocka.length; i++)
            {
                Kombinacija k;
                
                if (crveniTakmicar && info.plaviOdigrao1 && !info.crveniOdigrao1)
                {
                    k = protivnikDaProba;
                }
                else if (!crveniTakmicar && info.crveniOdigrao2 && !info.plaviOdigrao2)
                {
                    k = protivnikDaProba;
                }
                else
                {
                    k = skocka[i];
                }

                if (k.potvrdeno)
                    continue;
                
                if (k.znak1 == GameController.SkockoInfo.Znak.PRAZNO)
                {
                    k.znak1 = Kombinacija.IntToZnak(simbol);
                    break;
                }
                else if (k.znak2 == GameController.SkockoInfo.Znak.PRAZNO)
                {
                    k.znak2 = Kombinacija.IntToZnak(simbol);
                    break;
                }
                else if (k.znak3 == GameController.SkockoInfo.Znak.PRAZNO)
                {
                    k.znak3 = Kombinacija.IntToZnak(simbol);
                    break;
                }
                else if (k.znak4 == GameController.SkockoInfo.Znak.PRAZNO)
                {
                    k.znak4 = Kombinacija.IntToZnak(simbol);
                    break;
                }
            }
        }
    }

    public void obrisiSimbol(Kombinacija kombinacija, int r)
    {
        if (kombinacija.potvrdeno)
            return;
        
        switch (r)
        {
            case 0:
                kombinacija.znak1 = Znak.PRAZNO;
                break;
            case 1:
                kombinacija.znak2 = Znak.PRAZNO;
                break;
            case 2:
                kombinacija.znak3 = Znak.PRAZNO;
                break;
            case 3:
                kombinacija.znak4 = Znak.PRAZNO;
                break;
        }
    }

    public boolean potvrdaRendered(int i)
    {
        if (dobitnaKombinacija == info.dobitnaKombinacija1)
        {
            if (i == 0 && !crveniTakmicar)
                return true;
            else if (i == 1 && crveniTakmicar)
                return true;
            else
                return false;
        }
        else
        {
            if (i == 0 && crveniTakmicar)
                return true;
            else if (i == 1 && !crveniTakmicar)
                return true;
            else
                return false;
        }
    }
    
    
    public void potvrdiSkocka(Kombinacija k, boolean istekloVreme) throws IOException
    {
        brojPogadanja = 5;
        potvrdiSkocka(k);
    }
    
    public void potvrdiSkocka(Kombinacija k) throws IOException
    {
        if (!k.potvrdeno)
        {
            SkockoIgra.OdrediRezultatKombinacije(k, dobitnaKombinacija);
            brojPogadanja++;
            
            if (k.pobedeno)
            {
                if (samostalno == 1)
                {
                    IgraDAO.upisiPoeneMultiplayer(idIgre, 2, 10, 0);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("spojnice.xhtml");
                    return;
                }
                
                if (!info.plaviOdigrao1)
                {
                    info.plaviOdigrao1 = true;
                    info.crveniOdigrao1 = true;
                    IgraDAO.upisiPoeneMultiplayer(idIgre, 2, 10, 0);
                }
                else if (!info.crveniOdigrao1)
                {
                    info.crveniOdigrao1 = true;
                    IgraDAO.upisiPoeneMultiplayer(idIgre, 2, 0, 10);
                }
                else if (!info.crveniOdigrao2)
                {
                    info.crveniOdigrao2 = true;
                    info.plaviOdigrao2 = true;
                    IgraDAO.upisiPoeneMultiplayer(idIgre, 2, 0, 10);
                }
                else if (!info.plaviOdigrao2)
                {
                    info.plaviOdigrao2 = true;
                    IgraDAO.upisiPoeneMultiplayer(idIgre, 2, 10, 0);
                }

                if (dobitnaKombinacija == info.dobitnaKombinacija1)
                {
                    info.zavrseno1 = true;
                }
                
                if (dobitnaKombinacija == info.dobitnaKombinacija2)
                {
                    info.zavrseno2 = true;
                }
            }
            else if (brojPogadanja == 6 || k == protivnikDaProba)
            {
                if (samostalno == 1)
                {
                    IgraDAO.upisiPoeneMultiplayer(idIgre, 2, 0, 0);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("spojnice.xhtml");
                    return;
                }
                dozvoli = false;

                if (!info.plaviOdigrao1)
                {
                    info.plaviOdigrao1 = true;
                    info.preostaloVreme = 60;
                }
                else if (!info.crveniOdigrao1)
                {
                    info.crveniOdigrao1 = true;
                }
                else if (!info.crveniOdigrao2)
                {
                    info.crveniOdigrao2 = true;
                    info.preostaloVreme = 60;
                }
                else if (!info.plaviOdigrao2)
                {
                    info.plaviOdigrao2 = true;
                }

                if (info.plaviOdigrao1 && info.crveniOdigrao1)
                {
                    info.zavrseno1 = true;
                }
                
                if (info.plaviOdigrao2 && info.crveniOdigrao2)
                {
                    info.zavrseno2 = true;
                }
            }
        }
    }
}
