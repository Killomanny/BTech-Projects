#include <iostream>
#include <thread>
#include <atomic>
#include <stdlib.h>
#include <unistd.h>
#include <fstream>
#include <algorithm>
#include <random>
#include <vector>

using namespace std;
using namespace std::chrono;

int n, k;
double l1, l2;                         // from the question taking variables
atomic_flag locker = ATOMIC_FLAG_INIT; // using atomic flag

vector<double> meantime; // contains all the waiting times

void *testTAS(void *id)
{

    int tid = *(int *)id;

    std::random_device rd; // True random number generator
    std::mt19937 gen(rd());

    double a = l1;
    double b = l2;
    a = 1 / a;
    b = 1 / b;

    exponential_distribution<double> t1(a); // critical section time
    exponential_distribution<double> t2(b); // remainder section time

    std::ofstream op_2;
    op_2.open("out_tas.txt", std::ios_base::app);

    auto starttime = high_resolution_clock::now();

    for (int i = 0; i < k; i++)
    {

        auto stoptime = high_resolution_clock::now(); // stoping the time
        auto duration = duration_cast<microseconds>(stoptime - starttime);

        string a = to_string(i + 1) + " CS Request from thread " + to_string(tid) + " at time : " + to_string(duration.count()) + " microseconds \n";
        op_2 << a;

        double c = duration.count();

        while (locker.test_and_set())
            ;

        stoptime = high_resolution_clock::now(); // stoping the time
        duration = duration_cast<microseconds>(stoptime - starttime);

        c = duration.count() - c;
        meantime.push_back(c);

        a = to_string(i + 1) + " CS entry from thread " + to_string(tid) + " at time : " + to_string(duration.count()) + " microseconds \n";
        op_2 << a;

        double ab = t1(gen) * 1000;
        usleep(ab); // Simulation of critical-section

        stoptime = high_resolution_clock::now(); // stoping the time
        duration = duration_cast<microseconds>(stoptime - starttime);

        a = to_string(i + 1) + " CS Exit by thread " + to_string(tid) + " at time : " + to_string(duration.count()) + " microseconds\n";
        op_2 << a;

        locker.clear(); // Exit Section

        usleep(t2(gen) * 1000); // Simulation of Reminder Section
    }
}

int main()
{

    ifstream input;
    input.open("input.txt");
    input >> n >> k >> l1 >> l2;
    input.close();

    ofstream op_3;
    op_3.open("out_tas.txt", ios::out | ios::trunc);
    op_3.close();

    meantime.reserve(n * k * 2);

    pthread_t threads[n]; // // creating no_of_thread many thread ids
    int thread_ids[n];
    pthread_attr_t attr;
    pthread_attr_init(&attr); // Setting to default attribtes of thread

    for (int i = 0; i < n; i++)
    {
        thread_ids[i] = i + 1;
        pthread_create(&threads[i], &attr, testTAS, &thread_ids[i]); // creating the threads
    }

    for (int i = 0; i < n; i++)
    {
        pthread_join(threads[i], NULL); // joining the threads
    }

    double kt = 0;

    for (auto i : meantime)
    {
        kt = kt + i;
    }

    kt = kt / (n * k); // calculating the average waiting time

    double pt = *max_element(meantime.begin(), meantime.end()) / 1000; // calculating the worst waiting time

    cout << "Average waiting time :" << kt / 1000 << " in milli seconds\n";
    cout << "Max waiting time :" << pt << " in milli seconds\n";

    return 0;
}
