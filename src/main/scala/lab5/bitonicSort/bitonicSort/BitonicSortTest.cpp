#include <stdlib.h>
#include <omp.h>
#include <iostream>
#include "ArrayActions.h"
#include "BitonicSort.h"
#include "Utils.h"

int main(void) {
    while(1){
        const int count = 10;
        const int numElements = scanfLengthOfVector();
        int *in, *parallelIn, *serialIn;
        double t_start, t_finish, parallelMiddleTime = 0, serialMiddleTime = 0;
        
        if(!isPowerOf2(numElements)){
            std::cout << "Length must be power of 2. Try again. Next power of 2 = " <<
                nextPowerOf2(numElements) << std::endl;
            continue;
        }

        in = generateRandomVector(numElements);

        for(int  i = 0; i < count; i++){
            serialIn = copyVector(in, numElements);
            parallelIn = copyVector(in, numElements);
            
            t_start = omp_get_wtime();
            sort(serialIn, numElements);
            t_finish = omp_get_wtime();
            serialMiddleTime = serialMiddleTime + t_finish-t_start;

            t_start = omp_get_wtime();
            sortParallel(parallelIn, numElements);
            t_finish = omp_get_wtime();
            parallelMiddleTime = parallelMiddleTime + t_finish-t_start;

            free(serialIn);
            free(parallelIn);
        }

        std::cout << "Time serial middle = " << serialMiddleTime/count << std::endl;
        std::cout << "Time parallel middle = " << parallelMiddleTime/count << std::endl;
        
        if(isCorrect(serialIn, numElements)){
                std::cout << "Serial sorted correct!" << std::endl << std::endl;
        }
        if(isCorrect(parallelIn, numElements)){
                std::cout << "Parallel sorted correct!" << std::endl << std::endl;
        }

        free(in);
    }
    return 0;
}