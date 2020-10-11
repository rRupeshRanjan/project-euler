from itertools import product
import math
import utils.utility as util


def problem162():
    s = 0
    for k in range(16):
        s += (15*16**k-15*15**k-14*15**k*2+13*14**k+14*14**k*2-13*13**k)

    return s


def problem164(n):
    ret = {}
    if n == 2:
        for i, j in product(range(10), range(10)):
            ret[i, j] = int(i>0 and i+j<=9)
        return ret
    for (k, i), v in problem164(n - 1).items():
        for j in range(10-i-k):
            ret.setdefault((i, j), 0)
            ret[i, j] += v
    return ret


def problem166():
    pool = [[] for i in range(37)]
    for i1 in range(10):
        for i2 in range(10):
            for i3 in range(10):
                for i4 in range(10):
                    l = (i1, i2, i3, i4)
                    pool[sum(l)].append(l)
    count = 0
    for s, ls in enumerate(pool):
        dd = {}
        for l1 in ls:
            for l2 in ls:
                key = (l1[0]+l2[0], l1[1]+l2[1], l1[2]+l2[2], l1[3]+l2[3], l1[0]+l2[1], l1[3]+l2[2])
                dd.setdefault(key, 0)
                dd[key] += 1
        for (s1, s2, s3, s4, s5, s6), c in dd.items():
            key = (s-s1, s-s2, s-s3, s-s4, s-s6, s-s5)
            count += c * dd.get(key, 0)
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
    return problem169(n, k-1)+problem169(n-kmax, k-1)+problem169(n-kmax*2, k-1)


def problem171():
    n, m = 20, 9
    up = int(math.sqrt(n * 81))
    uplimit = up ** 2 + 1
    d = [[[0, 0] for j in range(uplimit)] for i in range(n)]
    for k in range(10):
        d[0][k*k] = [1, k]
    for i in range(1, n):
        ii = 10 ** i if i < m else 0
        for j in range(uplimit):
            for k in range(10):
                kk = k * k
                if j < kk:
                    break
                p, q = d[i-1][j-kk]
                d[i][j][0] += p
                d[i][j][1] += q + ii * k * p
    return str(sum(d[n-1][k*k][1] for k in range(1, up+1)))[-m:]


def problem173():
    tiles = 10**6
	result = 0
    # find how many n^2 - k^2 will be possible
	for n in range(3, tiles // 4 + 2):  # Outer square length
		for k in range(n - 2, 0, -2):  # Inner square length
			if n * n - k * k > tiles:
				break
			result += 1
	return result


def problem401():
    n,m = 10 ** 15, 10 ** 9
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
print(problem169(10**25))
print(problem171())
print(problem173())
print(problem401())
