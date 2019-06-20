package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import pia.common.SupervisorDAO;
import pia.database.AsocijacijaGrupa;
import pia.database.Spojnice;
import pia.database.SpojniceRec;

@ManagedBean(name = "supervisorController")
public class SupervisorController implements Serializable {

    private String novaSlagalicaRec;
    private String novaSlagalicaGreska;
    private String info;

    private String novaSpojnicaLevo1;
    private String novaSpojnicaDesno1;
    private String novaSpojnicaLevo2;
    private String novaSpojnicaDesno2;
    private String novaSpojnicaLevo3;
    private String novaSpojnicaDesno3;
    private String novaSpojnicaLevo4;
    private String novaSpojnicaDesno4;
    private String novaSpojnicaLevo5;
    private String novaSpojnicaDesno5;
    private String novaSpojnicaLevo6;
    private String novaSpojnicaDesno6;
    private String novaSpojnicaLevo7;
    private String novaSpojnicaDesno7;
    private String novaSpojnicaLevo8;
    private String novaSpojnicaDesno8;
    private String novaSpojnicaLevo9;
    private String novaSpojnicaDesno9;
    private String novaSpojnicaLevo10;
    private String novaSpojnicaDesno10;

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

    private String novaAsocijacijaGreska;
    private String novaSpojnicaGreska;

    public String getNovaSpojnicaLevo1() {
        return novaSpojnicaLevo1;
    }

    public void setNovaSpojnicaLevo1(String novaSpojnicaLevo1) {
        this.novaSpojnicaLevo1 = novaSpojnicaLevo1;
    }

    public String getNovaSpojnicaDesno1() {
        return novaSpojnicaDesno1;
    }

    public void setNovaSpojnicaDesno1(String novaSpojnicaDesno1) {
        this.novaSpojnicaDesno1 = novaSpojnicaDesno1;
    }

    public String getNovaSpojnicaLevo2() {
        return novaSpojnicaLevo2;
    }

    public void setNovaSpojnicaLevo2(String novaSpojnicaLevo2) {
        this.novaSpojnicaLevo2 = novaSpojnicaLevo2;
    }

    public String getNovaSpojnicaDesno2() {
        return novaSpojnicaDesno2;
    }

    public void setNovaSpojnicaDesno2(String novaSpojnicaDesno2) {
        this.novaSpojnicaDesno2 = novaSpojnicaDesno2;
    }

    public String getNovaSpojnicaLevo3() {
        return novaSpojnicaLevo3;
    }

    public void setNovaSpojnicaLevo3(String novaSpojnicaLevo3) {
        this.novaSpojnicaLevo3 = novaSpojnicaLevo3;
    }

    public String getNovaSpojnicaDesno3() {
        return novaSpojnicaDesno3;
    }

    public void setNovaSpojnicaDesno3(String novaSpojnicaDesno3) {
        this.novaSpojnicaDesno3 = novaSpojnicaDesno3;
    }

    public String getNovaSpojnicaLevo4() {
        return novaSpojnicaLevo4;
    }

    public void setNovaSpojnicaLevo4(String novaSpojnicaLevo4) {
        this.novaSpojnicaLevo4 = novaSpojnicaLevo4;
    }

    public String getNovaSpojnicaDesno4() {
        return novaSpojnicaDesno4;
    }

    public void setNovaSpojnicaDesno4(String novaSpojnicaDesno4) {
        this.novaSpojnicaDesno4 = novaSpojnicaDesno4;
    }

    public String getNovaSpojnicaLevo5() {
        return novaSpojnicaLevo5;
    }

    public void setNovaSpojnicaLevo5(String novaSpojnicaLevo5) {
        this.novaSpojnicaLevo5 = novaSpojnicaLevo5;
    }

    public String getNovaSpojnicaDesno5() {
        return novaSpojnicaDesno5;
    }

    public void setNovaSpojnicaDesno5(String novaSpojnicaDesno5) {
        this.novaSpojnicaDesno5 = novaSpojnicaDesno5;
    }

    public String getNovaSpojnicaLevo6() {
        return novaSpojnicaLevo6;
    }

    public void setNovaSpojnicaLevo6(String novaSpojnicaLevo6) {
        this.novaSpojnicaLevo6 = novaSpojnicaLevo6;
    }

    public String getNovaSpojnicaDesno6() {
        return novaSpojnicaDesno6;
    }

    public void setNovaSpojnicaDesno6(String novaSpojnicaDesno6) {
        this.novaSpojnicaDesno6 = novaSpojnicaDesno6;
    }

    public String getNovaSpojnicaLevo7() {
        return novaSpojnicaLevo7;
    }

    public void setNovaSpojnicaLevo7(String novaSpojnicaLevo7) {
        this.novaSpojnicaLevo7 = novaSpojnicaLevo7;
    }

    public String getNovaSpojnicaDesno7() {
        return novaSpojnicaDesno7;
    }

    public void setNovaSpojnicaDesno7(String novaSpojnicaDesno7) {
        this.novaSpojnicaDesno7 = novaSpojnicaDesno7;
    }

    public String getNovaSpojnicaLevo8() {
        return novaSpojnicaLevo8;
    }

    public void setNovaSpojnicaLevo8(String novaSpojnicaLevo8) {
        this.novaSpojnicaLevo8 = novaSpojnicaLevo8;
    }

    public String getNovaSpojnicaDesno8() {
        return novaSpojnicaDesno8;
    }

    public void setNovaSpojnicaDesno8(String novaSpojnicaDesno8) {
        this.novaSpojnicaDesno8 = novaSpojnicaDesno8;
    }

    public String getNovaSpojnicaLevo9() {
        return novaSpojnicaLevo9;
    }

    public void setNovaSpojnicaLevo9(String novaSpojnicaLevo9) {
        this.novaSpojnicaLevo9 = novaSpojnicaLevo9;
    }

    public String getNovaSpojnicaDesno9() {
        return novaSpojnicaDesno9;
    }

    public void setNovaSpojnicaDesno9(String novaSpojnicaDesno9) {
        this.novaSpojnicaDesno9 = novaSpojnicaDesno9;
    }

    public String getNovaSpojnicaLevo10() {
        return novaSpojnicaLevo10;
    }

    public void setNovaSpojnicaLevo10(String novaSpojnicaLevo10) {
        this.novaSpojnicaLevo10 = novaSpojnicaLevo10;
    }

    public String getNovaSpojnicaDesno10() {
        return novaSpojnicaDesno10;
    }

    public void setNovaSpojnicaDesno10(String novaSpojnicaDesno10) {
        this.novaSpojnicaDesno10 = novaSpojnicaDesno10;
    }

    public String getNovaSpojnicaGreska() {
        return novaSpojnicaGreska;
    }

    public void setNovaSpojnicaGreska(String novaSpojnicaGreska) {
        this.novaSpojnicaGreska = novaSpojnicaGreska;
    }

    public String getNovaAsocijacijaGreska() {
        return novaAsocijacijaGreska;
    }

    public void setNovaAsocijacijaGreska(String novaAsocijacijaGreska) {
        this.novaAsocijacijaGreska = novaAsocijacijaGreska;
    }

    public String getNovaAsocijacijaA1() {
        return novaAsocijacijaA1;
    }

    public void setNovaAsocijacijaA1(String novaAsocijacijaA1) {
        this.novaAsocijacijaA1 = novaAsocijacijaA1;
    }

    public String getNovaAsocijacijaA2() {
        return novaAsocijacijaA2;
    }

    public void setNovaAsocijacijaA2(String novaAsocijacijaA2) {
        this.novaAsocijacijaA2 = novaAsocijacijaA2;
    }

    public String getNovaAsocijacijaA3() {
        return novaAsocijacijaA3;
    }

    public void setNovaAsocijacijaA3(String novaAsocijacijaA3) {
        this.novaAsocijacijaA3 = novaAsocijacijaA3;
    }

    public String getNovaAsocijacijaA4() {
        return novaAsocijacijaA4;
    }

    public void setNovaAsocijacijaA4(String novaAsocijacijaA4) {
        this.novaAsocijacijaA4 = novaAsocijacijaA4;
    }

    public String getNovaAsocijacijaB1() {
        return novaAsocijacijaB1;
    }

    public void setNovaAsocijacijaB1(String novaAsocijacijaB1) {
        this.novaAsocijacijaB1 = novaAsocijacijaB1;
    }

    public String getNovaAsocijacijaB2() {
        return novaAsocijacijaB2;
    }

    public void setNovaAsocijacijaB2(String novaAsocijacijaB2) {
        this.novaAsocijacijaB2 = novaAsocijacijaB2;
    }

    public String getNovaAsocijacijaB3() {
        return novaAsocijacijaB3;
    }

    public void setNovaAsocijacijaB3(String novaAsocijacijaB3) {
        this.novaAsocijacijaB3 = novaAsocijacijaB3;
    }

    public String getNovaAsocijacijaB4() {
        return novaAsocijacijaB4;
    }

    public void setNovaAsocijacijaB4(String novaAsocijacijaB4) {
        this.novaAsocijacijaB4 = novaAsocijacijaB4;
    }

    public String getNovaAsocijacijaC1() {
        return novaAsocijacijaC1;
    }

    public void setNovaAsocijacijaC1(String novaAsocijacijaC1) {
        this.novaAsocijacijaC1 = novaAsocijacijaC1;
    }

    public String getNovaAsocijacijaC2() {
        return novaAsocijacijaC2;
    }

    public void setNovaAsocijacijaC2(String novaAsocijacijaC2) {
        this.novaAsocijacijaC2 = novaAsocijacijaC2;
    }

    public String getNovaAsocijacijaC3() {
        return novaAsocijacijaC3;
    }

    public void setNovaAsocijacijaC3(String novaAsocijacijaC3) {
        this.novaAsocijacijaC3 = novaAsocijacijaC3;
    }

    public String getNovaAsocijacijaC4() {
        return novaAsocijacijaC4;
    }

    public void setNovaAsocijacijaC4(String novaAsocijacijaC4) {
        this.novaAsocijacijaC4 = novaAsocijacijaC4;
    }

    public String getNovaAsocijacijaD1() {
        return novaAsocijacijaD1;
    }

    public void setNovaAsocijacijaD1(String novaAsocijacijaD1) {
        this.novaAsocijacijaD1 = novaAsocijacijaD1;
    }

    public String getNovaAsocijacijaD2() {
        return novaAsocijacijaD2;
    }

    public void setNovaAsocijacijaD2(String novaAsocijacijaD2) {
        this.novaAsocijacijaD2 = novaAsocijacijaD2;
    }

    public String getNovaAsocijacijaD3() {
        return novaAsocijacijaD3;
    }

    public void setNovaAsocijacijaD3(String novaAsocijacijaD3) {
        this.novaAsocijacijaD3 = novaAsocijacijaD3;
    }

    public String getNovaAsocijacijaD4() {
        return novaAsocijacijaD4;
    }

    public void setNovaAsocijacijaD4(String novaAsocijacijaD4) {
        this.novaAsocijacijaD4 = novaAsocijacijaD4;
    }

    public String getNovaAsocijacijaAResenje() {
        return novaAsocijacijaAResenje;
    }

    public void setNovaAsocijacijaAResenje(String novaAsocijacijaAResenje) {
        this.novaAsocijacijaAResenje = novaAsocijacijaAResenje;
    }

    public String getNovaAsocijacijaBResenje() {
        return novaAsocijacijaBResenje;
    }

    public void setNovaAsocijacijaBResenje(String novaAsocijacijaBResenje) {
        this.novaAsocijacijaBResenje = novaAsocijacijaBResenje;
    }

    public String getNovaAsocijacijaCResenje() {
        return novaAsocijacijaCResenje;
    }

    public void setNovaAsocijacijaCResenje(String novaAsocijacijaCResenje) {
        this.novaAsocijacijaCResenje = novaAsocijacijaCResenje;
    }

    public String getNovaAsocijacijaDResenje() {
        return novaAsocijacijaDResenje;
    }

    public void setNovaAsocijacijaDResenje(String novaAsocijacijaDResenje) {
        this.novaAsocijacijaDResenje = novaAsocijacijaDResenje;
    }

    public String getNovaAsocijacijaKonacnoResenje() {
        return novaAsocijacijaKonacnoResenje;
    }

    public void setNovaAsocijacijaKonacnoResenje(String novaAsocijacijaKonacnoResenje) {
        this.novaAsocijacijaKonacnoResenje = novaAsocijacijaKonacnoResenje;
    }

    public String getNovaSlagalicaRec() {
        return novaSlagalicaRec;
    }

    public void setNovaSlagalicaRec(String novaSlagalicaRec) {
        this.novaSlagalicaRec = novaSlagalicaRec;
    }

    public String getNovaSlagalicaGreska() {
        return novaSlagalicaGreska;
    }

    public void setNovaSlagalicaGreska(String novaSlagalicaGreska) {
        this.novaSlagalicaGreska = novaSlagalicaGreska;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void dodajNovuRec() throws IOException {
        info = "";
        novaSlagalicaGreska = "";

        if (novaSlagalicaRec.equals("")) {
            novaSlagalicaGreska = "Nova reč nije uneta.";
        } else {
            if (SupervisorDAO.NovaRec(novaSlagalicaRec)) {
                info = "Nova slagalica je uspešno dodata.";
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect("supervisor.xhtml");
            } else {
                novaSlagalicaGreska = "Uneta reč već postoji.";
            }
        }
    }

    public void vratiNazad() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect("supervisor.xhtml");
    }

    public void dodajNovuSpojnicu() throws IOException {
        SpojniceRec[] s = new SpojniceRec[10];

        if (novaSpojnicaLevo1.equals("") || novaSpojnicaDesno1.equals("")
                || novaSpojnicaLevo2.equals("") || novaSpojnicaDesno2.equals("")
                || novaSpojnicaLevo3.equals("") || novaSpojnicaDesno3.equals("")
                || novaSpojnicaLevo4.equals("") || novaSpojnicaDesno4.equals("")
                || novaSpojnicaLevo5.equals("") || novaSpojnicaDesno5.equals("")
                || novaSpojnicaLevo6.equals("") || novaSpojnicaDesno6.equals("")
                || novaSpojnicaLevo7.equals("") || novaSpojnicaDesno7.equals("")
                || novaSpojnicaLevo8.equals("") || novaSpojnicaDesno8.equals("")
                || novaSpojnicaLevo9.equals("") || novaSpojnicaDesno9.equals("")
                || novaSpojnicaLevo10.equals("") || novaSpojnicaDesno10.equals("")) {
            novaSpojnicaGreska = "Neko od polja nije uneto.";
            return;
        }
        
        s[0] = new SpojniceRec(null, novaSpojnicaLevo1, novaSpojnicaDesno1);
        s[1] = new SpojniceRec(null, novaSpojnicaLevo2, novaSpojnicaDesno2);
        s[2] = new SpojniceRec(null, novaSpojnicaLevo3, novaSpojnicaDesno3);
        s[3] = new SpojniceRec(null, novaSpojnicaLevo4, novaSpojnicaDesno4);
        s[4] = new SpojniceRec(null, novaSpojnicaLevo5, novaSpojnicaDesno5);
        s[5] = new SpojniceRec(null, novaSpojnicaLevo6, novaSpojnicaDesno6);
        s[6] = new SpojniceRec(null, novaSpojnicaLevo7, novaSpojnicaDesno7);
        s[7] = new SpojniceRec(null, novaSpojnicaLevo8, novaSpojnicaDesno8);
        s[8] = new SpojniceRec(null, novaSpojnicaLevo9, novaSpojnicaDesno9);
        s[9] = new SpojniceRec(null, novaSpojnicaLevo10, novaSpojnicaDesno10);

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if ((i != j && s[i].getLevaRec().equals(s[j].getLevaRec()))
                        || (i != j && s[i].getDesnaRec().equals(s[j].getDesnaRec()))) {
                    novaSpojnicaGreska = "Nije dozvoljeno imati u istoj koloni dve ili više istih reči.";
                    return;
                }
            }
        }

        if (SupervisorDAO.DodajSpojnicu(s)) {
            info = "Nova spojnica je uspešno dodata.";

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect("supervisor.xhtml");
        } else {
            novaSpojnicaGreska = "Greška prilikom dodavanja nova spojnice.";
        }
    }

    public void dodajNovuAsocijaciju() throws IOException {
        if (novaAsocijacijaA1.equals("") || novaAsocijacijaA2.equals("") || novaAsocijacijaA3.equals("") || novaAsocijacijaA4.equals("")
                || novaAsocijacijaB1.equals("") || novaAsocijacijaB2.equals("") || novaAsocijacijaB3.equals("") || novaAsocijacijaB4.equals("")
                || novaAsocijacijaC1.equals("") || novaAsocijacijaC2.equals("") || novaAsocijacijaC3.equals("") || novaAsocijacijaC4.equals("")
                || novaAsocijacijaD1.equals("") || novaAsocijacijaD2.equals("") || novaAsocijacijaD3.equals("") || novaAsocijacijaD4.equals("")
                || novaAsocijacijaAResenje.equals("") || novaAsocijacijaBResenje.equals("") || novaAsocijacijaCResenje.equals("") || novaAsocijacijaDResenje.equals("")
                || novaAsocijacijaKonacnoResenje.equals("")) {
            novaAsocijacijaGreska = "Neko od polja nije uneto";
            return;
        }

        AsocijacijaGrupa g1 = new AsocijacijaGrupa();
        g1.setRec1(novaAsocijacijaA1);
        g1.setRec2(novaAsocijacijaA2);
        g1.setRec3(novaAsocijacijaA3);
        g1.setRec4(novaAsocijacijaA4);
        g1.setResenjeGrupe(novaAsocijacijaAResenje);

        AsocijacijaGrupa g2 = new AsocijacijaGrupa();
        g2.setRec1(novaAsocijacijaB1);
        g2.setRec2(novaAsocijacijaB2);
        g2.setRec3(novaAsocijacijaB3);
        g2.setRec4(novaAsocijacijaB4);
        g2.setResenjeGrupe(novaAsocijacijaBResenje);

        AsocijacijaGrupa g3 = new AsocijacijaGrupa();
        g3.setRec1(novaAsocijacijaC1);
        g3.setRec2(novaAsocijacijaC2);
        g3.setRec3(novaAsocijacijaC3);
        g3.setRec4(novaAsocijacijaC4);
        g3.setResenjeGrupe(novaAsocijacijaCResenje);

        AsocijacijaGrupa g4 = new AsocijacijaGrupa();
        g4.setRec1(novaAsocijacijaD1);
        g4.setRec2(novaAsocijacijaD2);
        g4.setRec3(novaAsocijacijaD3);
        g4.setRec4(novaAsocijacijaD4);
        g4.setResenjeGrupe(novaAsocijacijaDResenje);

        if (SupervisorDAO.DodajAsocijaciju(g1, g2, g3, g4, novaAsocijacijaKonacnoResenje)) {
            info = "Nova asocijacija je uspešno dodata.";

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect("supervisor.xhtml");
        } else {
            novaSpojnicaGreska = "Greška prilikom dodavanja nova asocijacije.";
        }
    }
}