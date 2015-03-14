/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;


public class ImageImpl implements IImage {

    private String imageName;
    private String link;
    
    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String getID() {
        return this.imageName;
    }
    
}
