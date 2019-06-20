package pia.igre;

import pia.common.Utils;
import pia.controllers.GameController;
import pia.controllers.GameController.SkockoInfo.Kombinacija;
import pia.controllers.GameController.SkockoInfo.Rezultat;

public class SkockoIgra {

    public static GameController.SkockoInfo.Kombinacija GenerisiKombinaciju() {
        int z1, z2, z3, z4;

        z1 = Utils.GenerisiBroj(0, 5);
        z2 = Utils.GenerisiBroj(0, 5);
        z3 = Utils.GenerisiBroj(0, 5);
        z4 = Utils.GenerisiBroj(0, 5);

        return new GameController.SkockoInfo.Kombinacija(z1, z2, z3, z4);
    }

    public static void OdrediRezultatKombinacije(Kombinacija k, Kombinacija resenje) {
        int brojPogodenihMesta = 0;
        int brojPogodenihSimbola = 0;
        boolean[] obradeno = {false, false, false, false};
        int[] brojSimbola = {0, 0, 0, 0, 0, 0};
        int[] brojSimbolaRezultat = {0, 0, 0, 0, 0, 0};

        if (k.getZnak1() == resenje.getZnak1()) {
            brojPogodenihMesta++;
            obradeno[0] = true;
        }
        if (k.getZnak2() == resenje.getZnak2()) {
            brojPogodenihMesta++;
            obradeno[1] = true;
        }
        if (k.getZnak3() == resenje.getZnak3()) {
            brojPogodenihMesta++;
            obradeno[2] = true;
        }
        if (k.getZnak4() == resenje.getZnak4()) {
            brojPogodenihMesta++;
            obradeno[3] = true;
        }

        for (int i = 0; i < 4; i++) {
            if (obradeno[i] == false) {
                if (i == 0) {
                    brojSimbola[GameController.ZnakToInt(k.getZnak1())]++;
                } else if (i == 1) {
                    brojSimbola[GameController.ZnakToInt(k.getZnak2())]++;
                } else if (i == 2) {
                    brojSimbola[GameController.ZnakToInt(k.getZnak3())]++;
                } else if (i == 3) {
                    brojSimbola[GameController.ZnakToInt(k.getZnak4())]++;
                }
                
                if (i == 0) {
                    brojSimbolaRezultat[GameController.ZnakToInt(resenje.getZnak1())]++;
                } else if (i == 1) {
                    brojSimbolaRezultat[GameController.ZnakToInt(resenje.getZnak2())]++;
                } else if (i == 2) {
                    brojSimbolaRezultat[GameController.ZnakToInt(resenje.getZnak3())]++;
                } else if (i == 3) {
                    brojSimbolaRezultat[GameController.ZnakToInt(resenje.getZnak4())]++;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            if ((brojSimbola[i] != 0) && (brojSimbola[i] == brojSimbolaRezultat[i])) {
                brojPogodenihSimbola++;
            }
        }

        if (brojPogodenihMesta == 4)
            k.setPobedeno(true);
        
        int i = 0;
        while (brojPogodenihMesta > 0) {
            if (i == 0) {
                k.setRezultat1(Rezultat.POGODIO_NA_MESTU);
            } else if (i == 1) {
                k.setRezultat2(Rezultat.POGODIO_NA_MESTU);
            } else if (i == 2) {
                k.setRezultat3(Rezultat.POGODIO_NA_MESTU);
            } else if (i == 3) {
                k.setRezultat4(Rezultat.POGODIO_NA_MESTU);
            }

            i++;
            brojPogodenihMesta--;
        }
        while (brojPogodenihSimbola > 0) {
            if (i == 0) {
                k.setRezultat1(Rezultat.POGODIO_NIJE_NA_MESTUU);
            } else if (i == 1) {
                k.setRezultat2(Rezultat.POGODIO_NIJE_NA_MESTUU);
            } else if (i == 2) {
                k.setRezultat3(Rezultat.POGODIO_NIJE_NA_MESTUU);
            } else if (i == 3) {
                k.setRezultat4(Rezultat.POGODIO_NIJE_NA_MESTUU);
            }

            i++;
            brojPogodenihSimbola--;
        }
        
        k.setPotvrdeno(true);
    }
}