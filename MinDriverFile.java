import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MinDriverFile
{
    public static void main(String args[]) {

        // NAME/DATE
        System.out.println("Programmer: " + " Maria Petrova ");
        System.out.println("Date: 04.04.2022");
        //---------------------------------------------------------

        //loop four times to work through all the files needed
        for(int i = 1; i <= 4; i++) {
            //generate file names
            String inName = "in" + i + ".txt";
            String outName = "out" + i + ".txt";

            //call method to read input file, process, and write to specified output file
            fileReaderWriter(inName, outName);
        }

        System.out.println("\nProgram run successful.");

    } //close main()

    public static void fileReaderWriter(String in, String out) {
        //this method both reads the input files and writes to the output files

        //creates reader to read input, writer to write output
        try (Scanner reader = new Scanner(new File(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {

            //ArrayList to store elements of file
            ArrayList<Integer> input = new ArrayList<Integer>();
            // String to be written to output file
            String output = "";

            //reads the input file
            while(reader.hasNext()) {
                input.add(reader.nextInt());
            }

            output += "Input data: \n";
            for(int i = 0; i < input.size(); i++) {
                output += input.get(i) + " ";
            }

            //call testing methods using input
            MinHeapFile newHeap = buildHeap(input);
            checkHeap(newHeap);
            output += printHeap("\n\nHeap: \n", newHeap);

            newHeap.insert(31);
            checkHeap(newHeap);
            output += printHeap("\n\nHeap after insert 31: \n", newHeap);

            newHeap.insert(14);
            checkHeap(newHeap);
            output += printHeap("\n\nHeap after insert 14: \n", newHeap);

            while(newHeap.size() > 1) {
                newHeap.deleteMin();
                checkHeap(newHeap);
                output += printHeap("\n\nHeap after deleteMin: \n", newHeap);
            }
            output += "\n\nDeleted all.";


            //writes result to output file
            writer.write(output);
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    } //close fileReaderWriter()

    public static MinHeapFile buildHeap(ArrayList<Integer> arrL) {
        MinHeapFile heap = new MinHeapFile();

        for(int i = 0; i < arrL.size(); i++) {
            heap.insert(arrL.get(i));
        }

        return heap;
    }

    //print heap
    public static String printHeap(String str, MinHeapFile heap) {
        String output = "";
        output += str;

        for(int i = 1; i < heap.size(); i++) {
            output += heap.getElement(i) + " ";
        }

        return output;
    }

    // Consider heap elements ordered if first <= second or second is beyond heap size
    public static boolean areHeapElementsOrdered(MinHeapFile heap, int i, int j) {

        return (i >= heap.size() || j >= heap.size() || heap.getElement(i) <= heap.getElement(j));
    }

    public static String checkHeap(MinHeapFile heap){
        String fout = "";
        for(int i = 0; i < heap.size(); i++) {
            if(areHeapElementsOrdered(heap,i,i*2+1) && areHeapElementsOrdered(heap,i,i*2+2)){
                printHeap("Corrupted", heap);
            }
        }
        return fout;
    }


}
