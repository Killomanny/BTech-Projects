#include "types.h"
#include "x86.h"
#include "defs.h"
#include "date.h"
#include "param.h"
#include "memlayout.h"
#include "mmu.h"
#include "proc.h"

int sys_date(void)
{
  struct rtcdate *r;

  if (argptr(0, (void *)&r, sizeof(*r)) < 0)
    return -1;

  cmostime(r);
  return 0;
}

// In sysproc.c

// Definition of pgtPrint system call
// int sys_pgtPrint(void) {
//   uint* pgdir = (uint*)myproc()->pgdir; // Get the pointer to the page directory
//   cprintf("Page table entries for process %d:\n", myproc()->pid);
//   for (int i = 0; i < NPDENTRIES; i++) {
//     if (pgdir[i] & PTE_P) { // Check if the page table is present
//       uint* pgtab = (uint*)P2V(PTE_ADDR(pgdir[i])); // Get the virtual address of the page table
//       for (int j = 0; j < NPTENTRIES; j++) {
//         if (pgtab[j] & PTE_P && pgtab[j] & PTE_U) { // Check if the page is present and user-accessible
//           uint va = (i * NPTENTRIES + j) * PGSIZE;
//           cprintf("Entry number: %d, Virtual address: %p, Physical address: %p\n", i * NPTENTRIES + j, (void*)va, (void*)PTE_ADDR(pgtab[j]));
//         }
//       }
//     }
//   }
//   return 0;
// }

// sys_pgtPrint - prints the page table entries for the current process
int sys_pgtPrint(void)
{
  pde_t *pgdir = myproc()->pgdir; // get the current process's page directory
  pte_t *pte;
  int count = 0;
  int total_count = 0;

  // iterate through all page directory entries
  for (int i = 0; i < NPDENTRIES; i++)
  {
    // check if the page table is present and user-accessible
    if (pgdir[i] & PTE_P && pgdir[i] & PTE_U)
    {
      // get the physical address of the page table
      pte = (pte_t *)P2V(PTE_ADDR(pgdir[i]));

      // iterate through all page table entries
      for (int j = 0; j < NPTENTRIES; j++)
      {

        total_count++;

        // check if the page is present and user-accessible
        if (pte[j] & PTE_P && pte[j] & PTE_U)
        {
          // print the page table entry
          count++; // increment counter
          cprintf("Entry number: %d, Virtual address: 0x%x, Physical address: 0x%x\n", i * NPTENTRIES + j, i * NPTENTRIES * PGSIZE + j * PGSIZE, PTE_ADDR(pte[j]));
        }
      }
    }
  }

  cprintf("Number of valid page table entries: %d\n", count);
  cprintf("Total number of page table entries: %d\n", total_count);

  return 0;
}

// int
// sys_pgtPrint(void)
// {
//   uint* pgdir = myproc()->pgdir;
//   uint pde, pte, va, pa;
//   int entry_num = 0;

//   cprintf("Page table entries for process %d:\n", myproc()->pid);

//   for (va = 0; va < KERNBASE; va += PGSIZE) {
//     pde = pgdir[PDX(va)];
//     if (pde & PTE_P) {
//       pte = ((uint*)P2V(PTE_ADDR(pde)))[PTX(va)];
//       if (pte & PTE_P && pte & PTE_U) {
//         pa = PTE_ADDR(pte);
//         cprintf("Entry number: %d, Virtual address: 0x%x, Physical address: 0x%x\n", entry_num, va, pa);
//         entry_num++;
//       }
//     }
//   }

//   cprintf("Number of valid page table entries: %d\n", entry_num);

//   return 0 ;
// }

int sys_fork(void)
{
  return fork();
}

int sys_exit(void)
{
  exit();
  return 0; // not reached
}

int sys_wait(void)
{
  return wait();
}

int sys_kill(void)
{
  int pid;

  if (argint(0, &pid) < 0)
    return -1;
  return kill(pid);
}

int sys_getpid(void)
{
  return myproc()->pid;
}

int sys_sbrk(void)
{
  int addr;
  int n;

  if (argint(0, &n) < 0)
    return -1;
  addr = myproc()->sz;
  if (growproc(n) < 0)
    return -1;
  return addr;
}

int sys_sleep(void)
{
  int n;
  uint ticks0;

  if (argint(0, &n) < 0)
    return -1;
  acquire(&tickslock);
  ticks0 = ticks;
  while (ticks - ticks0 < n)
  {
    if (myproc()->killed)
    {
      release(&tickslock);
      return -1;
    }
    sleep(&ticks, &tickslock);
  }
  release(&tickslock);
  return 0;
}

// return how many clock tick interrupts have occurred
// since start.
int sys_uptime(void)
{
  uint xticks;

  acquire(&tickslock);
  xticks = ticks;
  release(&tickslock);
  return xticks;
}
