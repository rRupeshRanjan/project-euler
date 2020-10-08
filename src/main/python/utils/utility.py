import math

def memorize(func):
    pool = {}
    def wrapper(*arg):
        if arg not in pool:
            pool[arg] = func(*arg)
        return pool[arg]
    return wrapper


def is_prime(n: int):
    if n<0:
        return False

    for i in range(2, math.ceil(math.sqrt(n))):
        if n%i == 0:
            return False
    return True


def sieve(n):
    marked = [i % 2 for i in range(n)]
    marked[1] = 0
    marked[2] = 1
    for value in range(3, n, 2):
        if marked[value] == 1:
            for i in range(value*3, n, value*2):
                marked[i] = 0
    return marked


def get_primes_by_sieve(m, odd_only=False):
    sieves = sieve(m)
    if odd_only:
        primes = [i for i, f in enumerate(sieves) if f and i != 2]
    else:
        primes = [i for i, f in enumerate(sieves) if f]
    return primes, sieves