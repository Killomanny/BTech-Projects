#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <math.h>
#include <stdbool.h>
#include <time.h>

int dimension;
int total_threads;
int **intMatrix;
FILE *fg;
pthread_mutex_t lock;

int a = 1;

struct data_set // used for seeing thread number inside a  particular thread
{
    int threadNumber;
};
typedef struct data_set data;

struct finale2 // this is used in thread function like the pointer to this thread is returned at end of start_routine()
{
    int *che;
    int *valid;
    int a;
};
typedef struct finale2 finale2;

// This is threaded function
// This returns a dynamically allocated memory which contains details regarding this thread
// Pointer to this memory location is shared to main function
// Now Main memory can use this location
void *checksudoko1(void *val)
{

    data *set = (data *)val;
    int i = set->threadNumber;
    int lk = i;

    int max_size = ((3 * dimension) / total_threads) + 1;

    finale2 *kk = (finale2 *)calloc(1, sizeof(finale2));
    kk->che = (int *)calloc(max_size, sizeof(int));
    kk->valid = (int *)calloc(max_size, sizeof(int));

    int ch = 0;

    for (int hp = lk; hp <= 3 * dimension && a == 1; hp = hp + total_threads)
    {
        int array1[dimension + 1];

        for (int ij = 0; ij <= dimension; ij++)
        {
            array1[ij] = 0;
        }

        if (hp <= dimension) // here columns are checking
        {
            for (int ij = 1; ij <= dimension; ij++)
            {
                int pf = intMatrix[ij][hp];
                array1[pf] = 1;
            }
        }
        else
        {
            if (hp <= 2 * dimension) // here rows are checking
            {
                for (int ij = 1; ij <= dimension; ij++)
                {
                    int pf = intMatrix[hp - dimension][ij];
                    array1[pf] = 1;
                }
            }
            else // here grids are checking
            {
                double c = dimension;
                c = sqrt(c) + 0.5;
                int sqrt_dimension = c;
                int inj = hp - 2 * dimension;

                int hu = (inj - 1) / sqrt_dimension;
                hu = hu * sqrt_dimension + 1;
                int pu = (inj % sqrt_dimension - 1) * sqrt_dimension + 1;

                if (pu < 0)
                {
                    pu = pu + dimension;
                }

                for (int it = hu; it < sqrt_dimension + hu; it++)
                {
                    for (int j = pu; j < sqrt_dimension + pu; j++)
                    {
                        int pu = intMatrix[it][j];
                        array1[pu] = 1;
                    }
                }
            }
        }

        int sum = 0;

        for (int ik = 0; ik <= dimension; ik++)
        {
            sum = sum + array1[ik];
        }

        kk->che[ch] = hp;

        if (sum != (dimension)) // checking the following arrays
        {
            kk->valid[ch] = 0;
            ch++;
            kk->a = ch;

            // using the mutex for accessing and changing the global variable
            // this is related to how thread cancellation occurs

            pthread_mutex_lock(&lock);
            a = 0;
            pthread_mutex_unlock(&lock);
        }
        else
        {
            kk->valid[ch] = 1;
            ch++;
            kk->a = ch;
        }
    }

    return (void *)kk; // returning the kk here but typecasting it to (void *)
}

int main()
{

    FILE *open = fopen("input.txt", "r");

    fscanf(open, "%d", &total_threads);
    fscanf(open, "%d", &dimension);

    pthread_mutex_init(&lock, NULL);

    intMatrix = (int **)malloc((dimension + 1) * sizeof(int *));

    for (int i = 0; i < dimension + 1; ++i)
        intMatrix[i] = (int *)malloc((dimension + 1) * sizeof(int));

    for (int i = 0; i <= dimension; i++)
    {
        for (int j = 0; j <= dimension; j++)
        {
            intMatrix[i][j] = 0;
        }
    }

    for (int i = 1; i <= dimension; i++)
    {
        for (int j = 1; j <= dimension; j++)
        {
            fscanf(open, "%d", &intMatrix[i][j]);
        }
    }

    clock_t start, end;
    double cpu_time_used;
    start = clock();

    pthread_t tid[total_threads]; // creating no_of_thread many thread ids
    pthread_attr_t attr;
    pthread_attr_init(&attr); // Setting to default attribtes of thread

    for (int i = 0; i < total_threads; i++)
    {
        data *set = (data *)calloc(1, sizeof(data));
        set->threadNumber = i + 1;
        pthread_create(&tid[i], &attr, checksudoko1, set); // creating the threads
    }

    finale2 *main_case[total_threads];

    for (int i = 0; i < total_threads; i++)
    {
        pthread_join(tid[i], (void **)&main_case[i]);
        // This function call ensures the full completion of the thread execution
        // This also stores the returned void ** value from the threads created to a array named main_case
    }

    end = clock();
    cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;

    int klp = ((3 * dimension) / total_threads) + 1;
    int solve = 1;

    FILE *p;
    p = fopen("Output.txt", "w");
    fclose(p);

    FILE *f;
    f = fopen("Output.txt", "a");

    // outputing various values into output.txt this time won't be calculated into computed time as Pi is already computed above
    for (int i = 0; i < total_threads; i++)
    {

        for (int j = 0; j < main_case[i]->a; j++)
        {
            int a = main_case[i]->che[j];
            int b = main_case[i]->valid[j];

            if (a <= dimension && a != 0)
            {
                if (b == 0)
                {
                    fprintf(f, "thread %d checked coloumn no %d and found to be invalid\n", i + 1, a);
                    solve = 0;
                }
                else
                {
                    fprintf(f, "thread %d checked coloumn no %d and found to be valid\n", i + 1, a);
                }
            }

            if (a > dimension && a <= 2 * dimension)
            {
                if (b == 0)
                {
                    fprintf(f, "thread %d checked row no %d and found to be invalid\n", i + 1, a - dimension);
                    solve = 0;
                }
                else
                {
                    fprintf(f, "thread %d checked row no %d and found to be valid\n", i + 1, a - dimension);
                }
            }

            if (a > 2 * dimension && a <= 3 * dimension)
            {
                if (b == 0)
                {
                    fprintf(f, "thread %d checked grid no %d and found to be invalid\n", i + 1, a - dimension * 2);
                    solve = 0;
                }
                else
                {
                    fprintf(f, "thread %d checked grid no %d and found to be valid\n", i + 1, a - dimension * 2);
                }
            }
        }

        // releasing the memory
        free(main_case[i]->che);
        free(main_case[i]->valid);
        free(main_case[i]);
    }

    if (solve == 0)
    {
        fprintf(f, "Sudoko is invalid\n");
    }
    else
    {
        fprintf(f, "Sudoko is valid\n");
    }

    fprintf(f, "Total time taken is %f in seconds\n", cpu_time_used);

    fclose(f);
    fclose(open);

    pthread_mutex_destroy(&lock);
    pthread_exit(NULL);

    return 0;
}
