package pia.database;
// Generated May 31, 2019 8:26:26 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Korisnici generated by hbm2java
 */
public class Korisnici  implements java.io.Serializable {


     private Integer idKorisnik;
     private String ime;
     private String prezime;
     private String email;
     private String zanimanje;
     private String korisnickoIme;
     private String lozinka;
     private Integer pol;
     private Date datumRodenja;
     private String slika;
     private int privilegija;
     private byte odobren;

    public Korisnici() {
    }

	
    public Korisnici(String email, String korisnickoIme, String lozinka, int privilegija, byte odobren) {
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.privilegija = privilegija;
        this.odobren = odobren;
    }
    public Korisnici(String ime, String prezime, String email, String zanimanje, String korisnickoIme, String lozinka, Integer pol, Date datumRodenja, String slika, int privilegija, byte odobren) {
       this.ime = ime;
       this.prezime = prezime;
       this.email = email;
       this.zanimanje = zanimanje;
       this.korisnickoIme = korisnickoIme;
       this.lozinka = lozinka;
       this.pol = pol;
       this.datumRodenja = datumRodenja;
       this.slika = slika;
       this.privilegija = privilegija;
       this.odobren = odobren;
    }
   
    public Integer getIdKorisnik() {
        return this.idKorisnik;
    }
    
    public void setIdKorisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }
    public String getIme() {
        return this.ime;
    }
    
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPrezime() {
        return this.prezime;
    }
    
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getZanimanje() {
        return this.zanimanje;
    }
    
    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }
    public String getKorisnickoIme() {
        return this.korisnickoIme;
    }
    
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
    public String getLozinka() {
        return this.lozinka;
    }
    
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    public Integer getPol() {
        return this.pol;
    }
    
    public void setPol(Integer pol) {
        this.pol = pol;
    }
    public Date getDatumRodenja() {
        return this.datumRodenja;
    }
    
    public void setDatumRodenja(Date datumRodenja) {
        this.datumRodenja = datumRodenja;
    }
    public String getSlika() {
        return this.slika;
    }
    
    public void setSlika(String slika) {
        this.slika = slika;
    }
    public int getPrivilegija() {
        return this.privilegija;
    }
    
    public void setPrivilegija(int privilegija) {
        this.privilegija = privilegija;
    }
    public byte getOdobren() {
        return this.odobren;
    }
    
    public void setOdobren(byte odobren) {
        this.odobren = odobren;
    }




}


