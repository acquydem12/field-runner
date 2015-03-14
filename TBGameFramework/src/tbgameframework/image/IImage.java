/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

/**
 *
 * @author MrKupi
 */
public interface IImage {
    
    String getLink();
    String getID();
    
    static final String tagLink = "Link";
    static final String tagImageName = "ImageName";
}
