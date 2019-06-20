package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.SessionUtils;

@ManagedBean
@ViewScoped
public class SpojnicaController implements Serializable
{
    Map<String, String> kombinacija;
    GameController gameController;
    
    String[] leviZaPrikaz;
    String[] desniZaPrikaz;
    
    GameController.SpojniceInfo info;

    int idIgre;
    int samostalno;
    boolean poslato = false;

    int brojPogadanja = 0;

    boolean crveniTakmicar = false;
    boolean dozvoli = false;

    public int dohvatiPreostaloVreme()
    {
        return info.vreme;
    }

    public Map<String, String> getKombinacija()
    {
        return kombinacija;
    }

    public String[] getLeviZaPrikaz()
    {
        return leviZaPrikaz;
    }

    public String[] getDesniZaPrikaz()
    {
        return desniZaPrikaz;
    }
    
    public SpojnicaController()
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
                info = gameController.spojniceUToku.get(idIgre);
                
                kombinacija = info.kombinacija1;
                leviZaPrikaz = info.leviZaPrikaz1;
                desniZaPrikaz = info.desniZaPrikaz1;
                info.promenjeno = false;
                dozvoli = true;
            }
            else
            {
                info = gameController.spojniceUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");

                if (info.idPlavi == id)
                {
                    if (!info.plaviOdigrao1)
                    {
                        kombinacija = info.kombinacija1;
                        leviZaPrikaz = info.leviZaPrikaz1;
                        desniZaPrikaz = info.desniZaPrikaz1;
                        info.promenjeno = false;
                        dozvoli = true;
                    }
                    else if (!info.plaviOdigrao2)
                    {
                        kombinacija = info.kombinacija2;
                        leviZaPrikaz = info.leviZaPrikaz2;
                        desniZaPrikaz = info.desniZaPrikaz2;
                        dozvoli = false;
                    }
                }
                else if (info.idCrveni == id)
                {
                    if (!info.crveniOdigrao1)
                    {
                        kombinacija = info.kombinacija1;
                        leviZaPrikaz = info.leviZaPrikaz1;
                        desniZaPrikaz = info.desniZaPrikaz1;
                        dozvoli = false;
                        brojPoena = 0;
                    }
                    else if (!info.crveniOdigrao2)
                    {
                        kombinacija = info.kombinacija2;
                        leviZaPrikaz = info.leviZaPrikaz2;
                        desniZaPrikaz = info.desniZaPrikaz2;
                        info.promenjeno = false;
                        
                        info.pogodakLevi = new boolean[10];
                        info.pogodakDesni = new boolean[10];
                        info.indeksAktivan = 0;
                        brojPoena = 0;
                        
                        dozvoli = true;
                    }
                }
            }
        }
    }
    
    public String stilizuj(boolean levi, int indeks)
    {
        if (levi)
        {
            if (indeks == info.indeksAktivan)
                return "zutaSpojnica";
            else if ((indeks < info.indeksAktivan && info.pogodakLevi[indeks] == true) || (info.pogodakLevi[indeks]))
                return "zelenaSpojnica";
            else if (indeks < info.indeksAktivan && info.pogodakLevi[indeks] == false)
                return "crvenaSpojnica";
            else
                return "obicnaSpojnica";
        }
        else
        {
            if (info.pogodakDesni[indeks])
                return "zelenaSpojnica";
            else
                return "obicnaSpojnica";
        }
    }
    
    public void tajmerListener() throws IOException 
    {        
        if (crveniTakmicar)
        {
            if (info.plaviOdigrao1 && !info.zavrseno1 && !info.promenjeno)
            {
                info.promenjeno = true;
                
                for (int i = 0; i < 10; i++)
                {
                    if (info.pogodakLevi[i] == false)
                    {
                        info.indeksAktivan = i;
                        break;
                    }
                }
                
                dozvoli = true;
            }
            else if (info.zavrseno1 && kombinacija == info.kombinacija1)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("spojnice.xhtml");
            }
            else if (info.zavrseno1 && info.zavrseno2)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("asocijacije.xhtml");
            }
        }
        else
        {
            info.vreme--;
            if (info.vreme == 0)
            {
                predajDrugome();
            }
            
            if (info.crveniOdigrao2 && !info.zavrseno2 && !info.promenjeno)
            {
                info.promenjeno = true;
                
                for (int i = 0; i < 10; i++)
                {
                    if (info.pogodakLevi[i] == false)
                    {
                        info.indeksAktivan = i;
                        break;
                    }
                }
                
                dozvoli = true;
            }
            else if (info.zavrseno1 && kombinacija == info.kombinacija1)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("spojnice.xhtml");
            }
            else if (info.zavrseno1 && info.zavrseno2)
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("asocijacije.xhtml");
            }
        }
    }
    
    int brojPoena = 0;
    
    public void potvrdi(int indeks) throws IOException
    {
        if (dozvoli && info.pogodakDesni[indeks] == false)
        {
            String levaStrana = leviZaPrikaz[info.indeksAktivan];
            String desnaStranaTacno = kombinacija.get(levaStrana);

            if (desniZaPrikaz[indeks].equals(desnaStranaTacno))
            {
                for (int i = 0; i < leviZaPrikaz.length; i++)
                {
                    if (leviZaPrikaz[i].equals(levaStrana))
                        info.pogodakLevi[i] = true;

                    if (desniZaPrikaz[i].equals(desnaStranaTacno))
                        info.pogodakDesni[i] = true;
                }
                
                brojPoena++;
            }
            
            // while zbog protivnika da proba ako ovaj nije sve potrefio vec
            do
            {
                info.indeksAktivan++;
                if (info.indeksAktivan != 10 && info.pogodakLevi[info.indeksAktivan])
                    continue;
                else
                    break;
            } while (info.indeksAktivan < 10);
            
            if (info.indeksAktivan == 10)
            {
                dozvoli = false;
                predajDrugome();
            }
        }
    }
    
    private void predajDrugome() throws IOException
    {
        if (samostalno == 1)
        {
            IgraDAO.upisiPoeneMultiplayer(idIgre, 3, brojPoena, 0);
            FacesContext.getCurrentInstance().getExternalContext().redirect("asocijacije.xhtml");
            return;
        }
        
        if (!info.plaviOdigrao1)
        {
            info.plaviOdigrao1 = true;
            if (brojPoena == 10)
                info.crveniOdigrao1 = true;
            
            IgraDAO.upisiPoeneMultiplayer(idIgre, 3, brojPoena, 0);
        }
        else if (!info.crveniOdigrao1)
        {
            info.crveniOdigrao1 = true;
            
            IgraDAO.upisiPoeneMultiplayer(idIgre, 3, 0, brojPoena);
        }
        else if (!info.crveniOdigrao2)
        {
            info.crveniOdigrao2 = true;
            if (brojPoena == 10)
                info.plaviOdigrao2 = true;
            
            IgraDAO.upisiPoeneMultiplayer(idIgre, 3, 0, brojPoena);
        }
        else if (!info.plaviOdigrao2)
        {
            info.plaviOdigrao2 = true;
            
            IgraDAO.upisiPoeneMultiplayer(idIgre, 3, brojPoena, 0);
        }

        if (kombinacija == info.kombinacija1 && (brojPoena == 10 || (info.crveniOdigrao1 && info.plaviOdigrao1)))
        {
            info.zavrseno1 = true;
        }

        if (kombinacija == info.kombinacija2 && (brojPoena == 10 || (info.crveniOdigrao2 && info.plaviOdigrao2)))
        {
            info.zavrseno2 = true;
        }
        
        brojPoena = 0;
        info.vreme = 60;
    }
}