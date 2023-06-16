#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <math.h>
#include <stdbool.h>
#include <time.h>

int total_threads;
int dimension;
int **intMatrix;
FILE *fg;

// function which checks if it is sudoku or not
int checksudoko1(int p)
{
    int i = p;

    int array1[dimension + 1];

    for (int ij = 0; ij <= dimension; ij++)
    {
        array1[ij] = 0;
    }

    if (i <= dimension) // // here columns are checking
    {
        for (int ij = 1; ij <= dimension; ij++)
        {
            int pf = intMatrix[ij][i];
            array1[pf] = 1;
        }
    }
    else
    {
        if (i <= 2 * dimension) // here rows are checking
        {
            for (int ij = 1; ij <= dimension; ij++)
            {
                int pf = intMatrix[i - dimension][ij];
                array1[pf] = 1;
            }
        }
        else // here grids are checking
        {

            double c = dimension;
            c = sqrt(c) + 0.5;
            int sqrt_dimension = c;
            int inj = i - 2 * dimension;

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

    if (sum != (dimension)) // final check of the following array which came from above
    {
        return 0;
    }
    return 1;
}

int main()
{

    double start_time, run_time;

    FILE *open = fopen("input.txt", "r");

    // taking input from file total threads and dimension
    fscanf(open, "%d", &total_threads);
    fscanf(open, "%d", &dimension);

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

    // getting input from the file for the sudoku
    for (int i = 1; i <= dimension; i++)
    {
        for (int j = 1; j <= dimension; j++)
        {
            fscanf(open, "%d", &intMatrix[i][j]);
        }
    }

    // setting the number of threads
    omp_set_num_threads(total_threads);

    int array10[(3 * dimension) + 1];
    int array11[(3 * dimension) + 1];

    // starting time
    start_time = omp_get_wtime();

// here using omp
#pragma omp parallel for
    for (int i = 1; i <= 3 * dimension; i++)
    {
        array11[i] = checksudoko1(i);
        array10[i] = omp_get_thread_num();
    }

    run_time = omp_get_wtime() - start_time;

    FILE *p;
    p = fopen("Output.txt", "w");
    fclose(p);

    int solve = 1;

    FILE *f;
    f = fopen("Output.txt", "a");

    // printing the log into a file
    for (int i = 1; i <= 3 * dimension; i++)
    {
        int a = i;
        int b = array10[i];
        int c = array11[i];

        if (a <= dimension && a != 0)
        {
            if (c == 0)
            {
                fprintf(f, "thread %d checked coloumn no %d and found to be invalid\n", b, a);
                solve = 0;
            }
            else
            {
                fprintf(f, "thread %d checked coloumn no %d and found to be valid\n", b, a);
            }
        }

        if (a > dimension && a <= 2 * dimension)
        {
            if (c == 0)
            {
                fprintf(f, "thread %d checked row no %d and found to be invalid\n", b, a - dimension);
                solve = 0;
            }
            else
            {
                fprintf(f, "thread %d checked row no %d and found to be valid\n", b, a - dimension);
            }
        }

        if (a > 2 * dimension && a <= 3 * dimension)
        {
            if (c == 0)
            {
                fprintf(f, "thread %d checked grid no %d and found to be invalid\n", b, a - dimension * 2);
                solve = 0;
            }
            else
            {
                fprintf(f, "thread %d checked grid no %d and found to be valid\n", b, a - dimension * 2);
            }
        }
    }

    if (solve == 0)
    {
        fprintf(f, "Sudoko is invalid\n");
    }
    else
    {
        fprintf(f, "Sudoko is valid\n");
    }

    fprintf(f, "Total time taken is %f in seconds\n", run_time);

    fclose(f);
    fclose(open);

    return 0;
}