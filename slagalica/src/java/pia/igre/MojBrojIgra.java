package pia.igre;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import pia.common.Utils;
import pia.controllers.GameController;
import pia.controllers.GameController.KoDobija;

public class MojBrojIgra {

    public static class Symbol {

        enum SymbolType {

            BROJ,
            OPERATOR
        }

        public String vrednost;
        public SymbolType tip;

        public Symbol(String vrednost, SymbolType tip) {
            this.vrednost = vrednost;
            this.tip = tip;
        }
    }

    public static int[] GenerisiBrojeve() {
        int[] brojevi = new int[6];

        brojevi[0] = Utils.GenerisiBroj(1, 9);
        brojevi[1] = Utils.GenerisiBroj(1, 9);
        brojevi[2] = Utils.GenerisiBroj(1, 9);
        brojevi[3] = Utils.GenerisiBroj(1, 9);

        brojevi[4] = Utils.GenerisiBroj(1, 3);
        if (brojevi[4] == 1) {
            brojevi[4] = 10;
        } else if (brojevi[4] == 2) {
            brojevi[4] = 15;
        } else if (brojevi[4] == 3) {
            brojevi[4] = 20;
        }

        brojevi[5] = Utils.GenerisiBroj(1, 4);
        if (brojevi[5] == 1) {
            brojevi[5] = 25;
        } else if (brojevi[5] == 2) {
            brojevi[5] = 50;
        } else if (brojevi[5] == 3) {
            brojevi[5] = 75;
        } else if (brojevi[5] == 4) {
            brojevi[5] = 100;
        }

        return brojevi;
    }

    public static boolean IsOperator(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

    static int GetPriority(char c, boolean stack) {
        switch (c) {
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 3;
            case '^':
                if (stack) {
                    return 4;
                } else {
                    return 5;
                }
            case '(':
                if (stack) {
                    return 0;
                } else {
                    return 6;
                }
            case ')':
                return 1;
            default:
                return 0;
        }
    }

    static int GetRank(char c) {
        return (c == '(' || c == ')') ? 0 : -1;
    }

    public static List<Symbol> Parse(List<Symbol> input) {
        List<Symbol> result = new LinkedList<>();
        Stack<Symbol> stack = new Stack<>();

        int rank = 0;

        for (Symbol next : input) {
            if (next.tip != Symbol.SymbolType.OPERATOR) {
                result.add(next);
                rank++;
            } else {
                while ((!stack.empty()) && (GetPriority(next.vrednost.charAt(0), false) <= GetPriority(stack.peek().vrednost.charAt(0), true))) {
                    Symbol c = stack.peek();
                    stack.pop();
                    result.add(c);
                    rank += GetRank(c.vrednost.charAt(0));

                    if (rank < 1) {
                        throw new RuntimeException("Greska u parsiranju izraza");
                    }
                }

                if (!next.vrednost.equals(")")) {
                    stack.push(next);
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.empty()) {
            Symbol c = stack.pop();
            result.add(c);
            rank += GetRank(c.vrednost.charAt(0));
        }

        if (rank < 1) {
            throw new RuntimeException("Greska u parsiranju izraza");
        }

        return result;
    }

    static Symbol ReturnToken(String data) {
        if ((data.length() == 1) && IsOperator(data.charAt(0))) {
            return new Symbol(data, Symbol.SymbolType.OPERATOR);
        }

        try {
            int t = Integer.parseInt(data);
            return new Symbol(Integer.toString(t), Symbol.SymbolType.BROJ);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Symbol> TokenizeExpression(String exp) {
        List<Symbol> result = new LinkedList<>();
        String temp = "";
        char[] expression = exp.toCharArray();

        for (char c : expression) {
            if (!IsOperator(c)) {
                temp += c;
            } else {
                if (!"".equals(temp)) {
                    result.add(ReturnToken(temp));
                }

                result.add(ReturnToken(Character.toString(c)));
                temp = "";
            }
        }

        if (!"".equals(temp)) {
            result.add(ReturnToken(temp));
        }

        return result;
    }

    public static int CalculateExpression(List<Symbol> tokens) {
        Stack<Symbol> tmp = new Stack<>();
        int rez = 0;

        for (Symbol t : tokens) {
            if (t.tip == Symbol.SymbolType.BROJ) {
                tmp.push(t);
            } else {
                Symbol op2 = tmp.pop();
                Symbol op1 = tmp.pop();

                int v2 = Integer.parseInt(op2.vrednost);
                int v1 = Integer.parseInt(op1.vrednost);

                switch (t.vrednost) {
                    case "+":
                        rez = v1 + v2;
                        break;
                    case "-":
                        rez = v1 - v2;
                        break;
                    case "*":
                        rez = v1 * v2;
                        break;
                    case "/":
                        rez = v1 / v2;
                        break;
                }

                tmp.push(new Symbol(Integer.toString(rez), Symbol.SymbolType.BROJ));
            }
        }

        rez = Integer.parseInt(tmp.pop().vrednost);

        if (tmp.empty()) {
            return rez;
        } else {
            throw new RuntimeException("Neispravan postfiksni aritmeticki izraz");
        }
    }

    public static KoDobija OdrediBrojPoena(int trazeniBroj, int prijavljeniPlavi, int dobijeniPlavi, int prijavljeniCrveni, int dobijeniCrveni) {
        if (prijavljeniPlavi != dobijeniPlavi && prijavljeniCrveni != dobijeniCrveni)
            return new GameController.KoDobija(0, KoDobija.Takmicar.OBA);
        else if (prijavljeniPlavi != dobijeniPlavi)
            return new GameController.KoDobija(10, KoDobija.Takmicar.CRVENI);
        else if (prijavljeniCrveni != dobijeniCrveni)
            return new GameController.KoDobija(10, KoDobija.Takmicar.PLAVI);
        
        int plaviRazlika = Math.abs(trazeniBroj - dobijeniPlavi);
        int crveniRazlika = Math.abs(trazeniBroj - dobijeniCrveni);
        
        if (plaviRazlika < crveniRazlika)
            return new GameController.KoDobija(10, KoDobija.Takmicar.PLAVI);
        else if (plaviRazlika == crveniRazlika)
            return new GameController.KoDobija(5, KoDobija.Takmicar.OBA);
        else
            return new GameController.KoDobija(10, KoDobija.Takmicar.CRVENI);
    }
}
