package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.IgraDAO;
import pia.common.IgraDAO.StatistikaDana;
import pia.common.SessionUtils;
import pia.common.Utils;
import pia.controllers.GameController.SkockoInfo.Kombinacija;
import pia.database.IgraDana;
import pia.database.Partija;
import pia.igre.MojBrojIgra;
import pia.igre.SkockoIgra;
import pia.igre.SlagalicaIgra;

@ManagedBean()
@ViewScoped
public class KorisnikController implements Serializable
{

    int idIgre = -1;
    boolean igraSeCeka = false;
    boolean mozeDalje = false;
    String poruka;
    String igraDanaStatus;
    boolean prikljuciSe = false;
    List<Partija> spisakPartija = new LinkedList<>();
    int izabranaPartija = -1;

    @ManagedProperty(value = "#{gameController}")
    GameController gameController;

    public void setGameController(GameController gameController)
    {
        this.gameController = gameController;
    }

    public String getIgraDanaStatus()
    {
        return igraDanaStatus;
    }

    public boolean isIgraSeCeka()
    {
        return igraSeCeka;
    }

    public void setIgraSeCeka(boolean igraSeCeka)
    {
        this.igraSeCeka = igraSeCeka;
    }

    public boolean isMozeDalje()
    {
        return mozeDalje;
    }

    public void setMozeDalje(boolean mozeDalje)
    {
        this.mozeDalje = mozeDalje;
    }

    public String getPoruka()
    {
        return poruka;
    }

    public void setPoruka(String poruka)
    {
        this.poruka = poruka;
    }

    public int getIdIgre()
    {
        return idIgre;
    }

    public void setIdIgre(int idIgre)
    {
        this.idIgre = idIgre;
    }

    public boolean isPrikljuciSe()
    {
        return prikljuciSe;
    }

    public void setPrikljuciSe(boolean prikljuciSe)
    {
        this.prikljuciSe = prikljuciSe;
    }

    public List<Partija> getSpisakPartija()
    {
        return spisakPartija;
    }

    public void setSpisakPartija(List<Partija> spisakPartija)
    {
        this.spisakPartija = spisakPartija;
    }

    public int getIzabranaPartija()
    {
        return izabranaPartija;
    }

    public void setIzabranaPartija(int izabranaPartija)
    {
        this.izabranaPartija = izabranaPartija;
    }

    List<StatistikaDana> statistikaDana;
    List<IgraDAO.StatistikaProtivDrugih> statistikaProtivDrugih;

    public List<StatistikaDana> getStatistikaDana()
    {
        return statistikaDana;
    }

    public List<IgraDAO.StatistikaProtivDrugih> getStatistikaProtivDrugih()
    {
        return statistikaProtivDrugih;
    }

    public KorisnikController()
    {
        HttpSession sesija = SessionUtils.getSession();

        if (sesija != null)
        {
            sesija.removeAttribute("idIgre");
            sesija.removeAttribute("samostalno");
            sesija.removeAttribute("multiplayerIdPartija");
            sesija.removeAttribute("samostalno");
            sesija.removeAttribute("idIgre");
            sesija.removeAttribute("mestoKorisnika");

            poruka = "";
            igraDanaStatus = "";

            if (sesija.getAttribute("korisnickiID") != null)
            {
                statistikaDana = IgraDAO.dohvatiStatistikuDana((int) sesija.getAttribute("korisnickiID"));
                statistikaProtivDrugih = IgraDAO.dohvatiStatistikuProtivDrugih((int) sesija.getAttribute("korisnickiID"));
            }
        }
    }

    public void NapraviPartiju(boolean samostalno) throws IOException
    {
        if (!igraSeCeka || samostalno)
        {
            HttpSession sesija = SessionUtils.getSession();
            if (sesija == null || sesija.getAttribute("korisnickiID") == null)
            {
                throw new RuntimeException("greska");
            }

            if (samostalno)
            {
                int plavi = (int) sesija.getAttribute("korisnickiID");

                int status = IgraDAO.odigraoIgruDana(plavi);

                if (status == 0)
                {
                    idIgre = IgraDAO.napraviPartiju(plavi, samostalno, new java.sql.Date(new Date().getTime()));

                    sesija.setAttribute("idIgre", idIgre);
                    sesija.setAttribute("samostalno", 1);
                    napraviSamostalnuPartiju();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("slagalica.xhtml");
                    igraSeCeka = false;
                }
                else if (status == 1)
                {
                    igraDanaStatus = "Igra dana za današnji dan nije definisana.";
                }
                else if (status == 2)
                {
                    igraDanaStatus = "Već ste odigrali igru dana.";
                }
            }
            else
            {
                int plavi = (int) sesija.getAttribute("korisnickiID");
                idIgre = IgraDAO.napraviPartiju(plavi, samostalno, new java.sql.Date(new Date().getTime()));

                sesija.setAttribute("idIgre", idIgre);

                GameController.PartijaInfo pinfo = new GameController.PartijaInfo(idIgre, plavi, -1);
                gameController.partijeUToku.put(idIgre, pinfo);

                poruka = "Nova igra je uspešno napravljena. Čekanje dok se crveni igrač ne pojavi...";
                igraSeCeka = true;
            }
        }
    }

    public GameController getGameController()
    {
        return gameController;
    }

    public void cekajDrugogIgraca() throws Exception
    {
        HttpSession sesija = SessionUtils.getSession();

        if (!(sesija == null || sesija.getAttribute("korisnickiID") == null))
        {
            int plavi = (int) sesija.getAttribute("korisnickiID");

            if (gameController.partijeUToku.containsKey(idIgre))
            {
                if (gameController.partijeUToku.get(idIgre).crveniTakmicar != -1)
                {
                    sesija.setAttribute("multiplayerIdPartija", idIgre);
                    sesija.setAttribute("samostalno", 0);
                    sesija.setAttribute("idIgre", idIgre);
                    sesija.setAttribute("mestoKorisnika", 0);

                    FacesContext.getCurrentInstance().getExternalContext().redirect("slagalica.xhtml");
                }
            }
        }
    }

    public void zapocniIgru() throws Exception
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("slagalica.xhtml");
    }

    public void prikljuciSe()
    {
        prikljuciSe = true;
    }

    public void izborPartije()
    {
        HttpSession sesija = SessionUtils.getSession();
        if (!(sesija == null || sesija.getAttribute("korisnickiID") == null))
        {
            int plavi = (int) sesija.getAttribute("korisnickiID");
            spisakPartija = IgraDAO.spisakPartijaNaCekanju(plavi);
            if (idIgre != -1)
            {
                spisakPartija.removeIf(p -> p.equals(Integer.toString(plavi)));
            }
        }
    }

    public void dugmeDodajDrugogIgraca() throws IOException
    {
        if (izabranaPartija != -1)
        {
            HttpSession sesija = SessionUtils.getSession();
            if (!(sesija == null || sesija.getAttribute("korisnickiID") == null))
            {
                int crveni = (int) sesija.getAttribute("korisnickiID");

                ///////////////////////////////////
                GameController.SlagalicaInfo info = new GameController.SlagalicaInfo();
                info.idIgre = izabranaPartija;
                info.reci1 = SlagalicaIgra.GenerisiSlova();
                info.reci2 = SlagalicaIgra.GenerisiSlova();
                info.idPlavi = gameController.partijeUToku.get(izabranaPartija).plaviTakmicar;
                info.idCrveni = crveni;
                gameController.slagaliceUToku.put(izabranaPartija, info);

                ///////////////////////////////////
                GameController.MojBrojInfo mojBrojInfo = new GameController.MojBrojInfo();
                mojBrojInfo.idIgre = izabranaPartija;
                mojBrojInfo.brojevi1 = MojBrojIgra.GenerisiBrojeve();
                mojBrojInfo.brojevi2 = MojBrojIgra.GenerisiBrojeve();
                mojBrojInfo.ciljaniBroj1 = Utils.GenerisiBroj(0, 999);
                mojBrojInfo.ciljaniBroj2 = Utils.GenerisiBroj(0, 999);
                mojBrojInfo.idPlavi = gameController.partijeUToku.get(izabranaPartija).plaviTakmicar;
                mojBrojInfo.idCrveni = crveni;
                gameController.mojBrojUToku.put(izabranaPartija, mojBrojInfo);

                GameController.SkockoInfo skockoInfo = new GameController.SkockoInfo();
                skockoInfo.dobitnaKombinacija1 = SkockoIgra.GenerisiKombinaciju();
                skockoInfo.dobitnaKombinacija2 = SkockoIgra.GenerisiKombinaciju();
                skockoInfo.idPlavi = gameController.partijeUToku.get(izabranaPartija).plaviTakmicar;
                skockoInfo.idCrveni = crveni;
                gameController.skockoUToku.put(izabranaPartija, skockoInfo);

                GameController.SpojniceInfo spojniceInfo = new GameController.SpojniceInfo();
                spojniceInfo.kombinacija1 = IgraDAO.dohvatiSlucajnuSpojnicu();
                do
                {
                    spojniceInfo.kombinacija2 = IgraDAO.dohvatiSlucajnuSpojnicu();
                }
                while (spojniceInfo.kombinacija1 == spojniceInfo.kombinacija2);
                spojniceInfo.leviZaPrikaz1 = GameController.ispremestajSpojnicu(spojniceInfo.kombinacija1, true);
                spojniceInfo.desniZaPrikaz1 = GameController.ispremestajSpojnicu(spojniceInfo.kombinacija1, false);
                spojniceInfo.leviZaPrikaz2 = GameController.ispremestajSpojnicu(spojniceInfo.kombinacija2, true);
                spojniceInfo.desniZaPrikaz2 = GameController.ispremestajSpojnicu(spojniceInfo.kombinacija2, false);
                spojniceInfo.idPlavi = gameController.partijeUToku.get(izabranaPartija).plaviTakmicar;
                spojniceInfo.idCrveni = crveni;
                gameController.spojniceUToku.put(izabranaPartija, spojniceInfo);

                GameController.AsocijacijaInfo asocijacijaInfo = IgraDAO.dohvatiAsocijaciju();
                asocijacijaInfo.idPlavi = gameController.partijeUToku.get(izabranaPartija).plaviTakmicar;
                asocijacijaInfo.idCrveni = crveni;
                asocijacijaInfo.idIgre = izabranaPartija;
                gameController.asocijacijaUToku.put(izabranaPartija, asocijacijaInfo);

                IgraDAO.dodajDrugogIgraca(izabranaPartija, crveni);

                sesija.setAttribute("idIgre", izabranaPartija);
                sesija.setAttribute("samostalno", 0);
                sesija.setAttribute("mestoKorisnika", 1);
                // ovo redirektuje plavog igraca
                gameController.partijeUToku.get(izabranaPartija).crveniTakmicar = crveni;
                // TODO: skocko -> slagalica after testing ends
                FacesContext.getCurrentInstance().getExternalContext().redirect("slagalica.xhtml");
            }
        }
    }

    private void napraviSamostalnuPartiju() throws IOException
    {
        HttpSession sesija = SessionUtils.getSession();

        if (!(sesija == null || sesija.getAttribute("korisnickiID") == null))
        {
            int plavi = (int) sesija.getAttribute("korisnickiID");

            IgraDana igraDana = IgraDAO.dohvatiIgruDana();

            ///////////////////////////////////
            GameController.SlagalicaInfo info = new GameController.SlagalicaInfo();
            info.idIgre = idIgre;
            info.reci1 = deserijalizujSlagalicu(igraDana.getSlagalica());
            info.idPlavi = plavi;
            gameController.slagaliceUToku.put(idIgre, info);

            ///////////////////////////////////
            GameController.MojBrojInfo mojBrojInfo = new GameController.MojBrojInfo();
            mojBrojInfo.idIgre = idIgre;
            mojBrojInfo.brojevi1 = deserijalizujPonudeneBrojeve(igraDana.getMojBroj());
            mojBrojInfo.ciljaniBroj1 = deserijalizujTrazeniBroj(igraDana.getMojBroj());
            mojBrojInfo.idPlavi = plavi;
            gameController.mojBrojUToku.put(idIgre, mojBrojInfo);

            GameController.SkockoInfo skockoInfo = new GameController.SkockoInfo();
            skockoInfo.dobitnaKombinacija1 = deserijalizujSkocka(igraDana.getSkocko());
            skockoInfo.idPlavi = plavi;
            gameController.skockoUToku.put(idIgre, skockoInfo);

            GameController.SpojniceInfo spojniceInfo = new GameController.SpojniceInfo();
            spojniceInfo.kombinacija1 = IgraDAO.dohvatiKonkretnuSpojnicu(igraDana.getIdSpojnica());
            spojniceInfo.leviZaPrikaz1 = GameController.ispremestajSpojnicu(spojniceInfo.kombinacija1, true);
            spojniceInfo.desniZaPrikaz1 = GameController.ispremestajSpojnicu(spojniceInfo.kombinacija1, false);
            spojniceInfo.idPlavi = plavi;
            gameController.spojniceUToku.put(idIgre, spojniceInfo);

            GameController.AsocijacijaInfo asocijacijaInfo = IgraDAO.dohvatiAsocijaciju(igraDana.getIdAsocijacija());
            asocijacijaInfo.idPlavi = plavi;
            asocijacijaInfo.idIgre = idIgre;
            gameController.asocijacijaUToku.put(idIgre, asocijacijaInfo);

            sesija.setAttribute("idIgre", idIgre);
            sesija.setAttribute("samostalno", 1);
            sesija.setAttribute("mestoKorisnika", 0);
        }
    }

    private String[] deserijalizujSlagalicu(String tekst)
    {
        return tekst.split(",");
    }

    private int[] deserijalizujPonudeneBrojeve(String tekst)
    {
        String[] brojeviString = tekst.split(",");
        int[] rezultat = new int[6];

        for (int i = 1; i < brojeviString.length; i++)
        {
            rezultat[i - 1] = Integer.parseInt(brojeviString[i]);
        }

        return rezultat;
    }

    private int deserijalizujTrazeniBroj(String tekst)
    {
        return Integer.parseInt(tekst.split(",")[0]);
    }

    private Kombinacija deserijalizujSkocka(int broj)
    {
        int[] k = new int[4];
        int i = 0;

        while (broj != 0)
        {
            int b = broj % 10;
            broj /= 10;

            k[i++] = b;
        }

        return new Kombinacija(k[0], k[1], k[2], k[3]);
    }
}
