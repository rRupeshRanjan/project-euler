from functools import reduce
from itertools import product
import math
import utils.utility as util
import operator as op


@util.memorize
def problem114():
    L = 3

    def tiles(n):
        return 1 + sum((k-L+1)*tiles(n-k-1) for k in range(L, n+1))

    return tiles(50)


def problem115():
    M = 50
    L = 1000000

    @util.memorize
    def tiles(m, n):
        return 1 + sum((k-m+1)*tiles(m, n-k-1) for k in range(m, n+1))

    n = 1
    while True:
        n += 1
        if tiles(M, n) > L:
            break
    return(n)


def problem116():
    N = 50
    M = (2, 3, 4)

    @util.memorize
    def tiles(n, m):
        return 1 + sum(tiles(k, m) for k in range(n-m+1))

    return sum(tiles(N, k)-1 for k in M)


def problem117():
    N = 50
    M = (2, 3, 4)

    @util.memorize
    def tiles(n):
        return 1 + sum(tiles(k) for m in M for k in range(n-m+1))

    return tiles(N)


def problem119():
    L = 30

    ret = set()
    for a in range(100):
        x = a
        for b in range(20):
            x *= a
            if x < 10: continue
            if sum(int(i) for i in str(x)) == a: ret.add(x)

    return sorted(ret)[L-1]


def problem120():
    return sum(n*(n-2+n%2) for n in range(3, 1001))


def problem121():
    N = 15
    s = 0
    for l in product(*([(0, 1)]*N)):
        if sum(l) * 2 > N:
            s += reduce(op.mul,
                        ((i + 1) if v == 0 else 1 for i, v in enumerate(l)),1)

    return math.factorial(N+1)//s


print(problem114())
print(problem115())
print(problem116())
print(problem117())
print(problem119())
print(problem120())
print(problem121())
