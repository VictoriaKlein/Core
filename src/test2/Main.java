package test2;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{5,6,3,2,5,1,4,9};
        System.out.println("QuickSort: ");
        printArray(quicksort(arr));
        System.out.println("\n"+"MergeSort: ");
        printArray(mergeSort(arr));
    }

    //quicksort - log(n)
    private static int [] quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
        return array;
    }

    public static void quicksort(int[] array, int start, int end) {
        if(start >= end) return;
        int pivot = array[end];
        int l =  partition(array, start, end, pivot);
        quicksort(array, start, l-1);
        quicksort(array,l+1, end);
    }

    public static int partition (int[] array, int start, int end, int pivot) {
        int l = start;
        int r = end-1;
        while (l<r) {
            while (array[l] <= pivot && l < r) {
                l++;
            }
            while (array[r] >= pivot && l < r) {
                r--;
            }
            swap(array, l, r);
        }
        if (array[l]>array[end]) {
            swap(array, l, end);
        } else {
            l = end;
        }
        return l;
    }
    public static void swap (int array[], int l, int r){
        int temp;
        temp = array[l];
        array[l]=array[r];
        array[r]=temp;
    }

    //mergesort -n(log n)
    public static int [] mergeSort (int [] array) {
        int length = array.length;
        if (length < 2) {
            return array;
        }
        int devidedIndex = length / 2;
        int[] partedArrayL = new int[devidedIndex];
        int[] partedArrayR = new int[length - devidedIndex];
        for (int i = 0; i < devidedIndex; i++){
            partedArrayL[i] = array[i];
        }
        for (int i = devidedIndex; i < length; i++){
            partedArrayR[i-devidedIndex] = array[i];
        }
        mergeSort(partedArrayL);
        mergeSort(partedArrayR);
        merge(array, partedArrayL, partedArrayR);
        return array;
    }
    public static void merge (int array [], int partedArrayL [],int partedArrayR []) {
        int sizeOfpartedArrLeft = partedArrayL.length;
        int sizeOfpartedArrRight = partedArrayR.length;
        int l = 0, r = 0, f = 0;
        while (l < sizeOfpartedArrLeft && r < sizeOfpartedArrRight) {
            if (partedArrayL[l] <= partedArrayR[r]) {
                array[f] = partedArrayL[l];
                l++;
            } else {
                array[f] = partedArrayR[r];
                r++;
            }
            f++;
        }
        while (l < sizeOfpartedArrLeft) {
            array[f] = partedArrayL[l];
            l++;
            f++;
        }
        while (r < sizeOfpartedArrRight) {
            array[f] = partedArrayR[r];
            r++;
            f++;
        }
    }
    // print arr
    public static void printArray (int[]array){
        System.out.println("Sorted");
        for (int i=0; i< array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
