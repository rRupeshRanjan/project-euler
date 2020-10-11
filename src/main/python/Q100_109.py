import math
from itertools import combinations
import utils.utility as util

def problem101():
    M = 10
    deff = lambda n: 1-n+n**2-n**3+n**4-n**5+n**6-n**7+n**8-n**9+n**10

    def f(l, x):
        ss = 0
        lk = [k for k, v in l]
        for k, v in l:
            for kk in lk:
                if kk != k: v = v*(x-kk)/(k-kk)
            ss += v
        return ss

    ll = [(k, deff(k)*1.) for k in range(1, M+3)]

    for i in range(1, M+1):
        v = i+1
        assert int(f(ll[:i], v)) != int(ll[v-1][1])

    return int(round(sum(f(ll[:i], i + 1) for i in range(1, M + 1))))

def problem104():
    M = 1000000000
    sqrt5 = math.sqrt(5)
    phi = math.log10((sqrt5+1)/2)
    logsqrt5 = math.log10(sqrt5)

    def is_pandigital(n):
        if n < 100000000: return False
        flags = [0]*10
        flags[0] = 1
        while n > 0:
            n, m = n//10, n%10
            if flags[m]: return False
            flags[m] = 1
        return True

    a, b, k = 1, 1, 2
    while True:
        a, b, k = b, a+b, k+1
        a, b, k = b % M, (a+b)%M, k+1
        if is_pandigital(b):
            phik = phi*k-logsqrt5
            n = int(10**(phik-int(phi*k)+9))//10
            if is_pandigital(n): break
    return k

def problem106():
    s = 0
    n = 12
    for k in range(2, n//2+1):
        for p in combinations(range(n), k):
            qq = [i for i in range(n) if i not in p and i > p[0]]
            for q in combinations(qq, k):
                if not (all(q[i] > p[i] for i in range(k)) or all(q[i] < p[i] for i in range(k))):
                    s += 1
    return s

def problem108():
    primes, _ = util.get_primes_by_sieve(1000)

    def solve2(n):
        u = 1
        m = int(math.sqrt(n))
        for f in primes:
            if f > m:
                u *= 3
                break
            k = 0
            while n % f == 0:
                n //= f
                k += 1
            u *= (2*k+1)
            if n == 1:
                break
        return u

    n = 4
    l = 1000*2-1
    while solve2(n) < l:
        n += 1

    return n

def problem109():
    M = 100
    darts = [0] * 61
    for i in range(1, 61):
        if i <= 20: darts[i] += 1
        if i <= 40 and i % 2 == 0: darts[i] += 1
        if i <= 60 and i % 3 == 0: darts[i] += 1
        if i % 25 == 0: darts[i] += 1
    darts[0] = 1

    s = 0
    for i in range(61):
        for j in range(i, 61):
            if i == j:
                n = darts[i] * (darts[i] + 1) // 2
            else:
                n = darts[i] * darts[j]
            if i + j >= M: break
            k = min(40, M - i - j - 1)//2 + (M - i - j - 1 >= 50)
            s += n * k

    return(s)


# print(problem101())
print(problem104())
# print(problem106())
print(problem108())
# print(problem109())