package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.SessionUtils;

@ManagedBean(name = "asocijacijeController")
@ViewScoped
public class AsocijacijeController implements Serializable
{
    GameController gameController;
    GameController.AsocijacijaInfo info;
    
    boolean otvorio = false;
    int samostalno;
    int idIgre;
    
    boolean crveniTakmicar;
    String resenjeA, resenjeB, resenjeC, resenjeD, resenjeKonacno;
    int redniBrojIgre;

    public AsocijacijeController()
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
                info = gameController.asocijacijaUToku.get(idIgre);
                redniBrojIgre = info.brojIgre;
            }
            else
            {
                info = gameController.asocijacijaUToku.get(idIgre);
                redniBrojIgre = info.brojIgre;
            }
        }
    }
    
    public String getResenjeA()
    {
        return resenjeA;
    }

    public String getResenjeB()
    {
        return resenjeB;
    }

    public String getResenjeC()
    {
        return resenjeC;
    }

    public String getResenjeD()
    {
        return resenjeD;
    }

    public String getResenjeKonacno()
    {
        return resenjeKonacno;
    }

    public void setResenjeA(String resenjeA)
    {
        this.resenjeA = resenjeA;
    }

    public void setResenjeB(String resenjeB)
    {
        this.resenjeB = resenjeB;
    }

    public void setResenjeC(String resenjeC)
    {
        this.resenjeC = resenjeC;
    }

    public void setResenjeD(String resenjeD)
    {
        this.resenjeD = resenjeD;
    }

    public void setResenjeKonacno(String resenjeKonacno)
    {
        this.resenjeKonacno = resenjeKonacno;
    }
    
    public String[] getKolonaA()
    {
        return info.kolonaA;
    }

    public String[] getKolonaB()
    {
        return info.kolonaB;
    }

    public String[] getKolonaC()
    {
        return info.kolonaC;
    }

    public String[] getKolonaD()
    {
        return info.kolonaD;
    }
    
    public void otvoriPolje(int kolona, int red, boolean kodOtvara)
    {
        if ((otvorio == false && ((info.igraPlavi && !crveniTakmicar) || (!info.igraPlavi && crveniTakmicar))) || kodOtvara || samostalno == 1)
        {
            boolean[] otvoreno;
            String[] d;
            String[] prikaz;

            if (kolona == 0)
            {
                otvoreno = info.otvorenoA;
                d = info.bazaA;
                prikaz = info.kolonaA;
            }
            else if (kolona == 1)
            {
                otvoreno = info.otvorenoB;
                d = info.bazaB;
                prikaz = info.kolonaB;
            }
            else if (kolona == 2)
            {
                otvoreno = info.otvorenoC;
                d = info.bazaC;
                prikaz = info.kolonaC;
            }
            else
            {
                otvoreno = info.otvorenoD;
                d = info.bazaD;
                prikaz = info.kolonaD;
            }

            if (otvoreno[red] == false)
            {
                otvoreno[red] = true;
                prikaz[red] = d[red];
                if (!kodOtvara)
                    otvorio = true;
            }
        }
    }
    
    public String odrediStil(int kolona, int red)
    {
        boolean otvoreno = false;
        int pogodeno = 0;
        
        if (kolona == 0)
        {
            otvoreno = info.otvorenoA[red];
            pogodeno = info.pogodenoA;
        }
        else if (kolona == 1)
        {
            otvoreno = info.otvorenoB[red];
            pogodeno = info.pogodenoB;
        }
        else if (kolona == 2)
        {
            otvoreno = info.otvorenoC[red];
            pogodeno = info.pogodenoC;
        }
        else if (kolona == 3)
        {
            otvoreno = info.otvorenoD[red];
            pogodeno = info.pogodenoD;
        }
        else if (kolona == 5)
        {
            if (red == 0)
                pogodeno = info.pogodenoA;
            else if (red == 1)
                pogodeno = info.pogodenoB;
            else if (red == 2)
                pogodeno = info.pogodenoC;
            else if (red == 3)
                pogodeno = info.pogodenoD;
            else if (red == 4)
                pogodeno = info.pogodenoKonacno;
        }
        
        if (pogodeno == 1)
            return "plavaSpojnica";
        else if (pogodeno == 2)
            return "crvenaSpojnica";
        else if (otvoreno && pogodeno == 0)
            return "zutaSpojnica";
        else 
            return "obicnaSpojnica";
    }
    
    public void proveriUnos()
    {        
        boolean postojiNeotvoreno = false;
        for (int i = 0; i < 4; i++)
        {
            if (info.otvorenoA[i] == false || 
                info.otvorenoB[i] == false ||
                info.otvorenoC[i] == false ||
                info.otvorenoD[i] == false)
            {
                postojiNeotvoreno = true;
                break;
            }
        }
        
        if ((info.igraPlavi && !crveniTakmicar) || (!info.igraPlavi && crveniTakmicar) || samostalno == 1)
        {
            if (otvorio == false && postojiNeotvoreno)
                return;
            
            proveriUnos(4);
            for (int i = 0; i < 3; i++)
                proveriUnos(i);
            
            otvorio = false;
            
            if (samostalno == 0)
                info.igraPlavi = !info.igraPlavi;
        }
    }
    
    public boolean proveriUnos(int no)
    {
        if (no == 0)
        {
            if (info.pogodenoA == 0 && info.resenjeA.contains(resenjeA.toUpperCase()))
            {                
                for (int i = 0; i < 4; i++)
                    otvoriPolje(0, i, true);
                info.pogodenoA = (crveniTakmicar ? 2 : 1);
                IgraDAO.upisiPoeneMultiplayer(idIgre, 4, (!crveniTakmicar ? 5 : 0), (crveniTakmicar ? 5 : 0));
                
                return true;
            }
        }
        else if (no == 1)
        {
            if (info.pogodenoB == 0 && info.resenjeB.contains(resenjeB.toUpperCase()))
            {
                for (int i = 0; i < 4; i++)
                    otvoriPolje(1, i, true);
                info.pogodenoB = (crveniTakmicar ? 2 : 1);
                IgraDAO.upisiPoeneMultiplayer(idIgre, 4, (!crveniTakmicar ? 5 : 0), (crveniTakmicar ? 5 : 0));
                
                return true;
            }
        }
        else if (no == 2)
        {
            if (info.pogodenoC == 0 && info.resenjeC.contains(resenjeC.toUpperCase()))
            {
                for (int i = 0; i < 4; i++)
                    otvoriPolje(2, i, true);
                info.pogodenoC = (crveniTakmicar ? 2 : 1);
                IgraDAO.upisiPoeneMultiplayer(idIgre, 4, (!crveniTakmicar ? 5 : 0), (crveniTakmicar ? 5 : 0));
                
                return true;
            }
        }
        else if (no == 3)
        {
            if (info.pogodenoD == 0 && info.resenjeD.contains(resenjeD.toUpperCase()))
            {
                for (int i = 0; i < 4; i++)
                    otvoriPolje(3, i, true);
                info.pogodenoD = (crveniTakmicar ? 2 : 1);
                IgraDAO.upisiPoeneMultiplayer(idIgre, 4, (!crveniTakmicar ? 5 : 0), (crveniTakmicar ? 5 : 0));
                
                return true;
            }
        }
        else if (no == 4)
        {
            if (info.pogodenoKonacno == 0 && info.resenjeKonacno.contains(resenjeKonacno.toUpperCase()))
            {
                int brojNeotvorenihPolja = 0;
                
                for (int i = 0; i < 4; i++)
                {
                    if (info.otvorenoA[i] == false)
                        brojNeotvorenihPolja++;
                    if (info.otvorenoB[i] == false)
                        brojNeotvorenihPolja++;
                    if (info.otvorenoC[i] == false)
                        brojNeotvorenihPolja++;
                    if (info.otvorenoD[i] == false)
                        brojNeotvorenihPolja++;
                    otvoriPolje(0, i, true);
                    otvoriPolje(1, i, true);
                    otvoriPolje(2, i, true);
                    otvoriPolje(3, i, true);
                }
                IgraDAO.upisiPoeneMultiplayer(idIgre, 4, (!crveniTakmicar ? 5 * brojNeotvorenihPolja : 0), (crveniTakmicar ? 5 * brojNeotvorenihPolja : 0));
                IgraDAO.upisiPoeneMultiplayer(idIgre, 4, (!crveniTakmicar ? 10 : 0), (crveniTakmicar ? 10 : 0));
                
                if (info.pogodenoA == 0)
                    info.pogodenoA = (crveniTakmicar ? 2 : 1);
                
                if (info.pogodenoB == 0)
                    info.pogodenoB = (crveniTakmicar ? 2 : 1);
                
                if (info.pogodenoC == 0)
                    info.pogodenoC = (crveniTakmicar ? 2 : 1);
                
                if (info.pogodenoD == 0)
                    info.pogodenoD = (crveniTakmicar ? 2 : 1);
                
                resenjeA = info.resenjeA.iterator().next();
                resenjeB = info.resenjeB.iterator().next();
                resenjeC = info.resenjeC.iterator().next();
                resenjeD = info.resenjeD.iterator().next();
                resenjeKonacno = info.resenjeKonacno.iterator().next();
                
                info.pogodenoKonacno = (crveniTakmicar ? 2 : 1);
                
                try
                {
                    zavrsiAsocijaciju();
                }
                catch (Exception ex)
                {
                    
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public boolean resenjeDisabled(int no)
    {
        if (no == 0 && info.pogodenoA != 0)
            return true;
        else if (no == 1 && info.pogodenoB != 0)
            return true;
        else if (no == 2 && info.pogodenoC != 0)
            return true;
        else if (no == 3 && info.pogodenoD != 0)
            return true;
        else if (no == 4 && info.pogodenoKonacno != 0)
            return true;
        else
            return false;
    }
    
    public void tajmerListener() throws IOException
    {
        int novi = gameController.asocijacijaUToku.get(idIgre).brojIgre;
        if (novi != redniBrojIgre && novi == 2)
            FacesContext.getCurrentInstance().getExternalContext().redirect("asocijacije.xhtml");
        else if (info.brojIgre == 2 && info.zavrseno)
            FacesContext.getCurrentInstance().getExternalContext().redirect("rezultat.xhtml");
        
        if (!crveniTakmicar)
        {
            info.vreme--;
        }
        
        if (info.pogodenoA != 0)
            resenjeA = info.resenjeA.iterator().next();
        if (info.pogodenoB != 0)
            resenjeB = info.resenjeB.iterator().next();
        if (info.pogodenoC != 0)
            resenjeC = info.resenjeC.iterator().next();
        if (info.pogodenoD != 0)
            resenjeD = info.resenjeD.iterator().next();
        if (info.pogodenoKonacno != 0)
            resenjeKonacno = info.resenjeKonacno.iterator().next();
        
        if (info.vreme == 0)
        {
            zavrsiAsocijaciju();
        }
    }
    
    public int dohvatiPreostaloVreme()
    {
        return info.vreme;
    }

    private void zavrsiAsocijaciju() throws IOException
    {
        info.zavrseno = true;
        
        if (samostalno == 1)
            FacesContext.getCurrentInstance().getExternalContext().redirect("rezultat.xhtml");
        else if (info.brojIgre == 1)
        {
            GameController.AsocijacijaInfo asocijacijaInfo;
            
            do
            {
                asocijacijaInfo = IgraDAO.dohvatiAsocijaciju();
            } while (asocijacijaInfo.idUBazi == info.idUBazi);
            
            asocijacijaInfo.idPlavi = info.idPlavi;
            asocijacijaInfo.idCrveni = info.idCrveni;
            asocijacijaInfo.idIgre = idIgre;
            asocijacijaInfo.brojIgre = 2;
            asocijacijaInfo.igraPlavi = false;
            gameController.asocijacijaUToku.remove(idIgre);
            gameController.asocijacijaUToku.put(idIgre, asocijacijaInfo);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("asocijacije.xhtml");
        }
        else
            FacesContext.getCurrentInstance().getExternalContext().redirect("rezultat.xhtml");        
    }
}