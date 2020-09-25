def question52():
    limit = 7

    for i in range(1, 10):
        num = pow(10, i)
        for x in range(num, num+limit):
            digits = sorted(str(2 * x))
            if all(sorted(str(x*k)) == digits for k in range(6,2,-1)):
                return x
        limit = limit*10 + 7

print(question52())