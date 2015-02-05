#include "Utils.h"

/**
 * verify if number power of 2
 * @param number - maybe power of 2 (maybe not)
 * @return true if number really power of 2, else return false
 */
bool isPowerOf2(int number){
    while(number%2 != 1){
        number /= 2;
    }
    return number == 1;
}

/**
 * find next, for given number, power of 2
 * @param number - given number
 * @return pow of 2
 */
int nextPowerOf2(int number){
    if(isPowerOf2(number)){
        return number;
    }
    int nextNumber = 1;
    while(nextNumber < number){
        nextNumber *= 2;
    }
    return nextNumber;
}

/**
 * find prev, for given number, power of 2
 * @param number - given number
 * @return pow of 2
 */
int prevPowerOf2(int number){
    return nextPowerOf2(number)/2;
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
bool isCorrect(const int *a, const int *b, const int length){
    for(int i = 0; i < length; i++){
        if(a[i] != b[i]){
            return false;
        }
    }
    return true;
}

/**
* verify if vector sorted
* return true if all a[i] <= a[i+1]
*/
bool isCorrect(const int *a, const int length){
    for(int i = 0; i < length-1; i++){
        if(a[i] > a[i+1]){
            return false;
        }
    }
    return true;
}