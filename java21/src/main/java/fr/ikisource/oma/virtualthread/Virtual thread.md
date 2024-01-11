# LOOM : virtual threads

Loom enables you to detach a task from a thread platform.

Concurrency may be used in two different context :
1) Processing in-memory data in parallel using all the CPU cores
    - Each thread uses 100% of the CPU cores
    - No blocking operation

**It is not the use case of loom**, Loom does not accelerate the in-memory computations.

2) Increasing the throughput of your application
 - Your threads are waiting for data
 - So you add more threads

## Thread platform

A thread is not cheap
- Thread startup time : ~ 1ms (1000 threads : 1s, 1M threads : ~ 15 min)
- Thread memory consumption : 2MB of stack
- Context switching : 100 micro s (depends on OS)

Having 1 million of thread platform is not possible

idea : instead of using 1 thread for one request, use one thread for several requests
which leads to the asynchronous programing model.

## Virtual thread

A virtual thread is cheap (1000x cheaper than a platform thread)
- Memory in the order of ~ kB
- Creation time in the order of ~ micro s

You need a virtual thread ? Create it !

Why would you pool it ?
None, so do not pool virtual threads


