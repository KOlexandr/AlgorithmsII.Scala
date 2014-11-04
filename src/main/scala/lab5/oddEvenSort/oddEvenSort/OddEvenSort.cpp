#include "OddEvenSort.h"

/**
 * sort vector use odd-even sort algorithm
 * @param array
 * @param length
 */
void oddEvenSortSerial(int *array, const int length){
    for (int i = 0; i < length; i++) {
        if (i % 2 == 1) {
            for (int j = 0; j < length/2 - 1; j++){
                compareExchange(&array[2 * j + 1], &array[2 * j + 2]);
            }
            if (length % 2 == 1){
                compareExchange(&array[length - 2], &array[length - 1]);
            }
        } else {
            for (int j = 0; j < length/2; j++){
                compareExchange(&array[2 * j], &array[2 * j + 1]);
            }
        }
    }
}

/**
 * sort vector use odd-even sort parallel algorithm with openmp
 * @param array
 * @param length
 */
void oddEvenSortParallel(int *array, const int length){
    #pragma omp parallel default(none) shared(array)
    {
        for (int i = 0; i < length; i++) {
            if (i % 2 == 1) {
                #pragma omp for
                    for (int j = 0; j < length/2 - 1; j++){
                        compareExchange(&array[2 * j + 1], &array[2 * j + 2]);
                    }
                if (length % 2 == 1){
                    compareExchange(&array[length - 2], &array[length - 1]);
                }
            } else {
                #pragma omp for
                    for (int j = 0; j < length/2; j++){
                        compareExchange(&array[2 * j], &array[2 * j + 1]);
                    }
            }
        }
    }
}

/**
 * verify if value *a > *b ant if it true swap values
 * @param a
 * @param b
 */
void compareExchange(int *a, int *b){
    if(*a > *b){
        int tmp = *a;
    	*a = *b;
    	*b = tmp;
    }
}