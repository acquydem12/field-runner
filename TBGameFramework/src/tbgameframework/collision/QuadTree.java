/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tbgameframework.math.Collision;

/**
 *
 * @author MrKupi
 */
public class QuadTree {
    
    int MAX_WIDTH = 10;
    int MAX_HEIGHT = 10;
    
    private Rectangle area;
    private ArrayList<QuadTreeNode> listNode = new ArrayList();
    private QuadTree listSubTree[];
    private int numberOfNode = 0;   // number of quadtree node
    
    public QuadTree(Rectangle cArea){
        
        this.area = cArea;
    }
    
    public boolean addNode(QuadTreeNode node){
        // Make sure QuadTree hasn't empty, if empty, don't divided QuadTree to SubQuadTree
        double check = Collision.Square(this.numberOfNode) + Collision.Square(this.listNode.size());
        boolean hasOne = false;
        if(check == 0)
        {
            node.parent = this;
            this.listNode.add(node);
            return true;
        }
        // If QuadTree has one components, divided new Components and re-divide last component
        else if(check == 1 && listSubTree == null){
            hasOne = true;
        }

        // Most of for all node
        if(listSubTree == null){
            this.createSubTree();
        }

        // If don't have sub-quad-tree
        if(listSubTree != null)
        {
            int number = subtreeNodeFollow(node);
            if(number != 0)
            {
                ++numberOfNode;
                listSubTree[number - 1].addNode(node);
            }
            else
            {
                node.parent = this;
                this.listNode.add(node);
            }

            // Re-Divide last node to QuadTree
            if(hasOne)
            {
                QuadTreeNode lastNode = listNode.get(0);
                listNode.remove(lastNode);
                this.addNode(lastNode);
            }

        }// or if already divide the sub-quad-tree
        else
        {
            node.parent = this;
            this.listNode.add(node);
        }
        return true;
    }
    
    public boolean addNodeImmediate(QuadTreeNode node){
        
        if(Collision.IsContains(area, node.getBound())){
            node.parent = this;
            listNode.add(node);
            ++numberOfNode;
        }
        
        return true;
    }
    
    public int subtreeNodeFollow(QuadTreeNode node){
        if(Collision.IsContains(listSubTree[0].area, node.getBound())){ return 1; }
        else if(Collision.IsContains(listSubTree[1].area, node.getBound())) { return 2; }
        else if(Collision.IsContains(listSubTree[2].area, node.getBound())) { return 3; }
        else if(Collision.IsContains(listSubTree[3].area, node.getBound())) { return 4; }
        
        return 0;
    }
    
    public boolean removeNode(QuadTreeNode node){
        
        if(!Collision.IsIntersect(area, node.getBound())){
            return false;
        }
        
        int numberofSubTree;
        if(listSubTree == null){
            numberofSubTree = 0;
        }
        else{
            numberofSubTree = subtreeNodeFollow(node);
        }
        
        if(numberofSubTree == 0){
            for(int i = 0; i < listNode.size(); ++i)
            {
                if(listNode.get(i).getNode() == node.getNode())
                {
                    listNode.remove(i);
                }
            }
        }
        else{
            --this.numberOfNode;
            listSubTree[numberofSubTree - 1].removeNode(node);
        }
        
        // Just has 1 node after remove, need to re-divide
        if(this.numberOfNode + listNode.size() > 1 || this.listSubTree == null)
        {
            return true;
        }
        for(int number = 0; number < 4; ++number)
        {
            if(this.listSubTree[number].listNode.size() == 1)
            {
                QuadTreeNode newNode = this.listSubTree[number].listNode.get(0);
                this.listSubTree[number].listNode.remove(0);
                this.removeSubTree();
                this.listNode.add(newNode);
                return true;
            }
        }
        
        return true;
    }
    
    public void clean(){
        
        listNode.clear();
        numberOfNode = 0;
        if(listSubTree != null){
            for(int i = 3; i >= 0; --i){
                try {
                    listSubTree[i].clean();
                    listSubTree[i].finalize();
                } catch (Throwable ex) {
                    Logger.getLogger(QuadTree.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        listSubTree = null;
    }
    
    private boolean createSubTree(){
        
        // Already created
        if(listSubTree != null){
            return false;
        }
        listSubTree = new QuadTree[4];
        
        listSubTree[0] = new QuadTree(new Rectangle(area.x, area.y, area.width/2, area.height/2));
        listSubTree[1] = new QuadTree(new Rectangle(area.x + area.width/2, area.y, area.width/2, area.height/2));
        listSubTree[2] = new QuadTree(new Rectangle(area.x, area.y + area.height/2, area.width/2, area.height/2));
        listSubTree[3] = new QuadTree(new Rectangle(area.x + area.width/2, area.y + area.height/2, area.width/2, area.height/2));
        
        return true;
    }
    
    public boolean removeSubTree(){
        for(int i = 3; i >= 0; --i){
            try {
                listSubTree[i].clean();
                listSubTree[i].finalize();
            } catch (Throwable ex) {
                Logger.getLogger(QuadTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.listSubTree = null;
        return true;
    }
    
    private ArrayList<QuadTreeNode> getAllNode(){
        
        ArrayList<QuadTreeNode> nodes = new ArrayList();
        nodes.addAll(this.listNode);
        if(listSubTree != null){
            for(int i = 0; i < 4; ++i){
                nodes.addAll(listSubTree[i].getAllNode());
            }
        }
        
        return nodes;
    }
    
    public ArrayList<QuadTreeNode> getNodeRelative(QuadTreeNode node){
        
        ArrayList<QuadTreeNode> nodes = new ArrayList();
        
        if(Collision.IsIntersect(area, node.getBound())){
            nodes.addAll(this.listNode);
            
            if(listSubTree != null){
                int number = subtreeNodeFollow(node);
                if(number != 0){
                    nodes.addAll(listSubTree[number - 1].getNodeRelative(node));
                }
                else {
                    for(int i = 0; i < 4; ++i){
                        nodes.addAll(listSubTree[i].getAllNode());
                    }
                }
            }
            
            if(nodes.size() >= 2){
                return nodes;
            }
            return nodes;
        }
        if(nodes.size() >= 2){
            return nodes;
        }
        return nodes;
    }
        /*public boolean addNode(QuadTreeNode node){
        
        if( numberOfNode == 0 ){
            node.parent = this;
            this.listNode.add(node);
            ++numberOfNode;
            return true;
        }
        
        if(listSubTree == null){
            this.createSubTree();
        }
            
        if(listSubTree != null){
            int subtreeNumber = subtreeNodeFollow(node);
            if( subtreeNumber != 0 ){
                listSubTree[subtreeNumber - 1].addNode(node);
                ++numberOfNode;
            }
            else{
                node.parent = this;
                this.listNode.add(node);
                ++numberOfNode;
            }
            
            if((this.listNode.size() == 1 || this.listNode.size() == 2) && numberOfNode == 2){
                 QuadTreeNode lastNode = listNode.get(0);
                listNode.remove(lastNode);
                --numberOfNode;
                this.addNode(lastNode);
            }
        }
        else{
            node.parent = this;
            ++numberOfNode;
            this.listNode.add(node);
        }
        
        return true;
    }*/
}
