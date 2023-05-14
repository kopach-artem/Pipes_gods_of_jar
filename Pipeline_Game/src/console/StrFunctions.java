package console;

public interface StrFunctions
{
    //Ez a függvény megnézi, hogy egy stringben lévő karakter lehet-e integer
    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    /*
    Ez a függvény legvágja a vmiX_Y formátumú stringekből
    X és Y-t, és visszaadja egy int tömbben (az intervallum 0-99)
    Ha hibás a megadott string, akkor -1 -1 -el tér vissza a tömbben
     */
    public static int[] subPosString(String container, String at)
    {
        int posX=-1;
        int posY=-1;
        int i=container.length();
        int j=at.length();
        if(container.length()>j+2)
        {
            if(container.charAt(j+2)=='_')
            {
                if(container.length()>j+4)
                {
                    if(isDigit(container.charAt(i-5)) && isDigit(container.charAt(i-4)) && isDigit(container.charAt(i-2)) && isDigit(container.charAt(i-1)))
                    {
                        posX=Integer.parseInt(container.substring(i-5,i-3));
                        posY=Integer.parseInt(container.substring(i-2,i));
                    }
                }
                else if(isDigit(container.charAt(i-4)) && isDigit(container.charAt(i-3)) && isDigit(container.charAt(i-1)))
                {
                    posX=Integer.parseInt(container.substring(i-4,i-2));
                    posY=Integer.parseInt(container.substring(i-1,i));
                }
                else
                {
                    System.out.println("Some of the given positions value are incorrect");
                }
            }
            else if(container.charAt(j+1)=='_')
            {
                if(container.length()>j+3)
                {
                    if(isDigit(container.charAt(i-4)) && isDigit(container.charAt(i-2)) && isDigit(container.charAt(i-1)))
                    {
                        posX=Integer.parseInt(container.substring(i-4,i-3));
                        posY=Integer.parseInt(container.substring(i-2,i));
                    }
                }
                else if(isDigit(container.charAt(i-3)) && isDigit(container.charAt(i-1)))
                {
                    posX=Integer.parseInt(container.substring(i-3,i-2));
                    posY=Integer.parseInt(container.substring(i-1,i));
                }
                else
                {
                    System.out.println("Some of the given positions value are incorrect");
                }
            }
            else
            {
                System.out.println("There must be a '_' character between posX and posY");
            }
        }
        else
        {
            System.out.println("There must be a '_' character between posX and posY");
        }
        //pozíció egy tömbben tárolása
        int[] positions=new int[2];
        positions[0]=posX;
        positions[1]=posY;

        return positions;
    }
}
