def question50():
    limit = 1000000
    primes = list()
    notPrimes = [0] * limit
    notPrimes[0] = 1
    notPrimes[1] = 1

    for i in range(2, limit-1):
        if notPrimes[i] == 0:
            primes.append(i)
            for j in range(i*i, limit, i):
                notPrimes[j] = 1

    maxSum = 0
    maxRun = -1

    for i in range(0, len(primes)):
        sum = 0
        for j in range(i, len(primes)):
            sum += primes[j]
            if sum>=limit-1:
                break
            if notPrimes[sum]==0 and sum > maxSum and j-i > maxRun:
                maxSum = sum
                maxRun = j-i

    return maxSum

def question48():
    def modPower(base, exp, mod):
        rem = 1
        while exp > 0:
            if exp%2!=0:
                rem = (rem * base) % mod
            base = (base * base) % mod
            exp = exp >> 1
        return rem

    def powersum(n, m):
        s = 0
        for i in range(1, n + 1):
            s+= modPower(i, i, m)
            s%= m
        return s

    return powersum(1000, 10000000000)

print(question48())
print(question50())
