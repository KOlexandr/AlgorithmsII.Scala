#include <stdlib.h>
#define MIN_ELEMENTS_FOR_PARALLELISM 1000

void sort(int *, const int);
void sorter(int *, const int, const int);
void merger(int *, const int, const int); 
void bitonicSorter(int *, const int, const int);
void halfCleaner(int *, const int, const int);
void comparator(int *, const int, const int);
void sortParallel(int *, const int);
void sorter(int *, const int, const int, const int);