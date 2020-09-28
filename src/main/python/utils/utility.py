import math

def is_prime(n: int):
    if n<0:
        return False

    for i in range(2, math.ceil(math.sqrt(n))):
        if n%i == 0:
            return False
    return True

