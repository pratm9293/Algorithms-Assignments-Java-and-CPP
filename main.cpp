#include <iostream>
#include <fstream>
#include <climits>
#include <cstdlib>
using namespace std;

class bucketSort {
    int max, min, lastIndex;
    int *BucketAry;
    public:
        bucketSort(int x, int y) {
            min = x;
            max = y;
            lastIndex = (max-min);
            BucketAry = new int[lastIndex+1];
            for(int i = 0; i<lastIndex; i++){
                BucketAry[i] = 0;
            }
        }
        static int *findMinMax(fstream &inFile){
            int y=INT_MIN, x=INT_MAX, number;
            static int numAry[2];

            while(inFile >> number){
                if(number<0) {
                    cout << "Error: A negative value was read!" << endl;
                    exit(0);
                }
                if(number<x){
                    numAry[0] = number;
                    x = number;
                }
                if(number>y){
                    numAry[1] = number;
                    y = number;
                }
            }
                return numAry;
        }
        int getIndex(int data){
            BucketAry[data-min]++;
            return BucketAry[data-min];
        }
        void printSortData(fstream *outFile){
            for(int i = 0; i<=lastIndex; i++){
                for(int j = BucketAry[i]; j!= 0; j--){
                    *outFile << (min+i) << endl;
                }
            }
            outFile->close();
        }
};







int main(int argc, char* argv[])
{
    int number;
    int *numAry;
    fstream myFile;
    myFile.open(argv[1]);
    if(!myFile.is_open()){
        cout << "File did not open!" << endl;
        exit(0);
    }
    numAry = bucketSort::findMinMax(myFile);
    myFile.close();

    myFile.open(argv[1]);
    bucketSort *b = new bucketSort(numAry[0],numAry[1]);
    while(myFile >> number){
        b->getIndex(number);
    }

    myFile.close();
    myFile.open(argv[2]);
    b->printSortData(&myFile);
    myFile.close();
    delete b;

    return 0;

}

