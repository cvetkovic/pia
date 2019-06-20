package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pia.common.AdministratorDAO;
import pia.common.Utils;
import pia.database.Asocijacija;
import pia.database.AsocijacijaGrupa;
import pia.database.IgraDana;
import pia.database.Korisnici;
import pia.database.Spojnice;
import pia.database.SpojniceRec;
import pia.igre.MojBrojIgra;
import pia.igre.SlagalicaIgra;

@ManagedBean(name = "administratorController")
@ViewScoped
public class AdministratorController implements Serializable
{

    List<Korisnici> spisakZahteva = null;
    List<SpojniceRec> spojnice;
    private int izabranaSpojnica = -1;
    private int izabranaAsocijacija = -1;

    private Date izabraniDatum;
    private String greska;

    private String novaAsocijacijaA1;
    private String novaAsocijacijaA2;
    private String novaAsocijacijaA3;
    private String novaAsocijacijaA4;
    private String novaAsocijacijaB1;
    private String novaAsocijacijaB2;
    private String novaAsocijacijaB3;
    private String novaAsocijacijaB4;
    private String novaAsocijacijaC1;
    private String novaAsocijacijaC2;
    private String novaAsocijacijaC3;
    private String novaAsocijacijaC4;
    private String novaAsocijacijaD1;
    private String novaAsocijacijaD2;
    private String novaAsocijacijaD3;
    private String novaAsocijacijaD4;
    private String novaAsocijacijaAResenje;
    private String novaAsocijacijaBResenje;
    private String novaAsocijacijaCResenje;
    private String novaAsocijacijaDResenje;
    private String novaAsocijacijaKonacnoResenje;

    public AdministratorController()
    {
        spisakZahteva = AdministratorDAO.DohvatiSpisakZahteva();
    }

    public Date getIzabraniDatum()
    {
        return izabraniDatum;
    }

    public void setSpisakZahteva(List<Korisnici> spisakZahteva)
    {
        this.spisakZahteva = spisakZahteva;
    }

    public void setSpojnice(List<SpojniceRec> spojnice)
    {
        this.spojnice = spojnice;
    }

    public void setIzabraniDatum(Date izabraniDatum)
    {
        this.izabraniDatum = izabraniDatum;
    }

    public void setGreska(String greska)
    {
        this.greska = greska;
    }

    public String getGreska()
    {
        return greska;
    }

    public int getIzabranaAsocijacija()
    {
        return izabranaAsocijacija;
    }

    public void setIzabranaAsocijacija(int izabranaAsocijacija)
    {
        this.izabranaAsocijacija = izabranaAsocijacija;
    }

    public String getNovaAsocijacijaA1()
    {
        return novaAsocijacijaA1;
    }

    public void setNovaAsocijacijaA1(String novaAsocijacijaA1)
    {
        this.novaAsocijacijaA1 = novaAsocijacijaA1;
    }

    public String getNovaAsocijacijaA2()
    {
        return novaAsocijacijaA2;
    }

    public void setNovaAsocijacijaA2(String novaAsocijacijaA2)
    {
        this.novaAsocijacijaA2 = novaAsocijacijaA2;
    }

    public String getNovaAsocijacijaA3()
    {
        return novaAsocijacijaA3;
    }

    public void setNovaAsocijacijaA3(String novaAsocijacijaA3)
    {
        this.novaAsocijacijaA3 = novaAsocijacijaA3;
    }

    public String getNovaAsocijacijaA4()
    {
        return novaAsocijacijaA4;
    }

    public void setNovaAsocijacijaA4(String novaAsocijacijaA4)
    {
        this.novaAsocijacijaA4 = novaAsocijacijaA4;
    }

    public String getNovaAsocijacijaB1()
    {
        return novaAsocijacijaB1;
    }

    public void setNovaAsocijacijaB1(String novaAsocijacijaB1)
    {
        this.novaAsocijacijaB1 = novaAsocijacijaB1;
    }

    public String getNovaAsocijacijaB2()
    {
        return novaAsocijacijaB2;
    }

    public void setNovaAsocijacijaB2(String novaAsocijacijaB2)
    {
        this.novaAsocijacijaB2 = novaAsocijacijaB2;
    }

    public String getNovaAsocijacijaB3()
    {
        return novaAsocijacijaB3;
    }

    public void setNovaAsocijacijaB3(String novaAsocijacijaB3)
    {
        this.novaAsocijacijaB3 = novaAsocijacijaB3;
    }

    public String getNovaAsocijacijaB4()
    {
        return novaAsocijacijaB4;
    }

    public void setNovaAsocijacijaB4(String novaAsocijacijaB4)
    {
        this.novaAsocijacijaB4 = novaAsocijacijaB4;
    }

    public String getNovaAsocijacijaC1()
    {
        return novaAsocijacijaC1;
    }

    public void setNovaAsocijacijaC1(String novaAsocijacijaC1)
    {
        this.novaAsocijacijaC1 = novaAsocijacijaC1;
    }

    public String getNovaAsocijacijaC2()
    {
        return novaAsocijacijaC2;
    }

    public void setNovaAsocijacijaC2(String novaAsocijacijaC2)
    {
        this.novaAsocijacijaC2 = novaAsocijacijaC2;
    }

    public String getNovaAsocijacijaC3()
    {
        return novaAsocijacijaC3;
    }

    public void setNovaAsocijacijaC3(String novaAsocijacijaC3)
    {
        this.novaAsocijacijaC3 = novaAsocijacijaC3;
    }

    public String getNovaAsocijacijaC4()
    {
        return novaAsocijacijaC4;
    }

    public void setNovaAsocijacijaC4(String novaAsocijacijaC4)
    {
        this.novaAsocijacijaC4 = novaAsocijacijaC4;
    }

    public String getNovaAsocijacijaD1()
    {
        return novaAsocijacijaD1;
    }

    public void setNovaAsocijacijaD1(String novaAsocijacijaD1)
    {
        this.novaAsocijacijaD1 = novaAsocijacijaD1;
    }

    public String getNovaAsocijacijaD2()
    {
        return novaAsocijacijaD2;
    }

    public void setNovaAsocijacijaD2(String novaAsocijacijaD2)
    {
        this.novaAsocijacijaD2 = novaAsocijacijaD2;
    }

    public String getNovaAsocijacijaD3()
    {
        return novaAsocijacijaD3;
    }

    public void setNovaAsocijacijaD3(String novaAsocijacijaD3)
    {
        this.novaAsocijacijaD3 = novaAsocijacijaD3;
    }

    public String getNovaAsocijacijaD4()
    {
        return novaAsocijacijaD4;
    }

    public void setNovaAsocijacijaD4(String novaAsocijacijaD4)
    {
        this.novaAsocijacijaD4 = novaAsocijacijaD4;
    }

    public String getNovaAsocijacijaAResenje()
    {
        return novaAsocijacijaAResenje;
    }

    public void setNovaAsocijacijaAResenje(String novaAsocijacijaAResenje)
    {
        this.novaAsocijacijaAResenje = novaAsocijacijaAResenje;
    }

    public String getNovaAsocijacijaBResenje()
    {
        return novaAsocijacijaBResenje;
    }

    public void setNovaAsocijacijaBResenje(String novaAsocijacijaBResenje)
    {
        this.novaAsocijacijaBResenje = novaAsocijacijaBResenje;
    }

    public String getNovaAsocijacijaCResenje()
    {
        return novaAsocijacijaCResenje;
    }

    public void setNovaAsocijacijaCResenje(String novaAsocijacijaCResenje)
    {
        this.novaAsocijacijaCResenje = novaAsocijacijaCResenje;
    }

    public String getNovaAsocijacijaDResenje()
    {
        return novaAsocijacijaDResenje;
    }

    public void setNovaAsocijacijaDResenje(String novaAsocijacijaDResenje)
    {
        this.novaAsocijacijaDResenje = novaAsocijacijaDResenje;
    }

    public String getNovaAsocijacijaKonacnoResenje()
    {
        return novaAsocijacijaKonacnoResenje;
    }

    public void setNovaAsocijacijaKonacnoResenje(String novaAsocijacijaKonacnoResenje)
    {
        this.novaAsocijacijaKonacnoResenje = novaAsocijacijaKonacnoResenje;
    }

    public int getIzabranaSpojnica()
    {
        return izabranaSpojnica;
    }

    public void setIzabranaSpojnica(int izabranaSpojnica)
    {
        this.izabranaSpojnica = izabranaSpojnica;
    }

    public List<SpojniceRec> getSpojnice()
    {
        return spojnice;
    }

    public List<Korisnici> getSpisakZahteva()
    {
        return spisakZahteva;
    }

    public void OdobriZahtev(int id) throws IOException
    {
        AdministratorDAO.OdobriZahtev(id);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void ObrisiZahtev(int id) throws IOException
    {
        AdministratorDAO.ObrisiZahtev(id);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void vratiNazad() throws IOException
    {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect("administrator.xhtml");
    }

    public List<Spojnice> SpisakSpojnica()
    {
        return AdministratorDAO.SpisakSpojnica();
    }

    public List<Asocijacija> SpisakAsocijacija()
    {
        return AdministratorDAO.SpisakAsocijacija();
    }

    public void UcitajSpojnicu(int id)
    {
        spojnice = AdministratorDAO.UcitajSpojnicu(id);
    }

    public void izborSpojniceListener()
    {
        if (izabranaSpojnica != -1)
        {
            UcitajSpojnicu(izabranaSpojnica);
        }
    }

    public void UcitajAsocijaciju(int id)
    {
        Asocijacija asocijacija = AdministratorDAO.UcitajAsocijaciju(id);
        if (asocijacija == null)
        {
            return;
        }

        int id1 = asocijacija.getIdGrupa1();
        int id2 = asocijacija.getIdGrupa2();
        int id3 = asocijacija.getIdGrupa3();
        int id4 = asocijacija.getIdGrupa4();

        AsocijacijaGrupa g1 = AdministratorDAO.UcitajGrupuAsocijacije(id1);
        AsocijacijaGrupa g2 = AdministratorDAO.UcitajGrupuAsocijacije(id2);
        AsocijacijaGrupa g3 = AdministratorDAO.UcitajGrupuAsocijacije(id3);
        AsocijacijaGrupa g4 = AdministratorDAO.UcitajGrupuAsocijacije(id4);

        novaAsocijacijaA1 = g1.getRec1();
        novaAsocijacijaA2 = g1.getRec2();
        novaAsocijacijaA3 = g1.getRec3();
        novaAsocijacijaA4 = g1.getRec4();

        novaAsocijacijaB1 = g1.getRec1();
        novaAsocijacijaB2 = g2.getRec2();
        novaAsocijacijaB3 = g3.getRec3();
        novaAsocijacijaB4 = g4.getRec4();

        novaAsocijacijaC1 = g1.getRec1();
        novaAsocijacijaC2 = g2.getRec2();
        novaAsocijacijaC3 = g3.getRec3();
        novaAsocijacijaC4 = g4.getRec4();

        novaAsocijacijaD1 = g1.getRec1();
        novaAsocijacijaD2 = g2.getRec2();
        novaAsocijacijaD3 = g3.getRec3();
        novaAsocijacijaD4 = g4.getRec4();

        novaAsocijacijaAResenje = g1.getResenjeGrupe();
        novaAsocijacijaBResenje = g2.getResenjeGrupe();
        novaAsocijacijaCResenje = g3.getResenjeGrupe();
        novaAsocijacijaDResenje = g4.getResenjeGrupe();

        novaAsocijacijaKonacnoResenje = asocijacija.getResenjeAsocijacije();
    }

    public void izborAsocijacijeListener()
    {
        if (izabranaAsocijacija != -1)
        {
            UcitajAsocijaciju(izabranaAsocijacija);
        }
    }

    public void DodajIgruDana() throws IOException
    {
        if (izabranaSpojnica != -1 && izabranaSpojnica != -1 && izabraniDatum != null)
        {
            IgraDana igraDana = new IgraDana();
            
            String[] slova = SlagalicaIgra.GenerisiSlova();
            String ss = "";
            for (int i = 0 ; i < 2 * slova.length - 1; i++)
                if (i % 2 == 0)
                    ss += slova[i / 2];
                else 
                    ss += ",";
            igraDana.setSlagalica(ss);
            
            int trazeni = Utils.GenerisiBroj(0, 999);
            int[] brojevi = MojBrojIgra.GenerisiBrojeve();
            String mojBroj = Integer.toString(trazeni);
            for (int i = 0; i < brojevi.length; i++)
                mojBroj += ("," + Integer.toString(brojevi[i]));
            igraDana.setMojBroj(mojBroj);
            int skocko = 0;
            for (int i = 0; i < 4; i++)
                skocko = (skocko * 10) + Utils.GenerisiBroj(0, 5);
            igraDana.setSkocko(skocko);
            igraDana.setIdSpojnica(izabranaSpojnica);
            igraDana.setIdAsocijacija(izabranaAsocijacija);
            igraDana.setDatum(new java.sql.Date(izabraniDatum.getTime()));

            if (AdministratorDAO.dodajIgruDana(igraDana))
                FacesContext.getCurrentInstance().getExternalContext().redirect("administrator.xhtml");
            else
                greska = "Igra dana je već definisana i ne može biti promenjena jer je barem jedan korisnik već odigrao.";
        }
    }
}
