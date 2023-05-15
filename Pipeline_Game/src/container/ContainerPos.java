package container;


import java.io.Serializable;

/**
 * Ez az osztály a Containerek pozíciójáért felelős-
 */
public class ContainerPos implements Serializable {
    
    /**
     * Lekérdezi az adott pozícióban lévő Container-t.
     * @return Container
     */
    public Container getContainer() {
        return container;
    }

    
    /**
     * Beállítja a Container-t
     * @param container
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    private Container container;

    
    /**
     * Visszatér az X koordinátával
     * @return int
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Beállítja az X koordinátát a paraméterként kapottra.
     * @param posX - Az új X koordináta
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * X koordináta
     */
    private int posX;

    /**
     * Visszatér az Y koordinátával
     * @return int
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Beállítja az Y koordinátát a paraméterként kapottra.
     * @param posY - Az új Y koordináta
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Az Y koordináta.
     */
    private int posY;

    /**
     * ContainerPos konstruktora, létrehoz egy új példányt.
     * @param c - A Container
     * @param x - A Container kívánt X koordinátája.
     * @param y - A Container kívánt Y koordinátája.
     */
    public ContainerPos(Container c, int x, int y){
        container = c;
        posX = x;
        posY = y;
    }

    /**
     * Paraméter nélküli konstuktor.
     */
    public ContainerPos(){
        container = null;
        posX = -1;
        posY = -1;
    }

    /**
     * Megmézi, hogy a megadott koordináták mellette vannak-e a ContainerPos-nak.
     * @param xCord - X koordináta
     * @param yCord - Y koordináta
     * @return - true/false, mellette van-e vagy sem.
     */
    public boolean isOnNeighboringTile(int xCord, int yCord){
        if(this.posX - 1 >= 0){
            if(xCord == this.posX - 1 && yCord == this.posY){
                return true;
            }
        }
        if(this.posY - 1 >= 0){
            if(xCord == this.posX && yCord == this.posY - 1){
                return true;
            }
        }
        if(xCord == this.posX + 1 && yCord == this.posY){
            return true;
        }
        if(xCord == this.posX && yCord == this.posY + 1) {
            return true;
        }
        return false;
    }
}
