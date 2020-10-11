import itertools
from itertools import product
import math
import utils.utility as util


def problem162():
    s = 0
    for k in range(16):
        s += (15 * 16 ** k - 15 * 15 ** k - 14 * 15 ** k * 2 + 13 * 14 ** k + 14 * 14 ** k * 2 - 13 * 13 ** k)

    return s


def problem164(n):
    ret = {}
    if n == 2:
        for i, j in product(range(10), range(10)):
            ret[i, j] = int(i > 0 and i + j <= 9)
        return ret
    for (k, i), v in problem164(n - 1).items():
        for j in range(10 - i - k):
            ret.setdefault((i, j), 0)
            ret[i, j] += v
    return ret


def problem166():
    count = 0
    digits = tuple(range(10))
    for b in digits:
        for c in digits:
            for d in digits:
                for e in digits:
                    for i in digits:
                        m = b + c + d - e - i
                        if m < 0 or m > 9: continue
                        for k in digits:
                            f = b + c + d*2 - e - i - k
                            if f < 0 or f > 9: continue
                            for a in digits:
                                for g in digits:
                                    o = a + b + d - g - k
                                    if o < 0 or o > 9: continue
                                    j = a + b + c - g - m
                                    if j < 0 or j > 9: continue
                                    l = a + b + c + d - i - j - k
                                    if l < 0 or l > 9: continue
                                    h = a + b + c + d - e - f - g
                                    if h < 0 or h > 9: continue
                                    n = a + c + d - f - j
                                    if n < 0 or n > 9: continue
                                    p = a + b + c - h - l
                                    if p < 0 or p > 9: continue
                                    count += 1
    return count


@util.memorize
def problem169(n, k=None):
    if n < 0:
        return 0
    if n <= 1:
        return 1
    k2 = int(math.log(n, 2))
    if k is None:
        k = k2
    elif k < k2 - 1:
        return 0
    else:
        k = min(k, k2)
    if k == 0:
        return 1 if n < 3 else 0
    kmax = 2 ** k
    return problem169(n, k - 1) + problem169(n - kmax, k - 1) + problem169(n - kmax * 2, k - 1)


def problem171():
    LENGTH = 20
    BASE = 10
    MODULUS = 10**9

    # Maximum possible squared digit sum (for 99...99)
    MAX_SQR_DIGIT_SUM = (BASE - 1)**2 * LENGTH

    # sqsum[n][s] is the sum of all length-n numbers with a square digit sum of s, modulo MODULUS
    # count[n][s] is the count of all length-n numbers with a square digit sum of s, modulo MODULUS
    sqsum = []
    count = []

    for i in range(LENGTH + 1):
        sqsum.append([0] * (MAX_SQR_DIGIT_SUM + 1))
        count.append([0] * (MAX_SQR_DIGIT_SUM + 1))
        if i == 0:
            count[0][0] = 1
        else:
            for j in range(BASE):
                for k in itertools.count():
                    index = k + j**2
                    if index > MAX_SQR_DIGIT_SUM:
                        break
                    sqsum[i][index] = (sqsum[i][index] + sqsum[i - 1][k] + pow(BASE, i - 1, MODULUS) * j * count[i - 1][k]) % MODULUS
                    count[i][index] = (count[i][index] + count[i - 1][k]) % MODULUS

    ans = sum(sqsum[LENGTH][i**2] for i in range(1, int(math.sqrt(MAX_SQR_DIGIT_SUM))))
    return f"{ans%MODULUS:09}"


def problem173():
    tiles = 10 ** 6
    result = 0
    # find how many n^2 - k^2 will be possible
    for n in range(3, tiles // 4 + 2):  # Outer square length
        for k in range(n - 2, 0, -2):  # Inner square length
            if n * n - k * k > tiles:
                break
            result += 1
    return result


def problem401():
    n, m = 10 ** 15, 10 ** 9
    s, t = 0, int(math.sqrt(n))
    cache = [(n // i % m) for i in range(1, n // t + 1)]
    for i in range(t - 1):
        k = cache[i]
        s += k * (k + 1) * (2 * k + 1) // 6
    k = cache[t - 1]
    s -= (t - 1) * k * (k + 1) * (2 * k + 1) // 6
    s %= m
    for i in range(n // t):
        s += (i + 1) ** 2 * cache[i]
    return s % m


print("%X" % problem162())
print(sum(problem164(20).values()))
print(problem166())
print(problem169(10 ** 25))
print(problem171())
print(problem173())
# print(problem401())
