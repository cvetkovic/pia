package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.SessionUtils;
import pia.controllers.GameController.KoDobija.Takmicar;
import pia.igre.SlagalicaIgra;

@ManagedBean(name = "slagalicaController")
@ViewScoped
public class SlagalicaController implements Serializable
{

    String[] slova;
    boolean[] dozvoljeno =
    {
        true, true, true, true, true, true, true, true, true, true, true, true
    };
    String unetaRec = "";

    int idIgre;
    int samostalno;
    boolean poslato = false;

    GameController gameController;
    GameController.SlagalicaInfo info;

    int preostaloVreme;
    int identifikatorKorisnika;

    public GameController getGameController()
    {
        return gameController;
    }

    public void setGameController(GameController gameController)
    {
        this.gameController = gameController;
    }

    public boolean isPoslato()
    {
        return poslato;
    }

    public int getPreostaloVreme()
    {
        return preostaloVreme;
    }

    boolean ucitano1, ucitano2;
    
    public SlagalicaController()
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

            idIgre = (int) session.getAttribute("idIgre");

            if (samostalno == 1)
            {
                info = gameController.slagaliceUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");
                identifikatorKorisnika = id;

                slova = info.reci1;
                preostaloVreme = 60;
            }
            else
            {
                info = gameController.slagaliceUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");
                identifikatorKorisnika = id;

                if (info.idPlavi == id)
                {
                    if (!info.plaviOdigrao1)
                    {
                        slova = info.reci1;
                    }
                    else if (!info.plaviOdigrao2)
                    {
                        slova = info.reci2;
                    }
                }
                else if (info.idCrveni == id)
                {
                    if (!info.crveniOdigrao1)
                    {
                        slova = info.reci1;
                    }
                    else if (!info.crveniOdigrao2)
                    {
                        //preostaloVreme = 60;
                        slova = info.reci2;
                    }
                }
                
                if (!ucitano1 && slova == info.reci1)
                {
                    ucitano1 = true;
                    preostaloVreme = 60;
                }
                else if (!ucitano2 && slova == info.reci2)
                {
                    ucitano1 = true;
                    preostaloVreme = 60;
                }
            }
        }
    }

    public boolean[] getDozvoljeno()
    {
        return dozvoljeno;
    }

    public void setDozvoljeno(boolean[] dozvoljeno)
    {
        this.dozvoljeno = dozvoljeno;
    }

    public String[] getSlova()
    {
        return slova;
    }

    public String getUnetaRec()
    {
        return unetaRec;
    }

    public void setUnetaRec(String unetaRec)
    {
        this.unetaRec = unetaRec;
    }

    public void setSlova(String[] slova)
    {
        this.slova = slova;
    }

    public void dodajSlovo(int index)
    {
        unetaRec += slova[index];
        dozvoljeno[index] = !dozvoljeno[index];
    }

    public void ukloniSlovo()
    {
        if (!unetaRec.equals(""))
        {
            String c;

            if (unetaRec.length() >= 2 && unetaRec.substring(unetaRec.length() - 2, unetaRec.length()).equals("LJ"))
            {
                c = "LJ";
                unetaRec = unetaRec.substring(0, unetaRec.length() - 2);
            }
            else if (unetaRec.length() >= 2 && unetaRec.substring(unetaRec.length() - 2, unetaRec.length()).equals("NJ"))
            {
                c = "NJ";
                unetaRec = unetaRec.substring(0, unetaRec.length() - 2);
            }
            else if (unetaRec.length() >= 2 && unetaRec.substring(unetaRec.length() - 2, unetaRec.length()).equals("DŽ"))
            {
                c = "DŽ";
                unetaRec = unetaRec.substring(0, unetaRec.length() - 2);
            }
            else
            {
                c = Character.toString(unetaRec.charAt(unetaRec.length() - 1));
                unetaRec = unetaRec.substring(0, unetaRec.length() - 1);
            }

            for (int i = 0; i < slova.length; i++)
            {
                if (slova[i].equals(c) && dozvoljeno[i] == false)
                {
                    dozvoljeno[i] = !dozvoljeno[i];
                    break;
                }
            }
        }
    }

    public void potvrdiRec() throws IOException
    {
        poslato = true;
        if (samostalno == 1)
        {
            HttpSession session = SessionUtils.getSession();
            GameController.SlagalicaInfo info = gameController.slagaliceUToku.get(idIgre);
            int id = (int) session.getAttribute("korisnickiID");

            GameController.KoDobija brojPoena = SlagalicaIgra.OdrediBrojPoena(unetaRec, "");
            IgraDAO.upisiPoeneMultiplayer(info.idIgre,
                    0,
                    brojPoena.vrednost,
                    0);
            FacesContext.getCurrentInstance().getExternalContext().redirect("mojbroj.xhtml");
        }
        else
        {
            HttpSession session = SessionUtils.getSession();
            GameController.SlagalicaInfo info = gameController.slagaliceUToku.get(idIgre);
            int id = (int) session.getAttribute("korisnickiID");

            if (slova == info.reci1)
            {
                info.semaphore.acquireUninterruptibly();
                if (info.idPlavi == id)
                {
                    info.plaviOdigrao1 = true;
                    info.plaviRec1 = unetaRec;
                }
                else if (info.idCrveni == id)
                {
                    info.crveniOdigrao1 = true;
                    info.crveniRec1 = unetaRec;
                }
                info.semaphore.release();

                FacesContext.getCurrentInstance().getExternalContext().redirect("slagalica.xhtml");
            }
            else if (slova == info.reci2)
            {
                info.semaphore.acquireUninterruptibly();
                if (info.idPlavi == id)
                {
                    info.plaviOdigrao2 = true;
                    info.plaviRec2 = unetaRec;
                }
                else if (info.idCrveni == id)
                {
                    info.crveniOdigrao2 = true;
                    info.crveniRec2 = unetaRec;
                }
                info.semaphore.release();

                info.semaphore.acquireUninterruptibly();
                if (info.plaviOdigrao1 && info.plaviOdigrao2 && info.crveniOdigrao1 && info.crveniOdigrao2)
                {
                    GameController.KoDobija brojPoena1 = SlagalicaIgra.OdrediBrojPoena(info.plaviRec1, info.crveniRec1);
                    GameController.KoDobija brojPoena2 = SlagalicaIgra.OdrediBrojPoena(info.plaviRec2, info.crveniRec2);

                    IgraDAO.upisiPoeneMultiplayer(info.idIgre,
                            0,
                            ((brojPoena1.takmicar == Takmicar.PLAVI || brojPoena1.takmicar == Takmicar.OBA) ? brojPoena1.vrednost : 0),
                            ((brojPoena1.takmicar == Takmicar.CRVENI || brojPoena1.takmicar == Takmicar.OBA) ? brojPoena1.vrednost : 0));
                    IgraDAO.upisiPoeneMultiplayer(info.idIgre,
                            0,
                            ((brojPoena2.takmicar == Takmicar.PLAVI || brojPoena2.takmicar == Takmicar.OBA) ? brojPoena2.vrednost : 0),
                            ((brojPoena2.takmicar == Takmicar.CRVENI || brojPoena2.takmicar == Takmicar.OBA) ? brojPoena2.vrednost : 0));
                }
                info.semaphore.release();

                FacesContext.getCurrentInstance().getExternalContext().redirect("mojbroj.xhtml");
            }
        }
    }

    public void tajmerListener() throws IOException
    {
        preostaloVreme--;

        if (preostaloVreme == 0)
        {
            potvrdiRec();
        }
    }
}
