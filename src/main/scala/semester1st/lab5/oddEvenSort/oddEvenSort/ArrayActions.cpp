#include "ArrayActions.h"

/**
* print vector
*/
void printVector(int *vec, const int length){
	std::cout << "Test vector:" << std::endl;
	for(int i = 0; i < length; i++){
		std::cout << vec[i] << "\t";
		if((i+1)%10 == 0){
			std::cout << std::endl;
		}
    }
	std::cout << std::endl;
}

/**
* create new vector and fill it with random values
*/
int *generateRandomVector(const int length){
	int *in = (int *)malloc(length * sizeof(int));
	for(int i = 0; i < length; ++i){
		in[i] = rand()%1024 + 1;
	}
	return in;
}

/**
* create new vector with lenght and copy to it all values from input vector
*/
int *copyVector(int *vec, const int length){
	int *copy = (int *)malloc(length * sizeof(int));
	for(int i = 0; i < length; ++i){
		copy[i] = vec[i];
	}
	return copy;
}

/**
* set one value for all items of array
*/
void setValue(int *array, const int length, const int value){
	for(int i = 0; i < length; i++){
		array[i] = value;
	}
}