package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.SessionUtils;
import pia.igre.MojBrojIgra;
import pia.igre.SlagalicaIgra;
import pia.igre.MojBrojIgra.Symbol;

@ManagedBean(name = "mojBrojController")
@ViewScoped
public class MojBrojController implements Serializable
{

    int[] brojevi =
    {
        1, 3, 7, 5, 15, 75
    };
    boolean[] dozvoljeno =
    {
        true, true, true, true, true, true
    };
    List<String> unetiIzraz = new LinkedList<>();
    String unetiIzrazString = "";

    int trazeniBroj = -1;
    GameController gameController;
    GameController.MojBrojInfo info;

    boolean operatorDodat = true;
    boolean slovoDodato = false;

    int identifikatorKorisnika;

    int idIgre;
    int samostalno;
    boolean poslato = false;

    int prijavljeniBroj;

    public int getPrijavljeniBroj()
    {
        return prijavljeniBroj;
    }

    public void setPrijavljeniBroj(int prijavljeniBroj)
    {
        this.prijavljeniBroj = prijavljeniBroj;
    }

    int preostaloVreme;
    boolean ucitano1, ucitano2;

    public MojBrojController()
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
                info = gameController.mojBrojUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");
                identifikatorKorisnika = id;
                brojevi = info.brojevi1;
                trazeniBroj = info.ciljaniBroj1;
                preostaloVreme = 60;
            }
            else
            {
                info = gameController.mojBrojUToku.get(idIgre);
                int id = (int) session.getAttribute("korisnickiID");
                identifikatorKorisnika = id;

                if (info.idPlavi == id)
                {
                    if (!info.plaviOdigrao1)
                    {
                        brojevi = info.brojevi1;
                        trazeniBroj = info.ciljaniBroj1;
                    }
                    else if (!info.plaviOdigrao2)
                    {
                        brojevi = info.brojevi2;
                        trazeniBroj = info.ciljaniBroj2;
                    }
                }
                else if (info.idCrveni == id)
                {
                    if (!info.crveniOdigrao1)
                    {
                        brojevi = info.brojevi1;
                        trazeniBroj = info.ciljaniBroj1;
                    }
                    else if (!info.crveniOdigrao2)
                    {
                        brojevi = info.brojevi2;
                        trazeniBroj = info.ciljaniBroj2;
                        //info.preostaloVreme = 60;
                    }
                }

                if (!ucitano1 && brojevi == info.brojevi1)
                {
                    ucitano1 = true;
                    preostaloVreme = 60;
                }
                else if (!ucitano2 && brojevi == info.brojevi2)
                {
                    ucitano1 = true;
                    preostaloVreme = 60;
                }
            }
        }
    }

    public int getTrazeniBroj()
    {
        return trazeniBroj;
    }

    public void setTrazeniBroj(int trazeniBroj)
    {
        this.trazeniBroj = trazeniBroj;
    }

    public int getPreostaloVreme()
    {
        return preostaloVreme;
    }

    public boolean isPoslato()
    {
        return poslato;
    }

    public boolean[] getDozvoljeno()
    {
        return dozvoljeno;
    }

    public void setDozvoljeno(boolean[] dozvoljeno)
    {
        this.dozvoljeno = dozvoljeno;
    }

    public void dodajBroj(int index)
    {
        if (operatorDodat)
        {
            operatorDodat = false;
            slovoDodato = !slovoDodato;

            unetiIzraz.add(Integer.toString(brojevi[index]));
            dozvoljeno[index] = !dozvoljeno[index];

            unetiIzrazString = "";
            for (int i = 0; i < unetiIzraz.size(); i++)
            {
                unetiIzrazString += unetiIzraz.get(i);
            }
        }
    }

    public String getUnetiIzrazString()
    {
        return unetiIzrazString;
    }

    public void setUnetiIzrazString(String unetiIzrazString)
    {
        this.unetiIzrazString = unetiIzrazString;
    }

    public void dodajOperator(String operator)
    {
        if (slovoDodato || operator.equals("(") || operator.equals(")") || (!unetiIzraz.isEmpty() && ((String) unetiIzraz.get(unetiIzraz.size() - 1)).equals(")")))
        {
            operatorDodat = true;
            slovoDodato = false;

            unetiIzraz.add(operator);

            unetiIzrazString = "";
            for (int i = 0; i < unetiIzraz.size(); i++)
            {
                unetiIzrazString += unetiIzraz.get(i);
            }
        }
    }

    public int[] getBrojevi()
    {
        return brojevi;
    }

    public void setBrojevi(int[] brojevi)
    {
        this.brojevi = brojevi;
    }

    public List<String> getUnetiIzraz()
    {
        return unetiIzraz;
    }

    public void setUnetiIzraz(List<String> unetiIzraz)
    {
        this.unetiIzraz = unetiIzraz;
    }

    public void ukloniBroj()
    {
        if (!unetiIzraz.isEmpty())
        {
            String token = unetiIzraz.get(unetiIzraz.size() - 1);
            unetiIzraz.remove(unetiIzraz.size() - 1);
            if (token.length() == 1 && pia.igre.MojBrojIgra.IsOperator(token.charAt(0)))
            {
                if (!unetiIzraz.isEmpty() && pia.igre.MojBrojIgra.IsOperator(((String) unetiIzraz.get(unetiIzraz.size() - 1)).charAt(0)))
                {
                    operatorDodat = true;
                    slovoDodato = false;
                }
                else
                {
                    operatorDodat = false;
                    slovoDodato = true;
                }
            }
            else
            {
                for (int i = 0; i < brojevi.length; i++)
                {
                    if (brojevi[i] == Integer.parseInt(token) && dozvoljeno[i] == false)
                    {
                        dozvoljeno[i] = !dozvoljeno[i];

                        operatorDodat = true;
                        slovoDodato = false;

                        break;
                    }
                }
            }

            unetiIzrazString = "";
            for (int i = 0; i < unetiIzraz.size(); i++)
            {
                unetiIzrazString += unetiIzraz.get(i);
            }
        }
    }

    public void potvrdiBroj() throws IOException
    {
        poslato = true;
        int dobijeniBroj = 0;

        try
        {
            List<Symbol> tokenizedExpression = MojBrojIgra.TokenizeExpression(unetiIzrazString);
            List<Symbol> postfix = MojBrojIgra.Parse(tokenizedExpression);
            dobijeniBroj = MojBrojIgra.CalculateExpression(postfix);
        }
        catch (RuntimeException ex)
        {
            dobijeniBroj = -1;
        }

        if (samostalno == 1)
        {
            HttpSession session = SessionUtils.getSession();
            GameController.MojBrojInfo info = gameController.mojBrojUToku.get(idIgre);
            int id = (int) session.getAttribute("korisnickiID");

            GameController.KoDobija brojPoena = MojBrojIgra.OdrediBrojPoena(info.ciljaniBroj1,
                    prijavljeniBroj,
                    dobijeniBroj,
                    -1,
                    -1);

            IgraDAO.upisiPoeneMultiplayer(info.idIgre,
                    1,
                    brojPoena.vrednost,
                    0);

            FacesContext.getCurrentInstance().getExternalContext().redirect("skocko.xhtml");
        }
        else if (samostalno == 0)
        {
            HttpSession session = SessionUtils.getSession();
            GameController.MojBrojInfo info = gameController.mojBrojUToku.get(idIgre);
            int id = (int) session.getAttribute("korisnickiID");

            if (brojevi == info.brojevi1)
            {
                info.semaphore.acquireUninterruptibly();
                if (info.idPlavi == id)
                {
                    info.plaviOdigrao1 = true;
                    info.plaviBroj1 = dobijeniBroj;
                    info.prijavljeniPlavi1 = prijavljeniBroj;
                }
                else if (info.idCrveni == id)
                {
                    info.crveniOdigrao1 = true;
                    info.crveniBroj1 = dobijeniBroj;
                    info.prijavljeniCrveni1 = prijavljeniBroj;
                }
                info.semaphore.release();

                FacesContext.getCurrentInstance().getExternalContext().redirect("mojbroj.xhtml");
            }
            else if (brojevi == info.brojevi2)
            {
                info.semaphore.acquireUninterruptibly();
                if (info.idPlavi == id)
                {
                    info.plaviOdigrao2 = true;
                    info.plaviBroj2 = dobijeniBroj;
                    info.prijavljeniPlavi2 = prijavljeniBroj;
                }
                else if (info.idCrveni == id)
                {
                    info.crveniOdigrao2 = true;
                    info.crveniBroj2 = dobijeniBroj;
                    info.prijavljeniCrveni2 = prijavljeniBroj;
                }
                info.semaphore.release();

                info.semaphore.acquireUninterruptibly();
                if (info.plaviOdigrao1 && info.plaviOdigrao2 && info.crveniOdigrao1 && info.crveniOdigrao2)
                {
                    GameController.KoDobija brojPoena1 = MojBrojIgra.OdrediBrojPoena(info.ciljaniBroj1,
                            info.prijavljeniPlavi1,
                            info.plaviBroj1,
                            info.prijavljeniCrveni1,
                            info.crveniBroj1);
                    GameController.KoDobija brojPoena2 = MojBrojIgra.OdrediBrojPoena(info.ciljaniBroj2,
                            info.prijavljeniPlavi2,
                            info.plaviBroj2,
                            info.prijavljeniCrveni2,
                            info.crveniBroj2);

                    IgraDAO.upisiPoeneMultiplayer(info.idIgre,
                            1,
                            ((brojPoena1.takmicar == GameController.KoDobija.Takmicar.PLAVI || brojPoena1.takmicar == GameController.KoDobija.Takmicar.OBA) ? brojPoena1.vrednost : 0),
                            ((brojPoena1.takmicar == GameController.KoDobija.Takmicar.CRVENI || brojPoena1.takmicar == GameController.KoDobija.Takmicar.OBA) ? brojPoena1.vrednost : 0));
                    IgraDAO.upisiPoeneMultiplayer(info.idIgre,
                            1,
                            ((brojPoena2.takmicar == GameController.KoDobija.Takmicar.PLAVI || brojPoena2.takmicar == GameController.KoDobija.Takmicar.OBA) ? brojPoena2.vrednost : 0),
                            ((brojPoena2.takmicar == GameController.KoDobija.Takmicar.CRVENI || brojPoena2.takmicar == GameController.KoDobija.Takmicar.OBA) ? brojPoena2.vrednost : 0));
                }
                info.semaphore.release();
            }
        }
    }

    public void tajmerListener() throws IOException
    {
        if (brojevi == info.brojevi2 && info.plaviOdigrao2 && info.crveniOdigrao2)
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("skocko.xhtml");
        }

        if (!poslato)
        {
            preostaloVreme--;
        }

        if (preostaloVreme == 0)
        {
            potvrdiBroj();
        }
    }
}
