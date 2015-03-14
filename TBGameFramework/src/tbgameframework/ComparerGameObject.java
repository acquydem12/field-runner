/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.util.Comparator;

/**
 *
 * @author MrKupi
 */
public class ComparerGameObject implements Comparator<GameObject>{

    @Override
    public int compare(GameObject t, GameObject t1) {
        return t.getDepth() < t1.getDepth() ? -1 : t.getDepth() > t1.getDepth() ? 1 : 0;
    }
}
