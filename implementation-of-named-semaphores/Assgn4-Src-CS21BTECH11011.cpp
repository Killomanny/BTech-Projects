#include <pthread.h>
#include <iostream>
#include <atomic>
#include <unistd.h>
#include <fstream>
#include <random>
#include <vector>
#include <stdlib.h>
#include <thread>
#include <algorithm>
#include <semaphore.h>
#include <queue>

using namespace std;
using namespace std::chrono;

// variables taken from inp-params.txt file
int p, c, k;
double lp, lc;

int *pa;
int *ca;

// used for storing and checking car availability
queue<int> cars_queue;

sem_t *sem_cars;  // semaphore for available cars
sem_t *pas_mutex; // Semaphore for mutual exclusion between passenger processes

void *passengers_and_car(void *id)
{
    // file handling
    std::ofstream op_2;
    op_2.open("output.txt", std::ios_base::app);

    // starting time for each thread based on their arrival which wiwll be almost same
    auto starttime = high_resolution_clock::now();

    // getting the value of passenger id here
    int idp;
    idp = *(int *)(id);

    std::random_device rd; // True random number generator
    std::mt19937 gen(rd());

    double a = lp;
    a = 1 / a;

    int rides = k;

    double b = lc;
    b = 1 / b;

    exponential_distribution<double> t2(b); // critical section time
    exponential_distribution<double> t1(a);

    auto stoptime = high_resolution_clock::now(); // stoping the time
    auto duration = duration_cast<microseconds>(stoptime - starttime);

    string strp = "Passenger " + to_string(idp) + " enters the museum at " + to_string(duration.count()) + "ms from the start \n";
    string strc;

    op_2 << strp;

    int carnumber = 0;

    for (int i = 0; i < k; i++)
    {
        usleep(1000 * t1(gen)); // it's more like before 1st ride also wandering

        stoptime = high_resolution_clock::now(); // stoping the time
        duration = duration_cast<microseconds>(stoptime - starttime);

        strp = "Passenger " + to_string(idp) + " made a ride request at " + to_string(duration.count()) + "ms from the start \n";

        op_2 << strp;

        // checking if car is available
        sem_wait(sem_cars);

        // entered so he took some car
        stoptime = high_resolution_clock::now(); // stoping the time
        duration = duration_cast<microseconds>(stoptime - starttime);

        sem_wait(pas_mutex);
        // getting his car number
        carnumber = cars_queue.front();
        // removing the car he took from the queue
        cars_queue.pop();
        sem_post(pas_mutex);

        strp = "Car " + to_string(carnumber) + " accepts Passenger " + to_string(idp) + " request \n";
        op_2 << strp;

        string j = to_string(carnumber);

        strp = "Passenger " + to_string(idp) + " started riding at " + to_string(duration.count()) + "ms from the start \n" + "Car " + to_string(carnumber) + " is riding Passenger " + to_string(idp) + "\n";
        op_2 << strp;

        int jk = duration.count();

        // the time in the car is this value
        usleep(1000 * t2(gen));

        stoptime = high_resolution_clock::now(); // stoping the time
        duration = duration_cast<microseconds>(stoptime - starttime);

        strp = "Car " + to_string(carnumber) + " has finished Passenger " + to_string(idp) + " tour \n" + "Passenger " + to_string(idp) + " finished riding at " + to_string(duration.count()) + "ms from the start \n";
        op_2 << strp;

        int kj = duration.count();

        if (i == k - 1)
        { // calculating the time after passengers last ride
            pa[idp] = duration.count();
            strp = "Passenger " + to_string(idp) + " exits museum at " + to_string(duration.count()) + "ms from the start \n";
            op_2 << strp ;
        }

        sem_wait(pas_mutex);
        // passing the car into available after completing of ride along with storing the time into an array
        cars_queue.push(carnumber);
        ca[carnumber] = duration.count();
        sem_post(pas_mutex);

        sem_post(sem_cars);
    }
}

signed main()
{

    // reading input from the inp-params.txt
    ifstream input;
    input.open("inp-params.txt"); // reading the input
    input >> p >> c >> lp >> lc >> k;
    input.close();

    // creating or truncating the file named output.txt for storing various information related to time
    ofstream op_3;
    op_3.open("output.txt", ios::out | ios::trunc);
    op_3.close();

    // creating array for storing values to calculate in graphs
    pa = new int[p + 1];
    ca = new int[c + 1];

    // initializing semaphores

    const char *sem_name1 = "/semaphore1";
    sem_cars = sem_open(sem_name1, O_CREAT | O_EXCL, 0644, c);

    const char *sem_name3 = "/semaphore3";
    pas_mutex = sem_open(sem_name3, O_CREAT | O_EXCL, 0644, 1);

    pthread_t threads_pass[p]; // creating p no of thread ids
    int thread_ids_pass[p];    // passing id of passenger here

    for (int i = 0; i < c; i++)
    {
        cars_queue.push(i + 1);
    }

    pthread_attr_t attr;
    pthread_attr_init(&attr); // Setting to default attribtes of thread

    for (int i = 0; i < p; i++)
    {
        thread_ids_pass[i] = i + 1;
        pthread_create(&threads_pass[i], &attr, passengers_and_car, &thread_ids_pass[i]); // creating the threads
    }

    for (int i = 0; i < p; i++)
    {
        pthread_join(threads_pass[i], NULL); // joining threads
    }

    // closing the semaphore swhich are initialized
    sem_close(sem_cars);
    sem_unlink(sem_name1);

    sem_close(pas_mutex);
    sem_unlink(sem_name3);

    double ps = 0;
    double cs = 0;

    for (int i = 1; i <= p; i++)
    {
        ps = ps + pa[i];
    }

    // calculating average time taken by passenger to complete his tour in milliseconds rather the time
    ps = (ps / 1000) / p;

    for (int i = 1; i <= c; i++)
    {
        cs = cs + ca[i];
    }

    // calculating the average time takemn by car to complete its tour in that day
    cs = (cs / 1000) / c;

    // printing so see and taking the average of these in final calculations for graph
    cout << ps << " " << cs << "\n";

    return 0;
}