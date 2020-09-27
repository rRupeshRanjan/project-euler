def question62():
    cubes = []
    i = 1

    while True:
        cube = sorted(list(str(i**3)))
        cubes.append(cube)
        if cubes.count(cube) == 5:
            return cubes.index(cube)**3
        i = i+1

print(question62())