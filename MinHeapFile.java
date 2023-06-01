import java.util.ArrayList;

public class MinHeapFile {

    private ArrayList<Integer> heapArrL;

    public MinHeapFile() {
        heapArrL = new ArrayList<Integer>();
        heapArrL.add(0);
    }

    private void percolateUp() {
        int chi = heapArrL.size() - 1;
        int par = (int) Math.floor((chi)/2.0);

        while(heapArrL.get(chi) < heapArrL.get(par) && chi > 1) {
            swap(chi, par);
            chi = par;
            par = (int) Math.floor((chi)/2.0);

        }
    }

    private void percolateDown()
    {

        int lastInd = heapArrL.size() - 1;   //save new last index in heap

        int par = 1;        //start with index 1 (the root) as the parent
        int lChi = par * 2;   //assign left and right children
        int rChi = par * 2 + 1;

        if (par < lastInd && lChi < lastInd && rChi < lastInd) {

            while (heapArrL.get(par) > heapArrL.get(lChi) || heapArrL.get(par) > heapArrL.get(rChi)) {

                if (heapArrL.get(par) < heapArrL.get(rChi) && heapArrL.get(par) > heapArrL.get(lChi)) {
                    swap(par, lChi);
                    par = lChi;

                } else if (heapArrL.get(par) < heapArrL.get(lChi) && heapArrL.get(par) > heapArrL.get(rChi)) {
                    swap(par, rChi);
                    par = rChi;

                } else if (heapArrL.get(lChi) < heapArrL.get(rChi)) {
                    swap(par, lChi);
                    par = lChi;

                } else if (heapArrL.get(rChi) <= heapArrL.get(lChi)) {
                    swap(par, rChi);
                    par = rChi;
                }

                lChi = par * 2;       //re-assign children values
                rChi = par * 2 + 1;

                if (lChi > lastInd || rChi > lastInd) break;

            } //end while loop
        }
    }

    private void swap(int x, int y) {
        Integer xVal = heapArrL.get(x);
        Integer yVal = heapArrL.get(y);
        heapArrL.set(x, yVal);
        heapArrL.set(y, xVal);
    }

    public void insert(Integer x) {
        heapArrL.add(x);
        percolateUp();
    }

    public void deleteMin() {
        int lastVal = heapArrL.get(heapArrL.size()-1); //save last value in heap
        heapArrL.set(1, lastVal);   //set root to the lastVal

        percolateDown();
        heapArrL.remove(heapArrL.size()-1); //remove the lastVal (now a duplicate), reduce heap size by 1)

        if(heapArrL.size() == 3) {//technically a percolateDown() operation, but it needs to be right after the remove()
            if(heapArrL.get(1) > heapArrL.get(2)) {
                swap(1, 2);
            }
        }
    }

    public int size() { return heapArrL.size(); }

    public int getElement(int i) { return heapArrL.get(i); }
}