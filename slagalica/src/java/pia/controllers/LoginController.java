package pia.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pia.common.KorisniciDAO;
import pia.common.SessionUtils;
import pia.database.Korisnici;

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable {

    private boolean isSupervisor = false;
    private boolean isAdmin = false;
    private boolean isRegularUser = false;

    private String korisnickoIme;
    private String lozinka;

    private String greska;
    
    private int poenaPlavi = 0;
    private int poenaCrveni = 0;

    public LoginController() {
        HttpSession session = SessionUtils.getSession();
        Object tip = session.getAttribute("tip");

        if (tip != null) {
            if ((int) tip == 0) {
                isRegularUser = true;
            } else if ((int) tip == 1) {
                isSupervisor = true;
            } else if ((int) tip == 2) {
                isAdmin = true;
            }
        }
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsRegularUser() {
        return isRegularUser;
    }

    public void setIsRegularUser(boolean isRegularUser) {
        this.isRegularUser = isRegularUser;
    }

    public boolean isIsSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public String getGreska() {
        return greska;
    }

    public void setGreska(String greska) {
        this.greska = greska;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void prijaviSe() throws IOException {
        greska = "";

        if (korisnickoIme.equals("") || lozinka.equals("")) {
            greska = "Korisničko ime ili lozinka nije uneto.";
        }

        try {
            Korisnici k = KorisniciDAO.PrijaviSe(korisnickoIme, lozinka);
            int returnCode;
            if (k != null) {
                if (k.getOdobren() == 0)
                    returnCode = -2;
                else
                    returnCode = k.getPrivilegija();
            }
            else
                returnCode = -1;

            if (returnCode == -1) {
                greska = "Kombinacija korisničkog imena i lozinke nije validna.";
            } else if (returnCode == -2) // ni
            {
                greska = "Administrator nije odobrio zahtev za registraciju.";
            } else if (returnCode == 0) // korisnik
            {
                HttpSession sesija = SessionUtils.getSession();
                sesija.setAttribute("korisnickiID", k.getIdKorisnik());
                sesija.setAttribute("korisnickoIme", korisnickoIme);
                sesija.setAttribute("tip", 0);

                isAdmin = false;
                isSupervisor = false;

                isRegularUser = true;
                
                if (sesija.getAttribute("idIgre") != null)
                {
                    
                }

                FacesContext.getCurrentInstance().getExternalContext().redirect("igra/meni.xhtml");
            } else if (returnCode == 1) // supervizor
            {
                HttpSession sesija = SessionUtils.getSession();
                sesija.setAttribute("korisnickoIme", korisnickoIme);
                sesija.setAttribute("tip", 1);

                isRegularUser = false;
                isAdmin = false;

                isSupervisor = true;

                FacesContext.getCurrentInstance().getExternalContext().redirect("supervisor/supervisor.xhtml");
            } else if (returnCode == 2) // administrator
            {
                HttpSession sesija = SessionUtils.getSession();
                sesija.setAttribute("korisnickoIme", korisnickoIme);
                sesija.setAttribute("tip", 2);

                isRegularUser = false;
                isSupervisor = false;

                isAdmin = true;

                FacesContext.getCurrentInstance().getExternalContext().redirect("admin/administrator.xhtml");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void logout() throws IOException {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();

        String path = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

        if (isAdmin) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/index.xhtml");
        } else if (isSupervisor) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/index.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/index.xhtml");
        }

        isAdmin = false;
        isSupervisor = false;
        isRegularUser = false;
    }

    public String redirectToNovaSlagalica() {
        return "nova_slagalica";
    }

    public String redirectToNovaSpojnica() {
        return "nova_spojnica";
    }

    public String redirectToNovaAsocijacija() {
        return "nova_asocijacija";
    }
    
    public boolean igraUToku()
    {
        HttpSession sesija = SessionUtils.getSession();
        
        if (sesija != null && sesija.getAttribute("idIgre") != null)
            return true;
        else
            return false;
    }
}