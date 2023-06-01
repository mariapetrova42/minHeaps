/**
 * MinDriverInput.java
 *
 * @description a demonstration of the benefits of the MinHeap data structure through deletion of minimum element
 * @author Ria Petrova
 * @version 2023-05-01
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MinDriverInput
{
    /**
     * Builds a heap from values in a regular Integer[]
     * @param arr Integer array
     * @return MinHeapInput
     */
    public static MinHeapInput buildHeapArr(Integer[] arr)
    {
        MinHeapInput heap = new MinHeapInput();
        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }
        return heap;

    } //end buildHeapArr method

    /**
     * Prints out the values stored in the heap
     * @param str String attached to heap data
     * @param heap heap to be printed
     * @return String with info and heap
     */
    public static String printHeap(String str, MinHeapInput heap)
    {
        String output = str;

        for (int i = 1; i < heap.size(); i++) {
            output += heap.getElement(i) + " ";
        }

        return output;
    }

    /**
     * deleteMin algorithm for heap data structure
     * deletes the minimum value of the heap and ensures heap order afterwards
     * @param heap from which the minimum will be deleted
     */
    public static void heapDeleteMin(MinHeapInput heap)
    {
        ArrayList<Integer> heapArrayL = heap.getArrL();
        int lastVal = heapArrayL.get(heapArrayL.size() - 1); //save last value in heap
        heapArrayL.set(1, lastVal);   //set root to the lastVal

        heap.percolateDown();
        heapArrayL.remove(heapArrayL.size() - 1); //remove the lastVal (now a duplicate), reduce heap size by 1)

        if (heapArrayL.size() == 3) {//technically a percolateDown() operation, but it needs to be right after the remove()
            if (heapArrayL.get(1) > heapArrayL.get(2)) {
                heap.swap(1, 2);
            }
        }
    } //end of heapDeleteMin method

    /**
     * DeleteMin method, but for a regular array
     * @param arr Integer[] with the minimum
     * @return Integer[] with the minimum removed
     */
    public static Integer[] arrayDeleteMin(Integer[] arr)
    {
        int min = arr[0];
        int location = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                location = i;
            }
        }

        Integer[] newArr = new Integer[arr.length - 1];

        for (int i = 0; i < location; i++) {
            newArr[i] = arr[i];
        }
        for (int i = location; i < newArr.length; i++) {
            newArr[i] = arr[i + 1];
        }

        return newArr;

    } //end of arrayDeleteMin method


    public static void main(String[] args)
    {

        //setting up arrays and heaps for data insertion
        Scanner input = new Scanner(System.in);
        System.out.println("The functions of the deletMin algorithm for heaps");
        System.out.println("\tand the arrayDeleteMin algorithm for arrays will be ");
        System.out.println("\tdemonstrated with an existing array, and a user-input array.");

        Integer[] arr1 = {23, 12, 11, 45, 36, 13, 76, 49, 69, 34, 42, 98, 4, 57, 3};
        Integer[] arr2 = new Integer[20];

        MinHeapInput heap1 = buildHeapArr(arr1);
        MinHeapInput heap2 = new MinHeapInput();
        boolean valid = true;

        //getting input from user
        System.out.println("\n\nPlease enter 20 integers between the values of 0 and 100: ");
        for (int i = 0; i < 20; i++) {
            int j = input.nextInt();
            if (j < 0 || j > 100) {
                System.out.println("\nInput invalid, please re-run the program.");
                valid = false;
                break;
            }
            arr2[i] = j;
            heap2.insert(j);
        }

        //testing and results
        if (valid) {
            System.out.println("\n-----------------------------------------");
            System.out.println("TESTING HEAP'S DELETE MIN WITH AN EXISTING ARRAY: ");
            System.out.println("-----------------------------------------\n");

            System.out.println("Original array: ");
            for (int i = 0; i < arr1.length; i++) {
                System.out.print(arr1[i] + " ");
            }

            System.out.println(printHeap("\n\nOriginal heap from array: \n", heap1));
            heapDeleteMin(heap1);
            System.out.println(printHeap("\nHeap after removing minimum: \n", heap1));

            //----------------------------------------------------------------------------------

            System.out.println("\n-----------------------------------------");
            System.out.println("TESTING HEAP'S DELETE MIN WITH USER-INPUT VALUES: ");
            System.out.println("-----------------------------------------\n");


            System.out.println("\nOriginal array from user: ");
            for (int i = 0; i < arr2.length; i++) {
                System.out.print(arr2[i] + " ");
            }

            System.out.println(printHeap("\n\nOriginal heap from user array: \n", heap2));
            heapDeleteMin(heap2);
            System.out.println(printHeap("\nHeap after removing minimum: \n", heap2));


            System.out.println("\n\n---------------------------------------");
            System.out.println("---------------------------------------");
            System.out.println("Now the same algorithm, but with a regular array...");
            System.out.println("---------------------------------------");
            System.out.println("---------------------------------------");


            System.out.println("\n-----------------------------------------");
            System.out.println("TESTING ARRAY'S DELETE MIN WITH AN EXISTING ARRAY: ");
            System.out.println("-----------------------------------------\n");

            //test heap with values from an array that already exists
            System.out.println("Original array: ");
            for (int i = 0; i < arr1.length; i++) {
                System.out.print(arr1[i] + " ");
            }

            System.out.println("\n\nArray after deleting the minimum: ");
            for (int i = 0; i < arr1.length - 1; i++) {
                System.out.print(arrayDeleteMin(arr1)[i] + " ");
            }

            System.out.println("\n\n-----------------------------------------");
            System.out.println("TESTING ARRAY'S DELETE MIN WITH USER-INPUT VALUES: ");
            System.out.println("-----------------------------------------\n");

            System.out.println("Original array from user: ");
            for (int i = 0; i < arr2.length; i++) {
                System.out.print(arr2[i] + " ");
            }

            System.out.println("\n\nArray after deleting the minimum: ");
            for (int i = 0; i < arr2.length - 1; i++) {
                System.out.print(arrayDeleteMin(arr2)[i] + " ");
            }


        }

    }
}
