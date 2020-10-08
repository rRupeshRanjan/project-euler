from functools import reduce
import operator as op

M = 4000000
L = M*2-1
primes = (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)

min_n = reduce(op.mul, primes, 1)

def get_next(pos, n, k, limit):
    global min_n
    if k >= L:
        if n < min_n: min_n = n
        return True

    for p in range(1, limit+1):
        n *= primes[pos]
        if n > min_n: break
        if get_next(pos+1, n, k*(2*p+1), p): break
    return False

get_next(0, 1, 1, M)

print(min_n)