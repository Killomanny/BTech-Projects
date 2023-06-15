
#include "types.h"
#include "stat.h"
#include "user.h"

// int arrGlobal[10000000]; // Global array

int
main(int argc, char *argv[])
{

  int arrGlobal[10000]; // Global array

  for(int i = 0 ; i < 10000 ; i++ )
  {
    arrGlobal[i]++ ;
  }

  pgtPrint();
  exit();

}


