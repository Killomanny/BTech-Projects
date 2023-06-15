#include <iostream>
#include <bits/stdc++.h>
#include <fstream>
#include <vector>

using namespace std;

int main()
{

    int a, b, c, d;

    ////////////////////////////////////////////////////////////////////////////
    // implementing theh fifo

    ifstream input;
    input.open("inp.txt"); // reading the input
    input >> a >> b;         // a contains the framecount while b as size of page

    int arr[a];
    for (int i = 0; i < a; i++)
    {
        arr[i] = -1;
    }

    int firstminus = 0;
    int checker = 0;
    int no_of_faults = 0;

    while (input)
    {
        input >> c;

        if (!input)
        {
            break;
        }

        for (int i = 0; i < a; i++)
        {
            if (arr[i] == (c / b))
            {
                checker = 1;
                break;
            }
        }
        if (checker == 0)
        {
            no_of_faults = no_of_faults + 1;
            if (firstminus == (a))
            {
                for (int i = 0; i <= a - 2; i++)
                {
                    arr[i] = arr[i + 1];
                }
                arr[a - 1] = (c / b);
            }
            else
            {
                arr[firstminus] = (c / b);
                firstminus = firstminus + 1;
            }
        }
        checker = 0;
    }
    cout << "No of page faults with FIFO: " << no_of_faults << "\n";
    input.close();

    ///////////////////////////////////////////////////////////////////////////////
    //  implementing the LRU

    input.open("inp.txt"); // reading the input
    input >> a >> b;

    firstminus = 0;
    no_of_faults = 0;
    checker = 0;

    for (int i = 0; i < a; i++)
    {
        arr[i] = -1;
    }

    while (input)
    {
        input >> c;

        if (!input)
        {
            break;
        }

        for (int i = 0; i < a; i++)
        {
            if (arr[i] == (c / b))
            {
                checker = 1;
                int p = arr[i];
                for (int j = i; j < (firstminus - 1); j++)
                {
                    arr[j] = arr[j + 1];
                }
                arr[firstminus - 1] = p;
                break;
            }
        }
        if (checker == 0)
        {
            no_of_faults = no_of_faults + 1;
            if (firstminus == (a))
            {
                for (int i = 0; i <= a - 2; i++)
                {
                    arr[i] = arr[i + 1];
                }
                arr[a - 1] = (c / b);
            }
            else
            {
                arr[firstminus] = (c / b);
                firstminus = firstminus + 1;
            }
        }
        checker = 0;
    }
    cout << "No of page faults with LRU: " << no_of_faults << "\n";
    input.close();

    ////////////////////////////////////////////////////////////////////////////////
    // implementing the opt

    input.open("inp.txt"); // reading the input
    input >> a >> b;

    firstminus = 0;
    no_of_faults = 0;
    checker = 0;

    for (int i = 0; i < a; i++)
    {
        arr[i] = -1;
    }

    vector<int> v1;

    while (input)
    {
        input >> c;

        if (!input)
        {
            break;
        }

        int pj = c / b;

        v1.push_back(pj);
    }

    int size = v1.size();
    int goer = 0;

    int arr5[size];
    for (int i = 0; i < size; i++)
    {
        arr5[i] = v1[i];
    }

    while (goer != size)
    {
        for (int i = 0; i < a; i++)
        {
            if (arr[i] == arr5[goer])
            {
                checker = 1;
            }
        }
        if (checker == 0)
        {
            no_of_faults = no_of_faults + 1;

            if (firstminus == a)
            {
                int arr2[a];
                for (int k = 0; k < a; k++)
                {
                    arr2[k] = size;
                }

                for (int k = 0; k < a; k++)
                {
                    int newt = goer + 1;
                    int dkl = 0;

                    while (newt != size && dkl == 0)
                    {
                        if (arr[k] == v1[newt])
                        {
                            arr2[k] = newt;
                            dkl = 1;
                        }
                        newt = newt + 1;
                    }
                }

                int df = 0;
                int p = 0;

                for (int kj = 0; kj < a; kj++)
                {
                    if (df < arr2[kj])
                    {
                        df = arr2[kj];
                        p = kj;
                    }
                }

                arr[p] = arr5[goer];
            }
            else
            {
                arr[firstminus] = arr5[goer];
                firstminus = firstminus + 1;
            }
        }

        checker = 0;
        goer = goer + 1;
    }

    cout << "No of page faults with OPT: " << no_of_faults << "\n";

    return 0;
}