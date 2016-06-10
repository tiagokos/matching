# matching
Playing with matching algorithms a bit

# problems

- ~~input has to be validated (id / integer)~~
- ~~input has to be from a valid worker (id can be valid but nonexistent in the data set)~~
- what about if list of jobs is huge (billons of jobs... or billon of workers)?
- ~~response has to contain data (3 jobs), no matter what...~~
- is it worth to serialize the whole input?
- should I cache all the possibilities from the beginning and keep results in some in-memory cache?


# questions

- do I have to care about inactive workers?
- is it possible for a job to have 0 workers needed?
- do I have to care about old jobs (start date before than the present day)?
- as a response, is it needed to retrieve the full JSON structure related to a Job? Is the Id enough?


# resolutions

- worker object does not have to be fully serialized since extra properties won`t be used
- 

# vai se fuder
