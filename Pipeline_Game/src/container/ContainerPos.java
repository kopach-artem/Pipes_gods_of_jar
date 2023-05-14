package container;

import java.io.Serializable;

public class ContainerPos implements Serializable {
    
    /** 
     * @return Container
     */
    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    Container container;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    int posX;

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    int posY;

    public ContainerPos(Container c, int x, int y){
        container = c;
        posX = x;
        posY = y;
    }

    public ContainerPos(){
        container = null;
        posX = -1;
        posY = -1;
    }

    public boolean isOnNeighboringTile(int xCord, int yCord){
        if(this.posX - 1 >= 0){
            if(xCord == this.posX - 1 && yCord == this.posY){
                return true;
            }
        }
        else if(this.posY - 1 >= 0){
            if(xCord == this.posX && yCord == this.posY - 1){
                return true;
            }
        }
        else if(xCord == this.posX + 1 && yCord == this.posY){
            return true;
        }
        else if(xCord == this.posX && yCord == this.posY) {
            return true;
        }
        return false;
    }



}
