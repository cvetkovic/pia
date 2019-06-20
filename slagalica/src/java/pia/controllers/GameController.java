package pia.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "gameController", eager = true)
@ApplicationScoped
public class GameController {

    public static class KoDobija {

        public enum Takmicar {

            PLAVI,
            CRVENI,
            OBA
        }

        int vrednost;
        Takmicar takmicar;

        public KoDobija(int vrednost, Takmicar takmicar) {
            this.vrednost = vrednost;
            this.takmicar = takmicar;
        }
    }

    public static class PartijaInfo {

        public int idPartije;
        public int plaviTakmicar;
        public int crveniTakmicar;

        public PartijaInfo(int idPartije, int plaviTakmicar, int crveniTakmicar) {
            this.idPartije = idPartije;
            this.plaviTakmicar = plaviTakmicar;
            this.crveniTakmicar = crveniTakmicar;
        }

        public int getIdPartije() {
            return idPartije;
        }

        public int getPlaviTakmicar() {
            return plaviTakmicar;
        }

        public int getCrveniTakmicar() {
            return crveniTakmicar;
        }
    }

    public Map<Integer, PartijaInfo> partijeUToku = new HashMap<>();
    public PartijaInfo[] partijeUTokuReferenca;

    public GameController() {
        if (partijeUToku.size() != 0) {
            partijeUTokuReferenca = (PartijaInfo[]) partijeUToku.values().toArray();
        }
    }

    public Map<Integer, PartijaInfo> getPartijeUToku() {
        return partijeUToku;
    }

    public void setPartijeUToku(Map<Integer, PartijaInfo> partijeUToku) {
        this.partijeUToku = partijeUToku;
    }

    public PartijaInfo[] getPartijeUTokuReferenca() {
        if (partijeUToku.size() != 0) {
            partijeUTokuReferenca = partijeUToku.values().toArray(new PartijaInfo[1]);
        }

        return partijeUTokuReferenca;
    }

    public void setPartijeUTokuReferenca(PartijaInfo[] partijeUTokuReferenca) {
        this.partijeUTokuReferenca = partijeUTokuReferenca;
    }

    public static class SlagalicaInfo {

        int idIgre;
        int idPlavi;
        int idCrveni;
        String[] reci1;
        String[] reci2;

        boolean plaviOdigrao1;
        boolean crveniOdigrao1;
        boolean plaviOdigrao2;
        boolean crveniOdigrao2;
        
        //int preostaloVreme = 60;

        String plaviRec1;
        String plaviRec2;
        String crveniRec1;
        String crveniRec2;

        Semaphore semaphore = new Semaphore(1);
    }

    public Map<Integer, SlagalicaInfo> slagaliceUToku = new ConcurrentHashMap<>();

    public static class MojBrojInfo {

        int idIgre;
        int idPlavi;
        int idCrveni;
        int[] brojevi1;
        int[] brojevi2;
        int ciljaniBroj1;
        int ciljaniBroj2;

        boolean plaviOdigrao1;
        boolean crveniOdigrao1;
        boolean plaviOdigrao2;
        boolean crveniOdigrao2;

        int plaviBroj1;
        int plaviBroj2;
        int crveniBroj1;
        int crveniBroj2;

        int prijavljeniPlavi1;
        int prijavljeniPlavi2;
        int prijavljeniCrveni1;
        int prijavljeniCrveni2;

        //int preostaloVreme = 60;
        
        Semaphore semaphore = new Semaphore(1);
    }

    public Map<Integer, MojBrojInfo> mojBrojUToku = new ConcurrentHashMap<>();

    public static class SkockoInfo {

        public enum Znak {

            PRAZNO,
            SKOCKO,
            TREF,
            PIK,
            KARO,
            HERC,
            ZVEZDA
        }

        public enum Rezultat {

            NISTA,
            POGODIO_NIJE_NA_MESTUU,
            POGODIO_NA_MESTU
        }

        public static class Kombinacija {

            Znak znak1;
            Znak znak2;
            Znak znak3;
            Znak znak4;

            Rezultat rezultat1 = Rezultat.NISTA;
            Rezultat rezultat2 = Rezultat.NISTA;
            Rezultat rezultat3 = Rezultat.NISTA;
            Rezultat rezultat4 = Rezultat.NISTA;
            boolean potvrdeno = false;
            boolean pobedeno = false;

            public Kombinacija(int znak1, int znak2, int znak3, int znak4) {
                this(IntToZnak(znak1),
                        IntToZnak(znak2),
                        IntToZnak(znak3),
                        IntToZnak(znak4));
            }

            public Kombinacija(Znak znak1, Znak znak2, Znak znak3, Znak znak4) {
                this.znak1 = znak1;
                this.znak2 = znak2;
                this.znak3 = znak3;
                this.znak4 = znak4;
            }

            public Znak getZnak1() {
                return znak1;
            }

            public Znak getZnak2() {
                return znak2;
            }

            public Znak getZnak3() {
                return znak3;
            }

            public Znak getZnak4() {
                return znak4;
            }

            public static Znak IntToZnak(int znak) {
                switch (znak) {
                    case 0:
                        return Znak.SKOCKO;
                    case 1:
                        return Znak.TREF;
                    case 2:
                        return Znak.PIK;
                    case 3:
                        return Znak.KARO;
                    case 4:
                        return Znak.HERC;
                    case 5:
                        return Znak.ZVEZDA;
                    default:
                        return Znak.PRAZNO;
                }
            }

            public Rezultat getRezultat1() {
                return rezultat1;
            }

            public Rezultat getRezultat2() {
                return rezultat2;
            }

            public Rezultat getRezultat3() {
                return rezultat3;
            }

            public Rezultat getRezultat4() {
                return rezultat4;
            }

            public void setRezultat1(Rezultat rezultat1) {
                this.rezultat1 = rezultat1;
            }

            public void setRezultat2(Rezultat rezultat2) {
                this.rezultat2 = rezultat2;
            }

            public void setRezultat3(Rezultat rezultat3) {
                this.rezultat3 = rezultat3;
            }

            public void setRezultat4(Rezultat rezultat4) {
                this.rezultat4 = rezultat4;
            }

            public boolean isPotvrdeno() {
                return potvrdeno;
            }

            public void setPotvrdeno(boolean potvrdeno) {
                this.potvrdeno = potvrdeno;
            }

            public boolean isPobedeno() {
                return pobedeno;
            }

            public void setPobedeno(boolean pobedeno) {
                this.pobedeno = pobedeno;
            }
        }

        private Kombinacija napraviNista() {
            return new Kombinacija(Znak.PRAZNO, Znak.PRAZNO, Znak.PRAZNO, Znak.PRAZNO);
        }

        int preostaloVreme = 60;
        
        Kombinacija dobitnaKombinacija1;
        Kombinacija dobitnaKombinacija2;

        Kombinacija[] unetaKombinacijaPlavi1 = {napraviNista(),
            napraviNista(),
            napraviNista(),
            napraviNista(),
            napraviNista(),
            napraviNista()};
        Kombinacija unetaKombinacijaCrveni1 = napraviNista();

        Kombinacija[] unetaKombinacijaCrveni2 = {napraviNista(),
            napraviNista(),
            napraviNista(),
            napraviNista(),
            napraviNista(),
            napraviNista()};
        Kombinacija unetaKombinacijaPlavi2 = napraviNista();

        boolean plaviOdigrao1;
        boolean crveniOdigrao1;
        boolean plaviOdigrao2;
        boolean crveniOdigrao2;

        int idPlavi;
        int idCrveni;
        
        boolean zavrseno1;
        boolean zavrseno2;
    }

    public static String ZnakToSlika(SkockoInfo.Znak z) {
        switch (z) {
            case SKOCKO:
                return "images/skocko.png";
            case TREF:
                return "images/tref.png";
            case PIK:
                return "images/pik.png";
            case KARO:
                return "images/karo.png";
            case HERC:
                return "images/herc.png";
            case ZVEZDA:
                return "images/zvezda.png";
            default:
                return "images/nista.png";
        }
    }

    public static String ResenjeToSlika(SkockoInfo.Rezultat r) {
        switch (r) {
            case POGODIO_NA_MESTU:
                return "images/na_mestu.png";
            case POGODIO_NIJE_NA_MESTUU:
                return "images/nije_na_mestu.png";
            default:
                return "images/nista.png";
        }
    }

    public static int ZnakToInt(SkockoInfo.Znak z) {
        switch (z) {
            case SKOCKO:
                return 0;
            case TREF:
                return 1;
            case PIK:
                return 2;
            case KARO:
                return 3;
            case HERC:
                return 4;
            case ZVEZDA:
                return 5;
            default:
                return 0;
        }
    }

    public Map<Integer, SkockoInfo> skockoUToku = new ConcurrentHashMap<>();
    
    
    
    
    
    
    
    
    
    public static class SpojniceInfo
    {
        Map<String, String> kombinacija1;
        Map<String, String> kombinacija2;
        
        String[] leviZaPrikaz1;
        String[] desniZaPrikaz1;
        String[] leviZaPrikaz2;
        String[] desniZaPrikaz2;
        
        int vreme = 60;
        int idIgre;
        int idPlavi;
        int idCrveni;
        
        boolean zavrseno1;
        boolean zavrseno2;
        
        boolean plaviOdigrao1;
        boolean crveniOdigrao1;
        boolean plaviOdigrao2;
        boolean crveniOdigrao2;
        
        boolean[] pogodakLevi = new boolean[10];
        boolean[] pogodakDesni = new boolean[10];
        int indeksAktivan = 0;
        
        boolean promenjeno = false;
                
        public Map<String, String> getKombinacija1()
        {
            return kombinacija1;
        }

        public Map<String, String> getKombinacija2()
        {
            return kombinacija2;
        }
        
        Semaphore semaphore = new Semaphore(1);
    }
    
    public Map<Integer, SpojniceInfo> spojniceUToku = new ConcurrentHashMap<>();
    
    public static String[] ispremestajSpojnicu(Map<String, String> map, boolean levo)
    {
        List<String> s = new ArrayList<>();
        if (levo)
        {
            s.addAll(map.keySet());            
        }
        else
            s.addAll(map.values());
        
        Collections.shuffle(s);
        String[] niz = new String[s.size()];
        s.toArray(niz);
        
        return niz;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class AsocijacijaInfo
    {
        int idUBazi;
        
        String[] bazaA = new String[4];
        String[] bazaB = new String[4];
        String[] bazaC = new String[4];
        String[] bazaD = new String[4];
        Set<String> resenjeA = new HashSet<>();
        Set<String> resenjeB = new HashSet<>();
        Set<String> resenjeC = new HashSet<>();
        Set<String> resenjeD = new HashSet<>();
        Set<String> resenjeKonacno = new HashSet<>();
        
        int vreme = 240;
        int idIgre;
        int idPlavi;
        int idCrveni;
        
        boolean zavrseno;
        
        boolean[] otvorenoA = new boolean[4];
        boolean[] otvorenoB = new boolean[4];
        boolean[] otvorenoC = new boolean[4];
        boolean[] otvorenoD = new boolean[4];
        int pogodenoA;
        int pogodenoB;
        int pogodenoC;
        int pogodenoD;
        int pogodenoKonacno;
        
        boolean igraPlavi = true;
        int brojIgre = 1;
        
        String[] kolonaA = {"A1","A2","A3","A4"};
        String[] kolonaB = {"B1","B2","B3","B4"};
        String[] kolonaC = {"C1","C2","C3","C4"};
        String[] kolonaD = {"D1","D2","D3","D4"};

        public String[] getBazaA()
        {
            return bazaA;
        }

        public void setBazaA(String[] bazaA)
        {
            this.bazaA = bazaA;
        }

        public String[] getBazaB()
        {
            return bazaB;
        }

        public void setBazaB(String[] bazaB)
        {
            this.bazaB = bazaB;
        }

        public String[] getBazaC()
        {
            return bazaC;
        }

        public void setBazaC(String[] bazaC)
        {
            this.bazaC = bazaC;
        }

        public String[] getBazaD()
        {
            return bazaD;
        }

        public void setBazaD(String[] bazaD)
        {
            this.bazaD = bazaD;
        }

        public Set<String> getResenjeA()
        {
            return resenjeA;
        }

        public void setResenjeA(Set<String> resenjeA)
        {
            this.resenjeA = resenjeA;
        }

        public Set<String> getResenjeB()
        {
            return resenjeB;
        }

        public void setResenjeB(Set<String> resenjeB)
        {
            this.resenjeB = resenjeB;
        }

        public Set<String> getResenjeC()
        {
            return resenjeC;
        }

        public void setResenjeC(Set<String> resenjeC)
        {
            this.resenjeC = resenjeC;
        }

        public Set<String> getResenjeD()
        {
            return resenjeD;
        }

        public void setResenjeD(Set<String> resenjeD)
        {
            this.resenjeD = resenjeD;
        }

        public Set<String> getResenjeKonacno()
        {
            return resenjeKonacno;
        }

        public void setResenjeKonacno(Set<String> resenjeKonacno)
        {
            this.resenjeKonacno = resenjeKonacno;
        }

        public int getIdUBazi()
        {
            return idUBazi;
        }

        public void setIdUBazi(int idUBazi)
        {
            this.idUBazi = idUBazi;
        }
    }
    
    public Map<Integer, AsocijacijaInfo> asocijacijaUToku = new ConcurrentHashMap<>();
    
}