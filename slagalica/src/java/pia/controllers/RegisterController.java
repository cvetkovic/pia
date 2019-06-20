package pia.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pia.common.Encryption;
import pia.common.KorisniciDAO;
import pia.common.SessionUtils;
import pia.database.Korisnici;

@ManagedBean(name = "registerController")
@ViewScoped
public class RegisterController implements Serializable
{

    private String ime, prezime, email, zanimanje, korisnickoIme, lozinka, lozinkaPotvrda, pol;
    private Date datumRodenja;
    private String slika;
    private UploadedFile uploadedFile;

    private UUID guid;

    private String greska;
    private String status;

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }

    public RegisterController()
    {
        guid = UUID.randomUUID();
    }

    public String getGreska()
    {
        return greska;
    }

    public void setGreska(String greska)
    {
        this.greska = greska;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getIme()
    {
        return ime;
    }

    public void setIme(String ime)
    {
        this.ime = ime;
    }

    public String getPrezime()
    {
        return prezime;
    }

    public void setPrezime(String prezime)
    {
        this.prezime = prezime;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getZanimanje()
    {
        return zanimanje;
    }

    public void setZanimanje(String zanimanje)
    {
        this.zanimanje = zanimanje;
    }

    public String getKorisnickoIme()
    {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme)
    {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka()
    {
        return lozinka;
    }

    public void setLozinka(String lozinka)
    {
        this.lozinka = lozinka;
    }

    public String getLozinkaPotvrda()
    {
        return lozinkaPotvrda;
    }

    public void setLozinkaPotvrda(String lozinkaPotvrda)
    {
        this.lozinkaPotvrda = lozinkaPotvrda;
    }

    public String getPol()
    {
        return pol;
    }

    public void setPol(String pol)
    {
        this.pol = pol;
    }

    public Date getDatumRodenja()
    {
        return datumRodenja;
    }

    public void setDatumRodenja(Date datumRodenja)
    {
        this.datumRodenja = datumRodenja;
    }

    public String getSlika()
    {
        return slika;
    }

    public void setSlika(String slika)
    {
        this.slika = slika;
    }

    public void setUploadedFile(UploadedFile uploadedFile)
    {
        this.uploadedFile = uploadedFile;
    }

    public String registruj()
    {
        Korisnici k = new Korisnici();
        guid = UUID.randomUUID();

        k.setIme(ime);
        k.setPrezime(prezime);
        k.setEmail(email);
        k.setZanimanje(zanimanje);
        k.setKorisnickoIme(korisnickoIme);
        k.setLozinka(Encryption.HashSHA1(lozinka));
        k.setPol(Integer.parseInt(pol));

        java.sql.Date d = new java.sql.Date(datumRodenja.getTime());

        k.setDatumRodenja(d);

        if (!uploadedFile.getFileName().equals(""))
        {
            if (!aploduj(k, guid.toString()))
            {
                greska = "Dimenzija slike mora biti manja ili jednaka 300x300px.";
                return "";
            }
        }
        k.setPrivilegija(0);

        if (!lozinka.equals(lozinkaPotvrda))
        {
            greska = "Lozinka i njena potvrda se ne poklapaju.";
            return "";
        }

        try
        {
            if (KorisniciDAO.PostojiKorisnickoIme(korisnickoIme))
            {
                greska = "Korisničko ime je već zauzeto.";
                return "";
            }
            else if (KorisniciDAO.PostojiEmail(email))
            {
                greska = "E-mail adresa je već zauzeta.";
                return "";
            }
            else
            {
                KorisniciDAO.RegistrujKorisnika(k);
                status = "Korisnik je uspešno registrovan.";
                return "index.xhtml";
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return "index.xhtml";
    }

    private String getFileExtension(String name)
    {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1)
        {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public boolean aploduj(Korisnici k, String ime)
    {
        try (InputStream istream = uploadedFile.getInputstream())
        {
            String imeFajla = ime;
            String extension = uploadedFile.getFileName().substring(uploadedFile.getFileName().lastIndexOf("."));
            imeFajla = imeFajla + extension;
            k.setSlika(imeFajla);

            if (validateImageDimensions(uploadedFile.getContents()))
            {
                Files.copy(istream, new File(imeFajla).toPath());
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }

        return true;
    }

    private static boolean validateImageDimensions(byte[] bytes) throws IOException
    {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));

        return bufferedImage.getHeight() <= 300 && bufferedImage.getWidth() <= 300;
    }
}
