## Spring Boot Virtual Threads

This is a simple example of how to use Virtual Threads in Spring Boot.

http://localhost:8080/virtual-threads/api-docs

http://localhost:8080/virtual-threads/swagger-ui.html


Pool 1000
Requests 10K

Traditional

Number of requests: 10000
Elapsed time: 38645 ms
Number of requests per seconds: 263
Number of ko requests : 3586
Number of ko requests per seconds: 94


Virtual
Number of requests: 10000
Elapsed time: 42104 ms
Number of requests per seconds: 238
Number of ko requests : 64
Number of ko requests per seconds: 1




Pool 1000
Requests 1K

Traditional

Number of requests: 1000
Elapsed time: 8080 ms
Number of requests per seconds: 125
Number of ko requests : 34
Number of ko requests per seconds: 4

Virtual
Elapsed time: 7068 ms
Number of requests per seconds: 142
Number of ko requests : 0
Number of ko requests per seconds: 0



Pool 400
Requests 10K

Virtual
Number of requests: 10000
Elapsed time: 31333 ms
Number of requests per seconds: 322
Number of ko requests : 1304
Number of ko requests per seconds: 42



Pool 400
Requests 1000
Virtual

Number of requests: 1000
Elapsed time: 7078 ms
Number of requests per seconds: 142
Number of ko requests : 0
Number of ko requests per seconds: 0


Pool 400
Virtual with long running task 30 sec and client 60

Number of requests: 1000
Elapsed time: 32299 ms
Number of requests per seconds: 31
Number of ko requests : 0
Number of ko requests per seconds: 0



Traditional with long running task 30 sec and client 60
Number of requests: 1000
Elapsed time: 61654 ms
Number of requests per seconds: 16
Number of ko requests : 200
Number of ko requests per seconds: 3


Pool 200
Virtual with long running task 30 sec and client 60
Number of requests: 1000
Elapsed time: 32303 ms
Number of requests per seconds: 31
Number of ko requests : 0
Number of ko requests per seconds: 0



Traditional with long running task 30 sec and client 60
Number of requests: 1000
Elapsed time: 62603 ms
Number of requests per seconds: 16
Number of ko requests : 732
Number of ko requests per seconds: 11
