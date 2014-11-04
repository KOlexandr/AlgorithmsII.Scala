#include "BitonicSort.h"

/**
 * calls bitonicSort for array with data and use it sort array
 * @param array - array with data
 */
void sort(int *array, const int length){
    sorter(array, 0, length-1);
}

/**
 * calls sorter for subArrays of current subArray of input array and merge sorted subArrays
 * @param array - array with data
 * @param p - start of subArray
 * @param r - end of subArray
 */
void sorter(int *array, const int p, const int r){
    int q = (p+r)/2;
    if(p < q){
        sorter(array, p, q);
    }
    if(q+1 < r){
        sorter(array, q+1, r);
    }
    merger(array, p ,r);
}

/**
 * makes reverse of subArray and calls bitonicSorter for subArrays of current subArray
 * @param array - array with data
 * @param p - start of subArray
 * @param r - end of subArray
 */
void merger(int *array, const int p, const int r){
    int i = p;
    int j = r;
    while(i < j){
        comparator(array, i, j);
        i++; j--;
    }
    int q = (p + r)/2;
    bitonicSorter(array, p, q);
    bitonicSorter(array, q+1, r);
}

/**
 * makes halfClean of subArray (recursive calls itself for halfClean smaller subArrays of input subArray)
 * @param array - array with data
 * @param p - start of subArray
 * @param r - end of subArray
 */
void bitonicSorter(int *array, const int p, const int r){
    int q = (p+r)/2;
    if(p < r){
        halfCleaner(array, p, r);
        bitonicSorter(array, p, q);
        bitonicSorter(array, q+1, r);
    }
}

/**
 * makes bitonic sequence
 * @param array - array with data
 * @param p - start of subArray
 * @param r - end of subArray
 */
void halfCleaner(int *array, const int p, const int r){
    int q = (p+r)/2;
    for (int i = 0; i < (r-p+1)/2; ++i){
        comparator(array, p+i, q+1+i);
    }
}

/**
 * compare values with ids in array and swap them if first > second
 * @param array - array with values
 * @param i - first index
 * @param j - second index
 */
void comparator(int *array, const int i, const int j){
    if(array[i] > array[j]){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

/**
 * calls bitonicSort for array with data and use it sort array
 * @param array - array with data
 */
void sortParallel(int *array, const int length){
    #pragma omp parallel
    {
        #pragma omp single nowait
            sorter(array, 0, length-1, MIN_ELEMENTS_FOR_PARALLELISM);
    }
}

/**
 * calls sorter for subArrays of current subArray of input array and merge sorted subArrays
 * @param array - array with data
 * @param p - start of subArray
 * @param r - end of subArray
 */
void sorter(int *array, const int p, const int r, const int lowLimit){
    int q = (p+r)/2;
    if ((r - p) < lowLimit) { 
        sorter (array, p, r); 
    } else { 
        if(p < q){
            #pragma omp task firstprivate(array, lowLimit, p, q) 
                sorter(array, p, q);
        }
        if(q+1 < r){
            #pragma omp task firstprivate(array, lowLimit, r, q) 
                sorter(array, q+1, r);
        }
        #pragma omp taskwait
        merger(array, p ,r);
    }
}