/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

import java.util.Collection;

/**
 *
 * @author MrKupi
 */
public interface IButtonContainer {
    
    // Get all button membet
    Collection<IButton> getAll();
    // Add new button to container
    void AddButton(IButton button, IButtonRangeFactory factory, int pos);
    // Remove a button from container
    void RemoveButton(IButton button);
    // Reset all button in container to off
    void Reset();
}
