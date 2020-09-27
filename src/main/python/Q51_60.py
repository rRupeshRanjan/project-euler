def question52():
    limit = 7

    for i in range(1, 10):
        num = pow(10, i)
        for x in range(num, num+limit):
            digits = sorted(str(2 * x))
            if all(sorted(str(x*k)) == digits for k in range(6,2,-1)):
                return x
        limit = limit*10 + 7


def question56():
    return max(sum(map(int, str(a**b))) for a in range(90, 100) for b in range(90, 100))


def question57():
    c = 0
    num = den = 1
    for k in range(1000):
        num, den = 2 * den + num, den + num
        if len(str(num)) > len(str(den)):
            c += 1

    return c


print(question52())
print(question56())
print(question57())
