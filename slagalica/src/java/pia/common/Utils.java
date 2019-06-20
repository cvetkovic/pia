package pia.common;

import java.util.Date;
import java.util.Random;

public class Utils {
    private static Random r = new Random(new Date().getTime());
    
    public static int GenerisiBroj(int min, int max)
    {
        return r.nextInt((max - min) + 1) + min;
    }
}