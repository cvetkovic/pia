package pia.database;
// Generated May 31, 2019 8:26:26 PM by Hibernate Tools 4.3.1



/**
 * Asocijacija generated by hbm2java
 */
public class Asocijacija  implements java.io.Serializable {


     private Integer idAsocijacija;
     private int idGrupa1;
     private int idGrupa2;
     private int idGrupa3;
     private int idGrupa4;
     private String resenjeAsocijacije;

    public Asocijacija() {
    }

    public Asocijacija(int idGrupa1, int idGrupa2, int idGrupa3, int idGrupa4, String resenjeAsocijacije) {
       this.idGrupa1 = idGrupa1;
       this.idGrupa2 = idGrupa2;
       this.idGrupa3 = idGrupa3;
       this.idGrupa4 = idGrupa4;
       this.resenjeAsocijacije = resenjeAsocijacije;
    }
   
    public Integer getIdAsocijacija() {
        return this.idAsocijacija;
    }
    
    public void setIdAsocijacija(Integer idAsocijacija) {
        this.idAsocijacija = idAsocijacija;
    }
    public int getIdGrupa1() {
        return this.idGrupa1;
    }
    
    public void setIdGrupa1(int idGrupa1) {
        this.idGrupa1 = idGrupa1;
    }
    public int getIdGrupa2() {
        return this.idGrupa2;
    }
    
    public void setIdGrupa2(int idGrupa2) {
        this.idGrupa2 = idGrupa2;
    }
    public int getIdGrupa3() {
        return this.idGrupa3;
    }
    
    public void setIdGrupa3(int idGrupa3) {
        this.idGrupa3 = idGrupa3;
    }
    public int getIdGrupa4() {
        return this.idGrupa4;
    }
    
    public void setIdGrupa4(int idGrupa4) {
        this.idGrupa4 = idGrupa4;
    }
    public String getResenjeAsocijacije() {
        return this.resenjeAsocijacije;
    }
    
    public void setResenjeAsocijacije(String resenjeAsocijacije) {
        this.resenjeAsocijacije = resenjeAsocijacije;
    }




}


