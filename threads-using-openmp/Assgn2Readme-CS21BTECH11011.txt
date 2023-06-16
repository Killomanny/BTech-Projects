- PROGRAMMING ASSIGNMENT 2
- CS21BTECH11011
- B.ASLI NITEJ REDDY

This is a README for my assignment2 code 




For pthreads
 - gcc Assgn2Srcpthread-CS21BTECH11011.c -lpthread -lm
 - ./a.out


For openmp
 - gcc-12 Assgn2SrcOpenMp-CS21BTECH11011.c -fopenmp -lm
 - ./a.out




# above gcc-12 instead of gcc because in Mac the default gcc is not recognising the openmp

# in linux 
     you can run gcc Assgn2SrcOpenMp-CS21BTECH11011.c -fopenmp -lm directly

# after those there is sub folder named extra-credit which contains the name like
# early_termination_pthreads-CS21BTECH11011.c 




For extra-credit ( go into extra-credit folder by cd ./extra-credit here again generate input.txt)
 - gcc early_termination_pthreads-CS21BTECH11011.c -lpthread -lm
 - ./a.out




Note:

For pthread and openmp both codes and input.txt should present in same directory

In extra-credit folder also input.txt will be present like two input.txt but different directory here again early_termination_pthreads-CS21BTECH11011.c and input.txt should be present

Output file is named as Output.txt 










