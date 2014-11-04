/* 
 * File:   OddEvenSortTest.cpp
 * Author: kol
 *
 * Created on 17 листопада 2013, 5:20
 */

#include <stdlib.h>
#include <omp.h>
#include <iostream>
#include "ArrayActions.h"
#include "OddEvenSort.h"

int scanfLengthOfVector();
bool isCorrect(int *, int *, const int);

int main(void) {
    while(1){
        const int count = 1;
        const int numElements = scanfLengthOfVector();
        int *in, *oddEvenParallelIn, *oddEvenSerialIn;
        double t_start, t_finish, parallelMiddleTime = 0, serialMiddleTime = 0;
        
        in = generateRandomVector(numElements);

        for(int  i = 0; i < count; i++){
            oddEvenSerialIn = copyVector(in, numElements);
            oddEvenParallelIn = copyVector(in, numElements);

            t_start = omp_get_wtime();
            oddEvenSortParallel(oddEvenParallelIn, numElements);
            t_finish = omp_get_wtime();
            parallelMiddleTime = parallelMiddleTime + t_finish-t_start;
            
            t_start = omp_get_wtime();
            oddEvenSortSerial(oddEvenSerialIn, numElements);
            t_finish = omp_get_wtime();
            serialMiddleTime = serialMiddleTime + t_finish-t_start;
        }

        std::cout << "Time serial middle = " << serialMiddleTime/count << std::endl;
        std::cout << "Time parallel middle = " << parallelMiddleTime/count << std::endl;
        if(isCorrect(oddEvenSerialIn, oddEvenParallelIn, numElements)){
                std::cout << "Sorted correct!" << std::endl << std::endl;
        }
        free(in);
        free(oddEvenSerialIn);
        free(oddEvenParallelIn);
    }
    return 0;
}

int scanfLengthOfVector(){
    int lengthOfVector;
    std::cout << "Input length of vector: ";
    std::cin >> lengthOfVector;
    return lengthOfVector;
}

/**
* compare two vectors term by term
* return true if all elements equals
*/
bool isCorrect(int *a, int *b, const int length){
    for(int i = 0; i < length; i++){
        if(a[i] != b[i]){
            return false;
        }
    }
    return true;
}