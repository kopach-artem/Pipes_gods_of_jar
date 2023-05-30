
package player;

import java.io.Serializable;

/**
 * A pumpa módosításának típusát határozza meg
 * (azaz, hogy az AdjustPump(pi: Pipe, type: Type) függvénynél bemeneti csövet avagy kimeneti csövet változtatunk,
 * amennyiben bemeneti csövet akkor a type értéke Input, amennyiben pedig kimeneti csövet a type értéke Output)
 */
public enum Type implements Serializable {
    Input,
    Output
}
