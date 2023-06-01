/**
 * MinHeapInput.java
 * @description an arrayList-based implementation of a min heap
 * @author Ria Petrova
 * @version 2023-05-01
 */
import java.util.ArrayList;

public class MinHeapInput {

    //attributes
    protected ArrayList<Integer> heapArrayL;

    //methods
    /**
     * Constructor for MinHeapInput
     */
    public MinHeapInput() {
        heapArrayL = new ArrayList<Integer>();
        heapArrayL.add(0);  //value at index 0 is never accessed,
        // and merely acts as a placeholder for ease of index calculations later on
    }

    /**
     * returns the arrayList that stores the heap data
     */
    public ArrayList<Integer> getArrL() { return this.heapArrayL; }

    /**
     * returns the size (number of elements) of the heap
     * @return int representing size
     */
    public int size() { return heapArrayL.size(); }

    /**
     * Returns the element of the heap at a given index
     * @param i index of element to be found
     * @return value of element at given index
     */
    public int getElement(int i) { return heapArrayL.get(i); }

    /**
     * Method that swaps/bubbles up a smaller value towards its proper place in the heap
     * Nothing is done if heap is already in order
     */
    private void percolateUp() {
        int child = heapArrayL.size() - 1;
        int parent = (int) Math.floor((child)/2.0);

        while(heapArrayL.get(child) < heapArrayL.get(parent) && child > 1) {
            swap(child, parent);
            child = parent;
            parent = (int) Math.floor((child)/2.0);

        }
    } //end of percolateUp method

    /**
     * Method that swaps/bubbles down a larger value towards its proper place in the heap
     * Nothing is done if heap is already in order
     */
    protected void percolateDown() {

        int lastInd = heapArrayL.size() -1;   //save new last index in heap

        int parent = 1;        //start with index 1 (the root) as the parent
        int lChild = parent*2;   //assign left and right children
        int rChild = parent*2 + 1;

        if(parent < lastInd && lChild < lastInd && rChild < lastInd){

            while(heapArrayL.get(parent) > heapArrayL.get(lChild) || heapArrayL.get(parent) > heapArrayL.get(rChild)) {

                if(heapArrayL.get(parent) < heapArrayL.get(rChild) && heapArrayL.get(parent) > heapArrayL.get(lChild)){
                    swap(parent, lChild);
                    parent = lChild;

                } else if(heapArrayL.get(parent) < heapArrayL.get(lChild) && heapArrayL.get(parent) > heapArrayL.get(rChild)) {
                    swap(parent, rChild);
                    parent = rChild;

                } else if(heapArrayL.get(lChild) < heapArrayL.get(rChild)) {
                    swap(parent, lChild);
                    parent = lChild;

                } else if(heapArrayL.get(rChild) <= heapArrayL.get(lChild)) {
                    swap(parent, rChild);
                    parent = rChild;
                }

                lChild = parent*2;       //re-assign children values
                rChild = parent*2 + 1;

                if(lChild > lastInd || rChild > lastInd) break;

            } //end while loop
        }


    } //close percolateDown()

    /**
     * helper method to swap values at given indices
     * @param x index of one element to be swapped
     * @param y index of other element to be swapped
     */
    protected void swap(int x, int y) {
        Integer xVal = heapArrayL.get(x);
        Integer yVal = heapArrayL.get(y);
        heapArrayL.set(x, yVal);
        heapArrayL.set(y, xVal);
    } //end swap method

    /**
     * adds element to heap and ensures heap order
     * @param x balue of element to be added to heap
     */
    public void insert(Integer x) {
        heapArrayL.add(x);
        percolateUp();
    } //end of insert method


}