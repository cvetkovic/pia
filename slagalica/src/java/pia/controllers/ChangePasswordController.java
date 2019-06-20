package pia.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import pia.common.KorisniciDAO;

@ManagedBean(name = "changePasswordController")
@ViewScoped
public class ChangePasswordController implements Serializable
{
    private String korisnickoIme;
    private String staraLozinka;
    private String lozinka;
    private String lozinkaPotvrda;
    
    private String greska;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getLozinkaPotvrda() {
        return lozinkaPotvrda;
    }

    public void setLozinkaPotvrda(String lozinkaPotvrda) {
        this.lozinkaPotvrda = lozinkaPotvrda;
    }

    public String getGreska() {
        return greska;
    }

    public void setGreska(String greska) {
        this.greska = greska;
    }
    
    public String promeniLozinku()
    {
        try
        {
            if (korisnickoIme.equals("") || 
                staraLozinka.equals("") ||
                lozinka.equals("") ||
                lozinkaPotvrda.equals(""))
            {
                greska = "Подаци нису унети.";
                return "";
            }
            else
            {
                if (lozinka.equals(lozinkaPotvrda))
                {
                    if (!KorisniciDAO.PromeniLozinku(korisnickoIme, staraLozinka, lozinka))
                    {
                        greska = "Стари подаци нису валидни.";
                        return "";
                    }
                }
                else
                {
                    greska = "Нове лозинке се не поклапају.";
                    return "";
                }
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return "index.xhtml";
    }
}